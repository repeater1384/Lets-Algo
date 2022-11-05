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

	public List<Map> getProblemStats(String handle) {
		List<Map> result = restTemplate.getForObject(root + "user/problem_stats?handle=" + handle, List.class);
		for (Map map : result) {
			map.put("tier", GetGrade4Level.getGrade((int) map.get("level")));
		}
		return result;
	}

	public List<Map> getProblemStats(int problemId) {
		List<Map> result = restTemplate.getForObject(root + "problem/show?problemId=" + problemId, List.class);
		for (Map map : result) {
			log.info("{} -> {}", map.get("level"), GetGrade4Level.getGrade((int) map.get("level")));
		}
		return result;
	}

}
