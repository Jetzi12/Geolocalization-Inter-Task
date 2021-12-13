package com.companyCo.Geolocalization.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersLocalizationDTO {

    private String userId;
    private String name;
    private String email;
    private String deviceId;
    private String records;

    public UsersLocalizationDTO(String name, String email, String deviceId) {
        this.name = name;
        this.email = email;
        this.deviceId = deviceId;
    }

    public UsersLocalizationDTO() {
    }
}
