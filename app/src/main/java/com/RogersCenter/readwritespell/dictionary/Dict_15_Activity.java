package com.RogersCenter.readwritespell.dictionary;

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
import com.RogersCenter.readwritespell.quiz.Quiz1Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt12_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt13_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;
import static com.RogersCenter.readwritespell.Animations.keyboard_button_anim;
import static com.RogersCenter.readwritespell.Animations.keyboard_imgbutton_anim;

public class Dict_15_Activity extends AppCompatActivity {

    VideoView vv;
    View layout_keyboard, layout_bottom_nav;
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
    View clickview;
    String[] strArray = {"exp_vids/dict_15_1.webm", "exp_vids/dict_15_2.webm", "exp_vids/dict_15_3.webm", "exp_vids/dict_15_4.webm", "exp_vids/dict_15_5.webm", "exp_vids/dict_15_6.webm",
            "exp_vids/dict_15_7.webm", "exp_vids/dict_15_8.webm", "exp_vids/dict_15_9.webm", "exp_vids/dict_15_10.webm", "exp_vids/dict_15_11.webm", "exp_vids/dict_15_12.webm","exp_vids/dict_15_13.webm",
            "exp_vids/dict_15_14.webm","exp_vids/dict_15_15.webm","exp_vids/dict_15_16.webm","exp_vids/dict_15_17.webm","exp_vids/dict_15_18.webm","exp_vids/dict_15_19.webm","exp_vids/dict_15_20.webm",
            "exp_vids/dict_15_21.webm","exp_vids/dict_15_22.webm","exp_vids/dict_15_23.webm","exp_vids/dict_15_24.webm","exp_vids/dict_15_25.webm","exp_vids/dict_15_26.webm","exp_vids/dict_15_27.webm",
            "exp_vids/dict_15_28.webm","exp_vids/dict_15_29.webm","exp_vids/dict_15_30.webm","exp_vids/dict_15_31.webm","exp_vids/dict_15_32.webm","exp_vids/dict_15_33.webm","exp_vids/dict_15_34.webm",
            "exp_vids/dict_15_35.webm","exp_vids/dict_15_36.webm","exp_vids/dict_15_37.webm","exp_vids/dict_15_38.webm","exp_vids/dict_15_39.webm","exp_vids/dict_15_40.webm","exp_vids/dict_15_41.webm",
            "exp_vids/dict_15_42.webm","exp_vids/dict_15_43.webm","exp_vids/dict_15_44.webm","exp_vids/dict_15_45.webm","exp_vids/dict_15_46.webm","exp_vids/dict_15_47.webm","exp_vids/dict_15_48.webm",
            "exp_vids/dict_15_49.webm","exp_vids/dict_15_50.webm","exp_vids/dict_15_51.webm","exp_vids/dict_15_52.webm","exp_vids/dict_15_53.webm","exp_vids/dict_15_54.webm","exp_vids/dict_15_55.webm",
            "exp_vids/dict_15_56.webm","exp_vids/dict_15_57.webm","exp_vids/dict_15_58.webm","exp_vids/dict_15_59.webm","exp_vids/dict_15_60.webm","exp_vids/dict_15_61.webm","exp_vids/dict_15_62.webm",
            "exp_vids/dict_15_61.webm","exp_vids/dict_15_62.webm","exp_vids/dict_15_63.webm","exp_vids/dict_15_64.webm","exp_vids/dict_15_65.webm","exp_vids/dict_15_66.webm","exp_vids/dict_15_67.webm",
            "exp_vids/dict_15_68.webm","exp_vids/dict_15_69.webm","exp_vids/dict_15_70.webm","exp_vids/dict_15_71.webm","exp_vids/dict_15_72.webm","exp_vids/dict_15_73.webm","exp_vids/dict_15_74.webm",
            "exp_vids/dict_15_75.webm","exp_vids/dict_15_76.webm","exp_vids/dict_15_77.webm","exp_vids/dict_15_78.webm","exp_vids/dict_15_79.webm","exp_vids/dict_15_80.webm","exp_vids/dict_15_81.webm",
            "exp_vids/dict_15_82.webm","exp_vids/dict_15_83.webm","exp_vids/dict_15_84.webm","exp_vids/dict_15_85.webm","exp_vids/dict_15_86.webm","exp_vids/dict_15_87.webm","exp_vids/dict_15_88.webm",
            "exp_vids/dict_15_89.webm","exp_vids/dict_15_90.webm","exp_vids/dict_15_91.webm","exp_vids/dict_15_92.webm","exp_vids/dict_15_93.webm","exp_vids/dict_15_94.webm","exp_vids/dict_15_95.webm",
            "exp_vids/dict_15_96.webm","exp_vids/dict_15_97.webm","exp_vids/dict_15_98.webm","exp_vids/dict_15_99.webm","exp_vids/dict_15_100.webm","exp_vids/dict_15_101.webm","exp_vids/dict_15_102.webm",
            "exp_vids/dict_15_103.webm","exp_vids/dict_15_104.webm","exp_vids/dict_15_105.webm","exp_vids/dict_15_106.webm","exp_vids/dict_15_107.webm","exp_vids/dict_15_108.webm","exp_vids/dict_15_109.webm",
            "exp_vids/dict_15_110.webm","exp_vids/dict_15_111.webm","exp_vids/dict_15_112.webm","exp_vids/dict_15_113.webm","exp_vids/dict_15_114.webm","exp_vids/dict_15_115.webm","exp_vids/dict_15_116.webm",
            "exp_vids/dict_15_117.webm","exp_vids/dict_15_118.webm","exp_vids/dict_15_119.webm","exp_vids/dict_15_120.webm","exp_vids/dict_15_121.webm","exp_vids/dict_15_122.webm","exp_vids/dict_15_123.webm",
            "exp_vids/dict_15_124.webm","exp_vids/dict_15_125.webm","exp_vids/dict_15_126.webm","exp_vids/dict_15_127.webm","exp_vids/dict_15_128.webm","exp_vids/dict_15_129.webm","exp_vids/dict_15_130.webm",
            "exp_vids/dict_15_131.webm","exp_vids/dict_15_132.webm","exp_vids/dict_15_133.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dict_15);

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
                image_anim(Dict_15_Activity.this,iv_back);
                stopvid();
                finish();
            }
        });
        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Dict_15_Activity.this,iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                   checkfileexists();
                } else {
                    Toast.makeText(Dict_15_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Dict_15_Activity.this,iv_playpause);
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
                image_anim(Dict_15_Activity.this,iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                   checkfileexists();
                } else {
                    position -= 1;
                    if(position==strArray.length-1)
                    {
                        View layout_lastpage  = findViewById(R.id.layout_lastpage);
                        TextView tv_lastpage_text= findViewById(R.id.tv_lastpage_text);
                        ImageButton ib_home= findViewById(R.id.ib_home);
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                layout_lastpage.setVisibility(View.VISIBLE);
                                tv_lastpage_text.setText("You Completed Dictionary Lesson 15");
                                ib_home.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        StaticValues.SavePreferences("Dict_15_Activity","YES",Dict_15_Activity.this);
                                        Intent intent = new Intent(Dict_15_Activity.this, Reading_Chapt13_Activity.class);
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
                image_anim(Dict_15_Activity.this,iv_home);
                Intent i1 = new Intent(Dict_15_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });


        imgbtn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Dict_15_Activity.this,imgbtn_prev);
                    if (position > 0) {
//                    stopvid();
                        position -= 1;
                       checkfileexists();
                    } else {
                        Toast.makeText(Dict_15_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        imgbtn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Dict_15_Activity.this,imgbtn_home);
                    stopvid();
                    finish();
                }
            }
        });
        btn_key_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_a);
                    if (position == 7||position==61||position==97) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_b);
                    if (position ==35||position==90) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_c);
                    if (position ==14) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_d);
                    if (position == 11||position==32) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_e);
                    if (position == 3 ||position==12||position==15||position==19||position==72||position==79||position==86||position==132) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_f);
                    if (position == 18||position==23) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_g);
                    if (position == 29||position == 44||position == 51||position == 58||position == 67||position == 74||position == 81
                            ||position == 88||position == 99||position == 107||position == 117||position == 125||position == 136) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_h);
                    if (position == 45 ||position==52||position==56||position==59||position == 68||position == 75||position == 82||position == 89
                            ||position == 100||position == 104||position == 108||position == 112||position == 118||position == 112
                            ||position == 122||position == 126||position == 137) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    }
                    else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_i);
                    if (position == 43 ||position==50|position==57||position==66||position==73||position==80||position == 87) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_j);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_k);
                    if (position == 38) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_l);
                    if (position ==8||position==42||position==96) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_m);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_n);
                    if (position == 2 || position==85||position==49||position == 133) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_o);
                    if (position == 24||position==25||position == 30||position == 31||position == 36||position == 37||position == 91
                            ||position == 105||position == 113||position == 115||position == 123||position == 134) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_p);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_q);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_r);
                    if (position == 6||position==92||position == 114) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_s);
                    if (position == 65) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_t);
                    if (position == 5||position==26||position == 46||position == 53||position == 76||position == 103||position == 111
                            ||position == 121||position == 127)
                    {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_u);
                    if (position == 4||position == 13||position == 98||position == 106||position == 116||position == 124||position == 135)
                    {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_v);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_w);
                    if (position == 20||position == 60||position == 78)
                    {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_x);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_y);
                    if (position == 62)
                    {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Dict_15_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Dict_15_Activity.this,btn_key_z);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Dict_15_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
    }

    private void initializePlayer(int pos, List<Uri> urilist, List<String> mysound_list) {

        if (position == 2||position == 3||position == 4||position == 5||position == 6||position == 7||position == 8
                ||position == 11||position == 14||position == 18||position == 20||position == 24||position == 29||position == 35
                ||position == 38||position == 43||position == 45||position == 49||position == 62||position == 65||position == 104
                ||position == 107||position == 85||position == 133||position == 12||position == 15||position == 19||position == 72
                ||position == 79||position == 86||position == 132||position == 13||position == 98||position == 106||position == 116
                ||position == 124||position == 135||position == 26||position == 46||position == 53||position == 76||position == 103
                ||position == 111||position == 92||position == 114||position == 56||position == 61||position == 97||position == 42
                ||position == 96||position == 32||position == 23||position == 60||position == 78||position == 25||position == 30
                ||position == 31||position == 36||position == 37||position == 91||position == 105||position == 44||position == 51
                ||position == 58||position == 67||position == 74||position == 81||position == 88||position == 99||position == 90
                ||position == 50||position == 57||position == 66||position == 73||position == 80||position == 87||position == 52
                ||position == 56||position == 59||position == 68||position == 75||position == 82||position == 89||position == 100
                ||position == 108||position == 112||position == 118||position == 122||position == 126||position == 137||position == 113
                ||position == 115||position == 123||position == 134||position == 117||position == 125||position ==136||position == 121
                ||position ==127) {

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
                if (progressDoalog1!=null)
                {
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
        if(position==1||position==10||position==17||position == 22||position == 28||position == 34||position == 48||position == 55
                ||position == 64||position == 71||position == 77||position == 84||position == 94||position == 102||position == 110
                ||position == 120||position == 129||position == 131)
        {
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
                    }
                    else {
                        position -= 1;
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(Dict_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Dict_2_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                                //finish();
                            }
                        });

                    }
                }
            });
        }else {
            clickview.setVisibility(View.GONE);
            iv_next.setClickable(true);
            iv_next.setEnabled(true);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Dict_15_Activity.this,iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
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
                    tv_lastpage_text.setText("You Completed Dictionary Lesson 15");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Dict_15_Activity","YES",Dict_15_Activity.this);
                            Intent intent = new Intent(Dict_15_Activity.this, Reading_Chapt13_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }
    public void checkfileexists() {

            if (StaticValues.GetPreferences("Last_Open_position", Dict_15_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Dict_15_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Dict_15_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Dict_15_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Dict_15_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Dict_15_Activity", Dict_15_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Dictionary", Dict_15_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Dict_15_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Dict_15_Activity", Dict_15_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Dictionary", Dict_15_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Dict_15_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Dict_15_Activity", Dict_15_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Dictionary", Dict_15_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Dict_15_Activity.this);
    }
}