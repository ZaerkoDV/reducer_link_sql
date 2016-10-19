package com.instinctools.reducerlink.dao;

import java.util.List;
import com.instinctools.reducerlink.model.Link;

public interface LinkDao extends BaseDao<Link, Long> {
    public List<Link> getListLinkByIdUser(Long idUser);
    public long countLinkByUser(Long idUser);
    public List<String> getListUniqueTag();
}
