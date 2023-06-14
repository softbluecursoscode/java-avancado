package app;

import br.com.softblue.common.StringUtils;

public class MainClass {

	public static void main(String[] args) {
		
		String s = "abc";
		System.out.println(s + " => " + StringUtils.isEmpty(s));
	}
}
