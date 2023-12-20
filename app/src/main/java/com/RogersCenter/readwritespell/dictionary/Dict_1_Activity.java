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
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.RogersCenter.readwritespell.CustomAPEZProvider;
import com.RogersCenter.readwritespell.HomeActivity;
import com.RogersCenter.readwritespell.R;
import com.RogersCenter.readwritespell.StaticValues;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.Textview_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;

public class Dict_1_Activity extends AppCompatActivity {

    VideoView vv;
    View layout_bottom_nav;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    TextView tv_ord1, tv_ord2, tv_ord3;
    ConstraintLayout cl_order;
    String[] strArray = {"exp_vids/dict_1_1.webm", "exp_vids/dict_1_2.webm", "exp_vids/dict_1_3.webm", "exp_vids/dict_1_4.webm", "exp_vids/dict_1_5.webm", "exp_vids/dict_1_6.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dict_1);

        vv = findViewById(R.id.vv);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);

        tv_ord1 = findViewById(R.id.tv_ord1);
        tv_ord2 = findViewById(R.id.tv_ord2);
        tv_ord3 = findViewById(R.id.tv_ord3);
        cl_order = findViewById(R.id.cl_order);

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
                image_anim(Dict_1_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });

        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Dict_1_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                    checkfileexists();
                } else {
                    Toast.makeText(Dict_1_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Dict_1_Activity.this, iv_playpause);
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
                image_anim(Dict_1_Activity.this, iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    if (position >= 3) {
                        iv_next.setClickable(false);
                        iv_next.setEnabled(false);
                    } else {
                        iv_next.setClickable(true);
                        iv_next.setEnabled(true);
                        stopvid();
                        checkfileexists();
                    }

                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Dict_1_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Dict_1_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
//                            finish();
                        }
                    });

                }
            }
        });

        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Dict_1_Activity.this, iv_home);
                Intent i1 = new Intent(Dict_1_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        tv_ord1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 3) {
                    Textview_anim(Dict_1_Activity.this, tv_ord1);
                    position += 1;
                    if (position <= strArray.length - 1) {
                        stopvid();
                        checkfileexists();
                    }
                } else {
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_1_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        tv_ord2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 4) {
                    Textview_anim(Dict_1_Activity.this, tv_ord2);
                    position += 1;
                    if (position <= strArray.length - 1) {
                        stopvid();
                        checkfileexists();
                        if (!vv.isPlaying()) {
//                            finish();
//                        }
//                        if(position==strArray.length-1)
//                        {
                            View layout_lastpage = findViewById(R.id.layout_lastpage);
                            TextView tv_lastpage_text = findViewById(R.id.tv_lastpage_text);
                            ImageButton ib_home = findViewById(R.id.ib_home);
                            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    layout_lastpage.setVisibility(View.VISIBLE);
                                    tv_lastpage_text.setText("You Completed Dictionary Lesson 1");
                                    ib_home.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            StaticValues.SavePreferences("Dict_1_Activity","YES",Dict_1_Activity.this);
                                            Intent intent = new Intent(Dict_1_Activity.this,Dict_2_Activity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                }
                            });
                        }
                    }
                } else {
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_1_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        tv_ord3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 2) {
                    Textview_anim(Dict_1_Activity.this, tv_ord3);
                    position += 1;
                    if (position <= strArray.length - 1) {
                        stopvid();
                        checkfileexists();
                    }
                } else {
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_1_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });

    }

    public void checkfileexists() {
        if (StaticValues.GetPreferences("Last_Open_position", Dict_1_Activity.this) != null) {
            position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Dict_1_Activity.this));
            Log.d("LASTPOS", "" + position);
            StaticValues.RemovePreferences("Last_Open_position", Dict_1_Activity.this);
            StaticValues.RemovePreferences("Last_Open_Lesson", Dict_1_Activity.this);
            StaticValues.RemovePreferences("Last_Open_ParentLesson", Dict_1_Activity.this);
        }
        initializePlayer(position, listURI, sound_list);
    }

    private void initializePlayer(int pos, List<Uri> urilist, List<String> mysound_list) {

//        vv.setVideoPath("http://techslides.com/demos/sample-videos/small.webm");
        vv.setVideoURI(urilist.get(pos));
//        vv.setVideoURI(urilist.get(pos));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vv);
        vv.setMediaController(mediaController);
        vv.requestFocus();
        vv.start();
        vv.setMediaController(null);


        if (position >= 2 && position < 5) {
            cl_order.setVisibility(View.VISIBLE);
            tv_ord1.setText("set");
            tv_ord2.setText("step");
            tv_ord3.setText("sat");
        } else {
            cl_order.setVisibility(View.GONE);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Dict_1_Activity.this, iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
        }

        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (progressDoalog1 != null) {
                    progressDoalog1.dismiss();
                }
                iv_playpause.setImageResource(R.drawable.ic_pause);
            }
        });
        Log.d("POS", pos + "  " + urilist.get(pos));

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
        StaticValues.SavePreferences("Last_Open_Lesson", "Dict_1_Activity", Dict_1_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Dictionary", Dict_1_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Dict_1_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Dict_1_Activity", Dict_1_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Dictionary", Dict_1_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Dict_1_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Dict_1_Activity", Dict_1_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Dictionary", Dict_1_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Dict_1_Activity.this);
    }

}