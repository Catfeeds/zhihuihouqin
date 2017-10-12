package com.moe.wl.ui.main.activity.RoomReservation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.MeetingRoomAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomReservationActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rv_room_item)
    RecyclerView rvRoomItem;
    @BindView(R.id.activity_room_reservation)
    LinearLayout activityRoomReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_reservation);
        ButterKnife.bind(this);
        initTitle();
        initRecycler();
    }

    private void initRecycler() {
        rvRoomItem.setLayoutManager(new LinearLayoutManager(this));
        MeetingRoomAdapter roomAdapter=new MeetingRoomAdapter(this);
        rvRoomItem.setAdapter(roomAdapter);
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("会议室预定");
    }

    @OnClick(R.id.iv_search)
    public void onViewClicked() {
    }
}
