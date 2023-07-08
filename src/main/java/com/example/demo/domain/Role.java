package com.example.demo.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String roleName;
    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();
    @ManyToOne
    private User user;
}
