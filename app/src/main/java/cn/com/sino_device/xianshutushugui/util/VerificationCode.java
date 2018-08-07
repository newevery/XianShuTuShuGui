package cn.com.sino_device.xianshutushugui.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by Android Studio.
 * Date: 2018/5/25
 * Time: 11:38
 *
 * @author affe
 */
public class VerificationCode {

    /**
     * 用于生成验证码的字符
     */
    private static final char[] CHARS = {
            /* 数字 */
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            /* 小写字母 */
            //'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            //'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            /* 大写字母 */
            //'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            //'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    /**
     * 验证码默认长度
     */
    private static final int DEFAULT_CODE_LENGTH = 4;
    /**
     * 验证码默认字体大小
     */
    private static final int DEFAULT_FONT_SIZE = 24;
    /**
     * 默认干扰线条数
     */
    private static final int DEFAULT_LINE_NUMBER = 2;
    /**
     * 验证码默认padding
     */
    private static final int
            BASE_PADDING_LEFT = 15, RANGE_PADDING_LEFT = 20,
            BASE_PADDING_TOP = 25, RANGE_PADDING_TOP = 20;
    /**
     * 默认宽高
     */
    private static final int DEFAULT_WIDTH = 144, DEFAULT_HEIGHT = 48;

    /**
     * Canvas画布的宽高
     */
    private int width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;

    /**
     * 内边距
     */
    private int
            basePaddingLeft = BASE_PADDING_LEFT, rangePaddingLeft = RANGE_PADDING_LEFT,
            basePaddingTop = BASE_PADDING_TOP, rangePaddingTop = RANGE_PADDING_TOP;

    /**
     * 验证码的长度、干扰线条数、字体大小
     */
    private int
            codeLength = DEFAULT_CODE_LENGTH,
            lineNumber = DEFAULT_LINE_NUMBER,
            fontSize = DEFAULT_FONT_SIZE;

    /**
     *
     */
    private String code;
    private int paddingLeft, paddingTop;
    private Random random = new Random();

    private static VerificationCode mVerificationCode;

    public static VerificationCode getInstance() {
        if (mVerificationCode == null) {
            mVerificationCode = new VerificationCode();
        }
        return mVerificationCode;
    }

    public String getCode() {
        return code;
    }

    /**
     * 创建Bitmap
     *
     * @return
     */
    public Bitmap createBitmap() {
        paddingLeft = 0;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setTextSize(fontSize);

        code = generateCode();
        // 绘制验证码
        for (int i = 0; i < code.length(); i++) {
            randomStyle(paint);
            randomPadding();
            canvas.drawText(code.charAt(i) + "", paddingLeft, paddingTop, paint);
        }
        // 绘制干扰线
        for (int i = 0; i < lineNumber; i++) {
            drawLine(canvas, paint);
        }

        canvas.save();
        canvas.restore();
        return bitmap;
    }

    /**
     * 生成验证码
     *
     * @return
     */
    private String generateCode() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    /**
     * 画线
     *
     * @param canvas
     * @param paint
     */
    private void drawLine(Canvas canvas, Paint paint) {
        int color = randomColor(1);
        int startX = random.nextInt(width);
        int startY = random.nextInt(height);
        int stopX = random.nextInt(width);
        int stopY = random.nextInt(height);
        paint.setStrokeWidth(1);
        paint.setColor(color);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    /**
     * 颜色
     *
     * @param rate
     * @return
     */
    private int randomColor(int rate) {
        int red = random.nextInt(256) / rate;
        int green = random.nextInt(256) / rate;
        int blue = random.nextInt(256) / rate;
        return Color.rgb(red, green, blue);
    }

    /**
     * 样式
     *
     * @param paint
     */
    private void randomStyle(Paint paint) {
        int color = randomColor(1);
        paint.setColor(color);
        // 粗体
        paint.setFakeBoldText(random.nextBoolean());
        float skewX = random.nextInt(11) / 10;
        skewX = random.nextBoolean() ? skewX : -skewX;
        // 倾斜
        paint.setTextSkewX(skewX);
        // 下划线
        //paint.setUnderlineText(true);
        // 删除线
        //paint.setStrikeThruText(true);
    }

    /**
     * 内边距
     */
    private void randomPadding() {
        paddingLeft += basePaddingLeft + random.nextInt(rangePaddingLeft);
        paddingTop = basePaddingTop + random.nextInt(rangePaddingTop);
    }

}
