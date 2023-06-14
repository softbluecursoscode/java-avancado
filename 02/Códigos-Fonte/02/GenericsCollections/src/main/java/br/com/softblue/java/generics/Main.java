package br.com.softblue.java.generics;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
	
//		List list = new ArrayList();
//		
//		list.add("abc");
//		list.add("def");
//		list.add("ghi");
//		list.add(100);
//		list.add(new Date());
//		
//		for (Object obj : list) {
//			String s = (String) obj;
//			System.out.println(s);
//		}
		
		List<String> list = new ArrayList<>();
		
		list.add("abc");
		list.add("def");
		list.add("ghi");
		
		for (String s : list) {
			System.out.println(s);
		}
	}
}
