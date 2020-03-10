package codetally.validator;

import codetally.model.Charge;
import codetally.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ChargeValidator implements Validator {
    @Autowired
    private ChargeService chargeService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Charge.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Charge charge = (Charge) o;
        //TODO: something

    }
}
