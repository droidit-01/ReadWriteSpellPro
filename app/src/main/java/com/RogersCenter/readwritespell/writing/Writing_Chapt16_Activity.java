package com.RogersCenter.readwritespell.writing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.RogersCenter.readwritespell.CustomAPEZProvider;
import com.RogersCenter.readwritespell.HomeActivity;
import com.RogersCenter.readwritespell.R;
import com.RogersCenter.readwritespell.StaticValues;
import com.RogersCenter.readwritespell.dictionary.Dict_9_Activity;
import com.RogersCenter.readwritespell.langs.Lang_21_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;

public class Writing_Chapt16_Activity extends AppCompatActivity {

    VideoView vv;
    View layout_keyboard, layout_bottom_nav;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    String[] strArray = {"exp_vids/writing_intro_16.webm", "exp_vids/writing_brushes_1.webm", "exp_vids/writing_brushes_2.webm", "exp_vids/writing_brushes_3.webm", "exp_vids/writing_acted_1.webm", "exp_vids/writing_acted_2.webm",
            "exp_vids/writing_childish_1.webm", "exp_vids/writing_childish_2.webm", "exp_vids/writing_hopeful_1.webm", "exp_vids/writing_hopeful_2.webm", "exp_vids/writing_silently_1.webm", "exp_vids/writing_silently_2.webm",
            "exp_vids/writing_replay_1.webm", "exp_vids/writing_replay_2.webm", "exp_vids/writing_minibike_1.webm", "exp_vids/writing_minibike_2.webm", "exp_vids/writing_semimonthly_1.webm", "exp_vids/writing_semimonthly_2.webm",
            "exp_vids/writing_end_16.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_chapt16);

        vv = findViewById(R.id.vv);
        layout_keyboard = findViewById(R.id.layout_keyboard);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);

        for (int i = 0; i < strArray.length; i++) {
            Uri uri = CustomAPEZProvider.buildUri("" + strArray[i]);
            listURI.add(i, uri);
        }
        if (!listURI.isEmpty())
        {
            checkfileexists();
        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Writing_Chapt16_Activity.this,iv_back);
                stopvid();
                finish();
            }
        });

        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Writing_Chapt16_Activity.this,iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                   checkfileexists();
                } else {
                    Toast.makeText(Writing_Chapt16_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Writing_Chapt16_Activity.this,iv_playpause);
                if (vv.isPlaying()) {
                    pausePlayer();
                    iv_playpause.setImageResource(R.drawable.ic_play);
                } else {
                    resumetPlayer();
                    iv_playpause.setImageResource(R.drawable.ic_pause);

                }
            }
        });

        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Writing_Chapt16_Activity.this,iv_next);
                position += 1;
                if (position <= strArray.length - 1)  {
                    stopvid();
                   checkfileexists();
                } else {
                    position -= 1;
                    View layout_lastpage  = findViewById(R.id.layout_lastpage);
                    TextView tv_lastpage_text= findViewById(R.id.tv_lastpage_text);
                    ImageButton ib_home= findViewById(R.id.ib_home);
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            layout_lastpage.setVisibility(View.VISIBLE);
                            tv_lastpage_text.setText("You Completed Writing Lesson 16");
                            ib_home.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    StaticValues.SavePreferences("Writing_Chapt16_Activity","YES", Writing_Chapt16_Activity.this);
                                    Intent intent = new Intent(Writing_Chapt16_Activity.this, Lang_21_Activity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    });
                }
            }
        });

        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Writing_Chapt16_Activity.this,iv_home);
                Intent i1 = new Intent(Writing_Chapt16_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

    }
  
    private void initializePlayer(int pos, List<Uri> urilist, List<String> mysound_list) {

//        vv.setVideoPath("http://techslides.com/demos/sample-videos/small.webm");
//        vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + urilist.get(pos)));
        vv.setVideoURI(urilist.get(pos));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vv);
        vv.setMediaController(mediaController);
        vv.requestFocus();
        vv.start();
        vv.setMediaController(null);

        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                image_anim_last(Writing_Chapt16_Activity.this,iv_next);
                iv_playpause.setImageResource(R.drawable.ic_play);
            }
        });
//        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setVolume(0, 0);
//            }
//        });
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (progressDoalog1!=null)
                {
                    progressDoalog1.dismiss();
                }
                iv_playpause.setImageResource(R.drawable.ic_pause);
            }
        });
        Log.d("POS", pos + "  " + urilist.get(pos));
        if(position==strArray.length-1)
        {
            View layout_lastpage  = findViewById(R.id.layout_lastpage);
            TextView tv_lastpage_text= findViewById(R.id.tv_lastpage_text);
            ImageButton ib_home= findViewById(R.id.ib_home);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    layout_lastpage.setVisibility(View.VISIBLE);
                    tv_lastpage_text.setText("You Completed Writing Lesson 16");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Writing_Chapt16_Activity","YES", Writing_Chapt16_Activity.this);
                            Intent intent = new Intent(Writing_Chapt16_Activity.this, Lang_21_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }
    public void checkfileexists() {

            if (StaticValues.GetPreferences("Last_Open_position", Writing_Chapt16_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Writing_Chapt16_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Writing_Chapt16_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Writing_Chapt16_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Writing_Chapt16_Activity.this);
            }
            initializePlayer(position, listURI, sound_list);

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
        StaticValues.SavePreferences("Last_Open_Lesson", "Writing_Chapt16_Activity", Writing_Chapt16_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Writing", Writing_Chapt16_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Writing_Chapt16_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Writing_Chapt16_Activity", Writing_Chapt16_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Writing", Writing_Chapt16_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Writing_Chapt16_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Writing_Chapt16_Activity", Writing_Chapt16_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Writing", Writing_Chapt16_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Writing_Chapt16_Activity.this);
    }
}