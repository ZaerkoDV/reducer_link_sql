package com.instinctools.reducerlink.service;

import java.util.List;
import com.instinctools.reducerlink.model.Link;
import com.instinctools.reducerlink.model.LinkHistory;
import com.instinctools.reducerlink.service.support.PagedResult;
import com.instinctools.reducerlink.service.support.ValidationResult;

public interface LinkService {
    public Link getLinkById(Long idLink);
    public ValidationResult<Link> createLink(Link inputLink, long currentTimestamp);
    public ValidationResult<Link> updateLink(Link inputLink, long currentTimestamp);
    public Boolean deleteLink(Long idLink);
    public PagedResult<LinkHistory> getListLinkByIdUser(Long idUser, String orderBy, boolean orderAsc, int pageNum, int pageSize);
    public List<LinkHistory> getListLinkByTag(String tag, String orderBy, boolean orderAsc);
    public PagedResult<LinkHistory> getListAllLink(String orderBy, boolean orderAsc, int pageNum, int pageSize);
    public List<Link> getListLinkBetweenDate(long startTimestamp, long endTimestamp);
    public List<String> getListUniqueTag();
    public Long increaseNumberLinkVisits(Long idLink);
}
