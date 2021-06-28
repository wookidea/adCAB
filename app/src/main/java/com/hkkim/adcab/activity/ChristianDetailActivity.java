package com.hkkim.adcab.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import com.hkkim.adcab.R;
import com.hkkim.adcab.adapter.ChristianAdapter;
import com.hkkim.adcab.model.Christian;

import java.util.ArrayList;

public class ChristianDetailActivity extends AppCompatActivity implements ChristianAdapter.OnItemClickListener {

    private ChristianAdapter mAdapter;
    private ArrayList<Christian> mChristians;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_christian_detail);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mChristians = new ArrayList<Christian>();
        mAdapter = new ChristianAdapter(mChristians);
        mAdapter.setOnItemClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_detail);
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration decor = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(decor);

        mChristians.clear();
        for (int i=0; i<10; ++i) {
            Christian data = new Christian(String.format("상세성도-%d", i+1), "은장회", "8교구", "81구역", "010-3245-6436");
            mChristians.add(data);
        }
        mAdapter.notifyDataSetChanged();

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        Log.d("hkkim", String.format("position: %d", position));
    }

    @Override
    public void onItemClick(View v, int position) {
        Log.d("hkkim", String.format("position: %d", position));
    }
}