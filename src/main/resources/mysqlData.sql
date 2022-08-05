CREATE TABLE USERS(
                      ID INT auto_increment,
                      FIRST_NAME varchar(30) not null,
                      LAST_NAME varchar(30),
                      USERNAME varchar(30) unique,
                      PWD varchar(30),
                      ACTIVE_STATUS int default 1,
                      DATA_DATE timestamp default current_timestamp,
                      primary key(ID)
);

CREATE TABLE POST(
                     ID INT auto_increment,
                     TWEET varchar(500) not null,
                     USER_ID int not null,
                     ACTIVE_STATUS int default 1,
                     DATA_DATE timestamp default current_timestamp,
                     primary key(id),
                     constraint fk_post_user_id foreign key(USER_ID) references USERS(ID)
);

CREATE TABLE POST_COMMENT(
                             ID INT auto_increment,
                             POST_COMMENT varchar(300) not null,
                             POST_ID int not null,
                             USER_ID int not null,
                             ACTIVE_STATUS int default 1,
                             DATA_DATE timestamp default current_timestamp,
                             primary key(id),
                             constraint fk_post_comment_user_id foreign key(USER_ID) references USERS(ID),
                             constraint fk_post_comment_post_id foreign key(POST_ID) references POST(ID)
);

CREATE TABLE REQUEST(
                        ID int auto_increment,
                        SENDER_ID int not null,
                        RECEIVER_ID int not null,
                        ACTIVE_STATUS int default 1,
                        DATA_DATE timestamp default current_timestamp,
                        primary key(id),
                        constraint fk_request_sender_id foreign key(SENDER_ID) references USERS(ID),
                        constraint fk_request_receiver_id foreign key(RECEIVER_ID) references USERS(ID)
);

CREATE TABLE FOLLOWING(
                        ID int auto_increment,
                        SENDER_ID int not null,
                        RECEIVER_ID int not null,
                        ACTIVE_STATUS int default 1,
                        DATA_DATE timestamp default current_timestamp,
                        primary key(id),
                        constraint fk_followings_sender_id foreign key(SENDER_ID) references USERS(ID),
                        constraint fk_followings_receiver_id foreign key(RECEIVER_ID) references USERS(ID)
);

CREATE TABLE FOLLOWER(
                           ID int auto_increment,
                           SENDER_ID int not null,
                           RECEIVER_ID int not null,
                           ACTIVE_STATUS int default 1,
                           DATA_DATE timestamp default current_timestamp,
                           primary key(id),
                           constraint fk_follower_sender_id foreign key(SENDER_ID) references USERS(ID),
                           constraint fk_follower_receiver_id foreign key(RECEIVER_ID) references USERS(ID)
);