package sandbox;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        int a = 6;
        System.out.println("a=" + a);
        a = a >> 1;
        System.out.println("hello world!");
        System.out.println("a=" + a);

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(5);
        arrayList.add(7);
        arrayList.add(9);
        Integer[] integer = arrayList.toArray(new Integer[0]);
        for (Integer number : integer) {
            System.out.print(number + " ");
        }
        System.out.println(" ");

        // TODO 通过匿名内部类方式访问接口
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        // 100.0
        System.out.println(formula.calculate(100));
        // 4.0
        System.out.println(formula.sqrt(16));
    }

    interface Formula {

        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(a);
        }

    }
}
