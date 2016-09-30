package com.instinctools.reducerlink.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.instinctools.reducerlink.model.User;

public class UserDaoImpl extends BaseDao<User, Long> {
    public UserDaoImpl() {
        super(User.class);
    }

    public Long getIdUserByLoginPassword(String login,String password) {
        return null;
    }

    public User getUserByLoginPassword(String login,String password) {
        return null;
    }

    public User getUserByEmail(String email) {
        return null;
    }

    public List<User> getListUserByLastName(String lastName, String orderBy, boolean orderAsc) {
        return null;
    }

    public List<User> getListUserWithStatus(String status) {
        return null;
    }
}
