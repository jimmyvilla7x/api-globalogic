package com.globallogic.test.dto;

import com.globallogic.test.model.PhoneModel;
import com.globallogic.test.model.UserModel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PhoneDto {
    private long number;
    private int citycode;
    private String countrycode;
    private UserModel user;


    public PhoneModel createEntity() {
        PhoneModel e = new PhoneModel();
        this.createEntity(e);
        return e;
    }

    public PhoneModel createEntity(PhoneModel e) {
        e.setNumber(this.number);
        e.setCitycode(this.citycode);
        e.setCountrycode(this.countrycode);
        e.setUser(this.user);
        return e;
    }

}

