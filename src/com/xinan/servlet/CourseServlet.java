package com.xinan.servlet;

import com.google.gson.Gson;
import com.xinan.dao.CourseDao;
import com.xinan.entity.Course;
import com.xinan.entity.PageBean;
import com.xinan.service.PageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author 11940
 */
public class CourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CourseServlet(){
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
        CourseDao courseDao = CourseDao.getInstance();
        //创建Gson对象
        Gson gson = new  Gson();
        //cour id
        int cour_id;
        //当前页数
        int pageNum;
        //记录条数
        int pageSize;
        //分页服务对象
        PageService ps;
        //分页对象
        PageBean pb;
        //返回的分页 json 数据
        String pageJson;
        //请求 过来的操作
        String operate = request.getParameter("operate");
        try {
            switch (operate) {
                case "save":
                    Course course = new Course();
                    course.setCour_name(request.getParameter("cour_name"));
                    course.setCour_subject(request.getParameter("cour_subject"));
                    course.setCour_introduction(request.getParameter("cour_introduction"));
                    courseDao.save(course);
                    break;
                case "delete":
                    //批量删除
                    cour_id=-1;
                    //计数
                    int count=0;
                    while(request.getParameter("cour_id"+count)!=null&&!request.getParameter("cour_id"+count).equals("")){
                        cour_id = Integer.valueOf(request.getParameter("cour_id"+count));
                        courseDao.delete(cour_id);
                        count++;
                    }
                    break;
                case "findAll":
                    //所有记录的分页
                    //获取当前是第几页
                    pageNum = Integer.valueOf(request.getParameter("pageNum"));
                    //每页显示的记录数
                    pageSize = Integer.valueOf(request.getParameter("pageSize"));
                    //调用service层方法 处理
                    ps = new PageService();
                    //获取一个PageBean对象，pb包含了所有分页需要的数据
                    pb = ps.getAllCoursePage(pageNum,pageSize);
                    //创建Gson对象
                    //获得JSON格式的字符串
                    pageJson = gson.toJson(pb);
                    //返回给前端
                    response.getWriter().print(pageJson);
                    break;
                case "Search":
                    //根据teac_id的分页
                    //获取当前是第几页
                    String cour_subject = request.getParameter("cour_subject");
                    pageNum = Integer.valueOf(request.getParameter("pageNum"));
                    //每页显示的记录数
                    pageSize = Integer.valueOf(request.getParameter("pageSize"));
                    //调用service层方法 处理
                    ps = new PageService();
                    //获取一个PageBean对象，pb包含了所有分页需要的数据
                    pb = ps.PageWithSubject(cour_subject,pageNum,pageSize);
                    //获得JSON格式的字符串
                    pageJson = gson.toJson(pb);
                    //返回给前端
                    response.getWriter().print(pageJson);
                    break;
                default:
                    break;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
