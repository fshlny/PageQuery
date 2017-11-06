package com.fsh.collections;

public class StudentSearchIndex implements Comparable<StudentSearchIndex>{
	private int index;
	private String key;

	public int compareTo(StudentSearchIndex o) {
		int ret = key.compareTo(o.getKey());
		//如果相等，通过真实的索引值来进行比较
		//如果索引值比对比的索引值大，返回1
		//如果索引值比对比的索引值小，返回-1
		//如果相等，返回0
		if(ret == 0)
			return index > o.getIndex() ? 1 : (index < o.getIndex() ? -1 : 0);
		return ret;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public StudentSearchIndex(int index, String key) {
		this.index = index;
		this.key = key;
	}

}
