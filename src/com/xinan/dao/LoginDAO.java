package com.xinan.dao;

import com.xinan.daointer.LoginDaoInter;
import com.xinan.entity.User;
import com.xinan.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 对登录验证是否成功进行判断
 * @author 11940
 */
public class LoginDAO implements LoginDaoInter {
    private static LoginDAO loginDAO = new LoginDAO();
    private DBUtil dbUtil = DBUtil.getInstance();
    private Connection conn =dbUtil.getConnection();

    public static LoginDAO getInstance (){return loginDAO;}

    /**
     * 私有构造器
     */
    private LoginDAO() {
    }


    /**
     * 验证函数
     * @param phoneNumber
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public boolean isLoginOK( String phoneNumber, String password) throws SQLException {
        String selectSql = "SELECT * FROM user where phoneNumber = ? AND password = ? ";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ps.setString(1, phoneNumber);
        ps.setString(2, password);
        ResultSet rst = ps.executeQuery();
        if (rst.next()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isAdminister( String phoneNumber) throws SQLException {
        String sql = "SELECT * FROM user where phoneNumber = ? AND administer = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, phoneNumber);
        ps.setInt(2, 1);
        ResultSet rst = ps.executeQuery();
        if (rst.next()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUser(String phoneNumber) throws SQLException {
        String sql = "SELECT * FROM user where phoneNumber = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,phoneNumber);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        while(rs.next()) {
            user.setPhoneNumber(rs.getString("phoneNumber"));
            user.setPassword(rs.getString("password"));
            user.setAdminister(rs.getInt("administer"));
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("user_name"));
            user.setRegisterTime(rs.getTimestamp("lastTime"));
        }

        return user;
    }
}
