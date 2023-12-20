package com.RogersCenter.readwritespell.langs;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.RogersCenter.readwritespell.CustomAPEZProvider;
import com.RogersCenter.readwritespell.HomeActivity;
import com.RogersCenter.readwritespell.R;
import com.RogersCenter.readwritespell.StaticValues;
import com.RogersCenter.readwritespell.quiz.Quiz1Activity;
import com.RogersCenter.readwritespell.spellings.Spell_10_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_11_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;

public class Lang_12_Activity extends AppCompatActivity {
    VideoView vv;
    View layout_bottom_nav, view_cled;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    String[] strArray = {"exp_vids/lang_12_1.webm", "exp_vids/lang_12_2_cl.webm", "exp_vids/lang_12_3.webm", "exp_vids/lang_12_4_cl.webm", "exp_vids/lang_12_5.webm", "exp_vids/lang_12_6_cl.webm",
            "exp_vids/lang_12_7.webm", "exp_vids/lang_12_8_cl.webm", "exp_vids/lang_12_9.webm", "exp_vids/lang_12_10_cl.webm", "exp_vids/lang_12_11.webm", "exp_vids/lang_12_12_cl.webm", "exp_vids/lang_12_13.webm",
            "exp_vids/lang_12_14_cl.webm", "exp_vids/lang_12_15.webm", "exp_vids/lang_12_16_cl.webm", "exp_vids/lang_12_17.webm", "exp_vids/lang_12_18_cl.webm", "exp_vids/lang_12_19.webm", "exp_vids/lang_12_20_cl.webm",
            "exp_vids/lang_12_21.webm", "exp_vids/lang_12_22.webm", "exp_vids/lang_12_23.webm", "exp_vids/lang_12_24.webm", "exp_vids/lang_12_25.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_12);

        vv = findViewById(R.id.vv);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);
        view_cled = findViewById(R.id.view_clicked);

        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
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
                image_anim(Lang_12_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });

        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_12_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                   checkfileexists();
                } else {
                    Toast.makeText(Lang_12_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_12_Activity.this, iv_playpause);
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
                image_anim(Lang_12_Activity.this, iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                   checkfileexists();
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Lang_12_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_12_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                            //finish();
                        }
                    });

                }
            }
        });

        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_12_Activity.this, iv_home);
                Intent i1 = new Intent(Lang_12_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        view_cled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 1 || position == 3 || position == 5 || position == 7 || position == 9 || position == 11 || position == 13
                            || position == 15 || position == 17 || position == 19) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_12_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_12_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_12_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
    }

    private void initializePlayer(int pos, List<Uri> urilist, List<String> mysound_list) {

        if (position == 1 || position == 3 || position == 5 || position == 7 || position == 9 || position == 11 || position == 13
                || position == 15 || position == 17 || position == 19) {
            view_cled.setVisibility(View.VISIBLE);
        } else {
            view_cled.setVisibility(View.GONE);

            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Lang_12_Activity.this, iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
        }
//        vv.setVideoPath("http://techslides.com/demos/sample-videos/small.webm");
//        vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + urilist.get(pos)));
        vv.setVideoURI(urilist.get(pos));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vv);
        vv.setMediaController(mediaController);
        vv.requestFocus();
        vv.start();
        vv.setMediaController(null);
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (progressDoalog1!=null)
                {
                    progressDoalog1.dismiss();
                }
                iv_playpause.setImageResource(R.drawable.ic_play);
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
                    tv_lastpage_text.setText("You Completed Language Lesson 12");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Lang_12_Activity","YES", Lang_12_Activity.this);
                            Intent intent = new Intent(Lang_12_Activity.this, Spell_11_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }
    public void checkfileexists() {

            if (StaticValues.GetPreferences("Last_Open_position", Lang_12_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Lang_12_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Lang_12_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Lang_12_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Lang_12_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_12_Activity", Lang_12_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_12_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_12_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_12_Activity", Lang_12_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_12_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_12_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_12_Activity", Lang_12_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_12_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_12_Activity.this);
    }
}