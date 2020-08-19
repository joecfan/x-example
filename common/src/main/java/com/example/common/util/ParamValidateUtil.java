package com.example.common.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

/**
 * @author: zhuhui bao
 * @date: 15:28 2020/8/13
 **/
public class ParamValidateUtil {

    public static void main(String[] args) {
        ParamValidateVo vo =  new ParamValidateVo();
//        vo.setAa("xxxx");
        //vo.setBb(" ");
        //vo.setAmt(BigDecimal.ZERO);
        //vo.setSubParam1("sss");
        ParamValidateUtil.validate(vo);
    }

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T obj) {

        Set<ConstraintViolation<T>> cvSet = validator.validate(obj);
        for (ConstraintViolation<T> cv : cvSet) {

            System.err.println(cv.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName());
            System.err.println(cv.getRootBean().getClass().getName() + "类的"
                    + cv.getPropertyPath() + "属性 -> " + cv.getMessage());

        }
    }
}
