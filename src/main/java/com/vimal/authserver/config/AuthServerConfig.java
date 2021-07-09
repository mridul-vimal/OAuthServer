package com.vimal.authserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@Slf4j
public class AuthServerConfig  extends AuthorizationServerConfigurerAdapter {


    @Autowired
    PasswordEncoder encoder;

    @Value("${app.oauth.client-secret}")
    private String clientSecret;

    @Value("${app.oauth.client-id}")
    private String clientId;

    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
       security.checkTokenAccess("isAuthenticated")
               .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       clients.inMemory()
               .withClient(clientId)
               .secret(encoder.encode(clientSecret))
              // .authorizedGrantTypes("password")
               .authorizedGrantTypes("password","client_credentials")
               .accessTokenValiditySeconds(20000)
               .refreshTokenValiditySeconds(20000)
               .scopes("any")
               .and()
               .withClient("resource-server")
               .secret(encoder.encode("secret"))
               .authorizedGrantTypes("password")
               .scopes("any");
    }



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new InMemoryTokenStore()).authenticationManager(authenticationManager);
    }


//    @Bean
//    public JwtAccessTokenConverter tokenConverter() {
//        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
//        tokenConverter.setSigningKey(PRIVATE_KEY);
//        tokenConverter.setVerifierKey(PUBLIC_KEY);
//        return tokenConverter;
//    }
//
//    @Bean
//    public JwtTokenStore tokenStore() {
//        return new JwtTokenStore(tokenConverter());
//    }

}
