package com.RogersCenter.readwritespell.langs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.RogersCenter.readwritespell.quiz.Quiz1Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.Textview_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;

public class Lang_7_Activity extends AppCompatActivity {

    VideoView vv;
    View layout_keyboard, layout_bottom_nav, view_signed;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0, count1 = 0, count2 = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;

    ConstraintLayout cl_word;
    TextView tv_word1, tv_word2, tv_word3;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    String[] strArray = {"exp_vids/lang_7_1.webm", "exp_vids/lang_7_2.webm", "exp_vids/lang_7_3.webm", "exp_vids/lang_7_4.webm", "exp_vids/lang_7_5.webm", "exp_vids/lang_7_6.webm",
            "exp_vids/lang_7_7.webm", "exp_vids/lang_7_8.webm", "exp_vids/lang_7_9.webm", "exp_vids/lang_7_10.webm", "exp_vids/lang_7_11.webm", "exp_vids/lang_7_12.webm","exp_vids/lang_7_13.webm",
            "exp_vids/lang_7_14.webm","exp_vids/lang_7_15.webm","exp_vids/lang_7_16.webm","exp_vids/lang_7_17.webm","exp_vids/lang_7_18.webm","exp_vids/lang_7_19.webm","exp_vids/lang_7_20.webm",
            "exp_vids/lang_7_21.webm","exp_vids/lang_7_22.webm","exp_vids/lang_7_23.webm","exp_vids/lang_7_24.webm","exp_vids/lang_7_25.webm","exp_vids/lang_7_26.webm","exp_vids/lang_7_27.webm",
            "exp_vids/lang_7_28.webm","exp_vids/lang_7_29.webm","exp_vids/lang_7_30.webm","exp_vids/lang_7_31.webm","exp_vids/lang_7_32.webm","exp_vids/lang_7_33.webm","exp_vids/lang_7_34.webm",
            "exp_vids/lang_7_35.webm","exp_vids/lang_7_36.webm","exp_vids/lang_7_37.webm","exp_vids/lang_7_38.webm","exp_vids/lang_7_39.webm","exp_vids/lang_7_40.webm","exp_vids/lang_7_41.webm",
            "exp_vids/lang_7_42.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_7);

        vv = findViewById(R.id.vv);
        layout_keyboard = findViewById(R.id.layout_keyboard);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        view_signed = findViewById(R.id.view_signed);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);

        cl_word = findViewById(R.id.cl_word);
        tv_word1 = findViewById(R.id.tv_word1);
        tv_word2 = findViewById(R.id.tv_word2);
        tv_word3 = findViewById(R.id.tv_word3);

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
                image_anim(Lang_7_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });
        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_7_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                   checkfileexists();
                } else {
                    Toast.makeText(Lang_7_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_7_Activity.this, iv_playpause);
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
                image_anim(Lang_7_Activity.this, iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                   checkfileexists();
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Lang_7_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_7_Activity.this, Quiz1Activity.class);
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
                image_anim(Lang_7_Activity.this, iv_home);
                Intent i1 = new Intent(Lang_7_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        tv_word1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    Textview_anim(Lang_7_Activity.this, tv_word1);
                    if (position == 2 || position == 7 || position == 12 || position == 17 || position == 22 || position == 27 || position == 32
                            || position == 37) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_7_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_7_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_7_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        tv_word2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    Textview_anim(Lang_7_Activity.this, tv_word2);
                    if (position == 3 || position == 8 || position == 13 || position == 18 || position == 23 || position == 28 || position == 33
                            || position == 38) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_7_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_7_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_7_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        tv_word3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    Textview_anim(Lang_7_Activity.this, tv_word3);
                    if (position == 4 || position == 9 || position == 14 || position == 19 || position == 24 || position == 29
                            || position == 34 || position == 39) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_7_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_7_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_7_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });

        view_signed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    Textview_anim(Lang_7_Activity.this, tv_word3);
                    if(position == 5 || position == 10 || position == 15 || position == 20 || position == 25 || position == 30 || position == 35 || position == 40)
                    {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_7_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_7_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_7_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
    }

    public void SHOW() {
        iv_next.setEnabled(false);
        iv_next.setClickable(false);
        cl_word.setVisibility(View.VISIBLE);
    }

    public void HIDE() {
        iv_next.setEnabled(true);
        iv_next.setClickable(true);
        cl_word.setVisibility(View.GONE);
    }

    private void initializePlayer(int pos, List<Uri> urilist, List<String> mysound_list)
    {

//        vv.setVideoPath("http://techslides.com/demos/sample-videos/small.webm");
//        vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + urilist.get(pos)));
        vv.setVideoURI(urilist.get(pos));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vv);
        vv.setMediaController(mediaController);
        vv.requestFocus();
        vv.start();
        vv.setMediaController(null);

        if (position == 5 || position == 10 || position == 15 || position == 20 || position == 25 || position == 30 || position == 35 || position == 40)
        {
            view_signed.setVisibility(View.VISIBLE);
            iv_next.setEnabled(false);
            iv_next.setClickable(false);
        }else {
            view_signed.setVisibility(View.GONE);
            iv_next.setEnabled(true);
            iv_next.setClickable(true);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Lang_7_Activity.this,iv_next);
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
        if (position >= 2 && position <= 4) {
            tv_word1.setText("h");
            tv_word2.setText("i");
            tv_word3.setText("ll");
            SHOW();
        } else if (position >= 7 && position <= 9) {
            tv_word1.setText("w");
            tv_word2.setText("i");
            tv_word3.setText("ll");
            SHOW();
        } else if (position >= 12 && position <= 14) {
            tv_word1.setText("sp");
            tv_word2.setText("e");
            tv_word3.setText("ll");
            SHOW();
        } else if (position >= 17 && position <= 19) {
            tv_word1.setText("c");
            tv_word2.setText("u");
            tv_word3.setText("ff");
            SHOW();
        } else if (position >= 22 && position <= 24) {
            tv_word1.setText("st");
            tv_word2.setText("i");
            tv_word3.setText("ff");
            SHOW();
        } else if (position >= 27 && position <= 29) {
            tv_word1.setText("h");
            tv_word2.setText("i");
            tv_word3.setText("ss");
            SHOW();
        } else if (position >= 32 && position <= 34) {
            tv_word1.setText("k");
            tv_word2.setText("i");
            tv_word3.setText("ss");
            SHOW();
        } else if (position >= 37 && position <= 39) {
            tv_word1.setText("b");
            tv_word2.setText("o");
            tv_word3.setText("ss");
            SHOW();
        }else {
            HIDE();
        }
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
                    tv_lastpage_text.setText("You Completed Language Lesson 7");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }
            });
        }
    }
    public void checkfileexists() {

            if (StaticValues.GetPreferences("Last_Open_position", Lang_7_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Lang_7_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Lang_7_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Lang_7_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Lang_7_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_7_Activity", Lang_7_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_7_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_7_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_7_Activity", Lang_7_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_7_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_7_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_7_Activity", Lang_7_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_7_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_7_Activity.this);
    }
    public void tryagainsound() {
        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_7_Activity.this, R.raw.tryagain);
        mPlayer2.start();
    }
}