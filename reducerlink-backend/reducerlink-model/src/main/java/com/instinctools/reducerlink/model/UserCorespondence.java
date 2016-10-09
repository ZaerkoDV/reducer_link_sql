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
public class UserCorespondence extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genn")
    @SequenceGenerator(name = "seq_genn", sequenceName = "user_corespondence_id_seq", initialValue=1, allocationSize=1)
    @Column(name="id", columnDefinition="integer", nullable = false)
    private Long id;

    @Column
    private String email;

    @Column
    private String skype;

    @Column
    private String phone;

    @Column
    private String ipAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Override
    public Long getId() {
        return id;
    }

    public UserCorespondence setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserCorespondence setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSkype() {
        return skype;
    }

    public UserCorespondence setSkype(String skype) {
        this.skype = skype;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserCorespondence setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public UserCorespondence setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserCorespondence setUser(User user) {
        this.user = user;
        return this;
    }
}
