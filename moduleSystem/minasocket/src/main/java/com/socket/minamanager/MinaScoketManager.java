package com.socket.minamanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.socket.minamanager.service.MinaService;

public class MinaScoketManager {

    private ServiceConnection connBackSync = new ServiceConnection() {
        public void onServiceConnected(ComponentName var1, IBinder var2) {
        }

        public void onServiceDisconnected(ComponentName var1) {
        }
    };

    private static Context mContext;

    private MinaScoketManager() {
    }

    private static class Holder {
        private static MinaScoketManager infraredCameraControlUtil = new MinaScoketManager();
    }

    public static MinaScoketManager getInstance(Context context) {
        mContext = context;
        return Holder.infraredCameraControlUtil;
    }

    public void startConnection(String configMap) {
        Intent intent = new Intent(mContext, MinaService.class);
        intent.putExtra("configMap", configMap);
        mContext.getApplicationContext().bindService(intent, connBackSync, mContext.BIND_AUTO_CREATE);
    }

    public void stopConnection() {
        mContext.getApplicationContext().unbindService(connBackSync);
    }
}
