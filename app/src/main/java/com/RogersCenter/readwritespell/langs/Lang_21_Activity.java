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
import android.widget.Button;
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
import com.RogersCenter.readwritespell.dictionary.Dict_14_Activity;
import com.RogersCenter.readwritespell.quiz.Quiz1Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt11_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;
import static com.RogersCenter.readwritespell.Animations.keyboard_button_anim;
import static com.RogersCenter.readwritespell.Animations.keyboard_imgbutton_anim;

public class Lang_21_Activity extends AppCompatActivity {

    VideoView vv;
    View layout_keyboard, layout_bottom_nav,view_clicked;
    ImageButton imgbtn_prev,imgbtn_home;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    Button btn_key_a, btn_key_b, btn_key_c,btn_key_d,btn_key_e,btn_key_f,btn_key_g,btn_key_h,btn_key_i,btn_key_j,btn_key_k,btn_key_l,btn_key_m;
    Button btn_key_n, btn_key_o, btn_key_p,btn_key_q,btn_key_r,btn_key_s,btn_key_t,btn_key_u,btn_key_v,btn_key_w,btn_key_x,btn_key_y,btn_key_z;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    String[] strArray = {"exp_vids/lang_21_1.webm", "exp_vids/lang_21_2.webm", "exp_vids/lang_21_3.webm", "exp_vids/lang_21_4.webm", "exp_vids/lang_21_5.webm", "exp_vids/lang_21_6_cl.webm",
            "exp_vids/lang_21_7_a.webm", "exp_vids/lang_21_8_i.webm", "exp_vids/lang_21_9_m.webm", "exp_vids/lang_21_10.webm", "exp_vids/lang_21_11_cl.webm", "exp_vids/lang_21_12_s.webm","exp_vids/lang_21_13_a.webm",
            "exp_vids/lang_21_14_i.webm","exp_vids/lang_21_15_l.webm","exp_vids/lang_21_16.webm","exp_vids/lang_21_17_cl.webm","exp_vids/lang_21_18_b.webm","exp_vids/lang_21_19_r.webm","exp_vids/lang_21_20_a.webm",
            "exp_vids/lang_21_21_i.webm","exp_vids/lang_21_22_n.webm","exp_vids/lang_21_23.webm","exp_vids/lang_21_24_cl.webm","exp_vids/lang_21_25_d.webm","exp_vids/lang_21_26_a.webm","exp_vids/lang_21_27_y.webm",
            "exp_vids/lang_21_28.webm","exp_vids/lang_21_29_cl.webm","exp_vids/lang_21_30_s.webm","exp_vids/lang_21_31_a.webm","exp_vids/lang_21_32_y.webm","exp_vids/lang_21_33.webm","exp_vids/lang_21_34_cl.webm",
            "exp_vids/lang_21_35_t.webm","exp_vids/lang_21_36_r.webm","exp_vids/lang_21_37_a.webm","exp_vids/lang_21_38_y.webm","exp_vids/lang_21_39.webm","exp_vids/lang_21_40_cl.webm","exp_vids/lang_21_41_e.webm",
            "exp_vids/lang_21_42_a.webm","exp_vids/lang_21_43_t.webm","exp_vids/lang_21_44.webm","exp_vids/lang_21_45_cl.webm","exp_vids/lang_21_46_r.webm","exp_vids/lang_21_47_e.webm","exp_vids/lang_21_48_a.webm",
            "exp_vids/lang_21_49_d.webm","exp_vids/lang_21_50.webm","exp_vids/lang_21_51_cl.webm","exp_vids/lang_21_52_b.webm","exp_vids/lang_21_53_e.webm","exp_vids/lang_21_54_a.webm","exp_vids/lang_21_55_n.webm",
            "exp_vids/lang_21_56.webm","exp_vids/lang_21_57_cl.webm","exp_vids/lang_21_58_f.webm","exp_vids/lang_21_59_e.webm","exp_vids/lang_21_60_e.webm","exp_vids/lang_21_61_t.webm","exp_vids/lang_21_62.webm",
            "exp_vids/lang_21_63_cl.webm","exp_vids/lang_21_64_q.webm","exp_vids/lang_21_65_e.webm","exp_vids/lang_21_66_e.webm","exp_vids/lang_21_67_n.webm","exp_vids/lang_21_68.webm","exp_vids/lang_21_69_cl.webm",
            "exp_vids/lang_21_70_t.webm","exp_vids/lang_21_71_e.webm","exp_vids/lang_21_72_e.webm","exp_vids/lang_21_73_t.webm","exp_vids/lang_21_74_h.webm","exp_vids/lang_21_75.webm","exp_vids/lang_21_76_cl.webm",
            "exp_vids/lang_21_77_s.webm","exp_vids/lang_21_78_e.webm","exp_vids/lang_21_79_i.webm","exp_vids/lang_21_80_z.webm","exp_vids/lang_21_81_e.webm","exp_vids/lang_21_82.webm","exp_vids/lang_21_83_cl.webm",
            "exp_vids/lang_21_84_c.webm","exp_vids/lang_21_85_e.webm","exp_vids/lang_21_86_i.webm","exp_vids/lang_21_87_l.webm","exp_vids/lang_21_88_i.webm","exp_vids/lang_21_89_n.webm","exp_vids/lang_21_90_g.webm",
            "exp_vids/lang_21_91.webm","exp_vids/lang_21_92_cl.webm","exp_vids/lang_21_93_k.webm","exp_vids/lang_21_94_i.webm","exp_vids/lang_21_95_d.webm","exp_vids/lang_21_96_n.webm","exp_vids/lang_21_97_e.webm",
            "exp_vids/lang_21_98_y.webm","exp_vids/lang_21_99.webm","exp_vids/lang_21_100.webm","exp_vids/lang_21_101_c.webm","exp_vids/lang_21_102_h.webm","exp_vids/lang_21_103_i.webm","exp_vids/lang_21_104_m.webm",
            "exp_vids/lang_21_105_n.webm","exp_vids/lang_21_106_e.webm","exp_vids/lang_21_107_y.webm","exp_vids/lang_21_108.webm","exp_vids/lang_21_109_cl.webm","exp_vids/lang_21_110_g.webm","exp_vids/lang_21_111_o.webm",
            "exp_vids/lang_21_112_a.webm","exp_vids/lang_21_113_t.webm","exp_vids/lang_21_114.webm","exp_vids/lang_21_115_cl.webm","exp_vids/lang_21_116_f.webm","exp_vids/lang_21_117_o.webm","exp_vids/lang_21_118_a.webm",
            "exp_vids/lang_21_119_m.webm","exp_vids/lang_21_120.webm","exp_vids/lang_21_121_cl.webm","exp_vids/lang_21_122.webm","exp_vids/lang_21_123_cl.webm","exp_vids/lang_21_124_t.webm","exp_vids/lang_21_125_o.webm",
            "exp_vids/lang_21_126_e.webm","exp_vids/lang_21_127.webm","exp_vids/lang_21_128_cl.webm","exp_vids/lang_21_129.webm","exp_vids/lang_21_130_cl.webm","exp_vids/lang_21_131.webm","exp_vids/lang_21_132_cl.webm",
            "exp_vids/lang_21_133.webm","exp_vids/lang_21_134_cl.webm","exp_vids/lang_21_135.webm","exp_vids/lang_21_136_cl.webm","exp_vids/lang_21_137.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_21);

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
        view_clicked = findViewById(R.id.view_clicked);

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
                image_anim(Lang_21_Activity.this,iv_back);
                stopvid();
                finish();
            }
        });
        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_21_Activity.this,iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                   checkfileexists();
                } else {
                    Toast.makeText(Lang_21_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_21_Activity.this,iv_playpause);
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
                image_anim(Lang_21_Activity.this,iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                   checkfileexists();
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_21_Activity.this, Quiz1Activity.class);
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
                image_anim(Lang_21_Activity.this,iv_home);
                Intent i1 = new Intent(Lang_21_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });


        imgbtn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Lang_21_Activity.this,imgbtn_prev);
                    if (position > 0) {
//                    stopvid();
                        position -= 1;
                       checkfileexists();
                    } else {
                        Toast.makeText(Lang_21_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        imgbtn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Lang_21_Activity.this,imgbtn_home);
                    stopvid();
                    finish();
                }
            }
        });

        view_clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 5 || position == 10|| position == 16|| position == 23||position==28||position==33||position==39||position==44
                            ||position==50||position==56||position==62||position==68||position==75||position==82||position==91||position==108
                            ||position==114||position==120||position==122||position==127||position==129||position==131||position==133||position==135)
                    {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_21_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });

        btn_key_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_a);
                    if (position == 6||position==12||position==19||position==25||position==30||position==36
                            ||position==41||position==47||position==53||position==111||position==117) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_b);
                    if (position ==17||position==51) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_c);
                    if (position == 83||position==100) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_d);
                    if (position == 24||position==48||position==94) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_e);
                    if (position == 40||position==46||position==52||position==58||position==59||position==64
                            ||position==65||position==70||position==71||position==77||position==80||position==84
                            ||position==96||position==105||position==125) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_f);
                    if (position == 57||position==115) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_g);
                    if (position ==89||position==109) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_h);
                    if (position ==73||position==101) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_i);
                    if (position == 7||position==13||position==20||position==78||position==85||position==87||position==93
                            ||position==102) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_j);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_k);
                    if (position == 92) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_l);
                    if (position == 14||position==86) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_m);
                    if (position == 8||position==103||position==118) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_n);
                    if (position == 21||position==54||position==66||position==88||position==95||position==104) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_o);
                    if (position == 110||position==116||position==124) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_p);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_q);
                    if (position == 63) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_r);
                    if (position == 18||position==35||position==45) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_s);
                    if (position ==11||position==29||position==76) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_t);
                    if (position == 34||position==42||position==60||position==69||position==72||position==112||position==123) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_u);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_v);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_w);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_x);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_y);
                    if (position == 26||position==31||position==37||position==97||position==106) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_21_Activity.this,btn_key_z);
                    if (position ==79) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_21_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_21_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
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

        if (position == 5 || position == 10|| position == 16|| position == 23||position==28||position==33||position==39||position==44
                ||position==50||position==56||position==62||position==68||position==75||position==82||position==91||position==108
                ||position==114||position==120||position==122||position==127||position==129||position==131||position==133||position==135)
        {
            view_clicked.setVisibility(View.VISIBLE);
            layout_keyboard.setVisibility(View.GONE);
            layout_bottom_nav.setVisibility(View.VISIBLE);
        }
        else if (position == 0 || position == 1|| position == 2|| position == 3||position==4||position==9||position==15||position==22
                ||position==27||position==32||position==38||position==43||position==49||position==55||position==61||position==67||position==74
                ||position==81||position==90||position==98||position==99||position==107||position==113||position==119||position==121||position==126
                ||position==128||position==130||position==132||position==134||position==136){
            view_clicked.setVisibility(View.GONE);
            layout_keyboard.setVisibility(View.GONE);
            layout_bottom_nav.setVisibility(View.VISIBLE);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Lang_21_Activity.this,iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
        }
        else {
            view_clicked.setVisibility(View.GONE);
            layout_keyboard.setVisibility(View.VISIBLE);
            layout_bottom_nav.setVisibility(View.GONE);
        }   vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
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
                    tv_lastpage_text.setText("You Completed Language Lesson 21");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Lang_21_Activity","YES", Lang_21_Activity.this);
                            Intent intent = new Intent(Lang_21_Activity.this, Dict_14_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }
    public void checkfileexists() {
            if (StaticValues.GetPreferences("Last_Open_position", Lang_21_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Lang_21_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Lang_21_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Lang_21_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Lang_21_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_21_Activity", Lang_21_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_21_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_21_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_21_Activity", Lang_21_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_21_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_21_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_21_Activity", Lang_21_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_21_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_21_Activity.this);
    }
}