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
import com.RogersCenter.readwritespell.dictionary.Dict_1_Activity;
import com.RogersCenter.readwritespell.quiz.Quiz1Activity;
import com.RogersCenter.readwritespell.spellings.Spell_6_Activity;
import com.RogersCenter.readwritespell.spellings.Spell_7_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;

public class Lang_6_Activity extends AppCompatActivity {
    VideoView vv;
    View layout_keyboard, layout_bottom_nav, view_signed;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0, count1 = 0, count2 = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    ConstraintLayout cl_nomad, cl_no, cl_mad, cl_memo, cl_mem, cl_o, cl_protest, cl_pro, cl_test;
    View v_n, v_o1, v_m1, v_a, v_d, vm2, v_e, v_m3, v_o2, v_pr, v_o3, v_t, v_e2, v_st;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    String[] strArray = {"exp_vids/lang_6_1.webm", "exp_vids/lang_6_2.webm", "exp_vids/lang_6_3.webm", "exp_vids/lang_6_4.webm", "exp_vids/lang_6_5.webm", "exp_vids/lang_6_6.webm",
            "exp_vids/lang_6_7.webm", "exp_vids/lang_6_8.webm", "exp_vids/lang_6_9.webm", "exp_vids/lang_6_10.webm", "exp_vids/lang_6_11.webm", "exp_vids/lang_6_12.webm","exp_vids/lang_6_13.webm",
            "exp_vids/lang_6_14.webm","exp_vids/lang_6_15.webm","exp_vids/lang_6_16.webm","exp_vids/lang_6_17.webm","exp_vids/lang_6_18.webm","exp_vids/lang_6_19.webm","exp_vids/lang_6_20.webm",
            "exp_vids/lang_6_21.webm","exp_vids/lang_6_22.webm","exp_vids/lang_6_23.webm","exp_vids/lang_6_24.webm","exp_vids/lang_6_25.webm","exp_vids/lang_6_26.webm","exp_vids/lang_6_27.webm",
            "exp_vids/lang_6_28.webm","exp_vids/lang_6_29.webm","exp_vids/lang_6_30.webm","exp_vids/lang_6_31.webm","exp_vids/lang_6_32.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_6);

        vv = findViewById(R.id.vv);
        layout_keyboard = findViewById(R.id.layout_keyboard);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);

        cl_nomad = findViewById(R.id.cl_nomad);
        cl_no = findViewById(R.id.cl_no);
        cl_mad = findViewById(R.id.cl_mad);
        v_n = findViewById(R.id.v_n);
        v_o1 = findViewById(R.id.v_o1);
        v_m1 = findViewById(R.id.v_m1);
        v_a = findViewById(R.id.v_a);
        v_d = findViewById(R.id.v_d);

        cl_memo = findViewById(R.id.cl_memo);
        cl_mem = findViewById(R.id.cl_mem);
        vm2 = findViewById(R.id.vm2);
        v_e = findViewById(R.id.v_e);
        v_m3 = findViewById(R.id.v_m3);
        v_o2 = findViewById(R.id.v_o2);
        cl_o = findViewById(R.id.cl_o);

        cl_protest = findViewById(R.id.cl_protest);
        cl_pro = findViewById(R.id.cl_pro);
        v_pr = findViewById(R.id.v_pr);
        v_o3 = findViewById(R.id.v_o3);
        cl_test = findViewById(R.id.cl_test);
        v_t = findViewById(R.id.v_t);
        v_e2 = findViewById(R.id.v_e2);
        v_st = findViewById(R.id.v_st);


        position = 0;
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
                image_anim(Lang_6_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });
        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_6_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                   checkfileexists();
                } else {
                    Toast.makeText(Lang_6_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_6_Activity.this, iv_playpause);
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
                image_anim(Lang_6_Activity.this, iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                   checkfileexists();
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
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
                image_anim(Lang_6_Activity.this, iv_home);
                Intent i1 = new Intent(Lang_6_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        //word NOMAD
        cl_nomad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 3 || position == 12) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        v_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 5) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        v_o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 6) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        cl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 7) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        v_m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 8) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        v_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 9) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        v_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 10) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        cl_mad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 11) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });

        //word MEMO
        cl_memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 20) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        cl_mem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 18) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        vm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 15) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        v_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 16) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        v_m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 17) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        v_o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 19) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });

        //word PROTEST
        cl_protest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 30) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        cl_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 25) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        cl_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 29) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        v_pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 23) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        v_o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 24) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        v_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 26) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        v_e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 27) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        v_st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    if (position == 28) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                           checkfileexists();
                        } else {
                            position -= 1;
                            Toast.makeText(Lang_6_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Lang_6_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            //finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
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
        if (position >=3 && position <= 12) {
            cl_nomad.setVisibility(View.VISIBLE);
            cl_nomad.setElevation(2);
            cl_memo.setVisibility(View.GONE);
            cl_protest.setVisibility(View.GONE);
            iv_next.setEnabled(false);
            iv_next.setClickable(false);

            if (position == 3 || position == 12) {
                cl_no.setVisibility(View.INVISIBLE);
                cl_mad.setVisibility(View.INVISIBLE);
            }
            else if (position == 4) {
                iv_next.setEnabled(true);
                iv_next.setClickable(true);
            }
            else if (position == 5 || position == 6 || position == 7) {
                iv_next.setEnabled(false);
                iv_next.setClickable(false);
                cl_no.setVisibility(View.VISIBLE);
                cl_mad.setVisibility(View.INVISIBLE);
            } else if (position == 8 || position == 9 || position == 10 || position == 11) {
                cl_no.setVisibility(View.INVISIBLE);
                cl_mad.setVisibility(View.VISIBLE);
            }

        } else if (position >= 15 && position <= 20) {
            cl_nomad.setVisibility(View.GONE);
            cl_memo.setVisibility(View.VISIBLE);
            cl_memo.setElevation(2);
            cl_protest.setVisibility(View.GONE);
            iv_next.setEnabled(false);
            iv_next.setClickable(false);

            if (position == 20) {
                cl_no.setVisibility(View.INVISIBLE);
                cl_mad.setVisibility(View.INVISIBLE);
            } else if (position == 15 || position == 16 || position == 17 || position == 18) {
                cl_mem.setVisibility(View.VISIBLE);
                cl_o.setVisibility(View.INVISIBLE);
            } else if (position == 19) {
                cl_mem.setVisibility(View.INVISIBLE);
                cl_o.setVisibility(View.VISIBLE);
            }
        } else if (position >= 23 && position <= 30) {
            cl_nomad.setVisibility(View.GONE);
            cl_memo.setVisibility(View.GONE);
            cl_protest.setVisibility(View.VISIBLE);
            cl_protest.setElevation(2);
            iv_next.setEnabled(false);
            iv_next.setClickable(false);

            if (position == 30) {
                cl_pro.setVisibility(View.INVISIBLE);
                cl_test.setVisibility(View.INVISIBLE);
            } else if (position == 23 || position == 24 || position == 25) {
                cl_pro.setVisibility(View.VISIBLE);
                cl_test.setVisibility(View.INVISIBLE);
            } else if (position == 26 || position == 27 || position == 28 || position == 29) {
                cl_pro.setVisibility(View.INVISIBLE);
                cl_test.setVisibility(View.VISIBLE);
            }
        }
        else {
            cl_nomad.setVisibility(View.GONE);
            cl_memo.setVisibility(View.GONE);
            cl_protest.setVisibility(View.GONE);
            iv_next.setEnabled(true);
            iv_next.setClickable(true);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Lang_6_Activity.this,iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
        }
//        Log.d("POS", pos + "  " + urilist.get(pos));
        if(position==strArray.length-1)
        {
            View layout_lastpage  = findViewById(R.id.layout_lastpage);
            TextView tv_lastpage_text= findViewById(R.id.tv_lastpage_text);
            ImageButton ib_home= findViewById(R.id.ib_home);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    layout_lastpage.setVisibility(View.VISIBLE);
                    tv_lastpage_text.setText("You Completed Language Lesson 6");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (StaticValues.GetPreferences("Lang_6_Activity_Repeat",Lang_6_Activity.this)!=null)
                            {
                                StaticValues.SavePreferences("Lang_6_Activity","YES", Lang_6_Activity.this);
                                Intent intent = new Intent(Lang_6_Activity.this, Spell_7_Activity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                StaticValues.SavePreferences("Lang_6_Activity","YES", Lang_6_Activity.this);
                                Intent intent = new Intent(Lang_6_Activity.this, Spell_6_Activity.class);
                                startActivity(intent);
                                finish();
                            }

                        }
                    });
                }
            });
        }
    }
    public void checkfileexists() {

            if (StaticValues.GetPreferences("Last_Open_position", Lang_6_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Lang_6_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Lang_6_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Lang_6_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Lang_6_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_6_Activity", Lang_6_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_6_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_6_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_6_Activity", Lang_6_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_6_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_6_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_6_Activity", Lang_6_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_6_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_6_Activity.this);
    }
    public void tryagainsound() {
        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_6_Activity.this, R.raw.tryagain);
        mPlayer2.start();
    }
}