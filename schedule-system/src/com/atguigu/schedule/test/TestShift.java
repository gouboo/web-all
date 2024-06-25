package com.atguigu.schedule.test;

import org.junit.Test;

/**
 * ClassName: TestShift
 * Package: com.atguigu.schedule.test
 * Description: 测试移位操作符
 *
 * @Author: bushG
 * @Create: 2024/6/25 1:51
 * @Version: 1.0
 */
public class TestShift {
    @Test
    public void testUnsignRightShift(){
        int a = 109; // 正数
        int b = -109; // 负数，这里用正数表示，实际中Java使用补码

        // 无符号右移1位
        int resultA = a >>> 1; // 正数右移，左边填充0
        int resultB = b >>> 1; // 负数右移，左边填充0，实际中Java使用补码

        System.out.println("Original a (binary): " + Integer.toBinaryString(a));
        System.out.println("Original b (binary): " + Integer.toBinaryString(b));
        System.out.println("After unsigned right shift (a): " + Integer.toBinaryString(resultA));
        System.out.println("After unsigned right shift (b): " + Integer.toBinaryString(resultB));
    }
}
