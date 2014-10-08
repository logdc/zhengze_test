package com.zhengze.test1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestAccordingToIM {

	public static void main(String[] args) {
		doMatch("expected" + "Position" + "T12");
		doMatch("expected" + "NativeValue" + "T12");
		doMatch("expected" + "BaseValue" + "T12");
		doMatch("expected" + "ReportingValue" + "T12");
		doMatch("expecte1d" + "ReUse" + "T12");			//diff test
		doMatch("confirmed" + "Position" + "T12");
		doMatch("confirmed" + "NativeValue" + "T12");
		doMatch("confirmed" + "BaseValue" + "T12");
		doMatch("confirmed" + "ReportingValue" + "T12");
		doMatch("confirmed" + "ReUse" + "T12");
	}
	
	private static void doMatch(String s) {
		doMatch1(s);
		doMatch2(s);
	}
	
	private static void doMatch1(String s) {
		String s1 = null;
		String p = "(?<=expected|confirmed)(((?!Position).)*)(?=T\\d+)";
		Pattern pattern = Pattern.compile(p);
		Matcher matcher = pattern.matcher(s);
		System.out.print("match1: ");
		
		s1 = matcher.replaceFirst("Position");
		System.out.println(s1);
		
	}
	private static void doMatch2(String s) {
		System.out.print("match2: ");
		System.out.println(s.replaceFirst("NativeValue|BaseValue|ReportingValue|ReUse", "Position"));
	}

}
