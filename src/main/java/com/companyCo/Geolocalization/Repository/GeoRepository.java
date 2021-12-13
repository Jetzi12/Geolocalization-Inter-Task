package com.companyCo.Geolocalization.Repository;

import com.companyCo.Geolocalization.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GeoRepository extends JpaRepository<Users,Integer> {
    @Query("SELECT s FROM Users s WHERE s.deviceId = ?1")
    Optional<Users> findUserWithDeviceId(String deviceId);
    @Query("SELECT s FROM Users s WHERE s.email = ?1")
    Optional<Users> findUserWithEmail(String email);


}
