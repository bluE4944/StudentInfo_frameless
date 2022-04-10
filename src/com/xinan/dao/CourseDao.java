package com.xinan.dao;

import com.xinan.daointer.CourseDaoInter;
import com.xinan.entity.Course;
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
public class CourseDao implements CourseDaoInter {

    private static CourseDao courseDao = new CourseDao();

    /**
     *  连接数据库
     */
    private DBUtil dbUtil = DBUtil.getInstance();
    private Connection conn =dbUtil.getConnection();

    /**
     * 获得静态CourseDao对象
     * @return
     */
    public static CourseDao getInstance(){
        return courseDao;
    }

    /**
     * 私有构造器
     */
    private CourseDao(){}


    @Override
    public void save(Course course) throws SQLException {
        String sql = "INSERT INTO course(cour_name,cour_subject,cour_introduction) values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,course.getCour_name());
        ps.setString(2,course.getCour_subject());
        ps.setString(3,course.getCour_introduction());
        ps.execute();
    }

    @Override
    public void delete(int cour_id) throws SQLException {
        String sql = "DELETE FROM course WHERE cour_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,cour_id);
        ps.execute();
    }

    @Override
    public List<Course> findAll() throws SQLException {
        String sql = "SELECT * FROM course";
        PreparedStatement ps = conn.prepareStatement(sql);
        List<Course> courses = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Course course = new Course();
            course.setCour_id(rs.getInt("cour_id"));
            course.setCour_name(rs.getString("cour_name"));
            course.setCour_subject(rs.getString("cour_subject"));
            course.setCour_introduction(rs.getString("cour_introduction"));
            courses.add(course);
        }
        return courses;
    }

    @Override
    public List<Course> findOnePage(int startIndex, int pageSize) throws SQLException {
        String sql = "SELECT * FROM course LIMIT ?,?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,startIndex);
        ps.setInt(2,pageSize);
        List<Course> courses = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Course course = new Course();
            course.setCour_id(rs.getInt("cour_id"));
            course.setCour_name(rs.getString("cour_name"));
            course.setCour_subject(rs.getString("cour_subject"));
            course.setCour_introduction(rs.getString("cour_introduction"));
            courses.add(course);
        }
        return courses;
    }

    @Override
    public List<Course> withSubject(String cour_subject) throws SQLException {
        String sql = "SELECT * FROM course WHERE cour_subject=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,cour_subject);
        List<Course> courses = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Course course = new Course();
            course.setCour_id(rs.getInt("cour_id"));
            course.setCour_name(rs.getString("cour_name"));
            course.setCour_subject(rs.getString("cour_subject"));
            course.setCour_introduction(rs.getString("cour_introduction"));
            courses.add(course);
        }
        return courses;
    }

    @Override
    public List<Course> withSubjectPage(String cour_subject, int startIndex, int pageSize) throws SQLException {
        String sql = "SELECT * FROM course WHERE cour_subject=? LIMIT ?,?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,cour_subject);
        ps.setInt(2,startIndex);
        ps.setInt(3,pageSize);
        List<Course> courses = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Course course = new Course();
            course.setCour_id(rs.getInt("cour_id"));
            course.setCour_name(rs.getString("cour_name"));
            course.setCour_subject(rs.getString("cour_subject"));
            course.setCour_introduction(rs.getString("cour_introduction"));
            courses.add(course);
        }
        return courses;
    }
}
