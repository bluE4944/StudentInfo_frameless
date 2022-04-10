package com.xinan.service;

import com.xinan.dao.*;
import com.xinan.entity.*;
import com.xinan.util.DBUtil;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 11940
 */
public class PageService<T> {


    public PageService(){

    }

    /**
     * 获得查询后的 user数据的 PageBean对象
     * @param pageNum
     * @param pageSize
     * @param user
     * @return
     */
    public PageBean<User> getSelectPage(int pageNum,int pageSize,User user){
        UserDao userDao = UserDao.getInstance();

        //在这里将PageBean中的数据创建好，并将该对象传回去，
        //先要从数据库中获取查询语句获得的用户的数据量有多少，获得totalRecord
        try {
            List<User> allUser = userDao.userSelect(user);
            int totalRecord = allUser.size();

            //有了这三个数据就能创建 PageBean对象了
            PageBean<User> pb = new PageBean<>(pageNum,pageSize,totalRecord);

            //获取PageBean对象中的startIndex
            int startIndex = pb.getStartIndex();

            //有startIndex和PageSize,就可以拿到每页的数据了
            pb.setList(userDao.findSelectUser(startIndex,pageSize,user));
            return pb;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获得所有user数据的 PageBean对象
     * @param pageNum 当前页
     * @param pageSize 记录数
     * @return user分页对象
     */
    public PageBean<User> getAllUserPage(int pageNum,int pageSize){
        UserDao userDao = UserDao.getInstance();

        //在这里将PageBean中的数据创建好，并将该对象传回去，
        //先要从数据库中获取所有用户的数据量有多少，获得totalRecord
        try {
            List<User> allUser = userDao.findAllUser();
            int totalRecord = allUser.size();

            //有了这三个数据就能创建 PageBean对象了
            PageBean<User> pb = new PageBean<>(pageNum,pageSize,totalRecord);

            //获取PageBean对象中的startIndex
            int startIndex = pb.getStartIndex();

            //有startIndex和PageSize,就可以拿到每页的数据了
            pb.setList(userDao.findPartUser(startIndex,pageSize));
            return pb;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获得所有老师 分页对象
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageBean<Teacher> getAllTeacherPage(int pageNum, int pageSize){
        TeacherDao teacherDao = TeacherDao.getInstance();

        //在这里将PageBean中的数据创建好，并将该对象传回去，
        //先要从数据库中获取所有用户的数据量有多少，获得totalRecord
        try {
            List<Teacher> allData = teacherDao.findAllTeacher();
            int totalRecord = allData.size();

            //有了这三个数据就能创建 PageBean对象了
            PageBean<Teacher> pb = new PageBean<>(pageNum,pageSize,totalRecord);

            //获取PageBean对象中的startIndex
            int startIndex = pb.getStartIndex();

            //有startIndex和PageSize,就可以拿到每页的数据了
            pb.setList(teacherDao.findPartTeacher(startIndex,pageSize));
            return pb;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 查找所有clazz的分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageBean<Clazz> getAllClazzPage(int pageNum, int pageSize){
        ClazzDao clazzDao = ClazzDao.getInstance();

        //在这里将PageBean中的数据创建好，并将该对象传回去，
        //先要从数据库中获取所有用户的数据量有多少，获得totalRecord
        try {
            List<Clazz> allData = clazzDao.findAllClazz();
            int totalRecord = allData.size();

            //有了这三个数据就能创建 PageBean对象了
            PageBean<Clazz> pb = new PageBean<>(pageNum,pageSize,totalRecord);

            //获取PageBean对象中的startIndex
            int startIndex = pb.getStartIndex();

            //有startIndex和PageSize,就可以拿到每页的数据了
            pb.setList(clazzDao.findPartClazz(startIndex,pageSize));
            return pb;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据teac_id 获得Class 分页对象
     * @param teac_id 老师id
     * @param pageNum 当前页
     * @param pageSize 页面显示记录数量
     * @return Clazz 分页对象
     */
    public PageBean<Clazz> ClazzPageWithTeac_id(int teac_id,int pageNum, int pageSize){
        ClazzDao clazzDao = ClazzDao.getInstance();

        //在这里将PageBean中的数据创建好，并将该对象传回去，
        //先要从数据库中获取所有用户的数据量有多少，获得totalRecord
        try {
            List<Clazz> allData = clazzDao.allClazzWithTeac_id(teac_id);
            int totalRecord = allData.size();

            //有了这三个数据就能创建 PageBean对象了
            PageBean<Clazz> pb = new PageBean<>(pageNum,pageSize,totalRecord);

            //获取PageBean对象中的startIndex
            int startIndex = pb.getStartIndex();

            //有startIndex和PageSize,就可以拿到每页的数据了
            pb.setList(clazzDao.findClazzWithTeac_id(teac_id,startIndex,pageSize));
            return pb;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 获得所有学生信息 分页对象
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageBean<Student> getAllStudentPage(int pageNum, int pageSize){
        StudentDao studentDao = StudentDao.getInstance();

        //在这里将PageBean中的数据创建好，并将该对象传回去，
        //先要从数据库中获取所有用户的数据量有多少，获得totalRecord
        try {
            List<Student> allData = studentDao.findAll();
            int totalRecord = allData.size();

            //有了这三个数据就能创建 PageBean对象了
            PageBean<Student> pb = new PageBean<>(pageNum,pageSize,totalRecord);

            //获取PageBean对象中的startIndex
            int startIndex = pb.getStartIndex();

            //有startIndex和PageSize,就可以拿到每页的数据了
            pb.setList(studentDao.findPartStudent(startIndex,pageSize));
            return pb;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获得所有课程信息 分页对象
     * @param pageNum
     * @param pageSize
     * @return
     */
        public PageBean<Course> getAllCoursePage(int pageNum, int pageSize){
        CourseDao courseDao = CourseDao.getInstance();

        //在这里将PageBean中的数据创建好，并将该对象传回去，
        //先要从数据库中获取所有用户的数据量有多少，获得totalRecord
        try {
            List<Course> allData = courseDao.findAll();
            int totalRecord = allData.size();

            //有了这三个数据就能创建 PageBean对象了
            PageBean<Course> pb = new PageBean<>(pageNum,pageSize,totalRecord);

            //获取PageBean对象中的startIndex
            int startIndex = pb.getStartIndex();

            //有startIndex和PageSize,就可以拿到每页的数据了
            pb.setList(courseDao.findOnePage(startIndex,pageSize));
            return pb;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据teac_id 获得Class 分页对象
     * @param cour_subject 课程主题
     * @param pageNum 当前页
     * @param pageSize 页面显示记录数量
     * @return Course 分页对象
     */
    public PageBean<Course> PageWithSubject(String cour_subject,int pageNum, int pageSize){
        CourseDao courseDao = CourseDao.getInstance();

        //在这里将PageBean中的数据创建好，并将该对象传回去，
        //先要从数据库中获取所有用户的数据量有多少，获得totalRecord
        try {
            List<Course> allData = courseDao.withSubject(cour_subject);
            int totalRecord = allData.size();

            //有了这三个数据就能创建 PageBean对象了
            PageBean<Course> pb = new PageBean<>(pageNum,pageSize,totalRecord);

            //获取PageBean对象中的startIndex
            int startIndex = pb.getStartIndex();

            //有startIndex和PageSize,就可以拿到每页的数据了
            pb.setList(courseDao.withSubjectPage(cour_subject,startIndex,pageSize));
            return pb;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
