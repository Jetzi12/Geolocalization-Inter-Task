package com.companyCo.Geolocalization.DTOs;

import com.companyCo.Geolocalization.Model.Users;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DtosMappers {
    public UsersLocalizationDTO convertUsersToUserLocDTO (Users user){
        UsersLocalizationDTO userDTO  = new UsersLocalizationDTO();
        userDTO.setDeviceId(user.getDeviceId());
        userDTO.setRecords(user.getRecords());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setUserId(user.getUserId());
        return userDTO;
    }

    public Users convertUsersLocDtoToUsers (UsersLocalizationDTO usersLocalizationDTO){
        Users user = new Users();
        user.setRecords(usersLocalizationDTO.getRecords());
        user.setUserId(UUID.randomUUID().toString());
        user.setEmail(usersLocalizationDTO.getEmail());
        user.setName(usersLocalizationDTO.getName());
        user.setDeviceId(usersLocalizationDTO.getDeviceId());
        return user;
    }

}
