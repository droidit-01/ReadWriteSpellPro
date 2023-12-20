package com.RogersCenter.readwritespell.letters;

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
import android.widget.Button;
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
import com.RogersCenter.readwritespell.reading.Reading_Chapt1_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt2_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;
import static com.RogersCenter.readwritespell.Animations.keyboard_button_anim;
import static com.RogersCenter.readwritespell.Animations.keyboard_imgbutton_anim;

public class Letter_JKLI_Activity extends AppCompatActivity {

    VideoView vv;
    View layout_keyboard, layout_bottom_nav;
    ImageButton imgbtn_prev, imgbtn_home;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    Button btn_key_a, btn_key_b, btn_key_c, btn_key_d, btn_key_e, btn_key_f, btn_key_g, btn_key_h, btn_key_i, btn_key_j, btn_key_k, btn_key_l, btn_key_m;
    Button btn_key_n, btn_key_o, btn_key_p, btn_key_q, btn_key_r, btn_key_s, btn_key_t, btn_key_u, btn_key_v, btn_key_w, btn_key_x, btn_key_y, btn_key_z;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    String[] strArray = {"exp_vids/ltr_j_1.webm", "exp_vids/ltr_j_2.webm", "exp_vids/ltr_j_3.webm", "exp_vids/ltr_j_4.webm", "exp_vids/ltr_j_5.webm", "exp_vids/ltr_j_6.webm", "exp_vids/ltr_j_7.webm",
            "exp_vids/ltr_k_1.webm", "exp_vids/ltr_k_2.webm", "exp_vids/ltr_k_3.webm", "exp_vids/ltr_k_4.webm", "exp_vids/ltr_k_5.webm", "exp_vids/ltr_k_6.webm", "exp_vids/ltr_k_7.webm",
            "exp_vids/ltr_l_1.webm", "exp_vids/ltr_l_2.webm", "exp_vids/ltr_l_3.webm", "exp_vids/ltr_l_4.webm", "exp_vids/ltr_l_5.webm", "exp_vids/ltr_l_6.webm", "exp_vids/ltr_l_7.webm",
            "exp_vids/ltr_i_1.webm", "exp_vids/ltr_i_2.webm", "exp_vids/ltr_i_3.webm", "exp_vids/ltr_i_4.webm", "exp_vids/ltr_i_5.webm", "exp_vids/ltr_i_6.webm", "exp_vids/ltr_i_7.webm",
            "exp_vids/ltr_game_jkli_1.webm", "exp_vids/ltr_game_jkli_2.webm", "exp_vids/ltr_game_jkli_3.webm", "exp_vids/ltr_game_jkli_4.webm", "exp_vids/ltr_game_jkli_5.webm", "exp_vids/ltr_game_jkli_6.webm",
            "exp_vids/ltr_game_jkli_7.webm", "exp_vids/ltr_game_jkli_8.webm", "exp_vids/ltr_game_jkli_9.webm", "exp_vids/ltr_game_jkli_10.webm", "exp_vids/ltr_game_jkli_11.webm", "exp_vids/ltr_game_jkli_12.webm",
            "exp_vids/ltr_game_jkli_13.webm", "exp_vids/ltr_game_jkli_14.webm", "exp_vids/ltr_game_jkli_15.webm", "exp_vids/ltr_game_jkli_16.webm", "exp_vids/ltr_game_jkli_17.webm", "exp_vids/ltr_game_jkli_18.webm",
            "exp_vids/ltr_game_jkli_19.webm", "exp_vids/ltr_game_jkli_20.webm", "exp_vids/ltr_game_jkli_21.webm", "exp_vids/ltr_game_jkli_22.webm", "exp_vids/ltr_game_jkli_23.webm", "exp_vids/ltr_game_jkli_24.webm",
            "exp_vids/ltr_game_jkli_25.webm", "exp_vids/ltr_game_jkli_26.webm", "exp_vids/ltr_game_jkli_27.webm", "exp_vids/ltr_game_jkli_28.webm", "exp_vids/ltr_game_jkli_29.webm", "exp_vids/ltr_game_jkli_30.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_j_k_l_i);
        vv = findViewById(R.id.vv);
        layout_keyboard = findViewById(R.id.layout_keyboard);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        imgbtn_prev = findViewById(R.id.imgbtn_prev);
        imgbtn_home = findViewById(R.id.imgbtn_home);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);

        btn_key_a = findViewById(R.id.btn_key_a);
        btn_key_b = findViewById(R.id.btn_key_b);
        btn_key_c = findViewById(R.id.btn_key_c);
        btn_key_d = findViewById(R.id.btn_key_d);
        btn_key_e = findViewById(R.id.btn_key_e);
        btn_key_f = findViewById(R.id.btn_key_f);
        btn_key_g = findViewById(R.id.btn_key_g);
        btn_key_h = findViewById(R.id.btn_key_h);
        btn_key_i = findViewById(R.id.btn_key_i);
        btn_key_j = findViewById(R.id.btn_key_j);
        btn_key_k = findViewById(R.id.btn_key_k);
        btn_key_l = findViewById(R.id.btn_key_l);
        btn_key_m = findViewById(R.id.btn_key_m);
        btn_key_n = findViewById(R.id.btn_key_n);
        btn_key_o = findViewById(R.id.btn_key_o);
        btn_key_p = findViewById(R.id.btn_key_p);
        btn_key_q = findViewById(R.id.btn_key_q);
        btn_key_r = findViewById(R.id.btn_key_r);
        btn_key_s = findViewById(R.id.btn_key_s);
        btn_key_t = findViewById(R.id.btn_key_t);
        btn_key_u = findViewById(R.id.btn_key_u);
        btn_key_v = findViewById(R.id.btn_key_v);
        btn_key_w = findViewById(R.id.btn_key_w);
        btn_key_x = findViewById(R.id.btn_key_x);
        btn_key_y = findViewById(R.id.btn_key_y);
        btn_key_z = findViewById(R.id.btn_key_z);

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
                image_anim(Letter_JKLI_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });
        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Letter_JKLI_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                    checkfileexists();
                } else {
                    Toast.makeText(Letter_JKLI_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Letter_JKLI_Activity.this, iv_playpause);
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
                image_anim(Letter_JKLI_Activity.this, iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                    checkfileexists();
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
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
                image_anim(Letter_JKLI_Activity.this, iv_home);
                Intent i1 = new Intent(Letter_JKLI_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        imgbtn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Letter_JKLI_Activity.this, imgbtn_prev);
                    if (position > 0) {
//                    stopvid();
                        position -= 1;
                        checkfileexists();
                    } else {
                        Toast.makeText(Letter_JKLI_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        imgbtn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Letter_JKLI_Activity.this, imgbtn_home);
                    stopvid();
                    finish();
                }
            }
        });
        btn_key_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_a);
                    if (position == 38 || position == 51) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_b);
                    if (position == 35 || position == 52) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_c);
                    if (position == 36 || position == 50) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_d);
                    if (position == 28 || position == 49) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_e);
                    if (position == 40 || position == 47) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_f);
                    if (position == 34 || position == 44) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }

            }
        });
        btn_key_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_g);
                    if (position == 31 || position == 43) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_h);
                    if (position == 39 || position == 53) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_i);
                    if (position == 42 || position == 46) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_j);
                    if (position == 30) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else if (position == 56) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                        }
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                View layout_lastpage = findViewById(R.id.layout_lastpage);
                                TextView tv_lastpage_text = findViewById(R.id.tv_lastpage_text);
                                ImageButton ib_home = findViewById(R.id.ib_home);
                                layout_lastpage.setVisibility(View.VISIBLE);
                                tv_lastpage_text.setText("You Completed Letter Lesson 5");
                                ib_home.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        StaticValues.SavePreferences("Letter_JKLI_Activity","YES", Letter_JKLI_Activity.this);
                                        Intent intent = new Intent(Letter_JKLI_Activity.this, Reading_Chapt2_Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });

                            }
                        });
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_k);
                    if (position == 41 || position == 55) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_l);
                    if (position == 32 || position == 48) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_m);
                    if (position == 33 || position == 45) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_n);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_o);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_p);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_q);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_r);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_s);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_t);
                    if (position == 37 || position == 54) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_JKLI_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_JKLI_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_u);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_v);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_w);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_x);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_y);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_JKLI_Activity.this, btn_key_z);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_JKLI_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
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

        if (position >= 28) {
            layout_keyboard.setVisibility(View.VISIBLE);
            layout_bottom_nav.setVisibility(View.GONE);
        } else {
            layout_bottom_nav.setVisibility(View.VISIBLE);
            layout_keyboard.setVisibility(View.GONE);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Letter_JKLI_Activity.this, iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
        }

        if (position == 29) {
            position = position + 1;
            checkfileexists();
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
        if (position == strArray.length - 1) {
            View layout_lastpage = findViewById(R.id.layout_lastpage);
            TextView tv_lastpage_text = findViewById(R.id.tv_lastpage_text);
            ImageButton ib_home = findViewById(R.id.ib_home);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    layout_lastpage.setVisibility(View.VISIBLE);
                    tv_lastpage_text.setText("You Completed Letter Lesson 5");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Letter_JKLI_Activity","YES", Letter_JKLI_Activity.this);
                            Intent intent = new Intent(Letter_JKLI_Activity.this, Reading_Chapt2_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }

    public void checkfileexists() {
            if (StaticValues.GetPreferences("Last_Open_position", Letter_JKLI_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Letter_JKLI_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Letter_JKLI_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Letter_JKLI_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Letter_JKLI_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Letter_JKLI_Activity", Letter_JKLI_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Letters", Letter_JKLI_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Letter_JKLI_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Letter_JKLI_Activity", Letter_JKLI_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Letters", Letter_JKLI_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Letter_JKLI_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Letter_JKLI_Activity", Letter_JKLI_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Letters", Letter_JKLI_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Letter_JKLI_Activity.this);
    }
}