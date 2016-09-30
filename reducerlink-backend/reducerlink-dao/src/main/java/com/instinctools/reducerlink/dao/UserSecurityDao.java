package com.instinctools.reducerlink.dao;

import com.instinctools.reducerlink.model.UserSecurity;

public interface UserSecurityDao {
    public Boolean login(String login, String password);
    public Boolean logout(String login, String password);
    public String getUserRole(Long idUser);
    public Boolean isLoginPasswordExist(String login, String password);
    public UserSecurity findByAuthorizationToken(String authorizationToken);
}
