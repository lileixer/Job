package web.servlet;

import dao.BossDao;
import dao.Impl.BossDaoImpl;
import domain.Boss;
import domain.ResultInfo;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.BossService;
import service.Impl.BossServiceImpl;
import util.CodeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/boss/*")
public class BossServlet extends BaseServlet {
    BossService service = new BossServiceImpl();

    /**
     * 注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求
        Map<String, String[]> parameterMap = request.getParameterMap();
        //封装对象
        Boss boss = new Boss();

        try {
            BeanUtils.populate(boss,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        boolean flag = service.register(boss);
        ResultInfo info = new ResultInfo();
        if(flag){
            //能够注册
            info.setFlag(true);
            info.setMsg("注册成功，等待审核");
        }else {
            //不能注册
            info.setFlag(false);
            info.setMsg("用户已存在");
        }
        //将info序列化
        writeValue(info,response);
    }

    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String Check_Resut = new CodeUtils().check_msg(request,response,"check","CHECKCODE_SERVER");
        if(Check_Resut != null){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script>alert('验证码错误!')</script>");
            response.getWriter().write("<script>window.history.back(-2);</script>");
            return;
        }
        response.setContentType("text/html;charset=utf-8;");
        //获取数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        request.getSession().invalidate();
        //封装对象
        Boss boss = new Boss();
        try {
            BeanUtils.populate(boss,parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Boss b = service.login(boss);

        ResultInfo info = new ResultInfo();
        //判断是否null
        if (b == null){
            response.getWriter().write("<script>alert('用户名或密码错误!')</script>");
            response.getWriter().write("<script>window.history.back(-1);</script>");
        }
        //判断是否激活
        if (b != null && b.getCheck() == null){
            response.getWriter().write("<script>alert('管理员尚未审核，请等待审核!')</script>");
            response.getWriter().write("<script>window.history.back(-1);</script>");
        }
        if (b != null && b.getCheck() != null){
            request.getSession().setAttribute("boss",b);//登录成功标记
            request.getSession().setAttribute("identity","boss");
            response.getWriter().write("<script>alert('登陆成功')</script>");
            response.getWriter().write("<script>window.history.back(-1);</script>");
        }
    }
    /**
     * 获取姓名
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findUserName(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //从session中获取数据
        Object user = request.getSession().getAttribute("boss");
        writeValue(user,response);
    }

    /**
     * 修改密码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void modifyPassword(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String old = request.getParameter("oldPassword");
        String new_1 = request.getParameter("newPassword-1");
        String new_2 = request.getParameter("newPassword-2");

        response.setContentType("text/html;charset=utf-8");
        if (!new_1.equals(new_2)){
            response.getWriter().write("<script>alert('两次新密码输入不一致');</script>");
            response.getWriter().write("<script>window.history.back(-1);</script>");
        }else {
            Boss boss = (Boss) request.getSession().getAttribute("boss");
            BossDao dao = new BossDaoImpl();
            Boss b = dao.findByUsernameAndPassword(boss.getUsername(),old);
            if (b == null){
                response.getWriter().write("<script>alert('密码输入错误')</script>");
                response.getWriter().write("<script>window.history.back(-1);</script>");
            }else {
                service.modifyPassword(boss.getUsername(),new_1);
                response.getWriter().write("<script>alert('修改成功')</script>");
                response.getWriter().write("<script>window.history.back(-1);</script>");
            }
        }
    }

}
