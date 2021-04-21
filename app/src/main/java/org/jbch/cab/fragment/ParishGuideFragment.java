package org.jbch.cab.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jbch.cab.R;


public class ParishGuideFragment extends Fragment {

//    private ParishGuideViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mViewModel = new ViewModelProvider(this).get(ParishGuideViewModel.class);

        StringBuilder content = new StringBuilder();

        View root = inflater.inflate(R.layout.parish_guide_fragment, container, false);

        WebView webView = root.findViewById(R.id.web_parishguide);
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
        content.append("<h1>교구 안내도...</h1></p></body></html>");

        webView.loadData(content.toString(), "text/html; charset=utf-8", "UTF-8");

//        final TextView textView = root.findViewById(R.id.text_parishguide);
//        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return root;
    }

}