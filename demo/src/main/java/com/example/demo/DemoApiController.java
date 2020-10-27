package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.SecureRandom;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ws.transport.http.HttpUrlConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
public class DemoApiController {
	
	private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
	
	@GetMapping("/demoapistring")
	public String demoapistring() {
		return "데모 스트링 타입 리턴";
	}

	@CrossOrigin(origins = "http://localhost:6200")
	@GetMapping("/demoapi")
	public Map<String, Object> demoapi() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "송하영");
		map.put("birthday", "19910621");
		map.put("Return", true);
		return map;
	}
	
	@CrossOrigin(origins = "http://localhost:6200")
	@GetMapping("/getMovie")
	public String callGetMovie(HttpServletRequest request) {
		StringBuffer result = new StringBuffer();
		String keyword = request.getParameter("keyword");
		int display = Integer.parseInt(request.getParameter("display"));
		try {
			URL url;
            url = new URL("https://openapi.naver.com/v1/search/"
                    + "movie.json?query="
                    + URLEncoder.encode(keyword, "UTF-8")
                    + (display !=0 ? "&display=" +display :""));
 
            URLConnection urlConn = url.openConnection();
            urlConn.setRequestProperty("X-Naver-Client-Id", "rkGs4ri_LDBJWxH4thyy");
            urlConn.setRequestProperty("X-Naver-Client-Secret", "VuINpm3hPd");
            
    		BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
    		String returnLine;
    		result.append("");
    		while((returnLine = br.readLine()) != null) {
    			result.append(returnLine + "\n");
    		}
    					
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result+"";
	}
	
	@CrossOrigin(origins = "http://localhost:6200")
	@GetMapping("/getRestDeInfo")
	public String callRestDeInfo(HttpServletRequest request) {

		StringBuffer result = new StringBuffer();

		try {

			String year = request.getParameter("year");
			String month = request.getParameter("month");
			
			String urlStr = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?" +
			"ServiceKey=znWF0YRgwv7pzqf%2Bo6ggglOcOQgogMD2pxqsDpXmH9bg4imY01PgCvKWnrgFlvLCbMX4VOj%2F%2Fgeu8wDqBHFbpw%3D%3D" +
			"&solYear=" + year +
			"&solMonth=" + month +
			"&_type=json";
			
			URL url = new URL(urlStr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			String returnLine;
			result.append("");
			while((returnLine = br.readLine()) != null) {
				result.append(returnLine + "\n");
			}
			urlconnection.disconnect();

		} catch (HttpClientErrorException | HttpServerErrorException e) {			
			System.out.println(e.toString());

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result+"";
	}
	
	@CrossOrigin(origins = "http://localhost:6200")
	@GetMapping("/doLogin")
	public String doLogin(HttpServletRequest request) {
		
		//로그인 토큰을 return시킴;
		String token = generateNewToken();
		return token;
	}
	
	public static String generateNewToken() {
		
	    byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    return base64Encoder.encodeToString(randomBytes);
	}
	
//	public String generateToken() {
//	    String token = null;
//	    SecureRandom secureRandom;
//	    try {
//	        secureRandom = SecureRandom.getInstance("SHA1PRNG");
//	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
//	        secureRandom.setSeed(secureRandom.generateSeed(128));
//	        token= new String(digest.digest((secureRandom.nextLong() + "").getBytes()));
//	    } catch (NoSuchAlgorithmException e) {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//	    }
//	    return token;
//	}
	
}