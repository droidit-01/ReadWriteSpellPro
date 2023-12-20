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
import com.RogersCenter.readwritespell.dictionary.Dict_11_Activity;
import com.RogersCenter.readwritespell.dictionary.Dict_12_Activity;
import com.RogersCenter.readwritespell.quiz.Quiz1Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;
import static com.RogersCenter.readwritespell.Animations.keyboard_button_anim;
import static com.RogersCenter.readwritespell.Animations.keyboard_imgbutton_anim;

public class Lang_16_Activity extends AppCompatActivity {

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
    String[] strArray = {"exp_vids/lang_16_1.webm", "exp_vids/lang_16_2.webm", "exp_vids/lang_16_3.webm", "exp_vids/lang_16_4.webm", "exp_vids/lang_16_5.webm", "exp_vids/lang_16_6.webm",
            "exp_vids/lang_16_7.webm", "exp_vids/lang_16_8.webm", "exp_vids/lang_16_9.webm", "exp_vids/lang_16_10.webm", "exp_vids/lang_16_11.webm", "exp_vids/lang_16_12.webm","exp_vids/lang_16_13.webm",
            "exp_vids/lang_16_14.webm","exp_vids/lang_16_15.webm","exp_vids/lang_16_16.webm","exp_vids/lang_16_17.webm","exp_vids/lang_16_18.webm","exp_vids/lang_16_19.webm","exp_vids/lang_16_20.webm",
            "exp_vids/lang_16_21.webm","exp_vids/lang_16_22.webm","exp_vids/lang_16_23.webm","exp_vids/lang_16_24.webm","exp_vids/lang_16_25.webm","exp_vids/lang_16_26.webm","exp_vids/lang_16_27.webm",
            "exp_vids/lang_16_28.webm","exp_vids/lang_16_29.webm","exp_vids/lang_16_30.webm","exp_vids/lang_16_31.webm","exp_vids/lang_16_32.webm","exp_vids/lang_16_33.webm","exp_vids/lang_16_34.webm",
            "exp_vids/lang_16_35.webm","exp_vids/lang_16_36.webm","exp_vids/lang_16_37.webm","exp_vids/lang_16_38.webm","exp_vids/lang_16_39.webm","exp_vids/lang_16_40.webm","exp_vids/lang_16_41.webm",
            "exp_vids/lang_16_42.webm","exp_vids/lang_16_43.webm","exp_vids/lang_16_44.webm","exp_vids/lang_16_45.webm","exp_vids/lang_16_46.webm","exp_vids/lang_16_47.webm","exp_vids/lang_16_48.webm",
            "exp_vids/lang_16_49.webm","exp_vids/lang_16_50.webm","exp_vids/lang_16_51.webm","exp_vids/lang_16_52.webm","exp_vids/lang_16_53.webm","exp_vids/lang_16_54.webm","exp_vids/lang_16_55.webm",
            "exp_vids/lang_16_56.webm","exp_vids/lang_16_57.webm","exp_vids/lang_16_58.webm","exp_vids/lang_16_59.webm","exp_vids/lang_16_60.webm","exp_vids/lang_16_61.webm","exp_vids/lang_16_62.webm",
            "exp_vids/lang_16_63.webm","exp_vids/lang_16_64.webm","exp_vids/lang_16_65.webm","exp_vids/lang_16_66.webm","exp_vids/lang_16_67.webm","exp_vids/lang_16_68.webm","exp_vids/lang_16_69.webm",
            "exp_vids/lang_16_70.webm","exp_vids/lang_16_71.webm","exp_vids/lang_16_72.webm","exp_vids/lang_16_73.webm","exp_vids/lang_16_74.webm","exp_vids/lang_16_75.webm","exp_vids/lang_16_76.webm",
            "exp_vids/lang_16_77.webm","exp_vids/lang_16_78.webm","exp_vids/lang_16_79.webm","exp_vids/lang_16_80.webm","exp_vids/lang_16_81.webm","exp_vids/lang_16_82.webm","exp_vids/lang_16_83.webm",
            "exp_vids/lang_16_84.webm","exp_vids/lang_16_85.webm","exp_vids/lang_16_86.webm","exp_vids/lang_16_87.webm","exp_vids/lang_16_88.webm","exp_vids/lang_16_89.webm","exp_vids/lang_16_90.webm",
            "exp_vids/lang_16_91.webm","exp_vids/lang_16_92.webm","exp_vids/lang_16_93.webm","exp_vids/lang_16_94.webm","exp_vids/lang_16_95.webm","exp_vids/lang_16_96.webm","exp_vids/lang_16_97.webm",
            "exp_vids/lang_16_98.webm","exp_vids/lang_16_99.webm","exp_vids/lang_16_100.webm","exp_vids/lang_16_101.webm","exp_vids/lang_16_102.webm","exp_vids/lang_16_103.webm","exp_vids/lang_16_104.webm",
            "exp_vids/lang_16_105.webm","exp_vids/lang_16_106.webm","exp_vids/lang_16_107.webm","exp_vids/lang_16_108.webm","exp_vids/lang_16_109.webm","exp_vids/lang_16_110.webm","exp_vids/lang_16_111.webm",
            "exp_vids/lang_16_113.webm","exp_vids/lang_16_114.webm","exp_vids/lang_16_115.webm","exp_vids/lang_16_116.webm","exp_vids/lang_16_117.webm","exp_vids/lang_16_118.webm","exp_vids/lang_16_119.webm",
            "exp_vids/lang_16_120.webm","exp_vids/lang_16_121.webm","exp_vids/lang_16_122.webm","exp_vids/lang_16_123.webm","exp_vids/lang_16_124.webm","exp_vids/lang_16_125.webm","exp_vids/lang_16_126.webm",
            "exp_vids/lang_16_127.webm","exp_vids/lang_16_128.webm","exp_vids/lang_16_129.webm","exp_vids/lang_16_130.webm","exp_vids/lang_16_131.webm","exp_vids/lang_16_132.webm","exp_vids/lang_16_133.webm",
            "exp_vids/lang_16_134.webm","exp_vids/lang_16_135.webm","exp_vids/lang_16_136.webm","exp_vids/lang_16_137.webm","exp_vids/lang_16_138.webm","exp_vids/lang_16_139.webm","exp_vids/lang_16_140.webm",
            "exp_vids/lang_16_141.webm","exp_vids/lang_16_142.webm","exp_vids/lang_16_143.webm","exp_vids/lang_16_144.webm","exp_vids/lang_16_145.webm","exp_vids/lang_16_146.webm","exp_vids/lang_16_147.webm",
            "exp_vids/lang_16_148.webm","exp_vids/lang_16_149.webm","exp_vids/lang_16_150.webm","exp_vids/lang_16_151.webm","exp_vids/lang_16_152.webm","exp_vids/lang_16_153.webm","exp_vids/lang_16_154.webm",
            "exp_vids/lang_16_155.webm","exp_vids/lang_16_156.webm","exp_vids/lang_16_157.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_16);

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
                image_anim(Lang_16_Activity.this,iv_back);
                stopvid();
                finish();
            }
        });
        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_16_Activity.this,iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                   checkfileexists();
                } else {
                    Toast.makeText(Lang_16_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_16_Activity.this,iv_playpause);
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
                image_anim(Lang_16_Activity.this,iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                   checkfileexists();
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_16_Activity.this, Quiz1Activity.class);
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
                image_anim(Lang_16_Activity.this,iv_home);
                Intent i1 = new Intent(Lang_16_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });
        view_clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 1 || position == 7|| position == 13|| position == 21||position==28||position==35||position==43
                            ||position==50||position==57||position==68||position==74||position==82||position==89||position==97
                            ||position==107||position==118||position==135||position==147) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_16_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });

        imgbtn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Lang_16_Activity.this,imgbtn_prev);
                    if (position > 0) {
//                    stopvid();
                        position -= 1;
                       checkfileexists();
                    } else {
                        Toast.makeText(Lang_16_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        imgbtn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Lang_16_Activity.this,imgbtn_home);
                    stopvid();
                    finish();
                }
            }
        });

        btn_key_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_a);
                    if (position == 30||position==53||position==63||position==76||position==91||position==108) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_b);
                    if (position == 29) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_c);
                    if (position == 4||position==10||position==17||position==71||position==78||position==86||position==101||position==112) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_d);
                    if (position == 2||position==24||position==31||position==38) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_e);
                    if (position == 3||position==26||position==33||position==37||position==40||position==48||position==58||position==60
                            ||position==111||position==120||position==137||position==139||position==149) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_f);
                    if (position == 22||position==99||position==109||position==110) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_g);
                    if (position ==25 ||position==32||position==39||position==51) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_h);
                    if (position == 45||position==55||position==62||position==72||position==79||position==87) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_i);
                    if (position == 16||position==69||position==93||position==100||position==103||position==114||position==123
                            ||position==128||position==131||position==141||position==143||position==152) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_j);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_k);
                    if (position == 5||position==11||position==18) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_l);
                    if (position == 36||position==59||position==138) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_m);
                    if (position == 75||position==127) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_n);
                    if (position == 47||position==64||position==83||position==90||position==95||position==105||position==116
                            ||position==121||position==125||position==133||position==145||position==154) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_o);
                    if (position == 9||position==46||position==84||position==94||position==104||position==115||position==124||position==132
                            ||position==144||position==153) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_p);
                    if (position == 44||position==54||position==61) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_q);
                    if (position == 148) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_r);
                    if (position == 8||position==15||position==52) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_s);
                    if (position == 122||position==129||position==130||position==142||position==150) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_t);
                    if (position == 14||position==65||position==70||position==77||position==85||position==92||position==102||position==113
                            ||position==119||position==136||position==151) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_u);
                    if (position == 23) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_v);
                    if (position == 140) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_w);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_x);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_y);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Lang_16_Activity.this,btn_key_z);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_16_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_16_Activity.this, R.raw.tryagain);
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

        if (position == 1 || position == 7|| position == 13|| position == 21||position==28||position==35||position==43
                ||position==50||position==57||position==68||position==74||position==82||position==89||position==97||position==107
                ||position==118||position==135||position==147)
        {
            view_clicked.setVisibility(View.VISIBLE);
            layout_keyboard.setVisibility(View.GONE);
            layout_bottom_nav.setVisibility(View.VISIBLE);
        }
        else if (position == 0 || position == 6|| position == 12|| position == 19||position==20||position==27||position==34||position==41
                ||position==42||position==49||position==56||position==66||position==67||position==73||position==80||position==81||position==88
                ||position==96||position==98||position==106||position==117||position==126||position==134||position==146||position==155||position==156){
            view_clicked.setVisibility(View.GONE);
            layout_keyboard.setVisibility(View.GONE);
            layout_bottom_nav.setVisibility(View.VISIBLE);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Lang_16_Activity.this,iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
        }
        else {
            view_clicked.setVisibility(View.GONE);
            layout_keyboard.setVisibility(View.VISIBLE);
            layout_bottom_nav.setVisibility(View.GONE);

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
                    tv_lastpage_text.setText("You Completed Language Lesson 16");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Lang_16_Activity","YES", Lang_16_Activity.this);
                            Intent intent = new Intent(Lang_16_Activity.this, Dict_12_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }

    public void checkfileexists() {
            if (StaticValues.GetPreferences("Last_Open_position", Lang_16_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Lang_16_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Lang_16_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Lang_16_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Lang_16_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_16_Activity", Lang_16_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_16_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_16_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_16_Activity", Lang_16_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_16_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_16_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_16_Activity", Lang_16_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_16_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_16_Activity.this);
    }
}