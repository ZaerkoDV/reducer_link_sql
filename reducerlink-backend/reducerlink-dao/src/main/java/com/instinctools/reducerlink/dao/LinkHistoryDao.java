package com.instinctools.reducerlink.dao;

public interface LinkHistoryDao {
    public Long increaseNumberLinkVisits(Long idLink);
    public Long getNumberLinkVisits(Long idLink);
}
