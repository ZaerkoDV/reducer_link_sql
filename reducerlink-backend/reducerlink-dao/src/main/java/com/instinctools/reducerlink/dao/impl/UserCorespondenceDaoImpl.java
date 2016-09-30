package com.instinctools.reducerlink.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.instinctools.reducerlink.model.UserCorespondence;

public class UserCorespondenceDaoImpl extends BaseDao<UserCorespondence, Long> {
    public UserCorespondenceDaoImpl() {
        super(UserCorespondence.class);
    }

    public List<UserCorespondence> getListUserCorespondencesByIdUser(Long idUser) {
        return null;
    }

    public String getListEmailByIdUser(Long idUser) {
        return null;
    }

    public Boolean isEmailExist(String email) {
        return null;
    }

    public Boolean isSkypeExist(String skype) {
        return null;
    }

    public Boolean isPhoneExist(String phone) {
        return null;
    }

    public Boolean isUserIpAddress(String idUser, String ipAddress) {
        return null;
    }
}
