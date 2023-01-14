package com.psyprogrammer.owlplayer.ui.home;

import android.media.Image;
import android.widget.Button;

import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.psyprogrammer.owlplayer.R;

public class HomeViewModel extends ViewModel {



    private MutableLiveData<String> mText;
   // private  MutableLiveData <Image> mImage;

//    HomeFragmentActivity homeFragmentActivity = new HomeFragmentActivity();
//


    public HomeViewModel() {
        mText = new MutableLiveData<>();
       // homeFragmentActivity.getMediaPlayer().getTrackInfo();
//        mText.setValue(HomeFragmentActivity.);

      //  mImage = new MutableLiveData<>();
     //  mImage.setValue("birds.xml");

    }




    public LiveData<String> getText() {
        return mText;
    }
}