package com.lct.bean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("用户实体")
public class User implements Serializable {

    @ApiModelProperty("唯一标志")
    private Long id;
    @ApiModelProperty("用户名")
    private String name;
}
