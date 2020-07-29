package com.atguigu.crowd.test;

import com.atguigu.crowd.util.CrowdUtil;
import org.junit.Test;

public class StringTest {
    @Test
    public void testMD5(){
        String s ="123123";
        String encode = CrowdUtil.md5(s);
        System.out.println(encode);
    }
}
