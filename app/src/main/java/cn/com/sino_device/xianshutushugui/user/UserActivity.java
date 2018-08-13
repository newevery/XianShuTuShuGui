package cn.com.sino_device.xianshutushugui.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.bean.user.ResultGetUserInfo;
import cn.com.sino_device.xianshutushugui.user.edit.EditUserinfoActivity;
import cn.com.sino_device.xianshutushugui.util.SPUtils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人中心
 *
 * @author affe
 */
public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 返回按钮
     */
    private ImageButton ibGoBack;
    /**
     * 编辑资料按钮
     */
    private TextView tvEditInfo;
    /**
     * 用户头像
     */
    private CircleImageView civAvatar;
    /**
     * 用户昵称
     */
    private TextView tvNickname;
    /**
     * 用户性别
     */
    private ImageView ivSex;
    /**
     * 用户摘要信息
     */
    private TextView tvAbstract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initView();

    }

    private void initView() {
        ibGoBack = findViewById(R.id.ib_go_back);
        ibGoBack.setOnClickListener(this);
        tvEditInfo = findViewById(R.id.tv_edit_info);
        tvEditInfo.setOnClickListener(this);
        civAvatar = findViewById(R.id.civ_avatar);
        civAvatar.setOnClickListener(this);
        civAvatar.setImageURI(Uri.fromFile(new File(getFilesDir(), "avatar_current.jpg")));
        tvNickname = findViewById(R.id.tv_nickname);
        ivSex = findViewById(R.id.iv_sex);
        tvAbstract = findViewById(R.id.tv_abstract);
        String currentUserinfo = SPUtils.get(this, "CURRENT_USERINFO", "").toString();
        if (!"".equals(currentUserinfo)) {
            Gson gson = new Gson();
            ResultGetUserInfo resultGetUserInfo = gson.fromJson(currentUserinfo, ResultGetUserInfo.class);
            tvNickname.setText(resultGetUserInfo.getName());
            if ("男".equals(resultGetUserInfo.getSex())) {
                ivSex.setImageResource(R.drawable.ic_boy);
            } else {
                ivSex.setImageResource(R.drawable.ic_girl);
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_go_back:
                UserActivity.this.finish();
                break;
            case R.id.tv_edit_info:
                Intent intent = new Intent(UserActivity.this, EditUserinfoActivity.class);
                startActivity(intent);
                break;
            case R.id.civ_avatar:
                // TODO 修改头像
                break;
            default:
                break;
        }
    }
}
