package com.carvalho.pts_api_user.entity;

import com.carvalho.pts_api_user.AmazonS3Util;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)//Needs @enableJPAAuditing on app class
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String email;

    @CreatedDate
    @Column(nullable = false, updatable = false) //Don´t modify on updates
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)//Don´t want to modify on create
    private LocalDateTime lastModifiedDate;

    @Column(nullable = false)
    private String userType;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String gender;

    private String photo;

    @Column
    private boolean enabled = true;

    @OneToMany(mappedBy = "personalTrainer", cascade = CascadeType.ALL)
    private List<UserEntity> athletes;

    @ManyToOne
    @JoinColumn(name = "pt_id")
    private UserEntity personalTrainer;

    @Transient
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    @Transient
    public Integer getAge(){
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity client = (UserEntity) o;
        return Objects.equals(this.getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
