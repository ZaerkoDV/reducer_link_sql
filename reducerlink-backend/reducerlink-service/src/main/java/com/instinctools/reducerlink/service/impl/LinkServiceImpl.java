package com.instinctools.reducerlink.service.impl;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.instinctools.reducerlink.service.LinkService;
import com.instinctools.reducerlink.service.base.AuthorizedService;

@Service
@Transactional
public class LinkServiceImpl extends AuthorizedService implements LinkService {

}
