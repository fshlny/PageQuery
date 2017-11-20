package com.fsh.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collections.binarySearch的简单应用
 * @author fsh
 */
public class SimpleExample {
	
	public static void main(String[] args) {
		List<String> citys = new ArrayList<String>();
		
//		citys.add("北京");//0 -- 1
//		citys.add("上海");//1 -- 2
//		citys.add("广州");//2 -- 3
//		citys.add("深圳");//3 -- 4
//		citys.add("成都");//4 -- 5
		citys.add("ab");//5 -- 6
		citys.add("gw");//6 -- 7
		citys.add("eu");//6 -- 7
		citys.add("nt");//6 -- 7
		citys.add("fv");//6 -- 7
		citys.add("ta");//6 -- 7
		citys.add("un");//6 -- 7
		citys.add("qo");//6 -- 7
		citys.add("rt");//6 -- 7
		citys.add("mc");//6 -- 7

		//先升序排序
		Collections.sort(citys);
		
//		//查询--广州
//		int indexGZ = Collections.binarySearch(citys, "广州");
//		
//		System.out.println("通过Collections.binarySearch方法查询【广州】返回的数值是："+indexGZ);
//		//查询--湖
//		int indexH = Collections.binarySearch(citys, "Z");
//		System.out.println("通过Collections.binarySearch方法查询【湖】返回的数值是："+indexH);
		
		int indexStart = Collections.binarySearch(citys, "a");
		if(indexStart < 0)
			indexStart = -(indexStart + 1);
		else
			indexStart = indexStart -1;
		
		int indexEnd = Collections.binarySearch(citys, "t"+'\uFFFF');
		if(indexEnd < 0)
			indexEnd = -(indexEnd + 1);
		else
			indexEnd = indexEnd -1;
		System.out.println("开始index:"+indexStart+",结束:"+indexEnd);
	}
	
	
	
}
