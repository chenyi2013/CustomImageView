package com.example.customimageview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class CustomImageView extends ImageView {

	private final Matrix mDrawMatrix = new Matrix();

	/**
	 * 地图在X轴方向的起点座标
	 */
	private float moveX = 0;
	/**
	 * 地图在Y轴方向的起点座标
	 */
	private float moveY = 0;

	private GestureDetectorCompat mDetector;
	private ScaleGestureDetector scaleGestureDetector;
	private float scaleFactor = 1f;
	private AnimatorSet mAnimatorSet;

	private void init(Context context) {

		mDetector = new GestureDetectorCompat(getContext(),
				new MyGestureListener());
		scaleGestureDetector = new ScaleGestureDetector(getContext(),
				new ScaleListener());

		setImageMatrix(mDrawMatrix);

	}

	public CustomImageView(Context context) {
		super(context);
		init(context);
	}

	public CustomImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean retVal = scaleGestureDetector.onTouchEvent(event);
		retVal = mDetector.onTouchEvent(event) || retVal;
		return retVal || super.onTouchEvent(event);
	}

	@SuppressLint("NewApi")
	private Property<CustomImageView, Float> mSacleFactorProperty = new Property<CustomImageView, Float>(
			Float.class, "scaleFactor") {

		@Override
		public Float get(CustomImageView object) {

			return object.scaleFactor;

		}

		@Override
		public void set(CustomImageView object, Float value) {

			object.scaleFactor = value;
			// mDrawMatrix.setTranslate(moveX, moveY);
			// mDrawMatrix.setScale(scaleFactor, scaleFactor);
			mDrawMatrix.setScale(scaleFactor, scaleFactor, getWidth() / 2,
					getHeight() / 2);
			setImageMatrix(mDrawMatrix);

		}
	};

	public void scaleUp() {
		if (scaleFactor < 2 && scaleFactor + 0.5f <= 2) {
			startAnimator(scaleFactor, scaleFactor + 0.5f);
		} else if (scaleFactor < 2 && scaleFactor + 0.5f > 2) {
			startAnimator(scaleFactor, 2);
		}
	}

	public void scaleDown() {
		if (scaleFactor > 0.5f && scaleFactor - 0.5f >= 0.5) {
			startAnimator(scaleFactor, scaleFactor - 0.5f);
		} else if (scaleFactor > 0.5f && scaleFactor - 0.5f < 0.5) {
			startAnimator(scaleFactor, 0.5f);
		}

	}

	private void startAnimator(float start, float end) {

		if (mAnimatorSet != null && mAnimatorSet.isRunning()) {
			return;
		}
		mAnimatorSet = new AnimatorSet();
		mAnimatorSet.play(ObjectAnimator.ofFloat(this, mSacleFactorProperty,
				start, end));
		mAnimatorSet.setDuration(500);
		mAnimatorSet.start();

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

	}

	class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

		private float x;
		private float y;

		@Override
		public boolean onDown(MotionEvent e) {

			System.out.println("x" + e.getX());

			return true;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {

			// mDrawMatrix.setTranslate(100, 100);
			//mDrawMatrix.setScale(0.5f, 0.5f, 100 * 2, 100 * 2);
			mDrawMatrix.setScale(0.5f, 0.5f);
			setImageMatrix(mDrawMatrix);
			return true;
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {

			return true;
		}

	}

	class ScaleListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {

		@Override
		public boolean onScale(ScaleGestureDetector detector) {

			return true;
		}
	}

}
