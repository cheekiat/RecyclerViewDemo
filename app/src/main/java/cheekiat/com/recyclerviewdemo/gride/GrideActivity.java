package cheekiat.com.recyclerviewdemo.gride;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cheekiat.com.recyclerviewdemo.CustomDividerItemDecoration;
import cheekiat.com.recyclerviewdemo.CustomRecyclerViewAdapter;
import cheekiat.com.recyclerviewdemo.LoadMoreAdapter;
import cheekiat.com.recyclerviewdemo.R;
import cheekiat.com.recyclerviewdemo.SpaceItemDecoration;

public class GrideActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    LoadMoreAdapter adapter;
    boolean isLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if (isLast != true && position == adapter.getItemCount() - 1) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
        adapter = new LoadMoreAdapter(this);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new CustomRecyclerViewAdapter.RecyclerViewAdapterListener.OnLoadMore() {
            @Override
            public void onLoadMore() {

                if (adapter.getIsCanLoadMore()) {
                    adapter.setLoadMore(false);
                } else {
                    return;
                }
                callApi();
            }
        });

        callApi();
    }

    void addDataToList(List<Object> strs) {

        if (adapter.getItemCount() != 0) {
            adapter.remove(adapter.getItemCount() - 1);
        }

        adapter.addAll(strs);

        if (adapter.getItemCount() < 50) {
            adapter.add(null);
            adapter.setLoadMore(true);
        } else {
            isLast = true;
            adapter.setLoadMore(false);
        }
    }

    void callApi() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                List<Object> strs = new ArrayList<>();
                strs.add("A");
                strs.add("B");
                strs.add("C");
                strs.add("D");
                strs.add("E");
                strs.add("F");
                strs.add("G");

                addDataToList(strs);

            }
        }, 2000);
    }
}
