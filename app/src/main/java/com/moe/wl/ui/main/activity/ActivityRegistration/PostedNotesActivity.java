package com.moe.wl.ui.main.activity.ActivityRegistration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

public class PostedNotesActivity extends AppCompatActivity {

    @BindView(R.id.activity_title)
    TitleBar titleBar;
    @BindView(R.id.tv_posted_notes)
    TextView tvPostedNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted_notes);
        ButterKnife.bind(this);
        intiTitle();
    }

    private void intiTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("发布活动说明");
    }
}
