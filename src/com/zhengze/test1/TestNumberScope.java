package com.zhengze.test1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestNumberScope {

	public static void main(String[] args) {
		/*
		 * test a number scope in 0 - 365
		 */
		String p = "(^(\\d{0,2})$)|([1-2]\\d{2})|(3[0-5]\\d)|(36[0-5])";// wo le ge qu!

		Pattern pattern = Pattern.compile(p);

		for (int i = -5; i <= 370; i++) {
			String s = i + "";
			Matcher matcher = pattern.matcher(s);
			System.out.print(s);
			System.out.printf("\t");
			System.out.println(matcher.find());
		}
	}

}
