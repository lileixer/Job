package web.servlet;

import domain.ResultInfo;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.Impl.UserServiceImpl;
import service.UserService;
import util.CodeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    //调用service完成注册
    private UserService userService = new UserServiceImpl();
    /**
     * 注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
//        System.out.println(parameterMap.toString());
        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        boolean flag = userService.register(user);
        ResultInfo info = new ResultInfo();
        //响应结果
        if (flag) {
            //注册成功
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("用户已存在!");
        }
        //将info序列化
        writeValue(info, response);

    }

    /**
     * 存储文件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void saveFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> files = new ArrayList<>();
        Instant instant = Instant.now();
        //获取数据
        try {
            // 配置上传参数
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);
            int n = 0;
            // 迭代表单数据
            for (FileItem item : formItems) {
                // 处理不在表单中的字段
                if (!item.isFormField()) {
                    String fileName = item.getName();
                    String end = fileName.substring(fileName.lastIndexOf("."));
                    //定义上传文件的存放路径
                    String path = request.getServletContext().getRealPath("/uploadFile");
                    //定义上传文件的完整路径
                    String file = instant.toEpochMilli()+n+end;
                    String filePath = String.format("%s\\%s",path,file);
                    File storeFile = new File(filePath);
                    // 保存文件到硬盘
                    n++;
                    files.add(file);
                    item.write(storeFile);
                }
            }
        } catch (Exception ex) {

        }
        User user = new User();
        user.setPhoto(files.get(0));
        user.setResume(files.get(1));
        userService.getFile(user);
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
//        //清空数据
//        request.getSession().invalidate();
        //获取数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        User u = userService.login(user);
        //判断是否null
        if (u == null){
            response.getWriter().write("<script>alert('用户名或密码错误')</script>");
            response.getWriter().write("<script>window.history.back(-1);</script>");
        }else if (!"Y".equals(u.getStatus())){
            response.getWriter().write("<script>alert('您尚未激活，请激活')</script>");
            response.getWriter().write("<script>window.history.back(-1);</script>");
        }else if (u.getCheck() == null){
            response.getWriter().write("<script>alert('您尚未通过审核,请等待管理审核!')</script>");
            response.getWriter().write("<script>window.history.back(-1);</script>");
        }else{
            request.getSession().setAttribute("user",u);//登录成功标记
            request.getSession().setAttribute("identity","student");
            response.getWriter().write("<script>alert('登录成功!')</script>");
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
        Object user = request.getSession().getAttribute("user");
        writeValue(user,response);
    }

    /**
     * 退出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/index.html");
    }
    /**
     * 激活邮件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void activeUser(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //获取激活码
        String code = request.getParameter("code");
        if (code != null){

            boolean flag = userService.active(code);

            //判断标记
            String msg = null;
            if (flag){
                //激活成功
                msg = "激活成功，请等待管理员审核,<a href='/Job/index.html'>首页</a>";
            }else {
                //激活失败
                msg = "激活失败，请联系管理员";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

    /**
     * 获取所有信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAStudent(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //获取
        User user = (User) request.getSession().getAttribute("user");
        writeValue(user,response);
    }
    /**
     * 存储文件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Instant instant = Instant.now();
        String file = null;
        //获取数据
        try {
            // 配置上传参数
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);
            // 迭代表单数据
            for (FileItem item : formItems) {
                // 处理不在表单中的字段
                if (!item.isFormField()) {
                    String fileName = item.getName();
                    String end = fileName.substring(fileName.lastIndexOf("."));
                    //定义上传文件的存放路径
                    String path = request.getServletContext().getRealPath("/uploadFile");
                    //定义上传文件的完整路径
                    file = instant.toEpochMilli()+end;
                    String filePath = String.format("%s\\%s",path,file);
                    File storeFile = new File(filePath);
                    item.write(storeFile);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        User user = (User) request.getSession().getAttribute("user");
        String username = user.getUsername();
        userService.updateResume(username,file);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<script>alert('更新成功,重新登陆可见');</script>");
        response.getWriter().write("<script>window.history.back(-1);</script>");
    }
}
