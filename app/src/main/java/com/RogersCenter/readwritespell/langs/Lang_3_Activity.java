package com.RogersCenter.readwritespell.langs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
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

import com.RogersCenter.readwritespell.CustomAPEZProvider;
import com.RogersCenter.readwritespell.HomeActivity;
import com.RogersCenter.readwritespell.R;
import com.RogersCenter.readwritespell.StaticValues;
import com.RogersCenter.readwritespell.reading.Reading_Chapt7_Activity;
import com.RogersCenter.readwritespell.reading.Reading_Chapt8_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;

public class Lang_3_Activity extends AppCompatActivity
{

    VideoView vv;
    View layout_keyboard, layout_bottom_nav,clickec_view;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0,count1=0,count2=0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;

    ConstraintLayout cl_vowel,cl_firstslbs,cl_secondslbs;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    View v_n1,v_a,v_p,v_k,v_i,v_n2;
    String[] strArray = {"exp_vids/lang_3_1.webm", "exp_vids/lang_3_2.webm", "exp_vids/lang_3_3.webm", "exp_vids/lang_3_4.webm", "exp_vids/lang_3_5.webm", "exp_vids/lang_3_6.webm",
            "exp_vids/lang_3_7.webm", "exp_vids/lang_3_8.webm", "exp_vids/lang_3_9.webm", "exp_vids/lang_3_10.webm", "exp_vids/lang_3_11.webm", "exp_vids/lang_3_12.webm", "exp_vids/lang_3_13.webm",
            "exp_vids/lang_3_14.webm", "exp_vids/lang_3_15.webm", "exp_vids/lang_3_16.webm"};
    TextView tv_vowel1,tv_vowel2,tv_vowel3,tv_vowel4,tv_vowel5,tv_vowel6;
    private Dialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_3);

        vv = findViewById(R.id.vv);
        layout_keyboard = findViewById(R.id.layout_keyboard);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        clickec_view = findViewById(R.id.clickec_view);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);

        cl_firstslbs = findViewById(R.id.cl_firstslbs);
        cl_secondslbs = findViewById(R.id.cl_secondslbs);
        v_n1 = findViewById(R.id.v_n1);
        v_a = findViewById(R.id.v_a);
        v_p = findViewById(R.id.v_p);
        v_k = findViewById(R.id.v_k);
        v_i = findViewById(R.id.v_i);
        v_n2 = findViewById(R.id.v_n2);

        cl_vowel = findViewById(R.id.cl_vowel);
        v_a = findViewById(R.id.v_a);
        tv_vowel1 = findViewById(R.id.tv_vowel1);
        tv_vowel2 = findViewById(R.id.tv_vowel2);
        tv_vowel3 = findViewById(R.id.tv_vowel3);
        tv_vowel4 = findViewById(R.id.tv_vowel4);
        tv_vowel5 = findViewById(R.id.tv_vowel5);
        tv_vowel6 = findViewById(R.id.tv_vowel6);

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
                image_anim(Lang_3_Activity.this,iv_back);
                stopvid();
                finish();
            }
        });

        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_3_Activity.this,iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                   checkfileexists();
                } else {
                    Toast.makeText(Lang_3_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_3_Activity.this,iv_playpause);
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
                image_anim(Lang_3_Activity.this,iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                   checkfileexists();
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Lang_3_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_3_Activity.this, Quiz1Activity.class);
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
                image_anim(Lang_3_Activity.this,iv_home);
                Intent i1 = new Intent(Lang_3_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        tv_vowel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==3)
                {
                    count2++;
                    tv_vowel1.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                else {
                    tv_vowel1.setTextColor(getResources().getColor(R.color.textcolorlessons));
                    tryagainsound();
                }
            }
        });
        tv_vowel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==2)
                {
                    count1++;
                    tv_vowel2.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                else {
                    tv_vowel2.setTextColor(getResources().getColor(R.color.textcolorlessons));
                    tryagainsound();
                }
            }
        });
        tv_vowel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==3)
                {
                    count2++;
                    tv_vowel3.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                else {
                    tv_vowel3.setTextColor(getResources().getColor(R.color.textcolorlessons));
                    tryagainsound();
                }
            }
        });
        tv_vowel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==3)
                {
                    count2++;
                    tv_vowel4.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                else {
                    tv_vowel4.setTextColor(getResources().getColor(R.color.textcolorlessons));
                    tryagainsound();
                }
            }
        });
        tv_vowel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==2)
                {
                    count1++;
                    tv_vowel5.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                else {
                    tv_vowel5.setTextColor(getResources().getColor(R.color.textcolorlessons));
                    tryagainsound();
                }
            }
        });
        tv_vowel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==3)
                {
                    count2++;
                    tv_vowel6.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                else {
                    tv_vowel6.setTextColor(getResources().getColor(R.color.textcolorlessons));
                    tryagainsound();
                }
            }
        });

        clickec_view.setOnClickListener(new View.OnClickListener() {
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
                            Toast.makeText(Lang_3_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_3_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
//                            finish();
                        }
                    });

                }
            }
        });
//        if(count1>=2||count2==4)
//        {
//            iv_next.setEnabled(true);
//            iv_next.setClickable(true);
//        }
        vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==13)
                {
                    position += 1;
                    if (position <= strArray.length - 1) {
                        stopvid();
                       checkfileexists();
                    } else {
                        position -= 1;
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(Lang_3_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_3_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                                //finish();
                            }
                        });

                    }
                }
            }
        });
        cl_firstslbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==8)
                {
                    position += 1;
                    if (position <= strArray.length - 1) {
                        stopvid();
                       checkfileexists();
                    } else {
                        position -= 1;
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(Lang_3_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_3_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                                //finish();
                            }
                        });

                    }
                }
            }
        });
        cl_secondslbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==12)
                {
                    position += 1;
                    if (position <= strArray.length - 1) {
                        stopvid();
                       checkfileexists();
                    } else {
                        position -= 1;
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(Lang_3_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_3_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                                //finish();
                            }
                        });

                    }
                }
            }
        });
        v_n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==5)
                {
                    position += 1;
                    if (position <= strArray.length - 1) {
                        stopvid();
                       checkfileexists();
                    } else {
                        position -= 1;
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(Lang_3_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_3_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                                //finish();
                            }
                        });

                    }
                }
                else{
                    tryagainsound();
                }
            }
        });
        v_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==6)
                {
                    position += 1;
                    if (position <= strArray.length - 1) {
                        stopvid();
                       checkfileexists();
                    } else {
                        position -= 1;
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(Lang_3_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_3_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                                //finish();
                            }
                        });

                    }
                }
                else{
                    tryagainsound();
                }
            }
        });
        v_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==7)
                {
                    position += 1;
                    if (position <= strArray.length - 1) {
                        stopvid();
                       checkfileexists();
                    } else {
                        position -= 1;
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(Lang_3_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_3_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                                //finish();
                            }
                        });

                    }
                }
                else{
                    tryagainsound();
                }
            }
        });
        v_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==9)
                {
                    position += 1;
                    if (position <= strArray.length - 1) {
                        stopvid();
                       checkfileexists();
                    } else {
                        position -= 1;
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(Lang_3_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_3_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                                //finish();
                            }
                        });

                    }
                }
                else{
                    tryagainsound();
                }
            }
        });
        v_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==10)
                {
                    position += 1;
                    if (position <= strArray.length - 1) {
                        stopvid();
                       checkfileexists();
                    } else {
                        position -= 1;
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(Lang_3_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_3_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                                //finish();
                            }
                        });

                    }
                }
                else{
                    tryagainsound();
                }
            }
        });
        v_n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==11)
                {
                    position += 1;
                    if (position <= strArray.length - 1) {
                        stopvid();
                       checkfileexists();
                    } else {
                        position -= 1;
                        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(Lang_3_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_3_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                                //finish();
                            }
                        });

                    }
                }
                else{
                    tryagainsound();
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

        if(position==2||position==3)
        {
//            iv_next.setEnabled(false);
//            iv_next.setClickable(false);
            cl_vowel.setVisibility(View.VISIBLE);

        }
        else if(position==4)
        {
            iv_next.setEnabled(false);
            iv_next.setClickable(false);
            clickec_view.setVisibility(View.VISIBLE);
            cl_vowel.setVisibility(View.GONE);

        }
        else if(position>=5&&position<=13)
        {
            iv_next.setEnabled(false);
            iv_next.setClickable(false);
            clickec_view.setVisibility(View.GONE);
            cl_firstslbs.setVisibility(View.VISIBLE);
            cl_secondslbs.setVisibility(View.VISIBLE);

        }else {
            iv_next.setEnabled(true);
            iv_next.setClickable(true);
            clickec_view.setVisibility(View.GONE);
            cl_vowel.setVisibility(View.GONE);
            cl_firstslbs.setVisibility(View.GONE);
            cl_secondslbs.setVisibility(View.GONE);
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    image_anim_last(Lang_3_Activity.this,iv_next);
                    iv_playpause.setImageResource(R.drawable.ic_play);
                }
            });
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
                    tv_lastpage_text.setText("You Completed Language Lesson 3");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Lang_3_Activity","YES", Lang_3_Activity.this);
                            Intent intent = new Intent(Lang_3_Activity.this, Reading_Chapt8_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }
    public void checkfileexists() {

            if (StaticValues.GetPreferences("Last_Open_position", Lang_3_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Lang_3_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Lang_3_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Lang_3_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Lang_3_Activity.this);
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_3_Activity", Lang_3_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_3_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_3_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_3_Activity", Lang_3_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_3_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_3_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_3_Activity", Lang_3_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_3_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_3_Activity.this);
    }
    
    public void tryagainsound()
    {
        MediaPlayer mPlayer2 = MediaPlayer.create(Lang_3_Activity.this, R.raw.tryagain);
        mPlayer2.start();
    }
}