package com.instinctools.reducerlink.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instinctools.reducerlink.dao.UserCorespondenceDao;
import com.instinctools.reducerlink.dao.UserDao;
import com.instinctools.reducerlink.model.UserCorespondence;
import com.instinctools.reducerlink.model.UserSecurity;
import com.instinctools.reducerlink.service.UserCorespondenceService;
import com.instinctools.reducerlink.service.base.AuthorizedService;
import com.instinctools.reducerlink.service.support.ValidationResult;

@Service
@Transactional
public class UserCorespondenceServiceImpl extends AuthorizedService implements UserCorespondenceService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserCorespondenceDao userCorespondenceDao;

    @Override
    public List<UserCorespondence> getListUserCorespondenceByIdUser(Long idUser) {
        return userCorespondenceDao.getListUserCorespondencesByIdUser(idUser);
    }

    @Override
    public ValidationResult<UserCorespondence> createUserCorespondence(UserCorespondence inputUserCorespondnce) {
        ValidationResult<UserCorespondence> result = validate(inputUserCorespondnce, new ValidationResult<UserCorespondence>());

        if (result.isFaulted()) {
            return result;
        }

        return null;
    }

    public <T> ValidationResult<T> validate(UserCorespondence inputUserCorespondence, ValidationResult<T> result) {
        return result;
    }

}
