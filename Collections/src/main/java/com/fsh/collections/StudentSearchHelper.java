package com.fsh.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class StudentSearchHelper {
	//数据
	private List<Student> studentList = new ArrayList<Student>();
	//搜索索引List
	private List<StudentSearchIndex> studentIndexs = new ArrayList<StudentSearchIndex>();
	
	public StudentSearchHelper(TreeMap<Integer,Student> studentMap){
		reloadStudentSearchList(studentMap);
	}
	//初始化检索数据
	public void reloadStudentSearchList(TreeMap<Integer,Student> studentMap){
		if(studentMap == null) return;
		int i=0;
		ArrayList<StudentSearchIndex> indexStudentSearch = new ArrayList<StudentSearchIndex>();
		ArrayList<Student> students = new ArrayList<Student>();
		for (Entry<Integer,Student> st : studentMap.entrySet()) {
			if(!TextUtils.isEmpty(st.getValue().getSimplePY()))//拼音首字母检索
				indexStudentSearch.add(new StudentSearchIndex(i, st.getValue().getSimplePY()));
			if(!TextUtils.isEmpty(st.getValue().getName()))//名字检索
				indexStudentSearch.add(new StudentSearchIndex(i, st.getValue().getName()));
			i++;
			students.add(st.getValue());
		}
		Collections.sort(indexStudentSearch);//对索引进行升序排序
		studentList = students;
		studentIndexs = indexStudentSearch;
	}
	
	/**
	 * 搜索
	 * @param request
	 * @return SearchReply
	 */
	public SearchReply searchStudent(SearchRequest request){
		if(0 >= request.getPage()){
			return new SearchReply(request.getPage(), request.getPageAmount(), 0, new ArrayList<Student>());
		}
		List<StudentSearchIndex> searchIndexs = studentIndexs;
		
		int totalStudent = 0;//检索结果总数量
		//检索结果的开始值
		int searchStudentStartIndex = Collections.binarySearch(searchIndexs, new StudentSearchIndex(Integer.MIN_VALUE, request.getKey()));
		// "\uFFFF"是Unicode码的最后一个数值
		totalStudent = Collections.binarySearch(searchIndexs, new StudentSearchIndex(Integer.MAX_VALUE, request.getKey()+"\uFFFF"));
		
		//获取在索引列表中的索引值,获取在数据列表中的索引值
		if(searchStudentStartIndex < 0)
			searchStudentStartIndex = -(searchStudentStartIndex + 1);
		else
			searchStudentStartIndex = searchStudentStartIndex -1;
		
		if(totalStudent < 0)
			totalStudent = -(totalStudent + 1);
		else
			totalStudent = totalStudent -1;
		
		if(searchStudentStartIndex >= searchIndexs.size()){
			return new SearchReply(request.getPage(), request.getPageAmount(), 0, new ArrayList<Student>());
		}
		
		TreeMap<Integer,Integer> studentMap = new TreeMap<Integer, Integer>();
		
		for(int i=searchStudentStartIndex;i<totalStudent;i++){
			int index = searchIndexs.get(i).getIndex();
			if(index < studentList.size())
				studentMap.put(studentList.get(i).getId(), index);
		}
		
		int startIndex = request.getPage() * request.getPageAmount();
		int endIndex = (request.getPage()+1)*request.getPageAmount();
		ArrayList<Student> replyStudents = new ArrayList<Student>();
		int ii=0;
		for(Entry<Integer, Integer> entry:studentMap.entrySet()){
			//TODO 将数据添加到响应中
		}
		
		return null;
	}
}
