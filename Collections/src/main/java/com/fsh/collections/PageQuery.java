package com.fsh.collections;

import java.util.TreeMap;

public class PageQuery {
	private static TreeMap<Integer,Student> studentMap = new TreeMap<Integer, Student>();
	
	static{
		studentMap.put(1, new Student(1, 3, "浩然", "HR", "学前班一班"));
		studentMap.put(2, new Student(2, 3, "子轩", "ZX", "学前班一班"));
		studentMap.put(3, new Student(3, 4, "皓轩", "HX", "学前班一班"));
		studentMap.put(4, new Student(4, 3, "宇轩", "YX", "学前班一班"));
		studentMap.put(5, new Student(5, 4, "浩宇", "HY", "学前班一班"));
		studentMap.put(6, new Student(6, 3, "梓睿", "ZR", "学前班一班"));
		studentMap.put(7, new Student(7, 4, "梓轩", "ZX", "学前班一班"));
		studentMap.put(8, new Student(8, 4, "浩轩", "HX", "学前班一班"));
		studentMap.put(9, new Student(9, 3, "俊熙", "JX", "学前班一班"));
		studentMap.put(10, new Student(10, 3, "梓豪", "ZH", "学前班一班"));
		studentMap.put(11, new Student(11, 4, "子墨", "ZM", "学前班一班"));
		studentMap.put(12, new Student(12, 3, "博文", "BW", "学前班一班"));
		studentMap.put(13, new Student(13, 5, "一诺", "YN", "学前班一班"));
		studentMap.put(14, new Student(14, 3, "子涵", "ZH", "学前班一班"));
		studentMap.put(15, new Student(15, 3, "子睿", "ZR", "学前班一班"));
		studentMap.put(16, new Student(16, 4, "睿", "R", "学前班一班"));
		studentMap.put(17, new Student(17, 4, "雨泽", "YZ", "学前班一班"));
		studentMap.put(18, new Student(18, 5, "铭轩", "MX", "学前班一班"));
	}
	//{1=0, 3=2, 5=4, 8=7, 9=8}
	public static void main(String[] args) {
		StudentSearchHelper helper = new StudentSearchHelper(studentMap);
		SearchRequest request = new SearchRequest("H", 0, 10);
		SearchReply reply = helper.searchStudent(request);
		System.out.println(reply.toString());
	}
}
