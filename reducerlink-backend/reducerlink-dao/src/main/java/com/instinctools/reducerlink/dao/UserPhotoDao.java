package com.instinctools.reducerlink.dao;

import java.util.List;
import com.instinctools.reducerlink.model.UserPhoto;

public interface UserPhotoDao  extends BaseDao<UserPhoto, Long> {
    public List<Long> getListIdUserPhoto(Long idUser);
}
