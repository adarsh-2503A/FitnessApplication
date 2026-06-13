package com.adi.gateway;

import lombok.Data;

@Data
public class UserDTO {

    private String id;

    private String keyCloakId;

    private String email;

    private String password;

    private String firstName;

    private String lastName;
}
