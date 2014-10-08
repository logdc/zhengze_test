package com.zhengze.test1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {

	public static void main(String[] args) {
		// any digital, any symbol, any letter
		tryToMatch("\\d{2}\\s\\w", "123 adf");
		
		// inner select for number or letter
		tryToMatch("[^1-456]{2}", "5678901");
		
		// number of letter, at least and at most number of letter, only at least letter
		tryToMatch("f{2}ef\\w?d", "fwefffffefad");
		tryToMatch("f{2,5}e\\w+d", "fwefffffefad");
		tryToMatch("f{2,}\\w*d", "fwefffffefad");
		
		// start and close for whole sentence, start and close for a word
		tryToMatch("^a.c$", "abc, abc");
		tryToMatch("\\ba.c\\b", "abc, abc");
		
		// match word (together)
		tryToMatch("(abc|uvw){2,}", "sdfwefuvwabc");
		
		// eager mode 
		tryToMatch("(d)(\\w+)(d)", "adxxxdxxxde");
		// eager less
		tryToMatch("(d)(\\w+?)(d)", "adxxxdxxxde");
		
		// refer to ?number of bracket
		tryToMatch("('|\")(.*?)(\\1)", "'Hello', \"World\"");
		tryToMatch("(\\w)\\1{4,}", "aa bbbb abcdefg ccccc 111121111 999999999");
		tryToMatch("<(\\w+)\\s*(\\w+(=('|\").*?\\4)?\\s*)*>.*?</\\1>", 
				"<td id='td1' style=\"bgcolor:white\"></td>");
		
			
	}
	
	private static void tryToMatch(String patternStr, String testStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(testStr);
		System.out.print("Test Str: ");
		System.out.printf(testStr + "\t");
		if (matcher.find()) {
			System.out.print("found: ");
			System.out.print(matcher.group());
		} else {
			System.out.print("not found!");
		}
		System.out.println();
	}
}
