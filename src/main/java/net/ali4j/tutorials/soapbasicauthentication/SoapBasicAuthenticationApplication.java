package net.ali4j.tutorials.soapbasicauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Base64;

@SpringBootApplication
public class SoapBasicAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapBasicAuthenticationApplication.class, args);

		String ss = new String(Base64.getEncoder().encode("user:pass".getBytes()));
		System.out.println("encoded value:" + ss);
	}

}
