package com.aode.buyoapp.qinxiaoshou.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.LL.url;
import com.aode.buyoapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huizhou on 2016/5/25.
 */
public class ImageLoader {
    private List<Cloth> cloths;
    private int position;
    private ImageView picture;
    private Bitmap bitmap;
    private Cloth cloth;

    public ImageLoader(List<Cloth> cloths, int position, ImageView picture){
        this.cloth = cloths.get(position);
        this.position = position;
        this.picture = picture;
    }
    public ImageLoader(Cloth cloth,ImageView picture){
        this.cloth = cloth;
        this.picture = picture;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String msgText = bundle.getString("changpicture");
            if("changpicture".equals(msgText)){
                picture.setImageBitmap(bitmap);
                bitmap=null;
                try {
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    };


    //单张图片下载步骤
    public void loadAndRefreshPicture(){
        //加载图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(new url().getUrl() + "/tb/resources/file/cloth/" + cloth.getPicture()).build();
                    Response response = client.newCall(request).execute();
                    InputStream is = response.body().byteStream();
                    Bitmap bm = BitmapFactory.decodeStream(is);

                    bitmap = bm;
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("changpicture", "changpicture");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (NullPointerException e){
                    picture.setImageResource(R.drawable.no_picture);
                    e.printStackTrace();
                }
            }
        }).start();

    }


  //继续
    public void resume(){
        //加载图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(new url().getUrl() + "/tb/resources/file/cloth/" + cloth.getPicture()).build();
                    Response response = client.newCall(request).execute();
                    InputStream is = response.body().byteStream();
                    Bitmap bm = BitmapFactory.decodeStream(is);
                    bitmap = bm;
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("changpicture", "changpicture");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (NullPointerException e){
                    picture.setImageResource(R.drawable.cheese_3);
                    e.printStackTrace();
                }
            }
        }).start();

    }
   //暂停
    public void pause(){

    }
}
