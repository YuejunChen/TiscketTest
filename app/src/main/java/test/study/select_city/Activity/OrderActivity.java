package test.study.select_city.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.study.select_city.R;
import test.study.select_city.fragment.FragmentAdapter;
import test.study.select_city.fragment.FutOrderFragment;
import test.study.select_city.fragment.HisOrderFragment;
import test.study.select_city.utils.DataCleanManager;

public class OrderActivity extends AppCompatActivity {
    private ViewPager mPageVp;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;


    /**
     * Tab显示内容TextView
     */
    private TextView mTabHisTv, mTabFutTv;
    /**
     * Tab的那个引导线
     */
    private ImageView mTabLineIv;
    /**
     * Fragment
     */
    private FutOrderFragment mFutOrderFg;
    private HisOrderFragment mHisOrderFg;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainorder);
        findById();
        init();
        initTabLineWidth();

    }

    private void findById() {
        mTabHisTv = (TextView) this.findViewById(R.id.id_history);
        mTabFutTv = (TextView) this.findViewById(R.id.id_future);
        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line);
        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);
    }

    private void init() {
        mHisOrderFg = new HisOrderFragment();
        mFutOrderFg = new FutOrderFragment();
        mFragmentList.add(mFutOrderFg);
        mFragmentList.add(mHisOrderFg);

        mFragmentAdapter = new FragmentAdapter(
                this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

        mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                        .getLayoutParams();

                Log.e("offset:", offset + "");
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记2个页面,
                 * 从左到右分别为0,1
                 * 0->1; 1->0
                 */

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2));
                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2));
                }
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mTabHisTv.setTextColor(Color.BLUE);
                        break;
                    case 1:
                        mTabFutTv.setTextColor(Color.BLUE);
                        break;
                }
                currentIndex = position;
            }
        });

    }

    /**
     * 设置滑动条的宽度为屏幕的1/2根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 2;
        mTabLineIv.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        mTabHisTv.setTextColor(Color.BLACK);
        mTabFutTv.setTextColor(Color.BLACK);
    }
    @Override
    protected void onStop() {
        super.onStop();
        DataCleanManager dataCleanManager=new DataCleanManager();
        dataCleanManager.cleanInternalCache(OrderActivity.this);//退出活动的时候清除缓存
    }

}
