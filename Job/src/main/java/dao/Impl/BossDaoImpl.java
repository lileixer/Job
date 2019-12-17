package dao.Impl;

import dao.BossDao;
import domain.Boss;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class BossDaoImpl implements BossDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 判断username是否存在
     * @param username
     * @return
     */
    @Override
    public Boss findByUsername(String username) {
        String sql = "select * from boss_user where username = ?";

        Boss boss = null;
        try {
            boss = template.queryForObject(sql,new BeanPropertyRowMapper<>(Boss.class),username);
        } catch (DataAccessException e) {
        }
        return boss;
    }

    /**
     * 存储
     * @param boss
     */
    public void save(Boss boss){
        String sql = "insert into boss_user values(null,?,?,?,?,?,?,?,null)";

        template.update(sql,
                boss.getUsername(),
                boss.getPassword(),
                boss.getDepartment(),
                boss.getLeader(),
                boss.getTel(),
                boss.getEmail(),
                boss.getAddress());
    }
    /**
     * 根据用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    @Override
    public Boss findByUsernameAndPassword(String username, String password) {
        String sql = "select * from boss_user where username = ? and password = ?";
        Boss boss = null;
        try {
            boss = template.queryForObject(sql,new BeanPropertyRowMapper<>(Boss.class),username,password);
        } catch (DataAccessException e) {
        }
        return boss;
    }

    /**
     * 修改密码
     * @param psw
     * @return
     */
    @Override
    public void modifyPassword(String username,String psw) {
        String sql = "update boss_user set password = ? where username = ?";
        template.update(sql,psw,username);
    }
}
