package com.instinctools.reducerlink.dao;

import java.util.List;
import com.instinctools.reducerlink.model.User;
import com.instinctools.reducerlink.model.UserCorespondence;

public interface UserCorespondenceDao extends BaseDao<UserCorespondence, Long> {
    public User getUserByEmail(String email);
    public List<UserCorespondence> getListUserCorespondencesByIdUser(Long idUser);
    public List<String> getListEmailByIdUser(Long idUser);
    public Boolean isEmailExist(String email);
    public Boolean isSkypeExist(String skype);
    public Boolean isPhoneExist(String phone);
    public Boolean isUserIpAddress(String idUser, String ipAddress);
}
