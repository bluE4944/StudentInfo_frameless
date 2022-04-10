package com.xinan.entity;

import com.xinan.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * User MODEL
 * @author 11940
 */
public class User implements Serializable {
    private int id;
    private String phoneNumber;
    private String password;
    private String username;
    private String registerTime;
    private int administer;
    //用于URL传参和取参时的KEY
    public static String PHONENUMBER =  "phoneNumber";



    public static String PASSWORD = "passWord";
    public static String USERNAME = "userName";

    /**
     * 构造函数
     * @param phoneNumber
     * @param password
     * @param username
     */
    public User(String phoneNumber,String password,String username){
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.username = username;
        this.registerTime = DateUtil.getDateTime(new Date());
    }

    /**
     * 空构造函数
     */
    public User() {
        //初始化内容
        id = -1;
        administer = -1;
        username = "";
        phoneNumber = "";
        this.registerTime = DateUtil.getDateTime(new Date());
    }

    /*
        getter setter 方法
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getRegisterTime() {
        return this.registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime =DateUtil.getDateTime(registerTime) ;
    }

    public int getAdminister() {
        return administer;
    }

    public void setAdminister(int administer) {
        this.administer = administer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", registerTime=" + registerTime +
                ", administer=" + administer +
                '}';
    }
}
