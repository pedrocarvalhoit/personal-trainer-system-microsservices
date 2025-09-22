package com.carvalho.pts_api_user.controller;

import com.carvalho.pts_api_user.dto.UserAthleteDataResponseDto;
import com.carvalho.pts_api_user.dto.UserEditDataDto;
import com.carvalho.pts_api_user.dto.UserDataForNavbarResponseDto;
import com.carvalho.pts_api_user.dto.UserDto;
import com.carvalho.pts_api_user.entity.UserEntity;
import com.carvalho.pts_api_user.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final UserServiceImpl service;

    public UserController(UserServiceImpl userService) {
        this.service = userService;
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<UserDto> userList = service.listAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/get-user-data")
    public ResponseEntity<UserEditDataDto> getUserDataForEditForm(@RequestHeader("Authorization") String authHeader){
        return ResponseEntity.ok(service.getUserData(authHeader));
    }


    @GetMapping("/get-user-data-for-navbar")
    public ResponseEntity<UserDataForNavbarResponseDto> getUserDataForNavbar(@RequestHeader("Authorization") String authHeader){
        return ResponseEntity.ok(service.getUserDataForNavbar(authHeader));
    }

    @GetMapping("/get-athlete-data/{athleteId}")
    public ResponseEntity<UserEditDataDto> getAthleteDataForEditForm(@PathVariable String athleteId){
        return ResponseEntity.ok(service.getAthleteData(athleteId));
    }

    @PatchMapping(value = {"/upload-photo", "/upload-athlete-photo/{athleteId}"})
    public ResponseEntity<String> uploadPhoto(@RequestHeader("Authorization") String authHeader,
                                              @RequestPart("file") MultipartFile file,
                                              @PathVariable(required = false) String athleteId) throws IOException {
        return ResponseEntity.ok(service.uploadPhoto(file, authHeader, athleteId));
    }

    @PatchMapping(value = {"/update-user-data", "/update-athlete-data/{athleteId}"})
    public ResponseEntity<String> updateUserData(@RequestHeader("Authorization") String authHeader,
                                                 @Valid @RequestBody UserEditDataDto userEditDataDto,
                                                 @PathVariable(required = false) String athleteId) {
        return ResponseEntity.ok(service.updateUserData(authHeader, userEditDataDto, athleteId));
    }

    //findAllEnabled
    @GetMapping("/get-enabled-athletes")
    public ResponseEntity<Page<UserAthleteDataResponseDto>> getEnabledAthletes(@RequestHeader("Authorization") String authHeader,
                                                                               @RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "10") int size){
        Page<UserAthleteDataResponseDto> athletes = service.getEnabledAthletes(authHeader, page, size);
        return ResponseEntity.ok(athletes);
    }

    //findAllDisabled
    @GetMapping("/get-disabled-athletes")
    public ResponseEntity<Page<UserAthleteDataResponseDto>> getDisabledAthletes(@RequestHeader("Authorization") String authHeader,
                                                                               @RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "10") int size){
        Page<UserAthleteDataResponseDto> athletes = service.getDisabledAthletes(authHeader, page, size);
        return ResponseEntity.ok(athletes);
    }

    @PatchMapping("/change-status/{athleteId}")
    public ResponseEntity<Map<String, Object>> changeAthleteStatus(@PathVariable String athleteId){
        UserEntity athlete = service.changeAthleteStatus(athleteId);
        return ResponseEntity.ok(
                Map.of(
                        "athleteId", athlete.getId().toString(),
                        "enabled", athlete.isEnabled()
                )
        );
    }

}
