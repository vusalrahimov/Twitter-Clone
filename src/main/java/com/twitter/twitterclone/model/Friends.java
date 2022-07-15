package com.twitter.twitterclone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Friends extends BaseModel{
    private Integer id;
    private User follower;
    private User followings;
}
