package com.example.xiao.myappshuihu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiao.myappshuihu.R;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.dialog
 * 文件名：ShopDialog
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/9 0009 下午 2:03
 * 描述：TODO
 */
public class ShopDialog extends Dialog{
    public static ShopDialog dialog ;
    public static ImageView image_colse;
    public ShopDialog(Context context) {
        super(context);
    }
    public static Button btn_cancel;
    public static Button btn_confirm;
    public static TextView tv_update_title;

    public ShopDialog(Context context, int theme) {
        super(context, theme);
    }


    public static ShopDialog show(Context context,  boolean cancelable, OnCancelListener cancelListener) {
        dialog = new ShopDialog(context, R.style.Custom_Progress);
//        dialog.setTitle("");
        dialog.setContentView(R.layout.btoom_dialog);


        image_colse= (ImageView) dialog.findViewById(R.id.image_colse);

        // 按返回键是否取消
        dialog.setCancelable(cancelable);
        // 监听返回键处理
        dialog.setOnCancelListener(cancelListener);
        // 设置居中
        dialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); // 获取对话框当前的参数值

        p.height = (int) (d.getHeight()); // 高度设置为屏幕的0.7

        p.width = (int) (d.getWidth() ); // 宽度设置为整个屏幕宽度

        p.alpha = 1f; //设置对话框的透明度

        dialog.getWindow().setAttributes(p);//
        dialog.show();
        return dialog;
    }
    public static void dialogcancel(){
        dialog.cancel();
    }

    //设置对话框点击外部是否关闭，true为关闭，false为不
    public static  void setOutside(boolean flag){
        dialog.setCanceledOnTouchOutside(flag);
    }
}
