package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.DoctorDetailrvAdapter;

public class SpAllCommentActivity extends AppCompatActivity {

    @BindView(R.id.all_sp_comment_title)
    TitleBar allSpCommentTitle;
    @BindView(R.id.sp_comment_content)
    XRecyclerView recycler;
    // TODO: 2017/8/30 0030 假数据
   private List<String> num= Arrays.asList("123","34","34","45");
    private List<String> names = Arrays.asList("全部评价", "好评", "中评", "差评");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_all_comment);
        ButterKnife.bind(this);
        initTitle();
        initView();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        View view = View.inflate(this, R.layout.sp_comment, null);
        LayoutInflater inflater = LayoutInflater.from(this);
        /*LinearLayout container= (LinearLayout) view.findViewById(R.id.ll_sp_comment_container);
        for (int i = 0; i < names.size(); i++) {
            View view1 = View.inflate(this, R.layout.sp_comment_item, null);
            TextView tvCommentName= (TextView) view1.findViewById(R.id.tv_comment_name);
            TextView tvCommentNum= (TextView) view1.findViewById(R.id.tv_comment_num);
            tvCommentName.setText(names.get(i));
            tvCommentNum.setText(num.get(i));
            container.addView(view1);
        }*/
        TextView name1= (TextView) view.findViewById(R.id.tv_name1);
        TextView name2= (TextView) view.findViewById(R.id.tv_name2);
        TextView name3= (TextView) view.findViewById(R.id.tv_name3);
        TextView name4= (TextView) view.findViewById(R.id.tv_name4);
        TextView num1= (TextView) view.findViewById(R.id.tv_num1);
        TextView num2= (TextView) view.findViewById(R.id.tv_num2);
        TextView unm3= (TextView) view.findViewById(R.id.tv_num3);
        TextView num4= (TextView) view.findViewById(R.id.tv_num4);

        recycler.addHeaderView(view);
        //一下为评论列表
        DoctorDetailrvAdapter detailrvAdapter = new DoctorDetailrvAdapter(this);
        recycler.setAdapter(detailrvAdapter);
    }

    private void initTitle() {
        allSpCommentTitle.setBack(true);
        allSpCommentTitle.setTitle("全部评论");
    }
}
