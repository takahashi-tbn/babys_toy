package jp.co.tbn.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameMain  extends Activity {
	private GameData gameData = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ゲームデータを取得
        Intent intent = getIntent();
        gameData = (GameData)intent.getSerializableExtra("GAME_DATA");


        View mainView = new GameView001(getApplication(), gameData);

        setContentView(mainView);

    }
}