package service;

import domain.Message;
import domain.User;

import java.util.List;

public interface MessageService {
    void save(User user,String msg);

    List<Message> find();
}
