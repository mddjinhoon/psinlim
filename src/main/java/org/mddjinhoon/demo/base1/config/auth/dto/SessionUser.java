package org.mddjinhoon.demo.base1.config.auth.dto;

import org.mddjinhoon.demo.base1.domain.user.User;

import java.io.Serializable;

public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
