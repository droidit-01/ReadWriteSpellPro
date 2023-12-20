package com.RogersCenter.readwritespell.langs;

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
import com.RogersCenter.readwritespell.dictionary.Dict_8_Activity;
import com.RogersCenter.readwritespell.quiz.Quiz1Activity;
import com.RogersCenter.readwritespell.spellings.Spell_13_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.View_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;

public class Lang_14_Activity extends AppCompatActivity {

    VideoView vv;
    View layoutvideoviev, layout_bottom_nav,layout_dictgame;
    View dict_click_v1,dict_click_v2, dict_click_v3;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    String[] strArray = {"exp_vids/lang_14_1.webm", "exp_vids/lang_14_2.webm", "exp_vids/lang_14_3.webm", "exp_vids/lang_14_4.webm", "exp_vids/lang_14_5.webm", "exp_vids/lang_14_6.webm",
            "exp_vids/lang_14_7.webm", "exp_vids/lang_14_8.webm", "exp_vids/lang_14_9.webm", "exp_vids/lang_14_10.webm", "exp_vids/lang_14_11.webm", "exp_vids/lang_14_12.webm","exp_vids/lang_14_13.webm",
            "exp_vids/lang_14_14.webm","exp_vids/lang_14_15.webm","exp_vids/lang_14_16.webm","exp_vids/lang_14_17.webm","exp_vids/lang_14_18.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_14);

        vv = findViewById(R.id.vv);
        layoutvideoviev = findViewById(R.id.layoutvideoviev);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        layout_dictgame = findViewById(R.id.layout_dictgame);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);

        dict_click_v1 = findViewById(R.id.dict_click_v1);
        dict_click_v2 = findViewById(R.id.dict_click_v2);
        dict_click_v3 = findViewById(R.id.dict_click_v3);

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
                image_anim(Lang_14_Activity.this,iv_back);
                stopvid();
                finish();
            }
        });

        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_14_Activity.this,iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                   checkfileexists();
                } else {
                    Toast.makeText(Lang_14_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_14_Activity.this,iv_playpause);
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
                image_anim(Lang_14_Activity.this,iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                   checkfileexists();
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Lang_14_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_14_Activity.this, Quiz1Activity.class);
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
                image_anim(Lang_14_Activity.this,iv_home);
                Intent i1 = new Intent(Lang_14_Activity.this, HomeActivity.class);
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

        if(position>=12)
        {
            layout_dictgame.setVisibility(View.VISIBLE);
            layout_bottom_nav.setVisibility(View.VISIBLE);
            dict_click_v2.setVisibility(View.GONE);
            iv_next.setClickable(false);
            iv_next.setEnabled(false);

            dict_click_v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!vv.isPlaying()) {
                        View_anim(Lang_14_Activity.this,dict_click_v1);
                        if (position == 12||position == 13||position == 14) {
                            position += 1;
                            if (position <= strArray.length - 1) {
//                        stopvid();
                               checkfileexists();
                            } else {
                                position -= 1;
                                Toast.makeText(Lang_14_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                                stopvid();
                                Intent i = new Intent(Lang_14_Activity.this, Quiz1Activity.class);
                                startActivity(i);
                                //finish();
                            }
                        } else {
                            MediaPlayer mPlayer2 = MediaPlayer.create(Lang_14_Activity.this, R.raw.tryagain);
                            mPlayer2.start();
                        }
                    }

                }
            });
            dict_click_v3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!vv.isPlaying()) {
                        View_anim(Lang_14_Activity.this,dict_click_v3);
                        if (position == 11||position == 15||position == 16) {
                            position += 1;
                            if (position <= strArray.length - 1) {
//                        stopvid();
                               checkfileexists();
                            } else {
                                position -= 1;
                                Toast.makeText(Lang_14_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                                stopvid();
                                Intent i = new Intent(Lang_14_Activity.this, Quiz1Activity.class);
                                startActivity(i);
                                //finish();
                            }
                        } else {
                            MediaPlayer mPlayer2 = MediaPlayer.create(Lang_14_Activity.this, R.raw.tryagain);
                            mPlayer2.start();
                        }
                    }

                }
            });


        }
        else {
            iv_next.setClickable(true);
            iv_next.setEnabled(true);

            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Lang_14_Activity.this,iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
        }
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
                    tv_lastpage_text.setText("You Completed Language Lesson 14");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Lang_14_Activity","YES", Lang_14_Activity.this);
                            Intent intent = new Intent(Lang_14_Activity.this, Spell_13_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }
    public void checkfileexists() {
            if (StaticValues.GetPreferences("Last_Open_position", Lang_14_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Lang_14_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Lang_14_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Lang_14_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Lang_14_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_1_Activity", Lang_14_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_14_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_14_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_1_Activity", Lang_14_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_14_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_14_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_1_Activity", Lang_14_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_14_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_14_Activity.this);
    }
}