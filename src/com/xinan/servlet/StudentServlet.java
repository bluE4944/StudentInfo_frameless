package com.xinan.servlet;

import com.google.gson.Gson;
import com.xinan.dao.StudentDao;
import com.xinan.entity.PageBean;
import com.xinan.entity.Student;
import com.xinan.service.PageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author 11940
 */
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StudentServlet(){
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
        StudentDao studentDao = StudentDao.getInstance();
        //创建Gson对象
        Gson gson = new  Gson();
        //class id
        int stu_id;
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
        Student student = new Student();
        if(request.getParameter("clas_id")!=null&&!request.getParameter("clas_id").equals("")){
            student.setClas_id(Integer.valueOf(request.getParameter("clas_id")));
        }
        student.setStu_name(request.getParameter("stu_name"));
        student.setStu_sex(request.getParameter("stu_sex"));
        if(request.getParameter("stu_age")!=null&&!request.getParameter("stu_age").equals("")){
            student.setStu_age(Float.valueOf(request.getParameter("stu_age")));
        }
        if(request.getParameter("stu_totalPeriod")!=null&&!request.getParameter("stu_totalPeriod").equals("")){
            student.setStu_totalPeriod(Integer.valueOf(request.getParameter("stu_totalPeriod")));
        }
        if(request.getParameter("stu_remainPeriod")!=null&&!request.getParameter("stu_remainPeriod").equals("")){
            student.setStu_remainPeriod(Integer.valueOf(request.getParameter("stu_remainPeriod")));
        }
        try {
            switch (operate) {
                case "save":
                    student.setStu_start(new Date());
                    studentDao.save(student);
                    break;
                case "edit":
                    student.setStu_id(Integer.valueOf(request.getParameter("stu_id")));
                    studentDao.upDate(student);
                    break;
                case "delete":
                    stu_id = -1;
                    //计数
                    int count=0;
                    while(request.getParameter("stu_id"+count)!=null&&!request.getParameter("stu_id"+count).equals("")){
                        stu_id = Integer.valueOf(request.getParameter("stu_id"+count));
                        studentDao.delete(stu_id);
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
                    pb = ps.getAllStudentPage(pageNum,pageSize);
                    //获得JSON格式的字符串
                    pageJson = gson.toJson(pb);
                    //返回给前端
                    response.getWriter().print(pageJson);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
