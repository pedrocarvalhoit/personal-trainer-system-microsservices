package com.carvalho.pts_api_user.repository;

import com.carvalho.pts_api_user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String username);

    List<UserEntity> findByPersonalTrainerId(String ptId);

    @Query("SELECT u FROM UserEntity u WHERE u.enabled = TRUE AND u.personalTrainer.id = :personalTrainerId AND u.userType = 'ATHLETE'")
    Page<UserEntity> findAllEnabledAthletesByPersonalTrainer(Pageable pageable, @Param("personalTrainerId") String personalTrainerId);

    @Query("SELECT u FROM UserEntity u WHERE u.enabled = FALSE AND u.personalTrainer.id = :personalTrainerId AND u.userType = 'ATHLETE'")
    Page<UserEntity> findAllDisabledAthletesByPersonalTrainer(Pageable pageable, @Param("personalTrainerId") String personalTrainerId);
}
