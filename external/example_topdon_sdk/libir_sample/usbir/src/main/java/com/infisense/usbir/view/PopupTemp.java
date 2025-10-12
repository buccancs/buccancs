package com.infisense.usbir.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.infisense.usbir.R;
import com.infisense.usbir.adapter.TempAdapter;


public class PopupTemp {

    private Context mContext;
    private PopupWindow popupWindow;
    private RecyclerView recyclerView;

        public PopupTemp(Context context, TempAdapter imgAdapter, PopupWindow.OnDismissListener dismissListener) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycleview, null);
        popupWindow = new PopupWindow(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setOnDismissListener(dismissListener);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000)); // 解决 7.0 手机，点击外部不消失
        recyclerView = view.findViewById(R.id.recycler_view);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imgAdapter);
    }

        public void showAsDropDown(View parent) {
        popupWindow.showAsDropDown(parent);
    }

        public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

}


