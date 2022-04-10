package com.xinan.dao;

import com.xinan.daointer.SystemDaoInter;
import com.xinan.entity.User;
import com.xinan.util.DBUtil;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 11940
 */
public class SystemDao implements SystemDaoInter {
    private static SystemDao systemDao = new SystemDao();
    /**
     * 调用数据库连接工厂 获得数据库 连接
     */
    private DBUtil dbUtil = DBUtil.getInstance();
    private Connection conn =dbUtil.getConnection();

    public static SystemDao getInstance(){
        return systemDao;
    }
    /**
     * 私有化构造
     */
    private SystemDao(){

    }

    @Override
    public void setSysName(String name) throws SQLException {
        String selectSql = "UPDATE system SET sname=? WHERE sys_id = 1";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ps.setString(1,name);
        ps.execute();
    }

    @Override
    public void setLoginName(String name) throws SQLException {
        String selectSql = "UPDATE system SET slogin=? WHERE sys_id = 1";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ps.setString(1,name);
        ps.execute();
    }

    @Override
    public void setNavName(String name) throws SQLException {
        String selectSql = "UPDATE system SET snavName=? WHERE sys_id = 1";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ps.setString(1,name);
        ps.execute();

    }

    @Override
    public String getLoginName() throws SQLException {
        String selectSql = "SELECT * FROM system WHERE sys_id=1";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            return rs.getString("slogin");
        }
        return "";
    }

    @Override
    public String getSysName() throws SQLException {
        String selectSql = "SELECT * FROM system WHERE sys_id=1";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            return rs.getString("sname");
        }
        return "";
    }

    @Override
    public String getNavName() throws SQLException {
        String selectSql = "SELECT * FROM system WHERE sys_id=1";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            return rs.getString("snavName");
        }
        return "";
    }
}
