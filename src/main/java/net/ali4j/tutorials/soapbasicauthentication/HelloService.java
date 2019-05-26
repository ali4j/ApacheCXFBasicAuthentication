package net.ali4j.tutorials.soapbasicauthentication;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "Hello")
public interface HelloService {

    @WebMethod
    String sayHello(String name);
}
