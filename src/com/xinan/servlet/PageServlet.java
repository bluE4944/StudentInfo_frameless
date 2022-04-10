package com.xinan.servlet;

import com.google.gson.Gson;
import com.xinan.entity.PageBean;
import com.xinan.service.PageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 11940
 */
public class PageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PageServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //获取当前是第几页
        int pageNum = Integer.valueOf(request.getParameter("pageNum"));
        //每页显示的记录数
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        //调用service层方法 处理
        PageService us = new PageService();
        //获取一个PageBean对象，pb包含了所有分页需要的数据
        PageBean pb = us.getAllUserPage(pageNum,pageSize);
        //创建Gson对象
        Gson gson  = new Gson();
        //获得JSON格式的字符串
        String pageJson = gson.toJson(pb);
        //返回给前端
        response.getWriter().print(pageJson);
    }
}
