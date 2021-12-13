package com.companyCo.Geolocalization.Controllers;

import com.companyCo.Geolocalization.DTOs.UsersLocalizationDTO;
import com.companyCo.Geolocalization.Model.Localization;
import com.companyCo.Geolocalization.Services.GeoService;
import com.companyCo.Geolocalization.Services.GeoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/localization")
public class GeoController {

    private final GeoServiceInterface geoServiceInterface;

    @Autowired
    public GeoController(GeoService geoService) {
        this.geoServiceInterface = geoService;
    }

    @PostMapping("/sendLocalization")
    public void addLocalization(@RequestBody Localization localization){
        geoServiceInterface.addRecord(localization);
    }

    @PostMapping("/userAccount")
    public void addUser(@RequestBody UsersLocalizationDTO user){
        geoServiceInterface.addNewUser(user);
    }

    @GetMapping("/{id}")
    public String getLocalization(@PathVariable("id") String id){
        return geoServiceInterface.getRecords(id);
    }

    @GetMapping("/wholeList")
    public List<UsersLocalizationDTO> getWholeListOfUsers(){
        return geoServiceInterface.getUsers();
    }
}
