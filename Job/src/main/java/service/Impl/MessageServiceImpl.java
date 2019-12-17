package service.Impl;

import dao.Impl.MessageDaoImpl;
import dao.MessasgeDao;
import domain.Message;
import domain.User;
import service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    MessasgeDao dao = new MessageDaoImpl();

    /**
     * 存储留言板信息
     * @param user
     * @param msg
     */
    @Override
    public void save(User user, String msg) {
        dao.save(user,msg);
    }

    /**
     * 获取最后发的10行留言
     * @return
     */
    @Override
    public List<Message> find() {
        return dao.find();
    }
}
