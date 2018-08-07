package cn.com.sino_device.xianshutushugui.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.bean.user.AlterUserInfo;
import cn.com.sino_device.xianshutushugui.bean.user.ResultGetUserInfo;
import cn.com.sino_device.xianshutushugui.util.Base64BitmapUtil;
import cn.com.sino_device.xianshutushugui.util.SPUtils;
import cn.com.sino_device.xianshutushugui.util.UploadUtil;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.support.v4.content.FileProvider.getUriForFile;
import static com.google.common.base.Preconditions.checkNotNull;

public class EditUserinfoActivity extends AppCompatActivity implements UserContract.AlterUserInfoView, View.OnClickListener {
    private static final String TAG = "EditUserinfoActivity";

    /**
     * 相册选择后裁剪
     */
    private static final int REQUEST_CODE_ALBUM = 0x110;

    /**
     * 拍照后裁剪
     */
    private static final int REQUEST_CODE_CAMERA = 0x111;

    /**
     * 裁剪
     */
    private static final int REQUEST_CODE_CROP = 0x113;

    /**
     * 拍照返回的图片
     */
    private File imageFile;
    /**
     * 裁剪后的图片Uri
     */
    private Uri uritempFile;

    /**
     * 取消
     */
    private TextView tvCancel;

    /**
     * 完成
     */
    private TextView tvCommit;

    /**
     * 用户姓名
     */
    private TextView tvUserFullname;

    /**
     * 用户性别
     */
    private TextView tvSex;
    private OptionsPickerView opvSex;
    private ArrayList<String> sexOptions = new ArrayList<>();

    /**
     * 用户头像
     */
    private CircleImageView civAvatar;

    /**
     * 修改头像
     */
    private TextView tvChangeAvatar;
    private PopupWindow popupWindow;

    /**
     * 学号
     */
    private EditText etNo;

    /**
     * 学生姓名
     */
    private EditText etStuName;

    /**
     * 所在班级
     */
    private EditText etClasses;

    /**
     * 邮箱
     */
    private EditText etEmail;

    private UserPresenter mUserPresenter;
    private UserContract.AlterUserInfoPresenter mPresenter;
    private AlterUserInfo alterUserInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_userinfo);

        initView();
        mUserPresenter = new UserPresenter(this);

    }

    private void initView() {
        tvCancel = findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(this);
        tvCommit = findViewById(R.id.tv_commit);
        tvCommit.setOnClickListener(this);
        // 用户头像
        civAvatar = findViewById(R.id.civ_avatar);
        tvChangeAvatar = findViewById(R.id.tv_change_avatar);
        tvChangeAvatar.setOnClickListener(this);
        // 用户名
        tvUserFullname = findViewById(R.id.et_username);
        //  性别
        tvSex = findViewById(R.id.tv_sex);
        tvSex.setOnClickListener(this);
        initSexOptionsPickerView();
        // 学号
        etNo = findViewById(R.id.et_no);
        // 学生姓名
        etStuName = findViewById(R.id.et_stu_name);
        // 所在班级
        etClasses = findViewById(R.id.et_classes);
        // 邮箱
        etEmail = findViewById(R.id.et_email);
        String currentUserinfo = SPUtils.get(this, "CURRENT_USERINFO", "").toString();
        if (!currentUserinfo.equals("")) {
            Gson gson = new Gson();
            ResultGetUserInfo resultGetUserInfo = gson.fromJson(currentUserinfo, ResultGetUserInfo.class);
            civAvatar.setImageBitmap(Base64BitmapUtil.base64ToBitmap(resultGetUserInfo.getPhoto()));
            tvUserFullname.setText(resultGetUserInfo.getName());
            tvSex.setText(resultGetUserInfo.getSex());
            etNo.setText(resultGetUserInfo.getNo());
            ///etStuName.setText();
            ///etClasses.setText();
            etEmail.setText(resultGetUserInfo.getEmail());
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                EditUserinfoActivity.this.finish();
                break;
            case R.id.tv_commit:
                // 性别
                String sex = tvSex.getText().toString();
                // 学号
                String no = etNo.getText().toString();
                // 学生姓名
                String stuName = etStuName.getText().toString();
                // 所在班级
                String classes = etClasses.getText().toString();
                // 邮箱
                String email = etEmail.getText().toString();
                alterUserInfo = new AlterUserInfo();
                alterUserInfo.setSex(sex);
                alterUserInfo.setPhoto("image");
                alterUserInfo.setNo(no);
                alterUserInfo.setStu_name(stuName);
                alterUserInfo.setClasses(classes);
                alterUserInfo.setEmail(email);
                //
                mPresenter.alterUserInfo(alterUserInfo);
                ///EditUserinfoActivity.this.finish();
                break;
            case R.id.tv_change_avatar:
                showPopupWindow();
                break;
            case R.id.tv_sex:
                opvSex.show();
                break;
            // PopupWindow选项菜单
            case R.id.tv_take_picture:
                popupWindow.dismiss();
                // 拍照
                fromCamera();
                break;
            case R.id.tv_from_album:
                popupWindow.dismiss();
                // 从相册选择
                fromAlbum();
                break;
            case R.id.tv_pop_cancel:
                popupWindow.dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(UserContract.AlterUserInfoPresenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showAlterUserInfo(Object result) {
        // TODO
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", imageFile);
                        cropImage(contentUri);
                    } else {
                        cropImage(Uri.fromFile(imageFile));
                    }
                }
                break;
            case REQUEST_CODE_ALBUM:
                if (data == null) {
                    return;
                }
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    cropImage(uri);
                }
                break;
            case REQUEST_CODE_CROP:
                if (data == null) {
                    return;
                }
//                Bundle bundle = data.getExtras();
//                if (bundle != null) {
//                    Bitmap avatar = bundle.getParcelable("data");
//                    // 设置头像
//                    civAvatar.setImageBitmap(avatar);
//                    // 保存头像
//                    saveAvatarImage(avatar);
//                }
                try {
                    Bitmap avatar = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                    Log.i(TAG, uritempFile.toString());
                    // 设置头像
                    civAvatar.setImageBitmap(avatar);
                    // 保存头像
                    saveAvatarImage(avatar);
                    // 上传头像
                    Thread thread = new Thread(() -> {
                        // https://yanzhonghui.cn/developer/upload_file.php
                        UploadUtil.uploadFile(new File("/storage/emulated/0/avatar.jpg"), "https://yanzhonghui.cn/developer/upload_file.php");
                        ///UploadUtil.uploadFile(imageFile, "https://yanzhonghui.cn/developer/upload_file.php");
                    });
                    thread.start();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }

    /**
     * 从相册获取
     */
    private void fromAlbum() {
        Intent fromAlbumIntent = new Intent(Intent.ACTION_PICK);
        fromAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(fromAlbumIntent, REQUEST_CODE_ALBUM);
    }

    /**
     * 从相机获取
     */
    private void fromCamera() {
        imageFile = new File(getCacheDir(), "avatar.png");
        Log.i(TAG, imageFile.getAbsolutePath());
        Intent fromCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fromCameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fromCameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = getUriForFile(this, getPackageName() + ".fileprovider", imageFile);
            fromCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            fromCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        }
        startActivityForResult(fromCameraIntent, REQUEST_CODE_CAMERA);
    }

    /**
     * 裁剪图片
     *
     * @param uri
     */
    private void cropImage(Uri uri) {
        Intent cropImageIntent = new Intent("com.android.camera.action.CROP");
        cropImageIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        cropImageIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        cropImageIntent.setDataAndType(uri, "image/*");
        cropImageIntent.putExtra("crop", "true");
        // 圆形裁剪区域
        cropImageIntent.putExtra("circleCrop", "true");
        cropImageIntent.putExtra("aspectX", 1);
        cropImageIntent.putExtra("aspectY", 1);
        cropImageIntent.putExtra("outputX", 300);
        cropImageIntent.putExtra("outputY", 300);
        cropImageIntent.putExtra("scale", true);
        //
        ///cropImageIntent.putExtra("return-data", true);
        //裁剪后的图片Uri路径，uritempFile为Uri类变量
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "avatar.jpg");
        cropImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        cropImageIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        startActivityForResult(cropImageIntent, REQUEST_CODE_CROP);
    }


    /**
     * 图片旋转
     *
     * @param bitmap
     * @param degree
     * @return
     */
    private Bitmap rotateImage(Bitmap bitmap, int degree) {
        Bitmap temp = null;
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            temp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (temp == null) {
            temp = bitmap;
        }
        if (bitmap != temp) {
            bitmap.recycle();
        }
        return temp;
    }

    /**
     * 获取图片旋转角度
     *
     * @param path
     * @return
     */
    private int getImageRotationAngle(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
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
                default:
                    degree = 0;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 保存头像
     *
     * @param bitmap
     * @return
     */
    private String saveAvatarImage(Bitmap bitmap) {
        File avatar = new File(getFilesDir(), "avatar.png");
        try {
            FileOutputStream fos = new FileOutputStream(avatar);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return avatar.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 性别
     */
    private void initSexOptionsPickerView() {
        sexOptions.add("男");
        sexOptions.add("女");
        opvSex = new OptionsPickerBuilder(this,
                (options1, options2, options3, v) -> tvSex.setText(sexOptions.get(options1)))
                .setOptionsSelectChangeListener((options1, options2, options3) -> {
                }).build();
        opvSex.setNPicker(sexOptions, null, null);
    }

    /**
     * 显示选择头像图片的PopupWindow
     */
    private void showPopupWindow() {
        View contentView = LayoutInflater.from(EditUserinfoActivity.this).inflate(R.layout.popuplayout, null);
        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(contentView);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.Popupwindow);
        //
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 设置透明度
        lp.alpha = 0.4f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getWindow().getAttributes();
            lp1.alpha = 1f;
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            getWindow().setAttributes(lp1);
        });
        //
        TextView tvTakePicture = contentView.findViewById(R.id.tv_take_picture);
        tvTakePicture.setOnClickListener(this);
        TextView tvFromAlbum = contentView.findViewById(R.id.tv_from_album);
        tvFromAlbum.setOnClickListener(this);
        TextView tvCancel = contentView.findViewById(R.id.tv_pop_cancel);
        tvCancel.setOnClickListener(this);
        //
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_edit_userinfo, null);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }
}



