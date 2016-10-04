package com.instinctools.reducerlink.service.impl;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.instinctools.reducerlink.service.UserPhotoService;
import com.instinctools.reducerlink.service.base.AuthorizedService;

@Service
@Transactional
public class UserPhotoServiceImpl extends AuthorizedService implements UserPhotoService {
}
