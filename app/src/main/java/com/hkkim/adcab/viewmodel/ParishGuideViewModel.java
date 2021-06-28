package com.hkkim.adcab.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ParishGuideViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ParishGuideViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is parish guide fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}