package com.carvalho.pts_api_authentication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "auth_users")
@EntityListeners(AuditingEntityListener.class)//Needs @enableJPAAuditing on app class
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @CreatedDate
    @Column(nullable = false, updatable = false) //Don´t modify on updates
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)//Don´t want to modify on create
    private LocalDateTime lastModifiedDate;
}
