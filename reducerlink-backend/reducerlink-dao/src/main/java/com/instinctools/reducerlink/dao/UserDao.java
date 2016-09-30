package com.instinctools.reducerlink.dao;

import java.util.List;
import com.instinctools.reducerlink.model.User;

public interface UserDao {
    public Long getIdUserByLoginPassword(String login,String password);
    public User getUserByLoginPassword(String login,String password);
    public User getUserByEmail(String email);
    public List<User> getListUserByLastName(String lastName, String orderBy, boolean orderAsc);
    public List<User> getListUserWithStatus(String status);
}
