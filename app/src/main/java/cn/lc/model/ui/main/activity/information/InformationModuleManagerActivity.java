package cn.lc.model.ui.main.activity.information;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.InformationModuleAdapter;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.InformationClazzBean;
import cn.lc.model.ui.main.model.InformationModuleModel;
import cn.lc.model.ui.main.modelimpl.InformationModuleModelImpl;
import cn.lc.model.ui.main.presenter.InformationModulePresenter;
import cn.lc.model.ui.main.view.InformationModuleView;

/**
 * 类描述：信息公告模块管理页面
 * 作者：Shixhe On 2017/9/12 0012
 */
public class InformationModuleManagerActivity extends BaseActivity<InformationModuleModel, InformationModuleView, InformationModulePresenter> implements InformationModuleView {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.gridView_my)
    NoSlidingGridView gridViewMy;
    @BindView(R.id.gridView_all)
    NoSlidingGridView gridViewAll;

    private List<InformationClazzBean.NoticeTypeListEntity> myData;
    private List<InformationClazzBean.NoticeTypeListEntity> otherData;

    private InformationModuleAdapter adapterMy, adapterOther;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_information_module_manager);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("编辑分类");
        titleBar.setTitleRight("完成");

        myData = new ArrayList<>();
        otherData = new ArrayList<>();
        adapterMy = new InformationModuleAdapter(this, myData, 1);
        gridViewMy.setAdapter(adapterMy);
        adapterOther = new InformationModuleAdapter(this, otherData, 0);
        gridViewAll.setAdapter(adapterOther);
        getPresenter().getInformationClass(1);

        titleBar.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] ids = new int[myData.size() - 1];
                for (int i = 0; i < myData.size(); i++) {
                    if (i == 0) {
                        continue;
                    }
                    ids[i - 1] = myData.get(i).getId();
                }
                getPresenter().updataInformationModule(ids);
            }
        });
        adapterMy.setOnDeleteModuleClickListener(new InformationModuleAdapter.OnDeleteModuleClick() {
            @Override
            public void onClick(int id, String content) {
                // 删除item
                for (int i = 0; i < myData.size(); i++) {
                    if (id == myData.get(i).getId()) {
                        myData.remove(i);
                        InformationClazzBean.NoticeTypeListEntity entity = new InformationClazzBean.NoticeTypeListEntity();
                        entity.setId(id);
                        entity.setName(content);
                        otherData.add(entity);
                        adapterMy.notifyDataSetChanged();
                        adapterOther.notifyDataSetChanged();
                        return;
                    }
                }
            }
        });

        adapterOther.setOnAddModuleClickListener(new InformationModuleAdapter.OnAddModuleClick() {
            @Override
            public void onClick(int id, String content) {
                // 添加item
                for (int i = 0; i < otherData.size(); i++) {
                    if (id == otherData.get(i).getId()) {
                        otherData.remove(i);
                        InformationClazzBean.NoticeTypeListEntity entity = new InformationClazzBean.NoticeTypeListEntity();
                        entity.setId(id);
                        entity.setName(content);
                        myData.add(entity);
                        adapterMy.notifyDataSetChanged();
                        adapterOther.notifyDataSetChanged();
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void getInformationClassSucc(InformationClazzBean bean) {
        if (bean.getNoticeTypeList() == null)
            return;
        myData.clear();
        InformationClazzBean.NoticeTypeListEntity entity = new InformationClazzBean.NoticeTypeListEntity();
        entity.setId(10000);
        entity.setName("推荐");
        myData.add(entity);
        myData.addAll(bean.getNoticeTypeList());
        adapterMy.notifyDataSetChanged();
        getPresenter().getAllInformationClass(0);
    }

    @Override
    public void getAllInformationClassSucc(InformationClazzBean bean) {
        if (bean.getNoticeTypeList() == null)
            return;
        otherData.clear();
        otherData.addAll(bean.getNoticeTypeList());
        List<Integer> position = new ArrayList<>();
        for (int i = 0; i < otherData.size(); i++) {
            for (int j = 0; j < myData.size(); j++) {
                if (otherData.get(i).getId() == myData.get(j).getId()) {
                    position.add(i);
                }
            }
        }
        int num = 0;
        for (int i = 0; i < position.size(); i++) {
            otherData.remove(position.get(i) - num);
            num++;
        }

        adapterOther.notifyDataSetChanged();
    }

    @Override
    public void updataInformationClassSucc(CollectBean bean) {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public InformationModuleModel createModel() {
        return new InformationModuleModelImpl();
    }

    @Override
    public InformationModulePresenter createPresenter() {
        return new InformationModulePresenter();
    }
}
