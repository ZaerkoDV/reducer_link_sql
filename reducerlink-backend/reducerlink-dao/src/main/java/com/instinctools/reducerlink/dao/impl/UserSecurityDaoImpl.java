package com.instinctools.reducerlink.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.instinctools.reducerlink.dao.UserSecurityDao;
import com.instinctools.reducerlink.model.User;
import com.instinctools.reducerlink.model.UserSecurity;

@Repository
public class UserSecurityDaoImpl extends BaseDaoImpl<UserSecurity, Long> implements UserSecurityDao {
    public UserSecurityDaoImpl() {
        super(UserSecurity.class);
    }

    @Override
    public User getUserByLoginPassword(String login, String password) {
        Criteria criteria = createCriteria()
            .add(Restrictions.eq("login", login))
            .add(Restrictions.eq("password", password))
            .setProjection(Projections.property("user"));

        return (User) criteria.uniqueResult();
    }

    @Override
    public String getEncodedPasswordByLogin(String login) {
        Criteria criteria = createCriteria()
            .add(Restrictions.eq("login", login))
            .setProjection(Projections.property("password"));

        return (String) criteria.uniqueResult();
    }

    @Override
    public String getOldTokenByLogin(String login) {
        Criteria criteria = createCriteria()
            .add(Restrictions.eq("login", login))
            .setProjection(Projections.property("token"));

        return (String) criteria.uniqueResult();
    }

    @Override
    public String getUserRole(Long idUser) {
        Criteria criteria = createCriteria()
            .createAlias("user", "u")
            .add(Restrictions.eq("u.id", idUser))
            .setProjection(Projections.property("role"));

        return (String) criteria.uniqueResult();
    }

    @Override
    public Boolean isLoginExist(String login) {
        Criteria criteria = createCriteria().add(Restrictions.eq("login", login));
        return !criteria.list().isEmpty();
    }

    @Override
    public UserSecurity findUserSecurityByToken(String token) {
        return (UserSecurity) createCriteria().add(Restrictions.eq("token", token)).uniqueResult();
    }
}