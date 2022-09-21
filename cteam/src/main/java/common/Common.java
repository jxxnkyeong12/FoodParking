package common;

import java.security.MessageDigest;
import java.security.SecureRandom;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Common {
 
	
	//비밀번호를 암호화 하는데 사용할 salt생성 jk 2022/09/21
	public String generateSalt() {
		
		SecureRandom r	= new SecureRandom();
		 byte[] bytes = new byte[24]; 
		r.nextBytes(bytes); 
		
		StringBuffer data = new StringBuffer();
		for( byte b : bytes ) {
			
			data.append( String.format("%02x", b) ) ; 
		}
		
		return	data.toString(); 
		
	}
	
	//문자열을 salt를 사용해 비밀번호를 암호화하는 처리 jk - 2022/09/21
		public String getEncrypt(String salt, String pw) { 
			pw += salt; 
			
					 try {
						 MessageDigest digest = MessageDigest.getInstance("SHA-256");
						 digest.update(pw.getBytes());
						 
						byte[] bytes =  digest.digest();
						StringBuffer data = new StringBuffer();
						for( byte b  : bytes) {
							
							data.append(	String.format("%x", b) ); 
						}
						
						pw =   data.toString();
						
						
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					 return pw;
			
		}
	
}
