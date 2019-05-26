package net.ali4j.tutorials.soapbasicauthentication;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthenticationUserCredentials {

    private String username;
    private String password;

    public BasicAuthenticationUserCredentials(@NotNull String authorizationHeader) {
        String base64EncodedPart = authorizationHeader.substring(6);
        String userCredentials = new String(Base64.getDecoder().decode(base64EncodedPart.getBytes(StandardCharsets.UTF_8)));
        String[] strings = userCredentials .split("\\:");
        this.username = strings[0];
        this.password = strings[1];
    }

    public String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
