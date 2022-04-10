package com.xinan.util;

import sun.security.pkcs11.Secmod;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工厂
 *
 */
public class DBUtil {
    private static Connection conn = null;
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    private static DBUtil dbUtil = new DBUtil();

    static{
        Properties prop = new Properties();
        try{
            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("DBConfig.properties");
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("properties file read error!!!");
        }

        driver = prop.getProperty("driver");
        url = prop.getProperty("url");
        username = prop.getProperty("username");
        password = prop.getProperty("password");
    }

    /**
     * 私有构造器
     */
    private DBUtil(){

    }

    public Connection getConnection(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static DBUtil getInstance(){
        return dbUtil;
    }
}
