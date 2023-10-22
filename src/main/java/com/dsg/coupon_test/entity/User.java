package com.dsg.coupon_test.entity;

import com.dsg.coupon_test.enums.UserRole;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@ToString
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;
    @Column(unique = true, nullable = false, length = 30)
    private String email;
    @Column(nullable = false, length = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Builder
    public User(Long id, String name, String email, String password, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
