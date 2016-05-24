package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessProductAddPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessProductAddView;
import com.aode.buyoapp.R;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.internal.framed.Header;


/**
 * 添加商品界面
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessAddNewProductActivity extends AppCompatActivity implements IBusinessProductAddView {
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;//
    private Bitmap bitmap;
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    private File picture;

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ArrayList<String> mSelectPath;
    public static final int REQUEST_IMAGE = 2;
    public String imgUrl;

    EditText et_title;
    EditText et_size;
    EditText et_price;
    EditText et_stock;
    EditText et_color;
    EditText et_parttern;
    ImageView iv_prodct_image;
    private Cloth cloth;
    BusinessProductAddPresenter businessProductAddPresenter = new BusinessProductAddPresenter(this);


    //完成按钮
    Button button;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_add_product_content);

        et_title = (EditText) findViewById(R.id.et_title);
        et_size = (EditText) findViewById(R.id.et_size);
        et_price = (EditText) findViewById(R.id.et_price);
        et_stock = (EditText) findViewById(R.id.et_stock);
        et_color = (EditText) findViewById(R.id.et_color);
        et_parttern = (EditText) findViewById(R.id.et_parttern);
        //上传图片
        this.iv_prodct_image = (ImageView) findViewById(R.id.iv_prodct_image_add);


        //步骤一：添加一个FragmentTransaction的实例
        button = (Button) findViewById(R.id.btn_right_add);
        //步骤二：用add()方法加上Fragment的对象rightFragment
        toolbar = (Toolbar) findViewById(R.id.toolbar_business_product_details_add);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标

        //完成按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cloth = new Cloth();
                    cloth.setTitle(et_title.getText().toString().trim());
                    cloth.setSize(et_size.getText().toString().trim());
                    cloth.setPrice(Double.valueOf(et_price.getText().toString().trim()));
                    cloth.setStock(Long.valueOf(et_stock.getText().toString().trim()));
                    cloth.setColor(et_color.getText().toString().trim());
                    cloth.setPattern(et_parttern.getText().toString().trim());
                    cloth.setbId(Home_business.business.getId());
                    businessProductAddPresenter.ProductAdd();
                } catch (Exception e) {
                    e.printStackTrace();
                   Toast.makeText(getApplicationContext(),"请把商品信息填充完整",Toast.LENGTH_SHORT).show();
                }
            }
        });


        iv_prodct_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BusinessAddNewProductActivity.this);
                builder.setIcon(R.drawable.acq);
                builder.setTitle("请选择上传图片方式");
                //    指定下拉列表的显示数据
                final String[] cities = {"本地相册上传", "拍照上传"};
                //    设置一个下拉的列表选择项
                builder.setItems(cities, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (cities[which]) {
                            case "本地相册上传":
                                gallery();
                                break;
                            case "拍照上传":
                                camera();
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.show();
            }
        });

    }


    /*
     * 上传图片
     */
    public void upload() {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            byte[] buffer = out.toByteArray();

            byte[] encode = Base64.encode(buffer, Base64.DEFAULT);
            String photo = new String(encode);

          /*  RequestParams params = new RequestParams();
            params.put("photo", photo);
            String url = "http://110.65.99.66:8080/jerry/UploadImgServlet";

            AsyncHttpClient client = new AsyncHttpClient();
            client.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers,
                                      byte[] responseBody) {
                    try {
                        if (statusCode == 200) {

                            Toast.makeText(BusinessAddNewProductActivity.this, "头像上传成功!", 0)
                                    .show();
                        } else {
                            Toast.makeText(BusinessAddNewProductActivity.this,
                                    "网络访问异常，错误码：" + statusCode, 0).show();

                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers,
                                      byte[] responseBody, Throwable error) {
                    Toast.makeText(BusinessAddNewProductActivity.this,
                            "网络访问异常，错误码  > " + statusCode, 0).show();

                }
            });*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 从相册获取
     */
    public void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /*
     * 从相机获取
     */
    public void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (hasSdcard()) {
                tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
                picture = tempFile;
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(BusinessAddNewProductActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            try {
                bitmap = data.getParcelableExtra("data");
                this.iv_prodct_image.setImageBitmap(bitmap);
                /*boolean delete = tempFile.delete();
                System.out.println("delete = " + delete);*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 将bitmap转化成file格式
     *
     * @param bitmap
     * @return
     */
    public File saveBitmapFile(Bitmap bitmap) {
        if(file!=null){
            file.delete();
        }
        //将要保存图片的路径
        file = new File(Environment.getExternalStorageDirectory(),PHOTO_FILE_NAME);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();

        } catch (IOException e) {
            System.out.println("没有该图片文件，需要创建");
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 剪切图片
     */
    private void crop(Uri uri) {
        try {
            // 裁剪图片意图
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            // 裁剪框的比例，1：1
            intent.putExtra("aspectX", 0.6);
            intent.putExtra("aspectY", 1);
            // 裁剪后输出图片的尺寸大小
        /*intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);*/
            // 图片格式
            intent.putExtra("outputFormat", "JPEG");
            intent.putExtra("noFaceDetection", true);// 取消人脸识别
            intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
            startActivityForResult(intent, PHOTO_REQUEST_CUT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Cloth getProduct() {
        return cloth;
    }

    @Override
    public File getPicture() {
        picture = saveBitmapFile(bitmap);
        return picture;
    }
    @Override
    public void toMainActivity() {
        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplicationContext(), "添加失败", Toast.LENGTH_SHORT).show();
        finish();
    }
}


