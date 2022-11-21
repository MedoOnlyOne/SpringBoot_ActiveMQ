package com.example.artemisconsumer.models;

import java.io.Serializable;

public class MsgRqHdr implements Serializable {
    Msg Msg;

    public Msg getMsg() {
        return Msg;
    }

    public void setMsg(Msg msg) {
        Msg = msg;
    }
}
