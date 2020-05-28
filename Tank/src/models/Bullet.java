package models;

import controllers.SettingController;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet extends Item {

    public static Image IMG_BULLET = new ImageIcon(
            Bullet.class.getResource("/images/bullet.png"))
            .getImage();

    int orientation;

    public Bullet(int id, int x, int y, int size, int orientation) {
        super(id, x, y, size);
        this.orientation = orientation;
    }

    @Override
    public void move() {
        int bulletSpeed = SettingController.getInstance().getBulletSpeed();
        switch (orientation) {
            case MyTank.LEFT:
                x = x - bulletSpeed;
                break;
            case MyTank.RIGHT:
                x = x + bulletSpeed;
                break;
            case MyTank.UP:
                y = y - bulletSpeed;
                break;
            case MyTank.DOWN:
                y = y + bulletSpeed;
                break;
            default:
                break;
        }

    }

}
