package com.example.caoyujie.bottomnavigationbardemo;

import android.support.design.internal.NavigationMenuItemView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationBar navbar;
    private RadioGroup rg_mode,rg_style;
    private BadgeItem badge;
    private static int MODE = BottomNavigationBar.MODE_DEFAULT;
    private static int STYLE = BottomNavigationBar.BACKGROUND_STYLE_DEFAULT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initNavBar();

    }

    private void initView() {
        navbar = (BottomNavigationBar) findViewById(R.id.bottom_navbar);
        rg_mode = (RadioGroup) findViewById(R.id.rg_mode);
        rg_style = (RadioGroup) findViewById(R.id.rg_style);
        rg_mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.rb_fixd:
                        MODE = BottomNavigationBar.MODE_FIXED;
                        break;
                    case R.id.rb_shifing:
                        MODE = BottomNavigationBar.MODE_SHIFTING;
                        break;
                }
            }
        });

        rg_style.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.rb_static:
                        STYLE = BottomNavigationBar.BACKGROUND_STYLE_STATIC;
                        break;
                    case R.id.rb_ripple:
                        STYLE = BottomNavigationBar.BACKGROUND_STYLE_RIPPLE;
                        break;
                }
            }
        });
    }

    /**
     * 初始化 BottomNavigationBar
     */
    private void initNavBar() {
        navbar.clearAll();
        //生成一个标记
        badge = initBadge(4,"10",true);
        //生成BottomNavigationBar中的每一个item
        BottomNavigationItem item1 = new BottomNavigationItem(R.mipmap.ic_home,"home");
        BottomNavigationItem item2 = new BottomNavigationItem(R.mipmap.ic_chat,"chat");
        BottomNavigationItem item3 = new BottomNavigationItem(R.mipmap.ic_user,"user").setBadgeItem(badge);
        //将item添加到BottomNavigationBar中
        navbar.addItem(item1);
        navbar.addItem(item2);
        navbar.addItem(item3);
        //设置BottomNavigationBar的模式
        navbar.setMode(MODE);
        //设置背景模式
        navbar.setBackgroundStyle(STYLE);
        //默认选中项
        navbar.setFirstSelectedPosition(0);
        //统一设置点击颜色
        navbar.setActiveColor(R.color.colorAccent);
        //统一设置未点击颜色
        navbar.setInActiveColor(R.color.colorWhite);
        //统一设置BottomNavigationBar的背景色
        navbar.setBarBackgroundColor(R.color.colorBlue);
        //生成BottomNavigationBar
        navbar.initialise();
        navbar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

            }

            @Override
            public void onTabUnselected(int position) {
                if(position == 2){
                    if(!badge.isHidden())
                        badge.hide();
                }
            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    /**
     * 获得一个导航图标右上角的标记,例如 未读消息
     *
     * @param borderWidth  边距
     * @param text         内容
     * @param hideOnSelect 点击时是否显示标记
     */
    private BadgeItem initBadge(int borderWidth, String text, boolean hideOnSelect) {
        return new BadgeItem()
                .setBorderWidth(borderWidth)
                .setBackgroundColorResource(R.color.colorAccent)
                .setText(text)
                .setHideOnSelect(hideOnSelect);
    }

    public void change(View v){
        initNavBar();
    }


}
