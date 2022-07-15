package com.twitter.twitterclone.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseModel{
    private Integer id;
    private String tweet;
    private User user;
}
