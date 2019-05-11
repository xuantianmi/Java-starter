package com.phenix.springioc;

import org.junit.Test;

public class SimpleIOCTest {
    @Test
    public void getBean() throws Exception {
        String location =
            SimpleIOC.class.getClassLoader().getResource("ioc.xml").getFile();
        System.out.println(location);
        SimpleIOC bf = new SimpleIOC(location);
        Wheel wheel = (Wheel)bf.getBean("wheel");
        System.out.println(wheel);
        System.out.println(wheel.getRefBean().getParam1());
        Car car = (Car)bf.getBean("car");
        System.out.println(car);
    }
}