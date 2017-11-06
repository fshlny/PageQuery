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
		
		citys.add("北京");//0 -- 1
		citys.add("上海");//1 -- 2
		citys.add("广州");//2 -- 3
		citys.add("深圳");//3 -- 4
		citys.add("成都");//4 -- 5
		citys.add("南京");//5 -- 6
		citys.add("武汉");//6 -- 7
//		citys.add("重庆");//7 -- 8
//		citys.add("湖北");//8 -- 9
//		citys.add("湖南");//9 -- 10
		//先升序排序
		Collections.sort(citys);
		
		//查询--广州
		int indexGZ = Collections.binarySearch(citys, "广州");
		
		System.out.println("通过Collections.binarySearch方法查询【广州】返回的数值是："+indexGZ);
		//查询--湖
		int indexH = indexedBinarySearch(citys, "Z");
		System.out.println("通过Collections.binarySearch方法查询【湖】返回的数值是："+indexH);
		
	}
	
	private static
    int indexedBinarySearch(List<String> list, String key) {
        int low = 0;
        int high = list.size()-1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            String midVal = list.get(mid);
            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found
    }
}
