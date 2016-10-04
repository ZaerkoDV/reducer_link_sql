package com.instinctools.reducerlink.dao;

import com.instinctools.reducerlink.model.User;
import com.instinctools.reducerlink.model.UserSecurity;

public interface UserSecurityDao extends BaseDao<UserSecurity, Long> {
    public User getUserByLoginPassword(String login, String password);
    public String getEncodedPasswordByLogin(String login);
    public String getOldTokenByLogin(String login);
    public String getUserRole(Long idUser);
    public Boolean isLoginExist(String login);
    public UserSecurity findUserSecurityByToken(String token);
}
