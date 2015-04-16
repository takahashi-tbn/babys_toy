package jp.co.tbn.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Menu003 extends Activity {
	private String[] data = {
			 "アニメーション１"
			,"アニメーション２"
			,"アニメーション３"}; 
	  
	int soundNo;
	int graphicNo;
	private GameData gameData = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu003);

        //ゲームデータを取得
        Intent intent = getIntent();
        gameData = (GameData)intent.getSerializableExtra("GAME_DATA");

		
		ArrayAdapter<CharSequence> adapter
			= new ArrayAdapter<CharSequence>(Menu003.this, android.R.layout.simple_list_item_1, data);
		ListView listView = (ListView)findViewById(R.id.ListView01);
		listView.setAdapter(adapter);
        
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Intent intent = new Intent(Menu003.this, (Class<?>)Menu003.class);
				intent.setAction(Intent.ACTION_VIEW); 
			
				//選択画像を格納
				gameData.setGraphicNum(position);
				intent.putExtra("GAME_DATA",gameData);
				
				startActivity(intent);
			}
			
		});
    }
}