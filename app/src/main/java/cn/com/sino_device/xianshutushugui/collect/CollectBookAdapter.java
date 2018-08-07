package cn.com.sino_device.xianshutushugui.collect;

import android.content.Context;
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

public class CollectBookAdapter extends BaseAdapter {
    private Context context;
    private List<CollectBook> mDatas;
    public CollectBookAdapter(Context context, List<CollectBook> mDatas){
        this.context=context;
        this.mDatas=mDatas;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_mycollect, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CollectBook collectBook=mDatas.get(position);
        ImageLoader.getInstance().displayImage(collectBook.getImage(), holder.ivBookImage);
        holder.tvTitle.setText(collectBook.getName());
        holder.tvAuthor.setText("作者： "+collectBook.getAuthor());
        holder.tvCreateDate.setText("收藏时间： "+collectBook.getCreateDate());
        return convertView;
    }
    class ViewHolder {
        ImageView ivBookImage;
        TextView tvTitle, tvAuthor, tvCreateDate;

        ViewHolder(View itemView) {
            ivBookImage = itemView.findViewById(R.id.bookimage);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor = itemView.findViewById(R.id.author);
            tvCreateDate = itemView.findViewById(R.id.createdate);
        }
    }
}
