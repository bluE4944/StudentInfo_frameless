package com.xinan.entity;

import com.xinan.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 11940
 */
public class Clazz implements Serializable {

    /**
     * 班级id
     */
    private int clas_id;

    /**
     * 教师id
     */
    private int teac_id;

    /**
     * 班级名字
     */
    private String clas_name;

    /**
     * 班级记录开始时间
     */
    private String clas_start;

    /**
     * 班级记录结束时间
     */
    private String clas_end;

    /**
     * 班级上课时间
     */
    private String clas_time;

    /**
     *
     */
    private int clas_week;

    /**
     * 空构造
     */
    public Clazz() {
        this.clas_id = -1;
        this.teac_id = -1;
    }

    public int getClas_id() {
        return clas_id;
    }

    public void setClas_id(int clas_id) {
        this.clas_id = clas_id;
    }

    public int getTeac_id() {
        return teac_id;
    }

    public void setTeac_id(int teac_id) {
        this.teac_id = teac_id;
    }

    public String getClas_name() {
        return clas_name;
    }

    public void setClas_name(String clas_name) {
        this.clas_name = clas_name;
    }

    public String getClas_start() {
        return clas_start;
    }

    public void setClas_start(Date clas_start) {
        this.clas_start = DateUtil.getDate(clas_start);
    }

    public String getClas_end() {
        return clas_end;
    }

    public void setClas_end(Date clas_end) {
        this.clas_end = DateUtil.getDate(clas_end);
    }

    public String getClas_time() {
        return clas_time;
    }

    public void setClas_time(Date clas_time) {
        this.clas_time = DateUtil.getTime(clas_time);
    }

    public void setClas_start(String clas_start) {
        this.clas_start = clas_start;
    }

    public void setClas_end(String clas_end) {
        this.clas_end = clas_end;
    }

    public void setClas_time(String clas_time) {
        this.clas_time = clas_time;
    }

    public int getClas_week() {
        return clas_week;
    }

    public void setClas_week(int clas_week) {
        this.clas_week = clas_week;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "clas_id=" + clas_id +
                ", teac_id=" + teac_id +
                ", clas_name='" + clas_name + '\'' +
                ", clas_start='" + clas_start + '\'' +
                ", clas_end='" + clas_end + '\'' +
                ", clas_time='" + clas_time + '\'' +
                ", clas_week=" + clas_week +
                '}';
    }
}
