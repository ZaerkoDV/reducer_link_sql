package com.instinctools.reducerlink.dao;

import java.util.List;
import com.instinctools.reducerlink.model.Link;
import com.instinctools.reducerlink.model.LinkHistory;

public interface LinkHistoryDao extends BaseDao<LinkHistory, Long>{
    public Long getNumberLinkVisits(Long idLink);
    public LinkHistory getLinkHistoryByIdLink(Long idLink);
    public List<LinkHistory> getListAllLink(String orderBy, boolean orderAsc, int pageNum, int pageSize);
    public List<Link> getListLinkBetweenDate(long startTimestamp, long endTimestamp);
    public List<LinkHistory> getListLinkByIdUser(Long idUser, String orderBy, boolean orderAsc, int pageNum, int pageSize);
    public List<LinkHistory> getListLinkByTag(String tag, String orderBy, boolean orderAsc);
}
