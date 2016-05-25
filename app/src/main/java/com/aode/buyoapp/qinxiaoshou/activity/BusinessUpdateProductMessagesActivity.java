package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.BusinessProductChangePresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessProductChangeView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.view.ImageLoader;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 修改商品信息
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessUpdateProductMessagesActivity extends FragmentActivity implements IBusinessProductChangeView {
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;//
    private File file;
    private Bitmap bitmap;
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    private File picture;


    private Toolbar toolbar;
    private TextView toolbarTitle;
    private RadioGroup rg_h;
    private Cloth cloth;
    private Button button;
    BusinessProductChangePresenter businessProductChangePresenter = new BusinessProductChangePresenter(this);


    private EditText et_title;
    private EditText et_size;
    private EditText et_price;
    private EditText et_stock;
    private EditText et_color;
    private EditText et_parttern;
    private Cloth cloth2;
    private ImageView iv_prodct_image_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_update_product_content);
        //获取前面页面传递过来的数据
        Intent intent = this.getIntent();
        cloth = (Cloth) intent.getSerializableExtra("cloth");
        button = (Button) findViewById(R.id.btn_right_update);

        et_title = (EditText) findViewById(R.id.et_title);
        et_size = (EditText) findViewById(R.id.et_size);
        et_price = (EditText) findViewById(R.id.et_price);
        et_stock = (EditText) findViewById(R.id.et_stock);
        et_color = (EditText) findViewById(R.id.et_color);
        et_parttern = (EditText) findViewById(R.id.et_parttern);
        iv_prodct_image_update = (ImageView) findViewById(R.id.iv_prodct_image_update);

        toolbarTitle = (TextView) findViewById(R.id.tv_g_update_product_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar_business_product_details_update);
        rg_h = (RadioGroup) findViewById(R.id.rg_h_open_permission);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rg_h.setVisibility(View.INVISIBLE);
        toolbarTitle.setText("修改商品信息");

        new ImageLoader(cloth,iv_prodct_image_update).loadAndRefreshPicture();

        iv_prodct_image_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BusinessUpdateProductMessagesActivity.this);
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


        et_title.setText(cloth.getTitle());
        et_size.setText(cloth.getSize());
        et_price.setText(cloth.getPrice() + "");
        et_stock.setText(cloth.getStock() + "");
        et_color.setText(cloth.getColor());
        et_parttern.setText(cloth.getPattern());
        //完成按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cloth2 = new Cloth();
                    Long id = cloth.getId();
                    cloth2.setId(id);
                    cloth2.setTitle(et_title.getText().toString().trim());
                    cloth2.setSize(et_size.getText().toString().trim());
                    cloth2.setPrice(Double.valueOf(et_price.getText().toString().trim()));
                    cloth2.setStock(Long.valueOf(et_stock.getText().toString().trim()));
                    cloth2.setColor(et_color.getText().toString().trim());
                    cloth2.setPattern(et_parttern.getText().toString().trim());
                    cloth2.setbId(cloth.getbId());
                    cloth2.setPicture(cloth.getPicture());
                    businessProductChangePresenter.ProductChange();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "请填写完整布匹信息", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public Cloth getProduct() {
        return cloth2;
    }

    @Override
    public File getPicture() {
        picture = saveBitmapFile(bitmap);
        return picture;
    }

    @Override
    public void toMainActivity() {
        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplicationContext(), "修改失败", Toast.LENGTH_SHORT).show();
        finish();
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
                Toast.makeText(BusinessUpdateProductMessagesActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            try {
                bitmap = data.getParcelableExtra("data");
                iv_prodct_image_update.setImageBitmap(bitmap);
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
            bitmap.compress(Bitmap.CompressFormat.PNG,100, bos);
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
            // 裁剪框的比例，0.6：1
            intent.putExtra("aspectX", 0.8);
            intent.putExtra("aspectY", 1);
            // 裁剪后输出图片的尺寸大小
       /* intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 400);*/
            // 图片格式
            intent.putExtra("outputFormat", "PNG");
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

}
