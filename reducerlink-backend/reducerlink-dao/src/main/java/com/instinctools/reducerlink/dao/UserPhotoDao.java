package com.instinctools.reducerlink.dao;

import com.instinctools.reducerlink.model.UserPhoto;

public interface UserPhotoDao  extends BaseDao<UserPhoto, Long> {
    public UserPhoto getUserPhotoByIdUser(Long idUser);
    public Long getIdUserPhotoByIdUser(Long idUser);
}
