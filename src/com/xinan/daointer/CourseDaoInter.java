package com.xinan.daointer;

import com.xinan.entity.Course;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 11940
 */
public interface CourseDaoInter {

    /**
     * 保存
     * @param course
     * @throws SQLException
     */
    public void save(Course course) throws SQLException;

    /**
     * 删除
     * @param cour_id
     * @throws SQLException
     */
    public void delete(int cour_id) throws SQLException;

    /**
     * 查找所有Course
     * @return
     * @throws SQLException
     */
    public List<Course> findAll() throws  SQLException;

    /**
     * 查找一页
     * @param startIndex
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public List<Course> findOnePage(int startIndex, int pageSize) throws SQLException;

    /**
     * 根据主题查找
     * @param cour_subject
     * @return
     * @throws SQLException
     */
    public List<Course> withSubject(String cour_subject) throws SQLException;

    /**
     * 根据主题查找一个的Course
     * @param cour_subject
     * @param startIndex
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public List<Course> withSubjectPage(String cour_subject,int startIndex, int pageSize) throws SQLException;
}
