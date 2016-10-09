package com.instinctools.reducerlink.service.impl;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.instinctools.reducerlink.service.LinkHistoryService;
import com.instinctools.reducerlink.service.base.AuthorizedService;

@Service
@Transactional
public class LinkHistoryServiceImpl extends AuthorizedService implements LinkHistoryService {
}
