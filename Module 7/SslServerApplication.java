package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;

import javax.validation.constraints.Size;



@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}

@RestController
class ServerController{
//FIXME:  Add hash function to return the checksum value for the data string that should contain your name.    
   
	@GetMapping("/hash")
	public String greeting(@RequestParam(value = "name", defaultValue = "Brooke Slampak") @Size(max = 25) String name) throws Exception {
		return myHash(name);
	}	
	
	//@RequestMapping("/hash")
    public String myHash(String data) throws Exception{
    	
    	//Source/reference: https://www.tutorialspoint.com/java_cryptography/java_cryptography_message_digest.htm
    	
    	MessageDigest md = MessageDigest.getInstance("SHA-256");
    	
    	md.update(data.getBytes());
    	
    	byte[] digest = md.digest();
    	
        StringBuffer hexString = new StringBuffer();
        
        for (int i = 0;i<digest.length;i++) {
           hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        
        String returnString = String.format("<p>Hello %s!", data);
        returnString += "<p>data: This is your checksum verification.";
        returnString += "<p> SHA-265 Checksum Value: ";
    	        
        return  returnString + hexString;
    }
	
}
