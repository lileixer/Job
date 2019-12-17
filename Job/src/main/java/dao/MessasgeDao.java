package dao;

import domain.Message;
import domain.User;

import java.util.List;

public interface MessasgeDao {
    void save(User user, String msg);
    List<Message> find();
}
