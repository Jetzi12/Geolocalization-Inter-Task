package com.companyCo.Geolocalization.Model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Users {
    @Id
    @SequenceGenerator(
            name = "Geo_Sequence",
            sequenceName = "Geo_Sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Geo_Sequence"
    )
    private Integer id;
    private String userId;
    private String name;
    private String email;
    private String deviceId;
    private String records;

    public Users() {
    }

    public Users(String userId, String name, String email, String deviceId) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", records='" + records + '\'' +
                '}';
    }

}
