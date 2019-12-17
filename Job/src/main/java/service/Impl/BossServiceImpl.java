package service.Impl;

import dao.BossDao;
import dao.Impl.BossDaoImpl;
import domain.Boss;
import service.BossService;

public class BossServiceImpl implements BossService {
    BossDao bossDao = new BossDaoImpl();

    /**
     * 注册
     * @param boss
     * @return
     */
    @Override
    public boolean register(Boss boss) {
        Boss b = bossDao.findByUsername(boss.getUsername());
        if (b != null)return false;
        bossDao.save(boss);
        return true;
    }

    /**
     * 登录
     * @param boss
     * @return
     */
    @Override
    public Boss login(Boss boss) {
        return bossDao.findByUsernameAndPassword(boss.getUsername(),boss.getPassword());
    }

    /**
     * 修改密码
     * @param username
     * @param psw
     */
    @Override
    public void modifyPassword(String username, String psw) {
        bossDao.modifyPassword(username,psw);
    }
}
