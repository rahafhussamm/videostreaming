package com.example.video_streming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.video_streming.databinding.ActivityMainBinding;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class MainActivity extends AppCompatActivity {
String VideoLink="https://firebasestorage.googleapis.com/v0/b/lec6-5885f.appspot.com/o/videoplayback.mp4?alt=media&token=2bfb7615-489f-4190-9c2b-17c7181706c5";
    ActivityMainBinding binding;
    PlayerView PV;
    SimpleExoPlayer player;
    boolean playWhenReady=true;
    long currentPosition =0;
    int currentWindow=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
     PV=binding.playerView;
    }
    private void initPlayer(){
        player = new SimpleExoPlayer.Builder(this).build();
        PV.setPlayer(player);
        MediaItem item =MediaItem.fromUri(VideoLink);
        player.setMediaItem(item);
player.setPlayWhenReady(playWhenReady);
player.seekTo(currentWindow,currentPosition);
player.prepare();
    }
    private void releasePlayer() {
        if (player!=null){
            playWhenReady=player.getPlayWhenReady();
            currentWindow= player.getCurrentWindowIndex();
            currentPosition=player.getCurrentPosition();
            player=null;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        initPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(player!=null){
            initPlayer();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }
}