package com.instinctools.reducerlink.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.instinctools.reducerlink.dao.UserPhotoDao;
import com.instinctools.reducerlink.model.UserPhoto;

@Repository
public class UserPhotoDaoImpl extends BaseDaoImpl<UserPhoto, Long> implements UserPhotoDao {
    public UserPhotoDaoImpl() {
        super(UserPhoto.class);
    }

    @Override
    public UserPhoto getUserPhotoByIdUser(Long idUser) {
        Criteria criteria = createCriteria()
        .createAlias("user", "u")
        .add(Restrictions.eq("u.id", idUser));

        return (UserPhoto) criteria.uniqueResult();
    }

    @Override
    public Long getIdUserPhotoByIdUser(Long idUser) {
        Criteria criteria = createCriteria()
       .createAlias("user", "u")
       .setProjection(Projections.property("id"))
       .add(Restrictions.eq("u.id", idUser));

       return (Long) criteria.uniqueResult();
    }
}
