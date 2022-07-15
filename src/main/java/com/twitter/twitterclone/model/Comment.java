package com.twitter.twitterclone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseModel{
    private Integer id;
    private String postComment;
    private User user;
    private Post post;
}
