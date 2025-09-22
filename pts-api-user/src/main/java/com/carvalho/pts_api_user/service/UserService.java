package com.carvalho.pts_api_user.service;

import com.carvalho.pts_api_user.dto.*;
import com.carvalho.pts_api_user.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    void saveUserFromEvent(UserCreatedEventDto event) throws JsonProcessingException;

    List<UserDto> listAll();

    UserDataForNavbarResponseDto getUserDataForNavbar(String authHeader);

    String uploadPhoto(MultipartFile file, String authHeader, String athleteId) throws IOException;

    UserEditDataDto getUserData(String authHeader);

    String updateUserData(String authHeader, @Valid UserEditDataDto userEditDataDto, String athleteId);

    public Page<UserAthleteDataResponseDto> getEnabledAthletes(String authHeader, int page, int size);

    Page<UserAthleteDataResponseDto> getDisabledAthletes(String authHeader, int page, int size);

    UserEntity changeAthleteStatus(String athleteId);

    UserEditDataDto getAthleteData(String athleteId);

//    String updateAthleteData(String athleteId, @Valid UserEditDataDto userEditDataDto);
}
