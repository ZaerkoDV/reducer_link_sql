package com.instinctools.reducerlink.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.instinctools.reducerlink.dao.UserCorespondenceDao;
import com.instinctools.reducerlink.model.User;
import com.instinctools.reducerlink.model.UserCorespondence;

@Repository
public class UserCorespondenceDaoImpl extends BaseDaoImpl<UserCorespondence, Long> implements UserCorespondenceDao {
    public UserCorespondenceDaoImpl() {
        super(UserCorespondence.class);
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserCorespondence> getListUserCorespondencesByIdUser(Long idUser) {
        Criteria criteria = createCriteria()
            .createAlias("user", "u")
            .add(Restrictions.eq("u.id", idUser))
            .addOrder(Order.asc("u.id"));

        return criteria.list();
    }

    @Override
    public String getListEmailByIdUser(Long idUser) {
        return null;
    }

    @Override
    public Boolean isEmailExist(String email) {
        return null;
    }

    @Override
    public Boolean isSkypeExist(String skype) {
        return null;
    }

    @Override
    public Boolean isPhoneExist(String phone) {
        return null;
    }

    @Override
    public Boolean isUserIpAddress(String idUser, String ipAddress) {
        return null;
    }
}