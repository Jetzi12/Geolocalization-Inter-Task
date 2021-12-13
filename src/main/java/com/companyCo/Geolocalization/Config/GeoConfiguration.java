package com.companyCo.Geolocalization.Config;

import com.companyCo.Geolocalization.DTOs.DtosMappers;
import com.companyCo.Geolocalization.Model.Users;
import com.companyCo.Geolocalization.Repository.GeoRepository;
import com.companyCo.Geolocalization.Services.GeoService;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class GeoConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(GeoRepository geoRepository){
        return args -> {
            Users user = new Users(UUID.randomUUID().toString(),"mike","mike@gmail.com","12fweg43"); //ToDO STALEJ  nale[iej liquidbase
            Users user2 = new Users(UUID.randomUUID().toString(),"mikeing","mikeing@gmail.com","124we3");
            Users user3 = new Users(UUID.randomUUID().toString(),"miked","miked@gmail.com","124r3");
            geoRepository.saveAll(Stream.of(user,user2,user3).collect(Collectors.toList()));
        };
    }

    DtosMappers dtosMappers(){
        return new DtosMappers();
    }

    GeoService geoService(GeoRepository geoRepository, DtosMappers dtosMappers){
        return new GeoService(geoRepository, dtosMappers, new ArrayList<>());
    }

}






