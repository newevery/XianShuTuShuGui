package cn.com.sino_device.xianshutushugui.library;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketAsyncTask;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketInstance;
import cn.com.sino_device.xianshutushugui.bean.Book;
import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.search.SearchActivity;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author affe
 */
public class LibraryFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener {
    private static final String TAG = "LibraryFragment";
    private TabLayout mTab;
    private ViewPager mViewpager;
    private FragmentPagerAdapter mAdapter;
    private String[] titles;

    private ImageButton ibPersonal;
    private SearchView svSearch;

    //    private List<String> strings = new ArrayList<>();
//    private List<Fragment> fragments = new ArrayList<>();
//    private TabFragmentShouYeAdapter shouYeAdapter;
    private TextView tvNetError;

    public LibraryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library_book, container, false);
        tvNetError = view.findViewById(R.id.tv_netError);
        initView(view);
        initdate();

        return view;
    }

    private void initView(View view) {
        mTab = view.findViewById(R.id.tablayout_shouye);
        mViewpager = view.findViewById(R.id.viewpager_ShouYe);
        ibPersonal = view.findViewById(R.id.personal);
        ibPersonal.setOnClickListener(this);
        svSearch = view.findViewById(R.id.search);
        // 去除搜索框下划线
        try {
            Class<?> argClass = svSearch.getClass();
            Field field = argClass.getDeclaredField("mSearchPlate");
            field.setAccessible(true);
            View v = (View) field.get(svSearch);
            v.setBackgroundColor(Color.TRANSPARENT);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        // 自定义搜索图标、字体颜色和大小
        if (svSearch != null) {
            // 图标
            int imgId = svSearch.getContext().getResources().getIdentifier("android:id/search_mag_icon", null, null);
            ImageView img = svSearch.findViewById(imgId);
            img.setImageResource(R.drawable.ic_search);
            svSearch.setIconifiedByDefault(false);
            // 文字
            int txtId = svSearch.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
            TextView txt = svSearch.findViewById(txtId);
            txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
            txt.setTextColor(getActivity().getResources().getColor(R.color.colorDimGray));
            txt.setHintTextColor(getActivity().getResources().getColor(R.color.colorDarkGray));
        }
        svSearch.setOnQueryTextListener(this);
        //
//        tabLayout_shouye = view.findViewById(R.id.tablayout_shouye);
//        viewPager_shouye = view.findViewById(R.id.viewpager_ShouYe);
//        shouYeAdapter = new TabFragmentShouYeAdapter(fragments, strings,
//                getActivity().getSupportFragmentManager(), getActivity());
//        viewPager_shouye.setAdapter(shouYeAdapter);
//        tabLayout_shouye.setupWithViewPager(viewPager_shouye);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // 点击搜索后，跳转到 SearchActivity
        Intent intentSearch = new Intent(this.getActivity(), SearchActivity.class);
        intentSearch.putExtra("Keyword", query);
        startActivity(intentSearch);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal:
                DrawerLayout drawer = this.getActivity().findViewById(R.id.drawer_layout);
                // 打开抽屉
                drawer.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
    }

    private void initdate() {
//        Log.i(TAG, "准备获取BookType");
//        new WebSocketAsyncTask(new CallBack() {
//            @Override
//            public void onSuccess(String message) {
//                Gson gson = new Gson();
//                Result result = gson.fromJson(message, Result.class);
//                if (result.isSuccess()) {
//                    Log.i(TAG, result.getMsg());
//
//
//
//                }
//            }
//
//            @Override
//            public void onError(String error) {
//
//            }
//        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "getBookType", "{}");
//1：A马克思主义、列宁主义、毛泽东思想、邓小平理论
//2：B 哲学、宗教
//3：C社会科学总论
//4：D 政治、法律
//5：E 军事
//6：F 经济
//7：G 文化、科学、教育、体育
//8：H 语言、文字
//9：I 文学
//10：J 艺术
//11：K 历史、地理
//12：N 自然科学总论
//13：O 数理科学和化学
//14：P天文学、地球科学
//15：Q 生物科学
//16：R 医药、卫生
//17：S 农业科学
//18：T 工业技术
//19：U 交通运输
//20：V 航空、航天
//21：X环境科学、劳动保护科学（安全科学）
//22：Z 综合性图书
        titles = new String[]{"马列主义、毛泽东思想、邓小平理论",
                "哲学、宗教", "社会科学总论", "政治、法律", "军事", "经济",
                "文化、科学、教育、体育", "语言、文字", "文学", "艺术", "历史、地理",
                "自然科学总论", "数理科学和化学", "天文学、地球科学", "生物科学",
                "医药、卫生", "农业科学", "工业技术", "交通运输", "航空、航天",
                "环境科学、劳动保护科学（安全科学）", "综合性图书"};
//            titles = new String[]{getResources().getString(R.string.yejie), getResources().getString(R.string.yidong)
//                    , getResources().getString(R.string.yunjisuan), getResources().getString(R.string.yanfa)};
        mAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return LibraryListFragment.newInstance(position,getActivity());
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position % titles.length];
            }
        };
        mViewpager.setAdapter(mAdapter);
        mTab.setupWithViewPager(mViewpager);
    }


//
}
