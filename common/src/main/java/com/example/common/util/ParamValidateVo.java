package com.example.common.util;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: zhuhui bao
 * @date: 15:43 2020/8/13
 **/
@Data
public class ParamValidateVo {

    @NotEmpty(message = "AA为空")
    private String aa;

    @NotEmpty(message = "BB为空")
    private String bb;
}
