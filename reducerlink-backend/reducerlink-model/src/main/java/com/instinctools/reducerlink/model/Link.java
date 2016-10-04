package com.instinctools.reducerlink.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Link extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genn")
    @SequenceGenerator(name = "seq_genn", sequenceName = "link_id_seq", initialValue=1, allocationSize=1)
    @Column(name="id", columnDefinition="integer", nullable = false)
    private Long id;

    @Column
    private String tag;

    @Column
    private String comment;

    @Column
    private String shortUrl;

    @Column
    private String fullUrl;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private User user;

    @Override
    public Long getId() {
        return id;
    }

    public Link setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public Link setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Link setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public Link setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
        return this;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public Link setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Link setUser(User user) {
        this.user = user;
        return this;
    }
}
