package com.example.jin.canteen.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jin.canteen.R;
import com.example.jin.canteen.Request.CanteenRequest;
import com.example.jin.canteen.ShoppingCartActivity;
import com.example.jin.canteen.adapter.CanteenAdapter;
import com.example.jin.canteen.bean.Canteen;

import java.util.List;

public class CanteenActivity extends AppCompatActivity {
    private List<Canteen> canteens;
    private ListView canteenlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen);
        canteens= CanteenRequest.getallcanteens();
        canteenlist=(ListView)findViewById(R.id.canteenlist);
        CanteenAdapter canteenAdapter=new CanteenAdapter(this,canteens);
        canteenlist.setAdapter(canteenAdapter);
        canteenlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(CanteenActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

    }
}
