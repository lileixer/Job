package service.Impl;

import dao.Impl.UserDaoImpl;
import dao.UserDao;
import domain.User;
import service.UserService;
import util.MailUtils;
import util.UuidUtil;

public class UserServiceImpl implements UserService {
     User fuser = new User();
    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        //根据用户查询对象
        User u = userDao.findByUsername(user.getUsername());
        if (u != null){ return false; }//已存在用户
        //设置激活码
        user.setCode(UuidUtil.getUuid());
        //设置激活状态
        user.setStatus("N");
        //保存文件信息
        user.setPhoto(fuser.getPhoto());
        user.setResume(fuser.getResume());
        //保存用户信息
        userDao.save(user);
        //激活码邮件发送
        String content = "<a href='http://localhost:8080/Job/user/activeUser?code="+user.getCode()+"'>点击激活【民大兼职】</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    @Override
    public void getFile(User user) {
        fuser.setPhoto(user.getPhoto());
        fuser.setResume(user.getResume());
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //根据激活码查询用户对象
        User user = userDao.findByCode(code);
        //调用dao的修改激活状态的对象
        if (user != null){
            userDao.updateStatus(user);
            return true;
        }
        return false;
    }

    /**
     * 登录验证
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void updateResume(String username,String file) {
        userDao.updateResume(username,file);
    }

}
