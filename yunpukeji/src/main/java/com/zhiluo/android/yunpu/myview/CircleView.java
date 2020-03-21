package com.zhiluo.android.yunpu.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.zhiluo.android.yunpu.R;

import java.util.ArrayList;
import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-05-22.
 * 环形比例图绘制
 */

public class CircleView extends View {

    private int ScrWidth, ScrHeight;
    private float arrPer[];


    /**
     * isActivity： true 表示在会员统计页面的，false表示综合统计fragment中的
     */
    private boolean isActivity;

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //屏幕信息
        DisplayMetrics dm = getResources().getDisplayMetrics();
        ScrHeight = dm.heightPixels;
        ScrWidth = dm.widthPixels;
    }

    public CircleView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub


    }

    public boolean isActivity() {
        return isActivity;
    }

    public void setActivity(boolean activity) {
        isActivity = activity;
    }

    public float[] getArrPer() {
        return arrPer;
    }

    public void setArrPer(float[] arrPer) {
        this.arrPer = arrPer;
    }

    /**
     * RGB颜色数组
     */
    private int[][] getRGB() {
        int arrColorRgb[][];
        if (isActivity()) {
            arrColorRgb = new int[][]{{255, 158, 1},//橙色
                    {253, 214, 0},//黄色
            };
        } else {
            arrColorRgb = new int[][]{{254, 121, 56},//橙色
                    {254, 195, 15},//黄色
                    {111, 172, 252},//蓝色
            };
        }
        return arrColorRgb;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(ScrWidth / 3, ScrWidth / 2);
    }

    @Override
    public void onDraw(Canvas canvas) {

        //画布背景
        // canvas.drawColor(getResources().getColor(R.color.colorAccent));

        float cirX = ScrWidth / 4;
        float cirY = ScrHeight / 3;
        float radius = ScrWidth / 6;//圆环半径
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.white));
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        float arcLeft = cirX - radius;
        float arcTop = cirY - radius;
        float arcRight = cirX + radius;
        float arcBottom = cirY + radius;
        RectF arcRF0 = new RectF(arcLeft - radius / 2, arcTop - radius * 2, arcRight - radius / 2, arcBottom - radius * 2);
        // canvas.drawRect(arcRF0, paint);
        //画笔初始化
        Paint PaintArc = new Paint();
        PaintArc.setAntiAlias(true);

        Paint PaintLabel = new Paint();
        PaintLabel.setColor(Color.WHITE);
        PaintLabel.setTextSize(20);
        PaintLabel.setAntiAlias(true);//字体抗锯齿
        PaintLabel.setStrokeWidth(5);

        //位置计算类
        XChartCalc xcalc = new XChartCalc();

        float Percentage = 0.0f;
        float CurrPer = 90.0f;//圆环的起始位置，0是右边，
        int i = 0;
        arrPer = getArrPer();
        if (arrPer != null) {
            for (i = 0; i < arrPer.length; i++) {
                //将百分比转换为饼图显示角度
                Percentage = 360 * (arrPer[i] / 100);
                Percentage = (float) (Math.round(Percentage * 100)) / 100;
                //分配颜色
                PaintArc.setARGB(255, getRGB()[i][0], getRGB()[i][1], getRGB()[i][2]);
                Li("百分比--" + Percentage);
                //在饼图中显示所占比例
                canvas.drawArc(arcRF0, CurrPer, Percentage, true, PaintArc);

                //计算百分比标签
                xcalc.CalcArcEndPointXY(cirX-4, cirY, radius - radius / 2 / 2, CurrPer + Percentage / 2);
                xcalc.setPosX();
                xcalc.setPosY();
                //绘制百分比例数字
               /* if (arrPer.length > 1 && (arrPer[i] + "%").equals("0.0%")) {
                } else {
                    PaintLabel.setColor(getResources().getColor(R.color.black));
                    Li(Float.toString(arrPer[i]) + "%");
                    canvas.drawText(Float.toString(arrPer[i]) + "%", (xcalc.getPosX() - 25) - radius / 2, xcalc.getPosY() - radius * 2, PaintLabel);
                }*/
                //下次的起始角度
                CurrPer += Percentage;
            }
            //绘制百分比例数字
            if (arrPer != null) {
                for (int j = 0; j < arrPer.length; j++) {
                    if (arrPer.length == 1 && (arrPer[j] + "%").equals("0.0%")) {
                        PaintLabel.setColor(getResources().getColor(R.color.black));
                        canvas.drawText(Float.toString(arrPer[j]) + "%", (xcalc.getXList().get(j) - 25) - radius / 2, xcalc.getyList().get(j) - radius * 2, PaintLabel);
                    } else if (arrPer.length > 1 && (arrPer[j] + "%").equals("0.0%")) {
                    } else {
                        PaintLabel.setColor(getResources().getColor(R.color.white));
                        Li(Float.toString(arrPer[j]) + "%");
                        canvas.drawText(Float.toString(arrPer[j]) + "%", (xcalc.getXList().get(j) - 25) - radius / 2, xcalc.getyList().get(j) - radius * 2, PaintLabel);
                    }
                }
            }
            //画圆心
            if (arrPer[0] == 0.0f) {
                PaintArc.setColor(getResources().getColor(R.color.colorAccent));
                canvas.drawCircle(cirX - radius / 2, cirY - radius * 2, radius / 2.2f, PaintArc);
            } else {
                PaintArc.setColor(Color.WHITE);
                canvas.drawCircle(cirX - radius / 2, cirY - radius * 2, radius / 2.2f, PaintArc);
            }
        }
    }

    /**
     * 计算圆弧和半径交点
     */
    class XChartCalc {


        //Position位置
        private float posX = 0.0f;
        private float posY = 0.0f;
        private List<Float> xList = new ArrayList<>();
        private List<Float> yList = new ArrayList<>();

        public XChartCalc() {

        }

        //依圆心坐标，半径，扇形角度，计算出扇形终射线与圆弧交叉点的xy坐标
        public void CalcArcEndPointXY(float cirX, float cirY, float radius, float cirAngle) {

            //将角度转换为弧度
            float arcAngle = (float) (Math.PI * cirAngle / 180.0);
            if (cirAngle < 90) {
                posX = cirX + (float) (Math.cos(arcAngle)) * radius;
                posY = cirY + (float) (Math.sin(arcAngle)) * radius;
            } else if (cirAngle == 90) {
                posX = cirX;
                posY = cirY + radius;
            } else if (cirAngle > 90 && cirAngle < 180) {
                arcAngle = (float) (Math.PI * (180 - cirAngle) / 180.0);
                posX = cirX - (float) (Math.cos(arcAngle)) * radius;
                posY = cirY + (float) (Math.sin(arcAngle)) * radius;
            } else if (cirAngle == 180) {
                posX = cirX - radius;
                posY = cirY;
            } else if (cirAngle > 180 && cirAngle < 270) {
                arcAngle = (float) (Math.PI * (cirAngle - 180) / 180.0);
                posX = cirX - (float) (Math.cos(arcAngle)) * radius;
                posY = cirY - (float) (Math.sin(arcAngle)) * radius;
            } else if (cirAngle == 270) {
                posX = cirX;
                posY = cirY - radius;
            } else {
                arcAngle = (float) (Math.PI * (360 - cirAngle) / 180.0);
                posX = cirX + (float) (Math.cos(arcAngle)) * radius;
                posY = cirY - (float) (Math.sin(arcAngle)) * radius;
            }

        }

        public void setPosX() {
            xList.add(this.posX);
        }

        public void setPosY() {
            yList.add(this.posY);
        }

        public List<Float> getXList() {
            return xList;
        }

        public List<Float> getyList() {
            return yList;
        }

        /**
         * @return , x-point
         */
        public float getPosX() {
            return posX;
        }


        /**
         * @return , y-point
         */
        public float getPosY() {
            return posY;
        }

    }
}