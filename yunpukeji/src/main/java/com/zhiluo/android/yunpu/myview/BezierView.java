package com.zhiluo.android.yunpu.myview;

/**
 * Created by free on 2018/5/23.
 */

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.zhiluo.android.yunpu.R;

import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-05-22.
 * 贝塞尔曲线绘制
 */

public class BezierView extends View {
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

    public BezierView(Context context) {
        super(context);
    }

    public BezierView(Context context, AttributeSet attrs) {
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
            measurePath();
            postInvalidate();
        }
    }

    public void setDrawScale(float drawScale) {
        this.drawScale = drawScale;
        postInvalidate();
    }

    public void startAnimation(long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "drawScale", 0f, 1f);
        animator.setDuration(duration);
        animator.start();
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
        measurePath();
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        //绘制辅助线
//         canvas.drawPath(mAssistPath,paint);

        paint.setColor(Color.BLUE);
        Path dst = new Path();
        dst.rLineTo(0, 0);//消除硬件加速带来的bug
        float distance = mPathMeasure.getLength() * drawScale;//mPathMeasure.getLength()获取整个线段的长度，乘上一个drawScale后就把长度分成了和长度相等的等分，
        if (mPathMeasure.getSegment(0, distance, dst, true)) {//在每个片段长度为distance的基础上不断添加片段长度相同的其它片段//返回true还有片段需要添加，false片段添加完了
            // 下面就是绘制每一小片段
            //绘制线 ，每一个小片段的path被保存在了dst中，这里直接用dst就可以了；
            canvas.drawPath(dst, paint);
            float[] pos = new float[2];
            mPathMeasure.getPosTan(distance, pos, null);//获取每个小片段结束的地方的位置，pos[0]代表x的位置，pos[1]代表y的位置
            //绘制阴影
            //   drawShadowArea(canvas, dst, pos);
            //绘制点
            drawPoint(canvas, pos);
            //绘制日期
            drawDay(getDayList(), canvas, pos);
            //绘制值
            drawValue(canvas, pos);

        }
        /*greenPaint.setPathEffect(getPathEffect(mPathMeasure.getLength()));
        canvas.drawPath(mPath, greenPaint);*/
        //mPath.reset();adb shell screenrecord --bit-rate 2000000 /sdcard/test.mp4
        // drawText(getTextList(), canvas, paint);
    }

    /**
     * @param canvas  ,
     * @param pos   ,
     *              绘制值
     */
    private void drawValue(Canvas canvas, final float[] pos) {
        Paint mPaint = new Paint();
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(30);
        for (int i = 0; i < mPointList.size(); i++) {
            if (mPointList.get(i).x > pos[0]) {
                break;
            } else {
                if (i == 0) {
                    //第一个点
                    if (mPointList.get(0).y > mPointList.get(1).y) {
                        canvas.drawText(mPointList.get(i).y + "", mPointList.get(i).x - 30, getMeasuredHeight()-80-(mPointList.get(i).y - 30), mPaint);
                    } else {
                        canvas.drawText(mPointList.get(i).y + "", mPointList.get(i).x - 30, getMeasuredHeight()-80-(mPointList.get(i).y + 30), mPaint);
                    }
                    //最后一个点
                } else if (i == mPointList.size()-1) {
                    if (mPointList.get(i).y > mPointList.get(i-1).y) {
                        canvas.drawText(mPointList.get(i).y + "", mPointList.get(i).x - 30, getMeasuredHeight()-80-(mPointList.get(i).y +30), mPaint);
                    } else {
                        canvas.drawText(mPointList.get(i).y + "", mPointList.get(i).x - 30, getMeasuredHeight()-80-(mPointList.get(i).y -30), mPaint);
                    }
                    //中间点
                } else {
                    if (mPointList.get(i).y > mPointList.get(i + 1).y && mPointList.get(i).y > mPointList.get(i - 1).y) {
                        canvas.drawText(mPointList.get(i).y + "", mPointList.get(i).x - 30, getMeasuredHeight()-80-(mPointList.get(i).y + 50), mPaint);
                    } else {
                        canvas.drawText(mPointList.get(i).y + "", mPointList.get(i).x - 30, getMeasuredHeight()-80-(mPointList.get(i).y - 30), mPaint);
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
        mPaint.setColor(Color.BLACK);
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
        paint.setColor(0x88CCCCCC);
        canvas.drawPath(path, paint);
    }

    /**
     * 绘制点
     *
     * @param canvas
     * @param pos
     */
    private void drawPoint(Canvas canvas, final float[] pos) {
        Paint redPaint = new Paint();
        redPaint.setAntiAlias(true);
        redPaint.setColor(Color.BLUE);
        redPaint.setStrokeWidth(2);
        redPaint.setStyle(Paint.Style.FILL);
        for (Point point : mPointList) {
            if (point.x > pos[0]) {
                break;
            }
            canvas.drawCircle(point.x, getMeasuredHeight()-80-point.y, 5, redPaint);
        }
    }

    private PathEffect getPathEffect(float length) {
        return new DashPathEffect(new float[]{length * drawScale, length}, 0);
    }

    private void measurePath() {
        mPath = new Path();
        mAssistPath = new Path();
        float prePreviousPointX = Float.NaN;
        float prePreviousPointY = Float.NaN;
        float previousPointX = Float.NaN;
        float previousPointY = Float.NaN;
        float currentPointX = Float.NaN;
        float currentPointY = Float.NaN;
        float nextPointX;
        float nextPointY;

        final int lineSize = mPointList.size();
        for (int valueIndex = 0; valueIndex < lineSize; ++valueIndex) {
            if (Float.isNaN(currentPointX)) {
                Point point = mPointList.get(valueIndex);
                currentPointX = point.x;
                currentPointY = getMeasuredHeight()-80-point.y;
            }
            if (Float.isNaN(previousPointX)) {
                //是否是第一个点
                if (valueIndex > 0) {
                    Point point = mPointList.get(valueIndex - 1);
                    previousPointX = point.x;
                    previousPointY = getMeasuredHeight()-80-point.y;
                } else {
                    //是的话就用当前点表示上一个点
                    previousPointX = currentPointX;
                    previousPointY = currentPointY;
                }
            }

            if (Float.isNaN(prePreviousPointX)) {
                //是否是前两个点
                if (valueIndex > 1) {
                    Point point = mPointList.get(valueIndex - 2);
                    prePreviousPointX = point.x;
                    prePreviousPointY = getMeasuredHeight()-80-point.y;
                } else {
                    //是的话就用当前点表示上上个点
                    prePreviousPointX = previousPointX;
                    prePreviousPointY = previousPointY;
                }
            }

            // 判断是不是最后一个点了
            if (valueIndex < lineSize - 1) {
                Point point = mPointList.get(valueIndex + 1);
                nextPointX = point.x;
                nextPointY = getMeasuredHeight()-80-point.y;
            } else {
                //是的话就用当前点表示下一个点
                nextPointX = currentPointX;
                nextPointY = currentPointY;
            }

            if (valueIndex == 0) {
                // 将Path移动到开始点
                mPath.moveTo(currentPointX, currentPointY);
                mAssistPath.moveTo(currentPointX, currentPointY);
            } else {
                // 求出控制点坐标
                final float firstDiffX = (currentPointX - prePreviousPointX);
                final float firstDiffY = (currentPointY - prePreviousPointY);
                final float secondDiffX = (nextPointX - previousPointX);
                final float secondDiffY = (nextPointY - previousPointY);
                final float firstControlPointX = previousPointX + (lineSmoothness * firstDiffX);
                final float firstControlPointY = previousPointY + (lineSmoothness * firstDiffY);
                final float secondControlPointX = currentPointX - (lineSmoothness * secondDiffX);
                final float secondControlPointY = currentPointY - (lineSmoothness * secondDiffY);
                //画出曲线
                mPath.cubicTo(firstControlPointX, firstControlPointY, secondControlPointX, secondControlPointY,
                        currentPointX, currentPointY);
                //画直线
//                mPath.lineTo(currentPointX, currentPointY);
                //将控制点保存到辅助路径上
                mAssistPath.lineTo(firstControlPointX, firstControlPointY);
                mAssistPath.lineTo(secondControlPointX, secondControlPointY);
                mAssistPath.lineTo(currentPointX, currentPointY);
            }

            // 更新值,
            prePreviousPointX = previousPointX;
            prePreviousPointY = previousPointY;
            previousPointX = currentPointX;
            previousPointY = currentPointY;
            currentPointX = nextPointX;
            currentPointY = nextPointY;
        }
        mPathMeasure = new PathMeasure(mPath, false);
    }

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