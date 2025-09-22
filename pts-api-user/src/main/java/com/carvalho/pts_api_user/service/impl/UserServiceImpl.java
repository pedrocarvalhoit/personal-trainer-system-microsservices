package com.carvalho.pts_api_user.service.impl;

import com.carvalho.pts_api_user.AmazonS3Util;
import com.carvalho.pts_api_user.JwtParser;
import com.carvalho.pts_api_user.adapter.Adapter;
import com.carvalho.pts_api_user.dto.*;
import com.carvalho.pts_api_user.entity.UserEntity;
import com.carvalho.pts_api_user.exception.LoggedUserNotFoundException;
import com.carvalho.pts_api_user.exception.PermissionDeniedForEditUserException;
import com.carvalho.pts_api_user.repository.UserRepository;
import com.carvalho.pts_api_user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final Adapter adapter;
    private final JwtParser jwtParser;
    private final UserAthleteEventPublisherImpl publisher;

    public UserServiceImpl(UserRepository repository, Adapter adapter, JwtParser jwtParser, UserAthleteEventPublisherImpl publisher) {
        this.repository = repository;
        this.adapter = adapter;
        this.jwtParser = jwtParser;
        this.publisher = publisher;
    }

    //If is an Athlete, the metod set the logged user as personal trainer for the new Athlete User created
    @Override
    public void saveUserFromEvent(UserCreatedEventDto event) throws JsonProcessingException {
        UserEntity user = adapter.modelToEntity(event);

        if (event.getRole().equalsIgnoreCase("ATHLETE") && event.getPersonalTrainerId() != null) {
            UserEntity personalTrainer = repository.findById(event.getPersonalTrainerId())
                    .orElseThrow(() -> new IllegalArgumentException("Personal Trainer não encontrado com id " + event.getPersonalTrainerId()));
            user.setPersonalTrainer(personalTrainer);

            publisher.publish(UserAthleteCreatedEventDto.builder()
                    .athleteId(user.getId())
                    .fullName(user.getFullName())
                    .gender(user.getGender())
                    .build());

            user.setUserType(event.getRole());
            repository.save(user);

            publisher.publishToPersonalAthleteMapping(PersonalTrainerAndAthleteMappingDto.builder()
                    .personalId(personalTrainer.getId())
                    .athleteId(user.getId())
                    .build());
        }else {
            user.setUserType(event.getRole());
            repository.save(user);
        }
    }


    @Override
    public List<UserDto> listAll() {
        List<UserEntity> entities = repository.findAll();
        return entities.stream()
                .map(adapter::entityToModel)
                .toList();
    }

    @Override
    public UserDataForNavbarResponseDto getUserDataForNavbar(String authHeader) {
        UserEntity loggedUser = findLoggedUser(authHeader);

        return adapter.entityToNavbarResponse(loggedUser, AmazonS3Util.getPhotoUrl(loggedUser));
    }

    @Override
    public UserEditDataDto getUserData(String authHeader) {
        return adapter.entityToUserDataResponse(findLoggedUser(authHeader));
    }

    @Override
    public String updateUserData(String authHeader, @Valid UserEditDataDto dto, String athleteId) {
        UserEntity loggedUser = findLoggedUser(authHeader);
        UserEntity targetUser;

        if (athleteId != null && !athleteId.isEmpty()) {
            targetUser = repository.findById(athleteId)
                    .orElseThrow(() -> new LoggedUserNotFoundException(athleteId));
            checkPermission(loggedUser, targetUser);
        } else {
            targetUser = loggedUser;
        }

        adapter.toEditUser(targetUser, dto);
        repository.save(targetUser);

        return targetUser.getId();
    }

    @Override
    public UserEditDataDto getAthleteData(String athleteId) {
        UserEntity athlete = repository.findById(athleteId).orElseThrow(() -> new LoggedUserNotFoundException(athleteId));
        return adapter.entityToUserDataResponse(athlete);
    }

    @Override
    public UserEntity changeAthleteStatus(String athleteId) {
        UserEntity athlete = repository.findById(athleteId).orElseThrow(() -> {
            return new LoggedUserNotFoundException(athleteId);
        });

        athlete.setEnabled(!athlete.isEnabled());

        return repository.save(athlete);
    }

    public Page<UserAthleteDataResponseDto> getEnabledAthletes(String authHeader, int page, int size) {
        UserEntity user = findLoggedUser(authHeader);

        Pageable pageable = PageRequest.of(page, size, Sort.by("firstName").ascending());
        Page<UserEntity> athletePage = repository.findAllEnabledAthletesByPersonalTrainer(pageable, user.getId());

        List<UserAthleteDataResponseDto> dtoList = athletePage.getContent()
                .stream()
                .map(adapter::toAthleteDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, athletePage.getTotalElements());
    }

    public Page<UserAthleteDataResponseDto> getDisabledAthletes(String authHeader, int page, int size) {
        UserEntity user = findLoggedUser(authHeader);

        Pageable pageable = PageRequest.of(page, size, Sort.by("firstName").ascending());
        Page<UserEntity> athletePage = repository.findAllDisabledAthletesByPersonalTrainer(pageable, user.getId());

        List<UserAthleteDataResponseDto> dtoList = athletePage.getContent()
                .stream()
                .map(adapter::toAthleteDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, athletePage.getTotalElements());
    }



    @Override
    public String uploadPhoto(MultipartFile file, String authHeader, String athleteId) throws IOException {
        UserEntity loggedPersonalTrainer = findLoggedUser(authHeader);
        UserEntity targetUser;

        if(athleteId != null && !athleteId.isEmpty()) {
            targetUser = repository.findById(athleteId).orElseThrow(() -> new LoggedUserNotFoundException(athleteId));
            checkPermission(loggedPersonalTrainer, targetUser);
        }else {
            targetUser = loggedPersonalTrainer;
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        targetUser.setPhoto(fileName);
        repository.save(targetUser);

        String uploadDir = "user-photos/" + targetUser.getId();
        AmazonS3Util.removeFolder(uploadDir);
        AmazonS3Util.uploadFile(uploadDir, fileName, file.getInputStream());

        return AmazonS3Util.getPhotoUrl(targetUser);
    }

    private void checkPermission(UserEntity personalTrainer, UserEntity athlete) {
        if (!athlete.getPersonalTrainer().getId().equals(personalTrainer.getId())) {
           throw new PermissionDeniedForEditUserException("You can´t edit this athlete");
        }
    }

    private UserEntity findLoggedUser(String authHeader){
        String username = jwtParser.extractUsername(authHeader);

        UserEntity loggedUser = repository.findByEmail(username).orElseThrow(() -> {
            return LoggedUserNotFoundException.forUsername(username);
        });

        return loggedUser;
    }
}
