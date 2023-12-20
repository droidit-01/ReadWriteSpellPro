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
import com.RogersCenter.readwritespell.reading.Reading_Chapt14_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt19_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;
import static com.RogersCenter.readwritespell.Animations.keyboard_button_anim;
import static com.RogersCenter.readwritespell.Animations.keyboard_imgbutton_anim;

public class Dict_17_Activity extends AppCompatActivity {

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
    View clickview;
    String[] strArray = {"exp_vids/dict_17_1.webm", "exp_vids/dict_17_2.webm", "exp_vids/dict_17_3.webm", "exp_vids/dict_17_4.webm", "exp_vids/dict_17_5.webm", "exp_vids/dict_17_6.webm",
            "exp_vids/dict_17_7.webm", "exp_vids/dict_17_8.webm", "exp_vids/dict_17_9.webm", "exp_vids/dict_17_10.webm", "exp_vids/dict_17_11.webm", "exp_vids/dict_17_12.webm", "exp_vids/dict_17_13.webm",
            "exp_vids/dict_17_14.webm", "exp_vids/dict_17_15.webm", "exp_vids/dict_17_16.webm", "exp_vids/dict_17_17.webm", "exp_vids/dict_17_18.webm", "exp_vids/dict_17_19.webm", "exp_vids/dict_17_20.webm",
            "exp_vids/dict_17_21.webm", "exp_vids/dict_17_22.webm", "exp_vids/dict_17_23.webm", "exp_vids/dict_17_24.webm", "exp_vids/dict_17_25.webm", "exp_vids/dict_17_26.webm", "exp_vids/dict_17_27.webm",
            "exp_vids/dict_17_28.webm", "exp_vids/dict_17_29.webm", "exp_vids/dict_17_30.webm", "exp_vids/dict_17_31.webm", "exp_vids/dict_17_32.webm", "exp_vids/dict_17_33.webm", "exp_vids/dict_17_34.webm",
            "exp_vids/dict_17_35.webm", "exp_vids/dict_17_36.webm", "exp_vids/dict_17_37.webm", "exp_vids/dict_17_38.webm", "exp_vids/dict_17_39.webm", "exp_vids/dict_17_40.webm", "exp_vids/dict_17_41.webm",
            "exp_vids/dict_17_42.webm", "exp_vids/dict_17_43.webm", "exp_vids/dict_17_44.webm", "exp_vids/dict_17_45.webm", "exp_vids/dict_17_46.webm", "exp_vids/dict_17_47.webm", "exp_vids/dict_17_48.webm",
            "exp_vids/dict_17_49.webm", "exp_vids/dict_17_50.webm", "exp_vids/dict_17_51.webm", "exp_vids/dict_17_52.webm", "exp_vids/dict_17_53.webm", "exp_vids/dict_17_54.webm", "exp_vids/dict_17_55.webm",
            "exp_vids/dict_17_56.webm", "exp_vids/dict_17_57.webm", "exp_vids/dict_17_58.webm", "exp_vids/dict_17_59.webm", "exp_vids/dict_17_60.webm", "exp_vids/dict_17_61.webm", "exp_vids/dict_17_62.webm",
            "exp_vids/dict_17_63.webm", "exp_vids/dict_17_64.webm", "exp_vids/dict_17_65.webm", "exp_vids/dict_17_66.webm", "exp_vids/dict_17_67.webm",
            "exp_vids/dict_17_68.webm", "exp_vids/dict_17_69.webm", "exp_vids/dict_17_70.webm", "exp_vids/dict_17_71.webm", "exp_vids/dict_17_72.webm", "exp_vids/dict_17_73.webm", "exp_vids/dict_17_74.webm",
            "exp_vids/dict_17_75.webm", "exp_vids/dict_17_76.webm", "exp_vids/dict_17_77.webm", "exp_vids/dict_17_78.webm", "exp_vids/dict_17_79.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dict_17);

        vv = findViewById(R.id.vv);
        layout_keyboard = findViewById(R.id.layout_keyboard);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        iv_back = findViewById(R.id.iv_l2_back);
        imgbtn_prev = findViewById(R.id.imgbtn_prev);
        imgbtn_home = findViewById(R.id.imgbtn_home);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);
        clickview = findViewById(R.id.clickview);

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
                image_anim(Dict_17_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });
        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Dict_17_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                    checkfileexists();
                } else {
                    Toast.makeText(Dict_17_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Dict_17_Activity.this, iv_playpause);
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
                image_anim(Dict_17_Activity.this, iv_next);
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
                                tv_lastpage_text.setText("You Completed Dictionary Lesson 17");
                                ib_home.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        StaticValues.SavePreferences("Dict_17_Activity","YES",Dict_17_Activity.this);
                                        Intent intent = new Intent(Dict_17_Activity.this, Writing_Chapt19_Activity.class);
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
                image_anim(Dict_17_Activity.this, iv_home);
                Intent i1 = new Intent(Dict_17_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        imgbtn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Dict_17_Activity.this, imgbtn_prev);
                    if (position > 0) {
//                    stopvid();
                        position -= 1;
                        checkfileexists();
                    } else {
                        Toast.makeText(Dict_17_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        imgbtn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Dict_17_Activity.this, imgbtn_home);
                    stopvid();
                    finish();
                }
            }
        });
        btn_key_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_a);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_b);
                    if (position == 1) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_c);
                    if (position == 51 || position == 63) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_d);
                    if (position == 55 || position == 67) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_e);
                    if (position == 14 || position == 21 || position == 26 || position == 27 || position == 32 || position == 33 || position == 35
                            || position == 39 || position == 40 || position == 47 || position == 57 || position == 64 || position == 68
                            || position == 69 || position == 76) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_f);
                    if (position == 25) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_g);
                    if (position == 31) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_h);
                    if (position == 42 || position == 52) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_i);
                    if (position == 53 || position == 62) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_j);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_k);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_l);
                    if (position == 54) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_m);
                    if (position == 13 || position == 20 || position == 61 || position == 72) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_n);
                    if (position == 15 || position == 22 || position == 48 || position == 58) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_o);
                    if (position == 2 || position == 8 || position == 19 || position == 45 || position == 73 || position == 74) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_p);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_q);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_r);
                    if (position == 56 || position == 70) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_s);
                    if (position == 4 || position == 10 || position == 34 || position == 75) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_t);
                    if (position == 7 || position == 28 || position == 38 || position == 41) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_u);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_v);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_w);
                    if (position == 18) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_x);
                    if (position == 46) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_y);
                    if (position == 3 || position == 9) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_17_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_17_Activity.this, btn_key_z);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_17_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
    }

    private void initializePlayer(int pos, List<Uri> urilist, List<String> mysound_list) {

        if (position == 1 || position == 2 || position == 3 || position == 4 || position == 7 || position == 13 || position == 14
                || position == 15 || position == 18 || position == 25 || position == 31 || position == 42 || position == 46 || position == 47
                || position == 51 || position == 53 || position == 54 || position == 55 || position == 56 || position == 8 || position == 19
                || position == 45 || position == 73 || position == 74 || position == 9 || position == 10 || position == 34 || position == 75
                || position == 28 || position == 38 || position == 41 || position == 13 || position == 20 || position == 61 || position == 72
                || position == 21 || position == 26 || position == 27 || position == 32 || position == 33 || position == 35 || position == 39
                || position == 40 || position == 22 || position == 48 || position == 58 || position == 52 || position == 57 || position == 64
                || position == 68 || position == 69 || position == 76 || position == 63 || position == 62 || position == 67 || position == 70) {

            layout_keyboard.setVisibility(View.VISIBLE);
            layout_bottom_nav.setVisibility(View.GONE);
        } else {

            layout_bottom_nav.setVisibility(View.VISIBLE);
            layout_keyboard.setVisibility(View.GONE);
        }
//        if (position >= 3 && position < 5) {
//            cl_order.setVisibility(View.VISIBLE);
//            tv_ord1.setText("set");
//            tv_ord2.setText("step");
//            tv_ord3.setText("sat");
//        } else {
//            cl_order.setVisibility(View.GONE);
//        }

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
//        if (position==4)
//        {
//            delayVideo(8000);
//        }
//        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setVolume(0, 0);
//            }
//        });
        if (position == 0 || position == 6) {
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
                    } else {
                        position -= 1;
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(Dict_17_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Dict_2_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                                //finish();
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
                    image_anim_last(Dict_17_Activity.this, iv_next);
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
                    tv_lastpage_text.setText("You Completed Dictionary Lesson 17");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Dict_17_Activity","YES",Dict_17_Activity.this);
                            Intent intent = new Intent(Dict_17_Activity.this, Writing_Chapt19_Activity.class);
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

            if (StaticValues.GetPreferences("Last_Open_position", Dict_17_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Dict_17_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Dict_17_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Dict_17_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Dict_17_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Dict_17_Activity", Dict_17_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Dictionary", Dict_17_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Dict_17_Activity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Dict_17_Activity", Dict_17_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Dictionary", Dict_17_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Dict_17_Activity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Dict_17_Activity", Dict_17_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Dictionary", Dict_17_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Dict_17_Activity.this);
    }
}