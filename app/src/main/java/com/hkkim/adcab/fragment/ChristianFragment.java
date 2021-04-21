package com.hkkim.adcab.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.hkkim.adcab.activity.ChristianDetailActivity;
import com.hkkim.adcab.adapter.ChristianAdapter;
import com.hkkim.adcab.model.Christian;
import com.hkkim.adcab.viewmodel.ChristianViewModel;
import com.hkkim.adcab.R;

import java.util.ArrayList;

public class ChristianFragment extends Fragment implements ChristianAdapter.OnItemClickListener {

//    private ChristianViewModel mViewModel;
//    public static ChristianFragment newInstance() {
//        return new ChristianFragment();
//    }

    private ChristianAdapter mAdapter;
    private ArrayList<Christian> mChristians;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mChristians = new ArrayList<Christian>();
        mAdapter = new ChristianAdapter(mChristians);
        mAdapter.setOnItemClickListener(this);

        View view = inflater.inflate(R.layout.christian_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_christian);
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration decor = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(decor);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(ChristianViewModel.class);

        mChristians.clear();
        for (int i=0; i<10; ++i) {
            Christian data = new Christian(String.format("성도-%d", i+1), "봉사회", "5교구", "52구역", "010-1111-2222");
            mChristians.add(data);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View v, int position) {
        Log.d("hkkim", String.format("position: %d", position));

        Intent intent = new Intent(v.getContext(), ChristianDetailActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}