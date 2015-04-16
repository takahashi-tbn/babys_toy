package jp.co.tbn.android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

/** 描画用のView */
class GameView extends SurfaceView implements SurfaceHolder.Callback {
	private GameThread mainLoop;

	/** ゲームデータ */
	protected GameData gameData;
	public void setGameData(GameData gameData){
		this.gameData = gameData;
	}
	public GameData getGameData(){
		return this.gameData;
	}

	/** 画面サイズ */
	private int dispX = 480;
	private int dispY = 640;

	/** キャンバスサイズ */
	private int mCanvasHeight = 1;
    private int mCanvasWidth = 1;

    public int getCanvasHeight() {
		return mCanvasHeight;
	}
	public int getCanvasWidth() {
		return mCanvasWidth;
	}

	/** 画面表示用Bitmapクラス */
	private GameBitmap bitmap;
    public GameBitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(GameBitmap bitmap) {
		this.bitmap = bitmap;
	}

	/** タイマー */
    long ScriptTickCount = 0;
    long OldTickCount = 0;

    /** リソース */
	private Resources r;
    public Resources getR() {
		return r;
	}
	public void setR(Resources r) {
		this.r = r;
	}


    private int frame = 0;

    private TextView mStatusText;
    /** Handle to the surface manager object we interact with */
    private SurfaceHolder mSurfaceHolder;
    /** Message handler used by thread to interact with TextView */
    private Handler mHandler;
    /** Handle to the application context, used to e.g. fetch Drawables. */
    private Context mContext;
    /** Indicate whether the surface has been created & is ready to draw */
    private boolean mRun = false;


 //   public GameView(Context context, AttributeSet attrs) {
      public GameView(Context context) {
    	super(context);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        // create thread only; it's started in surfaceCreated()
        mainLoop = new GameThread(holder, context, new Handler() {
            @Override
            public void handleMessage(Message m) {
                mStatusText.setVisibility(m.getData().getInt("viz"));
                mStatusText.setText(m.getData().getString("text"));
            }
        });
        setFocusable(true); // make sure we get key events
    }
    // 画面サイズが変更されたときに呼び出されるメソッド
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        dispX = w;
        dispY = h;
    }


    // タッチイベントの処理
    public boolean onTouchEvent(MotionEvent event) {
		return mRun;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		mainLoop.setSurfaceSize(width, height);

	}
	public void surfaceCreated(SurfaceHolder arg0) {
		mainLoop.setRunning(true);
		mainLoop.start();

	}
	public void surfaceDestroyed(SurfaceHolder arg0) {
        boolean retry = true;
        mainLoop.setRunning(false);
        while (retry) {
            try {
            	mainLoop.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }

	}
    /**
     * Draws the ship, fuel/speed bars, and background to the provided
     * Canvas.
     */
    public void doDraw(Canvas canvas) {
    }


	/** 画面描画用スレッドクラス（内部クラス */
	class GameThread extends Thread {

		public GameThread(SurfaceHolder surfaceHolder, Context context,Handler handler) {

			// get handles to some important objects
            mSurfaceHolder = surfaceHolder;
            mHandler = handler;
            mContext = context;

	        r = context.getResources();

	        //タイマー初期化
	    	ScriptTickCount=OldTickCount=0;
		}

        /**
         * Starts the game, setting parameters for the current difficulty.
         */
        public void doStart() {

        }

        /**
         * Pauses the physics update & animation.
         */
        public void pause() {
        }

        /**
         * Restores game state from the indicated Bundle. Typically called when
         * the Activity is being restored after having been previously
         * destroyed.
         *
         * @param savedState Bundle containing the game state
         */
        public synchronized void restoreState(Bundle savedState) {
        }

        /**
         * Dump game state to the provided Bundle. Typically called when the
         * Activity is being suspended.
         *
         * @return Bundle with this view's state
         */
        public Bundle saveState(Bundle map) {
            return map;
        }

        /**
         * Sets the current difficulty.
         *
         * @param difficulty
         */
        public void setDifficulty(int difficulty) {
        }

        /**
         * Sets if the engine is currently firing.
         */
        public void setFiring(boolean firing) {
        }

        /**
         * Used to signal the thread whether it should be running or not.
         * Passing true allows the thread to run; passing false will shut it
         * down if it's already running. Calling start() after this was most
         * recently called with false will result in an immediate shutdown.
         *
         * @param b true to run, false to shut down
         */
        public void setRunning(boolean b) {
            mRun = b;
        }

        /**
         * Sets the game mode. That is, whether we are running, paused, in the
         * failure state, in the victory state, etc.
         *
         * @see #setState(int, CharSequence)
         * @param mode one of the STATE_* constants
         */
        public void setState(int mode) {
            synchronized (mSurfaceHolder) {
                setState(mode, null);
            }
        }

        /**
         * Sets the game mode. That is, whether we are running, paused, in the
         * failure state, in the victory state, etc.
         *
         * @param mode one of the STATE_* constants
         * @param message string to add to screen or null
         */
        public void setState(int mode, CharSequence message) {
            /*
             * This method optionally can cause a text message to be displayed
             * to the user when the mode changes. Since the View that actually
             * renders that text is part of the main View hierarchy and not
             * owned by this thread, we can't touch the state of that View.
             * Instead we use a Message + Handler to relay commands to the main
             * thread, which updates the user-text View.
             */
        }

        /* Callback invoked when the surface dimensions change. */
        public void setSurfaceSize(int width, int height) {
            // synchronized to make sure these all change atomically
            synchronized (mSurfaceHolder) {
                mCanvasWidth = width;
                mCanvasHeight = height;

                // don't forget to resize the background image
                bitmap.setBmpBackground(
                	bitmap.getBmpBackground().createScaledBitmap(bitmap.getBmpBackground(), width, height, true)
                );
            }
        }

        /**
         * Resumes from a pause.
         */
        public void unpause() {
      }

        /**
         * Handles a key-down event.
         *
         * @param keyCode the key that was pressed
         * @param msg the original event object
         * @return true
         */
        boolean doKeyDown(int keyCode, KeyEvent msg) {

                return false;
        }

        /**
         * Handles a key-up event.
         *
         * @param keyCode the key that was pressed
         * @param msg the original event object
         * @return true if the key was handled and consumed, or else false
         */
        boolean doKeyUp(int keyCode, KeyEvent msg) {
            boolean handled = false;

            return handled;
        }


        @Override
        public void run() {
            while (mRun) {
                Canvas c = null;
                try {
                	//canvasロック
                    c = mSurfaceHolder.lockCanvas(null);
                    synchronized (mSurfaceHolder) {
                    	//描画処理
                        doDraw(c);
                    }
                } finally {
                	//canvas開放
                    if (c != null) {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
}