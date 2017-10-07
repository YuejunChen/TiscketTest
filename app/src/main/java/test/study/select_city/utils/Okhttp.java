package test.study.select_city.utils;

import java.io.File;

import okhttp3.Cache;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Mr.Chen on 2017/8/10.
 */
public class Okhttp {
    public static synchronized String LoginWithPost(String username,String password){
        String str=null;
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("account.id", username)
                    .add("account.password", password)
                    .build();
            Request request = new Request.Builder()
                    .url("http://10.109.9.17:8080/test/servlet")
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            str=response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return str;
        }
    }

    public static synchronized String FutOrderWithPost(String userName){
        String str=null;
        try {
            OkHttpClient client=new OkHttpClient
                    .Builder()
                    .cache(new Cache(new File("cache"),10*1024*1024))
                    .build();
            RequestBody requestBody = new FormBody.Builder()
                    .add("username",userName)
                    .build();
            Request request = new Request.Builder()
                    .url("")
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            str=response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return str;
        }
    }
    public synchronized void DemandWithPost(String start, String end, String time, Callback callback){
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("startStation", start)
                            .add("arriveStation", end)
                            .add("date",time)
                            .build();
                    Request request = new Request.Builder()
                            .url("")
                            .post(requestBody)
                            .build();
                    client.newCall(request).enqueue(callback);
    }

        public static synchronized String TiscketWithPost(String username,String trainnumber){
        String str=null;
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("userName", username)
                    .add("trainNumber", trainnumber)
                    .build();
            Request request = new Request.Builder()
                    .url("")
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            str=response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return str;
        }
    }
}
