package com.zhiluo.android.yunpu.myview;



import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.lang.reflect.Field;
import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-05-22.
 * K线绘制
 */

public class KView extends View {
    private float lineSmoothness = 0.2f;
    private List<Point> mPointList;
    private Path mPath;
    private Path mAssistPath;
    private float drawScale = 1f;
    private PathMeasure mPathMeasure;
    private float defYAxis = 700f;
    private float defXAxis = 10f;
    private List<Float> textXPoint;
    private List<String> dayList;
    private int px;
    private PathMeasure measure;

    public KView(Context context) {
        super(context);
    }

    public KView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPointList(List<Point> pointList) {
        mPointList = pointList;
        //measurePath();
    }

    public List<String> getDayList() {
        return dayList;
    }

    public void setDayList(List<String> dayList) {
        this.dayList = dayList;
        // measurePath();
    }

    public void setLineSmoothness(float lineSmoothness) {
        if (lineSmoothness != this.lineSmoothness) {
            this.lineSmoothness = lineSmoothness;
            //measurePath();
            measureLinePath();
            postInvalidate();
        }
    }

    public void setDrawScale(float drawScale) {
        this.drawScale = drawScale;
        postInvalidate();
    }

    /**
     * @param duration  ，
     *                  设置view绘制的动画
     */
    public void startAnimation(long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "drawScale", 0f, 1f);
        animator.setDuration(duration);
        animator.start();
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public List<Float> getTextXPoint() {
        return textXPoint;
    }

    public void setTextXPoint(List<Float> textXPoint) {
        this.textXPoint = textXPoint;
    }


    /**
     * view的大小控制
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));

    }

    /**
     * 测试k线路径
     */
    private void measureLinePath() {
        Path path = new Path();
        path.moveTo(mPointList.get(0).x, mPointList.get(0).y);
        for (int i = 0; i < mPointList.size(); i++) {
            //  path.moveTo(mPointList.get(0).x, mPointList.get(0).y);
            path.lineTo(mPointList.get(i).x, mPointList.get(i).y);
        }
        measure = new PathMeasure(path, false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
      /*  int w1 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h1 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        this.measure(w1, h1);
        int w2 = this.getMeasuredWidth();
        int h2 = this.getMeasuredHeight();
        Li(w2 + "------" + h2);*/
        if (mPointList == null)
            return;
        // measurePath();
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#FF6839"));
        paint.setStrokeWidth(7);
        paint.setStyle(Paint.Style.STROKE);
        //绘制辅助线
        // canvas.drawPath(mAssistPath,paint);

        Path dst = new Path();
        dst.rLineTo(0, 0);//消除硬件加速可能带来的bug
        float distance = measure.getLength() * drawScale;//mPathMeasure.getLength()获取整个线段的长度，乘上一个drawScale后就把长度分成了和长度相等的等分，
        if (measure.getSegment(0, distance, dst, true)) {//在每个片段长度为distance的基础上不断添加片段长度相同的其它片段//返回true还有片段需要添加，false片段添加完了
            // 下面就是绘制每一小片段
            //绘制线 ，每一个小片段的path被保存在了dst中，这里直接用dst就可以了；
            canvas.drawPath(dst, paint);
            float[] pos = new float[2];
            measure.getPosTan(distance, pos, null);//获取每个小片段结束的地方的位置，pos[0]代表x的位置，pos[1]代表y的位置
            //绘制阴影
            drawShadowArea(canvas, dst, pos);
            //绘制点
            drawPoint(canvas, pos);
            //绘制白色底边
            drawWhitArea(canvas);
            //绘制日期
            drawDay(getDayList(), canvas, pos);
            //绘制值
            drawValue(canvas, pos);

        }

    }

    /**
     * @param canvas ,
     * @param pos    ,
     *               绘制值
     */
    private void drawValue(Canvas canvas, final float[] pos) {
        Paint mPaint = new Paint();
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#808080"));
        mPaint.setTextSize(30);
        for (int i = 0; i < mPointList.size(); i++) {
            if (mPointList.get(i).x > pos[0]) {
                break;
            } else {
                if (i == 0) {
                    //第一个点
                    if (mPointList.get(0).y > mPointList.get(1).y) {
                        canvas.drawText(mPointList.get(i).y + "", mPointList.get(i).x - 30, mPointList.get(i).y - 30, mPaint);
                    } else {
                        canvas.drawText(mPointList.get(i).y + "", mPointList.get(i).x - 30, mPointList.get(i).y + 30, mPaint);
                    }
                    //最后一个点
                } else if (i == mPointList.size() - 1) {
                    if (mPointList.get(i).y > mPointList.get(i - 1).y) {
                        canvas.drawText(mPointList.get(i).y + "", mPointList.get(i).x - 30, mPointList.get(i).y + 30, mPaint);
                    } else {
                        canvas.drawText(mPointList.get(i).y + "", mPointList.get(i).x - 30, mPointList.get(i).y - 30, mPaint);
                    }
                    //中间点
                } else {
                    if (mPointList.get(i).y > mPointList.get(i + 1).y && mPointList.get(i).y > mPointList.get(i - 1).y) {
                        canvas.drawText(mPointList.get(i).y + "", mPointList.get(i).x - 30, mPointList.get(i).y + 50, mPaint);
                    } else {
                        canvas.drawText(mPointList.get(i).y + "", mPointList.get(i).x - 30, mPointList.get(i).y - 30, mPaint);
                    }
                }
            }

        }
    }

    /**
     * @param dayList ,
     * @param canvas  ,
     * @param pos     ,
     *                绘制日期
     */
    private void drawDay(List<String> dayList, Canvas canvas, final float[] pos) {
        Paint mPaint = new Paint();
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#808080"));
        mPaint.setTextSize(25);
        for (int j = 0; j < dayList.size(); j++) {
            float textYPoint = getMeasuredHeight() - 10;
            if (mPointList.get(j).x > pos[0]) {
                break;
            }
            canvas.drawText(dayList.get(j), mPointList.get(j).x - 30, textYPoint, mPaint);
        }
    }

    /**
     * 绘制一个淡色的底边矩形框
     */
    private void drawWhitArea(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#f5f5f5"));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawRect(0,  getMeasuredHeight() - 40, YSLUtils.getPhoneWidth(),  getMeasuredHeight(), paint);
    }


    /**
     * 绘制阴影
     *
     * @param canvas
     * @param path
     * @param pos
     */
    private void drawShadowArea(Canvas canvas, Path path, float[] pos) {
        path.lineTo(pos[0], defYAxis);
        path.lineTo(defXAxis, defYAxis);
        path.close();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#FFF5EC"));
        canvas.drawPath(path, paint);
    }

    /**
     * 绘制点
     *
     * @param canvas
     * @param pos
     */
    private void drawPoint(Canvas canvas, final float[] pos) {
        //内圈白色画笔
        Paint innerPaint = new Paint();
        innerPaint.setAntiAlias(true);
        innerPaint.setColor(Color.WHITE);
        innerPaint.setStrokeWidth(5);
        innerPaint.setStyle(Paint.Style.FILL);
        //外圈橙色画笔
        Paint outPaint = new Paint();
        outPaint.setAntiAlias(true);
        outPaint.setColor(Color.parseColor("#FF6839"));
        outPaint.setStrokeWidth(5);
        outPaint.setStyle(Paint.Style.STROKE);

        for (Point point : mPointList) {
            if (point.x > pos[0]) {
                break;
            }
            canvas.drawCircle(point.x, point.y, 5, innerPaint);
            canvas.drawCircle(point.x, point.y, 8, outPaint);
        }
    }



    /**
     * @param measureSpec  ,
     * @return   .
     *           测量高
     */
    private int measureHeight(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 250;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;

    }

    /**
     * @param measureSpec  ,
     * @return   .
     *           测量宽
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 400;//根据自己的需要更改
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;

    }

}