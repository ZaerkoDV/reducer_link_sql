package com.instinctools.reducerlink.dao.impl;

import com.instinctools.reducerlink.model.UserSecurity;

public class UserSecurityDaoImpl extends BaseDao<UserSecurity, Long> {
    public UserSecurityDaoImpl() {
        super(UserSecurity.class);
    }

    public Boolean login(String login, String password) {
        return null;
    }

    public Boolean logout(String login, String password) {
        return null;
    }

    public String getUserRole(Long idUser) {
        return null;
    }

    public Boolean isLoginPasswordExist(String login, String password) {
        return null;
    }

    public UserSecurity findByAuthorizationToken(String authorizationToken) {
        return null;
    }
}
