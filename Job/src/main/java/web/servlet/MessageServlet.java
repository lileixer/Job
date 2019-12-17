package web.servlet;

import domain.Message;
import domain.User;
import service.Impl.MessageServiceImpl;
import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/message/*")
public class MessageServlet extends BaseServlet {
    MessageService service = new MessageServiceImpl();

    /**
     * 存储留言板信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void saveMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String msg = request.getParameter("msg");
        byte[] bytes = msg.getBytes("ISO8859-1");
        //再使用UTF-8进行编码
        msg = new String(bytes,"UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        service.save(user,msg);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<script>alert('留言成功');</script>");
    }

    /**
     * 获取最近留言
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Message> list = service.find();
        writeValue(list,response);
    }
}
