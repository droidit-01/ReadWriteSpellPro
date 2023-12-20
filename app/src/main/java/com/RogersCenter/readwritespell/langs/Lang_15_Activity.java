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
import com.RogersCenter.readwritespell.spellings.Spell_13_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;
import static com.RogersCenter.readwritespell.Animations.image_anim_last;

public class Lang_15_Activity extends AppCompatActivity {

    VideoView vv;
    View layoutvideoviev, layout_bottom_nav;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position = 0;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    String[] strArray = {"exp_vids/lang_15_1.webm", "exp_vids/lang_15_2.webm", "exp_vids/lang_15_3.webm", "exp_vids/lang_15_4.webm", "exp_vids/lang_15_5.webm", "exp_vids/lang_15_6.webm",
            "exp_vids/lang_15_7.webm", "exp_vids/lang_15_8.webm", "exp_vids/lang_15_9.webm", "exp_vids/lang_15_10.webm", "exp_vids/lang_15_11.webm", "exp_vids/lang_15_12.webm","exp_vids/lang_15_13.webm",
            "exp_vids/lang_15_14.webm","exp_vids/lang_15_15.webm","exp_vids/lang_15_16.webm","exp_vids/lang_15_17.webm","exp_vids/lang_15_18.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_15);

        vv = findViewById(R.id.vv);
        layoutvideoviev = findViewById(R.id.layoutvideoviev);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);

        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);

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
                image_anim(Lang_15_Activity.this,iv_back);
                stopvid();
                finish();
            }
        });

        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_15_Activity.this,iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                   checkfileexists();
                } else {
                    Toast.makeText(Lang_15_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Lang_15_Activity.this,iv_playpause);
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
                image_anim(Lang_15_Activity.this,iv_next);
                position += 1;
                if (position <= strArray.length - 1) {
                    stopvid();
                   checkfileexists();
                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Lang_15_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Lang_15_Activity.this, Quiz1Activity.class);
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
                image_anim(Lang_15_Activity.this,iv_home);
                Intent i1 = new Intent(Lang_15_Activity.this, HomeActivity.class);
                startActivity(i1);
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

        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                image_anim_last(Lang_15_Activity.this,iv_next);
                iv_playpause.setImageResource(R.drawable.ic_play);
            }
        });
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
                    tv_lastpage_text.setText("You Completed Language Lesson 15");
                    ib_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            StaticValues.SavePreferences("Lang_15_Activity","YES", Lang_15_Activity.this);
                            Intent intent = new Intent(Lang_15_Activity.this, Dict_11_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }
    }
    public void checkfileexists() {
            if (StaticValues.GetPreferences("Last_Open_position", Lang_15_Activity.this) != null) {
                position = Integer.parseInt(StaticValues.GetPreferences("Last_Open_position", Lang_15_Activity.this));
                Log.d("LASTPOS", "" + position);
                StaticValues.RemovePreferences("Last_Open_position", Lang_15_Activity.this);
                StaticValues.RemovePreferences("Last_Open_Lesson", Lang_15_Activity.this);
                StaticValues.RemovePreferences("Last_Open_ParentLesson", Lang_15_Activity.this);
            }
            initializePlayer(position, listURI, sound_list);

    }
    public void Check_INTERNET(int pos, String childname) {
        try {
            if (StaticValues.checkConnection(Lang_15_Activity.this)) {
                progressDoalog1 = new ProgressDialog(Lang_15_Activity.this, R.style.MyDialog);
//                progressDoalog1.setMessage("Loading..");
                progressDoalog1.setCanceledOnTouchOutside(false);
                progressDoalog1.show();


                listURI.add(position, Uri.parse(StaticValues.BaseURL_LANGS + childname));
                initializePlayer(pos, listURI, sound_list);

                downloadfiles(Lang_15_Activity.this,
                        "" + childname,
                        ".webm",
                        "Langs",
                        StaticValues.BaseURL_LANGS + childname);
            } else {
                final AlertDialog alertDialog;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Lang_15_Activity.this);
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
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(Lang_15_Activity.this, R.color.colorAccent));
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(Lang_15_Activity.this, R.color.colorAccent));
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_15_Activity", Lang_15_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_15_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_15_Activity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_15_Activity", Lang_15_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_15_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_15_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Lang_15_Activity", Lang_15_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Langs", Lang_15_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Lang_15_Activity.this);
    }
}