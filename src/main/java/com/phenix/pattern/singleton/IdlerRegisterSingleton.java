package com.phenix.pattern.singleton;

/**
 * 懒汉式（登记式/静态内部类方式）
 * 只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，
 * 从而实例化 instance（只有第一次使用这个单例的实例的时候才加载，同时不会有线程安全问题）。
 */
public class IdlerRegisterSingleton {
    private static class SingletonHolder {
        private static final IdlerRegisterSingleton INSTANCE = new IdlerRegisterSingleton();
    }
    private IdlerRegisterSingleton(){}

    public static final IdlerRegisterSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
