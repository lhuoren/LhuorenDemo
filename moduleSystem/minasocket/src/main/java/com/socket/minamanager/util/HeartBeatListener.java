package com.socket.minamanager.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.socket.minamanager.service.MinaService;

import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 监听服务器断线原因
 */

public class HeartBeatListener implements IoServiceListener {

    private Context mContext;

    public HeartBeatListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void serviceActivated(IoService arg0) throws Exception {
    }

    @Override
    public void serviceDeactivated(IoService arg0) throws Exception {
    }

    @Override
    public void serviceIdle(IoService arg0, IdleStatus arg1) throws Exception {
    }

    @Override
    public void sessionClosed(IoSession arg0) throws Exception {
        Log.d("", "hahahaha");
    }

    @Override
    public void sessionCreated(IoSession arg0) throws Exception {
    }

    @Override
    public void sessionDestroyed(IoSession arg0) {
        //连接断开重连
//        mContext.startService(new Intent(mContext, MinaService.class));
    }

}
