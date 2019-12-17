package web.servlet;

import domain.Department;
import service.DepartmentService;
import service.Impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/department/*")
public class DepartmentServlet extends BaseServlet {
    DepartmentService service = new DepartmentServiceImpl();

    /**
     * 获取院系
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getPartDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> list = service.findPart();
        writeValue(list,response);
    }

    /**
     * 获取所有院系和店名
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getAllDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> list = service.findAll();
        writeValue(list,response);
    }
}
