package net.ali4j.tutorials.soapbasicauthentication;

import javax.validation.constraints.NotNull;

public class BasicAuthenticationUserCredentials {

    private String username;
    private String password;

    public BasicAuthenticationUserCredentials(@NotNull String authorizationHeader) {
        String[] strings = authorizationHeader.substring(5).split("\\:");
        this.username = strings[0];
        this.password = strings[1];
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
