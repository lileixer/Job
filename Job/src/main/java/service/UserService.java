package service;

import domain.User;

import java.util.List;

public interface UserService {
    boolean register(User user);

    void getFile(User user);

    boolean active(String code);

    User login(User user);

    void updateResume(String username,String file);
}
