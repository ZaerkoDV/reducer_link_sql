package com.instinctools.reducerlink.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instinctools.reducerlink.dao.UserDao;
import com.instinctools.reducerlink.dao.UserPhotoDao;
import com.instinctools.reducerlink.model.User;
import com.instinctools.reducerlink.model.UserPhoto;
import com.instinctools.reducerlink.service.UserPhotoService;
import com.instinctools.reducerlink.service.base.AuthorizedService;

@Service
@Transactional
public class UserPhotoServiceImpl extends AuthorizedService implements UserPhotoService {
    @Autowired
    private UserPhotoDao userPhotoDao;

    @Autowired
    private UserDao userDao;

    @Override
    public BufferedImage getPhotoById(Long idPhoto) {
        UserPhoto userPhoto = ensureFound(userPhotoDao.findOne(idPhoto));

        try (ByteArrayInputStream bais = new ByteArrayInputStream(userPhoto.getPhotoData())) {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserPhoto saveUserPhoto(Long idUser, long currentTimestamp, byte[] imageFile) {
        User user = ensureFound(userDao.findOne(idUser));

        return userPhotoDao.save((new UserPhoto())
            .setUser(user)
            .setCreatedAtTimestamp(currentTimestamp)
            .setPhotoData(imageFile)
        );
    }

    @Override
    public Boolean deleteUserPhoto(Long idPhoto) {
        UserPhoto userPhoto = ensureFound(userPhotoDao.findOne(idPhoto));
        userPhotoDao.delete(userPhoto);

        return true;
    }
}
