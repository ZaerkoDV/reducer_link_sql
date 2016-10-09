package com.instinctools.reducerlink.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.instinctools.reducerlink.dao.LinkDao;
import com.instinctools.reducerlink.model.Link;

@Repository
public class LinkDaoImpl extends BaseDaoImpl<Link, Long> implements LinkDao {
    private static final Map<String, String> MAP_ORDER_BY;

    static {
        MAP_ORDER_BY = new HashMap<String, String>();

        MAP_ORDER_BY.put("tag", "tag");
        MAP_ORDER_BY.put("comment", "comment");
        MAP_ORDER_BY.put("id", "id");
    }

    public LinkDaoImpl() {
        super(Link.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Link> getListLinkByIdUser(Long idUser) {
        Criteria criteria = createCriteria()
        .createAlias("user", "u")
        .add(Restrictions.eq("u.id", idUser))
        .setMaxResults(20)
        .setFirstResult(0);

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Link> getListLinkByIdUser(Long idUser, String orderBy, boolean orderAsc, int pageNum, int pageSize) {
        Criteria criteria = createCriteria()
        .createAlias("user", "u")
        .add(Restrictions.eq("u.id", idUser));

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
    public long countLinkByUser(Long idUser) {
        Criteria criteria = createCriteria()
        .createAlias("user", "u")
        .add(Restrictions.eq("u.id", idUser));

        return ((Integer)criteria.list().size()).longValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Link> getListLinkByTag(String tag, String orderBy, boolean orderAsc) {
        Criteria criteria = createCriteria()
        .add(Restrictions.eq("tag", tag));

        if (orderAsc) {
            criteria.addOrder(Order.asc(MAP_ORDER_BY.get(orderBy)));
        } else {
            criteria.addOrder(Order.desc(MAP_ORDER_BY.get(orderBy)));
        }

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getListUniqueTag() {
       Criteria criteria = createCriteria()
       .setProjection(Projections.distinct(Projections.property("tag")));

       return criteria.list();
    }
}
