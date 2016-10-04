package com.instinctools.reducerlink.service.base;

import java.security.SecureRandom;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.instinctools.reducerlink.dao.UserSecurityDao;
import com.instinctools.reducerlink.model.UserSecurity;
import com.instinctools.reducerlink.service.base.BaseService;
import com.instinctools.reducerlink.service.support.AppException;

public class AuthorizedService extends BaseService {
    public static final String ERROR_NOT_AUTHORIZED = "notAuthorized";

    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private UserSecurityDao userSecurityDao;

    public UserSecurity ensureUserSecurity(String token) {
        if (token.isEmpty()) {
            throw new AppException(ERROR_NOT_AUTHORIZED);
        }

        UserSecurity userSecurity = userSecurityDao.findUserSecurityByToken(token);

        if (userSecurity.getUser() == null) {
            throw new AppException(ERROR_NOT_AUTHORIZED);
        }

        return userSecurity;
    }

    protected String generateToken() {
        return RandomStringUtils.random(128, 0, 0, true, true, null, secureRandom);
    }
}
