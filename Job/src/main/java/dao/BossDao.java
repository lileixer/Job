package dao;

import domain.Boss;

public interface BossDao {
    Boss findByUsername(String username);
    void save(Boss boss);
    Boss findByUsernameAndPassword(String username, String password);
    void modifyPassword(String username,String psw);
}
