package com.xinan.servlet;

import com.google.gson.Gson;
import com.xinan.dao.ClazzDao;
import com.xinan.dao.TeacherDao;
import com.xinan.entity.Clazz;
import com.xinan.entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DataServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DataServlet() {
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
        String operate  = request.getParameter("operate");
        Gson gson = new Gson();
        String msg;
        try {
            switch (operate) {
                case "getTeacher":
                    TeacherDao teacherDao = TeacherDao.getInstance();
                    List<Teacher> teachers = teacherDao.findAllTeacher();
                    msg = gson.toJson(teachers);
                    response.getWriter().print(msg);
                    break;
                case "getClazz":
                    ClazzDao clazzDao = ClazzDao.getInstance();
                    List<Clazz> clazzes = clazzDao.findAllClazz();
                    msg = gson.toJson(clazzes);
                    response.getWriter().print(msg);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}