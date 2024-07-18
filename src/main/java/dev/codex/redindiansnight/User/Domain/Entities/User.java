package dev.codex.redindiansnight.User.Domain.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.codex.redindiansnight.Booking.Domain.Entities.Booking;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private List<Permission> permissions;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<UserHobbies> userHobbies;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings;

    public User(String email, String firstName, String lastName, String password, Role role, List<Permission> permissions) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.permissions = permissions;
    }
}
