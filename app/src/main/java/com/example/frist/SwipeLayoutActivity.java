package com.example.frist;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;
import com.example.frist.bean.OrderItem;
import com.example.frist.bean.Student;
import com.example.frist.bean.User;
import com.example.frist.greendao.gen.StudentDao;
import com.example.frist.util.GreenDaoManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SwipeLayoutActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    TextView textView;
    FloatingActionButton button;
    private RecyclerView recyclerView;
    ArrayList<Student>students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        button=(FloatingActionButton)findViewById(R.id.button);
        recyclerView=(RecyclerView)findViewById(R.id.id_recyclerview);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.coll_toolbar);
        ImageView imageView = (ImageView) findViewById(R.id.imageview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
      //  textView = (TextView) findViewById(R.id.textview);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.common_google_signin_btn_icon_dark));
        //toolbar.setLogo(getResources().getDrawable(R.drawable.common_google_signin_btn_icon_dark));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwipeLayoutActivity.this.finish();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        collapsingToolbarLayout.setTitle("水果");
      //  textView.setText(getStrings("nima"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SwipeLayoutActivity.this,SecondActivity.class);
                SwipeLayoutActivity.this.startActivity(intent);
            }
        });
        Query query=GreenDaoManager.getInstance().getSession().getStudentDao().queryBuilder().
                where(StudentDao.Properties.Year.between(15,19), StudentDao.Properties.StuId.eq(1)).build();
        students=(ArrayList<Student>) query.list();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // recyclerView.setItemAnimator(new FadeInLeftAnimator());

        recyclerView.setAdapter(new RecyclerViewAdapter(this,students));
    }

    public class QuickAdapter extends BaseQuickAdapter<Student> {
        public QuickAdapter() {
            super(R.layout.swipe_item, students);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, Student item) {
            viewHolder.setText(R.id.tv_desc, item.getName());

            SwipeLayout swipeLayout=viewHolder.getView(R.id.sample1);
            swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            swipeLayout.addDrag(SwipeLayout.DragEdge.Left,swipeLayout.findViewById(R.id.bottom_wrapper));
        }
    }
    public class RecyclerViewAdapter extends RecyclerSwipeAdapter<RecyclerViewAdapter.SimpleViewHolder> {

        public class SimpleViewHolder extends RecyclerView.ViewHolder {
            SwipeLayout swipeLayout;
            TextView delect;
            TextView textViewData;
            Button buttonDelete;
            ImageView image;
            public SimpleViewHolder(View itemView) {
                super(itemView);
                swipeLayout = (SwipeLayout) itemView.findViewById(R.id.sample1);
                delect = (TextView) itemView.findViewById(R.id.delect);
                textViewData = (TextView) itemView.findViewById(R.id.tv_desc);
               // buttonDelete = (Button) itemView.findViewById(R.id.delete);
                image=(ImageView) itemView.findViewById(R.id.leftimage) ;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(), "onItemSelected: " + textViewData.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        private Context mContext;
        private ArrayList<Student> mDataset;

        protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

        public RecyclerViewAdapter(Context context, ArrayList<Student> objects) {
            this.mContext = context;
            this.mDataset = objects;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_item, parent, false);
            return new SimpleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
            String item = mDataset.get(position).getName();

            viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
            viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
                @Override
                public void onOpen(SwipeLayout layout) {
                  //  YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
                }
            });
            viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
                @Override
                public void onDoubleClick(SwipeLayout layout, boolean surface) {
                    Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
                }
            });
            viewHolder.delect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GreenDaoManager.getInstance().getSession().getStudentDao().delete(mDataset.get(position));
                    mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                    mDataset.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mDataset.size());
                    mItemManger.closeAllItems();

                }
            });
            viewHolder.textViewData.setText(item);
            Glide.with(mContext).load(R.drawable.app).crossFade().into(viewHolder.image);
            mItemManger.bindView(viewHolder.itemView,position);
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        @Override
        public int getSwipeLayoutResourceId(int position) {
            return R.id.sample1;
        }
    }
}
