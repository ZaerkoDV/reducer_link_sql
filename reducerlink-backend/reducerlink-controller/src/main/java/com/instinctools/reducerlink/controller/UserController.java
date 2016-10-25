package com.instinctools.reducerlink.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.instinctools.reducerlink.model.Link;
import com.instinctools.reducerlink.model.User;
import com.instinctools.reducerlink.model.UserCorespondence;
import com.instinctools.reducerlink.model.UserPhoto;
import com.instinctools.reducerlink.model.UserSecurity;
import com.instinctools.reducerlink.service.UserCorespondenceService;
import com.instinctools.reducerlink.service.UserPhotoService;
import com.instinctools.reducerlink.service.UserService;
import com.instinctools.reducerlink.service.support.ObjUtils;

@Controller
@RequestMapping(value="backend/user")
public class UserController extends BaseController {
    private static final String ACTIVE = "active";
    //private static final String BLOCKED = "blocked";
    private static final String DEPENDENT_LINK_EXIST = "dependentLinkExist";
    //private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserCorespondenceService userCorespondenceService;

    @Autowired
    private UserPhotoService userPhotoService;

    @RequestMapping(value = "/byId/get", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserByIdGet(@RequestBody Map<String, Object> request) {
        User user = userService.getUserById(
            ObjUtils.asLong(request, "id")
        );

        return buildOk(toMap(
            "id", user.getId(),
            "firstName", user.getFirstName(),
            "lastName", user.getLastName(),
            "middleName", user.getMiddleName(),
            "birth", user.getBirth(),
            "status", user.getStatus()
        ));
    }

    @RequestMapping(value = "/byToken/getId", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserByTokinGetId(@RequestBody Map<String, Object> request) {
        User user = userService.getUserByToken(ObjUtils.asString(request, "token"));

        return buildOk(toMap(
            "id", user.getId()
        ));
    }

    @RequestMapping(value = "/common/signup", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserFullCreate(@RequestBody Map<String, Object> request) {
        return buildValidationResult(
            userService.signup((new UserSecurity())
                .setLogin(ObjUtils.asString(request, "login"))
                .setPassword(ObjUtils.asString(request, "password"))
                .setUser(new User()
                .setFirstName(ObjUtils.asString(ObjUtils.asObject(request, "user"), "firstName"))
                .setLastName(ObjUtils.asString(ObjUtils.asObject(request, "user"), "lastName"))
                .setMiddleName(ObjUtils.asString(ObjUtils.asObject(request, "user"), "middleName"))
                .setBirth(ObjUtils.asLong(ObjUtils.asObject(request, "user"), "birth"))
                .setStatus(ObjUtils.asString(ObjUtils.asObject(request, "user"), "status", ACTIVE))
        )), userSecurity -> toMap("id", userSecurity.getUser().getId()));
    }

    @RequestMapping(value = "/common/update", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserCommonUpdate(@RequestBody Map<String, Object> request) {
        return buildValidationResult(
            userService.update((new User())
                .setId(ObjUtils.asLong(request, "id"))
                .setFirstName(ObjUtils.asString(request, "firstName"))
                .setLastName(ObjUtils.asString(request, "lastName"))
                .setMiddleName(ObjUtils.asString(request, "middleName"))
                .setBirth(ObjUtils.asLong(request, "birth"))
                .setStatus(ObjUtils.asString(request, "status", ACTIVE))
        ), user -> toMap("id", user.getId()));
    }

    @RequestMapping(value = "/common/delete", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserCommonDelete(@RequestBody Map<String, Object> request) {
        List<Link> dependentLinkList  = userService.deleteUser(ObjUtils.asLong(request, "id"));

        if (dependentLinkList.isEmpty()) {
           return buildOk(true);
        } else {
           return buildError(
               UserController.DEPENDENT_LINK_EXIST,
                   mapList(dependentLinkList, dependentLink -> toMap(
                       "id", dependentLink.getId(),
                       "comment", dependentLink.getComment()
                   ))
           );
        }
    }

    @RequestMapping(value = "/autorization/login", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserAutorizationLogin(@RequestBody Map<String, Object> request) {
        return buildValidationResult(userService.login(
            ObjUtils.asString(request, "login"),
            ObjUtils.asString(request, "password")
        ));
    }

    @RequestMapping(value = "/autorization/logout", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserAutorizationLogout(@RequestBody Map<String, Object> request) {
        return buildValidationResult(userService.logout(
            ObjUtils.asString(request, "token")
        ));
    }

    @RequestMapping(value = "/byLastName/getList", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserByLastNameGetList(@RequestBody Map<String, Object> request) {
        return buildPagedResult(userService.getListUserByLastName((new User())
            .setLastName(ObjUtils.asString(request, "lastName")),
            ObjUtils.asInteger(request, "pageNum"),
            Math.min(MAX_PAGE_SIZE, ObjUtils.asInteger(request, "pageSize"))
        ), user -> toMap(
            "id", user.getId(),
            "firstName", user.getFirstName(),
            "lastName", user.getLastName(),
            "middleName", user.getMiddleName(),
            "birth", user.getBirth()
        ));
    }

    @RequestMapping(value = "/withStatus/getList", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserWithStatusGetList(@RequestBody Map<String, Object> request) {
        return buildPagedResult(userService.getListUserWithStatus((new User())
            .setStatus(ObjUtils.asString(request, "status", ACTIVE)),
            ObjUtils.asString(request, "orderBy", "lastName"),
            ObjUtils.asBoolean(request, "orderAsc"),
            ObjUtils.asInteger(request, "pageNum"),
            Math.min(MAX_PAGE_SIZE, ObjUtils.asInteger(request, "pageSize"))
        ), user -> toMap(
            "id", user.getId(),
            "firstName", user.getFirstName(),
            "lastName", user.getLastName(),
            "middleName", user.getMiddleName(),
            "birth", user.getBirth()
        ));
    }

    @RequestMapping(value = "/corespondence/get", method = RequestMethod.POST)
       public ResponseEntity<?> actionPostUserCorespondenceGet(@RequestBody Map<String, Object> request) {
           List<UserCorespondence> listUserCorespondence = userCorespondenceService.getListUserCorespondenceByIdUser(
               ObjUtils.asLong(request, "id")
           );

           return buildOk(
               mapList(listUserCorespondence, userCorespondence -> toMap(
                   "id", userCorespondence.getId(),
                   "email", userCorespondence.getEmail(),
                   "skype", userCorespondence.getSkype(),
                   "phone", userCorespondence.getPhone(),
                   "ipv4", userCorespondence.getIpAddress()
               ))
           );
    }

    @RequestMapping(value = "/corespondence/create", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserCorespondenceCreate(@RequestBody Map<String, Object> request) {
        return buildValidationResult(
            userCorespondenceService.createUserCorespondence((new UserCorespondence())
                .setEmail(ObjUtils.asString(request, "email"))
                .setSkype(ObjUtils.asString(request, "skype"))
                .setPhone(ObjUtils.asString(request, "phone"))
                .setIpAddress(ObjUtils.asString(request, "ipAddress"))
                .setUser(new User()
                .setId(ObjUtils.asLong(ObjUtils.asObject(request, "user"), "id"))
            )), userCorespondence -> toMap(
                "idUser", userCorespondence.getUser().getId(),
                "idCorespondence", userCorespondence.getId()
            ));
    }

    @RequestMapping(value = "/corespondence/update", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserCorespondenceUpdate(@RequestBody Map<String, Object> request) {
        return buildValidationResult(
            userCorespondenceService.updateUserCorespondence((new UserCorespondence())
                .setId(ObjUtils.asLong(request, "id"))
                .setEmail(ObjUtils.asString(request, "email"))
                .setSkype(ObjUtils.asString(request, "skype"))
                .setPhone(ObjUtils.asString(request, "phone"))
                .setIpAddress(ObjUtils.asString(request, "ipAddress"))
            ), userCorespondence -> toMap(
                "idUser", userCorespondence.getUser().getId(),
                "idCorespondence", userCorespondence.getId()
            ));
    }

    @RequestMapping(value = "/corespondence/delete", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserCorespondenceDelete(@RequestBody Map<String, Object> request) {
        return buildOk(userCorespondenceService.deleteUserCorespondence(
            ObjUtils.asLong(request, "id")
        ));
    }

    @RequestMapping(value = "/photo/get/{idPhoto}", method = RequestMethod.GET)
    public BufferedImage actionPostUserPhotoGet(@PathVariable("idPhoto") Long idPhoto) {
        return userPhotoService.getPhotoById(idPhoto);
    }

    @RequestMapping(value = "/photo/getByIdUser/{idUser}", method = RequestMethod.GET)
    public BufferedImage actionPostGetUserPhotoByIdUser(@PathVariable("idUser") Long idUser) {
        return userPhotoService.getUserPhotoByIdUser(idUser);
    }

    @RequestMapping(value = "/photo/getIdPhotoByIdUser/{idUser}", method = RequestMethod.GET)
    public ResponseEntity<?> actionPostGetIdUserPhotoByIdUser(@PathVariable("idUser") Long idUser) {
        Long idUserPhoto = userPhotoService.getIdUserPhotoByIdUser(idUser);

        return buildOk(toMap(
            "id", idUserPhoto
        ));
    }

    @RequestMapping(value = "/photo/create", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserPhotoCreate(@RequestParam("id") Long idUser, @RequestParam("file") MultipartFile file) {
        byte[] imageFile;

        try {
            if (file.isEmpty()) {
                return buildError(ERROR_UPLOAD);
            }

            imageFile = file.getBytes();

        } catch (IOException e) {
            return buildError(ERROR_UPLOAD);
        }

        UserPhoto userPhoto = userPhotoService.saveUserPhoto(
            idUser,
            getCurrentTimestamp(),
            imageFile
        );

        return buildOk(toMap(
            "id", userPhoto.getId()
        ));
    }

    @RequestMapping(value = "/photo/delete", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserPhotoDelete(@RequestBody Map<String, Object> request) {
        return buildOk(userPhotoService.deleteUserPhoto(
            ObjUtils.asLong(request, "id")
        ));
    }
}
