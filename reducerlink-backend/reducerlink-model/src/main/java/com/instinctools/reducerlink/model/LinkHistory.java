package com.instinctools.reducerlink.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class LinkHistory extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genn")
    @SequenceGenerator(name = "seq_genn", sequenceName = "link_history_id_seq", initialValue=1, allocationSize=1)
    @Column(name="id", columnDefinition="integer", nullable = false)
    private Long id;

    @Column
    private Long createdAtTimestamp;

    @Column
    private Long sumClick;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Link link;

    @Override
    public Long getId() {
        return id;
    }

    public LinkHistory setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCreatedAtTimestamp() {
        return createdAtTimestamp;
    }

    public LinkHistory setCreatedAtTimestamp(Long createdAtTimestamp) {
        this.createdAtTimestamp = createdAtTimestamp;
        return this;
    }

    public Long getSumClick() {
        return sumClick;
    }

    public LinkHistory setSumClick(Long sumClick) {
        this.sumClick = sumClick;
        return this;
    }

    public Link getLink() {
        return link;
    }

    public LinkHistory setLink(Link link) {
        this.link = link;
        return this;
    }
}
