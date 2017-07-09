package cheekiat.com.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cheekiat.com.recyclerviewdemo.divider.DividerActivity;
import cheekiat.com.recyclerviewdemo.gride.GrideActivity;
import cheekiat.com.recyclerviewdemo.loadmore.LoadMoreActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.load_more).setOnClickListener(this);
        findViewById(R.id.divider).setOnClickListener(this);
        findViewById(R.id.gride).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.load_more:
                intent = new Intent(this, LoadMoreActivity.class);
                startActivity(intent);
                break;
            case R.id.divider:
                intent = new Intent(this, DividerActivity.class);
                startActivity(intent);
                break;
            case R.id.gride:
                intent = new Intent(this, GrideActivity.class);
                startActivity(intent);
                break;
        }
    }
}
