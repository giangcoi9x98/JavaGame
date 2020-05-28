/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Setting;

/**
 *
 * @author Giang
 */
public class SettingController {

    private SettingController() {
        setting = new Setting();
        setting = setting.loadOldSetting();
    }

    public static SettingController getInstance() {
        return SettingControllerHolder.INSTANCE;
    }

    public void loadOldSetting() {
        setting = new Setting();
        setting = setting.loadOldSetting();
    }

    public int getBulletSpeed() {
        return setting.getBulletSpeed();
    }

    public int getKeyPause() {
        return setting.getKeyPause();
    }

    public int getKeyFire() {
        return setting.getKeyFire();
    }

    public int getKeyDown() {
        return setting.getKeyDown();
    }

    public int getKeyUp() {
        return setting.getKeyUp();
    }

    public int getKeyRight() {
        return setting.getKeyRight();
    }

    public int getKeyLeft() {
        return setting.getKeyLeft();
    }

    public long getGameSpeed() {
        return setting.getGameSpeed();
    }

    public void setLevel(int MEDIUM) {
        this.setting.setLevel(MEDIUM);
    }

    public void setKeyUp(int keycode) {
        setting.setKeyUp(keycode);
    }

    public void setKeyPause(int keycode) {
        setting.setKeyPause(keycode);
    }

    public void setKeyFire(int keycode) {
        setting.setKeyFire(keycode);
    }

    public void setKeyRight(int keycode) {
        setting.setKeyRight(keycode);
    }

    public void setKeyLeft(int keycode) {
        setting.setKeyLeft(keycode);
    }

    public void setKeyDown(int keycode) {
        setting.setKeyDown(keycode);
    }

    long getPlayerFireRate() {
        return setting.getPlayerFireRate();
    }

    long getEnemyFireRate() {
        return setting.getEnemyFireRate();
    }

    public int getLevel() {
        return setting.getLevel();
    }

    public void saveSetting() {
        setting.saveSetting();
    }

    public long getHighScore() {
        return setting.getHighScore();
    }

    public void setHighScore(long hs) {
        setting.setHighScore(hs);
        setting.saveSetting();
    }
   public String getMap(){
       return setting.getMap();
   }
   public void setMap(String map){
       setting.setMap(map);
       setting.saveSetting();
   }
    private static class SettingControllerHolder {

        private static final SettingController INSTANCE = new SettingController();
    }

    Setting setting;

}
