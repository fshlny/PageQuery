# Collections之binarySearch方法

先说一下是如何接触到这个方法的吧：早上老板过来说xx你这个搜索怎么不支持中文检索啊，由于server有遇到过不支持中文字符的，小弟就先应付了一下说是编码不支持中文检索，老板是技术出生啊，哪里管你这些啊，编码不支持就改编码。没办法，就只有去啃搜索业务的代码。在代码中发现前辈有copy一份list用于搜索，其中就使用到了Collections的binarySearch方法检索里面的内容.要了解他的用法，就先去看他的文档吧。

## binarySearch定义

在Java的doc中有以下定义:

```
Searches the specified list for the specified object using the binary search algorithm.The list must be sorted into ascending order according to the natural ordering of its elements (as by the sort(List) method) prior to making this call.If it is not sorted, the results are undefined.If the list contains multiple elements equal to the specified object, there is no guarantee which one will be found. 

This method runs in log(n) time for a "random access" list (which provides near-constant-time positional access). If the specified list does not implement the RandomAccess interface and is large, this method will do an iterator-based binary search that performs O(n) link traversals and O(log n) element comparisons.

Type Parameters:<T> the class of the objects in the listParameters:list the list to be searched.key the key to be searched for.Returns:the index of the search key, if it is contained in the list; otherwise, (-(insertion point) - 1). The insertion point is defined as the point at which the key would be inserted into the list: the index of the first element greater than the key, or list.size() if all elements in the list are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.Throws:ClassCastException - if the list contains elements that are not mutually comparable (for example, strings and integers), or the search key is not mutually comparable with the elements of the list.
```
大概说的是此方法使用二分查找查询，列表必须是通过升序排序后的列表，如果列表中有相同的元素，则不能保证哪一个会被检索到。如果指定的列表没有实现RandomAccess接口并且很大(通过源码得知是5000)时，则此方法将执行基于迭代器的二进制搜索，以执行O（n）链接遍历和O（log n）元素比较。如果它包含在数组中，则返回搜索键的索引(从1开始)；否则返回 (-(插入点) - 1)。插入点为将键插入数组的那一点：即第一个大于此键的元素索引，如果数组中的所有元素都小于指定的键，则为 
a.length。注意，这保证了当且仅当此键被找到时，返回的值将 >= 0

binarySearch源码:

```
public static <T>
    int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        //如果list实现了RandomAccess接口或list的size小于5000就调用二分找
        //否则就调用基于迭代器的二分查找
        if (list instanceof RandomAccess || list.size()<BINARYSEARCH_THRESHOLD)
            return Collections.indexedBinarySearch(list, key);
        else
            return Collections.iteratorBinarySearch(list, key);
    }
```

```
//使用二分查找
private static <T>
    int indexedBinarySearch(List<? extends Comparable<? super T>> list, T key) {
        int low = 0;
        int high = list.size()-1;
        
        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<? super T> midVal = list.get(mid);
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
    //使用基于迭代器的二分查找
    private static <T>
    int iteratorBinarySearch(List<? extends Comparable<? super T>> list, T key)
    {
        int low = 0;
        int high = list.size()-1;
        ListIterator<? extends Comparable<? super T>> i = list.listIterator();
        //
        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<? super T> midVal = get(i, mid);
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

```


Collections的简单应用:

```
List<String> citys = new ArrayList<String>();
citys.add("北京");//0 -- 1
citys.add("上海");//1 -- 2
citys.add("广州");//2 -- 3
citys.add("深圳");//3 -- 4
citys.add("成都");//4 -- 5
citys.add("南京");//5 -- 6
citys.add("武汉");//6 -- 7
citys.add("重庆");//7 -- 8
//先升序排序
Collections.sort(citys);
//查询--广州
int indexGZ = Collections.binarySearch(citys, "广州");
System.out.println("通过Collections.binarySearch方法查询【广州】返回的数值是："+indexGZ);

```

结果不出意料：

```
通过Collections.binarySearch方法查询【广州】返回的数值是：3
```

在项目中前辈使用的Collections.binarySearch方法实现了搜索功能。其主要思路如下：

1. 拷贝一份数据存放在List中，这样获取数据的时候是最快的，不用通过网络或检索数据去获取。
2. 在向List添加数据的同时建立一份索引存储起来，到时候可以通过检索索引中的关键字获取到数据在List中的索引值
3. 对存储起来的搜索索引数组进行升序排序

小白就是嘴笨，好了大概意思就这样。将就着看。接下来我们按照前辈的思想实现一个分页检索

Student.java

```
public class Student {
    private int id;
    private int age;
    private String name;
    private String simplePY;
    private String className;
    //省略setter和getter方法和构造方法
    
}

```

索引类:StudentSearchIndex.java 

```
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

    //省略setter和getter方法

    public StudentSearchIndex(int index, String key) {
        this.index = index;
        this.key = key;
    }
}

```


StudentSearchHelper.java

```
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
                indexStudentSearch.add(new StudentSearchIndex(i, st.getValue().getSimplePY().toLowerCase()));
            if(!TextUtils.isEmpty(st.getValue().getName()))//名字检索
                indexStudentSearch.add(new StudentSearchIndex(i, st.getValue().getName().toLowerCase()));
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
        if(0 >= request.getPageAmount()){
            return new SearchReply(request.getPage(), request.getPageAmount(), 0, new ArrayList<Student>());
        }
        List<StudentSearchIndex> searchIndexs = studentIndexs;
        
        int totalStudent = 0;//检索结果总数量
        //检索结果的开始值
        int searchStudentStartIndex = Collections.binarySearch(searchIndexs, new StudentSearchIndex(Integer.MIN_VALUE, request.getKey().toLowerCase()));
        // "\uFFFF"是Unicode码的最后一个数值
        totalStudent = Collections.binarySearch(searchIndexs, new StudentSearchIndex(Integer.MAX_VALUE, request.getKey().toLowerCase()+'\uFFFF'));
        
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
                studentMap.put(studentList.get(index).getId(), index);
        }
        
        int startIndex = request.getPage() * request.getPageAmount();
        int endIndex = (request.getPage()+1)*request.getPageAmount();
        ArrayList<Student> replyStudents = new ArrayList<Student>();
        int ii=0;
        for(Entry<Integer, Integer> entry:studentMap.entrySet()){
            //TODO 将数据添加到响应中
            if(ii < startIndex){
                ii++;
                continue;
            }else if(ii<endIndex){
                //获取到对应的结果
                replyStudents.add(studentList.get(entry.getValue()));
            }else{
                break;
            }
        }
        
        int totalPageSize = (studentMap.size() % request.getPageAmount()) == 0 ? 
                (studentMap.size()/request.getPageAmount()) : (studentMap.size()/request.getPageAmount()+1);
        
        return new SearchReply(request.getPage()+1,request.getPageAmount(),totalPageSize,replyStudents);
    }
}

```

StudentSearchHelper通过Collections.binarySearch方法找出key开始的位置和结束(Unicode码以\uFFFF结束)位置，查询将查询的索引结果保存到map集合中，通过请求的页数的请求数量计算出总页数和返回请求结果.

因为binarySearch中是使用的compareTo方法，只能查询出以key开头和结尾的结果，不能查询以xxxkeyxxx的数据.
