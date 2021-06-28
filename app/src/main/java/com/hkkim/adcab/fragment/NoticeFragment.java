package com.hkkim.adcab.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.hkkim.adcab.viewmodel.NoticeViewModel;
import com.hkkim.adcab.R;

public class NoticeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        NoticeViewModel mViewModel = new ViewModelProvider(this).get(NoticeViewModel.class);

        StringBuilder content = new StringBuilder();

        View root = inflater.inflate(R.layout.notice_fragment, container, false);

        WebView webView = root.findViewById(R.id.web_notice);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setLoadWithOverviewMode(true);

        content.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">");
        content.append("<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">");
        content.append("p, li { white-space: pre-wrap; }");
        content.append("</style></head><body style=\" font-family:'나눔고딕'; font-size:13px; font-weight:400; font-style:normal;\">");
        content.append("<p align=\"center\" style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">");
        content.append("<h1>공지 사항...</h1></p></body></html>");

        webView.loadData(content.toString(), "text/html; charset=utf-8", "UTF-8");

//        final TextView textView = root.findViewById(R.id.text_notice);
//        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return root;
    }

}