package com.twitter.twitterclone.enums;

public enum EnumStatus {
    ACTIVE(1),DEACTIVE(0);

    public int value;

    EnumStatus(int value){
        this.value = value;
    }

}
