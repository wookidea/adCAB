package org.jbch.cab.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppInfoViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public AppInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is app info fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}