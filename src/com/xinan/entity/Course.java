package com.xinan.entity;

/**
 * @author 11940
 */
public class Course {
    /**
     * 课程id
     */
    private int cour_id;

    /**
     * 课程名
     */
    private String cour_name;

    /**
     * 课程主题
     */
    private String cour_subject;

    /**
     * 课程介绍 （知识点）
     */
    private String cour_introduction;

    /**
     * 空构造
     */
    public Course() {
        cour_id=-1;
    }

    /**
     * getter setter 方法
     */


    public int getCour_id() {
        return cour_id;
    }

    public void setCour_id(int cour_id) {
        this.cour_id = cour_id;
    }

    public String getCour_name() {
        return cour_name;
    }

    public void setCour_name(String cour_name) {
        this.cour_name = cour_name;
    }

    public String getCour_subject() {
        return cour_subject;
    }

    public void setCour_subject(String cour_subject) {
        this.cour_subject = cour_subject;
    }

    public String getCour_introduction() {
        return cour_introduction;
    }

    public void setCour_introduction(String cour_introduction) {
        this.cour_introduction = cour_introduction;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cour_id=" + cour_id +
                ", cour_name='" + cour_name + '\'' +
                ", cour_subject='" + cour_subject + '\'' +
                ", cour_introduction='" + cour_introduction + '\'' +
                '}';
    }
}
