package com.instinctools.reducerlink.dao;

import org.springframework.stereotype.Component;
import com.instinctools.reducerlink.model.User;

@Component
public class UserDao extends BaseDao<User, Long> {
    public UserDao() {
        super(User.class);
    }

    public void delete(){
        //createCriteria().
    }
}
