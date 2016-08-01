package com.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by allan on 22/07/16.
 */
@Entity
@Table(name = "USER_DETAILS",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"username"}))
public class User implements Serializable {

    @Id
    @Column(name = "username")
    @Getter
    @Setter
    private String username;

    @Column(name = "password")
    @Getter
    @Setter
    private String password;

//    @Column(name = "email")
//    @Getter
//    @Setter
//    private String email;

    @Getter
    @Setter
    @OneToMany(targetEntity = Authority.class, orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
//    @JoinTable(name = "USER_AUTH", joinColumns = {@JoinColumn(name = "username", referencedColumnName = "username")})
    private Set<Authority> authorities;
}
