package com.twitter.twitterclone.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Request extends BaseModel{
    private Integer id;
    private User sender;
    private User receiver;
}
