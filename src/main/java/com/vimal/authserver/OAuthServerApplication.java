package com.vimal.authserver;

import com.vimal.authserver.config.Account;
import com.vimal.authserver.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
@Slf4j
public class OAuthServerApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OAuthServerApplication.class, args);
	}

	@Bean
	public PasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private AccountRepository repository;
	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();
		repository.save(Account.builder().userName("mridul").password("password").build());
		log.info("It works");

	}
}
