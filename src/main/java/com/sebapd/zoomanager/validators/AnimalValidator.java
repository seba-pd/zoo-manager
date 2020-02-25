package com.sebapd.zoomanager.validators;

import com.sebapd.zoomanager.model.Animal;
import com.sebapd.zoomanager.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class  AnimalValidator implements Validator {
    
    @Autowired
    private AnimalService animalService;
    
    @Override
    public boolean supports(Class<?> aClass) {
        return Animal.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        Animal animal = (Animal) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"animalName","NotEmpty");
        if(animal.getAnimalName().length() >32)
            errors.rejectValue("animalName", "Size.animalForm.animalName");
        
        if(animalService.findByAnimalName(animal.getAnimalName()) != null)
            errors.rejectValue("animalName", "Duplicate.animalForm.animalName");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "animalDescription", "NotEmpty");
    }
}
