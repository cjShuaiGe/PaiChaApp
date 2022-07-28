package com.example.paichaapp.Util;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.paichaapp.LiveData.MyLiveData;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class Util {

   static OkHttpClient client = new OkHttpClient.Builder()
            .retryOnConnectionFailure(true)//允许失败重试
            .readTimeout(5, TimeUnit.SECONDS)//设置读取超时时间
            .writeTimeout(5, TimeUnit.SECONDS)//设置写的超时时间
            .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
            .build();
    static String url = "ws://39.98.41.126:31102/";
   static Request request = new Request.Builder().url(url).build();
    static final WebSocket webSocket = client.newWebSocket(request, new WebSocketListener() {
        @SuppressLint("LongLogTag")
        @Override
        public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
            super.onClosed(webSocket, code, reason);

        }

        @Override
        public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
            super.onClosing(webSocket, code, reason);
        }

        @SuppressLint("LongLogTag")
        @Override
        public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {

            super.onFailure(webSocket, t, response);
        }

        @SuppressLint("LongLogTag")
        @Override
        public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
            super.onMessage(webSocket, text);
           MyLiveData.getMessageData().postValue(text);
        }

        @Override
        public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @SuppressLint("LongLogTag")
        @Override
        public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
            super.onOpen(webSocket, response);
        }
    });

    public static WebSocket getWebSocket() {
        return webSocket;
    }

    public static void sendMessage(String s){

        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5,1,TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100));
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                getWebSocket().send(s);
            }
        });


    }
}
