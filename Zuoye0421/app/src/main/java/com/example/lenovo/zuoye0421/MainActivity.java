package com.example.lenovo.zuoye0421;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import BaseAdapter.MyBase;

public class MainActivity extends Activity {

    private ListView lv1;
    private RelativeLayout activity_main;
private List<String> mList=new ArrayList<>();
    private MyBase mMyBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION_CODES.KITKAT >= 19) {
          getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        for (int i = 0; i < 20; i++) {
            mList.add("梁坤"+i);

        }
        mMyBase = new MyBase(this, mList);
        lv1.setAdapter(mMyBase);
        lv1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(MainActivity.this,"按下",Toast.LENGTH_SHORT).show();
                    break;
                    case MotionEvent.ACTION_HOVER_MOVE:
   Toast.makeText(MainActivity.this,"我在移动",Toast.LENGTH_SHORT).show();
                    break;
                    case MotionEvent.ACTION_UP:
         Toast.makeText(MainActivity.this,"走",Toast.LENGTH_SHORT).show();
                    break;
                    case MotionEvent.ACTION_CANCEL:

            Toast.makeText(MainActivity.this,"触摸取消",Toast.LENGTH_SHORT).show();
                    break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                           Toast.makeText(MainActivity.this,"多点触摸动作",Toast.LENGTH_SHORT).show();

                    break;

                }

                return false;
            }
        });
        lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                View inflate = View.inflate(MainActivity.this, R.layout.papuwen, null);
                //定义一个数组数组里面定义两个参数：一个数组参数下标为0定义的是宽度
                //    第二个数组参数的下标为1是定义的高度
                int[] loca=new int[2];
                view.getLocationInWindow(loca);
                final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //点击在外面消失
                popupWindow.setOutsideTouchable(true);
                //在外边点击，如果要消失的话，需要设置背景
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //显示popupWinodow里面有4个参数（参数一是上面的，参数二为设置出现的位置
                // 参数三是获取的宽，参数四是获取的高度//）（(int) (loca[1]-view.getHeight()*0.5f)是改变他的高度）
             popupWindow.showAtLocation(parent, Gravity.TOP,loca[0], (int) (loca[1]-view.getHeight()*0.5f));
                //实现控件的功能
                TextView tv1= (TextView) inflate.findViewById(R.id.tv1);
                TextView tv2= (TextView) inflate.findViewById(R.id.tv2);
                TextView tv3= (TextView) inflate.findViewById(R.id.tv3);
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //先让他显示先让他关闭掉
                        popupWindow.dismiss();
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                    }
                });
                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ////先让他显示先让他关闭掉
                        popupWindow.dismiss();
                        mList.remove(position);
                        mMyBase.notifyDataSetChanged();
                    }
                });
                tv3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ////先让他显示先让他关闭掉
                        popupWindow.dismiss();
                        mList.add(position,"贱人");
                        mMyBase.notifyDataSetChanged();
                    }
                });
                return true;
            }
        });
    }

    private void initView() {
        lv1 = (ListView) findViewById(R.id.lv1);


    }
}
