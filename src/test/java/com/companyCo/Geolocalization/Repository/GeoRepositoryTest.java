package com.companyCo.Geolocalization.Repository;

import com.companyCo.Geolocalization.DTOs.DtosMappers;
import com.companyCo.Geolocalization.DTOs.UsersLocalizationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class GeoRepositoryTest {

    @Autowired
    private GeoRepository geoRepository;
    String email = "af@gmail.com";
    String deviceId = "1234";

    @Test
    void itShouldFindUserWithDeviceId() {

        //given
        DtosMappers dtosMappers = new DtosMappers();
        geoRepository.save(dtosMappers.convertUsersLocDtoToUsers(new UsersLocalizationDTO("Lilien",email,deviceId)));

        //when
        boolean exists = geoRepository.findUserWithDeviceId(deviceId).isPresent();

        //then
        assertThat(exists).isTrue();
    }

    @Test
    void itShouldFindUserWithEmail() {

        //given
        DtosMappers dtosMappers = new DtosMappers();
        geoRepository.save(dtosMappers.convertUsersLocDtoToUsers(new UsersLocalizationDTO("Lilien",email,deviceId)));

        //when
        boolean exists = geoRepository.findUserWithEmail(email).isPresent();

        //then
        assertThat(exists).isTrue();
    }
}