package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	@GetMapping("/getRestDeInfo")
	public String callRestDeInfo() {

//		HashMap<String, Object> result = new HashMap<String, Object>();

		StringBuffer result = new StringBuffer();

		try {

//			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//			factory.setConnectTimeout(5000);
//			factory.setReadTimeout(5000);
//			RestTemplate restTemplate = new RestTemplate(factory);
//
//			HttpHeaders header = new HttpHeaders();
//			HttpEntity<?> entity = new HttpEntity<>(header);
//
//			String url = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";
//			UriComponentsBuilder uri = UriComponentsBuilder.fromPath(url)
//														.queryParam("ServiceKey", "znWF0YRgwv7pzqf%2Bo6ggglOcOQgogMD2pxqsDpXmH9bg4imY01PgCvKWnrgFlvLCbMX4VOj%2F%2Fgeu8wDqBHFbpw%3D%3D")
//														.queryParam("solYear", "2020")
//														.queryParam("solMonth", "10")
//														.queryParam("_type", "json");
//			System.out.println(uri);
//			// 이 한줄의 코드로 API를 호출해 MAP타입으로 전달 받는다.
//			ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
//			result.put("statusCode", resultMap.getStatusCodeValue()); // http status code를 확인
//			result.put("header", resultMap.getHeaders()); // 헤더 정보 확인
//			result.put("body", resultMap.getBody()); // 실제 데이터 정보 확인
//
//			// 데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌
//			ObjectMapper mapper = new ObjectMapper();
//			jsonInString = mapper.writeValueAsString(resultMap.getBody());
			
			
			String urlStr = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?" +
			"ServiceKey=znWF0YRgwv7pzqf%2Bo6ggglOcOQgogMD2pxqsDpXmH9bg4imY01PgCvKWnrgFlvLCbMX4VOj%2F%2Fgeu8wDqBHFbpw%3D%3D" +
			"&solYear=2020" +
			"&solMonth=10" +
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
}