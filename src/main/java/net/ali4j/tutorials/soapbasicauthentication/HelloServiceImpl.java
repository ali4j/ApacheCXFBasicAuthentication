package net.ali4j.tutorials.soapbasicauthentication;

import org.apache.cxf.interceptor.InInterceptors;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@Component
@WebService(
        targetNamespace = "net.ali4j.tutorials.soapbasicauthentication",
        endpointInterface = "net.ali4j.tutorials.soapbasicauthentication.HelloService")
@InInterceptors(interceptors = {"net.ali4j.tutorials.soapbasicauthentication.BasicAuthenticationInterceptor"})
public class HelloServiceImpl implements HelloService{

    private final static String HELLO_PREFIX = "Hello ";

    @Override
    public String sayHello(String name) {
        return HELLO_PREFIX + name;
    }
}
