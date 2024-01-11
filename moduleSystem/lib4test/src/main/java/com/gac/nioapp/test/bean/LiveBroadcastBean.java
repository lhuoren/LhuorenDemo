package com.gac.nioapp.test.bean;


public class LiveBroadcastBean {


    /**
     * roomId : 1
     */

    private String roomId;
    private int type;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
