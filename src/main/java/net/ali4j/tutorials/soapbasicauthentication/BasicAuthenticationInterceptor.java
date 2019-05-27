package net.ali4j.tutorials.soapbasicauthentication;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class BasicAuthenticationInterceptor extends AbstractPhaseInterceptor<Message> {

    public BasicAuthenticationInterceptor() {
        super(Phase.PRE_INVOKE);
    }

    private static final String DEFAULT_USERNAME = "username";
    private static final String DEFAULT_PASSWORD = "password";

    @Override
    public void handleMessage(Message message) throws Fault {
        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);

        Optional<String> authorizationOptional =
                Optional.of(request.getHeader("Authorization").trim())
                        .filter(s -> !s.isEmpty());

        if (authorizationOptional.isPresent()) {
            if(!checkAuthentication(authorizationOptional.get())) unauthenticated();
        }
        else unauthenticated();
    }

    private boolean checkAuthentication(String authenticationHeaderValue) {
        BasicAuthenticationUserCredentials credentials =
                new BasicAuthenticationUserCredentials(authenticationHeaderValue);
        return credentials.getUsername().equals(DEFAULT_USERNAME) && credentials.getPassword().equals(DEFAULT_PASSWORD);

    }


    private void unauthenticated() {
        throw new RuntimeException("invalid credentials");
    }

}
