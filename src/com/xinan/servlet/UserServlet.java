package com.xinan.servlet;

import com.google.gson.Gson;
import com.xinan.dao.UserDao;
import com.xinan.entity.PageBean;
import com.xinan.entity.User;
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
public class UserServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserServlet() {
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
        User user = new User();
        //返回给前端的信息
        String msg = "";

        String operate = request.getParameter("operate");
        if(request.getParameter("uid")!=null&&!request.getParameter("uid").equals("")) {
            user.setId(Integer.valueOf(request.getParameter("uid")));
        }
        if(request.getParameter("administer")!=null&&!request.getParameter("administer").equals("")) {
            user.setAdminister(Integer.valueOf(request.getParameter("administer")));
        }
        user.setUsername(request.getParameter("uname"));
        user.setPhoneNumber(request.getParameter("uphoneNumber"));
        try {
            switch (operate){
                case "save":
                    if(userDao.haveId(user.getId())){
                        msg = "保存失败，已有相同id的用户了哟，宝贝！";
                        response.getWriter().print(msg);
                        return ;
                    }
                    userDao.save(user);
                    break;
                case "select":
                    if(!userDao.haveUser(user)){
                        msg = "没有找到相关信息的用户哟，宝贝！";
                        response.getWriter().print(msg);
                        return ;
                    }
                    break;
                case "selectUser":
                    /**
                     *  是建表的ajax 的请求
                     *  返回PageBean 对象
                     */
                    //获取当前是第几页
                    int pageNum = Integer.valueOf(request.getParameter("pageNum"));
                    //每页显示的记录数
                    int pageSize = Integer.valueOf(request.getParameter("pageSize"));
                    //调用service层方法 处理
                    PageService us = new PageService();
                    //获取一个PageBean对象，pb包含了所有分页需要的数据
                    PageBean pb = us.getSelectPage(pageNum,pageSize,user);
                    //创建Gson对象
                    Gson gson  = new Gson();
                    //获得JSON格式的字符串
                    String pageJson = gson.toJson(pb);
                    //返回给前端
                    response.getWriter().print(pageJson);
                    break;
                case "update":
                    //更新用户信息的操作
                    if(!userDao.haveId(user.getId())){
                        msg = "更新失败，没有相关信息的用户哟，宝贝！";
                        response.getWriter().print(msg);
                        return ;
                    }
                    userDao.update(user);
                    break;
                case  "delete":
                    int user_id=-1;
                    //计数
                    int count=0;
                    while(request.getParameter("user_id"+count)!=null&&!request.getParameter("user_id"+count).equals("")){
                        user_id = Integer.valueOf(request.getParameter("user_id"+count));
                        userDao.delete(user_id);
                        count++;
                    }
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
