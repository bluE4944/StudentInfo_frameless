package com.xinan.entity;

import java.util.ArrayList;

/**
 * @author 11940
 */
public class SelectSqlBean<T> {
    /**
     * 可添加的sql语句
     */
    private StringBuilder sbSql;
    /**
     * 有多少T型的数据 为了让其他类型的也能用
     */
    private ArrayList<T> intNum = new ArrayList<>();

    /**
     * int 集合
     */
    private ArrayList<Integer> integers = new ArrayList<>();

    /**
     * 有多少String类型的数据
     */
    private ArrayList<String> stringNum = new ArrayList<>();

    /**
     * 计数
     */
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        this.count++;
    }

    public SelectSqlBean() {
    }

    public StringBuilder getSbSql() {
        return sbSql;
    }

    public void setSbSql(StringBuilder sbSql) {
        this.sbSql = sbSql;
    }

    public ArrayList<T> getIntNum() {
        return intNum;
    }

    public void setIntNum(ArrayList<T> intNum) {
        this.intNum = intNum;
    }

    public ArrayList<String> getStringNum() {
        return stringNum;
    }

    public void setStringNum(ArrayList<String> stringNum) {
        this.stringNum = stringNum;
    }

    public ArrayList<Integer> getIntegers() {
        return integers;
    }

    public void setIntegers(ArrayList<Integer> integers) {
        this.integers = integers;
    }

}
