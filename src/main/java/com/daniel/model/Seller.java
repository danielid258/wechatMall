package com.daniel.model;

import lombok.Data;

import javax.persistence.Id;

/**
 * on 6/6/2018.
 */
@Data
public class Seller {
    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
