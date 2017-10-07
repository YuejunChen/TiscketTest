package test.study.select_city.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import test.study.select_city.R;
import test.study.select_city.fragment.HomeFragment;
import test.study.select_city.fragment.LoginFragment;
import test.study.select_city.fragment.MeFragment;
import test.study.select_city.fragment.OrderFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    //三个tab布局
    private RelativeLayout homeLayout, orderLayout, meLayout;
    //底部标签切换的Fragment
    private Fragment homeFragment, orderFragment, meFragment,loginFragment, currentFragment;
    //底部标签图片
    private ImageView homeImg, orderImg, meImg;
    //底部标签的文本
    private TextView homeTv, orderTv, meTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initTab();
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        homeLayout = (RelativeLayout) findViewById(R.id.rl_home);
        orderLayout = (RelativeLayout) findViewById(R.id.rl_order);
        meLayout = (RelativeLayout) findViewById(R.id.rl_me);
        homeLayout.setOnClickListener(this);
        orderLayout.setOnClickListener(this);
        meLayout.setOnClickListener(this);

        homeImg = (ImageView) findViewById(R.id.iv_home);
        orderImg = (ImageView) findViewById(R.id.iv_order);
        meImg = (ImageView) findViewById(R.id.iv_me);
        homeTv = (TextView) findViewById(R.id.tv_home);
        orderTv = (TextView) findViewById(R.id.tv_order);
        meTv = (TextView) findViewById(R.id.tv_me);

    }

    /**
     * 初始化底部标签
     */
    private void initTab() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }

        if (!homeFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_layout, homeFragment).commit();

            // 记录当前Fragment
            currentFragment = homeFragment;
            // 设置图片文本的变化
            homeImg.setImageResource(R.drawable.btn_home_pre);
            homeTv.setTextColor(getResources()
                    .getColor(R.color.bottomtab_press));
            orderImg.setImageResource(R.drawable.btn_order_nor);
            orderTv.setTextColor(getResources().getColor(
                    R.color.bottomtab_normal));
            meImg.setImageResource(R.drawable.btn_my_nor);
            meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_home: // 知道
                clickTab1Layout();
                break;
            case R.id.rl_order: // 我想知道
                clickTab2Layout();
                break;
            case R.id.rl_me: // 我的
                clickTab3Layout();
                break;
            default:
                break;
        }
    }


    /**
     * 点击第一个tab
     */
    private void clickTab1Layout() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), homeFragment);

        // 设置底部tab变化
        homeImg.setImageResource(R.drawable.btn_home_pre);
        homeTv.setTextColor(getResources().getColor(R.color.bottomtab_press));
        orderImg.setImageResource(R.drawable.btn_order_nor);
        orderTv.setTextColor(getResources().getColor(
                R.color.bottomtab_normal));
        meImg.setImageResource(R.drawable.btn_my_nor);
        meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
    }

    /**
     * 点击第二个tab
     */
    private void clickTab2Layout() {
        if (orderFragment == null) {
            orderFragment = new OrderFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), orderFragment);

        homeImg.setImageResource(R.drawable.btn_home_nor);
        homeTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
        orderImg.setImageResource(R.drawable.btn_order_pre);
        orderTv.setTextColor(getResources().getColor(
                R.color.bottomtab_press));
        meImg.setImageResource(R.drawable.btn_my_nor);
        meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));

    }

    /**
     * 点击第三个tab
     */
    private void clickTab3Layout() {
        SharedPreferences pref=getSharedPreferences("userdata",MODE_PRIVATE);
        boolean userState=pref.getBoolean("userstate",false);
        if(userState==false){
            if (loginFragment == null) {
                loginFragment = new LoginFragment();
            }
            addOrShowFragment(getSupportFragmentManager().beginTransaction(), loginFragment);
        }
        else {
            if (meFragment == null) {
                meFragment = new MeFragment();
            }
            addOrShowFragment(getSupportFragmentManager().beginTransaction(), meFragment);
        }
        homeImg.setImageResource(R.drawable.btn_home_nor);
        homeTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
        orderImg.setImageResource(R.drawable.btn_order_nor);
        orderTv.setTextColor(getResources().getColor(
                R.color.bottomtab_normal));
        meImg.setImageResource(R.drawable.btn_my_pre);
        meTv.setTextColor(getResources().getColor(R.color.bottomtab_press));

    }

    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    public void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }

        currentFragment = fragment;
    }
}