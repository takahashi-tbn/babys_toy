package jp.co.tbn.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Menu001 extends Activity {
	private String[] data = {"START", "効果音選択", "効果音作成"};
	private GameData gameData = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu001);

    	//ゲームデータを取得
        Intent intent = getIntent();
        if(intent != null){
        	gameData = (GameData)intent.getSerializableExtra("GAME_DATA");
            if(gameData == null)gameData = new GameData();
        }


		ArrayAdapter<CharSequence> adapter
			= new ArrayAdapter<CharSequence>(Menu001.this, android.R.layout.simple_list_item_1, data);
		ListView listView = (ListView)findViewById(R.id.ListView01);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent intent = null;

				switch(position){
				case 0:
					//①ゲーム開始
					intent = new Intent(Menu001.this, GameMain.class);
					break;

				case 1:
					//②効果音選択メニューへ繊維
					intent = new Intent(Menu001.this, Menu002.class);
					break;

				case 2:
					//③効果音録音画面へ遷移
					intent = new Intent(Menu001.this, RecAudioFile.class);
					break;
				};

				//ゲームデータをセット
				intent.putExtra("GAME_DATA",gameData);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
			}

		});
    }
}