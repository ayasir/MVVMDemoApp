package com.example.mvvmdemo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;



public class MainActivityViewModel extends ViewModel {

    public String TAG = this.getClass().getSimpleName();
    private MutableLiveData<String> mRandomNumber;

    public MutableLiveData<String> getmRandomNumber(){

        Log.i(TAG, "getmRandomNumber: ");

        if(mRandomNumber == null){
            createRandomNumber();
        }
        return mRandomNumber;
    }

    public void createRandomNumber() {
        Log.i(TAG, "createRandomNumber: ");
        Random random = new Random();
        mRandomNumber.setValue("Number: "+(random.nextInt(10-1)+1));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "onCleared: ViewModel Destroyed");
    }
}
