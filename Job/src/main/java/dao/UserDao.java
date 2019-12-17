package dao;

import domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 根据用户名查询用户是否存在
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);

    void  updateResume(String username,String file);
}
