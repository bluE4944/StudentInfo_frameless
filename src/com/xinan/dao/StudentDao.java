package com.xinan.dao;

import com.xinan.daointer.StudentDaoInter;
import com.xinan.entity.SelectSqlBean;
import com.xinan.entity.Student;
import com.xinan.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author 11940
 */
public class StudentDao implements StudentDaoInter {
    /**
     * 私有的静态StudentDao对象
     */
    private static StudentDao studentDao= new StudentDao();
    /**
     * 调用数据库连接工厂 获得数据库 连接
     */
    private DBUtil dbUtil = DBUtil.getInstance();
    private Connection conn =dbUtil.getConnection();

    /**
     * 课拼接的sql语句
     */
    private StringBuilder sbSql;

    /**
     * 私有化构造器
     */
    private StudentDao(){

    }

    public static StudentDao getInstance(){
        return studentDao;
    }

    @Override
    public void save(Student student) throws SQLException {
        String saveSql = "INSERT INTO student(clas_id,stu_name,stu_sex,stu_age,stu_start) values(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(saveSql);
        ps.setInt(1,student.getClas_id());
        ps.setString(2,student.getStu_name());
        ps.setString(3,student.getStu_sex());
        ps.setFloat(4,student.getStu_age());
        ps.setString(5,student.getStu_start());
        ps.execute();
    }

    @Override
    public void upDate(Student student) throws SQLException {
        String sql = "UPDATE student SET clas_id = ?,stu_name=?,stu_sex=?,stu_age=?,stu_remainPeriod=?,stu_totalPeriod=? WHERE stu_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,student.getClas_id());
        ps.setString(2,student.getStu_name());
        ps.setString(3,student.getStu_sex());
        ps.setFloat(4,student.getStu_age());
        ps.setInt(5,student.getStu_remainPeriod());
        ps.setInt(6,student.getStu_totalPeriod());
        ps.setInt(7,student.getStu_id());
        ps.execute();
    }

    @Override
    public void delete(int stu_id) throws SQLException {
        String sql = "DELETE  FROM student WHERE stu_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,stu_id);
        ps.execute();
    }

    @Override
    public List<Student> findAll() throws SQLException {
        String selectSql = "SELECT * FROM student ";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Student> arrayList = new ArrayList<>();
        while (rs.next()){
            Student student = new Student();
            student.setStu_id(rs.getInt("stu_id"));
            student.setStu_age(rs.getFloat("stu_age"));
            student.setStu_name(rs.getString("stu_name"));
            student.setStu_sex(rs.getString("stu_sex"));
            arrayList.add(student);
        }
        return arrayList;
    }

    @Override
    public List<Student> findSelectStudent(int startIndex, int pageSize, Student student,int littleAge,int bigAge) throws SQLException {
        SelectSqlBean<Float> selectSqlBean = getSql("SELECT *",student,littleAge,bigAge);
        //拼接sql语句
        selectSqlBean.getSbSql().append(" LIMIT ?,?");
        PreparedStatement ps = conn.prepareStatement(sbSql.toString());
        //设置数据
        for (float temp: selectSqlBean.getIntNum()) {
            selectSqlBean.incrementCount();
            ps.setFloat(selectSqlBean.getCount(),temp);
        }
        for(int temp : selectSqlBean.getIntegers()){
            selectSqlBean.incrementCount();
            ps.setInt(selectSqlBean.getCount(),temp);
        }
        //设置数据
        for (String str: selectSqlBean.getStringNum()) {
            //计数器加1
            selectSqlBean.incrementCount();
            //设置数据
            ps.setString(selectSqlBean.getCount(),str);
        }
        selectSqlBean.incrementCount();
        ps.setInt(selectSqlBean.getCount(),startIndex);
        selectSqlBean.incrementCount();
        ps.setInt(selectSqlBean.getCount(),pageSize);
        ResultSet rs = ps.executeQuery();
        List<Student> students = new ArrayList<>();
        while (rs.next()){
            Student newStudent = new Student();
            student.setStu_id(rs.getInt("stu_id"));
            student.setClas_id(rs.getInt("clas_id"));
            student.setStu_age(rs.getFloat("stu_age"));
            student.setStu_name(rs.getString("stu_name"));
            student.setStu_sex(rs.getString("stu_sex"));
            student.setStu_totalPeriod(rs.getInt("stu_totalPeriod"));

            student.setStu_remainPeriod(rs.getInt("stu_remainPeriod"));
            students.add(newStudent);
        }
        return students;
    }

    @Override
    public List<Student> studentSelect(Student student,int littleAge,int bigAge) throws SQLException {
        SelectSqlBean<Float> selectSqlBean = getSql("SELECT *",student,littleAge,bigAge);
        PreparedStatement ps = conn.prepareStatement(sbSql.toString());
        //设置数据
        if(littleAge!=0&&bigAge!=0){
            //输入了两个值
            selectSqlBean.incrementCount();
            ps.setInt(selectSqlBean.getCount(),littleAge);
            selectSqlBean.incrementCount();
            ps.setInt(selectSqlBean.getCount(),bigAge);
        }
        //设置数据
        for (String str: selectSqlBean.getStringNum()) {
            //计数器加1
            selectSqlBean.incrementCount();
            //设置数据
            ps.setString(selectSqlBean.getCount(),str);
        }
        ResultSet rs = ps.executeQuery();
        List<Student> students = new ArrayList<>();
        while (rs.next()){
            Student newStudent = new Student();
            newStudent.setStu_id(rs.getInt("stu_id"));
            newStudent.setClas_id(rs.getInt("clas_id"));
            newStudent.setStu_age(rs.getFloat("stu_age"));
            newStudent.setStu_name(rs.getString("stu_name"));
            newStudent.setStu_sex(rs.getString("stu_sex"));
            newStudent.setStu_totalPeriod(rs.getInt("stu_totalPeriod"));
            newStudent.setStu_remainPeriod(rs.getInt("stu_remainPeriod"));
            students.add(newStudent);
        }
        return students;
    }

    @Override
    public List<Student> findPartStudent(int startIndex, int pageSize) throws SQLException {
        String selectSql = "SELECT * FROM student LIMIT ?,?";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ps.setInt(1,startIndex);
        ps.setInt(2,pageSize);
        ResultSet rs = ps.executeQuery();
        ArrayList<Student> arrayList = new ArrayList<>();
        while (rs.next()){
            Student student = new Student();
            student.setStu_id(rs.getInt("stu_id"));
            student.setClas_id(rs.getInt("clas_id"));
            student.setStu_age(rs.getFloat("stu_age"));
            student.setStu_name(rs.getString("stu_name"));
            student.setStu_sex(rs.getString("stu_sex"));
            student.setStu_totalPeriod(rs.getInt("stu_totalPeriod"));
            student.setStu_remainPeriod(rs.getInt("stu_remainPeriod"));
            arrayList.add(student);
        }
        return arrayList;
    }

    @Override
    public boolean haveStudent(Student student,int littleAge,int bigAge) throws SQLException {
        SelectSqlBean<Float> selectSqlBean = getSql("SELECT *",student,littleAge,bigAge);
        PreparedStatement ps = conn.prepareStatement(sbSql.toString());
        //设置数据
        if(littleAge!=0&&bigAge!=0){
            //输入了两个值
            selectSqlBean.incrementCount();
            ps.setInt(selectSqlBean.getCount(),littleAge);
            selectSqlBean.incrementCount();
            ps.setInt(selectSqlBean.getCount(),bigAge);
        }
        //设置数据
        for (String str: selectSqlBean.getStringNum()) {
            //计数器加1
            selectSqlBean.incrementCount();
            //设置数据
            ps.setString(selectSqlBean.getCount(),str);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            return true;
        }
        return false;
    }


    @Override
    public List<Student> teac_idFindStudent(int teac_id) throws SQLException {
        String sql = "SELECT * " +
                "FROM student,class,teacher " +
                "WHERE student.stu_id=class.stu_id AND class.teac_id = teacher.teac_id AND teac_id = ? " +
                "ORDER BY stu_id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,teac_id);
        ResultSet rs = ps.executeQuery();
        List<Student> students = new ArrayList<>();
        while (rs.next()){
            Student student = new Student();
            student.setStu_id(rs.getInt("stu_id"));
            student.setStu_age(rs.getFloat("stu_age"));
            student.setStu_name(rs.getString("stu_name"));
            student.setStu_sex(rs.getString("stu_sex"));
            student.setClas_id(rs.getInt("clas_id"));
            student.setStu_remainPeriod(rs.getInt("stu_remainPeriod"));
            student.setStu_totalPeriod(rs.getInt("stu_totalPeriod"));
            student.setStu_start(rs.getDate("stu_start"));
            student.setStu_end(rs.getDate("stu_end"));
            students.add(student);
        }
        return students;
    }

    @Override
    public List<Student> clas_idFindStudent(int clas_id) throws SQLException {
        String sql = "SELECT * " +
                "FROM student,class " +
                "WHERE student.stu_id=class.stu_id AND clas_id = ? " +
                "ORDER BY stu_id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,clas_id);
        ResultSet rs = ps.executeQuery();
        List<Student> students = new ArrayList<>();
        while (rs.next()){
            Student student = new Student();
            student.setStu_id(rs.getInt("stu_id"));
            student.setStu_age(rs.getFloat("stu_age"));
            student.setStu_name(rs.getString("stu_name"));
            student.setStu_sex(rs.getString("stu_sex"));
            student.setClas_id(rs.getInt("clas_id"));
            student.setStu_remainPeriod(rs.getInt("stu_remainPeriod"));
            student.setStu_totalPeriod(rs.getInt("stu_totalPeriod"));
            students.add(student);
        }
        return students;
    }


    /**
     * 是否添加AND
     */
    private void needAddAnd(){
        String str = sbSql.toString();
        String pattern = ".*=.*";
        //利用正则表达式 检查 查询语句里是否有 = 号
        boolean isMatch = Pattern.matches(pattern, str);
        if(isMatch){
            //有等于号，表示之前有数据 ，添加AND
            sbSql.append(" AND ");
        }
        //没有等号就表示之前没数据 ，不用添加AND
    }

    /**
     * 是否添加逗号
     */
    private void needAddComma(){
        String str = sbSql.toString();
        String pattern = ".*=.*";
        //利用正则表达式 检查 查询语句里是否有 = 号
        boolean isMatch = Pattern.matches(pattern, str);
        if(isMatch){
            //有等于号，表示之前有数据 ，添加AND
            sbSql.append(" , ");
        }
        //没有等号就表示之前没数据 ，不用添加AND
    }

    /**
     * 根据student对象获得SelectSqlBean 对象 包含拼接的sql语句 和 其中参数的值
     * @param sql
     * @param student
     * @param littleAge 小的年龄值
     * @param bigAge    大的年龄的值
     * @return
     */
    private SelectSqlBean getSql(String sql , Student student,int littleAge,int bigAge){
        //获得administer
        float  stu_age= student.getStu_age();
        //获得名字
        String stu_name = student.getStu_name();
        //获得性别
        String stu_sex = student.getStu_sex();

        //有多少float类型的数据
        ArrayList<Float> floatNum = new ArrayList<>();
        //有多少String类型的数据
        ArrayList<String> stringNum = new ArrayList<>();
        //有多少Int类型的数据
        ArrayList<Integer> integers = new ArrayList<>();


        SelectSqlBean<Float> selectSqlBean = new SelectSqlBean();

        //拼接sql语句
        if(sql.equals("update")){
            sbSql = new StringBuilder(sql+" student SET ");
        }else {
            sbSql = new StringBuilder(sql+" FROM student WHERE ");
        }
        /**
         * int  float类型的数据加在前面
         * 按照
         * clas_id stu_age stu_name stu_sex   的顺序来添加
         */
        if(stu_age!=-1.0){
            //查询了age 或是更改了id
            if(littleAge==0&&bigAge==0){
                //一个值没有 更改，或是查找对应的 age
                sbSql.append(" stu_age=? ");
                floatNum.add(stu_age);
            }else {
                //输入了两个值
                sbSql.append(" stu_age>? AND stu_age<? ");
                integers.add(littleAge);
                integers.add(bigAge);
            }
        }


        //stu_name 不为空
        if (stu_name!=null&&!stu_name.equals("")){
            if(sql.equals("update")){
                needAddComma();
            }else {
                needAddAnd();
            }
            sbSql.append(" stu_name=? ");
            stringNum.add(stu_name);
        }

        //stu_sex 不为空
        if(stu_sex!=null&&!stu_sex.equals("")){
            if(sql.equals("update")){
                needAddComma();
            }else {
                needAddAnd();
            }
            sbSql.append(" stu_sex=? ");
            stringNum.add(stu_sex);
        }

        selectSqlBean.setIntegers(integers);
        selectSqlBean.setSbSql(sbSql);
        selectSqlBean.setIntNum(floatNum);
        selectSqlBean.setStringNum(stringNum);
        return selectSqlBean;
    }
}
