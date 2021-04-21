package com.hkkim.adcab.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hkkim.adcab.viewmodel.AppInfoViewModel;
import com.hkkim.adcab.R;

import java.io.InputStream;

public class AppInfoFragment extends Fragment {

//    private AppInfoViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mViewModel = new ViewModelProvider(this).get(AppInfoViewModel.class);

        View root = inflater.inflate(R.layout.app_info_fragment, container, false);

//        final TextView textView = root.findViewById(R.id.text_appinfo);
//        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        AssetManager am = getResources().getAssets();
        InputStream is = null;

        try {
            is = am.open("books.jpg");
            Bitmap bm = BitmapFactory.decodeStream(is);
            ImageView imageView = root.findViewById(R.id.imageApp_image);
            imageView.setImageBitmap(bm);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (is != null) {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return root;
    }

}