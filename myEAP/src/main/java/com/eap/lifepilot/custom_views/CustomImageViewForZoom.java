package com.eap.lifepilot.custom_views;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

/**
 * CustomImageViewForZoom is the custom Image view to provide pinch zoom in and
 * zoom out functionality.
 * 
 * @author Gateway Technolabs
 * 
 */
public class CustomImageViewForZoom extends ImageView {
	Matrix matrix;
	// We can be in one of these 3 states
	private int NONE = 0;
	private int DRAG = 1;
	private int ZOOM = 2;
	int mode = NONE;
	// Remember some things for zooming
	PointF last = new PointF();
	PointF start = new PointF();
	float minScale = 1f;
	float maxScale = 3f;
	float[] m;
	int viewWidth, viewHeight;
	static final int CLICK = 3;
	public float saveScale = 1f;
	protected float origWidth, origHeight;
	int oldMeasuredWidth, oldMeasuredHeight;
	ScaleGestureDetector mScaleDetector;
	Context context;
	private PointF curr;
	boolean flagToDetactSingleTouch = true;;

	public CustomImageViewForZoom(Context context) {
		super(context);
		sharedConstructing(context);
	}

	public CustomImageViewForZoom(Context context, AttributeSet attrs) {
		super(context, attrs);
		sharedConstructing(context);
	}

	private void sharedConstructing(Context context) {
		super.setClickable(true);
		this.context = context;
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
		matrix = new Matrix();
		m = new float[9];
		setImageMatrix(matrix);
		setScaleType(ScaleType.MATRIX);
		setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mScaleDetector.onTouchEvent(event);
				curr = new PointF(event.getX(), event.getY());
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					// FrameLayout.LayoutParams layoutParams = new
					// FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT,
					// FrameLayout.LayoutParams.FILL_PARENT);
					// setLayoutParams(layoutParams);

					last.set(curr);
					start.set(last);
					mode = DRAG;
					flagToDetactSingleTouch = true;
					break;
				case MotionEvent.ACTION_MOVE:
					if (mode == DRAG) {
						if (Math.abs(curr.x - last.x) > 25.0f || Math.abs(curr.y - last.y) > 25.0f) {
							float deltaX = curr.x - last.x;
							float deltaY = curr.y - last.y;
							float fixTransX = getFixDragTrans(deltaX, viewWidth, origWidth * saveScale);
							float fixTransY = getFixDragTrans(deltaY, viewHeight, origHeight * saveScale);
							matrix.postTranslate(fixTransX, fixTransY);
							fixTrans();
							last.set(curr.x, curr.y);
							flagToDetactSingleTouch = false;
						}
						if(saveScale==1f) {
							getParent().requestDisallowInterceptTouchEvent(false);
						}
						else {
							getParent().requestDisallowInterceptTouchEvent(true);
						}
					}
					break;
				case MotionEvent.ACTION_UP:
					if (flagToDetactSingleTouch) {
						onSingleClick(curr);
						flagToDetactSingleTouch = true;
					}

					mode = NONE;
					int xDiff = (int) Math.abs(curr.x - start.x);
					int yDiff = (int) Math.abs(curr.y - start.y);
					if (xDiff < CLICK && yDiff < CLICK)
						performClick();
					break;

				case MotionEvent.ACTION_POINTER_UP:
					mode = NONE;
					break;
					
				}

				setImageMatrix(matrix);
				onImageMatrixChanged();
				invalidate();
				return true; // indicate event was handled
			}
			
			
		});
	}

	private InterfaceToDetactSingleTouchListener detactSingleTouchListener;

	public InterfaceToDetactSingleTouchListener getDetactSingleTouchListener() {
		return detactSingleTouchListener;
	}

	public void setDetactSingleTouchListener(InterfaceToDetactSingleTouchListener detactSingleTouchListener) {
		this.detactSingleTouchListener = detactSingleTouchListener;
	}

	protected void onSingleClick(PointF pointF) {
		Log.d("onSingleClick", "On Single Click");
		if (detactSingleTouchListener != null) {
			detactSingleTouchListener.onSingleClickCallBack(pointF);
		}
	}

	private InterfaceDecodeOnMatrixChangeListener onMatrixChangeListener;

	public InterfaceDecodeOnMatrixChangeListener getOnMatrixChangeListener() {
		return onMatrixChangeListener;
	}

	public void setOnMatrixChangeListener(InterfaceDecodeOnMatrixChangeListener onMatrixChangeListener) {
		this.onMatrixChangeListener = onMatrixChangeListener;
	}

	protected void onImageMatrixChanged() {
//		Log.d("onImageMatrixChanged", "On Image Matrix changed");
		if (onMatrixChangeListener != null) {
			onMatrixChangeListener.onMatrixChangedCallBack(getImageMatrix());
		}
	}

	public void setMaxZoom(float x) {
		maxScale = x;
	}

	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
			mode = ZOOM;
			flagToDetactSingleTouch = false;
			return true;
		}

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			float mScaleFactor = detector.getScaleFactor();
			float origScale = saveScale;
			saveScale *= mScaleFactor;
			if (saveScale > maxScale) {
				saveScale = maxScale;
				mScaleFactor = maxScale / origScale;
			} else if (saveScale < minScale) {
				saveScale = minScale;
				mScaleFactor = minScale / origScale;
			}

			if (origWidth * saveScale <= viewWidth || origHeight * saveScale <= viewHeight)
				matrix.postScale(mScaleFactor, mScaleFactor, viewWidth / 2, viewHeight / 2);
			else
				matrix.postScale(mScaleFactor, mScaleFactor, detector.getFocusX(), detector.getFocusY());

			fixTrans();
			return true;
		}
	}

	void fixTrans() {
		matrix.getValues(m);
		float transX = m[Matrix.MTRANS_X];
		float transY = m[Matrix.MTRANS_Y];

		float fixTransX = getFixTrans(transX, viewWidth, origWidth * saveScale);
		float fixTransY = getFixTrans(transY, viewHeight, origHeight * saveScale);

		if (fixTransX != 0 || fixTransY != 0)
			matrix.postTranslate(fixTransX, fixTransY);
	}

	float getFixTrans(float trans, float viewSize, float contentSize) {
		float minTrans, maxTrans;

		if (contentSize <= viewSize) {
			minTrans = 0;
			maxTrans = viewSize - contentSize;
		} else {
			minTrans = viewSize - contentSize;
			maxTrans = 0;
		}

		if (trans < minTrans)
			return -trans + minTrans;
		if (trans > maxTrans)
			return -trans + maxTrans;
		return 0;
	}

	float getFixDragTrans(float delta, float viewSize, float contentSize) {
		if (contentSize <= viewSize) {
			return 0;
		}
		return delta;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		viewWidth = MeasureSpec.getSize(widthMeasureSpec);
		viewHeight = MeasureSpec.getSize(heightMeasureSpec);

		//
		// Rescales image on rotation
		//
		if (oldMeasuredHeight == viewWidth && oldMeasuredHeight == viewHeight || viewWidth == 0 || viewHeight == 0)
			return;
		oldMeasuredHeight = viewHeight;
		oldMeasuredWidth = viewWidth;

		if (saveScale == 1) {
			// Fit to screen.
			float scale;

			Drawable drawable = getDrawable();
			if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0)
				return;
			int bmWidth = drawable.getIntrinsicWidth();
			int bmHeight = drawable.getIntrinsicHeight();

			Log.d("bmSize", "bmWidth: " + bmWidth + " bmHeight : " + bmHeight);

			float scaleX = (float) viewWidth / (float) bmWidth;
			float scaleY = (float) viewHeight / (float) bmHeight;
			scale = Math.min(scaleX, scaleY);
			matrix.setScale(scale, scale);

			// Center the image
			float redundantYSpace = (float) viewHeight - (scale * (float) bmHeight);
			float redundantXSpace = (float) viewWidth - (scale * (float) bmWidth);
			redundantYSpace /= (float) 2;
			redundantXSpace /= (float) 2;

			matrix.postTranslate(redundantXSpace, redundantYSpace);

			origWidth = viewWidth - 2 * redundantXSpace;
			origHeight = viewHeight - 2 * redundantYSpace;
			setImageMatrix(matrix);
		}
		fixTrans();
	}

	public void setSaveScale(float saveScale) {
		this.saveScale = saveScale;
	}

	public float getSaveScale() {
		return this.saveScale;
	}

	public void resetZoom() {
		if (saveScale != 1f) {
			saveScale = 1f;

			// Drawable drawable = getDrawable();
			// if (drawable == null || drawable.getIntrinsicWidth() == 0 ||
			// drawable.getIntrinsicHeight() == 0)
			// return;
			// int bmWidth = drawable.getIntrinsicWidth();
			// int bmHeight = drawable.getIntrinsicHeight();
			//
			// float scaleX = (float) viewWidth / (float) bmWidth;
			// float scaleY = (float) viewHeight / (float) bmHeight;
			// // scale = Math.min(scaleX, scaleY);
			// matrix.setScale(scaleX, scaleY);
			//
			// // Center the image
			// /*
			// * float redundantYSpace = (float) viewHeight - (scaleX * (float)
			// * bmHeight); float redundantXSpace = (float) viewWidth - (scaleY
			// *
			// * (float) bmWidth); redundantYSpace /= (float) 2; redundantXSpace
			// * /= (float) 2; matrix.postTranslate(redundantXSpace,
			// * redundantYSpace); origWidth = viewWidth - 2 * redundantXSpace;
			// * origHeight = viewHeight - 2 * redundantYSpace;
			// */
			//
			// origWidth = viewWidth;
			// origHeight = viewHeight;
			//
			// setImageMatrix(matrix);
			// // matrix.setScale(scaleX, scaleY);
			// invalidate();

			if (saveScale == 1) {
				// Fit to screen.
				float scale;

				Drawable drawable = getDrawable();
				if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0)
					return;
				int bmWidth = drawable.getIntrinsicWidth();
				int bmHeight = drawable.getIntrinsicHeight();

				float scaleX = (float) viewWidth / (float) bmWidth;
				float scaleY = (float) viewHeight / (float) bmHeight;
				scale = Math.min(scaleX, scaleY);
				matrix.setScale(scale, scale);

				// Center the image
				float redundantYSpace = (float) viewHeight - (scale * (float) bmHeight);
				float redundantXSpace = (float) viewWidth - (scale * (float) bmWidth);
				redundantYSpace /= (float) 2;
				redundantXSpace /= (float) 2;

				matrix.postTranslate(redundantXSpace, redundantYSpace);

				origWidth = viewWidth - 2 * redundantXSpace;
				origHeight = viewHeight - 2 * redundantYSpace;
				setImageMatrix(matrix);
				invalidate();
			}
			fixTrans();
		}
	}

}