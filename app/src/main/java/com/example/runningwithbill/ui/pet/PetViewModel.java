package com.example.runningwithbill.ui.pet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PetViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PetViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is pet fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}