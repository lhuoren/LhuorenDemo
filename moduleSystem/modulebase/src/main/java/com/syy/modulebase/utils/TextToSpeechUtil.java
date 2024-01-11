package com.syy.modulebase.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Locale;

public class TextToSpeechUtil implements TextToSpeech.OnInitListener {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    // TTS对象
    private TextToSpeech mTextToSpeech;

    private TextToSpeechUtil() {
        initTextToSpeech();
    }

    private static class Holder {
        @SuppressLint("StaticFieldLeak")
        private static TextToSpeechUtil textToSpeechUtil = new TextToSpeechUtil();
    }

    public static TextToSpeechUtil getInstance(Context context) {
        mContext = new WeakReference<>(context).get();
        return Holder.textToSpeechUtil;
    }

    public void initTextToSpeech() {
        // 参数Context,TextToSpeech.OnInitListener

        if (mTextToSpeech == null) {
            mTextToSpeech = new TextToSpeech(mContext, this);
            // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
            mTextToSpeech.setPitch(1.5f);
            // 设置语速
            mTextToSpeech.setSpeechRate(1.0f);
        }

        LogUtils.i("SpeechUtils", "mTextToSpeech:" + mTextToSpeech);
    }

    public void submit(String text) {
        // TODO validate success, do something
        if (mTextToSpeech != null && !mTextToSpeech.isSpeaking()) {
            /*
                TextToSpeech的speak方法有两个重载。
                // 执行朗读的方法
                speak(CharSequence text,int queueMode,Bundle params,String utteranceId);
                // 将朗读的的声音记录成音频文件
                synthesizeToFile(CharSequence text,Bundle params,File file,String utteranceId);
                第二个参数queueMode用于指定发音队列模式，两种模式选择
                （1）TextToSpeech.QUEUE_FLUSH：该模式下在有新任务时候会清除当前语音任务，执行新的语音任务
                （2）TextToSpeech.QUEUE_ADD：该模式下会把新的语音任务放到语音任务之后，
                等前面的语音任务执行完了才会执行新的语音任务
             */
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }

    }

    /**
     * 用来初始化TextToSpeech引擎
     *
     * @param status SUCCESS或ERROR这2个值
     */
    @Override
    public void onInit(int status) {
        LogUtils.i("onInit", "status:" + status);

        LogUtils.i("SpeechUtils", "播报引擎加载——111-" + mTextToSpeech);

        if (mTextToSpeech != null) {
            int isSupportChinese = mTextToSpeech.isLanguageAvailable(Locale.CHINESE);//是否支持中文
            mTextToSpeech.getMaxSpeechInputLength();//最大播报文本长度

            LogUtils.i("SpeechUtils", "播报引擎加载——222" + isSupportChinese);

            if (isSupportChinese == TextToSpeech.LANG_AVAILABLE) {
                int setLanRet = mTextToSpeech.setLanguage(Locale.CHINESE);//设置语言
                int setSpeechRateRet = mTextToSpeech.setSpeechRate(1.0f);//设置语
                int setPitchRet = mTextToSpeech.setPitch(1.0f);//设置音量
                String defaultEngine = mTextToSpeech.getDefaultEngine();//默认引擎
                if (status == TextToSpeech.SUCCESS) {
                    // setLanguage设置语言
                    int result = mTextToSpeech.setLanguage(Locale.CHINA);
                    // TextToSpeech.LANG_MISSING_DATA：表示语言的数据丢失
                    // TextToSpeech.LANG_NOT_SUPPORTED：不支持
                    //初始化TextToSpeech引擎成功，初始化成功后才可以play等
                    LogUtils.i("SpeechUtils", "播报引擎加载成功");
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(mContext, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
                    } else {
                        LogUtils.i("SpeechUtils99", "播报引擎加载失败");
                    }
                }
            }
        } else {
            //初始化TextToSpeech引擎失败
            LogUtils.i("SpeechUtils333", "播报引擎加载失败");
            try {
                LogUtils.d("textToSpeech", "status-1:" + status);
                Intent checkIntent = new Intent();
                checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                ((Activity) mContext).startActivityForResult(checkIntent, 0);
            } catch (ActivityNotFoundException exception) {
                LogUtils.d("textToSpeech", "status-2:" + status);
                Uri uri = Uri.parse("market://details?id=" + "com.google.android.tts&hl=fr");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    mContext.startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + "com.google.android.tts&hl=fr")));
                }
            }
        }
    }

    public void textToSpeechStop() {
        // 不管是否正在朗读TTS都被打断
        mTextToSpeech.stop();
        // 关闭，释放资源
        mTextToSpeech.shutdown();
    }

    public void textToSpeechShutdown() {
        if (mTextToSpeech != null) {
            mTextToSpeech.stop();
            mTextToSpeech.shutdown();
            mTextToSpeech = null;
        }
    }

}
