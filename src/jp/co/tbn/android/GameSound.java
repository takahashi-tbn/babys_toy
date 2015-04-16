package jp.co.tbn.android;

import android.content.Context;
import android.media.MediaPlayer;

public class GameSound {

	//ガラガラ音リスト
	public static final int soundIdList[] = {
		R.id.radiobutton_first,
		R.id.radiobutton_second,
		R.id.radiobutton_third,
		R.id.radiobutton_fourth
	};

	//ガラガラ音リスト
	public static final int soundDataList[] = {
		R.raw.garagara01,
		R.raw.garagara02,
		R.raw.garagara03,
		R.raw.garagara03,
//		R.raw.user_garagara
	};

	public static final String saveFile = "/sdcard/babysToy/user_garagara.3gp";

	private MediaPlayer gragraPlayer;

	GameSound(Context context, int number){
		//効果音セット
		gragraPlayer = MediaPlayer.create(context, soundDataList[number]);
	}

	public MediaPlayer getGragraPlayer() {
		return gragraPlayer;
	}

	public void setGragraPlayer(MediaPlayer gragraPlayer) {
		this.gragraPlayer = gragraPlayer;
	}
}
