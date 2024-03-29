package com.bairuitech.anychat.common;

import com.bairuitech.anychat.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;

public class TraceSelectDialog extends PopupWindow {
	private RadioButton mTraceSenderBtn;
	private RadioButton mTraceReceiverBtn;
	private Button mTraceConfimBtn;
	private Button mTraceCancelBtn;
	private View mLayoutView;
	
	public  TraceSelectDialog(Activity content, OnClickListener onListener) {
		super(content);
		LayoutInflater inflater = (LayoutInflater)content.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mLayoutView = inflater.inflate(R.layout.udptrace, null);
		
		mTraceSenderBtn = (RadioButton)mLayoutView.findViewById(R.id.traceSender);
		mTraceReceiverBtn = (RadioButton)mLayoutView.findViewById(R.id.traceReceiver);
		mTraceConfimBtn = (Button)mLayoutView.findViewById(R.id.udpTraceConfirm);
		mTraceCancelBtn = (Button)mLayoutView.findViewById(R.id.udpTraceCancel);
		
		mTraceConfimBtn.setOnClickListener(onListener);
		mTraceCancelBtn.setOnClickListener(onListener);
		
		mTraceSenderBtn.setChecked(true);
		
		this.setContentView(mLayoutView);
		this.setWidth(LayoutParams.FILL_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.AnimBottom);
		ColorDrawable dw = new ColorDrawable((Color.TRANSPARENT));
		this.setBackgroundDrawable(dw);
		mLayoutView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int height = mLayoutView.findViewById(R.id.selectRole_layout).getTop();
				int y = (int)event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				
				return true;
			}
		});
	}
	
	public short getCurSelectTraceRole(){
		if (mTraceSenderBtn.isChecked()) {
			return 0;	// 0表示发送者
		}else {
			return 1;   // 1表示接受者
		}
	}
}