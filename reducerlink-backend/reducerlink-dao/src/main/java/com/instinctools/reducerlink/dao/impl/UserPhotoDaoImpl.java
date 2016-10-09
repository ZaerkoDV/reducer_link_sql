package com.instinctools.reducerlink.dao.impl;

import java.util.List;
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> getListIdUserPhoto(Long idUser) {
        Criteria criteria = createCriteria()
            .createAlias("user", "u")
            .setProjection(Projections.property("id"))
            .add(Restrictions.eq("u.id", idUser));

        return criteria.list();
    }
}
