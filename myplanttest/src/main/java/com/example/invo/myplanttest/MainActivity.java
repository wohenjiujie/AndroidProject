package com.example.invo.myplanttest;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.bumptech.glide.Glide;
import com.example.invo.myplanttest.services.AuthService;
import com.example.invo.myplanttest.util.BitmapUtil;
import com.example.invo.myplanttest.util.ImageUtil;
import com.example.invo.myplanttest.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    private TextView mTitle;
    private ImageView mImage,mChooseLocalImage;
    private Button mCaptureImage;
    private View mLine;

    public static final String APP_ID = "10675026";
    public static final String API_KEY = "1iMR3Il9PL4KzUHbpyhshCix";
    public static final String SECRET_KEY = "b7ncbQ67peyij7bf7qomnaBiieakmf31";

    public static final String FACE_APP_ID = "10734368";
    public static final String FACE_API_KEY = "6cvleSFbyRIRHzhijfYrHZFj";
    public static final String FACE_SECRET_KEY = "SDnCUfrtH0lgrK01HgTe2ZRLNsmCx5xy";

//    10734368","6cvleSFbyRIRHzhijfYrHZFj","SDnCUfrtH0lgrK01HgTe2ZRLNsmCx5xy

    private Uri mUri;
    private String imagePath = null;
    private Bitmap mBitmap = null;//待处理的位图，默认为空
    private TranslateAnimation lineAnimation;
    private int selection = 0;
    private byte[] arrays;
    private JSONObject json;
    private String face_result = null, face_age = null, face_gender = null, face_race = null, face_beauty = null, face_expression = null;
    private Boolean tag=false;


    private static final int PLANT = 1, ANIMAL = 2, DISHES = 3, CAR = 4, FACE = 5;
    private static final int ACTION_CHOOSE_IMAGE = 0x201;//访问本地图片的请求码
    private static final int REQUEST_CODE_TAKE_PICTURE = 0x202;//访问相机的请求码
    BitmapUtil mBu = new BitmapUtil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        findView();
        controlProcess();
        imageProcess();
//        captureProcess();

//        String tokenAccess = AuthService.getAuth("vexyAVviNYi4MFzsHjGjUd9R", "8gL8FKTIEFQLs7jDxvxKvvydWGmoHLfY");
//        Log.d("MainActivity", tokenAccess);

        /*mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*读取本地照片*//*
                Intent chooseImage = new Intent(Intent.ACTION_PICK);
                chooseImage.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                if (chooseImage.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(chooseImage, ACTION_CHOOSE_IMAGE);
                } else {
                    Toast.makeText(MainActivity.this, "访问图库失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(capture, REQUEST_CODE_TAKE_PICTURE);
            }
        });*/


    }
    public void resizePhoto(){
        if(imagePath!=null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            if (BuildConfig.DEBUG) Log.d("MainActivity", "mBitmap:" + mBitmap);
            options.inJustDecodeBounds = true;//返回图片宽高信息
            BitmapFactory.decodeFile(imagePath, options);
            //让图片小于1024
            double radio = Math.max(options.outWidth * 1.0d / 1024f, options.outHeight * 1.0d / 1024f);
            options.inSampleSize = (int) Math.ceil(radio);//向上取整倍数
            options.inJustDecodeBounds = false;//显示图片
            mBitmap = BitmapFactory.decodeFile(imagePath, options);
        }
    }
    public void handle_photo(){
        //调整宽高比例，便于imageView控件显示
        resizePhoto();
        //调整角度，防止图片在某些手机（如三星）显示时歪斜
        if(imagePath!=null) {
            int degree = getPicRotate(imagePath);
            Matrix m = new Matrix();
            m.setRotate(degree);
            mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), m, true);
        }
        //质量压缩图片
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);//此时并不是压缩，而是把压缩的数据存入bytearrayOutputStream
        int quality=100;
        while(byteArrayOutputStream.toByteArray().length/1024>4000){//小于4M
            quality-=10;
            mBitmap.compress(Bitmap.CompressFormat.JPEG,quality,byteArrayOutputStream);
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG,quality,byteArrayOutputStream);
        arrays=byteArrayOutputStream.toByteArray();
        mBu.setBitmap(mBitmap);
    }


    public int getPicRotate(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /*
     * 寻找控件
     * */
    void findView() {
        mTitle = findViewById(R.id.title);
        mLine = findViewById(R.id.progress_line);
        mImage = findViewById(R.id.initImage);
        mImage.setImageResource(R.drawable.rem);
        mChooseLocalImage = findViewById(R.id.local_image);
    }

    void lineProgress() {
        int height = mImage.getHeight();
        mLine.setVisibility(View.VISIBLE);
        double time = height / 0.5;
        lineAnimation = new TranslateAnimation(0, 0, 0, height);
        lineAnimation.start();
        lineAnimation.setDuration((int) time);
        lineAnimation.setRepeatCount(Animation.INFINITE);//无限循环
        mLine.startAnimation(lineAnimation);
    }

    void controlProcess() {
        mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alterDialog = new AlertDialog.Builder(MainActivity.this);
                final String[] mItem = {"植物识别", "动物识别", "菜品识别", "车辆识别", "人脸识别"};
                alterDialog.setItems(mItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        lineProgress();
                        selection = which;
                        mTitle.setText(mItem[which]);

                        //新建线程
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                if (which == 4) {
                                    HashMap<String, String> options = new HashMap<>();
                                    options.put("face_field", "age,gender,race,beauty,expression,type");//注意后期改善表
                                    AipFace client = new AipFace(FACE_APP_ID, FACE_API_KEY, FACE_SECRET_KEY);
                                    client.setConnectionTimeoutInMillis(2000);
                                    client.setSocketTimeoutInMillis(6000);
                                    try {
                                        json = client.detect(arrays, options);
                                        Message message = Message.obtain();
                                        message.what = 5;
                                        message.obj = json;
                                        mHandler.sendMessage(message);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Message message = Message.obtain();
                                        message.what = FACE;
                                        message.obj = e;
                                        mHandler.sendMessage(message);
                                    }
                                } else {
                                    AipImageClassify aipImageClassify = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
                                    aipImageClassify.setConnectionTimeoutInMillis(2000);
                                    aipImageClassify.setSocketTimeoutInMillis(6000);
                                    try {
                                        int what = services(selection);
                                        Message message = Message.obtain();
                                        message.what = what;
                                        message.obj = json;
                                        mHandler.sendMessage(message);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Message message = Message.obtain();
                                        message.what = FACE;
                                        message.obj = e;
                                        mHandler.sendMessage(message);
                                    }
                                }

                            }
                        }).start();
                        if (BuildConfig.DEBUG) Log.d("MainActivity", "which:" + which);
                    }
                }).create().show();
            }
        });
    }

    void imageProcess() {
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(MainActivity.this,"测试使用");
            }
        });
    }

   /* void captureProcess() {
        mChooseLocalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    }
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
                String[] mitems={"从相册中选择","拍照"};
                alertDialog.setTitle("选择图片方式").setItems(mitems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0){
                            Intent chooseImage=new Intent(Intent.ACTION_PICK);
                            chooseImage.setType("image/*");
                            if (chooseImage.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(chooseImage, 1);
                            } else {
                                ToastUtil.showToast(MainActivity.this,"访问图库失败！");
                            }
                        }else if(which==1){
                            Intent capture=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "photo_invo.jpg");
//                            File file = new File(MainActivity.this.getCacheDir(), "/invo/invo.jpg");
//                            capture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//授予临时权限
//                            capture.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            *//*if (Build.VERSION.SDK_INT >= 24) {
                                mUri = FileProvider.getUriForFile(MainActivity.this.getApplicationContext(), "com.example.invo.myplanttest.fileprovider", file);
                            } else {
                                mUri = Uri.fromFile(file);
                            }*//*

                            try{
                                if(file.exists()){
                                    file.delete();
                                }
                                file.createNewFile();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                            *//*File file = null;
                            String storagePath;
                            File storageDir;
                            try {
                                    storagePath=.getInstance().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }*//*
                            mUri = Uri.fromFile(file);
                            capture.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                            imagePath=file.getAbsolutePath();
                            startActivityForResult(capture,2);
                        }
                    }
                }).create().show();
            }
        });
    }*/

    public void localImage(View view) {
        //读取本地照片
        Intent chooseImage=new Intent(Intent.ACTION_PICK);
//        chooseImage.setType("image/*");
        chooseImage.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

        if (chooseImage.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(chooseImage, 1);
        } else {
            ToastUtil.showToast(MainActivity.this,"访问图库失败！");
        }
    }

    public void captureImage(View view) {
        Intent capture=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "photo_invo.jpg");
        try{
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        mUri = Uri.fromFile(file);
        capture.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        imagePath=file.getAbsolutePath();
        startActivityForResult(capture,2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        /*
         * 读取照片的结果并加载在预处理的bitmap中
         * result -1成功 1失败
         * */
        super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode == ACTION_CHOOSE_IMAGE) {
            mBitmap = ImageUtil.getBitmapFromUri(data.getData(), this);
        }
        if (requestCode == REQUEST_CODE_TAKE_PICTURE) {
            mBitmap = (Bitmap) data.getExtras().get("data");
        }
        mImage.setImageBitmap(mBitmap);*/

        /*if (data == null) {
            ToastUtil.showToast(MainActivity.this,"访问图库失败！");
            if (BuildConfig.DEBUG) Log.d("MainActivity", "requestCode:" + requestCode);
            if (BuildConfig.DEBUG) Log.d("MainActivity", "resultCode:" + resultCode);
            if (BuildConfig.DEBUG) Log.d("MainActivity", "data:" + data);
            return;
        }*/

        if (BuildConfig.DEBUG) Log.d("MainActivity", "requestCode:" + requestCode);
        if (BuildConfig.DEBUG) Log.d("MainActivity", "resultCode:" + resultCode);
        if (BuildConfig.DEBUG) Log.d("MainActivity", "data:" + data);
        switch (requestCode) {

            case 1:
                if(requestCode==1){
                    if (data == null) {
                        /*if (mBitmap == null) {
                            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rem);
                            mImage.setImageBitmap(mBitmap);
                        } else {
                            handle_photo();
                            mImage.setImageBitmap(mBitmap);
                            ToastUtil.showToast(MainActivity.this,"访问失败！");
                        }*/
                        ToastUtil.showToast(MainActivity.this, "没有选中内容！");
                        return;
                    } else {
                        Uri uri = data.getData();
                        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                        cursor.moveToNext();
                        imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
                        cursor.close();
                    }

                }
                break;
            /*case 2:

                if (requestCode == 2) {
                    if (data == null) {
                        ToastUtil.showToast(MainActivity.this, "没有拍摄内容！");
//                        return;
                    }
//                    mBitmap = (Bitmap) data.getExtras().get("data");

                }
                break;*/
        }

        if(requestCode==1 || requestCode==2){
            if (data == null&&requestCode==2) {
                mBitmap = mBu.getmBitmap();
            }
            //设置bitmap到imageView上
            handle_photo();
            mImage.setImageBitmap(mBitmap);
            mBu.setBitmap(mBitmap);
        }

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            lineAnimation.cancel();
            try {
                switch (msg.what) {
                    case PLANT:
                        JSONObject jsonObject1 = (JSONObject) msg.obj;
                        JSONArray jsonArray1 = new JSONArray(jsonObject1.optString("result"));
                        String name1 = jsonArray1.optJSONObject(0).optString("name");
                        String score1 = jsonArray1.optJSONObject(0).optString("score");
                        String[] mitems1 = {"名称：" + name1, "可能性：" + score1};
                        AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(MainActivity.this);
                        alertDialog1.setTitle("植物识别报告").setItems(mitems1, null).create().show();
                        break;

                    case ANIMAL:
                        JSONObject jsonObject2 = (JSONObject) msg.obj;
                        JSONArray jsonArray2 = new JSONArray(jsonObject2.optString("result"));
                        String name2 = jsonArray2.optJSONObject(0).optString("name");
                        String score2 = jsonArray2.optJSONObject(0).optString("score");
                        String[] mitems2 = {"名称：" + name2, "可能性：" + score2};
                        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(MainActivity.this);
                        alertDialog2.setTitle("动物识别报告").setItems(mitems2, null).create().show();
                        break;

                    case DISHES:
                        JSONObject jsonObject3 = (JSONObject) msg.obj;
                        JSONArray jsonArray3 = new JSONArray(jsonObject3.optString("result"));
                        String name3 = "", calorie3 = "", score3 = "";
                        name3 = jsonArray3.optJSONObject(0).optString("name");
                        calorie3 = jsonArray3.optJSONObject(0).optString("calorie");
                        score3 = jsonArray3.optJSONObject(0).optString("probability");
                        AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(MainActivity.this);
                        String[] mitems3 = {"名称：" + name3, "卡路里：" + calorie3, "可能性：" + score3};
                        alertDialog3.setTitle("菜品识别报告").setItems(mitems3, null).create().show();
                        break;

                    case CAR:
                        JSONObject jsonObject4 = (JSONObject) msg.obj;
                        JSONArray jsonArray4 = new JSONArray(jsonObject4.optString("result"));
                        String name4 = jsonArray4.optJSONObject(0).optString("name");
                        String score4 = jsonArray4.optJSONObject(0).optString("score");
                        String[] mitems4 = {"名称：" + name4, "可能性：" + score4};
                        AlertDialog.Builder alertDialog4 = new AlertDialog.Builder(MainActivity.this);
                        alertDialog4.setTitle("车型识别报告").setItems(mitems4, null).create().show();
                        break;

                    case 5:     //后期修改
                        JSONObject jsonObject5 = (JSONObject) msg.obj;
                        face_result = jsonObject5.optString("result_num");
                        if (Integer.parseInt(face_result) >= 1) {
                            try {
                                JSONArray jsonArray5 = new JSONArray(jsonObject5.optString("result"));
                                face_age = jsonArray5.optJSONObject(0).optString("age");
                                face_gender = jsonArray5.optJSONObject(0).optString("gender");
                                if (face_gender.equals("female")) {
                                    face_gender = "女";
                                } else {
                                    face_gender = "男";
                                }
                                face_race = jsonArray5.optJSONObject(0).optString("race");
                                if (face_race.equals("yellow")) {
                                    face_race = "黄种人";
                                } else if (face_race.equals("white")) {
                                    face_race = "白种人";
                                } else if (face_race.equals("black")) {
                                    face_race = "黑种人";
                                } else if (face_race.equals("arabs")) {
                                    face_race = "阿拉伯人";
                                }
                                int express = Integer.parseInt(jsonArray5.optJSONObject(0).optString("expression"));
                                if (express == 0) {
                                    face_expression = "无";
                                } else if (express == 1) {
                                    face_expression = "微笑";
                                } else {
                                    face_expression = "大笑";
                                }
                                face_beauty = jsonArray5.optJSONObject(0).optString("beauty");
                                double beauty = Math.ceil(Double.parseDouble(face_beauty) + 25);
                                if (beauty >= 100) {
                                    beauty = 99.0;
                                } else if (beauty < 70) {
                                    beauty += 10;
                                } else if (beauty > 80 && beauty < 90) {
                                    beauty += 5;
                                } else if (beauty >= 90 && beauty < 95) {
                                    beauty += 2;
                                }
                                face_beauty = String.valueOf(beauty);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(MainActivity.this);
                            String[] mItems5 = {"性别：" + face_gender, "年龄：" + face_age, "肤色：" + face_race, "颜值：" + face_beauty, "笑容：" + face_expression};
                            alertDialog5.setTitle("人脸识别报告").setItems(mItems5, null).create().show();
                        } else {
                            AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(MainActivity.this);
                            alertDialog5.setTitle("人脸识别报告").setMessage("图片不够清晰，请重新选择").create().show();
                        }
                        break;

                    case 123:   //用于测试处理相机icon的状态，后期可处理
                        mChooseLocalImage.setVisibility(View.GONE);
                        break;
                    default:
                        Exception exception = (Exception) msg.obj;
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                        alertDialog.setTitle("识别错误").setMessage(exception.toString()).create().show();
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("识别报告").setMessage("图片无法解析").create().show();
            }
        }
    };

    protected int services(int position) {
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(6000);
        if (imagePath == null) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.id.initImage);
            //没有handle bitmap
            handle_photo();
            json = client.plantDetect(arrays, new HashMap<String, String>());
            return 1;
        } else {
            if (position == 0) {
                json = client.plantDetect(arrays, new HashMap<String, String>());
                return 1;
            } else if (position == 1) {
                json = client.animalDetect(arrays, new HashMap<String, String>());
                return 2;
            } else if (position == 2) {
                json = client.plantDetect(arrays, new HashMap<String, String>());
                return 3;
            } else if (position == 3) {
                json = client.plantDetect(arrays, new HashMap<String, String>());
                return 4;
            }
        }
        return 1;
    }



}
