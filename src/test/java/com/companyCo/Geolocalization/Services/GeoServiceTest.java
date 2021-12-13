package com.companyCo.Geolocalization.Services;

import com.companyCo.Geolocalization.DTOs.DtosMappers;
import com.companyCo.Geolocalization.DTOs.UsersLocalizationDTO;
import com.companyCo.Geolocalization.Model.Localization;
import com.companyCo.Geolocalization.Model.Users;
import com.companyCo.Geolocalization.Repository.GeoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@ContextConfiguration
@ExtendWith(MockitoExtension.class)
class GeoServiceTest {

    @Autowired
    private GeoRepository geoRepository;
    private GeoService geoService;
    private DtosMappers dtosMappers;

    @BeforeEach
    void setUp(){

        geoService = new GeoService(geoRepository, dtosMappers, new ArrayList<>());

    }

    @Test
    void canGetRecords() {
        //given
        UsersLocalizationDTO usersLocalizationDTO = new UsersLocalizationDTO("lilien","f@gmail.com","1234");
        geoService.addNewUser(usersLocalizationDTO);
        for (int i = 0; i < 10; i++) {
            geoService.addRecord(new Localization("1234", 12L, 124L));
        }
        //then
        assertThat(geoService.getRecords("1234")).isNotEmpty();
    }

    @Test
    void canAddRecord() {
        //given
        UsersLocalizationDTO usersLocalizationDTO = new UsersLocalizationDTO("lilisen","fsrthfss@gmail.com","123434");
        geoService.addNewUser(usersLocalizationDTO);
        //when
        geoService.addRecord(new Localization("123434",12L,124L));
        //then
        assertThat(geoService.getListOfRecords().size()).isNotZero();
    }

    @Test
    void canGetUsers() {
        //when
        UsersLocalizationDTO usersLocalizationDTO = new UsersLocalizationDTO("lilien","fsrthss@gmail.com","1434");
        geoService.addNewUser(usersLocalizationDTO);
        //then
        assertThat(geoService.getUsers().size()).isNotZero();

    }

    @Test
    void canAddNewUser() {
        //given
        UsersLocalizationDTO usersLocalizationDTO = new UsersLocalizationDTO("litlien","fes@gmail.com","12345");
        DtosMappers dtosMappers = new DtosMappers();

        //when
        geoService.addNewUser(usersLocalizationDTO);

        //then
        assertThat(geoRepository.findUserWithDeviceId("12345")).isNotNull();
        Users user = geoRepository.findUserWithDeviceId("12345").orElseThrow(() -> new IllegalStateException(""));

        assertThat(user.getName()).isEqualTo(dtosMappers.convertUsersLocDtoToUsers(usersLocalizationDTO).getName());
        assertThat(user.getEmail()).isEqualTo(dtosMappers.convertUsersLocDtoToUsers(usersLocalizationDTO).getEmail());
        assertThat(user.getDeviceId()).isEqualTo(dtosMappers.convertUsersLocDtoToUsers(usersLocalizationDTO).getDeviceId());
    }
}