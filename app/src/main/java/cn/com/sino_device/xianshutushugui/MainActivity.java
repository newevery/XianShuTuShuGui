package cn.com.sino_device.xianshutushugui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import cn.com.sino_device.xianshutushugui.base.BaseActivity;
import cn.com.sino_device.xianshutushugui.bean.user.GetUserInfo;
import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.bean.user.ResultGetUserInfo;
import cn.com.sino_device.xianshutushugui.borrow.BorrowFragment;
import cn.com.sino_device.xianshutushugui.collect.MyCollectFragment;
import cn.com.sino_device.xianshutushugui.donate.DonateFragment;
import cn.com.sino_device.xianshutushugui.giveback.GivebackFragment;
import cn.com.sino_device.xianshutushugui.library.LibraryFragment;
import cn.com.sino_device.xianshutushugui.setting.SettingActivity;
import cn.com.sino_device.xianshutushugui.test.TestActivity;
import cn.com.sino_device.xianshutushugui.ui.BottomNavigationViewHelper;
import cn.com.sino_device.xianshutushugui.ui.DisableScrollbleViewPager;
import cn.com.sino_device.xianshutushugui.user.UserActivity;
import cn.com.sino_device.xianshutushugui.user.userinfo.GetUserInfoContract;
import cn.com.sino_device.xianshutushugui.user.userinfo.GetUserInfoPresenter;
import cn.com.sino_device.xianshutushugui.util.Base64BitmapUtil;
import cn.com.sino_device.xianshutushugui.util.FileUtils;
import cn.com.sino_device.xianshutushugui.util.SPUtils;
import cn.com.sino_device.xianshutushugui.wxapi.bean.WeChatUserInfo;
import de.hdodenhof.circleimageview.CircleImageView;

import static cn.com.sino_device.xianshutushugui.GlobalConsts.ERROR_CODE_SUCCESS;
import static com.google.common.base.Preconditions.checkNotNull;


/**
 * @author affe
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, GetUserInfoContract.GetUserInfoView {
    private static final String TAG = "MainActivity";

    /**
     * 抽屉布局
     */
    private DrawerLayout drawer;
    /**
     * 抽屉导航
     */
    private NavigationView drawerNavigationView;
    private CircleImageView civAvatar;
    private TextView tvName;

    /**
     * 禁止滑动的ViewPager
     */
    private DisableScrollbleViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private MainPagerAdapter pagerAdapter;
    /**
     * 底部导航菜单
     */
    private BottomNavigationView bottomNav;
    /**
     * 底部导航菜单监听
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_library:
                    // 书库
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_donate:
                    // 捐书
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_borrow:
                    // 借书
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_mycollect:
                    // 收藏
                    viewPager.setCurrentItem(3);
                    return true;
                case R.id.navigation_giveback:
                    // 还书
                    viewPager.setCurrentItem(4);
                    return true;
                default:
                    return false;
            }
        }
    };


    private GetUserInfoPresenter mGetUserInfoPresenter;
    private GetUserInfoContract.GetUserInfoPresenter mPresenter;
    private GetUserInfo getUserInfo;
    private ResultGetUserInfo resultGetUserInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));


        // 抽屉布局
        drawer = findViewById(R.id.drawer_layout);
        drawerNavigationView = findViewById(R.id.drawer_nav_view);
        drawerNavigationView.setNavigationItemSelectedListener(this);
        // 获取抽屉headerLayout
        View headerView = drawerNavigationView.getHeaderView(0);
        civAvatar = headerView.findViewById(R.id.civ_avatar);
        civAvatar.setOnClickListener(this);
        tvName = headerView.findViewById(R.id.tv_nickname);

        // 底部导航菜单
        viewPager = findViewById(R.id.viewpager);
        bottomNav = findViewById(R.id.bottom_nav_view);
        BottomNavigationViewHelper.disableShiftMode(bottomNav);
        // 设置底部导航菜单监听
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // 书库
        LibraryFragment libraryFragment = new LibraryFragment();
        // 捐书
        DonateFragment donateFragment = new DonateFragment();
        // 借书
        BorrowFragment borrowFragment = new BorrowFragment();
        //收藏
        MyCollectFragment myCollectFragment=new MyCollectFragment();
        // 还书
        GivebackFragment givebackFragment = new GivebackFragment();

        fragments = new ArrayList<>();
        fragments.add(libraryFragment);
        fragments.add(donateFragment);
        fragments.add(borrowFragment);
        fragments.add(myCollectFragment);
        fragments.add(givebackFragment);

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(5);

        // 申请权限
        initPermission();

        mGetUserInfoPresenter = new GetUserInfoPresenter(this);

        getUserInfo = new GetUserInfo();
        Intent intent = getIntent();
        if (intent.getSerializableExtra("WeChatUserInfo") != null) {
            // TODO
            // 微信登录
            WeChatUserInfo userInfo = (WeChatUserInfo) intent.getSerializableExtra("WeChatUserInfo");
            getUserInfo.setMobile(userInfo.getUnionid());
            mPresenter.getUserInfo(getUserInfo);
        } else {
            // 手机号登录
            String mobile = SPUtils.get(this, "CURRENT_USER", "").toString();
            if (!"".equals(mobile)) {
                getUserInfo.setMobile(mobile);
                mPresenter.getUserInfo(getUserInfo);
            }
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.book_card) {

        } else if (id == R.id.setting) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.civ_avatar:
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        ArrayList<String> toApplyList = new ArrayList<String>();
        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                //进入到这里代表没有权限.
            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }

    @Override
    public void showGetUserInfo(Object object) {
        Log.i(TAG, object.toString());
        if (object.getClass() == Result.class) {
            Result result = (Result) object;
            //
            if (ERROR_CODE_SUCCESS.equals(result.getErrorCode())) {
                // 取出 result 中的 msg，转换成 ResultGetUserInfo
                String msg = result.getMsg();
                msg = msg.replace("\\", "");
                Log.i(TAG, msg);
                Gson gson = new Gson();
                resultGetUserInfo = gson.fromJson(msg, ResultGetUserInfo.class);

                // TODO
                SPUtils.remove(this,"CURRENT_USERINFO");
                SPUtils.put(this, "CURRENT_USERINFO", msg);
                //
                tvName.setText(resultGetUserInfo.getName());
                if (!"".equals(resultGetUserInfo.getPhoto())) {
                    Bitmap bitmap = Base64BitmapUtil.base64ToBitmap(resultGetUserInfo.getPhoto());
                    civAvatar.setImageBitmap(bitmap);
                    saveAvatarImage(bitmap);
                }
                /// mPresenter.getUserAvatar(resultGetUserInfo.getPhoto());

                Looper.prepare();
                Toast.makeText(MainActivity.this, resultGetUserInfo.getClasses(), Toast.LENGTH_LONG).show();
                Looper.loop();
            } else {
                //
                Looper.prepare();
                Toast.makeText(MainActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        } else {
            Log.i(TAG, object.toString());
            Looper.prepare();
            Toast.makeText(MainActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    @Override
    public void showGetUserAvatar(Object object) {
        Log.i(TAG, object.toString());
        if (object.getClass() == Bitmap.class) {
            civAvatar.setImageBitmap((Bitmap) object);
            saveAvatarImage((Bitmap) object);
        }
    }

    @Override
    public void setPresenter(GetUserInfoContract.GetUserInfoPresenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


    /**
     * 保存头像
     *
     * @param bitmap
     * @return
     */
    private String saveAvatarImage(Bitmap bitmap) {
        File avatar = new File(getFilesDir(), "avatar_current.jpg");
        FileUtils.deleteFile(avatar.getAbsolutePath());
        try {
            FileOutputStream fos = new FileOutputStream(avatar);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
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
}
