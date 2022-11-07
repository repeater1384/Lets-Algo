package com.ssafy.repeater.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlingUtil {
	static public List<Map<String, Object>> doCrawl(String id, String problemNo) {
		String url = String.format("https://www.acmicpc.net/status?problem_id=%s&user_id=%s", problemNo, id);
//		if (problemNo.equals(""))
//			return null;

		Connection conn = Jsoup.connect(url);

		try {
			List<Map<String, Object>> result = new ArrayList<>();
			Document document = conn.get();
			Element element = document.getElementById("status-table");
			String[] keyNames = { "submitNo", "id", "problemNo", "result", "memory", "time", "lang", "codeLen" };
			for (Element row : element.select("tbody tr")) {
				Map<String, Object> resultMap = new HashMap<>();
				List<Element> realData = row.getElementsByTag("td");
				for (int i = 0; i < keyNames.length; i++) {
					resultMap.put(keyNames[i], realData.get(i).text());
				}
				String submitDate = row.getElementsByClass("real-time-update show-date ").attr("title");
				System.out.println(submitDate);
				resultMap.put("submitDate", submitDate);
				
				// 맞는 문제 번호가 없어서, 디폴트로 검색된 경우는 null을 리턴하자
				if (!resultMap.get("problemNo").equals(problemNo))
					return null;
				
				result.add(resultMap);
			}
			
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		final String inflearnUrl = "https://www.inflearn.com/courses/it-programming";
		Connection conn = Jsoup.connect(inflearnUrl);

		try {
			Document document = conn.get();
			Elements imageUrlElements = document.getElementsByClass("swiper-lazy");

			for (Element element : imageUrlElements) {
				System.out.println(element);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}