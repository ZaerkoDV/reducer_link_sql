package com.instinctools.reducerlink.dao;

import java.util.List;
import com.instinctools.reducerlink.model.Link;
import com.instinctools.reducerlink.model.LinkHistory;

public interface LinkHistoryDao extends BaseDao<LinkHistory, Long>{
    public Long getNumberLinkVisits(Long idLink);
    public LinkHistory getLinkHistoryByIdLink(Long idLink);
    public List<Link> getListLinkBetweenDate(long startTimestamp, long endTimestamp);
}
