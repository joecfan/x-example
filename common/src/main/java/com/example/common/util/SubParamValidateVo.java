package com.example.common.util;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: zhuhui bao
 * @date: 15:54 2020/8/13
 **/
@Data
public class SubParamValidateVo {

    @NotEmpty(message = "subParam1为空")
    private String subParam1;
}
