package com.instinctools.reducerlink.dao.impl;

import org.springframework.stereotype.Repository;
import com.instinctools.reducerlink.dao.LinkHistoryDao;
import com.instinctools.reducerlink.model.LinkHistory;

@Repository
public class LinkHistoryDaoImpl extends BaseDaoImpl<LinkHistory, Long> implements LinkHistoryDao {
    public LinkHistoryDaoImpl() {
        super(LinkHistory.class);
    }

    @Override
    public Long increaseNumberLinkVisits(Long idLink) {
        return null;
    }

    @Override
    public Long getNumberLinkVisits(Long idLink) {
        return null;
    }
}
