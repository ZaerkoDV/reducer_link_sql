package com.instinctools.reducerlink.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

public class UserPhoto extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genn")
    @SequenceGenerator(name = "seq_genn", sequenceName = "public.id_user_photo_seq", initialValue=1, allocationSize=1)
    @Column(name="id_user_photo", columnDefinition="integer", nullable = false)
    private Long id;

    @Column
    private Long createdAtTimestamp;

    @Column
    private byte[] photoData;

    @ManyToOne
    @JoinColumn
    private User user;

    @Override
    public Long getId() {
        return id;
    }

    public UserPhoto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCreatedAtTimestamp() {
        return createdAtTimestamp;
    }

    public UserPhoto setCreatedAtTimestamp(Long createdAtTimestamp) {
        this.createdAtTimestamp = createdAtTimestamp;
        return this;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public UserPhoto setPhotoData(byte[] photoData) {
        this.photoData = photoData;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserPhoto setUser(User user) {
        this.user = user;
        return this;
    }
}
