package com.socket.minamanager.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.socket.minamanager.manger.ConnectionManager;
import com.socket.minamanager.util.ConnectionConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MinaService extends Service {

    private ConnectionThread thread;
    private String configMap;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        configMap = intent.getStringExtra("configMap");

        //全局context 避免内存泄漏
        thread = new ConnectionThread("mina", getApplicationContext());
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        configMap = intent.getStringExtra("configMap");

        //全局context 避免内存泄漏
        thread = new ConnectionThread("mina", getApplicationContext());
        thread.start();
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.disConnection();
        thread = null;
    }


    //负责调用ConnectionManager类来完成与服务器连接
    class ConnectionThread extends HandlerThread {

        private Context context;

        boolean isConnection;

        ConnectionManager mManager;

        ConnectionConfig config;


        public ConnectionThread(String name, Context context) {
            super(name);
            this.context = context;

            Map<String,String> configJsonMap = new Gson().fromJson(configMap, new TypeToken<HashMap<String,String>>(){}.getType());

            config = new ConnectionConfig.Builder(context)
                    .setIp(Objects.requireNonNull(configJsonMap.get("ip")))
                    .setPort(Integer.parseInt(Objects.requireNonNull(configJsonMap.get("port"))) )
                    .setReadBuilder(10240)
                    .setConnectionTimeout(50000)
                    .setHeartRequestInterval(5 * 60)
                    .setHeartRequestTimeout(5000)
                    .builder();

            mManager = new ConnectionManager(config);
        }

        //run 开始连接服务器
        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();

            //死循环
            for (; ; ) {
                isConnection = mManager.connection();
                if (isConnection) {
                    break;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        //断开连接
        public void disConnection() {

            mManager.disConnect();
        }
    }
}
