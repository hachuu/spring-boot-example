package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApiController {
	@GetMapping("/demoapistring")
	public String demoapistring() {
		return "데모 스트링 타입 리턴";
	}
	@GetMapping("/demoapi")
	public Map<String, Object> demoapi() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "홍길동");
		map.put("birthday", 15920505);
		return map;
	}
}