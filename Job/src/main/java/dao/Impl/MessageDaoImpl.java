package dao.Impl;

import dao.MessasgeDao;
import domain.Message;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageDaoImpl implements MessasgeDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 存储留言板信息
     * @param user
     * @param msg
     */
    @Override
    public void save(User user, String msg) {
        String sql = "insert into message_board values(null,?,?,?)";
        //获取当前时间
        Date date = new Date();
        //时间格式化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = format.format(date);
        template.update(sql,user.getName(),msg,time);
    }

    /**
     * 获取最后发的10行留言
     * @return
     */
    @Override
    public List<Message> find() {
        String sql = "SELECT * FROM( SELECT * FROM message_board ORDER BY id DESC LIMIT 0,10 ) as s";
        List<Message> list = template.query(sql,new BeanPropertyRowMapper<>(Message.class));
        return list;
    }
}
