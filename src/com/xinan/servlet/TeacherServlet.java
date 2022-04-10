package com.xinan.servlet;

import com.google.gson.Gson;
import com.xinan.dao.TeacherDao;
import com.xinan.entity.PageBean;
import com.xinan.entity.Teacher;
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
public class TeacherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public TeacherServlet(){
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
        TeacherDao teacherDao = TeacherDao.getInstance();
        Teacher teacher = new Teacher();
        String operate = request.getParameter("operate");
        try {
            switch (operate) {
                case "save":
                    teacher.setTeac_name(request.getParameter("teac_name"));
                    teacher.setTeac_phoneNumber(request.getParameter("teac_phoneNumber"));
                    if (request.getParameter("teac_address") != null && !request.getParameter("teac_address").equals("")) {
                        teacher.setTeac_address(request.getParameter("teac_address"));
                    }
                    teacherDao.save(teacher);
                    break;
                case "delete":
                    int teac_id=-1;
                    //计数
                    int count=0;
                    while(request.getParameter("teac_id"+count)!=null&&!request.getParameter("teac_id"+count).equals("")){
                        teac_id = Integer.valueOf(request.getParameter("teac_id"+count));
                        teacherDao.delete(teac_id);
                        count++;
                    }
                    break;
                case "findAll":
                    //获取当前是第几页
                    int pageNum = Integer.valueOf(request.getParameter("pageNum"));
                    //每页显示的记录数
                    int pageSize = Integer.valueOf(request.getParameter("pageSize"));
                    //调用service层方法 处理
                    PageService us = new PageService();
                    //获取一个PageBean对象，pb包含了所有分页需要的数据
                    PageBean pb = us.getAllTeacherPage(pageNum,pageSize);
                    //创建Gson对象
                    Gson gson  = new Gson();
                    //获得JSON格式的字符串
                    String pageJson = gson.toJson(pb);
                    //返回给前端
                    response.getWriter().print(pageJson);
                    break;
                default:
                    break;
            }
        }catch (SQLException e) {
            System.out.println("TeacherServlet----->SQL异常");
            e.printStackTrace();
        }
    }
}
