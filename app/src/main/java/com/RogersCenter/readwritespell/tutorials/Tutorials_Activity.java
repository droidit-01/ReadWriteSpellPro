package com.RogersCenter.readwritespell.tutorials;

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
import com.RogersCenter.readwritespell.letters.Letter_MGT_Activity;
import com.RogersCenter.readwritespell.letters.Letter_lessons_Activity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;

public class Tutorials_Activity extends AppCompatActivity {
    VideoView vv;
    View layout_bottom_nav;
    List<Uri> listURI = new ArrayList<>();
    List<String> sound_list = new ArrayList<>();
    int position;
    ImageView iv_back, iv_previous, iv_playpause, iv_next, iv_home;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    TextView tv_prev,tv_next;
    String[] strArray = {"exp_vids/introduction_of_english.webm","exp_vids/history_of_english.webm","exp_vids/vowels_consonants.webm"};
    private ProgressDialog progressDoalog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials);

        position = getIntent().getIntExtra("position",0);

        vv = findViewById(R.id.vv);
        layout_bottom_nav = findViewById(R.id.layout_bottom_nav);
        iv_back = findViewById(R.id.iv_l2_back);
        iv_previous = findViewById(R.id.iv_l2_previous);
        iv_playpause = findViewById(R.id.iv_l2_playpause);
        iv_next = findViewById(R.id.iv_l2_next);
        iv_home = findViewById(R.id.iv_l2_home);
        tv_prev = findViewById(R.id.textView9);
        tv_next = findViewById(R.id.textView8);

        tv_prev.setVisibility(View.GONE);
        tv_next.setVisibility(View.GONE);
        iv_next.setVisibility(View.GONE);
        iv_previous.setVisibility(View.GONE);

        if (position==0)
        {
            Uri uri = CustomAPEZProvider.buildUri("" + strArray[0]);
            listURI.add(uri);
//            listURI.add( Uri.fromFile(new File(strArray[0])));
        }else if (position==1)
        {
            Uri uri = CustomAPEZProvider.buildUri("" + strArray[1]);
            listURI.add(uri);
//            listURI.add( Uri.fromFile(new File(strArray[1])));
        }else if (position==2)
        {
            Uri uri = CustomAPEZProvider.buildUri("" + strArray[2]);
            listURI.add(uri);
//            listURI.add(Uri.fromFile(new File(strArray[2])));
        }

        if (!listURI.isEmpty())
        {
            checkfileexists();
        }
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Tutorials_Activity.this,iv_back);
                stopvid();
                finish();
            }
        });
        if(position==2)
        {
            iv_home.setImageDrawable(ContextCompat.getDrawable(Tutorials_Activity.this, R.drawable.ic_right_arrow));
        }

        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Tutorials_Activity.this,iv_previous);
                if (position > 0) {
//                    stopvid();
                    position -= 1;
                   checkfileexists();
                } else {
                    Toast.makeText(Tutorials_Activity.this, "This is first video", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Tutorials_Activity.this,iv_playpause);
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
                image_anim(Tutorials_Activity.this,iv_next);
                position += 1;
                if (position <= listURI.size() - 1) {
                    if(position>=3)
                    {
                        iv_next.setClickable(false);
                        iv_next.setEnabled(false);
                    }else {
                        iv_next.setClickable(true);
                        iv_next.setEnabled(true);
                        stopvid();
                       checkfileexists();
                    }

                } else {
                    position -= 1;
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(Tutorials_Activity.this, "This is last video", Toast.LENGTH_SHORT).show();
//                            stopvid();
//                            Intent i = new Intent(Tutorials_Activity.this, Quiz1Activity.class);
//                            startActivity(i);
                            finish();
                        }
                    });

                }
            }
        });

        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==2)
                {
                    image_anim(Tutorials_Activity.this,iv_home);
                    Intent i1 = new Intent(Tutorials_Activity.this, Letter_lessons_Activity.class);
                    startActivity(i1);
                    finish();
                }else{
                    image_anim(Tutorials_Activity.this,iv_home);
                    Intent i1 = new Intent(Tutorials_Activity.this, HomeActivity.class);
                    startActivity(i1);
                    finish();
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
                    StaticValues.SavePreferences("Tutorials_Activity","YES", Tutorials_Activity.this);
                   Intent i = new Intent(Tutorials_Activity.this, Letter_lessons_Activity.class);
                   startActivity(i);
                   finish();
                }
            });
        }
    }
   
    public void checkfileexists() {

            StaticValues.RemovePreferences("Last_Open_position", Tutorials_Activity.this);
            StaticValues.RemovePreferences("Last_Open_Lesson", Tutorials_Activity.this);
            StaticValues.RemovePreferences("Last_Open_ParentLesson", Tutorials_Activity.this);

        initializePlayer(0, listURI,sound_list);
    }
    public void Check_INTERNET(int pos, String childname) {
        try {
            if (StaticValues.checkConnection(Tutorials_Activity.this)) {
                progressDoalog1 = new ProgressDialog(Tutorials_Activity.this, R.style.MyDialog);
//                progressDoalog1.setMessage("Loading..");
                progressDoalog1.setCanceledOnTouchOutside(false);
                progressDoalog1.show();
                listURI.add(position, Uri.parse(StaticValues.BaseURL_TUTORIALS + childname));
                initializePlayer(pos, listURI,sound_list);

                downloadfiles(Tutorials_Activity.this,
                        "" + childname,
                        ".webm",
                        "Tutorials",
                        StaticValues.BaseURL_TUTORIALS + childname);
            } else {
                final AlertDialog alertDialog;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Tutorials_Activity.this);
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
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(Tutorials_Activity.this, R.color.colorAccent));
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(Tutorials_Activity.this, R.color.colorAccent));
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
        StaticValues.SavePreferences("Last_Open_Lesson", "Tutorials_Activity", Tutorials_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Tutorials", Tutorials_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Tutorials_Activity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticValues.SavePreferences("Last_Open_Lesson", "Tutorials_Activity", Tutorials_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Tutorials", Tutorials_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Tutorials_Activity.this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        StaticValues.SavePreferences("Last_Open_Lesson", "Tutorials_Activity", Tutorials_Activity.this);
        StaticValues.SavePreferences("Last_Open_ParentLesson", "Tutorials", Tutorials_Activity.this);
        StaticValues.SavePreferences("Last_Open_position", "" + position, Tutorials_Activity.this);
    }
}