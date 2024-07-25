package com.app.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq",sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;
    @Column(unique = true, nullable = false,length = 100)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(name = "is_enable")
    private boolean isEnabled;
    @Column(name = "account_no_expired")
    private boolean accountNoExpired;
    @Column(name = "account_no_locked")
    private boolean accountNoLocked;
    @Column(name = "credential_no_expired")
    private boolean credentialNoExpired;

    //relacion
    /*
        CascadeType.ALL guarda automaticamento los roles que tenga el usuario asociado
     */
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)

    // nombre de la tabla con las relaciones // nombre de la 1columna con la primera forikey //nombre de la 2columna con la primera forikey
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RolEntity> roles =  new HashSet<>();

}
