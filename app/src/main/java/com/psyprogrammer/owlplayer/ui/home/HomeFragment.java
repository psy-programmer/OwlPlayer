package com.psyprogrammer.owlplayer.ui.home;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.psyprogrammer.owlplayer.R;


import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    MediaPlayer mediaPlayer;

    public SeekBar getSeekBar() {
        return seekBar;
    }

    public void setSeekBar(SeekBar seekBar) {
        this.seekBar = seekBar;
    }

    SeekBar seekBar;
    Button playButton;
    Button nextButton;
    Button prevButton;

    int trackId;
    TextView songName;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.name_song);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });
        seekBar = root.findViewById(R.id.seekBar);
        playButton = root.findViewById(R.id.play);
        nextButton = root.findViewById(R.id.next);
        prevButton = root.findViewById(R.id.prev);
        songName = root.findViewById(R.id.name_song);

        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sonata);
        songName.setText("");
        seekBar.setMax(mediaPlayer.getDuration());
        seekListener();

        playButton.setOnClickListener(view -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                if (trackId == 0){
                songName.setText(R.string.sonataName);}
                else songName.setText(R.string.mozartName);
                playButton.setText("Pause");
            } else {
                mediaPlayer.pause();
                playButton.setText("Play");
            }
        });

        nextButton.setOnClickListener(view -> {
            buttonSettings();
        });

        prevButton.setOnClickListener(view -> {
            buttonSettings();
        });

        return root;
    }

    public void seekListener () {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, 0, 1000);
    }

    public void buttonSettings() {
        if (mediaPlayer.isPlaying() && trackId == 0) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.mozart);
            songName.setText(R.string.mozartName);
            seekBar.setMax(mediaPlayer.getDuration());
            mediaPlayer.start();
            trackId = 1;
        } else if (trackId == 1){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sonata);
            songName.setText(R.string.sonataName);
            seekBar.setMax(mediaPlayer.getDuration());
            trackId = 0;
            mediaPlayer.start();
        }
        else mediaPlayer.pause();
    }
}