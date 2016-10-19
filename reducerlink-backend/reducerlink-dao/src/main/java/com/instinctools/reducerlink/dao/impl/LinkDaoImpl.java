package com.instinctools.reducerlink.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.instinctools.reducerlink.dao.LinkDao;
import com.instinctools.reducerlink.model.Link;

@Repository
public class LinkDaoImpl extends BaseDaoImpl<Link, Long> implements LinkDao {
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

    @Override
    public long countLinkByUser(Long idUser) {
        Criteria criteria = createCriteria()
        .createAlias("user", "u")
        .add(Restrictions.eq("u.id", idUser));

        return ((Integer)criteria.list().size()).longValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getListUniqueTag() {
       Criteria criteria = createCriteria()
       .setProjection(Projections.distinct(Projections.property("tag")));

       return criteria.list();
    }
}
