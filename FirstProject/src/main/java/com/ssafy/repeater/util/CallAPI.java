package com.ssafy.repeater.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CallAPI {
	static String root = "https://solved.ac/api/v3/";
	static RestTemplate restTemplate = new RestTemplate();
	private static CallAPI instance = new CallAPI();

	private CallAPI() {
	}

	public static CallAPI getInstance() {
		return instance;
	}

	public static Map<String, Object> getUserShow(String handle) {
		Map<String, Object> result = restTemplate.getForObject(root + "user/show?handle=" + handle, Map.class);
		return result;
	}

	public List<Map<String, Object>> getProblemStats(String handle) {
		List<Map<String,Object>> result = restTemplate.getForObject(root + "user/problem_stats?handle=" + handle,
				List.class);
		for (Map<String, Object> map : result) {
			map.put("tier", ParseUtil.getGrade4Level((int) map.get("level")));
		}
		return result;
	}

}
