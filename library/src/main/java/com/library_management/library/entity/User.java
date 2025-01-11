package com.library_management.library.entity;

import com.library_management.library.constant.UserRole;
import com.library_management.library.constant.AccountStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;

    String firstName;
    String lastName;
    String username;
    String password;
    String email;
    String phone;
    String address;

    @Enumerated(EnumType.STRING)
    AccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    UserRole role;
}
