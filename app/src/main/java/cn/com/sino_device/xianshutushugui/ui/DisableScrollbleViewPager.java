package cn.com.sino_device.xianshutushugui.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Android Studio.
 * Date: 2018/4/24
 * Time: 16:47
 */
public class DisableScrollbleViewPager extends ViewPager {

    /**
     * 允许（true）或禁止（false）ViewPager 滑动切换
     */
    private boolean scrollble = false;

    public DisableScrollbleViewPager(Context context) {
        super(context);
    }

    public DisableScrollbleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollble) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!scrollble) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
