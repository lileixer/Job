package service;

import domain.Boss;

public interface BossService {
    boolean register(Boss boss);
    Boss login(Boss boss);
    void modifyPassword(String username,String psw);
}
