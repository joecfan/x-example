package com.example.common.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author: zhuhui bao
 * @date: 15:28 2020/8/13
 **/
public class ParamValidateUtil {

    public static void main(String[] args) {
        ParamValidateVo vo =  new ParamValidateVo();
        //vo.setAa("xxxx");
        ParamValidateUtil.validate(vo);
    }

    public static <T> void validate(T obj) {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<T>> cvSet = validator.validate(obj);
        for (ConstraintViolation<T> cv : cvSet) {

            System.err.println(cv.getRootBean().getClass().getName() + "类的"
                    + cv.getPropertyPath() + "属性 -> " + cv.getMessage());

        }
    }
}
