package com.Messaging_System.infrastructure.config;

public class AllowedEndpoints {

    public static String[] userAllowedEndpoints = {
            "/data/user/get-starter-data", "/data/message/get-from-user"
    };

    public static String[] noAuthorizationAllowedEndpoints = {
            "/authentication/register", "/authentication/login",
            "/authentication/tag-recommendation", "/user-message-ws"
    };

    public static String[] onlyForAdmsEndpoints = {

    };

}
