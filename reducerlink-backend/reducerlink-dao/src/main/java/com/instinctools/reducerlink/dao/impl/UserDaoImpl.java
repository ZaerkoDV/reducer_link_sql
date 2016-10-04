package com.instinctools.reducerlink.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.instinctools.reducerlink.dao.UserDao;
import com.instinctools.reducerlink.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {
    private static final Map<String, String> MAP_ORDER_BY;
    private static final Map<String, String> MAP_STATUS;

    static {
        MAP_ORDER_BY = new HashMap<String, String>();

        MAP_ORDER_BY.put("status", "status");
        MAP_ORDER_BY.put("firstName", "firstName");
        MAP_ORDER_BY.put("lastName", "lastName");
        MAP_ORDER_BY.put("middleName", "middleName");
        MAP_ORDER_BY.put("birth", "birth");
    }

    static {
        MAP_STATUS = new HashMap<String, String>();

        MAP_STATUS.put("active", "active");
        MAP_STATUS.put("blocked", "blocked");
    }

    public UserDaoImpl() {
        super(User.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getListUserByLastName(User inputUser, int pageNum, int pageSize) {
        Criteria criteria = createCriteria()
            .add(Restrictions.eq("lastName", inputUser.getLastName()))
            .setFirstResult((pageNum-1) * pageSize)
            .setMaxResults(pageSize);

        return criteria.list();
    }

    @Override
    public long countTotalUser() {
        return ((Integer)createCriteria().list().size()).longValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getListUserWithStatus(User inputUser, String orderBy, boolean orderAsc, int pageNum, int pageSize) {
        Criteria criteria = createCriteria()
        .add(Restrictions.eq("status", MAP_STATUS.get(inputUser.getStatus())));

        if (orderAsc) {
            criteria.addOrder(Order.asc(MAP_ORDER_BY.get(orderBy)));
        } else {
            criteria.addOrder(Order.desc(MAP_ORDER_BY.get(orderBy)));
        }

        criteria.setFirstResult((pageNum-1) * pageSize)
        .setMaxResults(pageSize);

        return criteria.list();
    }

    @Override
    public long countUserWithStatus(User inputUser) {
        Criteria criteria = createCriteria().add(Restrictions.eq("status", MAP_STATUS.get(inputUser.getStatus())));
        return ((Integer)criteria.list().size()).longValue();
    }
}
