package cheekiat.com.recyclerviewdemo.loadmore;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cheekiat.com.recyclerviewdemo.CustomRecyclerViewAdapter;
import cheekiat.com.recyclerviewdemo.LoadMoreAdapter;
import cheekiat.com.recyclerviewdemo.R;

public class LoadMoreActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    LoadMoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
        }else{
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
