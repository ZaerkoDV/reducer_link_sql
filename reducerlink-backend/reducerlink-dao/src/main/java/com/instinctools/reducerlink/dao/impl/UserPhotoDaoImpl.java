package com.instinctools.reducerlink.dao.impl;

import com.instinctools.reducerlink.model.UserPhoto;

public class UserPhotoDaoImpl extends BaseDao<UserPhoto, Long> {
    public UserPhotoDaoImpl() {
        super(UserPhoto.class);
    }

    public UserPhoto findByIdUserAndId(Long idUser, Long idUserPhoto) {
        return null;
    }
    public Long getListIdUserPhoto(Long idUser) {
        return null;
    }
}
