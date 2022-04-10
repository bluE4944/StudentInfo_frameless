package com.xinan.servlet;

import com.xinan.dao.LoginDAO;
import com.xinan.dao.UserDao;
import com.xinan.entity.User;
import com.xinan.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author 11940
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //取出URL中的账号密码参数
        String phoneNumber = request.getParameter(User.PHONENUMBER);
        String password = request.getParameter(User.PASSWORD);
        LoginDAO loginDAO = LoginDAO.getInstance();

        //验证过程
        try {
            if(loginDAO.isLoginOK(phoneNumber,password)){
                out.println("OK");
            }else{
                out.println("Wrong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //登录页面的phoneNumber
        String phoneNumber = request.getParameter(User.PHONENUMBER);
        //登录页面的passWord
        String password = request.getParameter(User.PASSWORD);
        //welcome页面的请求参数
        String getName = request.getParameter("getName");
        //返回的信息
        String msg =null;

        //登陆DAO
        LoginDAO loginDAO = LoginDAO.getInstance();
        User user = null;

        //登录页面验证
        try {
            //welcome页面获取用户名
            if(getName!=null&&!getName.equals("")) {
                if (getName.equals("yes")) {
                    //获得user对象
                    user = loginDAO.getUser(phoneNumber);
                    response.getWriter().print(user.getUsername());
                    return;
                }
            }

            if(loginDAO.isLoginOK(phoneNumber,password) && loginDAO.isAdminister(phoneNumber)){
                System.out.println("LoginServlet.doPost()"+"登陆成功");
            }else{
                System.out.println("LoginServlet.doPost()"+"登陆失败");
                //账号或密码错误
                if(!loginDAO.isLoginOK(phoneNumber,password)){
                    msg = "您的账户或密码错误！";
                }else if(!loginDAO.isAdminister(phoneNumber)){
                    msg = "您不是管理员";
                }else {
                    msg = "其他为止错误";
                }
                response.getWriter().print(msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
