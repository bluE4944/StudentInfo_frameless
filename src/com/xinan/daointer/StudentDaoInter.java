package com.xinan.daointer;

import com.xinan.entity.Student;
import com.xinan.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 11940
 */
public interface StudentDaoInter {

    /**
     * 根据前端传来的student数据 保存
     * @param student
     * @throws SQLException
     */
    public void save(Student student ) throws SQLException;

    /**
     * 根据前端传来的student数据 更新
     * @param student
     * @throws SQLException
     */
    public void upDate(Student student) throws SQLException;

    /**
     * 根据stu_id删除对象
     * @param stu_id
     * @throws SQLException
     */
    public void delete(int stu_id) throws SQLException;

    /**
     * 查找所有student 表中 所有记录
     * @return student集合
     * @throws SQLException
     */
    public List<Student> findAll() throws SQLException;

    /**
     * 获得查询后的一页 student 集合
     * @param startIndex
     * @param pageSize
     * @param student
     * @param littleAge 比较中小的年龄
     * @param bigAge    比较中大的年龄
     * @return
     * @throws SQLException
     */
    public List<Student> findSelectStudent(int startIndex, int pageSize, Student student,int littleAge,int bigAge) throws SQLException;

    /**
     * 获得执行查询条件后 查询到的所有student记录集合
     * @param student
     * @param littleAge 比较中小的年龄
     * @param bigAge    比较中大的年龄
     * @return
     * @throws SQLException
     */
    public List<Student> studentSelect(Student student,int littleAge,int bigAge)throws SQLException;

    /**
     * 查找一页的Student 数据库数据
     * @param startIndex
     * @param pageSize
     * @return  Student 对象的集合
     * @throws SQLException
     */
    public List<Student> findPartStudent(int startIndex,int pageSize)throws SQLException;

    /**
     * 根据传进来的student对象 查询表里是否有这条信息，如果有 则返回true 反之返回false
     * @param student student对象
     * @param littleAge 比较中小的年龄
     * @param bigAge    比较中大的年龄
     * @return 有记录返回true
     * @throws SQLException
     */
    public boolean haveStudent(Student student,int littleAge,int bigAge)throws SQLException;


    /**
     * 根据老师id查询 他的所有学生
     * @param teac_id
     * @return
     * @throws SQLException
     */
    public List<Student> teac_idFindStudent(int teac_id)throws SQLException;

    /**
     * 通过班级id查找 这个班的所有学生
     * @param clas_id
     * @return
     * @throws SQLException
     */
    public List<Student> clas_idFindStudent(int clas_id)throws SQLException;
}
