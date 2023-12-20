package com.RogersCenter.readwritespell.letters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class Letter_lessons_Activity extends AppCompatActivity {

    ConstraintLayout cl_letters_parent;
    TextView title, subtitle;
    RecyclerView rv_lessons;
    ImageView iv_back, iv_help, iv_home, img_help;
    ProgressBar mProgressBar;
    private ProgressDialog pDialog;
    private Lessons_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_lessons);

        cl_letters_parent = findViewById(R.id.cl_letters_parent);
        iv_back = findViewById(R.id.iv_l1_back);
        iv_help = findViewById(R.id.iv_l1_help);
        iv_home = findViewById(R.id.iv_l1_home);
        img_help = findViewById(R.id.img_help);
        title = findViewById(R.id.tv_ltr_lsn_title);
        subtitle = findViewById(R.id.tv_ltr_lsn_sub_title);
        rv_lessons = findViewById(R.id.rv_ltr_lsns);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_letters);

        adapter = new Lessons_Adapter();
        rv_lessons.setAdapter(adapter);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_anim(Letter_lessons_Activity.this, iv_back);
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
                image_anim(Letter_lessons_Activity.this, iv_home);
                Intent i1 = new Intent(Letter_lessons_Activity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        if (StaticValues.GetPreferences("FLOW", Letter_lessons_Activity.this).equalsIgnoreCase("RECOMMENDED")) {
            String LASTOPENCLASS = StaticValues.GetPreferences("Last_Open_Lesson", Letter_lessons_Activity.this);
            String LASTOPENParent = StaticValues.GetPreferences("Last_Open_ParentLesson", Letter_lessons_Activity.this);
            if (LASTOPENCLASS != null && LASTOPENParent != null) {
                StaticValues.GOTOLastOpenedCLASS(Letter_lessons_Activity.this, "" + LASTOPENCLASS);
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
                if (StaticValues.GetPreferences("Letter_BCA_Activity", Letter_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Letter_BCA_Activity", Letter_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Letter_lessons_Activity.this.getColor(R.color.home_card_bg1t));
                        myViewHolder.tv_unitchapter.setTextColor(Letter_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 2) {
                if (StaticValues.GetPreferences("Letter_DF_Activity", Letter_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Letter_DF_Activity", Letter_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Letter_lessons_Activity.this.getColor(R.color.home_card_bg2t));
                        myViewHolder.tv_unitchapter.setTextColor(Letter_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 3) {
                if (StaticValues.GetPreferences("Letter_MGT_Activity", Letter_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Letter_MGT_Activity", Letter_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Letter_lessons_Activity.this.getColor(R.color.home_card_bg3t));
                        myViewHolder.tv_unitchapter.setTextColor(Letter_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 4) {
                if (StaticValues.GetPreferences("Letter_HE_Activity", Letter_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Letter_HE_Activity", Letter_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Letter_lessons_Activity.this.getColor(R.color.home_card_bg4t));
                        myViewHolder.tv_unitchapter.setTextColor(Letter_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 5) {
                if (StaticValues.GetPreferences("Letter_JKLI_Activity", Letter_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Letter_JKLI_Activity", Letter_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Letter_lessons_Activity.this.getColor(R.color.home_card_bg5t));
                        myViewHolder.tv_unitchapter.setTextColor(Letter_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 6) {
                if (StaticValues.GetPreferences("Letter_PNO_Activity", Letter_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Letter_PNO_Activity", Letter_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Letter_lessons_Activity.this.getColor(R.color.home_card_bg5t));
                        myViewHolder.tv_unitchapter.setTextColor(Letter_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 7) {
                if (StaticValues.GetPreferences("Letter_RSU_Activity", Letter_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Letter_RSU_Activity", Letter_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Letter_lessons_Activity.this.getColor(R.color.home_card_bg4t));
                        myViewHolder.tv_unitchapter.setTextColor(Letter_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 8) {
                if (StaticValues.GetPreferences("Letter_QVW_Activity", Letter_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Letter_QVW_Activity", Letter_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Letter_lessons_Activity.this.getColor(R.color.home_card_bg2t));
                        myViewHolder.tv_unitchapter.setTextColor(Letter_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            if (pos == 9) {
                if (StaticValues.GetPreferences("Letter_XYZ_Activity", Letter_lessons_Activity.this) != null) {
                    if (StaticValues.GetPreferences("Letter_XYZ_Activity", Letter_lessons_Activity.this).equalsIgnoreCase("YES")) {
                        myViewHolder.cv_unnitchapter.setCardBackgroundColor(Letter_lessons_Activity.this.getColor(R.color.home_card_bg3t));
                        myViewHolder.tv_unitchapter.setTextColor(Letter_lessons_Activity.this.getColor(R.color.textcolor));
                    }
                }
            }
            myViewHolder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StaticValues.RemovePreferences("Last_Open_position", Letter_lessons_Activity.this);
                    StaticValues.RemovePreferences("Last_Open_Lesson", Letter_lessons_Activity.this);
                    StaticValues.RemovePreferences("Last_Open_ParentLesson", Letter_lessons_Activity.this);
                    if (position == 0) {
                        Intent i1 = new Intent(Letter_lessons_Activity.this, Letter_BCA_Activity.class);
                        startActivity(i1);
                    } else if (position == 1) {
                        Intent i1 = new Intent(Letter_lessons_Activity.this, Letter_DF_Activity.class);
                        startActivity(i1);
                    } else if (position == 2) {
                        Intent i1 = new Intent(Letter_lessons_Activity.this, Letter_MGT_Activity.class);
                        startActivity(i1);
                    } else if (position == 3) {
                        Intent i1 = new Intent(Letter_lessons_Activity.this, Letter_HE_Activity.class);
                        startActivity(i1);
                    } else if (position == 4) {
                        Intent i1 = new Intent(Letter_lessons_Activity.this, Letter_JKLI_Activity.class);
                        startActivity(i1);
                    } else if (position == 5) {
                        Intent i1 = new Intent(Letter_lessons_Activity.this, Letter_PNO_Activity.class);
                        startActivity(i1);
                    } else if (position == 6) {
                        Intent i1 = new Intent(Letter_lessons_Activity.this, Letter_RSU_Activity.class);
                        startActivity(i1);
                    } else if (position == 7) {
                        Intent i1 = new Intent(Letter_lessons_Activity.this, Lettet_QVW_Activity.class);
                        startActivity(i1);
                    } else if (position == 8) {
                        Intent i1 = new Intent(Letter_lessons_Activity.this, Letter_XYZ_Activity.class);
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
            return 9;
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