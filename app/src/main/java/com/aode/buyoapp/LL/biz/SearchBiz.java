package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.Listener.SearchBusinessListener;
import com.aode.buyoapp.LL.Listener.SearchClothListener;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.url;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by LiLei on 2016/6/1.Go.
 */
public class SearchBiz implements ISearchBiz {

    private url url = new url();

    @Override
    public void SearchCloth(String key, String title, final SearchClothListener searchClothListener) {
        abstract class ClothCallback extends Callback<List<Cloth>> {
            @Override
            public List<Cloth> parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                Type listType = new TypeToken<LinkedList<Cloth>>() {
                }.getType();
                List<Cloth> cloths = new Gson().fromJson(string, listType);
                return cloths;
            }
        }
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/search")
                .addParams("key", key)
                .addParams("title", title)
                .build()
                .execute(new ClothCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        searchClothListener.getFailed();
                    }

                    @Override
                    public void onResponse(List<Cloth> response) {
                        searchClothListener.getSuccessCloth(response);
                    }

                    @Override
                    public List<Cloth> parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }
                });
    }


    @Override
    public void SearchBusiness(String key, String title, final SearchBusinessListener searchBusinessListener) {
        abstract class BusinessCallback extends Callback<List<Business>> {
            @Override
            public List<Business> parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                Type listType = new TypeToken<LinkedList<Business>>() {
                }.getType();
                List<Business> businesses = new Gson().fromJson(string, listType);
                return businesses;
            }
        }
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/search")
                .addParams("key", key)
                .addParams("title", title)
                .build()
                .execute(new BusinessCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        searchBusinessListener.getFailed();
                    }

                    @Override
                    public void onResponse(List<Business> response) {
                        searchBusinessListener.getSuccessBusiness(response);
                    }

                    @Override
                    public List<Business> parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }
                });

    }
}
