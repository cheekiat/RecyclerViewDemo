package cheekiat.com.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Chee Kiat on 08/07/2017.
 */

public class LoadMoreAdapter extends CustomRecyclerViewAdapter {

    Context mContext;
    final int TYPE_CONTENT = 0;
    final int TYPE_LOADING = 1;

    public LoadMoreAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CONTENT:
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_texy, parent, false);
                return new ViewHolder(view);
            case TYPE_LOADING:
                View viewLoading = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_loading, parent, false);
                return new ViewHolder(viewLoading);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final String str = (String) objects.get(position);
        switch (getItemViewType(position)) {
            case TYPE_CONTENT:
                ((ViewHolder) holder).setData(position, str);

                break;
            case TYPE_LOADING:
                break;
        }

        if (position > getItemCount() - 3) {

            if (getIsCanLoadMore()) {
                onLoadMore.onLoadMore();
            }
        }

    }

    @Override
    public int getItemViewType(int position) {

        if (objects.get(position) == null) {
            return TYPE_LOADING;
        } else {
            return TYPE_CONTENT;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView name;
        public String str;
        public int position;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView) view.findViewById(R.id.text);
        }

        public void setData(int position, String str) {
            this.str = str;
            this.position = position;
            name.setText("" + position);
            mView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClick != null) {
                onItemClick.onItemClick(str, position, view);
            }
        }
    }


}
