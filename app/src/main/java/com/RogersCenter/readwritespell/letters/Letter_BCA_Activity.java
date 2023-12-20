package com.RogersCenter.readwritespell.letters;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
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

import com.RogersCenter.readwritespell.CustomAPEZProvider;
import com.RogersCenter.readwritespell.HomeActivity;
import com.RogersCenter.readwritespell.R;
import com.RogersCenter.readwritespell.StaticValues;
import com.RogersCenter.readwritespell.quiz.Quiz1Activity;
import com.android.vending.expansion.zipfile.ZipResourceFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;
import static com.RogersCenter.readwritespell.Animations.keyboard_button_anim;
import static com.RogersCenter.readwritespell.Animations.keyboard_imgbutton_anim;

public class Letter_BCA_Activity extends AppCompatActivity {
    VideoView vv;
    View layout_keyboard, layout_bottom_nav;
    ImageButton imgbtn_prev, imgbtn_home;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    Button btn_key_a, btn_key_b, btn_key_c, btn_key_d, btn_key_e, btn_key_f, btn_key_g, btn_key_h, btn_key_i, btn_key_j, btn_key_k, btn_key_l, btn_key_m;
    Button btn_key_n, btn_key_o, btn_key_p, btn_key_q, btn_key_r, btn_key_s, btn_key_t, btn_key_u, btn_key_v, btn_key_w, btn_key_x, btn_key_y, btn_key_z;
    int count = 1;
    ZipResourceFile expansionFile1;
    String[] strArray = {"exp_vids/ltr_b_1.webm", "exp_vids/ltr_b_2.webm", "exp_vids/ltr_b_3.webm", "exp_vids/ltr_b_4.webm", "exp_vids/ltr_b_5.webm", "exp_vids/ltr_b_6.webm", "exp_vids/ltr_b_7.webm",
            "exp_vids/ltr_b_8.webm", "exp_vids/ltr_c_1.webm", "exp_vids/ltr_c_2.webm", "exp_vids/ltr_c_3.webm", "exp_vids/ltr_c_4.webm", "exp_vids/ltr_c_5.webm", "exp_vids/ltr_c_6.webm", "exp_vids/ltr_c_7.webm",
            "exp_vids/ltr_a_1.webm", "exp_vids/ltr_a_2.webm", "exp_vids/ltr_a_3.webm", "exp_vids/ltr_a_4.webm", "exp_vids/ltr_a_5.webm", "exp_vids/ltr_a_6.webm", "exp_vids/ltr_a_7.webm",
            "exp_vids/ltr_bca_1.webm", "exp_vids/ltr_bca_2.webm", "exp_vids/ltr_bca_3.webm", "exp_vids/ltr_bca_4.webm", "exp_vids/ltr_bca_5.webm", "exp_vids/ltr_bca_6.webm", "exp_vids/ltr_bca_7.webm"};
    private ProgressDialog progressDoalog1;

    private static String convertInputStreamToString(InputStream inputStream)
            throws IOException {

        final char[] buffer = new char[8192];
        final StringBuilder result = new StringBuilder();

        // InputStream -> Reader
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            int charsRead;
            while ((charsRead = reader.read(buffer, 0, buffer.length)) > 0) {
                result.append(buffer, 0, charsRead);
            }
        }

        return result.toString();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_b_c_a);

        vv = findViewById(R.id.vv);
        layout_keyboard = findViewById(R.id.layout_keyboard);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);
        imgbtn_prev = findViewById(R.id.imgbtn_prev);
        imgbtn_home = findViewById(R.id.imgbtn_home);

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
        if (!listURI.isEmpty()) {
            checkfileexists();
        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Letter_BCA_Activity.this, iv_back);
                stopvid();
                finish();
            }
        });
        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Letter_BCA_Activity.this, iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                    checkfileexists();
                } else {
                    Toast.makeText(Letter_BCA_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Letter_BCA_Activity.this, iv_playpause);
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
                image_anim(Letter_BCA_Activity.this, iv_next);
                position += 1;

                if (position <= strArray.length - 1) {
                    stopvid();
                    checkfileexists();
//                    initializePlayer(position, listURI, sound_list);
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Letter_BCA_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Letter_BCA_Activity.this, iv_home);
                Intent i1 = new Intent(Letter_BCA_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        imgbtn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Letter_BCA_Activity.this, imgbtn_prev);
                    if (position > 0) {
//                    stopvid();
                        position -= 1;
                        initializePlayer(position, listURI, sound_list);
                    } else {
                        Toast.makeText(Letter_BCA_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        imgbtn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_imgbutton_anim(Letter_BCA_Activity.this, imgbtn_home);
                    stopvid();
                    finish();
                }
            }
        });

        btn_key_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_a);
                    if (position == 22 || position == 25) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
//                            initializePlayer(position, listURI, sound_list);
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_BCA_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_BCA_Activity.this, Quiz1Activity.class);
                            startActivity(i);
//                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_b);
                    if (position == 23 || position == 26) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
//                            initializePlayer(position, listURI, sound_list);
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_BCA_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_BCA_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_c);
                    if (position == 24) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
//                            initializePlayer(position, listURI, sound_list);
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_BCA_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                            stopvid();
                            Intent i = new Intent(Letter_BCA_Activity.this, Quiz1Activity.class);
                            startActivity(i);
                            finish();
                        }
                    } else if (position == 27) {
                        position += 1;
                        if (position <= strArray.length - 1) {
//                        stopvid();
                            checkfileexists();
//                            initializePlayer(position, listURI, sound_list);
                        } else {
                            position -= 1;
                            Toast.makeText(Letter_BCA_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                        mPlayer2.start();
                    }
                }
            }
        });
        btn_key_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_d);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_e);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_f);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_g);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_h);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_i);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_j);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_k);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_l);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_m);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_n);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_o);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_p);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_q);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_r);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_s);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_t);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_u);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_v);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_w);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_x);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_y);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
        btn_key_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    keyboard_button_anim(Letter_BCA_Activity.this, btn_key_z);
                    MediaPlayer mPlayer2 = MediaPlayer.create(Letter_BCA_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                }
            }
        });
    }

    private void initializePlayer(int pos, List<Uri> urilist, List<String> mysound_list) {
//        vv.setVideoPath("http://techslides.com/demos/sample-videos/small.webm");
        vv.setVideoURI(urilist.get(position));
//        vv.setVideoURI(urilist.get(pos));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vv);
        vv.setMediaController(mediaController);
        vv.requestFocus();
        vv.start();
        vv.setMediaController(null);

        if (position >= 22) {
            layout_keyboard.setVisibility(View.VISIBLE);
            layout_bottom_nav.setVisibility(View.GONE);
        } else {
            layout_bottom_nav.setVisibility(View.VISIBLE);
            layout_keyboard.setVisibility(View.GONE);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Letter_BCA_Activity.this, iv_next);

                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
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
                    tv_lastpage_text.setText("You Completed Letter Lesson 1");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Letter_BCA_Activity", "YES", Letter_BCA_Activity.this);
                            Intent intent = new Intent(Letter_BCA_Activity.this, Letter_DF_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }

    public void checkfileexists() {
        if (StaticValues.GetPreferences("Last_Open_position", Letter_BCA_Activity.this) != null) {
            position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Letter_BCA_Activity.this));
            Log.d("LASTPOS", "" + position);
            StaticValues.RemovePreferences("Last_Open_position", Letter_BCA_Activity.this);
            StaticValues.RemovePreferences("Last_Open_Lesson", Letter_BCA_Activity.this);
            StaticValues.RemovePreferences("Last_Open_ParentLesson", Letter_BCA_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Letter_BCA_Activity", Letter_BCA_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Letters", Letter_BCA_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Letter_BCA_Activity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Letter_BCA_Activity", Letter_BCA_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Letters", Letter_BCA_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Letter_BCA_Activity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Letter_BCA_Activity", Letter_BCA_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Letters", Letter_BCA_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Letter_BCA_Activity.this);
    }
}