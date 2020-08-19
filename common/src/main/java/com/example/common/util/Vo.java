package com.example.common.util;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

/**
 * @author: zhuhui bao
 * @date: 16:25 2020/8/7
 **/
//@Data
public class Vo {

    @NotEmpty(message = "AA")
    private String aa;
    @NotEmpty(message = "BB")
    private String bb;

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }
}
