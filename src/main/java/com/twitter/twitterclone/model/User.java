package com.twitter.twitterclone.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseModel{
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
}
