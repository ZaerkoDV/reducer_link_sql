package com.instinctools.reducerlink.dao;

import java.util.List;
import com.instinctools.reducerlink.model.Link;

public interface LinkDao {
    public List<Link> getListLinkByIdUser(Long idUser);
    public List<Link> getListLinkByUser(Long idUser, String orderBy, boolean orderAsc);
    public List<Link> getListLinkByTag(String tag, String orderBy, boolean orderAsc);
    public List<Link> getListLinkBetweenDate(long startTimestamp, long endTimestamp);
    public List<String> getListUniqueTag();
}
