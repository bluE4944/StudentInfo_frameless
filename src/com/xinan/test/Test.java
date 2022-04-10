package com.xinan.test;

import com.google.gson.Gson;
import com.xinan.dao.LoginDAO;
import com.xinan.dao.UserDao;
import com.xinan.entity.User;
import com.xinan.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * 用来测试
 * @author 11940
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = UserDao.getInstance();
//        String pattern1 = ".*=.*";
//        String str  = "SELECT * FROM user where phoneNumber = ? =1";
//        boolean isMatch = Pattern.matches(pattern1, str);
//        System.out.println(isMatch);

        User user = new User("123456","123456","xiaobiaodi");

        userDao.userSelect(user);
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        System.out.println(userJson);

        //本地验证Main函数
        LoginDAO ud = LoginDAO.getInstance();
        DBUtil dbUtil = DBUtil.getInstance();
        Connection conn = dbUtil.getConnection();
        try {
            String url = "Server/css/index.css";
            String pattern = ".*jsp";
            //利用正则表达式 检查路径里（url）是否有 .jsp
            Date date = new Date();
//            if(isMatch){
//                System.out.println("zhen");
//            }
//            System.out.println(isMatch);
            System.out.println(ud.isLoginOK("13062371742", "*friendzx*"));
            System.out.println(ud.isLoginOK("1234567897", "*friendzx*"));
            System.out.println(ud.isAdminister("13062371742"));
            System.out.println(ud.isAdminister("13247585849"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
