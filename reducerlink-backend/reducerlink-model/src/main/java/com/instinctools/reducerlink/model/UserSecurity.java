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
public class UserSecurity extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genn")
    @SequenceGenerator(name = "seq_genn", sequenceName = "user_security_id_seq", initialValue=1, allocationSize=1)
    @Column(name="id", columnDefinition="integer", nullable = false)
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String role;

    @Column
    private String token;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @Override
    public Long getId() {
        return id;
    }

    public UserSecurity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserSecurity setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserSecurity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserSecurity setRole(String role) {
        this.role = role;
        return this;
    }

    public String getToken() {
        return token;
    }

    public UserSecurity setToken(String token) {
        this.token = token;
        return this;
    }

    public User getUser(){
        return user;
    }

    public UserSecurity setUser(User user) {
        this.user = user;
        return this;
    }
}
