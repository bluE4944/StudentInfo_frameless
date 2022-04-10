package com.xinan.servlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 11940
 */
public class AjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjaxServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //获取参数
        String page = request.getParameter("page");
        String pageUrl = "page/page_"+page+".jsp";
        RequestDispatcher rd = request.getRequestDispatcher(pageUrl);
        rd.forward(request,response);
    }

}
