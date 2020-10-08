package com.example.common.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: zhuhui bao
 * @date: 15:28 2020/8/13
 **/
public class ParamValidateUtil {

    public static void main(String[] args) {

        BigDecimal bg = new BigDecimal("102.0");
        //bg = bg.setScale(0, BigDecimal.ROUND_DOWN);
        System.out.println(bg);

        ParamValidateVo vo =  new ParamValidateVo();
        vo.setAa("xxxx");
        vo.setBb(" ");
        vo.setAmt(BigDecimal.TEN);
        //vo.setSubParam1("sss");
        SubParamValidateVo subParamValidateVo = new SubParamValidateVo();
        //ParamValidateUtil.validate(subParamValidateVo);

        List<SubParamValidateVo> subParamValidateVoList = new ArrayList<>();
        ParamValidateUtil.validate(subParamValidateVoList);
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
