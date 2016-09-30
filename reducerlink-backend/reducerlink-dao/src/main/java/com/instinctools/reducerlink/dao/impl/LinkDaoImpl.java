package com.instinctools.reducerlink.dao.impl;

import java.util.List;
import com.instinctools.reducerlink.model.Link;

public class LinkDaoImpl extends BaseDao<Link, Long> {
    public LinkDaoImpl() {
        super(Link.class);
    }

    public List<Link> getListLinkByUser(Long idUser, String orderBy, boolean orderAsc) {
        return null;
    }

    public List<Link> getListLinkByTag(String tag, String orderBy, boolean orderAsc) {
        return null;
    }

    public List<Link> getListLinkBetweenDate(long startTimestamp, long endTimestamp) {
        return null;
    }

    public List<String> getListUniqueTag() {
       return null;
    }
}
