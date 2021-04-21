package org.jbch.cab.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jbch.cab.R;
import org.jbch.cab.adapter.ChristianAdapter;
import org.jbch.cab.model.Christian;

import java.util.ArrayList;

public class RegisterFragment extends Fragment {

    private ChristianAdapter mAdapter;
    private ArrayList<Christian> mChristians;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mChristians = new ArrayList<Christian>();
        mAdapter = new ChristianAdapter(mChristians);

        View view = inflater.inflate(R.layout.register_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_register);
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
            Christian data = new Christian(String.format("등록-%d", i+1), "봉사회", "5교구", "52구역", "010-1111-2222");
            mChristians.add(data);
        }
        mAdapter.notifyDataSetChanged();
    }

}