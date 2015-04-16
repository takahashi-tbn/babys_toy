package jp.co.tbn.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;
import android.view.MotionEvent;

/** 描画用のView */
class GameView001 extends GameView {

    //ゲームメイン変数-----------------
    //画像管理クラス
    private GameBitmap001 bitmap;
    private GameSound sound;
    private Vibrator vibrator;

    /** canvasの表示角度 */
    float rotateAngle = 0.0f;
    float rotateSpeed = 1.0f;

    private int frame = 0;


 //   public GameView(Context context, AttributeSet attrs) {
      public GameView001(Context context, GameData gameData) {
    	super(context);

    	//ゲームデータを保管
    	setGameData(gameData);

        //Bitmap画像一括初期化
        bitmap = new GameBitmap001(getR());
        setBitmap(bitmap);

        //サウンド初期化
        sound = new GameSound(context, gameData.getSoundNum());

        // バイブレータ
        vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);

    }

    // タッチイベントの処理
    public boolean onTouchEvent(MotionEvent event) {
        int tx = (int)event.getX();
        int ty = (int)event.getY();

        String action = "";
        switch (event.getAction()) {
        	case MotionEvent.ACTION_DOWN:
        		action = "ACTION_DOWN";
        		rotateAngle = 0.0f;
                rotateSpeed = 25.0f;
        		break;
        	case MotionEvent.ACTION_UP:
        		rotateAngle = 0.0f;
                rotateSpeed = 1.0f;
        		action = "ACTION_UP";
        		break;
        	case MotionEvent.ACTION_MOVE:
        		action = "ACTION_MOVE";
        		break;
        	case MotionEvent.ACTION_CANCEL:
        		action = "ACTION_CANCEL";
        		break;
        }


        return true;
	}


    /**
     * Draws the ship, fuel/speed bars, and background to the provided
     * Canvas.
     */
    public void doDraw(Canvas canvas) {

        // 画面を黒で初期化
        canvas.drawColor(Color.BLACK);

        canvas.save();
        canvas.rotate(rotateAngle,getCanvasWidth()/2,getCanvasHeight()/2);

        //メインアイテム描画
        canvas.drawBitmap(bitmap.getBmpMainItem(), 0, 0, null);
        canvas.restore();

        //角度変化
        rotateAngle += rotateSpeed;
        if(Math.abs(rotateAngle) > 30.0f){

        	//端まで来たら効果音と振動が発生
        	sound.getGragraPlayer().seekTo(0);
        	sound.getGragraPlayer().start();

        	vibrator.vibrate(30);

        	//折り返し
        	rotateSpeed *= -1;
        }

        //背景描画
//        canvas.drawBitmap(bitmap.getBmpBackground(), 0, 0, null);


        // Message の描画
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        String msg = "Frame:" + (frame++);
        canvas.drawText(msg, 2, 30, paint);

    }
}