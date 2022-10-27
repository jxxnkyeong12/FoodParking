package common;

import java.io.BufferedReader;
import java.io.File;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.json.simple.JSONObject;
import org.springframework.util.FileCopyUtils;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@RestController
public class CommonService {

	// API요청 - sb
	public String requestAPI(StringBuffer apiUrl) {
		String url = apiUrl.toString();
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			con.disconnect();
			url = res.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return url;
	}

	// API - sb
	public String requestAPI(StringBuffer apiUrl, String property) {
		String url = apiUrl.toString();
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", property);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			con.disconnect();
			url = res.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return url;
	}

	// 문자인증 서비스 하려고 시도시도 -jk
	public void certifiedPhoneNumber(String userPhoneNumber, int randomNumber) {
		String api_key = "NCSOXCJRPONYJEND";
		String api_secret = "XNPWDDPMBHURXZ70TJXWJUXXAEMHNIIL";
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", userPhoneNumber); // 수신전화번호
		params.put("from", "01046639823"); // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
		params.put("type", "SMS");
		params.put("text", "[푸드파킹] 인증번호는" + "[" + randomNumber + "]" + "입니다.");
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}

	}

	// 첨부파일 다운로드
	public void fileDownload(String filename, String filepath, HttpServletResponse response,
			HttpServletRequest request) {
		// 다운로드할 파일객체를 생성
		// DB저장
		// http://192.168.0.2/iot/upload/notice/2022/08/25/21d94db7-f9c5-4b1b-9229-f4799fe01716_kakao_login.zip
		// 실제파일
		// d://app/iot/upload/notice/2022/08/25/21d94db7-f9c5-4b1b-9229-f4799fe01716_kakao_login.zip
		// http://192.168.0.2/iot --> d://app/iot
		filepath = "\\\\301-12" + request.getContextPath() + filepath;
		File file = new File(filepath);

		// 클라이언트에 쓰기작업할 ContentType 을 지정한다: MimeType
		String mime = request.getSession().getServletContext().getMimeType(filepath);
		response.setContentType(mime);

		try {
			filename = URLEncoder.encode(filename, "utf-8");
			response.setHeader("content-disposition", "attachment; filename=" + filename);

			ServletOutputStream out = response.getOutputStream();
			FileCopyUtils.copy(new FileInputStream(file), out);
			out.flush();
		} catch (Exception e) {
		}

		// Input / Output - byte, 문자
		// FileInputStream / FileOutputStream
		// FileReader/ FileWriter
		// BufferedReader ( new FileReader )
	}

	// 첨부파일 업로드 -jk
	public String fileUpload(String category, MultipartFile file, HttpServletRequest request) {

		String path = "\\\\301-12" + request.getContextPath();
		String upload = "/upload/" + category;

		path += upload;

		// 해당 경로 폴더가 없으면 만든다
		File folder = new File(path);
		if (!folder.exists())
			folder.mkdirs();

		String uuid = "_" + file.getOriginalFilename();

		try {
			file.transferTo(new File(path, uuid));
		} catch (Exception e) {

		}

		return appName(request) + upload + "/" + uuid;
	}

	public String appName(HttpServletRequest req) {
		return req.getRequestURL().toString().replace(req.getServletPath(), "");
	}

	// 첨부파일 업로드 -jk
	public String fileUploadhw(String category, MultipartFile file, HttpServletRequest request) {

		String path = "////301-12" + request.getContextPath();
		String upload = "/upload/" + category;

		path += upload;

		// 해당 경로 폴더가 없으면 만든다
		File folder = new File(path);
		if (!folder.exists())
			folder.mkdirs();
		String uuid = "_" + file.getOriginalFilename();

		try {
			file.transferTo(new File(path, uuid));
		} catch (Exception e) {

		}
		return upload + "/" + uuid;
	}

	// 아이디, 비밀번호를 이메일로 전송 - ssb
	public boolean sendPassword(String email, String name, String pw) {
		HtmlEmail mail = new HtmlEmail();
		// 메일서버지정
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true); // Console을 통해 메일전송과정 확인

		// 로그인하기
		mail.setAuthentication("ssb9701", "ssb97015364");
		// 로그인버튼 클릭하기
		mail.setSSLOnConnect(true);
		boolean send = true;

		try {

			// 메일 보내는이 지정
			// 보내는 이메일주소: 이메일아이디@naver.com
			mail.setFrom("ssb9701@naver.com", "푸드파킹관리자");
			// 메일 받는이 지정
			mail.addTo(email, name);

			// 메일제목
			mail.setSubject("푸드파킹 임시비밀번호 발급");

			// 메일내용
			StringBuffer msg = new StringBuffer();
			msg.append("<html>");
			msg.append("<body>");
			msg.append("<h3>[").append(name).append("]님 임시비밀번호 발급</h3>");
			msg.append("<p>발급된 임시 비밀번호로 로그인 후 비밀번호를 변경하세요</p>");
			msg.append("<p>임시 비밀번호: <strong>").append(pw).append("</strong></p>");
			msg.append("</body>");
			msg.append("</html>");

			mail.setHtmlMsg(msg.toString());

			// 메일보내기버튼 클릭
			mail.send();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			send = false;
		}
		return send;
	}

	
	// 아이디, 비밀번호를 이메일로 전송 - ssb
	public boolean sendEmail(String email, String name, String result, int status) {
		HtmlEmail mail = new HtmlEmail();
		// 메일서버지정
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true); // Console을 통해 메일전송과정 확인

		// 로그인하기
		mail.setAuthentication("ssb9701", "ssb97015364");
		// 로그인버튼 클릭하기
		mail.setSSLOnConnect(true);
		boolean send = true;

		try {
			
			// 메일 보내는이 지정
			// 보내는 이메일주소: 이메일아이디@naver.com
			mail.setFrom("ssb9701@naver.com", "푸드파킹관리자");
			// 메일 받는이 지정
			mail.addTo(email, name);

			// 메일제목
			mail.setSubject("푸드파킹 입점 신청결과");

			// 메일내용
			if(status == 2) {
				StringBuffer msg = new StringBuffer();
				msg.append("<html>");
				msg.append("<body>");
				msg.append("<h3>[").append(name).append("]입점 신청결과</h3>");
				msg.append("<p>입점신청이 완료 되었습니다</p>");
				msg.append("<p>입점을 환영 합니다환영합니다!!</p>");
				msg.append("</body>");
				msg.append("</html>");
				mail.setHtmlMsg(msg.toString());
			}else {
				StringBuffer msg = new StringBuffer();
				msg.append("<html>");
				msg.append("<body>");
				msg.append("<h3>[").append(name).append("]입점 신청결과</h3>");
				msg.append("<p>입점 신청이 실패 하셨습니다</p>");
				msg.append("<p>실패 사유: <strong>").append(result).append("</strong></p>");
				msg.append("</body>");
				msg.append("</html>");
				mail.setHtmlMsg(msg.toString());
			}
			// 메일보내기버튼 클릭
			mail.send();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			send = false;
		}
		return send;
	}
	


	// 비밀번호를 암호화하는데 사용할 솔트생성
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

	// salt 문자열을 사용해 비밀번호를 암호화하는 처리
	public String getEncrypt(String salt, String pw) {
		pw += salt;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(pw.getBytes());

			byte[] bytes = digest.digest();
			StringBuffer data = new StringBuffer();
			for (byte b : bytes) {
				data.append(String.format("%02x", b)); // 16진수로 변환 00~09
			}
			pw = data.toString();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pw;
	}

}
