package com.moe.wl.ui.mywidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.moe.wl.framework.application.SoftApplication;

import java.util.ArrayList;
import java.util.List;

import mvp.cn.util.DensityUtil;

public class FlowLayout extends ViewGroup{
/*
	//存储所有子View
	private List<List<View>> mAllChildViews = new ArrayList<>();
	//每一行的高度
	private List<Integer> mLineHeight = new ArrayList<>();

	public FlowLayout(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	public FlowLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}
	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub

		//父控件传进来的宽度和高度以及对应的测量模式
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

		//如果当前ViewGroup的宽高为wrap_content的情况
		int width = 0;//自己测量的 宽度
		int height = 0;//自己测量的高度
		//记录每一行的宽度和高度
		int lineWidth = 0;
		int lineHeight = 0;

		//获取子view的个数
		int childCount = getChildCount();
		for(int i = 0;i < childCount; i ++){
			View child = getChildAt(i);
			//测量子View的宽和高
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			//得到LayoutParams
			MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
			//子View占据的宽度
			int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
			//子View占据的高度
			int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
			//换行时候
			if(lineWidth + childWidth > sizeWidth){
				//对比得到最大的宽度
				width = Math.max(width, lineWidth);
				//重置lineWidth
				lineWidth = childWidth;
				//记录行高
				height += lineHeight;
				lineHeight = childHeight;
			}else{//不换行情况
				//叠加行宽
				lineWidth += childWidth;
				//得到最大行高
				lineHeight = Math.max(lineHeight, childHeight);
			}
			//处理最后一个子View的情况
			if(i == childCount -1){
				width = Math.max(width, lineWidth);
				height += lineHeight;
			}
		}
		//wrap_content
		setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width,
				modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		mAllChildViews.clear();
		mLineHeight.clear();
		//获取当前ViewGroup的宽度
		int width = getWidth();

		int lineWidth = 0;
		int lineHeight = 0;
		//记录当前行的view
		List<View> lineViews = new ArrayList<View>();
		int childCount = getChildCount();
		for(int i = 0;i < childCount; i ++){
			View child = getChildAt(i);
			MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
			int childWidth = child.getMeasuredWidth();
			int childHeight = child.getMeasuredHeight();

			//如果需要换行
			if(childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width){
				//记录LineHeight
				mLineHeight.add(lineHeight);
				//记录当前行的Views
				mAllChildViews.add(lineViews);
				//重置行的宽高
				lineWidth = 0;
				lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
				//重置view的集合
				lineViews = new ArrayList();
			}
			lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
			lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
			lineViews.add(child);
		}
		//处理最后一行
		mLineHeight.add(lineHeight);
		mAllChildViews.add(lineViews);

		//设置子View的位置
		int left = 0;
		int top = 0;
		//获取行数
		int lineCount = mAllChildViews.size();
		for(int i = 0; i < lineCount; i ++){
			//当前行的views和高度
			lineViews = mAllChildViews.get(i);
			lineHeight = mLineHeight.get(i);
			for(int j = 0; j < lineViews.size(); j ++){
				View child = lineViews.get(j);
				//判断是否显示
				if(child.getVisibility() == View.GONE){
					continue;
				}
				MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
				int cLeft = left + lp.leftMargin;
				int cTop = top + lp.topMargin;
				int cRight = cLeft + child.getMeasuredWidth();
				int cBottom = cTop + child.getMeasuredHeight();
				//进行子View进行布局
				child.layout(cLeft, cTop, cRight, cBottom);
				left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
			}
			left = 0;
			top += lineHeight;
		}

	}
	*//**
	 * 与当前ViewGroup对应的LayoutParams
	 *//*
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		// TODO Auto-generated method stub

		return new MarginLayoutParams(getContext(), attrs);
	}



*//**/
	public static final int verticalSpacing = DensityUtil.dip2px(SoftApplication.softApplication,15);
	public static final int horizontalSpacing = DensityUtil.dip2px(SoftApplication.softApplication,10);
	public List<Line> lineList = new ArrayList<Line>();
	
	public FlowLayout(Context context) {
		this(context,null);
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		this(context,attrs,-1);
	}

	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int left = getPaddingLeft();
		int top = getPaddingTop();
		
		for (int i = 0; i < lineList.size(); i++) {
			Line line = lineList.get(i);
			line.layout(left, top);
			top += line.lineHeight+verticalSpacing;
		}
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//创建集合添加行对象
		lineList.clear();
		//创建行对象
		Line line = new Line();
		//获取宽度大小
		int width = MeasureSpec.getSize(widthMeasureSpec);
		//获取行的可用宽度
		int usedWidth = width-getPaddingLeft()-getPaddingRight();
		
		for (int i = 0; i < getChildCount(); i++) {
			View childView = getChildAt(i);
			//系统测量一次
			childView.measure(0, 0);
			//测量到的孩子节点的宽度
			int measuredWidth = childView.getMeasuredWidth();
			
			if(line.getLineViewCount()==0){
				line.addLineView(childView);
			}else if(measuredWidth+line.lineWidth+horizontalSpacing>usedWidth){
				//如果添加当前控件后,宽度大于可用宽度,则需要换行
				lineList.add(line);
				//创建新行
				line = new Line();
				line.addLineView(childView);
			}else{
				line.addLineView(childView);
			}
			//在结合中添加行对象
			if(i == getChildCount()-1){
				lineList.add(line);
			}
		}
		
		int height = getPaddingBottom()+getPaddingTop();
		for(int i=0;i<lineList.size();i++){
			height += lineList.get(i).lineHeight;
		}
		
		height += (lineList.size()-1)*verticalSpacing;
		
		widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	public class Line{
		//定义行宽度
		public int lineWidth;
		//定义行高度
		public int lineHeight;
		//放置view的集合
		public List<View> lineView = new ArrayList<View>();
		
		public int getLineViewCount(){
			return lineView.size();
		}
		
		public void addLineView(View view){
			lineView.add(view);
			view.measure(0, 0);
			
			lineHeight = Math.max(lineHeight, view.getMeasuredHeight());
			
			if(lineView.size() == 0){
				//节点是0个的时候,宽度就是当前控件的宽度
				lineWidth = view.getMeasuredWidth();
			}else{
				//节点大于0个的时候,宽度需要添加间距
				lineWidth += view.getMeasuredWidth()+horizontalSpacing;
			}
		}
		
		public void layout(int left,int top){
			int childWidth;
			//获取剩余宽度
			int totalSurplusWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - lineWidth;
			//剩余宽度平均分配给行对象中每一个view
			int surplusWidth = totalSurplusWidth/getLineViewCount();
			for (int i = 0; i < lineView.size(); i++) {
				//获取行中的每一个对象,给其分配宽度大小
				View view = lineView.get(i);
				//测量
				view.measure(0, 0);
				//添加分配后的宽度
				if(lineView.size()==1){
					childWidth=view.getMeasuredWidth();
				}else{
					childWidth = view.getMeasuredWidth()+surplusWidth;
				}
				int width32 = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
				int height32 = MeasureSpec.makeMeasureSpec(lineHeight, MeasureSpec.EXACTLY);
				
				view.measure(width32, height32);
				
				//行中控件排列位置
				view.layout(left, top, left+childWidth, top+lineHeight);
				left += childWidth+horizontalSpacing; 
			}
		}
	}
}
