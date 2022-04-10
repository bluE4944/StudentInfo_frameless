package com.xinan.servlet;

import com.google.gson.Gson;
import com.xinan.dao.SystemDao;
import com.xinan.dao.UserDao;
import com.xinan.entity.SystemBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author 11940
 */
public class SettingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SettingServlet() {
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
        UserDao userDao = UserDao.getInstance();
        SystemDao systemDao = SystemDao.getInstance();
        String set_operate  = request.getParameter("set_operate");
        String msg = "";
        String name = "";
        SystemBean systemBean = new SystemBean();
        try {
            switch (set_operate){
                case "setPassword":
                    String newPassword = request.getParameter("newPassword");
                    String oldPassword =request.getParameter("oldPassword");
                    if(!userDao.setPassword(oldPassword,newPassword,request)){
                        //修改密码失败
                        msg = "修改密码失败，原密码错误，baby！";
                        response.getWriter().print(msg);
                    }else {
                        msg = "密码修改成功！";
                        response.getWriter().print(msg);
                    }
                    break;
                case "setSystemName":
                    name = request.getParameter("name");
                    systemDao.setSysName(name);
                    break;
                case "setLoginName":
                    name = request.getParameter("name");
                    systemDao.setLoginName(name);
                    break;
                case "setNavName":
                    name = request.getParameter("name");
                    systemDao.setNavName(name);
                    break;
                case "getLoginName":
                    name =  systemDao.getLoginName();
                    response.getWriter().print(name);
                    break;
                case "getAllName":
                    systemBean.setNavName(systemDao.getNavName());
                    systemBean.setSystemName(systemDao.getSysName());
                    systemBean.setLoginName(systemDao.getLoginName());
                    Gson gson = new Gson();
                    msg = gson.toJson(systemBean);
                    response.getWriter().print(msg);
                    break;
                default:
                    break;
            }
            }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
