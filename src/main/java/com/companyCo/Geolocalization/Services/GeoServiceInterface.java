package com.companyCo.Geolocalization.Services;

import com.companyCo.Geolocalization.DTOs.UsersLocalizationDTO;
import com.companyCo.Geolocalization.Model.Localization;
import java.util.List;

public interface GeoServiceInterface {
    void addRecord(Localization localization);
    String getRecords(String deviceId);
    List<UsersLocalizationDTO> getUsers();
    void addNewUser(UsersLocalizationDTO userDTO);
}
