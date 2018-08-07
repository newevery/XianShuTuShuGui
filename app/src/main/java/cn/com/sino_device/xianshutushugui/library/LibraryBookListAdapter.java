package cn.com.sino_device.xianshutushugui.library;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.bean.book.LibraryBookBean;

/**
 * author： bwl on 2016-04-11.
 * email: bxl049@163.com
 */
public class LibraryBookListAdapter extends RecyclerView.Adapter {
    private final int TYPE_NORMAL = 0;
    private final int TYPE_FOOT = 1;
    private Context mContext;
    private List<LibraryBookBean> mDatas = new ArrayList<>();
    private OnItemClickLitener mOnItemClickLitener;
    private boolean isLoading;
    private String mError = null;
    private String TAG = "LibraryBookListAdapter__ ";

    public LibraryBookListAdapter(Context context) {
        this.mContext = context;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public void addDatas(List<LibraryBookBean> datas) {
        mDatas.addAll(datas);
    }

    public void setDatas(List<LibraryBookBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        Log.i(TAG, datas.size()+"");
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOT;
        }
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.librarybooklist_item, parent, false);
            return new ItemViewHolder(view);
        }
        if (viewType == TYPE_FOOT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.librarybook_item_footer, parent, false);
            return new FootHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).title.setText(mDatas.get(position).getName());
            ((ItemViewHolder) holder).author.setText(mDatas.get(position).getAuthor());
            ((ItemViewHolder) holder).publisher.setText(mDatas.get(position).getType());
//            ((ItemViewHolder) holder).title.setText(mDatas.get(position).getTitle()) ImageLoader.getInstance().displayImage(libraryBookBeanList.get(position).getImages(), holder.image);;
//            ((ItemViewHolder) holder).content.setText(mDatas.get(position).getContent());
//            ((ItemViewHolder) holder).date.setText(mDatas.get(position).getDate());
//            if (mDatas.get(position).getImgLink() != null) {
//                ((ItemViewHolder) holder).icon.setVisibility(View.VISIBLE);
//                Picasso.with(mContext).load(mDatas.get(position).getImgLink()).placeholder(R.mipmap.news_default_icon)
//                        .error(R.mipmap.news_default_icon).into(((ItemViewHolder) holder).icon);
//            }else{
//                ((ItemViewHolder) holder).icon.setVisibility(View.GONE);
//            }
            // 如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }

        }
        if (holder instanceof FootHolder) {
            ((FootHolder) holder).foot.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if (mError != null) {
                ((FootHolder) holder).progressBar.setVisibility(View.GONE);
                ((FootHolder) holder).message.setText(mError);
            } else {
                ((FootHolder) holder).progressBar.setVisibility(View.VISIBLE);
                ((FootHolder) holder).message.setText("加载中....");
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, author, publisher;


        public ItemViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.bookimage);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            publisher = itemView.findViewById(R.id.publisher);

        }
    }

    class FootHolder extends RecyclerView.ViewHolder {
        LinearLayout foot;
        ProgressWheel progressBar;
        TextView message;

        public FootHolder(View itemView) {
            super(itemView);
            foot = itemView.findViewById(R.id.item_library_foot);
            progressBar = itemView.findViewById(R.id.item_librarybook_progressbar);
            message = itemView.findViewById(R.id.item_librarybook_message);
        }
    }


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    public List<LibraryBookBean> getDatas() {
        return mDatas;
    }

    public String getmError() {
        return mError;
    }

    public void setmError(String mError) {
        this.mError = mError;
    }
}
