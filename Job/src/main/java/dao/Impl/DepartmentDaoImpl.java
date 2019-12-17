package dao.Impl;

import dao.DepartmentDao;
import domain.Department;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 获取所有的学院列表
     * @return
     */
    @Override
    public  List<Department> findAll() {
        String sql = "select * from academic";
        List<Department> list = null;
        try {
            list = template.query(sql,new BeanPropertyRowMapper<>(Department.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取院系列表
     * @return
     */
    @Override
    public List<Department> findPart() {
        String sql = "select * from academic where type = 1";
        List<Department> list = null;
        try {
            list = template.query(sql,new BeanPropertyRowMapper<>(Department.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

}
