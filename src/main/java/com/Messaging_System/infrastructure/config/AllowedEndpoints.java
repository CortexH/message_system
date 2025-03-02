package com.Messaging_System.infrastructure.config;

public class AllowedEndpoints {

    public static String URL_FOR_SWAGGER = "localhost:6969";

    public static String[] userAllowedEndpoints = {
            "/data/user/get-starter-data", "/data/message/get-from-user"
    };

    public static String[] noAuthorizationAllowedEndpoints = {
            "/authentication/register", "/authentication/login",
            "/authentication/tag-recommendation", "/user-message-ws",
            "/h2-console/**", "/swagger-ui/**", "/v2/**", "/v3/**"
    };

    public static String[] onlyForAdmsEndpoints = {

    };

}
