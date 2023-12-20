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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.RogersCenter.readwritespell.CustomAPEZProvider;
import com.RogersCenter.readwritespell.HomeActivity;
import com.RogersCenter.readwritespell.R;
import com.RogersCenter.readwritespell.StaticValues;
import com.RogersCenter.readwritespell.quiz.Quiz1Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt8_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_5_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.Textview_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;

public class Lang_4_Activity extends AppCompatActivity {

    VideoView vv;
    View layout_keyboard, layout_bottom_nav, view_signed;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0, count1 = 0, count2 = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    ConstraintLayout cl_word;
    TextView tv_word1, tv_word2, tv_word3;
    String[] strArray = {"exp_vids/lang_step_1.webm", "exp_vids/lang_step_2.webm", "exp_vids/lang_step_3.webm", "exp_vids/lang_step_4.webm", "exp_vids/lang_step_5.webm", "exp_vids/lang_step_6.webm",
            "exp_vids/lang_slip_1.webm", "exp_vids/lang_slip_2.webm", "exp_vids/lang_slip_3.webm", "exp_vids/lang_slip_4.webm", "exp_vids/lang_slip_5.webm", "exp_vids/lang_spot_1.webm", "exp_vids/lang_spot_2.webm",
            "exp_vids/lang_spot_3.webm", "exp_vids/lang_spot_4.webm", "exp_vids/lang_spot_5.webm", "exp_vids/lang_trim_1.webm", "exp_vids/lang_trim_2.webm", "exp_vids/lang_trim_3.webm", "exp_vids/lang_trim_4.webm",
            "exp_vids/lang_trim_5.webm", "exp_vids/lang_glad_1.webm", "exp_vids/lang_glad_2.webm", "exp_vids/lang_glad_3.webm", "exp_vids/lang_glad_4.webm", "exp_vids/lang_glad_5.webm", "exp_vids/lang_flip_1.webm",
            "exp_vids/lang_flip_2.webm", "exp_vids/lang_flip_3.webm", "exp_vids/lang_flip_4.webm", "exp_vids/lang_flip_5.webm", "exp_vids/lang_skin_1.webm", "exp_vids/lang_skin_1.webm", "exp_vids/lang_skin_2.webm",
            "exp_vids/lang_skin_3.webm", "exp_vids/lang_skin_4.webm", "exp_vids/lang_skin_5.webm", "exp_vids/lang_blob_1.webm", "exp_vids/lang_blob_2.webm", "exp_vids/lang_blob_3.webm", "exp_vids/lang_blob_4.webm",
            "exp_vids/lang_blob_5.webm", "exp_vids/lang_brat_1.webm", "exp_vids/lang_brat_2.webm", "exp_vids/lang_brat_3.webm", "exp_vids/lang_brat_4.webm", "exp_vids/lang_brat_5.webm", "exp_vids/lang_drip_1.webm",
            "exp_vids/lang_drip_2.webm", "exp_vids/lang_drip_3.webm", "exp_vids/lang_drip_4.webm", "exp_vids/lang_drip_5.webm", "exp_vids/lang_prom_1.webm", "exp_vids/lang_prom_2.webm", "exp_vids/lang_prom_3.webm",
            "exp_vids/lang_prom_4.webm", "exp_vids/lang_prom_5.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_4);

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
                image_anim(Lang_4_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });

        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_4_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                   checkfileexists();
                } else {
                    Toast.makeText(Lang_4_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_4_Activity.this, iv_playpause);
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
                image_anim(Lang_4_Activity.this, iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                   checkfileexists();
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Lang_4_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_4_Activity.this, Quiz1Activity.class);
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
                image_anim(Lang_4_Activity.this, iv_home);
                Intent i1 = new Intent(Lang_4_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        tv_word1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    Textview_anim(Lang_4_Activity.this, tv_word1);
                    if (position == 1 || position == 6 || position == 11 || position == 16 || position == 21 || position == 26 || position == 31 || position == 36
                            || position == 41 || position == 46 || position == 51) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_4_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_4_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_4_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        tv_word2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    Textview_anim(Lang_4_Activity.this, tv_word2);
                    if (position == 2 || position == 7 || position == 12 || position == 17 || position == 22 || position == 27 || position == 32 || position == 37
                            || position == 42 || position == 47 || position == 52) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_4_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_4_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_4_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        tv_word3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    Textview_anim(Lang_4_Activity.this, tv_word3);
                    if (position == 3 || position == 8 || position == 13 || position == 18 || position == 23 || position == 28
                            || position == 33 || position == 38 || position == 43 || position == 48 || position == 53) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_4_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_4_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_4_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });

        view_signed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    Textview_anim(Lang_4_Activity.this, tv_word3);
                    if (position == 4 || position == 9 || position == 14 || position == 19 || position == 24 || position == 29 || position == 34 || position == 39
                            || position == 44 || position == 49 || position == 54) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_4_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_4_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_4_Activity.this, R.raw.tryagain);
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

        if (position == 4 || position == 9 || position == 14 || position == 19 || position == 24 || position == 29 || position == 34 || position == 39
                || position == 44 || position == 49 || position == 54) {
            view_signed.setVisibility(View.VISIBLE);
            iv_next.setEnabled(false);
            iv_next.setClickable(false);
        } else {
            view_signed.setVisibility(View.GONE);
            iv_next.setEnabled(true);
            iv_next.setClickable(true);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Lang_4_Activity.this, iv_next);
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

        if (position >= 1 && position <= 3) {
            tv_word1.setText("st");
            tv_word2.setText("e");
            tv_word3.setText("p");
            SHOW();
        } else if (position >= 6 && position <= 8) {
            tv_word1.setText("sl");
            tv_word2.setText("i");
            tv_word3.setText("p");
            SHOW();
        } else if (position >= 11 && position <= 13) {
            tv_word1.setText("sp");
            tv_word2.setText("o");
            tv_word3.setText("t");
            SHOW();
        } else if (position >= 16 && position <= 18) {
            tv_word1.setText("tr");
            tv_word2.setText("i");
            tv_word3.setText("m");
            SHOW();
        } else if (position >= 21 && position <= 23) {
            tv_word1.setText("gl");
            tv_word2.setText("a");
            tv_word3.setText("d");
            SHOW();
        } else if (position >= 26 && position <= 28) {
            tv_word1.setText("fl");
            tv_word2.setText("i");
            tv_word3.setText("p");
            SHOW();
        } else if (position >= 31 && position <= 33) {
            tv_word1.setText("sk");
            tv_word2.setText("i");
            tv_word3.setText("n");
            SHOW();
        } else if (position >= 36 && position <= 38) {
            tv_word1.setText("bl");
            tv_word2.setText("o");
            tv_word3.setText("b");
            SHOW();
        } else if (position >= 41 && position <= 43) {
            tv_word1.setText("br");
            tv_word2.setText("a");
            tv_word3.setText("t");
            SHOW();
        } else if (position >= 46 && position <= 48) {
            tv_word1.setText("dr");
            tv_word2.setText("i");
            tv_word3.setText("p");
            SHOW();
        } else if (position >= 51 && position <= 53) {
            tv_word1.setText("pr");
            tv_word2.setText("o");
            tv_word3.setText("m");
            SHOW();
        } else {
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
                    tv_lastpage_text.setText("You Completed Language Lesson 4");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Lang_4_Activity","YES", Lang_4_Activity.this);
                            Intent intent = new Intent(Lang_4_Activity.this, Spell_5_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }

    public void checkfileexists() {

            if (StaticValues.GetPreferences("Last_Open_position", Lang_4_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Lang_4_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Lang_4_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Lang_4_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Lang_4_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_4_Activity", Lang_4_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_4_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_4_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_4_Activity", Lang_4_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_4_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_4_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_4_Activity", Lang_4_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_4_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_4_Activity.this);
    }
    public void tryagainsound() {
        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_4_Activity.this, R.raw.tryagain);
        mPlayer2.start();
    }
}