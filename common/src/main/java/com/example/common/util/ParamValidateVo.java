package com.example.common.util;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * @author: zhuhui bao
 * @date: 15:43 2020/8/13
 **/
@Data
public class ParamValidateVo extends SubParamValidateVo{

    @NotEmpty(message = "AA为空")
    private String aa;

    @NotNull(message = "BB为空")
    private String bb;

    @NotNull(message = "金额为空")
    @Positive(message = "金额要大于0")
    private BigDecimal amt;

    //private SubParamValidateVo subParamValidateVo;
}
