package jp.co.tbn.android;

import java.io.IOException;
import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RecAudioFile extends Activity {
	private Button mExecuteButton;
	private MediaRecorder mRecorder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rec_audio_file);

		// MediaRecorderのインスタンスを作成
		mRecorder = new MediaRecorder();
		// 入力ソースをマイクに設定
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		// 記録フォーマットを3GPPに設定
		mRecorder.setOutputFormat(
				MediaRecorder.OutputFormat.THREE_GPP);
		// 音声コーデックをAMR-NBに設定
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		// 出力ファイルのパスを指定
		mRecorder.setOutputFile(
				GameSound.saveFile);
		try {
			// レコーダーを準備
			mRecorder.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 録音を開始
		mRecorder.start();

		this.mExecuteButton =
			(Button) findViewById(R.id.execute_button);
		mExecuteButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					// 録音を停止
					mRecorder.stop();
					// 再使用に備えてレコーダーの状態をリセット
					mRecorder.reset();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 使わなくなった時点でレコーダーリソースを解放する
		mRecorder.release();
	}
}
