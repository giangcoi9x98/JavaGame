/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.sun.glass.events.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiennd
 */
public class Setting implements Serializable {

    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int HARD = 3;
    public static final int LEGEND = 4;
    public int level = EASY;

    public int playerFireRate = 1000;
    public int gameSpeed = 20;
    public int enemyFireRate = 1000;
    public int bulletSpeed = 1;

    public int keyLeft = KeyEvent.VK_LEFT;
    public int keyRight = KeyEvent.VK_RIGHT;
    public int keyUp = KeyEvent.VK_UP;
    public int keyDown = KeyEvent.VK_DOWN;
    public int keyFire = KeyEvent.VK_SPACE;
    public int keyPause = KeyEvent.VK_P;
    public long highScore = 0;
    public String map="/map/map1.txt";

    public long getHighScore() {
        return highScore;
    }
    
    public void setHighScore(long highScore) {
        this.highScore = highScore;
    }
    public String getMap(){
        return map;
    }
    public void setMap(String map){
        this.map = map;
    }
    public final String SETTING_FILE_PATH = "/setting";

    File settingFile = new File(SETTING_FILE_PATH);

    public Setting() {

    }

    public Setting(Setting setting) {

    }

    public void saveSetting() {
        setupLevel();
        checkFileExist();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(settingFile));
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPlayerFireRate() {
        return playerFireRate;
    }

    public void setPlayerFireRate(int playerFireRate) {
        this.playerFireRate = playerFireRate;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public int getEnemyFireRate() {
        return enemyFireRate;
    }

    public void setEnemyFireRate(int enemyFireRate) {
        this.enemyFireRate = enemyFireRate;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public int getKeyLeft() {
        return keyLeft;
    }

    public void setKeyLeft(int keyLeft) {
        this.keyLeft = keyLeft;
    }

    public int getKeyRight() {
        return keyRight;
    }

    public void setKeyRight(int keyRight) {
        this.keyRight = keyRight;
    }

    public int getKeyUp() {
        return keyUp;
    }

    public void setKeyUp(int keyUp) {
        this.keyUp = keyUp;
    }

    public int getKeyDown() {
        return keyDown;
    }

    public void setKeyDown(int keyDown) {
        this.keyDown = keyDown;
    }

    public int getKeyFire() {
        return keyFire;
    }

    public void setKeyFire(int keyFire) {
        this.keyFire = keyFire;
    }

    public int getKeyPause() {
        return keyPause;
    }

    public void setKeyPause(int keyPause) {
        this.keyPause = keyPause;
    }

    public File getSettingFile() {
        return settingFile;
    }

    public void setSettingFile(File settingFile) {
        this.settingFile = settingFile;
    }

    public Setting loadOldSetting() {
        checkFileExist();
        Setting oldSetting;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(settingFile));
            oldSetting = (Setting) in.readObject();
            in.close();
        } catch (Exception e) {
            oldSetting = this;
        }
        return oldSetting;
    }

    private void checkFileExist() {
        if (!settingFile.exists()) {
            try {
                settingFile.createNewFile();

            } catch (IOException ex) {
                Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println(settingFile.getAbsolutePath());
        }
    }

    public void restoreDefaultSetting() {
        level = EASY;

        playerFireRate = 1000;
        gameSpeed = 20;
        enemyFireRate = 1000;
        bulletSpeed = 2;

        keyLeft = KeyEvent.VK_LEFT;
        keyRight = KeyEvent.VK_RIGHT;
        keyUp = KeyEvent.VK_UP;
        keyDown = KeyEvent.VK_DOWN;
        keyFire = KeyEvent.VK_SPACE;

    }

    public void setupLevel() {
        switch (level) {
            case Setting.EASY:
                playerFireRate = 1000;
                gameSpeed = 20;
                enemyFireRate = 1500;
                bulletSpeed = 2;
                break;
            case Setting.MEDIUM:
                playerFireRate = 1000;
                gameSpeed = 15;
                enemyFireRate = 1000;
                bulletSpeed = 2;
                break;
            case Setting.HARD:
                playerFireRate = 1000;
                gameSpeed = 10;
                enemyFireRate = 1000;
                bulletSpeed = 1;
                break;
            case Setting.LEGEND:
                playerFireRate = 500;
                gameSpeed = 10;
                enemyFireRate = 500;
                bulletSpeed = 1;
                break;
        }
    }

    @Override
    public String toString() {
        return "Setting{"
                + "\n\tplayerFireRate=" + playerFireRate
                + "\n\tgameSpeed=" + gameSpeed
                + "\n\tenemyFireRate=" + enemyFireRate
                + "\n\tbulletSpeed=" + bulletSpeed
                + "\n\tkeyLeft=" + keyLeft
                + "\n\tkeyRight=" + keyRight
                + "\n\tkeyUp=" + keyUp
                + "\n\tkeyDown=" + keyDown
                + "\n\tkeyFire=" + keyFire
                + "\n\tsettingFile=" + settingFile.getAbsolutePath()
                + "\n}";
    }

}
