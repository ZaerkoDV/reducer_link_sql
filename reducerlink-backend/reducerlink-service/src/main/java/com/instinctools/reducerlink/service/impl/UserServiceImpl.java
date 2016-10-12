package com.instinctools.reducerlink.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instinctools.reducerlink.dao.LinkDao;
import com.instinctools.reducerlink.dao.UserDao;
import com.instinctools.reducerlink.dao.UserSecurityDao;
import com.instinctools.reducerlink.model.Link;
import com.instinctools.reducerlink.model.User;
import com.instinctools.reducerlink.model.UserSecurity;
import com.instinctools.reducerlink.service.UserService;
import com.instinctools.reducerlink.service.base.AuthorizedService;
import com.instinctools.reducerlink.service.support.PagedResult;
import com.instinctools.reducerlink.service.support.ValidationResult;

@Service
@Transactional
public class UserServiceImpl extends AuthorizedService implements UserService {
    private static final String ERROR_INVALID_CREDENTIALS = "invalidCredentials";
    private static final String ADMIN_ROLE = "admin";
    private static final String LOGIN_REQUIRED = "loginRequired";
    private static final String LOGIN_EXIST = "loginExist";
    private static final String PASSWORD_REQUIRED = "passwordRequired";
    private static final String FIRST_NAME_REQUIRED = "firstNameRequired";
    private static final String LAST_NAME_REQUIRED = "lastNameRequired";
    private static final String BIRTH_REQUIRED = "birthRequired";
    public static BCryptPasswordEncoder BCPE = new BCryptPasswordEncoder();

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserSecurityDao userSecurityDao;

    @Autowired
    private LinkDao linkDao;

    @Override
    public User getUserById(Long idUser){
        return ensureFound(userDao.findOne(idUser));
    }

    @Override
    public ValidationResult<UserSecurity> signup(UserSecurity inputUserSecurity) {
        ValidationResult<UserSecurity> result = validate(inputUserSecurity, new ValidationResult<UserSecurity>());

        if (result.isFaulted()) {
            return result;
        }

        inputUserSecurity.setRole(ADMIN_ROLE);
        inputUserSecurity.setPassword(BCPE.encode(inputUserSecurity.getPassword()));
        inputUserSecurity.setToken(generateToken());

        userDao.save(inputUserSecurity.getUser());
        return result.setResult(userSecurityDao.save(inputUserSecurity));
    }

    @Override
    public ValidationResult<User> update(User inputUser) {
        ValidationResult<User> result = validate(new UserSecurity().setUser(inputUser), new ValidationResult<User>());

        if (result.isFaulted()) {
            return result;
        }

        User updatedUser = ensureFound(userDao.findOne(inputUser.getId()));
        updatedUser.setFirstName(inputUser.getFirstName())
        .setLastName(inputUser.getLastName())
        .setMiddleName(inputUser.getMiddleName())
        .setBirth(inputUser.getBirth())
        .setStatus(inputUser.getStatus());

        return result.setResult(userDao.save(updatedUser));
    }

    @Override
    public List<Link> deleteUser(Long idUser) {
        List<Link> dependentListList = linkDao.getListLinkByIdUser(idUser);

        if (dependentListList.isEmpty()) {
            userDao.delete(idUser);
        }

        return dependentListList;
    }

    @Override
    public ValidationResult<String> login(String login, String password) {
        String encodedPassword = userSecurityDao.getEncodedPasswordByLogin(login);
        //find and compare
        if (!BCPE.matches(password, encodedPassword)) {
            return ValidationResult.<String>fromErrorCode(ERROR_INVALID_CREDENTIALS);
        }

        //find old token for change by login and return new token
        String oldToken = userSecurityDao.getOldTokenByLogin(login);
        UserSecurity userSecurity = ensureFound(userSecurityDao.findUserSecurityByToken(oldToken));
        userSecurity.setToken(generateToken());
        userSecurityDao.save(userSecurity);

        return ValidationResult.<String>fromResult(userSecurity.getToken());
    }

    @Override
    public ValidationResult<Boolean> logout(String token) {
        UserSecurity userSecurity = ensureUserSecurity(token);

        userSecurity.setToken(null);
        userSecurityDao.save(userSecurity);

        return ValidationResult.<Boolean>fromResult(true);
    }

    @Override
    public PagedResult<User> getListUserByLastName(User inputUser, int pageNum, int pageSize) {
        return new PagedResult<User>(
            userDao.getListUserByLastName(inputUser, pageNum, pageSize),
            computePages(userDao.countTotalUser(), pageSize)
        );
    }

    @Override
    public PagedResult<User> getListUserWithStatus(User inputUser, String orderBy, boolean orderAsc, int pageNum, int pageSize) {
        return new PagedResult<User>(
            userDao.getListUserWithStatus(inputUser, orderBy, orderAsc, pageNum, pageSize),
            computePages(userDao.countUserWithStatus(inputUser), pageSize)
        );
    };

    public <T> ValidationResult<T> validate(UserSecurity inputUserSecurity, ValidationResult<T> result) {
        if (inputUserSecurity.getUser().getId() == null) {
            if (inputUserSecurity.getLogin().isEmpty()) {
                result.addError(LOGIN_REQUIRED);
            }

            if (userSecurityDao.isLoginExist(inputUserSecurity.getLogin())) {
                result.addError(LOGIN_EXIST);
            }

            if (inputUserSecurity.getPassword().isEmpty()) {
                result.addError(PASSWORD_REQUIRED);
            }
        }

        if (inputUserSecurity.getUser().getFirstName().isEmpty()) {
            result.addError(FIRST_NAME_REQUIRED);
        }

        if (inputUserSecurity.getUser().getLastName().isEmpty()) {
            result.addError(LAST_NAME_REQUIRED);
        }

        if (inputUserSecurity.getUser().getBirth().toString().isEmpty()) {
           result.addError(BIRTH_REQUIRED);
        }

        return result;
    }
}