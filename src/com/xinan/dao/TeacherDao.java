package com.xinan.dao;

import com.xinan.daointer.TeacherDaoInter;
import com.xinan.entity.Teacher;
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
public class TeacherDao implements TeacherDaoInter {
    private static TeacherDao teacherDao = new TeacherDao();

    /**
     *  连接数据库
     */
    private DBUtil dbUtil = DBUtil.getInstance();
    private Connection conn =dbUtil.getConnection();

    public static TeacherDao getInstance(){
        return teacherDao;
    }

    /**
     * 私有构造器
     */
    private TeacherDao(){

    }

    @Override
    public void save(Teacher teacher) throws SQLException {
        StringBuilder saveSql = new StringBuilder("INSERT INTO teacher(teac_name,teac_phoneNumber");
        if(teacher.getTeac_address()!=null&&!teacher.getTeac_address().equals("")){
            saveSql.append(",teac_address) values(?,?,?)");
        }else {
            saveSql.append(") values(?,?)");
        }
        PreparedStatement ps = conn.prepareStatement(saveSql.toString());
        ps.setString(1,teacher.getTeac_name());
        ps.setString(2,teacher.getTeac_phoneNumber());
        if(teacher.getTeac_address()!=null&&!teacher.getTeac_address().equals("")) {
            ps.setString(3, teacher.getTeac_address());
        }
        ps.execute();
    }

    @Override
    public void delete(int teac_id) throws SQLException {
        if(teac_id==-1){
            System.out.println("TeacherDao.delete------>删除失败，teac_id=-1");
            return;
        }
        String sql = "DELETE FROM teacher WHERE teac_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,teac_id);
        ps.execute();
    }

    @Override
    public List<Teacher> findAllTeacher() throws SQLException{
        String sql = "SELECT * FROM teacher";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Teacher> teachers = new ArrayList<>();
        while (rs.next()){
            Teacher teacher = new Teacher();
            teacher.setTeac_id(rs.getInt("teac_id"));
            teacher.setTeac_name(rs.getString("teac_name"));
            teacher.setTeac_phoneNumber(rs.getString("teac_phoneNumber"));
            teacher.setTeac_address(rs.getString("teac_address"));
            teachers.add(teacher);
        }
        return teachers;
    }

    @Override
    public List<Teacher> findPartTeacher(int startIndex, int pageSize) throws SQLException {
        String selectSql = "SELECT * FROM teacher LIMIT ?,?";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ps.setInt(1,startIndex);
        ps.setInt(2,pageSize);
        ResultSet rs = ps.executeQuery();
        List<Teacher> teachers = new ArrayList<>();
        while (rs.next()){
            Teacher teacher = new Teacher();
            teacher.setTeac_id(rs.getInt("teac_id"));
            teacher.setTeac_name(rs.getString("teac_name"));
            teacher.setTeac_phoneNumber(rs.getString("teac_phoneNumber"));
            teacher.setTeac_address(rs.getString("teac_address"));
            teachers.add(teacher);
        }
        return teachers;
    }
}
