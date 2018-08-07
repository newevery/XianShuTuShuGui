package cn.com.sino_device.xianshutushugui.user.signup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import cn.com.sino_device.xianshutushugui.GlobalConsts;
import cn.com.sino_device.xianshutushugui.MainActivity;
import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketInstance;
import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.bean.user.ResultGetInfo;
import cn.com.sino_device.xianshutushugui.bean.user.UploadPhoto;
import cn.com.sino_device.xianshutushugui.bean.user.UserRegister;
import cn.com.sino_device.xianshutushugui.organization.OrganizationPresenter;
import cn.com.sino_device.xianshutushugui.user.UserContract;
import cn.com.sino_device.xianshutushugui.user.UserContract.RegisterView;
import cn.com.sino_device.xianshutushugui.user.UserPresenter;
import cn.com.sino_device.xianshutushugui.util.Base64BitmapUtil;
import cn.com.sino_device.xianshutushugui.util.SPUtils;
import cn.com.sino_device.xianshutushugui.util.UploadUtil;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.support.v4.content.FileProvider.getUriForFile;
import static cn.com.sino_device.xianshutushugui.GlobalConsts.ERROR_CODE_SUCCESS;
import static cn.com.sino_device.xianshutushugui.organization.OrganizationContract.*;
import static com.google.common.base.Preconditions.checkNotNull;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.SignUpView, OnClickListener {
    private static final String TAG = "SignUpActivity";

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
     * 取消
     */
    private TextView tvCancel;
    /**
     * 完成
     */
    private TextView tvCommit;
    /**
     * 用户头像
     */
    private CircleImageView civAvatar;

    /**
     * 用户头像 Base64
     */
    String avatarBase64;

    /**
     * 修改头像
     */
    private TextView tvSetAvatar;
    private PopupWindow popupWindow;

    /**
     * 用户名
     */
    private EditText etUsername;

    /**
     * 用户性别
     */
    private TextView tvSex;
    private OptionsPickerView opvSex;
    private ArrayList<String> sexOptions = new ArrayList<>();

    /**
     * 手机号
     */
    private EditText etMobile;

    /**
     * 密码
     */
    private TextInputLayout tilPassword;
    private TextInputEditText tietPassword;

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

    private SignUpPresenter mSignUpPresenter;
    private SignUpContract.SignUpPresenter mPresenter;
    private UserRegister userRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();
        mSignUpPresenter = new SignUpPresenter(this);
    }

    private void initView() {
        tvCancel = findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(this);
        tvCommit = findViewById(R.id.tv_commit);
        tvCommit.setOnClickListener(this);
        civAvatar = findViewById(R.id.civ_avatar);
        tvSetAvatar = findViewById(R.id.tv_set_avatar);
        tvSetAvatar.setOnClickListener(this);
        etUsername = findViewById(R.id.et_username);
        tvSex = findViewById(R.id.tv_sex);
        tvSex.setOnClickListener(this);
        initSexOptionsPickerView();
        etMobile = findViewById(R.id.et_mobile);
        tilPassword = findViewById(R.id.til_password);
        tietPassword = findViewById(R.id.tiet_password);
        etNo = findViewById(R.id.et_no);
        etStuName = findViewById(R.id.et_stu_name);
        etClasses = findViewById(R.id.et_classes);
        etEmail = findViewById(R.id.et_email);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                SignUpActivity.this.finish();
                break;
            case R.id.tv_commit:
                String name = etUsername.getText().toString();
                String sex = tvSex.getText().toString();
                String mobile = etMobile.getText().toString();
                String password = tietPassword.getText().toString();
                String no = etNo.getText().toString();
                String stuName = etStuName.getText().toString();
                String classes = etClasses.getText().toString();
                String email = etEmail.getText().toString();

                if (validatePassword(password)) {
                    userRegister = new UserRegister();
                    userRegister.setName(name);
                    userRegister.setSex(sex);
                    userRegister.setMobile(mobile);
                    userRegister.setPassword(password);
                    userRegister.setPhoto(avatarBase64);
                    Log.i(TAG,avatarBase64);
                    userRegister.setNo(no);
                    userRegister.setStu_name(stuName);
                    userRegister.setClasses(classes);
                    userRegister.setDeposit("0");
                    userRegister.setEmail(email);
                    /**
                     * TODO 用户IP，需通过接口获取
                     */
                    userRegister.setLogin_ip("0.0.0.0");
                    // 2007-01-31 00:00:00
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String now = sdf.format(date);
                    userRegister.setLogin_date("2007-01-31 00:00:00");
                    userRegister.setCreate_by("");
                    userRegister.setCreate_date(now);
                    //
                    mPresenter.userSignUp(userRegister);
                }
                break;
            case R.id.tv_set_avatar:
                // TODO
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
    public void setPresenter(SignUpContract.SignUpPresenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showSignUp(Object object) {
        Log.i(TAG, object.toString());
        if (object.getClass() == Result.class) {
            Result result = (Result) object;
            Log.i(TAG, result.toString());
            //
            if (ERROR_CODE_SUCCESS.equals(result.getErrorCode())) {
                // 注册成功
                // 跳转到主界面
                Intent intentMain = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intentMain);
                SignUpActivity.this.finish();
                Looper.prepare();
                Toast.makeText(SignUpActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                Looper.loop();
            } else {
                // 注册失败
                Looper.prepare();
                Toast.makeText(SignUpActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        } else {
            Log.i(TAG, object.toString());
            Looper.prepare();
            Toast.makeText(SignUpActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
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
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Bitmap avatar = bundle.getParcelable("data");
                    // 设置头像
                    civAvatar.setImageBitmap(avatar);
                    // 保存头像
                    saveAvatarImage(avatar);
                    ///avatarBase64 = Base64BitmapUtil.bitmapToBase64(avatar);
                    ///Log.i(TAG, avatarBase64);

                    // 上传头像
                    Thread thread = new Thread(() -> {
                        // https://yanzhonghui.cn/developer/upload_file.php
                        UploadUtil.uploadFile(new File("/storage/emulated/0/avatar.jpg"), "https://yanzhonghui.cn/developer/upload_file.php");
                        ///UploadUtil.uploadFile(imageFile, "https://yanzhonghui.cn/developer/upload_file.php");
                    });
                     thread.start();



//                    UploadPhoto uploadPhoto = new UploadPhoto();
//                    uploadPhoto.setMobile("13020180710");
//                    uploadPhoto.setPhoto(img);
//                    WebSocketInstance.wsConnect("uploadPhoto", uploadPhoto.toString(), new CallBack() {
//                        @Override
//                        public void onSuccess(String message) {
//                            Log.i(TAG, "0000" + message);
//                        }
//
//                        @Override
//                        public void onError(String error) {
//
//                        }
//                    });


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
        cropImageIntent.putExtra("return-data", true);
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
     * 显示选择头像图片的PopupWindow
     */
    private void showPopupWindow() {
        View contentView = LayoutInflater.from(SignUpActivity.this).inflate(R.layout.popuplayout, null);
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
     * 验证密码
     *
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showError(tilPassword, "密码不能为空");
            return false;
        }
        if (password.length() < GlobalConsts.PASSWORD_MIN_LENGTH
                || password.length() > GlobalConsts.PASSWORD_MAX_LENGTH) {
            showError(tilPassword, "密码长度为6-18位");
            return false;
        }
        return true;
    }

    /**
     * 显示错误提示，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        Objects.requireNonNull(textInputLayout.getEditText()).setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }
}
