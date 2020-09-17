package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApiController {
	@GetMapping("/demoapistring")
	public String demoapistring() {
		return "���� ��Ʈ�� Ÿ�� ����";
	}
	@GetMapping("/demoapi")
	public Map<String, Object> demoapi() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "ȫ�浿");
		map.put("birthday", 15920505);
		return map;
	}
}