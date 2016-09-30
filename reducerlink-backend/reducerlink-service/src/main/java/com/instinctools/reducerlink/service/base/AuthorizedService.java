package com.instinctools.reducerlink.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import com.instinctools.reducerlink.dao.UserSecurityDao;
import com.instinctools.reducerlink.model.User;
import com.instinctools.reducerlink.model.UserSecurity;
import com.instinctools.reducerlink.service.base.BaseService;
import com.instinctools.reducerlink.service.support.AppException;

public class AuthorizedService extends BaseService {
    public static final String ERROR_NOT_AUTHORIZED = "notAuthorized";

    @Autowired
    private UserSecurityDao userSecurityDao;

    public User ensureUser(String authorizationToken) {
        if (authorizationToken.isEmpty()) {
            throw new AppException(ERROR_NOT_AUTHORIZED);
        }

        UserSecurity userSecurity = userSecurityDao.findByAuthorizationToken(authorizationToken);

        if (userSecurity.getUser() == null) {
            throw new AppException(ERROR_NOT_AUTHORIZED);
        }

        return userSecurity.getUser();
    }
}
