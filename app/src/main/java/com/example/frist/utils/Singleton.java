package com.example.frist.utils;

/**
 * 双重检查加锁的单例模式
 * Created by Administrator on 2017/11/14.
 */

public class Singleton {
    /**
     * 对保存实例的变量添加volitile的修饰
     */
    public volatile static Singleton singleton=null;
    public static Singleton getInstance(){
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if (singleton==null){
            //同步块，线程安全的创建实例
            synchronized (Singleton.class){
                //再次检查实例是否存在，如果不存在才真正的创建实例
                singleton=new Singleton();
            }
        }
        return singleton;
    }
}
