package org.jbch.cab.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WorldMissionViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public WorldMissionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is world mission fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
