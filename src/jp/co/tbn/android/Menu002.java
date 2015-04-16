package jp.co.tbn.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class Menu002 extends Activity implements OnCheckedChangeListener{
	private String[] data = {
			 "ガラガラ"
			,"ピコピコ"
			,"パパッ"};

	private GameData gameData = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu002);

        RadioGroup radiogroup = (RadioGroup)this.findViewById(R.id.radiogroup);

        //ラジオボタンのチェンジイベントを取得できるようにする。
        radiogroup.setOnCheckedChangeListener(this);

    	//ゲームデータを取得
        Intent intent = getIntent();
        gameData = (GameData)intent.getSerializableExtra("GAME_DATA");

        int soundId = GameSound.soundIdList[gameData.getSoundNum()];
       	//ラジオボタンにチェックを入れる
        radiogroup.check(soundId);


        //決定ボタンの動作設定
        Button btn = (Button)this.findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Intent intent = new Intent(Menu002.this, Menu001.class);
        		intent.setAction(Intent.ACTION_VIEW);
            	intent.putExtra("GAME_DATA",gameData);
        		startActivity(intent);
        	}
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId){

    	int num = 0;
    	for(int i = 0; i < GameSound.soundIdList.length; i++){
    		if(checkedId == GameSound.soundIdList[i]){
    			break;
    		}
    		num++;
    	}
    	//効果音変更
   		gameData.setSoundNum(num);

    	Toast.makeText(this, "Selected  "+ String.valueOf(gameData.getSoundNum()),Toast.LENGTH_SHORT).show();


    }
}