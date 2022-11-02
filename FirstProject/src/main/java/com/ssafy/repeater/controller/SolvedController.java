package com.ssafy.repeater.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.repeater.util.CallAPI;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/solved")
@Slf4j
public class SolvedController {
	CallAPI callAPI = CallAPI.getInstance();

	@GetMapping("/user/show")
	public ResponseEntity<?> getUserShow(@RequestParam String handle) {
		log.info("getUserShow Call {}", handle);
		Map<String, Object> result = callAPI.getUserShow(handle);
		log.info("getUserShow Result : {}", result);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/user/problem_stats")
	public ResponseEntity<?> getProblemStats(@RequestParam String handle) {
		log.info("getUserShow Call {}", handle);
		List<Map> result = callAPI.getProblemStats(handle);
		log.info("getUserShow Result : {}", result);
		return new ResponseEntity<List<Map>>(result, HttpStatus.OK);
	}
}
