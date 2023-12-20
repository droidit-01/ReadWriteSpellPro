package com.RogersCenter.readwritespell.spellings;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.RogersCenter.readwritespell.ChaptersActivity;
import com.RogersCenter.readwritespell.HomeActivity;
import com.RogersCenter.readwritespell.R;
import com.RogersCenter.readwritespell.StaticValues;

import java.util.ArrayList;
import java.util.List;

import static com.RogersCenter.readwritespell.Animations.image_anim;

public class Spelling_lessons_Activity extends AppCompatActivity {

    TextView title, subtitle;
    RecyclerView rv_lessons;
    ImageView iv_back, iv_help, iv_home, img_help;
    private Lessons_Adapter adapter;
    private PopupWindow mPopupWindow;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelling_lessons);

        iv_back = findViewById(R.id.iv_l1_back);
        iv_help = findViewById(R.id.iv_l1_help);
        iv_home = findViewById(R.id.iv_l1_home);
        img_help = findViewById(R.id.img_help);

        title = findViewById(R.id.tv_spell_lsn_title);
        subtitle = findViewById(R.id.tv_spell_lsn_sub_title);
        rv_lessons = findViewById(R.id.rv_spell_lsns);

        adapter = new Lessons_Adapter();
        rv_lessons.setAdapter(adapter);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Spelling_lessons_Activity.this, iv_back);
                finish();
            }
        });

        iv_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_help.setVisibility(View.VISIBLE);
            }
        });
        img_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_help.setVisibility(View.GONE);
            }
        });

        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Spelling_lessons_Activity.this, iv_home);
                Intent i1 = new Intent(Spelling_lessons_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        if (StaticValues.GetPreferences("FLOW", Spelling_lessons_Activity.this).equalsIgnoreCase("RECOMMENDED")) {
            String LASTOPENCLASS = StaticValues.GetPreferences("Last_Open_Lesson", Spelling_lessons_Activity.this);
            String LASTOPENParent = StaticValues.GetPreferences("Last_Open_ParentLesson", Spelling_lessons_Activity.this);
            if (LASTOPENCLASS != null && LASTOPENParent != null) {
                StaticValues.GOTOLastOpenedCLASS(Spelling_lessons_Activity.this, "" + LASTOPENCLASS);
            }
        }
    }

    public class Lessons_Adapter extends RecyclerView.Adapter<Lessons_Adapter.MyViewHolder> {
        List<String> Query_details = new ArrayList<>();
        Context context;

        public Lessons_Adapter() {
//            this.context = context;
//            this.Query_details = Query_details;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_chaptrers, viewGroup, false);
            MyViewHolder holder = new MyViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {

            int pos = position + 1;
            myViewHolder.tv_unitchapter.setText("" + pos);
            //colors
            if (pos == 1) {
                if (StaticValues.GetPreferences("Spell_1_Activity", Spelling_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Spell_1_Activity", Spelling_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Spelling_lessons_Activity.this.getColor(R.color.home_card_bg1t));
                        myViewHolder.tv_unitchapter.setTextColor(Spelling_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 2) {
                if (StaticValues.GetPreferences("Spell_2_Activity", Spelling_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Spell_2_Activity", Spelling_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Spelling_lessons_Activity.this.getColor(R.color.home_card_bg2t));
                        myViewHolder.tv_unitchapter.setTextColor(Spelling_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 3) {
                if (StaticValues.GetPreferences("Spell_3_Activity", Spelling_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Spell_3_Activity", Spelling_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Spelling_lessons_Activity.this.getColor(R.color.home_card_bg3t));
                        myViewHolder.tv_unitchapter.setTextColor(Spelling_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 4) {
                if (StaticValues.GetPreferences("Spell_4_Activity", Spelling_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Spell_4_Activity", Spelling_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Spelling_lessons_Activity.this.getColor(R.color.home_card_bg4t));
                        myViewHolder.tv_unitchapter.setTextColor(Spelling_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 5) {
                if (StaticValues.GetPreferences("Spell_5_Activity", Spelling_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Spell_5_Activity", Spelling_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Spelling_lessons_Activity.this.getColor(R.color.home_card_bg5t));
                        myViewHolder.tv_unitchapter.setTextColor(Spelling_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 6) {
                if (StaticValues.GetPreferences("Spell_6_Activity", Spelling_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Spell_6_Activity", Spelling_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Spelling_lessons_Activity.this.getColor(R.color.home_card_bg5t));
                        myViewHolder.tv_unitchapter.setTextColor(Spelling_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 7) {
                if (StaticValues.GetPreferences("Spell_7_Activity", Spelling_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Spell_7_Activity", Spelling_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Spelling_lessons_Activity.this.getColor(R.color.home_card_bg4t));
                        myViewHolder.tv_unitchapter.setTextColor(Spelling_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 8) {
                if (StaticValues.GetPreferences("Spell_8_Activity", Spelling_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Spell_8_Activity", Spelling_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Spelling_lessons_Activity.this.getColor(R.color.home_card_bg2t));
                        myViewHolder.tv_unitchapter.setTextColor(Spelling_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 9) {
                if (StaticValues.GetPreferences("Spell_9_Activity", Spelling_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Spell_9_Activity", Spelling_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Spelling_lessons_Activity.this.getColor(R.color.home_card_bg3t));
                        myViewHolder.tv_unitchapter.setTextColor(Spelling_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 10) {
                if (StaticValues.GetPreferences("Spell_10_Activity", Spelling_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Spell_10_Activity", Spelling_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Spelling_lessons_Activity.this.getColor(R.color.home_card_bg1t));
                        myViewHolder.tv_unitchapter.setTextColor(Spelling_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 11) {
                if (StaticValues.GetPreferences("Spell_11_Activity", Spelling_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Spell_11_Activity", Spelling_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Spelling_lessons_Activity.this.getColor(R.color.home_card_bg1t));
                        myViewHolder.tv_unitchapter.setTextColor(Spelling_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 12) {
                if (StaticValues.GetPreferences("Spell_12_Activity", Spelling_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Spell_12_Activity", Spelling_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Spelling_lessons_Activity.this.getColor(R.color.home_card_bg2t));
                        myViewHolder.tv_unitchapter.setTextColor(Spelling_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 13) {
                if (StaticValues.GetPreferences("Spell_13_Activity", Spelling_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Spell_13_Activity", Spelling_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Spelling_lessons_Activity.this.getColor(R.color.home_card_bg3t));
                        myViewHolder.tv_unitchapter.setTextColor(Spelling_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }

            myViewHolder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StaticValues.RemovePreferences("Last_Open_position", Spelling_lessons_Activity.this);
                    StaticValues.RemovePreferences("Last_Open_Lesson", Spelling_lessons_Activity.this);
                    StaticValues.RemovePreferences("Last_Open_ParentLesson", Spelling_lessons_Activity.this);
                    if (position == 0) {
                        Intent i1 = new Intent(Spelling_lessons_Activity.this, Spell_1_Activity.class);
                        startActivity(i1);
                    } else if (position == 1) {
                        Intent i1 = new Intent(Spelling_lessons_Activity.this, Spell_2_Activity.class);
                        startActivity(i1);
                    } else if (position == 2) {
                        Intent i1 = new Intent(Spelling_lessons_Activity.this, Spell_3_Activity.class);
                        startActivity(i1);
                    } else if (position == 3) {
                        Intent i1 = new Intent(Spelling_lessons_Activity.this, Spell_4_Activity.class);
                        startActivity(i1);
                    } else if (position == 4) {
                        Intent i1 = new Intent(Spelling_lessons_Activity.this, Spell_5_Activity.class);
                        startActivity(i1);
                    } else if (position == 5) {
                        Intent i1 = new Intent(Spelling_lessons_Activity.this, Spell_6_Activity.class);
                        startActivity(i1);
                    } else if (position == 6) {
                        Intent i1 = new Intent(Spelling_lessons_Activity.this, Spell_7_Activity.class);
                        startActivity(i1);
                    } else if (position == 7) {
                        Intent i1 = new Intent(Spelling_lessons_Activity.this, Spell_8_Activity.class);
                        startActivity(i1);
                    } else if (position == 8) {
                        Intent i1 = new Intent(Spelling_lessons_Activity.this, Spell_9_Activity.class);
                        startActivity(i1);
                    } else if (position == 9) {
                        Intent i1 = new Intent(Spelling_lessons_Activity.this, Spell_10_Activity.class);
                        startActivity(i1);
                    } else if (position == 10) {
                        Intent i1 = new Intent(Spelling_lessons_Activity.this, Spell_11_Activity.class);
                        startActivity(i1);
                    } else if (position == 11) {
                        Intent i1 = new Intent(Spelling_lessons_Activity.this, Spell_12_Activity.class);
                        startActivity(i1);
                    } else if (position == 12) {
                        Intent i1 = new Intent(Spelling_lessons_Activity.this, Spell_13_Activity.class);
                        startActivity(i1);
                    }

                }
            });
        }


        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return 13;
//            return Query_details.size();
        }

        public void setData() {

            notifyDataSetChanged();
            // where this.data is the recyclerView's dataset you are
            // setting in adapter=new Adapter(this,db.getData());
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv_unitchapter;
            ImageView iv_unitchapter_download;
            ConstraintLayout parent;
            CardView cv_unnitchapter;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                parent = itemView.findViewById(R.id.cl_unitchapter_parent);
                cv_unnitchapter = itemView.findViewById(R.id.cv_unnitchapter);
                tv_unitchapter = itemView.findViewById(R.id.tv_unitchapter);
                iv_unitchapter_download = itemView.findViewById(R.id.iv_unitchapter_download);
            }
        }
    }
}