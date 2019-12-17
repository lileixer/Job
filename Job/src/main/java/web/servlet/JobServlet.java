package web.servlet;

import domain.Boss;
import domain.Job;
import org.apache.commons.beanutils.BeanUtils;
import service.Impl.JobServiceImpl;
import service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.util.Map;

@WebServlet("/Job/*")
public class JobServlet extends BaseServlet {
    JobService service = new JobServiceImpl();
    /**
     * 发布招聘信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void releaseJobNews(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8;");
        //从session中获取数据
        Boss boss = (Boss) request.getSession().getAttribute("boss");
        //获取表单所有数据
        Job job = new Job();
        Map<String,String[]> map = request.getParameterMap();

        try {
            BeanUtils.populate(job,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //获取发布者id
        job.setBossid(boss.getId());
        //获取发布部门
        job.setDepartment(boss.getDepartment());
        service.savejobNews(job);
        response.getWriter().write("<script>alert('发布成功')</script>");
        response.getWriter().write("<script>window.history.back(-1);</script>");
    }
}
