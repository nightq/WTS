package dev.nightq.wts.tools;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Nightq on 16/9/12.
 * @see android.view.View 的 帮助类
 */

public class ViewHelper {

    /**
     * 显示文字,没有类型就不显示
     */
    public static void showTextToView(String content, TextView view) {
        showTextToView(content, view, view);
    }

    /**
     * 显示文字,没有类型就不显示
     */
    public static void showTextToView(String content, TextView view, View targetView) {
        if (view == null || targetView == null) {
            return;
        }
        if (TextUtils.isEmpty(content)) {
            targetView.setVisibility(View.GONE);
        } else {
            targetView.setVisibility(View.VISIBLE);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                view.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY));
            } else {
                view.setText(Html.fromHtml(content));
            }
        }
    }

}