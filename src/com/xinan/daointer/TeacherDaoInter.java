package com.xinan.daointer;

import com.xinan.entity.Teacher;
import com.xinan.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 11940
 */
public interface TeacherDaoInter {
    /**
     * 保存Teacher对象到数据库中
     * @param teacher
     * @throws SQLException
     */
    public void save(Teacher teacher) throws SQLException;

    /**
     * 根据teac_id删除teacher 记录
     * @param teac_id
     * @throws SQLException
     */
    public void delete(int teac_id) throws SQLException;

    /**
     * 查找所有teacher
     * @return
     * @throws SQLException
     */
    public List<Teacher> findAllTeacher() throws SQLException;

    /**
     * 查找一页的 Teacher 数据库数据
     * @param startIndex
     * @param pageSize
     * @return  User 对象的集合
     * @throws SQLException
     */
    public List<Teacher> findPartTeacher(int startIndex, int pageSize)throws SQLException;

}
