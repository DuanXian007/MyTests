package com.duanxian.web;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by Lionsong on 2017/6/14.
 */
public class Manager {
    private static final Logger LOGGER = LogManager.getLogger(Manager.class);

    public static void main(String[] args) {
//        Resource resource = new ClassPathResource("xbean.xml");
//        BeanFactory factory = new XmlBeanFactory(resource);
        BeanFactory factory = new ClassPathXmlApplicationContext("xbean.xml");
        User user =(User)factory.getBean("user");
        LOGGER.info("Name: "+user.getName());
        LOGGER.info("Sex: "+user.getSex());
        LOGGER.info("Age: "+user.getAge());
    }
}
