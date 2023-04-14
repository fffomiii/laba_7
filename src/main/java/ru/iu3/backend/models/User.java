package ru.iu3.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
public class User {

    public User() { }
    public User(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public long id;

    @JsonIgnore
    @Column(name = "password")
    public String password;
    @Column(name = "login", unique = true, nullable = false)
    public String login;

    @Column(name = "email", unique = true, nullable = false)
    public String email;

    @JsonIgnore
    @Column(name = "salt")
    public String salt;


    @Column(name = "token")
    public String token;


    @Column(name = "activity")
    public LocalDateTime activity;

    @ManyToMany(mappedBy = "users")
    public Set<Museum> museums = new HashSet<>();

    public void addMuseum(Museum m) {
        this.museums.add(m);
        m.users.add(this);
    }

    public void removeMuseum(Museum m) {
        this.museums.remove(m);
        m.users.remove(this);
    }
}
