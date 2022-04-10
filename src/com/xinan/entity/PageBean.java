package com.xinan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 使用泛型是为了在其他项目也可使用该类分页
 * @author 11940
 */
public class PageBean<T> implements Serializable {
    /**
     * 跳转其他页总共能显示几页，应为奇数
     */
    private final int viewPage = 5;

    /*已知数据*/
    /**
     * 当前页，从请求传过来
     */
    private int pageNum;

    /**
     * 每页显示的数据条数
     */
    private int pageSize;

    /**
     * 总的记录条数，查询数据库得到的数据
     */
    private int totalRecord;

    /* 计算得出的数据*/

    /**
     * 总页数，通过totalRecord 和pageSize计算可得
     */
    private int totalPage;

    /**
     * 开始索引，需要从数据库第几行开始取，有了startIndex和pageSize
     * 就知道limit语句的两个数据，就能获得每页需要的显示的数据
     */
    private  int startIndex;

    /**
     * 存放每页需要显示的数据
     */
    private List<T> list;

    /**
     * 分页显示的最前面的页数
     * 如 1 、 2 、 3 、 4 、 5 start为1
     */
    private int start;

    /**
     * 分页显示的最前面的页数
     * 如 1 、 2 、 3 、 4 、 5 end为1
     */
    private int end ;

    /**
     * 通过传进来的三个参数 计算出totalPage 和startIndex
     * @param pageNum
     * @param pageSize
     * @param totalRecord
     */
    public PageBean(int pageNum, int pageSize, int totalRecord) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;

        //totalPage 总页数
        if(totalRecord%pageSize==0){
            //表示可以整除 没有多余数据，总页数正好是它两相除
            this.totalPage = totalRecord/pageSize;
        }else {
            //表示不整除，有多余数据，要再加一页显示多余数据
            this.totalPage = totalRecord/pageSize+1;
        }

        //开始索引 当前页减1 再乘页面显示 数据条数
        this.startIndex = (pageNum-1)*pageSize;
        //初始 分页的第一个页数 和最后一个页数
        this.start = 1;
        this.end = this.viewPage;

        //显示页数的算法
        //显示 viewPage 页
        if(totalPage<=this.viewPage){
            //总页数小于或等于 viewPage  ，end就为总页数的值
            this.end=this.totalPage;
        }else {
            //总页数大于 viewPage ，那么就要根据当前是第几页，来判断start 和end为多少
            this.start = this.pageNum -((this.viewPage-1)/2);
            this.end = this.pageNum + ((this.viewPage-1)/2);
            //不符合规则的情况
            if(start <= 1){
                //如果当前页面是第一页或第二页 ，那么就不使用上面这个规则
                this.start = 1;
                this.end = this.viewPage;
            }else if(end >=this.totalPage){
                //如果当前页面是倒数第一页或第二页 ，那么也不使用上面这个规则
                this.end = totalPage;
                this.start = end-this.viewPage+1;
            }
            System.out.println(start+"--->"+end);
        }

    }

    /**
     * 空构造器
     */
    public PageBean() {
    }



    /*setter
      getter*/

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getViewPage() {
        return viewPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "viewPage=" + viewPage +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalRecord=" + totalRecord +
                ", totalPage=" + totalPage +
                ", startIndex=" + startIndex +
                ", list=" + list.toString() +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
