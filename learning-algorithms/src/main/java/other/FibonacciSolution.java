package other;

import java.text.DecimalFormat;

/**
 * 斐波那契数列
 * @Author ZhangNana
 * @DATE 2021/7/10 17:50
 * @Version 1.0
 */
public class FibonacciSolution {

    /**
     * 指的是这样一个数列：0、1、1、2、3、5、8、13、21、34、……在数学上，斐波那契数列以如下被以递推的方法定义：F(0)=0，F(1)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 2，n ∈ N*）
     * @param args
     */
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#0");
        long start = System.currentTimeMillis();
        //int i = Solution1(100); //63245986;spend328
        float i = Solution2(50);//63245986;spend0
        System.out.println(df.format(i) + ";spend" + (System.currentTimeMillis() - start));
    }

    /**
     * 使用递归思路
     * @return
     */
    public static int Solution1(int number){
        if (number == 0 || number == 1){
            return number;
        }
        return Solution1(number - 1) + Solution1(number - 2);
    }


    /**
     * 使用数组循环操作
     * 2.first = 0，second = 1，temp = 1；
     * 3.first = 1，second = 1，temp = 2;
     * 4.first = 1, second = 2, temp = 3;
     * 5.first = 2, second = 3, temp = 5;
     * 6.first = 3, second = 5, temp = 8;
     * ......
     * @param number
     * @return
     */
    public static float Solution2(int number){
        int[] data = {0,1};
        if (number < 2){
            return data[number];
        }
        //和冒泡排序思路大致差不多，temp用来交换
        float first = 0;
        float second = 1;
        float temp = 0;
        for (int i = 2; i <= number; i++) {
            temp = first + second;
            first = second;
            second = temp;
        }
        return temp;
    }



}
