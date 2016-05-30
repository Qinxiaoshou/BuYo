package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.Listener.RecruitsAddListener;
import com.aode.buyoapp.LL.Listener.RecruitsDetailListener;
import com.aode.buyoapp.LL.Listener.RecruitsListener;
import com.aode.buyoapp.LL.bean.Recruit;
import com.aode.buyoapp.LL.url;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by LiLei on 2016/5/24.Go.
 */
public class RecruitsBiz implements IRecruitsBiz {

    private url url = new url();

    @Override
    public void recruitsList(final RecruitsListener recruitsListener) {


        abstract class RecruitsCallback extends Callback<List<Recruit>> {
            @Override
            public List<Recruit> parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                Type listType = new TypeToken<List<Recruit>>() {
                }.getType();
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .create();
                List<Recruit> recruits = gson.fromJson(string, listType);
                return recruits;
            }
        }
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/recruit/list")
                .build()
                .execute(new RecruitsCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println("错误:" + e);
                        recruitsListener.getFailed();
                    }

                    @Override
                    public void onResponse(List<Recruit> response) {
                        recruitsListener.getSuccess(response);
                    }

                    @Override
                    public List<Recruit> parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }
                });
    }

    @Override
    public void recruitsDetail(Recruit id, final RecruitsDetailListener recruitsDetailListener) {
        String json = new Gson().toJson(id);


        abstract class RecruitCallback extends Callback<Recruit> {
            @Override
            public Recruit parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                Type type = new TypeToken<Recruit>() {
                }.getType();
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .create();
                Recruit recruit = gson.fromJson(string, Recruit.class);
                return recruit;
            }
        }

        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/recruit/get")
                .addParams("recruitStr", json)
                .build()
                .execute(new RecruitCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        recruitsDetailListener.getFailed();
                        System.out.println("错误:" + e);
                    }

                    @Override
                    public void onResponse(Recruit response) {
                        recruitsDetailListener.getSuccess(response);
                    }

                    @Override
                    public Recruit parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }
                });
    }

    @Override
    public void recruitsAdd(Recruit recruit, final RecruitsAddListener recruitsAddListener) {

        String json = new Gson().toJson(recruit);

        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/recruit/add")
                .addParams("recruitStr", json)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        recruitsAddListener.getFailed();
                        System.out.println("错误:" + e);
                    }

                    @Override
                    public void onResponse(Object response) {
                        recruitsAddListener.getSuccess();
                    }
                });
    }
}
