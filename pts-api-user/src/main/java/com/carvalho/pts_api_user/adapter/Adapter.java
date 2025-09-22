package com.carvalho.pts_api_user.adapter;

import com.carvalho.pts_api_user.AmazonS3Util;
import com.carvalho.pts_api_user.dto.*;
import com.carvalho.pts_api_user.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Adapter {

    public UserEntity modelToEntity(UserCreatedEventDto dto){
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(dto, entity, "role", "personalTrainerId");
        return entity;
    }

    public UserDto entityToModel(UserEntity entity) {
        UserDto dto = UserDto.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public UserDataForNavbarResponseDto entityToNavbarResponse(UserEntity entity, String temporaryUrl) {
        return UserDataForNavbarResponseDto.builder()
                .firstName(entity.getFirstName())
                .photo(temporaryUrl)
                .build();
    }

    public UserEditDataDto entityToUserDataResponse(UserEntity loggedUser) {
        return UserEditDataDto.builder()
                .firstName(loggedUser.getFirstName())
                .lastName(loggedUser.getLastName())
                .dateOfBirth(loggedUser.getDateOfBirth())
                .phone(loggedUser.getPhone())
                .gender(loggedUser.getGender())
                .build();
    }

    public void toEditUser(UserEntity user, @Valid UserEditDataDto userEditDataDto) {
        user.setFirstName(userEditDataDto.getFirstName());
        user.setLastName(userEditDataDto.getLastName());
        user.setDateOfBirth(userEditDataDto.getDateOfBirth());
        user.setGender(userEditDataDto.getGender());
        user.setPhone(userEditDataDto.getPhone());
    }

    public UserAthleteDataResponseDto toAthleteDto(UserEntity userEntity) {
        return UserAthleteDataResponseDto.builder()
                .id(userEntity.getId())
                .fullName(userEntity.getFullName())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .photo(AmazonS3Util.getPhotoUrl(userEntity))
                .dateOfBirth(userEntity.getDateOfBirth())
                .age(userEntity.getAge())
                .gender(userEntity.getGender())
                .enabled(userEntity.isEnabled())
                .build();
    }
}
