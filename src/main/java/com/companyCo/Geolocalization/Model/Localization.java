package com.companyCo.Geolocalization.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Localization {

    private String deviceId;
    private Long latitude;
    private Long longitude;

    @Override
    public String toString() {
        return "Lokalizacja{" +
                "deviceId='" + deviceId + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public String toStringToDB() {
        return latitude.toString() + "," + longitude.toString();
    }
}
