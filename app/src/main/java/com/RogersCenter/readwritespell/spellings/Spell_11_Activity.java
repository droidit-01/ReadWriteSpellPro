package com.RogersCenter.readwritespell.spellings;

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
import com.RogersCenter.readwritespell.writing.Writing_Chapt13_Activity;
import com.RogersCenter.readwritespell.writing.Writing_Chapt14_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;
import static com.RogersCenter.readwritespell.Animations.keyboard_button_anim;
import static com.RogersCenter.readwritespell.Animations.keyboard_imgbutton_anim;

public class Spell_11_Activity extends AppCompatActivity {

    VideoView vv;
    View layout_keyboard, layout_bottom_nav;
    ImageButton imgbtn_prev,imgbtn_home;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    Button btn_key_a, btn_key_b, btn_key_c, btn_key_d, btn_key_e, btn_key_f, btn_key_g, btn_key_h, btn_key_i, btn_key_j, btn_key_k, btn_key_l, btn_key_m;
    Button btn_key_n, btn_key_o, btn_key_p, btn_key_q, btn_key_r, btn_key_s, btn_key_t, btn_key_u, btn_key_v, btn_key_w, btn_key_x, btn_key_y, btn_key_z;
    View view_clicked;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    String[] strArray = {"exp_vids/spell_11_1_c.webm","exp_vids/spell_11_2_o.webm","exp_vids/spell_11_3_l.webm","exp_vids/spell_11_4_d.webm","exp_vids/spell_11_5_c.webm","exp_vids/spell_11_6_h.webm",
            "exp_vids/spell_11_7_i.webm","exp_vids/spell_11_8_l.webm","exp_vids/spell_11_9_d.webm","exp_vids/spell_11_10_w.webm","exp_vids/spell_11_11_i.webm","exp_vids/spell_11_12_n.webm",
            "exp_vids/spell_11_13_d.webm","exp_vids/spell_11_14_f.webm","exp_vids/spell_11_15_i.webm","exp_vids/spell_11_16_n.webm","exp_vids/spell_11_17_d.webm","exp_vids/spell_11_18_m.webm",
            "exp_vids/spell_11_19_o.webm","exp_vids/spell_11_20_s.webm","exp_vids/spell_11_21_t.webm","exp_vids/spell_11_22.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_1);

        vv = findViewById(R.id.vv);
        layout_keyboard = findViewById(R.id.layout_keyboard);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        layout_keyboard.setVisibility(View.VISIBLE);
        layout_bottom_nav.setVisibility(View.GONE);
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
                image_anim(Spell_11_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });
        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Spell_11_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                   checkfileexists();
                } else {
                    Toast.makeText(Spell_11_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Spell_11_Activity.this, iv_playpause);
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
                image_anim(Spell_11_Activity.this, iv_next);
                position += 1;
                if (position <= strArray.length - 1)  {
                    stopvid();
                   checkfileexists();
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Spell_11_Activity.this, Quiz1Activity.class);
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
                image_anim(Spell_11_Activity.this, iv_home);
                Intent i1 = new Intent(Spell_11_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        imgbtn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Spell_11_Activity.this,imgbtn_prev);
                    if (position > 0) {
//                    stopvid();
                        position -= 1;
                       checkfileexists();
                    } else {
                        Toast.makeText(Spell_11_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        imgbtn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Spell_11_Activity.this,imgbtn_home);
                    stopvid();
                    finish();
                }
            }
        });

        btn_key_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_a);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_b);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_c);
                    if (position == 0 || position == 4) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_d);
                    if (position == 3 || position == 8 || position == 12
                            || position == 16) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_e);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_f);
                    if (position == 13) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_g);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_h);
                    if (position == 5) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_i);
                    if (position == 6 || position == 10 || position == 14) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_j);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_k);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_l);
                    if (position == 2 || position == 7) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_m);
                    if (position == 17) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_n);
                    if (position == 11 || position == 15) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_o);
                    if (position == 1||position==18) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_p);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_q);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_r);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_s);
                    if (position == 19) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_t);
                    if (position == 20) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                            if (position == 21) {
                                if (!vv.isPlaying()) {
                                    //finish();
                                }
                            }
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_u);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_v);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_w);
                    if (position == 9) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_x);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_y);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Spell_11_Activity.this, btn_key_z);
                    if (position == 1000) {
                        position += 1;
                        if (position <= strArray.length - 1)  {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Spell_11_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();

                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Spell_11_Activity.this, R.raw.tryagain);
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
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                image_anim_last(Spell_11_Activity.this,iv_next);
                iv_playpause.setImageResource(R.drawable.ic_play);
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
                    tv_lastpage_text.setText("You Completed Spelling Lesson 11");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Spell_11_Activity","YES", Spell_11_Activity.this);
                            Intent intent = new Intent(Spell_11_Activity.this, Writing_Chapt14_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }
    public void checkfileexists() {

            if (StaticValues.GetPreferences("Last_Open_position", Spell_11_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Spell_11_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Spell_11_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Spell_11_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Spell_11_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Spell_11_Activity", Spell_11_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Spelling", Spell_11_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Spell_11_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Spell_11_Activity", Spell_11_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Spelling", Spell_11_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Spell_11_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Spell_11_Activity", Spell_11_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Spelling", Spell_11_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Spell_11_Activity.this);
    }
}