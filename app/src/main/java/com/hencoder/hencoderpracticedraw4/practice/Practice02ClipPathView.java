package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice02ClipPathView extends View {
    Paint paint = new Paint();
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Path path1;
    Path path2;

    public Practice02ClipPathView(Context context) {
        super(context);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        path1 = new Path() ;
        /**
         * Path.Direction.CW:  顺时针
         * Path.Direction.CCW:  逆时针
         */
        path1.addCircle(point1.x + 200, point1.y + 200,100,Path.Direction.CW);
        path2 = new Path() ;
        /**
         * Path.FillType.EVEN_ODD：用奇偶规则填充
         * Path.FillType.INVERSE_EVEN_ODD：和EVEN_ODD规则恰好相反
         * Path.FillType.WINDING：用非零环绕数规则填充
         * Path.FillType.INVERSE_WINDING：和WINDING的反效果
         *
         * 效果图可参考：http://images.cnitblog.com/i/287944/201403/120946220789132.png
         *
         * 奇偶规则是指：对任意点P，从P到无穷远处画一条射线，注意该射线不能和多边形的任何顶点相交，计算该射线和多边形的各条边的交点个数，
         *             如果交点数目为奇数，则认为P点在多边形内，反之，为偶数则认为P点在多边形外
         *
         * 非零环绕数规则是指：从任意点P同样想无穷远处画一条射线，同样不能和任何顶点相交。
         *                  首先初始化环绕边数为0，当P点沿射线方向移动时，统计该穿过该射线的边的方向。
         *                  每当多边形从右到左穿过射线时，边数加1，反之减1。
         *                  最后如果环绕边数为非0，则认为P是内部点，为0则认为P是外部点
         */
        path2.setFillType(Path.FillType.INVERSE_WINDING);
        path2.addCircle(point2.x + 200, point2.y + 200, 100, Path.Direction.CCW);   //

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         *
         * 把参数换成 path ，所以能裁切的形状更多一些
         *
         * */
        canvas.save() ;
        canvas.clipPath(path1) ;
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();


        canvas.save() ;
        canvas.clipPath(path2) ;
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
