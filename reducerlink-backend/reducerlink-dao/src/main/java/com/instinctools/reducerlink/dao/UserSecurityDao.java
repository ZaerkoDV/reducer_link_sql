package com.instinctools.reducerlink.dao;

import com.instinctools.reducerlink.model.UserSecurity;

public class UserSecurityDao extends BaseDao<UserSecurity, Long> {
    public UserSecurityDao() {
        super(UserSecurity.class);
    }
}
