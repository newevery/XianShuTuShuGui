package cn.com.sino_device.xianshutushugui.borrow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.bean.book.BookBorrow;
import cn.com.sino_device.xianshutushugui.util.StringUtil;

/**
 * @author zyg
 */
public class BorrowBookAdapter extends BaseAdapter {
    private List<BookBorrow> borrowBookList;
    private Context context;

    public BorrowBookAdapter(Context context, List<BookBorrow> borrowBookList) {
        this.borrowBookList = borrowBookList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return borrowBookList == null ? 0 : borrowBookList.size();
    }

    @Override
    public Object getItem(int position) {
        return borrowBookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_yuyue, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final BookBorrow bookBorrow = borrowBookList.get(position);
        holder.tvBookName.setText(bookBorrow.getBookName());
        holder.tvOwner.setText("拥有者："+bookBorrow.getUserName());
        holder.tvCreateTime.setText("预约时间："+bookBorrow.getCreateTime());
        holder.tvDay.setText("预约天数："+bookBorrow.getDay());
        if (bookBorrow.getType()=="0"){
            holder.tvState.setText("未审核");
        }else {
            holder.tvState.setText("审核成功");
        }
        holder.tvAuditResult.setText(bookBorrow.getAuditResult());
        return convertView       ;
    }

    /**
     * 初始化控件
     */
    class ViewHolder {
        TextView tvBookName, tvOwner, tvCreateTime, tvDay,tvState,tvAuditResult;

        ViewHolder(View itemView) {
            tvBookName = itemView.findViewById(R.id.tv_bookname);
            tvOwner = itemView.findViewById(R.id.tv_owner);
            tvCreateTime = itemView.findViewById(R.id.tv_createTime);
            tvDay = itemView.findViewById(R.id.tv_day);
            tvState = itemView.findViewById(R.id.tv_state);
            tvAuditResult = itemView.findViewById(R.id.tv_auditresult);
        }
    }

}
