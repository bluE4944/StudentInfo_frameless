package com.xinan.filter;

import com.xinan.dao.LoginDAO;
import com.xinan.entity.User;
import com.xinan.util.DBUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * @author 11940
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "*.jsp", dispatcherTypes = {})
public class LoginFilter implements Filter {
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String url = request.getRequestURI();

        int idx = url.indexOf("/");
        String endWith = url.substring(idx + 1);
        try {
            if (isOK(endWith)) {
                /*不是登陆页面 进行拦截处理*/
                System.out.println("不是登陆页面，进行拦截处理");
                if (!isLogin(request)) {
                    System.out.println("没有登陆过或者账号密码错误，跳转到登陆页面");
                    response.sendRedirect("/Server/page/error.html");
                } else {
                    System.out.println("已经登陆，进行下一步");
                    chain.doFilter(req, resp);
                }
            } else {
                chain.doFilter(req, resp);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isOK(String url) {
        String pattern = ".*jsp";
        //利用正则表达式 检查路径里（url）是否有 .jsp
        boolean isMatch = Pattern.matches(pattern, url);

        if (isMatch) {
            //有 .jsp
            System.out.println("有jsp");
            if (url.equals("index.jsp")) {
                //是首页 ，返回false ，不检查
                return false;
            }
            // 不是首页为 false
            return true;
        } else {
            //没有 .jsp 直接返回false
            return false;
        }
    }

    private boolean isLogin(HttpServletRequest request) {
        //获得cookie
        Cookie[] cookies = request.getCookies();

        LoginDAO loginDAO = LoginDAO.getInstance();
        String phoneNumber = "";
        String password = "";
        //判断cookie有内容
        if (cookies != null && cookies.length > 0) {
            //获得账号密码
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(User.PHONENUMBER)) {
                    phoneNumber = cookie.getValue();
                } else if (cookie.getName().equals(User.PASSWORD)) {
                    password = cookie.getValue();
                }
            }
        }

        try {
            //连接数据 ，检索账号密码是否正确
            if (loginDAO.isLoginOK(phoneNumber, password)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("过滤器连接数据库失败");
        }

        return false;
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("LoginFilter  init");
    }

}
