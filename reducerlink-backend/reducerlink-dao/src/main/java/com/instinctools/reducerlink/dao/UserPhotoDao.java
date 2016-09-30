package com.instinctools.reducerlink.dao;

import com.instinctools.reducerlink.model.UserPhoto;

public interface UserPhotoDao {
    public UserPhoto findByIdUserAndId(Long idUser, Long idUserPhoto);
    public Long getListIdUserPhoto(Long idUser);
}
