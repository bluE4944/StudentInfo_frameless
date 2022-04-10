package com.xinan.entity;

import com.xinan.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 11940
 */
public class Student implements Serializable {
    /**
     * 学生id 主键
     */
    private int stu_id;

    /**
     * 学生所在班级id 主键
     */
    private int clas_id;
    /**
     * 学生姓名
     */
    private String stu_name;
    /**
     * 学生性别
     */
    private String stu_sex;
    /**
     * 学生年龄
     */
    private float stu_age;

    /**
     * 学生剩余学时
     */
    private int stu_remainPeriod;

    /**
     * 学生总学时
     */
    private int stu_totalPeriod;

    /**
     * 学生在这个班的开始时间
     */
    private String stu_start;

    /**
     * 学生在这个班结束时间
     */
    private String stu_end;

    /**
     * 空构造
     */
    public Student() {
        stu_id=-1;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_sex() {
        return stu_sex;
    }

    public void setStu_sex(String stu_sex) {
        this.stu_sex = stu_sex;
    }

    public float getStu_age() {
        return stu_age;
    }

    public void setStu_age(float stu_age) {
        this.stu_age = stu_age;
    }

    public int getClas_id() {
        return clas_id;
    }

    public void setClas_id(int clas_id) {
        this.clas_id = clas_id;
    }

    public int getStu_remainPeriod() {
        return stu_remainPeriod;
    }

    public void setStu_remainPeriod(int stu_remainPeriod) {
        this.stu_remainPeriod = stu_remainPeriod;
    }

    public int getStu_totalPeriod() {
        return stu_totalPeriod;
    }

    public void setStu_totalPeriod(int stu_totalPeriod) {
        this.stu_totalPeriod = stu_totalPeriod;
    }

    public String getStu_start() {
        return stu_start;
    }

    public void setStu_start(Date stu_start) {
        this.stu_start = DateUtil.getDate(stu_start);
    }

    public String getStu_end() {
        return stu_end;
    }

    public void setStu_end(Date stu_end) {
        this.stu_end = DateUtil.getDate(stu_end);
    }

    @Override
    public String toString() {
        return "Student{" +
                "stu_id=" + stu_id +
                ", clas_id=" + clas_id +
                ", stu_name='" + stu_name + '\'' +
                ", stu_sex='" + stu_sex + '\'' +
                ", stu_age=" + stu_age +
                ", stu_remainPeriod=" + stu_remainPeriod +
                ", stu_totalPeriod=" + stu_totalPeriod +
                ", stu_start='" + stu_start + '\'' +
                ", stu_end='" + stu_end + '\'' +
                '}';
    }
}
