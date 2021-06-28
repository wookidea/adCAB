package com.hkkim.adcab.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YearPlanViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public YearPlanViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is year plan fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
