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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@RestController
public class CommonService {
      
      //API요청 - sb
      public String requestAPI(StringBuffer apiUrl) {
         String url = apiUrl.toString();
          try {
              HttpURLConnection con = (HttpURLConnection)new URL(url).openConnection();
              con.setRequestMethod("GET");
              int responseCode = con.getResponseCode();
              BufferedReader br;
              if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
              } else {  // 에러 발생
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
      
      //API - sb
      public String requestAPI(StringBuffer apiUrl, String property) {
         String url = apiUrl.toString();
         try {
            HttpURLConnection con = (HttpURLConnection)new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", property);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
               br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            } else {  // 에러 발생
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
   
   
   //문자인증 서비스 하려고 시도시도 -jk 
   public void certifiedPhoneNumber(String userPhoneNumber, int randomNumber) {
      String api_key = "NCSOXCJRPONYJEND";
       String api_secret = "XNPWDDPMBHURXZ70TJXWJUXXAEMHNIIL";
       Message coolsms = new Message(api_key, api_secret);

       // 4 params(to, from, type, text) are mandatory. must be filled
       HashMap<String, String> params = new HashMap<String, String>();
       params.put("to", userPhoneNumber);    // 수신전화번호
       params.put("from", "01046639823");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
       params.put("type", "SMS");
       params.put("text", "[푸드파킹] 인증번호는" + "["+randomNumber+"]" + "입니다.");
       params.put("app_version", "test app 1.2"); // application name and version

       try {
           JSONObject obj = (JSONObject) coolsms.send(params);
           System.out.println(obj.toString());
         } catch (CoolsmsException e) {
           System.out.println(e.getMessage());
           System.out.println(e.getCode());
         }
   }
   
   //첨부파일 업로드 -jk
   public String fileUpload(String category, MultipartFile file, HttpServletRequest request ) {
 
      String path = "d://" + request.getContextPath(); 
      String upload = "/upload/" + category ;
   
      path +=upload;
      
      //해당 경로 폴더가 없으면 만든다
      File folder = new File(path);
      if(!folder.exists()) folder.mkdirs(); 
          String uuid = "_" + file.getOriginalFilename();
      
      try {
         file.transferTo(new File(path, uuid ));
      } catch (Exception e) {
      }
      return appName(request) + upload + "/" + uuid;
   }
   
   public String appName(HttpServletRequest req) { 
     return req.getRequestURL().toString().replace(req.getServletPath(), ""); 
   }

   
   //첨부파일 업로드 -jk
   public String fileUploadhw(String category, MultipartFile file, HttpServletRequest request ) {
 
      String path = "d://" + request.getContextPath(); 
      String upload = "/upload/" + category ;
                  
   
      path +=upload;
      
      //해당 경로 폴더가 없으면 만든다
      File folder = new File(path);
      if(!folder.exists()) folder.mkdirs(); 
          String uuid    = "_" + file.getOriginalFilename();
      
      try {
         file.transferTo(new File(path, uuid ));
      } catch (Exception e) {
         
         
      }
      return upload + "/" + uuid;
   }
  }