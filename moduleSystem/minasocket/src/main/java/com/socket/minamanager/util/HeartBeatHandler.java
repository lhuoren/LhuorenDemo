package com.socket.minamanager.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.nio.charset.StandardCharsets;

public class HeartBeatHandler extends IoHandlerAdapter {

    private final String TAG = "HeartBeatHandler";

    public static final String BROADCAST_ACTION = "com.commonlibrary.mina.broadcast";
    public static final String MESSAGE = "message";
    private Context mContext;

    public HeartBeatHandler(Context context) {
        this.mContext = context;

    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        Log.e(TAG, ConnectUtils.stringNowTime() + " : 客户端调用exceptionCaught-"+cause.getMessage());
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        Log.e(TAG,  "接收到服务器端消息：" + message.toString());
        if (mContext != null) {
            Intent intent = new Intent(BROADCAST_ACTION);
            String result=new String(message.toString().getBytes(), StandardCharsets.UTF_8);
            intent.putExtra(MESSAGE, result);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        Log.e(TAG, ConnectUtils.stringNowTime() + " : 客户端调用messageSent");
        //        session.close(true);//加上这句话实现短连接的效果，向客户端成功发送数据后断开连接
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        Log.e(TAG, ConnectUtils.stringNowTime() + " : 客户端调用sessionClosed");

    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        Log.e(TAG, ConnectUtils.stringNowTime() + " : 客户端调用sessionCreated");

    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        Log.e(TAG, ConnectUtils.stringNowTime() + " : 客户端调用sessionIdle");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        Log.e(TAG, ConnectUtils.stringNowTime() + " : 客户端调用sessionOpened");
    }
}
