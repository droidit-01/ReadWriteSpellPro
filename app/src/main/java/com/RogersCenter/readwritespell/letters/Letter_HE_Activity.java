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
import com.RogersCenter.readwritespell.reading.Reading_Chapt15_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt1_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;
import static com.RogersCenter.readwritespell.Animations.keyboard_button_anim;
import static com.RogersCenter.readwritespell.Animations.keyboard_imgbutton_anim;

public class Letter_HE_Activity extends AppCompatActivity {

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
    String[] strArray = {"exp_vids/ltr_h_1.webm", "exp_vids/ltr_h_2.webm", "exp_vids/ltr_h_3.webm", "exp_vids/ltr_h_4.webm", "exp_vids/ltr_h_5.webm", "exp_vids/ltr_h_6.webm", "exp_vids/ltr_h_7.webm",
            "exp_vids/ltr_e_1.webm", "exp_vids/ltr_e_2.webm", "exp_vids/ltr_e_3.webm", "exp_vids/ltr_e_4.webm", "exp_vids/ltr_e_5.webm", "exp_vids/ltr_e_6.webm", "exp_vids/ltr_e_7.webm",
            "exp_vids/ltr_game_he_1.webm", "exp_vids/ltr_game_he_2.webm", "exp_vids/ltr_game_he_3.webm", "exp_vids/ltr_game_he_4.webm", "exp_vids/ltr_game_he_5.webm", "exp_vids/ltr_game_he_6.webm",
            "exp_vids/ltr_game_he_7.webm", "exp_vids/ltr_game_he_8.webm", "exp_vids/ltr_game_he_9.webm", "exp_vids/ltr_game_he_10.webm", "exp_vids/ltr_game_he_11.webm", "exp_vids/ltr_game_he_12.webm",
            "exp_vids/ltr_game_he_13.webm", "exp_vids/ltr_game_he_14.webm", "exp_vids/ltr_game_he_15.webm", "exp_vids/ltr_game_he_16.webm", "exp_vids/ltr_game_he_17.webm", "exp_vids/ltr_game_he_18.webm",
            "exp_vids/ltr_game_he_19.webm", "exp_vids/ltr_game_he_20.webm", "exp_vids/ltr_game_he_21.webm", "exp_vids/ltr_game_he_22.webm", "exp_vids/ltr_game_he_23.webm", "exp_vids/ltr_game_he_24.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_h_e);
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
                image_anim(Letter_HE_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });
        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Letter_HE_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                    checkfileexists();
                } else {
                    Toast.makeText(Letter_HE_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Letter_HE_Activity.this, iv_playpause);
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
                image_anim(Letter_HE_Activity.this, iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                    checkfileexists();
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Letter_HE_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Letter_HE_Activity.this, Quiz1Activity.class);
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
                image_anim(Letter_HE_Activity.this, iv_home);
                Intent i1 = new Intent(Letter_HE_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        imgbtn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Letter_HE_Activity.this, imgbtn_prev);
                    if (position > 0) {
//                    stopvid();
                        position -= 1;
                        checkfileexists();
                    } else {
                        Toast.makeText(Letter_HE_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        imgbtn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Letter_HE_Activity.this, imgbtn_home);
                    stopvid();
                    finish();
                }
            }
        });
        btn_key_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_a);
                    if (position == 25 || position == 29) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_HE_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_HE_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_b);
                    if (position == 20) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_HE_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_HE_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else if (position == 36) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_HE_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                        }
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                View layout_lastpage = findViewById(R.id.layout_lastpage);
                                TextView tv_lastpage_text = findViewById(R.id.tv_lastpage_text);
                                ImageButton ib_home = findViewById(R.id.ib_home);
                                layout_lastpage.setVisibility(View.VISIBLE);
                                tv_lastpage_text.setText("You Completed Letter Lesson 4");
                                ib_home.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        StaticValues.SavePreferences("Letter_HE_Activity","YES", Letter_HE_Activity.this);
                                        Intent intent = new Intent(Letter_HE_Activity.this, Reading_Chapt1_Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });

                            }
                        });
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_c);
                    if (position == 17 || position == 30) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_HE_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_HE_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_d);
                    if (position == 18 || position == 35) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_HE_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_HE_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_e);
                    if (position == 16 || position == 26) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_HE_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_HE_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_f);
                    if (position == 14 || position == 33) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_HE_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_HE_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }

            }
        });
        btn_key_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_g);
                    if (position == 24 || position == 32) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_HE_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_HE_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_h);
                    if (position == 22 || position == 31) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_HE_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_HE_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_i);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_j);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_k);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_l);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_m);
                    if (position == 19 || position == 34) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_HE_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_HE_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_n);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_o);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_p);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_q);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_r);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_s);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_t);
                    if (position == 21 || position == 28) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_HE_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_HE_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_u);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_v);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_w);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_x);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_y);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_HE_Activity.this, btn_key_z);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_HE_Activity.this, R.raw.tryagain);
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

        if (position >= 14) {
            layout_keyboard.setVisibility(View.VISIBLE);
            layout_bottom_nav.setVisibility(View.GONE);
        } else {
            layout_bottom_nav.setVisibility(View.VISIBLE);
            layout_keyboard.setVisibility(View.GONE);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Letter_HE_Activity.this, iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
        }

        if (position == 15 || position == 23 || position == 27) {
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
                    tv_lastpage_text.setText("You Completed Letter Lesson 4");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Letter_HE_Activity","YES", Letter_HE_Activity.this);
                            Intent intent = new Intent(Letter_HE_Activity.this, Reading_Chapt1_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }

    public void checkfileexists() {
          if (StaticValues.GetPreferences("Last_Open_position", Letter_HE_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Letter_HE_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Letter_HE_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Letter_HE_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Letter_HE_Activity.this);
            }
            initializePlayer(position, listURI, sound_list);
    }

    public void Check_INTERNET(int pos, String childname) {
        try {
            if (StaticValues.checkConnection(Letter_HE_Activity.this)) {
                progressDoalog1 = new ProgressDialog(Letter_HE_Activity.this, R.style.MyDialog);
//                progressDoalog1.setMessage("Loading..");
                progressDoalog1.setCanceledOnTouchOutside(false);
                progressDoalog1.show();

                listURI.add(position, Uri.parse(StaticValues.BaseURL_LETTERS + childname));
                initializePlayer(pos, listURI, sound_list);

                downloadfiles(Letter_HE_Activity.this,
                        "" + childname,
                        ".webm",
                        "Letters",
                        StaticValues.BaseURL_LETTERS + childname);
            } else {
                final AlertDialog alertDialog;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Letter_HE_Activity.this);
                alertDialogBuilder.setTitle("Error Encountered.");
                alertDialogBuilder.setMessage("The network is not Available in this area.")
                        .setCancelable(false)
                        .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Check_INTERNET(pos, childname);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog = alertDialogBuilder.create();
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(Letter_HE_Activity.this, R.color.colorAccent));
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(Letter_HE_Activity.this, R.color.colorAccent));
                    }
                });
                alertDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadfiles(Context context, String filename, String filexten, String destdir, String url) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationInExternalFilesDir(context,destdir,filename+filexten);
        request.setDestinationInExternalFilesDir(context, destdir, filename);
        downloadManager.enqueue(request);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Letter_HE_Activity", Letter_HE_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Letters", Letter_HE_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Letter_HE_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Letter_HE_Activity", Letter_HE_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Letters", Letter_HE_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Letter_HE_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Letter_HE_Activity", Letter_HE_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Letters", Letter_HE_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Letter_HE_Activity.this);
    }
}