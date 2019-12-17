package dao.Impl;


import dao.UserDao;
import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;


public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 通过username获取所有信息
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        //定义sql
        User user = null;
        try {
            String sql = "select * from student_user where username = ?";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * 存储user信息
     * @param user
     */
    @Override
    public void save(User user) {
        String sql = "insert into student_user(username,password,tel,email,school,department,major,student_number,name,sex,photo,resume,status,code)"+
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getTel(),
                user.getEmail(),
                user.getSchool(),
                user.getDepartment(),
                user.getMajor(),
                user.getStudent_number(),
                user.getName(),
                user.getSex(),
                user.getPhoto(),
                user.getResume(),
                user.getStatus(),
                user.getCode());
    }

    /**
     * 根据激活码查询用户对象
     * @return
     */
    @Override
    public User findByCode(String code) {
        String sql = "select * from student_user where code = ?";
        User user = null;
        try {
//            System.out.println("sdfs" + code);
            user = template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 修改用户激活状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        String sql = "update student_user set status = 'Y' where uid = ?";
        template.update(sql,user.getUid());
    }

    /**
     * 根据用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "select * from student_user where username = ? and password = ?";
        User user = null;
        try {
            user = template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),username,password);
        } catch (DataAccessException e) {
        }
        return user;
    }

    /**
     * 更换简历
     * @param file
     */
    @Override
    public void updateResume(String username,String file) {
        String sql = "update student_user set resume = ? where username = ?";
        template.update(sql,file,username);
    }
}
