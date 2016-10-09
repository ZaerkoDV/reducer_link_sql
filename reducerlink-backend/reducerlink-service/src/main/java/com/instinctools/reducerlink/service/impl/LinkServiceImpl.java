package com.instinctools.reducerlink.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instinctools.reducerlink.dao.LinkDao;
import com.instinctools.reducerlink.dao.LinkHistoryDao;
import com.instinctools.reducerlink.dao.UserDao;
import com.instinctools.reducerlink.model.Link;
import com.instinctools.reducerlink.model.LinkHistory;
import com.instinctools.reducerlink.model.User;
import com.instinctools.reducerlink.service.LinkService;
import com.instinctools.reducerlink.service.base.AuthorizedService;
import com.instinctools.reducerlink.service.support.PagedResult;
import com.instinctools.reducerlink.service.support.ValidationResult;

@Service
@Transactional
public class LinkServiceImpl extends AuthorizedService implements LinkService {
    private static final String TAG_REQUIRED= "tagRequired";
    private static final String COMMENT_REQUIRED= "commentRequired";
    private static final String SHORT_URL_REQUIRED= "shortUrlRequired";
    private static final String FULL_URL_REQUIRED= "fullUrlRequired";

    @Autowired
    private UserDao userDao;

    @Autowired
    private LinkDao linkDao;

    @Autowired
    private LinkHistoryDao linkHistoryDao;

    @Override
    public Link getLinkById(Long idLink) {
        return ensureFound(linkDao.findOne(idLink));
    }

    @Override
    public ValidationResult<Link> createLink(Link inputLink, long currentTimestamp) {
        ValidationResult<Link> result = validate(inputLink, new ValidationResult<Link>());

        if (result.isFaulted()) {
            return result;
        }

        User user = ensureFound(userDao.findOne(inputLink.getUser().getId()));
        inputLink.setUser(user);
        inputLink.setShortUrl(inputLink.getFullUrl());
        linkDao.save(inputLink);

        LinkHistory linkHistory = new LinkHistory()
        .setSumClick((long)0)
        .setLink(inputLink)
        .setCreatedAtTimestamp(currentTimestamp);
        linkHistoryDao.save(linkHistory);

        return result.setResult(inputLink);
    }

    @Override
    public ValidationResult<Link> updateLink(Link inputLink, long currentTimestamp) {
        ValidationResult<Link> result = validate(inputLink, new ValidationResult<Link>());

        if (result.isFaulted()) {
            return result;
        }

        Link updatedLink = ensureFound(linkDao.findOne(inputLink.getId()));
        updatedLink.setTag(inputLink.getTag())
        .setComment(inputLink.getComment())
        .setFullUrl(inputLink.getFullUrl())
        .setShortUrl(inputLink.getFullUrl());
        linkDao.save(updatedLink);

        LinkHistory updatedLinkHistory = ensureFound(linkHistoryDao.getLinkHistoryByIdLink(updatedLink.getId()));
        updatedLinkHistory.setCreatedAtTimestamp(currentTimestamp);
        linkHistoryDao.save(updatedLinkHistory);

        return result.setResult(inputLink);
    }

    @Override
    public Boolean deleteLink(Long idLink) {
        Link link = ensureFound(linkDao.findOne(idLink));
        linkDao.delete(link);

        return true;
    }

    @Override
    public PagedResult<Link> getListLinkByIdUser(Long idUser, String orderBy, boolean orderAsc, int pageNum, int pageSize) {
        return new PagedResult<Link>(
            linkDao.getListLinkByIdUser(idUser, orderBy, orderAsc, pageNum, pageSize),
            computePages(linkDao.countLinkByUser(idUser), pageSize)
        );
    }

    @Override
    public List<Link> getListLinkByTag(String tag, String orderBy, boolean orderAsc) {
        return linkDao.getListLinkByTag(tag, orderBy, orderAsc);
    }

    @Override
    public List<Link> getListLinkBetweenDate(long startTimestamp, long endTimestamp) {
        return linkHistoryDao.getListLinkBetweenDate(startTimestamp, endTimestamp);
    }

    @Override
    public List<String> getListUniqueTag() {
        return linkDao.getListUniqueTag();
    }

    public <T> ValidationResult<T> validate(Link inputLink, ValidationResult<T> result) {
        if (inputLink.getId() == null) {
            ensureFound(userDao.findOne(inputLink.getUser().getId()));
        }

        if (inputLink.getId() != null) {
            ensureFound(linkDao.findOne(inputLink.getId()));
        }

        if (inputLink.getTag().isEmpty()) {
            result.addError(TAG_REQUIRED);
        }

        if (inputLink.getComment().isEmpty()) {
            result.addError(COMMENT_REQUIRED);
        }

        if (inputLink.getFullUrl().isEmpty()) {
           result.addError(FULL_URL_REQUIRED);
        }

        return result;
    }

    public Long increaseNumberLinkVisits(Long idLink) {
        LinkHistory linkHistory = ensureFound(linkHistoryDao.getLinkHistoryByIdLink(idLink));
        linkHistory.setSumClick(linkHistory.getSumClick()+1);
        linkHistoryDao.save(linkHistory);

        return linkHistory.getSumClick();
    }
}