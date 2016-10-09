package com.instinctools.reducerlink.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getListEmailByIdUser(Long idUser) {
        Criteria criteria = createCriteria()
        .createAlias("user", "u")
        .setProjection(Projections.property("email"))
        .add(Restrictions.eq("u.id", idUser));

        return criteria.list();
    }

    @Override
    public Boolean isEmailExist(String email) {
        Criteria criteria = createCriteria().add(Restrictions.eq("email", email));
        return !criteria.list().isEmpty();
    }

    @Override
    public Boolean isSkypeExist(String skype) {
        Criteria criteria = createCriteria().add(Restrictions.eq("skype", skype));
        return !criteria.list().isEmpty();
    }

    @Override
    public Boolean isPhoneExist(String phone) {
        Criteria criteria = createCriteria().add(Restrictions.eq("phone", phone));
        return !criteria.list().isEmpty();
    }

    @Override
    public Boolean isUserIpAddress(String idUser, String ipAddress) {
        Criteria criteria = createCriteria()
            .createAlias("user", "u")
            .add(Restrictions.eq("u.id", idUser))
            .add(Restrictions.eq("ipAddress", ipAddress));

        return criteria.list().isEmpty();
    }
}