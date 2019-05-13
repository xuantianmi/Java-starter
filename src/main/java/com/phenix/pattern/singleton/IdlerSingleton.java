package com.phenix.pattern.singleton;

/**
 * 懒汉式(双重检查加锁)
 */
public class IdlerSingleton {

    /**
     *   volatile保证，JVM命令顺序（属性变量非配内存、初始化、变量赋值）不会打乱
     *   当uniqueInstance变量被初始化成Singleton实例时，多个线程可以正确处理uniqueInstance变量
     */
    private volatile static IdlerSingleton uniqueInstance;

    private IdlerSingleton() {
    }

    /**
     *
     * @return
     */
    public static IdlerSingleton getInstance() {
        //检查实例，如果不存在，就进入同步代码块
        if (uniqueInstance == null) {
            //只有第一次才彻底执行这里的代码
            synchronized(IdlerSingleton.class) {
                //进入同步代码块后，再检查一次，如果仍是null，才创建实例
                if (uniqueInstance == null) {
                    uniqueInstance = new IdlerSingleton();
                }
            }
        }
        return uniqueInstance;
    }

}
