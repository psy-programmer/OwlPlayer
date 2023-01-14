package com.psyprogrammer.owlplayer.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.psyprogrammer.owlplayer.R;

public class InfoFragment extends Fragment {

    private InfoViewModel infoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        infoViewModel =
                new ViewModelProvider(this).get(InfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_info, container, false);
        final TextView textView = root.findViewById(R.id.info_me);
        infoViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText("Hi!" +
                " My name is Zoya Klocheva, I am an Android developer. " +
                "This is my non-commercial home project.\n" +
                "I am constantly learning, developing, creating something new. " +
                "If you have any questions about cooperation, you can write here:\n"));


        return root;

    }
}