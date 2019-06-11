package arrayAndList;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class TestLinkedList {

    @Test
    public void testQuery(){

        // 通过调试可以发现 JDK 源码的查找使用了 二分的思想
        LinkedList<Integer> list=new LinkedList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11));
        System.out.println(list.get(3));
        System.out.println(list.get(9));
    }



}
