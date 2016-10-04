package com.instinctools.reducerlink.dao.impl;

import org.springframework.stereotype.Repository;
import com.instinctools.reducerlink.dao.UserPhotoDao;
import com.instinctools.reducerlink.model.UserPhoto;

@Repository
public class UserPhotoDaoImpl extends BaseDaoImpl<UserPhoto, Long> implements UserPhotoDao {
    public UserPhotoDaoImpl() {
        super(UserPhoto.class);
    }

    @Override
    public UserPhoto findByIdUserAndId(Long idUser, Long idUserPhoto) {
        return null;
    }
    @Override
    public Long getListIdUserPhoto(Long idUser) {
        return null;
    }
}
