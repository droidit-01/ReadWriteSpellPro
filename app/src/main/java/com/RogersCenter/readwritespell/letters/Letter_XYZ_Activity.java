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
import com.RogersCenter.readwritespell.reading.Reading_Chapt4_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt6_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;
import static com.RogersCenter.readwritespell.Animations.keyboard_button_anim;
import static com.RogersCenter.readwritespell.Animations.keyboard_imgbutton_anim;

public class Letter_XYZ_Activity extends AppCompatActivity {
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
    String[] strArray = {"exp_vids/ltr_x_1.webm", "exp_vids/ltr_x_2.webm", "exp_vids/ltr_x_3.webm", "exp_vids/ltr_x_4.webm", "exp_vids/ltr_x_5.webm", "exp_vids/ltr_x_6.webm", "exp_vids/ltr_x_7.webm",
            "exp_vids/ltr_y_1.webm", "exp_vids/ltr_y_2.webm", "exp_vids/ltr_y_3.webm", "exp_vids/ltr_y_4.webm", "exp_vids/ltr_y_5.webm", "exp_vids/ltr_y_6.webm", "exp_vids/ltr_y_7.webm",
            "exp_vids/ltr_z_1.webm", "exp_vids/ltr_z_2.webm", "exp_vids/ltr_z_3.webm", "exp_vids/ltr_z_4.webm", "exp_vids/ltr_z_5.webm", "exp_vids/ltr_z_6.webm", "exp_vids/ltr_z_7.webm",
            "exp_vids/ltr_game_xyz_1.webm", "exp_vids/ltr_game_xyz_2.webm", "exp_vids/ltr_game_xyz_3.webm", "exp_vids/ltr_game_xyz_4.webm", "exp_vids/ltr_game_xyz_5.webm", "exp_vids/ltr_game_xyz_6.webm",
            "exp_vids/ltr_game_xyz_7.webm", "exp_vids/ltr_game_xyz_8.webm", "exp_vids/ltr_game_xyz_9.webm", "exp_vids/ltr_game_xyz_10.webm", "exp_vids/ltr_game_xyz_11.webm", "exp_vids/ltr_game_xyz_12.webm",
            "exp_vids/ltr_game_xyz_13.webm", "exp_vids/ltr_game_xyz_14.webm", "exp_vids/ltr_game_xyz_15.webm", "exp_vids/ltr_game_xyz_16.webm", "exp_vids/ltr_game_xyz_17.webm", "exp_vids/ltr_game_xyz_18.webm",
            "exp_vids/ltr_game_xyz_19.webm", "exp_vids/ltr_game_xyz_20.webm", "exp_vids/ltr_game_xyz_21.webm", "exp_vids/ltr_game_xyz_22.webm", "exp_vids/ltr_game_xyz_23.webm", "exp_vids/ltr_game_xyz_24.webm",
            "exp_vids/ltr_game_xyz_25.webm", "exp_vids/ltr_game_xyz_26.webm", "exp_vids/ltr_game_xyz_27.webm", "exp_vids/ltr_game_xyz_28.webm", "exp_vids/ltr_game_xyz_29.webm", "exp_vids/ltr_game_xyz_30.webm",
            "exp_vids/ltr_game_xyz_31.webm", "exp_vids/ltr_game_xyz_32.webm", "exp_vids/ltr_game_xyz_33.webm", "exp_vids/ltr_game_xyz_34.webm", "exp_vids/ltr_game_xyz_35.webm", "exp_vids/ltr_game_xyz_36.webm",
            "exp_vids/ltr_game_xyz_37.webm", "exp_vids/ltr_game_xyz_38.webm", "exp_vids/ltr_game_xyz_39.webm", "exp_vids/ltr_game_xyz_40.webm", "exp_vids/ltr_game_xyz_41.webm", "exp_vids/ltr_game_xyz_42.webm",
            "exp_vids/ltr_game_xyz_43.webm", "exp_vids/ltr_game_xyz_44.webm", "exp_vids/ltr_game_xyz_45.webm", "exp_vids/ltr_game_xyz_46.webm", "exp_vids/ltr_game_xyz_47.webm", "exp_vids/ltr_game_xyz_48.webm",
            "exp_vids/ltr_game_xyz_49.webm", "exp_vids/ltr_game_xyz_50.webm", "exp_vids/ltr_game_xyz_51.webm", "exp_vids/ltr_game_xyz_52.webm", "exp_vids/ltr_game_xyz_53.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_x_y_z);
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
                image_anim(Letter_XYZ_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });

        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Letter_XYZ_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                    checkfileexists();
                } else {
                    Toast.makeText(Letter_XYZ_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Letter_XYZ_Activity.this, iv_playpause);
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
                image_anim(Letter_XYZ_Activity.this, iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                    checkfileexists();
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
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
                image_anim(Letter_XYZ_Activity.this, iv_home);
                Intent i1 = new Intent(Letter_XYZ_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        imgbtn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Letter_XYZ_Activity.this, imgbtn_prev);
                    if (position > 0) {
//                    stopvid();
                        position -= 1;
                        checkfileexists();
                    } else {
                        Toast.makeText(Letter_XYZ_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        imgbtn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Letter_XYZ_Activity.this, imgbtn_home);
                    stopvid();
                    finish();
                }
            }
        });

        btn_key_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_a);
                    if (position == 40 || position == 61) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_b);
                    if (position == 37 || position == 66) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_c);
                    if (position == 25) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else if (position == 72) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                        }
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                View layout_lastpage = findViewById(R.id.layout_lastpage);
                                TextView tv_lastpage_text = findViewById(R.id.tv_lastpage_text);
                                ImageButton ib_home = findViewById(R.id.ib_home);
                                layout_lastpage.setVisibility(View.VISIBLE);
                                tv_lastpage_text.setText("You Completed Letter Lesson 9");
                                ib_home.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        StaticValues.SavePreferences("Letter_XYZ_Activity","YES", Letter_XYZ_Activity.this);
                                        Intent intent = new Intent(Letter_XYZ_Activity.this, Reading_Chapt6_Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });

                            }
                        });
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_d);
                    if (position == 28 || position == 59) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_e);
                    if (position == 21 || position == 48) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_f);
                    if (position == 42 || position == 62) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }

            }
        });
        btn_key_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_g);
                    if (position == 36 || position == 60) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_h);
                    if (position == 22 || position == 47) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_i);
                    if (position == 44 || position == 58) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_j);
                    if (position == 46 || position == 49) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_k);
                    if (position == 43 || position == 51) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_l);
                    if (position == 39 || position == 63) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_m);
                    if (position == 41 || position == 50) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_n);
                    if (position == 30 || position == 70) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_o);
                    if (position == 23 || position == 56) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_p);
                    if (position == 38 || position == 54) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_q);
                    if (position == 32 || position == 71) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_r);
                    if (position == 27 || position == 67) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_s);
                    if (position == 26 || position == 57) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_t);
                    if (position == 31 || position == 68) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_u);
                    if (position == 29 || position == 55) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_v);
                    if (position == 24 || position == 52) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_w);
                    if (position == 34 || position == 53) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_x);
                    if (position == 33 || position == 64) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_y);
                    if (position == 45 || position == 69) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_XYZ_Activity.this, btn_key_z);
                    if (position == 35 || position == 65) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_XYZ_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_XYZ_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_XYZ_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
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

        if (position >= 21) {
            layout_keyboard.setVisibility(View.VISIBLE);
            layout_bottom_nav.setVisibility(View.GONE);
        } else {
            layout_bottom_nav.setVisibility(View.VISIBLE);
            layout_keyboard.setVisibility(View.GONE);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Letter_XYZ_Activity.this, iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
        }
//        if(position==55)
//        {
//            position=position+1;
//          checkfileexists();
//        }
//        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setVolume(0, 0);
//            }
//        });
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
                    tv_lastpage_text.setText("You Completed Letter Lesson 9");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Letter_XYZ_Activity","YES", Letter_XYZ_Activity.this);
                            Intent intent = new Intent(Letter_XYZ_Activity.this, Reading_Chapt6_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }

    public void checkfileexists() {
            if (StaticValues.GetPreferences("Last_Open_position", Letter_XYZ_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Letter_XYZ_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Letter_XYZ_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Letter_XYZ_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Letter_XYZ_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Letter_XYZ_Activity", Letter_XYZ_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Letters", Letter_XYZ_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Letter_XYZ_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Letter_XYZ_Activity", Letter_XYZ_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Letters", Letter_XYZ_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Letter_XYZ_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Letter_XYZ_Activity", Letter_XYZ_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Letters", Letter_XYZ_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Letter_XYZ_Activity.this);
    }
}