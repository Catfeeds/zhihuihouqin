package cn.lc.model.ui.main.activity.ActivityRegistration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

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
