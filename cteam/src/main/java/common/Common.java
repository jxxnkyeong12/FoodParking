package common;

import java.io.File;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Common {

	
	//첨부파일 업로드 
	public String fileUpload(String category, MultipartFile file, HttpServletRequest request ) {

//		String path = "\\\\301-12\\공용" + request.getContextPath(); 
		String path = "d://" + request.getContextPath(); 
		String upload = "/upload/" + category 
						+ new SimpleDateFormat("/yyyy/MM/dd").format(new Date()); 
	
		path +=upload;
		
		//해당 경로 폴더가 없으면 만든다
		File folder = new File(path);
		if(!folder.exists()) folder.mkdirs(); 
	    	String uuid	 = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		
		try {
			file.transferTo(new File(path, uuid ));
		} catch (Exception e) {
			
			
		}
		return appName(request) + upload + "/" + uuid;
	}
	
	public String appName(HttpServletRequest req) { 
	  return req.getRequestURL().toString().replace(req.getServletPath(), ""); 
	}
	
	
	
	// 비밀번호를 암호화 하는데 사용할 salt생성 jk 2022/09/21
	public String generateSalt() {

		SecureRandom r = new SecureRandom();
		byte[] bytes = new byte[24];
		r.nextBytes(bytes);

		StringBuffer data = new StringBuffer();
		for (byte b : bytes) {

			data.append(String.format("%02x", b));
		}

		return data.toString();

	}

	// 문자열을 salt를 사용해 비밀번호를 암호화하는 처리 jk - 2022/09/21
	public String getEncrypt(String salt, String pw) {
		pw += salt;

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(pw.getBytes());

			byte[] bytes = digest.digest();
			StringBuffer data = new StringBuffer();
			for (byte b : bytes) {

				data.append(String.format("%x", b));
			}

			pw = data.toString();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pw;

	}

}
