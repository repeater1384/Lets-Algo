package com.ssafy.repeater.util;

public class ParseUtil {
	// solved.ac API 에서 제공하는 문제 레벨을 등급으로 바꾸어주는 util입니다.
	// EX) 3 -> Bronze3, 4 -> Broneze2

	static String[] grades = { "Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ruby" };

	// 1,2,3,4,5 -> 브론즈
	// 6,7,8,9,10 -> 실버 ...

	static String getGrade4Level(int level) {
		if (level == 0) {
			return "Unrated";
		}
		int flag = (level - 1) / 5;
		return grades[flag] + (6 - (level-flag*5));
	}
}
