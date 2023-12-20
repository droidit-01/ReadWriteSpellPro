package com.RogersCenter.readwritespell.quiz;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.RogersCenter.readwritespell.HomeActivity;
import com.RogersCenter.readwritespell.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Cad_Game_Activity extends AppCompatActivity {

    @BindView(R.id.iv_cardgame_back)
    ImageView ivCardgameBack;
    @BindView(R.id.iv_cardgame_help)
    ImageView ivCardgameHelp;
    @BindView(R.id.iv_cardgame_home)
    ImageView ivCardgameHome;
    @BindView(R.id.cl2)
    ConstraintLayout cl2;
    @BindView(R.id.rv_card_game)
    RecyclerView rvCardGame;
    @BindView(R.id.btn_key_a)
    Button btnKeyA;
    @BindView(R.id.btn_key_b)
    Button btnKeyB;
    @BindView(R.id.btn_key_c)
    Button btnKeyC;
    @BindView(R.id.btn_key_d)
    Button btnKeyD;
    @BindView(R.id.btn_key_e)
    Button btnKeyE;
    @BindView(R.id.btn_key_f)
    Button btnKeyF;
    @BindView(R.id.btn_key_g)
    Button btnKeyG;
    @BindView(R.id.btn_key_h)
    Button btnKeyH;
    @BindView(R.id.btn_key_i)
    Button btnKeyI;
    @BindView(R.id.btn_key_j)
    Button btnKeyJ;
    @BindView(R.id.btn_key_k)
    Button btnKeyK;
    @BindView(R.id.btn_key_l)
    Button btnKeyL;
    @BindView(R.id.btn_key_m)
    Button btnKeyM;
    @BindView(R.id.btn_key_n)
    Button btnKeyN;
    @BindView(R.id.btn_key_o)
    Button btnKeyO;
    @BindView(R.id.btn_key_p)
    Button btnKeyP;
    @BindView(R.id.btn_key_q)
    Button btnKeyQ;
    @BindView(R.id.btn_key_r)
    Button btnKeyR;
    @BindView(R.id.btn_key_s)
    Button btnKeyS;
    @BindView(R.id.btn_key_t)
    Button btnKeyT;
    @BindView(R.id.btn_key_u)
    Button btnKeyU;
    @BindView(R.id.btn_key_v)
    Button btnKeyV;
    @BindView(R.id.btn_key_w)
    Button btnKeyW;
    @BindView(R.id.btn_key_x)
    Button btnKeyX;
    @BindView(R.id.btn_key_y)
    Button btnKeyY;
    @BindView(R.id.btn_key_z)
    Button btnKeyZ;
    @BindView(R.id.cl_cadgamehome)
    LinearLayout clCadgamehome;
    Button btnTag;

    List<String> TEXTS_LIST = new ArrayList<>();
    List<Button> BUTTON_LIST = new ArrayList<>();
    TextCard_Game_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_game);
        ButterKnife.bind(this);
        TEXTS_LIST.add("S");
        TEXTS_LIST.add("H");
        TEXTS_LIST.add("U");
        TEXTS_LIST.add("B");
        TEXTS_LIST.add("H");
        TEXTS_LIST.add("A");
        TEXTS_LIST.add("M");

        setview();

//        adapter = new TextCard_Game_Adapter(TEXTS_LIST);
//        rvCardGame.setAdapter(adapter);
    }

    public void setview()
    {
        for (int i = 0; i < TEXTS_LIST.size(); i++) {
            btnTag = new Button(this);
            btnTag.setBackground(getResources().getDrawable(R.drawable.cardbg));
            btnTag.setPadding(0,16,0,16);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            btnTag.setLayoutParams(params);
            btnTag.setId(i);
            btnTag.setTextColor(getResources().getColor(R.color.textcolor));
            btnTag.setTextSize(18);
            btnTag.setTag(""+ TEXTS_LIST.get(i));
            clCadgamehome.addView(btnTag);
            BUTTON_LIST.add(btnTag);
//            ((Button) findViewById(i)).setOnClickListener(this);
        }
    }

    @OnClick({R.id.btn_key_a, R.id.btn_key_b, R.id.btn_key_c, R.id.btn_key_d, R.id.btn_key_e, R.id.btn_key_f, R.id.btn_key_g, R.id.btn_key_h, R.id.btn_key_i, R.id.btn_key_j, R.id.btn_key_k, R.id.btn_key_l, R.id.btn_key_m, R.id.btn_key_n, R.id.btn_key_o, R.id.btn_key_p, R.id.btn_key_q, R.id.btn_key_r, R.id.btn_key_s, R.id.btn_key_t, R.id.btn_key_u, R.id.btn_key_v, R.id.btn_key_w, R.id.btn_key_x, R.id.btn_key_y, R.id.btn_key_z, R.id.iv_cardgame_back, R.id.iv_cardgame_help, R.id.iv_cardgame_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_key_a:
                keyboard_button_anim(btnKeyA);
                break;
            case R.id.btn_key_b:
                keyboard_button_anim(btnKeyB);
                break;
            case R.id.btn_key_c:
                keyboard_button_anim(btnKeyC);
                break;
            case R.id.btn_key_d:
                keyboard_button_anim(btnKeyD);
                break;
            case R.id.btn_key_e:
                keyboard_button_anim(btnKeyE);
                break;
            case R.id.btn_key_f:
                keyboard_button_anim(btnKeyF);
                break;
            case R.id.btn_key_g:
                keyboard_button_anim(btnKeyG);
                break;
            case R.id.btn_key_h:
                keyboard_button_anim(btnKeyH);
                break;
            case R.id.btn_key_i:
                keyboard_button_anim(btnKeyI);
                break;
            case R.id.btn_key_j:
                keyboard_button_anim(btnKeyJ);
                break;
            case R.id.btn_key_k:
                keyboard_button_anim(btnKeyK);
                break;
            case R.id.btn_key_l:
                keyboard_button_anim(btnKeyL);
                break;
            case R.id.btn_key_m:
                keyboard_button_anim(btnKeyM);
                break;
            case R.id.btn_key_n:
                keyboard_button_anim(btnKeyN);
                break;
            case R.id.btn_key_o:
                keyboard_button_anim(btnKeyO);
                break;
            case R.id.btn_key_p:
                keyboard_button_anim(btnKeyP);
                break;
            case R.id.btn_key_q:
                keyboard_button_anim(btnKeyQ);
                break;
            case R.id.btn_key_r:
                keyboard_button_anim(btnKeyR);
                break;
            case R.id.btn_key_s:
                keyboard_button_anim(btnKeyS);
                break;
            case R.id.btn_key_t:
                keyboard_button_anim(btnKeyT);
                break;
            case R.id.btn_key_u:
                keyboard_button_anim(btnKeyU);
                break;
            case R.id.btn_key_v:
                keyboard_button_anim(btnKeyV);
                break;
            case R.id.btn_key_w:
                keyboard_button_anim(btnKeyW);
                break;
            case R.id.btn_key_x:
                keyboard_button_anim(btnKeyX);
                break;
            case R.id.btn_key_y:
                keyboard_button_anim(btnKeyY);
                break;
            case R.id.btn_key_z:
                keyboard_button_anim(btnKeyZ);
                break;

            case R.id.iv_cardgame_back:
                image_anim(ivCardgameBack);
                finish();
                break;
            case R.id.iv_cardgame_help:
                image_anim(ivCardgameHelp);
                break;
            case R.id.iv_cardgame_home:
                image_anim(ivCardgameHome);
                Intent i2 = new Intent(Cad_Game_Activity.this, HomeActivity.class);
                startActivity(i2);
                finish();
                break;
        }
    }

    public void keyboard_button_anim(Button btn) {
        btn.startAnimation(AnimationUtils.loadAnimation(Cad_Game_Activity.this, R.anim.image_click));

        for (int i = 0; i < BUTTON_LIST.size(); i++) {
            if (BUTTON_LIST.get(i).getText().toString().isEmpty())
            {
                if(btn.getText().toString().equalsIgnoreCase(BUTTON_LIST.get(i).getTag().toString()))
                {
                    MediaPlayer mPlayer2 = MediaPlayer.create(Cad_Game_Activity.this, R.raw.correct);
                    mPlayer2.start();
                    BUTTON_LIST.get(i).setText(""+btn.getText().toString());
                    break;
                }
                else {
                    MediaPlayer mPlayer2 = MediaPlayer.create(Cad_Game_Activity.this, R.raw.tryagain);
                    mPlayer2.start();
                    break;
                }

            }
        }

            if (!BUTTON_LIST.get(BUTTON_LIST.size()-1).getText().toString().isEmpty())
            {
                //load new word here
                Toast.makeText(Cad_Game_Activity.this,"Start new game or go to home",Toast.LENGTH_SHORT).show();
                clCadgamehome.removeAllViews();
                BUTTON_LIST.clear();
                TEXTS_LIST.clear();
                TEXTS_LIST.add("H");
                TEXTS_LIST.add("E");
                TEXTS_LIST.add("Y");
                setview();
            }
    }

    public void image_anim(ImageView img) {
        img.startAnimation(AnimationUtils.loadAnimation(Cad_Game_Activity.this, R.anim.image_click));
    }

    public class TextCard_Game_Adapter extends RecyclerView.Adapter<TextCard_Game_Adapter.MyViewHolder> {
        List<String> Query_details = new ArrayList<>();
        Context context;

        public TextCard_Game_Adapter(List<String> Query_details) {
            this.context = context;
            this.Query_details = Query_details;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_card_game, viewGroup, false);
            MyViewHolder holder = new MyViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {

//            Glide.with(Claim_Detail_Activity.this)
//                    .load("" + Query_details.get(position).getPhotoName())
//                    .placeholder(R.drawable.dummyimg)
//                    .into(myViewHolder.iv_claim_photo);

//            myViewHolder.tv_unitcardgame.setText("" + Query_details.get(position));
            myViewHolder.tv_unitcardgame.setText("");
        }


        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
//            return 9;
            return Query_details.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv_unitcardgame;
            ConstraintLayout parent;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                parent = itemView.findViewById(R.id.cl_unitcardgame_parent);
                tv_unitcardgame = itemView.findViewById(R.id.tv_unitcardgame);

            }
        }
    }
}