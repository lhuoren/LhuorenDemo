package com.socket.minamanager.manger;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

import com.socket.minamanager.R;
import com.socket.minamanager.factoty.FrameCodecFactory;
import com.socket.minamanager.factoty.HeartBeatMessageFactory;
import com.socket.minamanager.util.ConnectionConfig;
import com.socket.minamanager.util.HeartBeatHandler;
import com.socket.minamanager.util.HeartBeatListener;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ConnectionManager {

    private ConnectionConfig mConfig;

    private WeakReference<Context> mContext; //避免内存泄漏

    private NioSocketConnector mConnection;

    private IoSession mSession;

    private InetSocketAddress mAddress;

    public ConnectionManager(ConnectionConfig config) {
        this.mConfig = config;
        this.mContext = new WeakReference<>(config.getContext());

        init();
    }

    //通过构建者模式来进行初始化
    private void init() {

        Log.e("mConfig.getIp()", mConfig.getIp() + "," + mConfig.getPort());

        mAddress = new InetSocketAddress(mConfig.getIp(), mConfig.getPort());

        mConnection = new NioSocketConnector();

        mConnection.getSessionConfig().setSendBufferSize(mConfig.getReadBufferSize());

        mConnection.getSessionConfig().setKeepAlive(true);

        //设置心跳包
        KeepAliveFilter heartFilter = new KeepAliveFilter(new HeartBeatMessageFactory());

        heartFilter.setRequestInterval(mConfig.getHeartRequestInterval());

        heartFilter.setRequestTimeout(mConfig.getHeartRequestTimeout());

        mConnection.setConnectTimeoutMillis(mConfig.getConnectionTime());

        //设置 handler 处理业务逻辑
        mConnection.setHandler(new HeartBeatHandler(mContext.get()));

        mConnection.addListener(new HeartBeatListener(mContext.get()));

        //设置协议封装解析处理
        mConnection.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new FrameCodecFactory()));

        //编码过滤
        mConnection.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new ObjectSerializationCodecFactory()));

        //设置ip地址及端口
        mConnection.setDefaultRemoteAddress(mAddress);

        //设置读数据大小
        mConnection.getSessionConfig().setReadBufferSize(mConfig.getReadBufferSize());

        //添加日志过滤
        mConnection.getFilterChain().addLast("Logging", new LoggingFilter());

    }

    //连接方法（外部调用）
    public boolean connection() {



        Log.e("connection", "准备连接");

        try {
            ConnectFuture futrue = mConnection.connect();
            //一直连接，直至成功
            futrue.awaitUninterruptibly();
            mSession = futrue.getSession();

            SessionManager.getInstance().setSeesion(mSession);
            Log.e("connection", "连接成功");
        } catch (Exception e) {
            Log.e("connection", "连接失败:" + e);
            return false;

        }

        return mSession != null;
    }

    //断开连接方法（外部调用）
    public void disConnect() {
        //关闭
        mConnection.dispose();
        //大对象置空
        mConnection = null;
        mSession = null;
        mAddress = null;
        mContext = null;
    }

}
