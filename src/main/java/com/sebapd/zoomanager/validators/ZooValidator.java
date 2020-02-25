package com.sebapd.zoomanager.validators;

import com.sebapd.zoomanager.model.Zoo;
import com.sebapd.zoomanager.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ZooValidator implements Validator {

    @Autowired
    private ZooService zooService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Zoo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Zoo zoo = (Zoo) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zooName", "NotEmpty");
        if(zoo.getZooName().length() > 32)
            errors.rejectValue("zooName", "Size.zooForm.zooName");

        if(zooService.findByZooName(zoo.getZooName()) !=null)
            errors.rejectValue("zooName","Duplicate.zooform.zooName");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zooDescription", "NotEmpty");


    }
}
