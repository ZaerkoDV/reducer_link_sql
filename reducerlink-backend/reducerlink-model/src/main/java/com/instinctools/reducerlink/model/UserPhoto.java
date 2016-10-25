package com.instinctools.reducerlink.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class UserPhoto extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genn")
    @SequenceGenerator(name = "seq_genn", sequenceName = "user_photo_id_seq", initialValue=1, allocationSize=1)
    @Column(name="id", columnDefinition="integer", nullable = false)
    private Long id;

    @Column
    private Long createdAtTimestamp;

    @Column
    private byte[] photoData;

    @OneToOne(fetch = FetchType.LAZY)
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
