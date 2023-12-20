package com.RogersCenter.readwritespell.reading;

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
import com.RogersCenter.readwritespell.langs.Lang_18_Activity;
import com.RogersCenter.readwritespell.quiz.Quiz1Activity;
import com.RogersCenter.readwritespell.spellings.Spell_4_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.View_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;

public class Reading_Chapt9_Activity extends AppCompatActivity {

    VideoView vv;
    View layoutvideoviev, layout_bottom_nav; //layout_read_game_chpt;
    int position = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    List<Uri> listURI = new ArrayList<>();
    ConstraintLayout cl_read_click;
    View read_v_1, read_v_2, read_v_3, read_v_4, read_v_5;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    String[] strArray = {"exp_vids/read_9_1.webm", "exp_vids/read_9_2_v1.webm", "exp_vids/read_9_3_v2.webm", "exp_vids/read_9_4_v3.webm", "exp_vids/read_9_5_v4.webm", "exp_vids/read_9_6_v5.webm",
            "exp_vids/read_9_7.webm", "exp_vids/read_9_8.webm", "exp_vids/read_9_9.webm", "exp_vids/read_9_10.webm", "exp_vids/read_9_11.webm", "exp_vids/read_9_12.webm", "exp_vids/read_9_13.webm",
            "exp_vids/read_9_14.webm", "exp_vids/read_9_15.webm", "exp_vids/read_9_16.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_chapt9);

        vv = findViewById(R.id.vv);
        layoutvideoviev = findViewById(R.id.layoutvideoviev);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
//        layout_read_game_chpt = findViewById(R.id.layout_read_game_chpt);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);

        cl_read_click = findViewById(R.id.cl_read_click);
        read_v_1 = findViewById(R.id.read_v_1);
        read_v_2 = findViewById(R.id.read_v_2);
        read_v_3 = findViewById(R.id.read_v_3);
        read_v_4 = findViewById(R.id.read_v_4);
        read_v_5 = findViewById(R.id.read_v_5);
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
                image_anim(Reading_Chapt9_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });
        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Reading_Chapt9_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                    checkfileexists();
                } else {
                    Toast.makeText(Reading_Chapt9_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Reading_Chapt9_Activity.this, iv_playpause);
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
                image_anim(Reading_Chapt9_Activity.this, iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                    checkfileexists();
                } else {
//                    if(position>52)
//                    {
                    layoutvideoviev.setVisibility(View.GONE);
//                    layout_read_game_chpt.setVisibility(View.VISIBLE);
                    cl_read_click.setVisibility(View.GONE);
//                    }
//                    position -= 1;
//                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mediaPlayer) {
//                            Toast.makeText(Reading_Chapt9_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
////                            stopvid();
////                            Intent i = new Intent(Reading_Chapt9_Activity.this, Quiz1Activity.class);
////                            startActivity(i);
//                            //finish();
//                        }
//                    });

                }
            }
        });
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Reading_Chapt9_Activity.this, iv_home);
                Intent i1 = new Intent(Reading_Chapt9_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        read_v_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    View_anim(Reading_Chapt9_Activity.this, read_v_1);
                    if (position == 1) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt9_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt9_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt9_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        read_v_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    View_anim(Reading_Chapt9_Activity.this, read_v_2);
                    if (position == 2) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt9_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt9_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt9_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        read_v_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    View_anim(Reading_Chapt9_Activity.this, read_v_3);
                    if (position == 3) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt9_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt9_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt9_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        read_v_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    View_anim(Reading_Chapt9_Activity.this, read_v_4);
                    if (position == 4) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt9_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt9_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt9_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        read_v_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    View_anim(Reading_Chapt9_Activity.this, read_v_5);
                    if (position == 5) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Reading_Chapt9_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Reading_Chapt9_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Reading_Chapt9_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
    }

    private void initializePlayer(int pos, List<Uri> urilist) {


//
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
                if (progressDoalog1 != null) {
                    progressDoalog1.dismiss();
                }
                iv_playpause.setImageResource(R.drawable.ic_pause);
            }
        });

        if (position >= 1 && position <= 4) {
            layoutvideoviev.setVisibility(View.VISIBLE);
            cl_read_click.setVisibility(View.VISIBLE);
            iv_next.setClickable(false);
            iv_next.setEnabled(false);
        } else {
            layoutvideoviev.setVisibility(View.VISIBLE);
//            layout_read_game_chpt.setVisibility(View.GONE);
            cl_read_click.setVisibility(View.GONE);
            iv_next.setClickable(true);
            iv_next.setEnabled(true);

            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    image_anim_last(Reading_Chapt9_Activity.this, iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
        }
//        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setVolume(0, 0);
//            }
//        })
        if(position==strArray.length-1)
        {
            View layout_lastpage  = findViewById(R.id.layout_lastpage);
            TextView tv_lastpage_text= findViewById(R.id.tv_lastpage_text);
            ImageButton ib_home= findViewById(R.id.ib_home);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    layout_lastpage.setVisibility(View.VISIBLE);
                    tv_lastpage_text.setText("You Completed Reading Lesson 9");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Reading_Chapt9_Activity","YES", Reading_Chapt9_Activity.this);
                            Intent intent = new Intent(Reading_Chapt9_Activity.this, Lang_18_Activity.class);
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

            if (StaticValues.GetPreferences("Last_Open_position", Reading_Chapt9_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Reading_Chapt9_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Reading_Chapt9_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Reading_Chapt9_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Reading_Chapt9_Activity.this);
            }
            initializePlayer(position, listURI);

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
        StaticValues.SavePreferences("Last_Open_Lesson", "Reading_Chapt9_Activity", Reading_Chapt9_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Reading", Reading_Chapt9_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Reading_Chapt9_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Reading_Chapt9_Activity", Reading_Chapt9_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Reading", Reading_Chapt9_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Reading_Chapt9_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Reading_Chapt9_Activity", Reading_Chapt9_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Reading", Reading_Chapt9_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Reading_Chapt9_Activity.this);
    }
}