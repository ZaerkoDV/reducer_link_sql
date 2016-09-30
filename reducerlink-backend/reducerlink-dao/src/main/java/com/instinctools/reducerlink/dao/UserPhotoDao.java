package com.instinctools.reducerlink.dao;

import com.instinctools.reducerlink.model.UserPhoto;

public class UserPhotoDao extends BaseDao<UserPhoto, Long> {
    public UserPhotoDao() {
        super(UserPhoto.class);
    }
}
