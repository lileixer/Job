package service.Impl;

import dao.Impl.JobDaoImpl;
import dao.JobDao;
import domain.Job;
import service.JobService;

public class JobServiceImpl implements JobService {
    JobDao dao = new JobDaoImpl();
    /**
     * 存储招聘信息
     * @param job
     */
    @Override
    public void savejobNews(Job job) {
        dao.save(job);
    }
}
