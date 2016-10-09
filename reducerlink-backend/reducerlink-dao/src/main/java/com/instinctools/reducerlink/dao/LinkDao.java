package com.instinctools.reducerlink.dao;

import java.util.List;
import com.instinctools.reducerlink.model.Link;

public interface LinkDao extends BaseDao<Link, Long> {
    public List<Link> getListLinkByIdUser(Long idUser);
    public List<Link> getListLinkByIdUser(Long idUser, String orderBy, boolean orderAsc, int pageNum, int pageSize);
    public long countLinkByUser(Long idUser);
    public List<Link> getListLinkByTag(String tag, String orderBy, boolean orderAsc);
    public List<String> getListUniqueTag();
}
