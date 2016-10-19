package com.instinctools.reducerlink.service.impl;

import org.apache.commons.validator.routines.InetAddressValidator;
//import com.maxmind.geoip.Location;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instinctools.reducerlink.dao.UserCorespondenceDao;
import com.instinctools.reducerlink.dao.UserDao;
import com.instinctools.reducerlink.model.User;
import com.instinctools.reducerlink.model.UserCorespondence;
import com.instinctools.reducerlink.service.UserCorespondenceService;
import com.instinctools.reducerlink.service.base.AuthorizedService;
import com.instinctools.reducerlink.service.support.ValidationResult;

@Service
@Transactional
public class UserCorespondenceServiceImpl extends AuthorizedService implements UserCorespondenceService {
    private static final String EMAIL_REQUIRED = "emailRequired";
    private static final String EMAIL_EXIST = "emailExsist";
    private static final String SKYPE_REQUIRED = "skypeRequired";
    private static final String SKYPE_EXIST = "skypeExist";
    private static final String PHONE_EXIST = "phoneExist";
    private static final String IP_ADDRESS_REQUIRED = "ipAddressRequired";
    private static final String IP_ADDRESS_MISSING = "ipAddressMissing";
    //private static final String PATH_TO_IP_V4 = "GeoLiteCity.dat";
    //private static final String PATH_TO_IP_V6 = "GeoLiteCityv6.dat";

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
        ValidationResult<UserCorespondence> result = validateUserCorespondence(inputUserCorespondnce, new ValidationResult<UserCorespondence>());

        if (result.isFaulted()) {
            return result;
        }

        User user = ensureFound(userDao.findOne(inputUserCorespondnce.getUser().getId()));
        inputUserCorespondnce.setUser(user);

        return ValidationResult.<UserCorespondence>fromResult(userCorespondenceDao.save(inputUserCorespondnce));
    }

    @Override
    public ValidationResult<UserCorespondence> updateUserCorespondence(UserCorespondence inputUserCorespondnce) {
        ValidationResult<UserCorespondence> result = validateUserCorespondence(inputUserCorespondnce, new ValidationResult<UserCorespondence>());

        if (result.isFaulted()) {
            return result;
        }

        UserCorespondence userCorespondence = ensureFound(userCorespondenceDao.findOne(inputUserCorespondnce.getId()))
        .setEmail(inputUserCorespondnce.getEmail())
        .setSkype(inputUserCorespondnce.getSkype())
        .setPhone(inputUserCorespondnce.getPhone())
        .setIpAddress(inputUserCorespondnce.getIpAddress());

        return ValidationResult.<UserCorespondence>fromResult(userCorespondenceDao.save(userCorespondence));
    }

    @Override
    public Boolean deleteUserCorespondence(Long idUserCorespondence) {
        UserCorespondence userCorespondence = ensureFound(userCorespondenceDao.findOne(idUserCorespondence));
        userCorespondenceDao.delete(userCorespondence);

        return true;
    }

    public <T> ValidationResult<T> validateUserCorespondence(UserCorespondence inputUserCorespondence, ValidationResult<T> result) {
        if (inputUserCorespondence.getId() == null) {//first create
            if (inputUserCorespondence.getUser().getId() == null) {
                result.addError(ERROR_NOT_FOUND);
            }
        }

        if (inputUserCorespondence.getEmail().isEmpty()) {
            result.addError(EMAIL_REQUIRED);
        }

        if (userCorespondenceDao.isEmailExist(inputUserCorespondence.getEmail())) {
            result.addError(EMAIL_EXIST);
        }

        if (inputUserCorespondence.getSkype().isEmpty()) {
            result.addError(SKYPE_REQUIRED);
        }

        if (userCorespondenceDao.isSkypeExist(inputUserCorespondence.getSkype())) {
            result.addError(SKYPE_EXIST);
        }

        if (userCorespondenceDao.isPhoneExist(inputUserCorespondence.getPhone())) {
            result.addError(PHONE_EXIST);
        }

        if (inputUserCorespondence.getIpAddress().isEmpty()) {
           result.addError(IP_ADDRESS_REQUIRED);
        }

        if (!isValidIpV4(inputUserCorespondence.getIpAddress()) && !isValidIpV6(inputUserCorespondence.getIpAddress())) {
            result.addError(IP_ADDRESS_MISSING);
        }

        return result;
    }

//    public Location getLocationByIp(String ipAddress) throws IOException {
//        URL url = null;
//        Location location = null;
//        LookupService lookupService = null;
//
//        if (isValidIpV4(ipAddress)) {
//            url = getClass().getClassLoader().getResource(PATH_TO_IP_V4);
//            lookupService = new LookupService(url.getPath(), LookupService.GEOIP_MEMORY_CACHE);
//            location = lookupService.getLocation(ipAddress);
//        } else if (isValidIpV6(ipAddress)) {
//            url = getClass().getClassLoader().getResource(PATH_TO_IP_V6);
//            lookupService = new LookupService(url.getPath(), LookupService.GEOIP_MEMORY_CACHE);
//            location = lookupService.getLocationV6(ipAddress);
//        }
//
//        return location;
//    }

    public boolean isValidIpV4(final String ipAddress) {
        return new InetAddressValidator().isValidInet4Address(ipAddress);
    }

    public boolean isValidIpV6(final String ipAddress) {
        return new InetAddressValidator().isValidInet6Address(ipAddress);
    }
}
