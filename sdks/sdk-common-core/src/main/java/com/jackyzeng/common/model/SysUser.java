package com.jackyzeng.common.model;

import lombok.Data;

@Data
public class SysUser extends BaseModel {

    private String username;

    private String password;

    private String mobile;

    private String roles;

}
