package com.instinctools.reducerlink.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.instinctools.reducerlink.dao.LinkHistoryDao;
import com.instinctools.reducerlink.model.Link;
import com.instinctools.reducerlink.model.LinkHistory;

@Repository
public class LinkHistoryDaoImpl extends BaseDaoImpl<LinkHistory, Long> implements LinkHistoryDao {
    private static final Map<String, String> MAP_ORDER_BY;

    static {
        MAP_ORDER_BY = new HashMap<String, String>();

        MAP_ORDER_BY.put("tag", "l.tag");
        MAP_ORDER_BY.put("comment", "l.comment");
        MAP_ORDER_BY.put("id", "l.id");
        MAP_ORDER_BY.put("createdAtTimestamp", "createdAtTimestamp");
    }

    public LinkHistoryDaoImpl() {
        super(LinkHistory.class);
    }

    @Override
    public Long getNumberLinkVisits(Long idLink) {
        Criteria criteria = createCriteria()
        .createAlias("link", "l")
        .setProjection(Projections.property("sumClick"))
        .add(Restrictions.eq("l.id", idLink));

        return (Long) criteria.uniqueResult();
    }

    @Override
    public LinkHistory getLinkHistoryByIdLink(Long idLink) {
        Criteria criteria = createCriteria()
        .createAlias("link", "l")
        .add(Restrictions.eq("l.id", idLink));

        return (LinkHistory) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LinkHistory> getListAllLink(String orderBy, boolean orderAsc, int pageNum, int pageSize) {
        Criteria criteria = createCriteria()
        .createAlias("link", "l");

        if (orderAsc) {
            criteria.addOrder(Order.asc(MAP_ORDER_BY.get(orderBy)));
        } else {
            criteria.addOrder(Order.desc(MAP_ORDER_BY.get(orderBy)));
        }

        criteria.setFirstResult((pageNum-1) * pageSize)
        .setMaxResults(pageSize);

        return criteria.list();
    };

    @SuppressWarnings("unchecked")
    @Override
    public List<Link> getListLinkBetweenDate(long startTimestamp, long endTimestamp) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.ge("createdAtTimestamp", startTimestamp));
        criteria.add(Restrictions.lt("createdAtTimestamp", endTimestamp));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LinkHistory> getListLinkByIdUser(Long idUser, String orderBy, boolean orderAsc, int pageNum, int pageSize) {
        Criteria criteria = createCriteria()
        .createAlias("link", "l")
        .createAlias("l.user", "u")
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

    @SuppressWarnings("unchecked")
    @Override
    public List<LinkHistory> getListLinkByTag(String tag, String orderBy, boolean orderAsc) {
        Criteria criteria = createCriteria()
        .createAlias("link", "l")
        .add(Restrictions.eq("l.tag", tag));

        if (orderAsc) {
            criteria.addOrder(Order.asc(MAP_ORDER_BY.get(orderBy)));
        } else {
            criteria.addOrder(Order.desc(MAP_ORDER_BY.get(orderBy)));
        }

        return criteria.list();
    }
}
