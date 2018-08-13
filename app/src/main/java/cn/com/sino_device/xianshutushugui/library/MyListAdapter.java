package cn.com.sino_device.xianshutushugui.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.bean.book.LibraryBookBean;
import cn.com.sino_device.xianshutushugui.book.BookDetailActivity;
import cn.com.sino_device.xianshutushugui.borrow.BorrowDetailActivity;

public class MyListAdapter extends BaseAdapter {
    private Context context;
    private List<LibraryBookBean> libraryBookBeanList;

    //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public MyListAdapter(Context context, List<LibraryBookBean> libraryBookBeanList) {
        this.context = context;
        this.libraryBookBeanList = libraryBookBeanList;
    }

    //返回数据集的长度
    @Override
    public int getCount() {
        return libraryBookBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return libraryBookBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.librarybooklist_item, parent, false); //加载布局
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(libraryBookBeanList.get(position).getPhoto1(), holder.image);
//        Glide.with(context).load(libraryBookBeanList.get(position).getImages()).into(holder.image);
        holder.title.setText(libraryBookBeanList.get(position).getName());
        holder.author.setText(libraryBookBeanList.get(position).getAuthor());
        holder.publisher.setText(libraryBookBeanList.get(position).getPublisher());
        holder.pubdate.setText("出版时间： "+libraryBookBeanList.get(position).getPubdate());
        holder.pages.setText("页码： "+libraryBookBeanList.get(position).getPages());
        holder.level.setText(libraryBookBeanList.get(position).getLevel());
        holder.price.setText("定价： "+libraryBookBeanList.get(position).getPrice());

        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView title, author, publisher, pubdate, pages, level, price;

        public ViewHolder(View itemView) {
            image = itemView.findViewById(R.id.bookimage);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            publisher = itemView.findViewById(R.id.publisher);
            pubdate = itemView.findViewById(R.id.pubdate);
            pages = itemView.findViewById(R.id.pages);
            level = itemView.findViewById(R.id.level);
            price = itemView.findViewById(R.id.price);

        }
    }
}