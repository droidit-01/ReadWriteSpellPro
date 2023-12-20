package com.RogersCenter.readwritespell;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.RogersCenter.readwritespell.quiz.Quiz1Activity;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();

    ImageView ivBack,ivPrevious,ivRewind,ivPlaypause,ivForward,ivNext,ivHome;
    int position = 0;
    ConstraintLayout constraintLayout2;
    VideoView vv;
    private MediaPlayer mPlayer2;

    public static void createNotificationChannel(Context context) {
        // Notification channels are only available in Oreo (API 26) and higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);

            // Define the notification channel
            String channelId = "your_channel_id";
            CharSequence channelName = "Your Channel Name";
            String channelDescription = "Your Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            channel.setDescription(channelDescription);

            // Register the channel with the system
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBack = findViewById(R.id.iv_back);
        ivPrevious = findViewById(R.id.iv_previous);
        ivRewind = findViewById(R.id.iv_rewind);
        ivPlaypause = findViewById(R.id.iv_playpause);
        ivForward = findViewById(R.id.iv_forward);
        ivNext = findViewById(R.id.iv_next);
        ivHome = findViewById(R.id.iv_home);
        constraintLayout2 = findViewById(R.id.constraintLayout2);
        vv = findViewById(R.id.vv);

        position = 0;
        initializePlayer(position, listURI, sound_list);



    }

    private void initializePlayer(int pos, List<Uri> urilist, List<String> mysound_list) {

        vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + urilist.get(pos)));
//        vv.setVideoURI(urilist.get(pos));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vv);
        vv.setMediaController(mediaController);
        vv.requestFocus();
        vv.start();
//        vv.setZOrderOnTop(false);
//        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            // Close the progress bar and play the video
//            public void onPrepared(MediaPlayer mp) {
//                vv.start();
//            }
//        });
        vv.setMediaController(null);
//
//        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setVolume(0, 0);
//            }
//        });

//        int resID=getResources().getIdentifier(mysound_list.get(pos), "raw", getPackageName());
//        mPlayer2  = MediaPlayer.create(MainActivity.this, resID);
//        mPlayer2.start();
//        mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                Log.d("MEDIA", "" + mediaPlayer);
//
//            }
//        });

//        defaultBandwidthMeter = new DefaultBandwidthMeter();
//        factory = new AdaptiveTrackSelection.Factory();
//        trackSelector = new DefaultTrackSelector(factory);
//        loadControl = new DefaultLoadControl();
////
//        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
//        playerView.setPlayer(player);
//        DefaultBandwidthMeter dBandwidthMeter = new DefaultBandwidthMeter();
//        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
//                Util.getUserAgent(this, "com.exoplayerdemo"), dBandwidthMeter);
//        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
//        // below line you can pass video url
//        MediaSource mediaSource = new ExtractorMediaSource(urilist.get(pos),
//                dataSourceFactory, extractorsFactory, null, null);
//
//        LoopingMediaSource loopingMediaSource = new LoopingMediaSource(mediaSource);
//        player.prepare(mediaSource);
//        player.setPlayWhenReady(true);
//        player.setVolume(5);


//        player = new SimpleExoPlayer.Builder(MainActivity.this).build();
//        DefaultDataSourceFactory defaultDataSourceFactory=new DefaultDataSourceFactory(this, Util.getUserAgent(this,"ReadWriteSpell"));
//        player.setPlayWhenReady(true);
//        ExtractorMediaSource extractorMediaSource=new ExtractorMediaSource.Factory(defaultDataSourceFactory)
//                .createMediaSource(urilist.get(pos));
//        player.prepare(extractorMediaSource);
//        playerView.setPlayer(player);


//
//        MediaItem mediaItem = MediaItem.fromUri(urilist.get(pos));

        // With this
//        MediaItem mediaItem = new MediaItem.Builder()
//                .setUri(urilist.get(pos))
//                .setMimeType(MimeTypes.APPLICATION_MPD)
//                .build();

//        player.setMediaItem(mediaItem);
//        player.setPlayWhenReady(true);
//        player.prepare();
//
//        playerView.setPlayer(player);


        Log.d("POS", pos + "  " + urilist.get(pos));
//        String videoPath = RawResourceDataSource.buildRawResourceUri(R.raw.abcd).toString();

//        Uri uri = RawResourceDataSource.buildRawResourceUri(R.raw.abcd);

//        ExtractorMediaSource audioSource = new ExtractorMediaSource(
//                urilist.get(pos),
//                new DefaultDataSourceFactory(MainActivity.this, "MyExoplayer"),
//                new DefaultExtractorsFactory(),
//                null,
//                null
//        );


//        player.prepare(audioSource);
//        playerView.setPlayer(player);
//        player.setPlayWhenReady(true);
//        player.prepare();
//        playerView.hideController();
//        playerView.setControllerVisibilityListener(new PlayerControlView.VisibilityListener() {
//            @Override
//            public void onVisibilityChange(int visibility) {
//                if (visibility == View.VISIBLE) {
//                    playerView.hideController();
//                }
//            }
//        });

//        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT);

    }


    @OnClick({R.id.iv_back, R.id.iv_previous, R.id.iv_rewind, R.id.iv_playpause, R.id.iv_forward, R.id.iv_next, R.id.iv_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ivBack.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.image_click));
                stopvid();
                finish();
                break;
            case R.id.iv_previous:
                ivPrevious.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.image_click));
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                    initializePlayer(position, listURI, sound_list);
                } else {
                    Toast.makeText(MainActivity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.iv_rewind:
                ivRewind.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.image_click));
                vv.seekTo(vv.getCurrentPosition() - 5000);
                break;
            case R.id.iv_playpause:
                ivPlaypause.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.image_click));
                if (vv.isPlaying()) {
                    pausePlayer();
                    ivPlaypause.setImageResource(R.drawable.ic_pause);
                } else {
                    resumetPlayer();
                    ivPlaypause.setImageResource(R.drawable.ic_play);
                }
//                player.setPlayWhenReady(!player.getPlayWhenReady());
                break;
            case R.id.iv_forward:
                ivForward.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.image_click));
                vv.seekTo(vv.getCurrentPosition() + 5000);
                break;
            case R.id.iv_next:
                ivNext.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.image_click));
                position += 1;
                if (position <= listURI.size() - 1) {
//                    stopvid();

                    initializePlayer(position, listURI, sound_list);
                } else {
                    position -= 1;
                    Toast.makeText(MainActivity.this, "This is last video", Toast.LENGTH_SHORT).show();
                    stopvid();
                    Intent i = new Intent(MainActivity.this, Quiz1Activity.class);
                    startActivity(i);
                    finish();
                }
                break;
            case R.id.iv_home:
                ivHome.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.image_click));
                stopvid();
                Intent i2 = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i2);
                finish();
                break;
        }
    }

    public void stopvid() {
        if (vv != null)
            vv.stopPlayback();
    }

    private void pausePlayer() {
        if (vv.isPlaying()) {
            vv.pause();
        }
//        player.getPlaybackState();
    }

    private void resumetPlayer() {
        vv.start();
//        player.setPlayWhenReady(true);
//        player.getPlaybackState();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopvid();
    }
}