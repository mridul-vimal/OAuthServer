package com.vimal.authserver.entity;

import lombok.*;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TokenRequest {
    @JsonProperty(value = "username")
    private String userName;
    private String password;
    @JsonProperty(value = "grant_type")
    private String grantType;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("client_secret")
    private String clientSecret;
}
