package com.phenix.pattern.singleton;

public class EnumSingleton {
    public static void main(String[] args) {
        ESingleton singleton = ESingleton.INSTANCE;
        //output:枚举方法实现单例
        singleton.doSomeThing();

    }

}

enum ESingleton {
    //定义一个枚举的元素，它就是 Singleton 的一个实例
    INSTANCE;

    public void doSomeThing() {
        System.out.println("枚举方法实现单例");
    }
}
