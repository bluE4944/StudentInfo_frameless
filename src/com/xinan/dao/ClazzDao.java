package com.xinan.dao;

import com.xinan.daointer.ClazzDaoInter;
import com.xinan.entity.Clazz;
import com.xinan.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 11940
 */
public class ClazzDao implements ClazzDaoInter {

    private static ClazzDao clazzDao = new ClazzDao();

    /**
     *  连接数据库
     */
    private DBUtil dbUtil = DBUtil.getInstance();
    private Connection conn =dbUtil.getConnection();

    /**
     * 获得静态ClazzDao对象
     * @return
     */
    public static ClazzDao getInstance(){
        return clazzDao;
    }

    /**
     * 私有构造器
     */
    private ClazzDao(){}

    @Override
    public void save(Clazz clazz) throws SQLException {
        String saveSql = "INSERT INTO class(teac_id,clas_name,clas_time,clas_week) values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(saveSql);
        ps.setInt(1,clazz.getTeac_id());
        ps.setString(2,clazz.getClas_name());
        ps.setString(3,clazz.getClas_time());
        ps.setInt(4,clazz.getClas_week());
        ps.execute();
    }

    @Override
    public void delete(int clas_id) throws SQLException {
        String sql = "DELETE FROM class WHERE clas_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,clas_id);
        ps.execute();
    }

    @Override
    public List<Clazz> findAllClazz() throws SQLException {
        String sql = "SELECT * FROM class";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Clazz> clazzes = new ArrayList<>();
        while (rs.next()){
            Clazz clazz = new Clazz();
            clazz.setClas_id(rs.getInt("clas_id"));
            clazz.setTeac_id(rs.getInt("teac_id"));
            clazz.setClas_name(rs.getString("clas_name"));
            if(rs.getDate("clas_start")!=null&&!rs.getDate("clas_start").equals("")){
                clazz.setClas_start(rs.getDate("clas_start"));
            }
            if(rs.getDate("clas_end")!=null&&!rs.getDate("clas_end").equals("")){
                clazz.setClas_end(rs.getDate("clas_end"));
            }
            clazz.setClas_time(rs.getTime("clas_time"));
            clazz.setClas_week(rs.getInt("clas_week"));
            clazzes.add(clazz);
        }
        return clazzes;
    }

    @Override
    public List<Clazz> findClazzWithTeac_id(int teac_id,int startIndex, int pageSize) throws SQLException {
        String sql = "SELECT * FROM class WHERE  teac_id = ? LIMIT ?,?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,teac_id);
        ps.setInt(2,startIndex);
        ps.setInt(3,pageSize);
        ResultSet rs = ps.executeQuery();
        List<Clazz> clazzes = new ArrayList<>();
        while (rs.next()){
            Clazz clazz = new Clazz();
            clazz.setClas_id(rs.getInt("clas_id"));
            clazz.setTeac_id(rs.getInt("teac_id"));
            clazz.setClas_name(rs.getString("clas_name"));
            if(rs.getDate("clas_start")!=null&&!rs.getDate("clas_start").equals("")){
                clazz.setClas_start(rs.getDate("clas_start"));
            }
            if(rs.getDate("clas_end")!=null&&!rs.getDate("clas_end").equals("")){
                clazz.setClas_end(rs.getDate("clas_end"));
            }
            clazz.setClas_time(rs.getTimestamp("clas_time"));
            clazz.setClas_week(rs.getInt("clas_week"));
            clazzes.add(clazz);
        }
        return clazzes;
    }

    @Override
    public List<Clazz> allClazzWithTeac_id(int teac_id) throws SQLException {
        String sql = "SELECT * FROM class WHERE  teac_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,teac_id);
        ResultSet rs = ps.executeQuery();
        List<Clazz> clazzes = new ArrayList<>();
        while (rs.next()){
            Clazz clazz = new Clazz();
            clazz.setClas_id(rs.getInt("clas_id"));
            clazz.setTeac_id(rs.getInt("teac_id"));
            clazz.setClas_name(rs.getString("clas_name"));
            if(rs.getDate("clas_start")!=null&&!rs.getDate("clas_start").equals("")){
                clazz.setClas_start(rs.getDate("clas_start"));
            }
            if(rs.getDate("clas_end")!=null&&!rs.getDate("clas_end").equals("")){
                clazz.setClas_end(rs.getDate("clas_end"));
            }
            clazz.setClas_time(rs.getTimestamp("clas_time"));
            clazz.setClas_week(rs.getInt("clas_week"));
            clazzes.add(clazz);
        }
        return clazzes;
    }

    @Override
    public List<Clazz> findPartClazz(int startIndex, int pageSize) throws SQLException {
        String selectSql = "SELECT * FROM class LIMIT ?,?";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ps.setInt(1,startIndex);
        ps.setInt(2,pageSize);
        ResultSet rs = ps.executeQuery();
        List<Clazz> clazzes = new ArrayList<>();
        while (rs.next()){
            Clazz clazz = new Clazz();
            clazz.setClas_id(rs.getInt("clas_id"));
            clazz.setTeac_id(rs.getInt("teac_id"));
            clazz.setClas_name(rs.getString("clas_name"));
            if(rs.getDate("clas_start")!=null&&!rs.getDate("clas_start").equals("")){
                clazz.setClas_start(rs.getDate("clas_start"));
            }
            if(rs.getDate("clas_end")!=null&&!rs.getDate("clas_end").equals("")){
                clazz.setClas_end(rs.getDate("clas_end"));
            }
            clazz.setClas_time(rs.getTimestamp("clas_time"));
            clazz.setClas_week(rs.getInt("clas_week"));
            clazzes.add(clazz);
        }
        return clazzes;
    }
}
