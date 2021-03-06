package com.jardelzermiani.cadastrocliente;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

/**
 * @author   Kartik Sharma
 * @category Circle Wave Progress View
 *
 */
public class CircleWaveMensal extends View {

	private Context mContext;

	private int mScreenWidth;
	private int mScreenHeight;

	private Paint mRingPaint;
	private Paint mCirclePaint;
	private Paint mWavePaint;
	private Paint flowPaint;
	private Paint leftPaint;

	private int mRingSTROKEWidth = 80;
	private int mCircleSTROKEWidth = 0;

	private int mCircleColor = Color.rgb(119,83,245);
	private int mWaveColor = Color.rgb(119,83,245);
	private int mRingColor = Color.rgb(184,177,238);

	private Handler mHandler;
	private long c = 0L;
	private boolean mStarted = false;
	private final float f = 0.033F;
	private int mAlpha = 90;// Transparency
	private float mAmplitude = 8.0F; // Amplitude
	private float mWaterLevel = 0.0F;// Water Level(0~1)
	private Path mPath;

	private String progressPer = "Percentage %";
	private String updateText ="Update Status";

	/**
	 * @param context
	 */
	public CircleWaveMensal(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		init(mContext);
	}

	/**
	 * @param context
	 * @param attrs
	 **/


	public CircleWaveMensal(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		init(mContext);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public CircleWaveMensal(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		mContext = context;
		init(mContext);
	}

	public void setmWaterLevel(float mWaterLevel) {
		this.mWaterLevel = mWaterLevel;
	}

	public void setWaveProgess(String fProgress){
		this.progressPer = fProgress;
	}

	public int getWaveProgress(){
		return (int)mWaterLevel+1;

	}

	public void setWaveUpdate(String fLeft){
		this.updateText=fLeft;
	}

	private void init(Context context) {

		mRingPaint = new Paint();
		mRingPaint.setColor(mRingColor);
		mRingPaint.setAlpha(90);
		mRingPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		mRingPaint.setAntiAlias(true);

		mCirclePaint = new Paint();
		mCirclePaint.setColor(mCircleColor);
		mCirclePaint.setAlpha(200);
		mCirclePaint.setStyle(Paint.Style.STROKE);
		mCirclePaint.setAntiAlias(true);
		mCirclePaint.setStrokeWidth(mCircleSTROKEWidth);

		mWavePaint = new Paint();
		mWavePaint.setColor(mWaveColor);
		mWavePaint.setAlpha(200);
		mPath = new Path();

		flowPaint = new Paint();
		flowPaint.setColor(Color.rgb(38,38,38));
		flowPaint.setStyle(Paint.Style.FILL);
		flowPaint.setAntiAlias(true);
		flowPaint.setTextSize(58);

		leftPaint = new Paint();
		leftPaint.setColor(Color.rgb(38,38,38));
		leftPaint.setStyle(Paint.Style.FILL);
		leftPaint.setAntiAlias(true);
		leftPaint.setTextSize(45);


		mHandler = new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 0) {
					invalidate();
					if (mStarted) {
						// Message constantly so that the view is redrawn.
						mHandler.sendEmptyMessageDelayed(0, 60L);
						progressPer = String.valueOf((int)(100*mWaterLevel)+1+" %");
					}
				}
			}
		};
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = measure(widthMeasureSpec, true);
		int height = measure(heightMeasureSpec, false);
		if (width < height) {
			setMeasuredDimension(width, width);
		} else {
			setMeasuredDimension(height, height);
		}

	}

	/**
	 * @category Measuring
	 * @param measureSpec
	 * @param isWidth
	 * @return
	 */
	private int measure(int measureSpec, boolean isWidth) {
		int result;
		int mode = MeasureSpec.getMode(measureSpec);
		int size = MeasureSpec.getSize(measureSpec);
		int padding = isWidth ? getPaddingLeft() + getPaddingRight()
				: getPaddingTop() + getPaddingBottom();
		if (mode == MeasureSpec.EXACTLY) {
			result = size;
		} else {
			result = isWidth ? getSuggestedMinimumWidth()
					: getSuggestedMinimumHeight();
			result += padding;
			if (mode == MeasureSpec.AT_MOST) {
				if (isWidth) {
					result = Math.max(result, size);
				} else {
					result = Math.min(result, size);
				}
			}
		}
		return result;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(1100, 1100, oldw, oldh);
		mScreenWidth = 1100;
		mScreenHeight = 1100;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// Get width and height controls
		int width = 1100;
		int height = getHeight();

		//Calculate the distance of the current level of water line and the center line
		float centerOffset = Math.abs(mScreenWidth / 2 * mWaterLevel - mScreenWidth / 4);
		//Computing the angle with the horizontal center line
		float horiAngle = (float)(Math.asin(centerOffset / (mScreenWidth / 4)) * 180 / Math.PI);
		//Sector start angle and sweep angle
		float startAngle, sweepAngle;
		if (mWaterLevel > 0.5F) {
			startAngle = 360F - horiAngle;
			sweepAngle = 180F + 2 * horiAngle;
		} else {
			startAngle = horiAngle;
			sweepAngle = 180F - 2 * horiAngle;
		}


		float num = flowPaint.measureText(progressPer);
		canvas.drawText(progressPer, mScreenWidth * 4 / 8 - num / 2 - 200,
				mScreenHeight * 4 / 8 - 150, flowPaint);
		float left = leftPaint.measureText(updateText);
		canvas.drawText(updateText, mScreenWidth * 4 / 8 - left / 2 - 200,
				mScreenHeight * 3 / 8 - 150, leftPaint);

		// If the startwave method is not called, Draw water at rest
		if ((!mStarted) || (mScreenWidth == 0) || (mScreenHeight == 0)) {
			// Draw the height of the water at rest
			RectF oval = new RectF(mScreenWidth / 4 - 200, mScreenHeight / 4 - 200,
					mScreenWidth * 3 / 4 - 200, mScreenHeight * 3 / 4 - 200);
			canvas.drawArc(oval, startAngle, sweepAngle, false, mWavePaint);
			return;
		}
		// Draw the height of the water at rest
		RectF oval = new RectF(mScreenWidth / 4 - 200, mScreenHeight / 4 - 200,
				mScreenWidth * 3 / 4 - 200, mScreenHeight * 3 / 4 - 200);
		canvas.drawArc(oval, startAngle, sweepAngle, false, mWavePaint);

		if (this.c >= 8388607L) {
			this.c = 0L;
		}
		// Each time onDraw c is incremented
		c = (1L + c);
		float f1 = mScreenHeight * (0.820F - (0.25F + mWaterLevel / 2)) - mAmplitude;
		//Length of the current water line
		float waveWidth = (float) Math.sqrt(mScreenWidth * mScreenWidth / 16 - centerOffset * centerOffset);
		//And an offset circle radius
		float offsetWidth = mScreenWidth / 4 - waveWidth;

		int top = (int) (f1 + mAmplitude);
		mPath.reset();
		//X coordinate of the starting vibration , X coordinate of the end vibration
		int startX, endX;
		if (mWaterLevel > 0.50F) {
			startX = (int) (mScreenWidth / 4 - 200 + offsetWidth);
			endX = (int) (mScreenWidth / 2 - 200 + mScreenWidth / 4 - offsetWidth);
		} else {
			startX = (int) (mScreenWidth / 4 - 200 + offsetWidth);
			endX = (int) (mScreenWidth / 2 - 200 + mScreenWidth / 4 - offsetWidth);
		}
		// Wave effect
		while (startX < endX) {
			int startY = (int)
					(f1 - mAmplitude * Math.sin(Math.PI * (4.0F * (startX + this.c * width * this.f)) / width));
			canvas.drawLine(startX, startY, startX, top, mWavePaint);
			startX++;
		}

		canvas.drawCircle(mScreenWidth / 2 - 200, mScreenHeight / 2 - 200,
				mScreenWidth / 4, mRingPaint);

		canvas.drawCircle(mScreenWidth / 2 - 200, mScreenHeight / 2 - 200, mScreenWidth / 4, mCirclePaint);
		canvas.restore();
	}

	@Override
	public Parcelable onSaveInstanceState() {
		// Force our ancestor class to save its state
		Parcelable superState = super.onSaveInstanceState();
		SavedState ss = new SavedState(superState);
		ss.progress = (int) c;
		return ss;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		SavedState ss = (SavedState) state;
		super.onRestoreInstanceState(ss.getSuperState());
		c = ss.progress;
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		// Turn off hardware acceleration to prevent abnormal unsupported operation exception
		this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
	}

	/**
	 * @category Start the Wave
	 */
	public void startWave() {
		if (!mStarted) {
			this.c = 0L;
			mStarted = true;
			this.mHandler.sendEmptyMessage(0);
		}
	}

	/**
	 * @category Stop the Wave
	 */
	public void stopWave() {
		if (mStarted) {
			this.c = 0L;
			mStarted = false;
			this.mHandler.removeMessages(0);
		}
	}


	public boolean isWavePresent(){
		return mStarted;
	}

	/**
	 * @category Save state
	 */
	static class SavedState extends BaseSavedState {
		int progress;

		/**
		 * Constructor called from {@link ProgressBar#onSaveInstanceState()}
		 */
		SavedState(Parcelable superState) {
			super(superState);
		}

		/**
		 * Constructor called from {@link #CREATOR}
		 */
		private SavedState(Parcel in) {
			super(in);
			progress = in.readInt();
		}

		@Override
		public void writeToParcel(Parcel out, int flags) {
			super.writeToParcel(out, flags);
			out.writeInt(progress);
		}

		public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
			public SavedState createFromParcel(Parcel in) {
				return new SavedState(in);
			}

			public SavedState[] newArray(int size) {
				return new SavedState[size];
			}
		};
	}

}
