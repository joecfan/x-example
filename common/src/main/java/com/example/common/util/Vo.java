package com.example.common.util;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author: zhuhui bao
 * @date: 16:25 2020/8/7
 **/
@Data
public class Vo {

    @NotEmpty(message = "AA")
    private String aa;
    @NotEmpty(message = "BB")
    private int bb;
    @NotEmpty(message = "CC")
    private String cc;

    private List<Vo2> vo2List;


}
