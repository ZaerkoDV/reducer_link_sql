package com.instinctools.reducerlink.dao.impl;

import com.instinctools.reducerlink.model.LinkHistory;

public class LinkHistoryDaoImpl extends BaseDao<LinkHistory, Long> {
    public LinkHistoryDaoImpl() {
        super(LinkHistory.class);
    }

    public Long increaseNumberLinkVisits(Long idLink) {
        return null;
    }

    public Long getNumberLinkVisits(Long idLink) {
        return null;
    }
}
