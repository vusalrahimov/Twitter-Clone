package com.twitter.twitterclone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Followings extends BaseModel{
    private Integer id;
    private User sender;
    private User receiver;
}
