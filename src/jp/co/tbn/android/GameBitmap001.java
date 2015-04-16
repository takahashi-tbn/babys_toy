package jp.co.tbn.android;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameBitmap001 extends GameBitmap{
	
	private Resources r;
	private Bitmap bmpMainItem;

	public GameBitmap001(Resources r){
		this.r = r;

		//���C���A�C�e��
		setBmpMainItem(BitmapFactory.decodeResource(this.r, R.drawable.bitmap001_01));

		//�w�i
		setBmpBackground(BitmapFactory.decodeResource(this.r, R.drawable.bitmap001_01));

	}
	
	public void setBmpMainItem(Bitmap bmp) {
		this.bmpMainItem = bmp;
	}
	public Bitmap getBmpMainItem() {
		return bmpMainItem;
	}
	

}
