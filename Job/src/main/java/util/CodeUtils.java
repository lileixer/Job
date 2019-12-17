package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证码判断
 */
public class CodeUtils extends HttpServlet {
    public String check_msg(HttpServletRequest request, HttpServletResponse response,String check_1,String check_2) throws ServletException, IOException {
        //验证校验
        String check = request.getParameter(check_1);
        //从session中获取验证码
        HttpSession session = request.getSession();
        String check_code = (String) session.getAttribute(check_2);
        //移除验证码
        session.removeAttribute(check_2);
        System.out.println(check_code+"----"+check);
        //比较
        if (check_code == null || !check_code.equalsIgnoreCase(check)) {
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
            //将info序列化
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);

            return json;
        }else return null;
    }
}
