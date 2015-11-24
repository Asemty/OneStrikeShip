package ru.asemty.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import ru.asemty.lib.utils.LoadHelper;

public class SaveUnit implements Serializable {
	private static final long serialVersionUID = 6912496768252984946L;
	String key;
	String discription;
	boolean flag;
	static ArrayList<SaveUnit> units;
	static String savePath = System.getProperty("user.home")+"/Documents/My Games/"+GameConstatnts.gameName+"save";
	static String fileName = "/sav.file";
	static String configName = "/conf.cfg";

	public SaveUnit(String key, String discription) {
		this.key = key.substring(0, 5).toUpperCase();
		this.discription = discription;
		flag = false;
	}

	public static void saveUnitInit() {
		units = new ArrayList<SaveUnit>();
		addNewData("CHASR","THE QUICK AND THE DEAD");
		addNewData("SKNOT","And why not a rainbow?");
		addNewData("PLONG","Where my Geiger counter?");
		
		addNewData("SHPND","Press C to creane shield.Shield is immortal, but laser penetrates therethrough");
		addNewData("CHAND","Where my Geiger counter?");
		addNewData("SKNND","Where my Geiger counter?");
		addNewData("PLOND","Where my Geiger counter?");
	}
	public static void addNewData(String key, String discription) {
		SaveUnit temp=new SaveUnit(key, discription);
		for (SaveUnit su : units) {
			if (su.key.equals(temp.key)) {
				System.err.println("Date with this name already exists:"+temp.key);
				return;
			}
		}
		units.add(temp);
	}
	public static boolean isComplite(String keyWord) {
		for (SaveUnit su : units) {
			if (su.key.equals(keyWord.toUpperCase())) {
				return su.flag;
			}
		}
		System.err.println("Not found key word: " + keyWord);
		return false;
	}

	public static void setComplite(String keyWord) {
		for (SaveUnit su : units) {
			if (su.key.equals(keyWord.toUpperCase())) {
				su.flag = true;
				return;
			}
		}
		System.err.println("Not found key word: " + keyWord);
	}

	public static int getDataSize() {
		return units.size();
	}

	public static String getName(int i) {
		if (units.size() > i) {
			return units.get(i).flag ? units.get(i).key : "?????";
		} else {
			System.err.println("Index is larger then data size :" + i + " from " + units.size());
			return "";
		}
	}

	public static String getDiscription(int i) {
		if (units.size() > i) {
			return units.get(i).flag ? units.get(i).discription : "";
		} else {
			System.err.println("Index is larger then data size :" + i + " from " + units.size());
			return "";
		}
	}

	public static void saveData() {
		StringBuffer sb = new StringBuffer();
		for (SaveUnit su : units) {
			if (su.flag) {
				sb.append(su.key + '&');
			}
		}
		LoadHelper.saveObjectToFile(savePath+fileName, sb);
	}

	public static void loadData() {
		File f = new File(savePath+fileName);
		saveUnitInit();
		if (f.exists()) {
			Object obj = LoadHelper.loadObjectFromFile(savePath+fileName);
			if (obj instanceof StringBuffer) {
				StringBuffer sb = (StringBuffer) obj;
				String[] strArr = sb.toString().split("&");
				for (String s : strArr) {
					if(s.length()==5)
					setComplite(s);
				}
			}
		} else {
			saveData();
		}
	}

	public static void delData() {
		saveUnitInit();
		saveData();
	}
	
	public static void saveConfig() {
		File f=LoadHelper.castFileFromObject(savePath+configName);
		try {
			FileWriter fw= new FileWriter(f);
			fw.write(SpaceGame.isFullScreen+"\n");
			fw.write(GameState.masterVolume+"\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void loadConfig() {
		File f=LoadHelper.castFileFromObject(savePath+configName);
		if(f.exists())
		try {
			Scanner sc= new Scanner(f);
			SpaceGame.setFullScreen(sc.nextBoolean(), SpaceGame.isStretchScreen);
			GameState.masterVolume=sc.nextInt();
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
