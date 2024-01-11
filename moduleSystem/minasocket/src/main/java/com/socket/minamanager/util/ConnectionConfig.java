package com.socket.minamanager.util;

import android.content.Context;

public class ConnectionConfig {

    private Context context;

    private String ip;

    private int port;

    private int readBufferSize;

    private long connectionTime;

    private int heartRequestInterval;

    private int heartRequestTimeout;

    public Context getContext() {
        return context;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public int getReadBufferSize() {
        return readBufferSize;
    }

    public long getConnectionTime() {
        return connectionTime;
    }

    public int getHeartRequestInterval() {
        return heartRequestInterval;
    }

    public int getHeartRequestTimeout() {
        return heartRequestTimeout;
    }

    public static class Builder{

        private Context context;

        private String ip = "192.168.0.1";

        private int port = 9288;

        private int readBufferSize = 10240;

        private long connectionTimeout = 50000;

        private int heartRequestInterval = 5 * 60;

        private int heartRequestTimeout = 10;

        public Builder(Context context){

            this.context = context;

        }

        public Builder setIp(String ip){
            this.ip = ip;
            return this;
        }

        public Builder setPort(int port){
            this.port = port;
            return  this;
        }

        public Builder setReadBuilder(int size){
            this.readBufferSize = size;
            return this;
        }

        public Builder setConnectionTimeout(int time){
            this.connectionTimeout = time;
            return this;
        }

        public Builder setHeartRequestInterval(int heartRequestInterval) {
            this.heartRequestInterval = heartRequestInterval;
            return this;
        }

        public Builder setHeartRequestTimeout(int heartRequestTimeout) {
            this.heartRequestTimeout = heartRequestTimeout;
            return this;
        }


        private void applyConfig(ConnectionConfig config){
            config.context = this.context;
            config.ip = this.ip;
            config.port = this.port;
            config.readBufferSize = this.readBufferSize;
            config.connectionTime = this.connectionTimeout;
            config.heartRequestInterval = this.heartRequestInterval;
            config.heartRequestTimeout = this.heartRequestTimeout;

        }

        public ConnectionConfig builder(){

            ConnectionConfig config = new ConnectionConfig();
            applyConfig(config);

            return config;
        }

    }
}
