package com.RogersCenter.readwritespell.dictionary;

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
import android.os.Handler;
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
import com.RogersCenter.readwritespell.langs.Lang_6_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;

public class Dict_2_Activity extends AppCompatActivity {

    VideoView vv;
    View layout_bottom_nav;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    View clickview;
    String[] strArray = {"exp_vids/dict_2_1.webm", "exp_vids/dict_2_2.webm", "exp_vids/dict_2_3.webm", "exp_vids/dict_2_4.webm", "exp_vids/dict_2_5.webm", "exp_vids/dict_2_6.webm",
            "exp_vids/dict_2_7.webm", "exp_vids/dict_2_8.webm", "exp_vids/dict_2_9.webm", "exp_vids/dict_2_10.webm", "exp_vids/dict_2_11.webm", "exp_vids/dict_2_12.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dict_2);

        vv = findViewById(R.id.vv);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);
        clickview = findViewById(R.id.clickview);

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
                image_anim(Dict_2_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });

        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Dict_2_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                    checkfileexists();
                } else {
                    Toast.makeText(Dict_2_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Dict_2_Activity.this, iv_playpause);
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
                image_anim(Dict_2_Activity.this, iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                    checkfileexists();
                } else {
                    position -= 1;
                    if (position == strArray.length - 1) {
                        View layout_lastpage = findViewById(R.id.layout_lastpage);
                        TextView tv_lastpage_text = findViewById(R.id.tv_lastpage_text);
                        ImageButton ib_home = findViewById(R.id.ib_home);
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                layout_lastpage.setVisibility(View.VISIBLE);
                                tv_lastpage_text.setText("You Completed Dictionary Lesson 2");
                                ib_home.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        StaticValues.SavePreferences("Dict_2_Activity","YES",Dict_2_Activity.this);
                                        Intent intent = new Intent(Dict_2_Activity.this, Lang_6_Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        });
                    }

                }
            }
        });

        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Dict_2_Activity.this, iv_home);
                Intent i1 = new Intent(Dict_2_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

    }

    private void initializePlayer(int pos, List<Uri> urilist, List<String> mysound_list) {

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
                if (progressDoalog1 != null) {
                    progressDoalog1.dismiss();
                }
                iv_playpause.setImageResource(R.drawable.ic_pause);
            }
        });
        if (position == 4 || position == 5 || position == 7 || position == 8) {
            clickview.setVisibility(View.VISIBLE);
            iv_next.setClickable(false);
            iv_next.setEnabled(false);
            clickview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position += 1;
                    if (position <= strArray.length - 1) {
                        stopvid();
                        checkfileexists();
                        if (position == strArray.length - 1) {
                            View layout_lastpage = findViewById(R.id.layout_lastpage);
                            TextView tv_lastpage_text = findViewById(R.id.tv_lastpage_text);
                            ImageButton ib_home = findViewById(R.id.ib_home);
                            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    layout_lastpage.setVisibility(View.VISIBLE);
                                    tv_lastpage_text.setText("You Completed Dictionary Lesson 2");
                                    ib_home.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            StaticValues.SavePreferences("Dict_2_Activity","YES",Dict_2_Activity.this);
                                            Intent intent = new Intent(Dict_2_Activity.this, Lang_6_Activity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                }
                            });
                        }
                    } else {
                        position -= 1;
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(Dict_2_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Dict_2_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
//                                finish();
                            }
                        });

                    }
                }
            });
        } else {
            clickview.setVisibility(View.GONE);
            iv_next.setClickable(true);
            iv_next.setEnabled(true);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Dict_2_Activity.this, iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
        }

        if (position == strArray.length - 1) {
            View layout_lastpage = findViewById(R.id.layout_lastpage);
            TextView tv_lastpage_text = findViewById(R.id.tv_lastpage_text);
            ImageButton ib_home = findViewById(R.id.ib_home);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    layout_lastpage.setVisibility(View.VISIBLE);
                    tv_lastpage_text.setText("You Completed Dictionary Lesson 2");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Dict_2_Activity","YES",Dict_2_Activity.this);
                            Intent intent = new Intent(Dict_2_Activity.this, Lang_6_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
        Log.d("POS", pos + "  " + urilist.get(pos));

    }

    public void checkfileexists() {
            if (StaticValues.GetPreferences("Last_Open_position", Dict_2_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Dict_2_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Dict_2_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Dict_2_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Dict_2_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Dict_2_Activity", Dict_2_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Dictionary", Dict_2_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Dict_2_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Dict_2_Activity", Dict_2_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Dictionary", Dict_2_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Dict_2_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Dict_2_Activity", Dict_2_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Dictionary", Dict_2_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Dict_2_Activity.this);
    }

    public void pauseVideo(long ms) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (vv.isPlaying()) {
                    vv.pause();
                }
            }
        }, ms);
    }
}