package com.xinan.daointer;

import com.xinan.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 11940
 */
public interface LoginDaoInter {
    /**
     *  连接数据库检查账户密码的正确性
     * @param phoneNumber
     * @param password
     * @return 账户密码的正确性
     * @throws SQLException
     */
    public boolean isLoginOK( String phoneNumber, String password)throws SQLException;

    /**
     *  判断是否为管理员
     * @param phoneNumber
     * @return true为管理员，反之则不是
     * @throws SQLException
     */
    public boolean isAdminister( String phoneNumber)throws SQLException;

    /**
     * 获得对应电话的User对象
     * @param phoneNumber
     * @return
     * @throws SQLException
     */
    public User getUser(String phoneNumber)throws SQLException;
}
