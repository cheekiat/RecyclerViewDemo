package cheekiat.com.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chee Kiat on 06/07/2017.
 */

public abstract class CustomRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<Object> objects = new ArrayList<>();
    public RecyclerViewAdapterListener.OnItemClick onItemClick;
    public RecyclerViewAdapterListener.OnLoadMore onLoadMore;
    private boolean isLoadMore;

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public void setOnItemClickListener(RecyclerViewAdapterListener.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setOnLoadMoreListener(RecyclerViewAdapterListener.OnLoadMore onLoadMore) {
        this.onLoadMore = onLoadMore;
    }

    public void add(Object object) {
        this.objects.add(object);
        notifyDataSetChanged();
    }

    public void addAll(List<Object> objects) {
        this.objects.addAll(objects);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        this.objects.remove(position);
        notifyDataSetChanged();
    }

    public interface RecyclerViewAdapterListener {

        void setOnItemClickListener(OnItemClick onItemClick);

        void setOnLoadMoreListener(OnLoadMore onLoadMore);

        public interface OnLoadMore {
            void onLoadMore();
        }

        public interface OnItemClick {
            void onItemClick(Object object, int position, View view);
        }
    }

    public void setLoadMore(boolean isLoadMore) {
        this.isLoadMore = isLoadMore;
    }

    public boolean getIsCanLoadMore() {

        if (getItemCount() == 0) {
            return false;
        }
        return isLoadMore;
    }
}

