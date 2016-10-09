package com.instinctools.reducerlink.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.instinctools.reducerlink.dao.LinkHistoryDao;
import com.instinctools.reducerlink.model.Link;
import com.instinctools.reducerlink.model.LinkHistory;

@Repository
public class LinkHistoryDaoImpl extends BaseDaoImpl<LinkHistory, Long> implements LinkHistoryDao {
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
    public List<Link> getListLinkBetweenDate(long startTimestamp, long endTimestamp) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.ge("createdAtTimestamp", startTimestamp));
        criteria.add(Restrictions.lt("createdAtTimestamp", endTimestamp));

        return criteria.list();
    }
}
