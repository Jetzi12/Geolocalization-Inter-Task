package com.companyCo.Geolocalization.DataHandling;

import com.companyCo.Geolocalization.Model.Localization;
import java.util.HashMap;
import java.util.List;

public class DataSupervisor {
    StringBuilder recordsInString = new StringBuilder();
    HashMap<String,String> recordsWithDiffDeviceId = new HashMap<>();

    public HashMap<String,String> checkIfListIsFull(List<Localization> listOfRecords){
        if(listOfRecords.size() >= 10){
            recordsWithDiffDeviceId.clear();
            for (Localization record : listOfRecords) {
                if(!recordsWithDiffDeviceId.containsKey(record.getDeviceId())){
                    recordsWithDiffDeviceId.put(record.getDeviceId(),record.toStringToDB());
                } else {
                    recordsInString.append(recordsWithDiffDeviceId.get(record.getDeviceId()));
                    recordsInString.append("/");
                    recordsInString.append(record.toStringToDB());
                    recordsWithDiffDeviceId.replace(record.getDeviceId(),recordsInString.toString());
                    recordsInString.setLength(0);
                }
            }
        }
        return recordsWithDiffDeviceId;
    }

}
