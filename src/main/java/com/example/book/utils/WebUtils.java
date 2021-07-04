package com.example.book.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.Cookie;
import java.util.Map;

/**
 * 获取表单参数并实例化对象
 * @author chenzhilei
 * @date 2021/6/28) 11:42)
 */
public class WebUtils {

    public static <T> T copyParamToBean(Map value,T bean){
        try {
            System.out.println("注入之前"+bean);
            BeanUtils.populate(bean,value);
            System.out.println("注入之后"+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String s,int defaultValue){
        try {
            int value = Integer.parseInt(s);
            return value;
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return defaultValue;
    }

    public static Cookie findCookie(String s,Cookie[] cookies){
        if(s==null||cookies.length==0||cookies==null){
            return null;
        }
        for (Cookie cookie : cookies) {
            if(s.equals(cookie.getName())){
                return cookie;
            }
        }
        return null;
    }
}
