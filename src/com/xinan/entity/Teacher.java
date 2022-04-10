package com.xinan.entity;

import java.io.Serializable;

/**
 * @author 11940
 */
public class Teacher implements Serializable {
    /**
     *  教师id
     */
    private int teac_id;

    /**
     * 教师姓名
     */
    private String teac_name;

    /**
     * 教师电话
     */
    private String teac_phoneNumber;

    /**
     * 教师住址
     */
    private String teac_address;

    public Teacher() {
        this.teac_id = -1;
    }

    public int getTeac_id() {
        return teac_id;
    }

    public void setTeac_id(int teac_id) {
        this.teac_id = teac_id;
    }

    public String getTeac_name() {
        return teac_name;
    }

    public void setTeac_name(String teac_name) {
        this.teac_name = teac_name;
    }

    public String getTeac_phoneNumber() {
        return teac_phoneNumber;
    }

    public void setTeac_phoneNumber(String teac_phoneNumber) {
        this.teac_phoneNumber = teac_phoneNumber;
    }

    public String getTeac_address() {
        return teac_address;
    }

    public void setTeac_address(String teac_address) {
        this.teac_address = teac_address;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teac_id=" + teac_id +
                ", teac_name='" + teac_name + '\'' +
                ", teac_phoneNumber='" + teac_phoneNumber + '\'' +
                ", teac_address='" + teac_address + '\'' +
                '}';
    }
}
