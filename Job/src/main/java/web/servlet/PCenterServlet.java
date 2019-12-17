package web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PCenterServlet")
public class PCenterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8;");
        String identity = (String) request.getSession().getAttribute("identity");
        if (identity == null){
            response.getWriter().write("<script>alert('请先登录');</script>");
            response.getWriter().write("<script>window.history.go(-1);</script>");
        } else if ("student".equals(identity)){
            response.sendRedirect("studentCenter.html");
//            request.getRequestDispatcher("").forward(request,response);
        }else {
            response.sendRedirect("bossCenter.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
