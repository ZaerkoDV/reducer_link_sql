package com.instinctools.reducerlink.service;

import java.awt.image.BufferedImage;
import com.instinctools.reducerlink.model.UserPhoto;

public interface UserPhotoService {
    public BufferedImage getPhotoById(Long idPhoto);
    public UserPhoto saveUserPhoto(Long idUser, long currentTimestamp, byte[] imageFile);
    public Boolean deleteUserPhoto(Long idPhoto);
    public BufferedImage getUserPhotoByIdUser(Long idUser);
    public Long getIdUserPhotoByIdUser(Long idUser);
}
