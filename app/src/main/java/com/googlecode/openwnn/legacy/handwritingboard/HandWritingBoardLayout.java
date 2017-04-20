/*
 * View3.java
 *
 * Version info
 *
 * Create time
 *
 * Last modify time
 *
 * Copyright (c) 2010 FOXCONN Technology Group All rights reserved
 */
package com.googlecode.openwnn.legacy.handwritingboard;

import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsoluteLayout;

import com.googlecode.openwnn.legacy.OnHandWritingRecognize;
import com.googlecode.openwnn.legacy.WnnWord;
import com.wwengine.hw.WWHandWrite;

/**
 * View3.java
 * <p>
 */
/**
 * description
 * 
 * @author cairuizhi
 */
public class HandWritingBoardLayout extends AbsoluteLayout {
	private static final String TAG = "SelfAbsoluteLayout";

	private OnHandWritingRecognize mOnHandWritingRecognize;

	private float mx;
	private float my;
	private Path mPath;
	private Paint mPaint;
	private Paint mPaintText;
	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private static char[] mResult1;
	private static short[] mTracks;
	private static int mCount;
	private static Context mContext;
	private static int mFontSize;

	/**
	 * @param context
	 */
	public HandWritingBoardLayout(Context context) {
		super(context);
		init();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public HandWritingBoardLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;

		ApplicationInfo mAppInfo = mContext.getApplicationInfo();

		init();
		hw_init();
	}

	private void hw_init() {

		byte[] hwData = readData(mContext.getAssets(), "hwdata.bin");
		if (hwData == null) {
			return;
		}

		WWHandWrite.apkBinding(mContext);

		if (WWHandWrite.hwInit(hwData, 0) != 0) {
			return;
		}

		mResult1 = new char[256];
		mTracks = new short[1024];
		mCount = 0;
	}

	private void init() {

		// why only set background then invalidate() valid
		this.setBackgroundColor(android.R.color.holo_blue_bright);

		{
			mPath = new Path();
			mPaint = new Paint();
			mPaint.setAntiAlias(true);
			mPaint.setDither(true);
			mPaint.setColor(0xFFFF0000);
			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setStrokeJoin(Paint.Join.ROUND);
			mPaint.setStrokeCap(Paint.Cap.ROUND);
			mPaint.setStrokeWidth(6);
		}

		mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintText.setColor(0xFFFF0000);
		mPaintText.setTextSize(mFontSize);
		mPaintText.setTextAlign(Paint.Align.LEFT);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawPath(mPath, mPaint);

		// canvas.drawText(mResult.toString(), 10, 100, mPaintText);
		// canvas.drawText(mResult1, 0, 10, 5, 20 + mFontSize/2, mPaintText);
	}

	private void touch_start(float x, float y) {
		mPath.moveTo(x, y);
		mX = x;
		mY = y;

		mTracks[mCount++] = (short) x;
		mTracks[mCount++] = (short) y;
	}

	private void touch_move(float x, float y) {
		{
			float dx = Math.abs(x - mX);
			float dy = Math.abs(y - mY);
			if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
				mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
				mX = x;
				mY = y;
			}
		}
		mTracks[mCount++] = (short) x;
		mTracks[mCount++] = (short) y;
	}

	private void touch_up() {
		mPath.lineTo(mX, mY);

		mTracks[mCount++] = -1;
		mTracks[mCount++] = 0;
		recognize();
	}

	private void recognize() {
		short[] mTracksTemp;
		int countTemp = mCount;

		mTracksTemp = mTracks.clone();
		mTracksTemp[countTemp++] = -1;
		mTracksTemp[countTemp++] = -1;

		WWHandWrite.hwRecognize(mTracksTemp, mResult1, 10, 0xFFFF);

		if (mOnHandWritingRecognize != null) {

			mOnHandWritingRecognize.handWritingRecognized(convertCharToWnnWord(mResult1));
		}

	}

	public void reset_recognize() {
		mCount = 0;
		mResult1 = new char[256];
		{
			mPath.reset();
		}
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touch_start(x, y);
			invalidate();
			break;

		case MotionEvent.ACTION_MOVE:
			touch_move(x, y);
			invalidate();
			break;

		case MotionEvent.ACTION_UP:
			touch_up();
			invalidate();
			break;
		}
		return true;
		// return super.onTouchEvent(event);
	}

	// //////////////////////////////////////////////////////////////
	// readData
	// //////////////////////////////////////////////////////////////
	private static byte[] readData(AssetManager am, String name) {
		try {

			InputStream is = am.open(name);
			if (is == null) {
				return null;
			}

			int length = is.available();
			if (length <= 0) {
				return null;
			}

			byte[] buf = new byte[length];
			if (buf == null) {
				return null;
			}

			if (is.read(buf, 0, length) == -1) {
				return null;
			}

			is.close();

			return buf;

		} catch (Exception ex) {
			return null;
		}
	}

	public void setOnHandWritingRecognize(OnHandWritingRecognize handWritingRecognize) {
		this.mOnHandWritingRecognize = handWritingRecognize;
	}

	private ArrayList<WnnWord> convertCharToWnnWord(char[] result) {
		ArrayList<WnnWord> words = new ArrayList<WnnWord>();
		int length = result.length;
		for (int i = 0; i < length; ++i) {
			WnnWord wnnWord = new WnnWord(String.valueOf(result[i]), "");
			words.add(wnnWord);
		}
		return words;
	}
}
