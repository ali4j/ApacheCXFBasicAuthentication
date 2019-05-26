package net.ali4j.tutorials.soapbasicauthentication;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

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

        String authorization = request.getHeader("Authorization");

        if (authorization == null || authorization.isEmpty())
            unauthenticated();

        else {

            BasicAuthenticationUserCredentials credentials = new BasicAuthenticationUserCredentials(authorization);

            if (!credentials.getUsername().equals(DEFAULT_USERNAME) || !credentials.getPassword().equals(DEFAULT_PASSWORD))
                unauthenticated();
        }
    }


    private void unauthenticated() {
        throw new RuntimeException("invalid credentials");
    }

}
