package com.companyCo.Geolocalization.Services;

import com.companyCo.Geolocalization.DTOs.DtosMappers;
import com.companyCo.Geolocalization.DTOs.UsersLocalizationDTO;
import com.companyCo.Geolocalization.DataHandling.DataSupervisor;
import com.companyCo.Geolocalization.Model.Localization;
import com.companyCo.Geolocalization.Model.Users;
import com.companyCo.Geolocalization.Repository.GeoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Log4j
@Service
@AllArgsConstructor
public class GeoService implements GeoServiceInterface{
    private final GeoRepository geoRepository;
    private final DtosMappers convertersToDTOs;
    private List<Localization> listOfRecords;

    @Transactional
    @Override
    public void addRecord(Localization localization) {
        if(localization.getDeviceId().isEmpty() || localization.getLatitude() == null || localization.getLongitude() == null){
            throw new IllegalStateException("Wrong message");
        }
        geoRepository.findUserWithDeviceId(localization.getDeviceId()).orElseThrow(() -> new IllegalStateException("Unused deviceId"));
        listOfRecords.add(localization);
        HashMap<String,String> textOfRecords = new DataSupervisor().checkIfListIsFull(listOfRecords);

        if(textOfRecords.size()>0){
            textOfRecords.forEach((a,b)-> {
                Users usersToUpdateRec = geoRepository.findUserWithDeviceId(a).orElseThrow(() -> new IllegalStateException("Error with finding proper user"));
                usersToUpdateRec.setRecords(b.toString());
                geoRepository.save(usersToUpdateRec);
            });
            listOfRecords.clear();
        }
    }
    @Override
    public String getRecords(String deviceId) {
        Optional<Users> user = geoRepository.findUserWithDeviceId(deviceId);
        return user.map(Users::getRecords).orElseThrow(() -> new IllegalStateException("There is none device with that id "));

    }

    @Override
    public List<UsersLocalizationDTO> getUsers() {
        List<Users> wholeList = geoRepository.findAll();

        DtosMappers convertersToDTOs = new DtosMappers();
        return wholeList.stream().map(a -> convertersToDTOs.convertUsersToUserLocDTO(a)).collect(Collectors.toList());
    }

    @Override
    public void addNewUser(UsersLocalizationDTO userDTO) {
        Optional<Users> usersOptional = geoRepository.findUserWithEmail(userDTO.getEmail());

        if((userDTO.getEmail().isEmpty())|| userDTO.getName().isEmpty() || userDTO.getDeviceId().isEmpty() ){
            throw new IllegalStateException("You have to fill name,email and device id");
        }
        if(usersOptional.isPresent()){
            throw new IllegalStateException("Email is already taken");
        } else if(geoRepository.findUserWithDeviceId(userDTO.getDeviceId()).isPresent() && !userDTO.getDeviceId().isEmpty()) {
            throw new IllegalStateException("Device is parred with another account");
        }
        Users user = new DtosMappers().convertUsersLocDtoToUsers(userDTO);
        geoRepository.save(user);
    }
}
