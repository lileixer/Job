package dao.Impl;

import dao.JobDao;
import domain.Job;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class JobDaoImpl implements JobDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 存储招聘信息
     * @param job
     */
    @Override
    public void save(Job job) {
        String sql = "insert into job_message values(null,?,?,?,?,?,?,?,?)";
        template.update(sql,
                job.getBossid(),
                job.getDepartment(),
                job.getTitle(),
                job.getContent(),
                job.getSalary(),
                job.getStarttime(),
                job.getEndtime(),1);
    }
}
