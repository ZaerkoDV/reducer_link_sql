package com.instinctools.reducerlink.dao;

import com.instinctools.reducerlink.model.Link;

public class LinkDao extends BaseDao<Link, Long> {
    public LinkDao() {
        super(Link.class);
    }
}
