package com.instinctools.reducerlink.dao;

import com.instinctools.reducerlink.model.LinkHistory;

public class LinkHistoryDao extends BaseDao<LinkHistory, Long> {
    public LinkHistoryDao() {
        super(LinkHistory.class);
    }
}
