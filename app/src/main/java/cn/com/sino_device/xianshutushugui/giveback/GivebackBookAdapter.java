package cn.com.sino_device.xianshutushugui.giveback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.bean.book.BookBorrow;
import cn.com.sino_device.xianshutushugui.util.StringUtil;

/**
 * @author affe
 */
public class GivebackBookAdapter extends BaseAdapter {

    private List<BookBorrow> giveBackBookList;
    private GivebackBookAdapter.CheckInterface checkInterface;
    private Context context;

    public GivebackBookAdapter(Context context) {
        this.context = context;
    }

    public void setBorrowBookList(List<BookBorrow> giveBackBookList) {
        this.giveBackBookList = giveBackBookList;
        notifyDataSetChanged();
    }

    /**
     * 单选接口
     *
     * @param checkInterface
     */
    public void setCheckInterface(GivebackBookAdapter.CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    @Override
    public int getCount() {
        return giveBackBookList == null ? 0 : giveBackBookList.size();
    }

    @Override
    public Object getItem(int position) {
        return giveBackBookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final GivebackBookAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_givebackbook, parent, false);
            holder = new GivebackBookAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GivebackBookAdapter.ViewHolder) convertView.getTag();
        }
        final BookBorrow book_Borrow = giveBackBookList.get(position);
//        boolean choosed = book_Borrow.isChoosed();
//        if (choosed) {
//            holder.ckOneChose.setChecked(true);
//        } else {
//            holder.ckOneChose.setChecked(false);
//        }
//        String author = book_Borrow.getAuthor();
//        if (!StringUtil.isEmpty(author)) {
//            holder.tvBookAuthor.setText(author + "");
//        } else {
//            holder.tvBookAuthor.setText(book_Borrow.getAuthor() + "著");
//        }
//        holder.tvBookTitle.setText(book_Borrow.getTitle());
//        holder.tvBookPrice.setText("￥" + book_Borrow.getPrice() + "/天");
//        //预约天数
//        holder.tvSubscribeDay.setText("预借" + book_Borrow.getDay() + "天");
//        //实际天数
//        holder.tvActualDay.setText("实借" + book_Borrow.getDay() + "天");
//
//
//        holder.tvActualPrice.setText(book_Borrow.getPrice() * book_Borrow.getDay() + "元");
//        ImageLoader.getInstance().displayImage(book_Borrow.getImage_small(), holder.ivShowBookPic);
//        //单选框按钮
//        holder.ckOneChose.setOnClickListener(
//                v -> {
//                    book_Borrow.setChoosed(((CheckBox) v).isChecked());
//                    //向外暴露接口
//                    checkInterface.checkGroup(position, ((CheckBox) v).isChecked());
//                }
//        );
        return convertView;
    }

    /**
     * 初始化控件
     */
    class ViewHolder {
        ImageView ivShowBookPic;
        TextView tvBookTitle, tvBookAuthor, tvBookPublisher, tvBookPrice, tvSubscribeDay, tvActualDay, tvActualPrice;
        CheckBox ckOneChose;

        public ViewHolder(View itemView) {
            ckOneChose = itemView.findViewById(R.id.ck_chose);
            ivShowBookPic = itemView.findViewById(R.id.iv_showbook_pic);
            tvBookTitle = itemView.findViewById(R.id.tv_book_title);
            tvBookAuthor = itemView.findViewById(R.id.tv_book_author);
            tvBookPublisher = itemView.findViewById(R.id.tv_book_publisher);
            tvBookPrice = itemView.findViewById(R.id.tv_book_price);
            //预约天数
            tvSubscribeDay = itemView.findViewById(R.id.tv_subscribe_day);
            //实际天数
            tvActualDay = itemView.findViewById(R.id.tv_actual_day);
            //实际应付金额
            tvActualPrice = itemView.findViewById(R.id.tv_actual_price);
        }
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }
}
