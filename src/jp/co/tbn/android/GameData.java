package jp.co.tbn.android;

import java.io.Serializable;

public class GameData implements Serializable{
	
	private int soundNum;
	private int graphicNum;
	
	
	public int getSoundNum() {
		return soundNum;
	}
	public void setSoundNum(int soundNum) {
		this.soundNum = soundNum;
	}
	public int getGraphicNum() {
		return graphicNum;
	}
	public void setGraphicNum(int graphicNum) {
		this.graphicNum = graphicNum;
	}

}
