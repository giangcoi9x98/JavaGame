package views;

import controllers.GameController;
import controllers.SettingController;
import models.MyTank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    private MainMenuFrame mainMenuFrame;
    private GamePlayFrame gamePlayFrame;
    private GameController managerItem;
    private Thread thread;
    private boolean isLeft;
    private boolean isRight;
    private boolean isUp;
    private boolean isDown;
    private boolean isFire;
    private boolean pause = false;
 
    
    public GamePanel() {
        ///
        
        setSize(GamePlayFrame.WIDTH_FRAME, GamePlayFrame.HEIGHT_FRAME);
        setLocation(0, 0);
        
        setBackground(Color.BLACK);
        managerItem = new GameController();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == SettingController.getInstance().getKeyLeft()) {
                    isLeft = true;
                } else if (key == SettingController.getInstance().getKeyRight()) {
                    isRight = true;
                } else if (key == SettingController.getInstance().getKeyUp()) {
                    isUp = true;
                } else if (key == SettingController.getInstance().getKeyDown()) {
                    isDown = true;
                } else if (key == SettingController.getInstance().getKeyFire()) {
                    isFire = true;
                } else if (key == SettingController.getInstance().getKeyPause()) {
                    pause = !pause;
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == SettingController.getInstance().getKeyLeft()) {
                    isLeft = false;
                } else if (key == SettingController.getInstance().getKeyRight()) {
                    isRight = false;
                } else if (key == SettingController.getInstance().getKeyUp()) {
                    isUp = false;
                } else if (key == SettingController.getInstance().getKeyDown()) {
                    isDown = false;
                } else if (key == SettingController.getInstance().getKeyFire()) {
                    isFire = false;
                };
            }
            
        }
        );
        
        setRequestFocusEnabled(true);
        setFocusable(true);
        
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // noi de ve
        // Graphics la but ve

        Graphics2D g2d = (Graphics2D) g;
        
        managerItem.drawMyTank(g2d);
        managerItem.drawEnemyAllTank(g2d);
        managerItem.drawBulletOfMyTank(g2d);
        managerItem.drawAllBulletEnemyTank(g2d);
        managerItem.drawAll(g2d);
        
    }
    int count = 0;
    
    @Override
    public void run() {
        //chay khi goi thread.start
        count = 0;
        long hs = SettingController.getInstance().getHighScore();
        while (true) {
            try {
                Thread.sleep(SettingController.getInstance().getGameSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (pause) {
                continue;
            }
            count += SettingController.getInstance().getGameSpeed();
         
            
            System.out.println(count);
            moveMyTank();
            moveBulletOfTank();
            fireOfMyTank();
            managerItem.interactBulletOfMyTank();
            managerItem.moveAllEnemyTank();
            managerItem.moveAllBulletEnemyTank();
            managerItem.fireEnemyTank();
            managerItem.interactBulletOfAllEnemyTank();
            managerItem.killEnemyTank();
            if (managerItem.checkGameOver()) {
                
                int ans = JOptionPane.showConfirmDialog(this, "You Lose Try Again !", "TITLE", JOptionPane.OK_CANCEL_OPTION);
                
                switch (ans) {
                    case JOptionPane.OK_OPTION:
                        System.out.println("ok pressed");
                  
                        System.out.println("high Score :" + SettingController.getInstance().getHighScore());
                        gamePlayFrame.replay();
                        
                        break;
                    case JOptionPane.CANCEL_OPTION:
                        System.out.println("cancel pressed");
                        
                        System.out.println("high Score :" + SettingController.getInstance().getHighScore());
                        gamePlayFrame.setVisible(false);
                        gamePlayFrame.mainMenuFrame.setVisible(true);
                        
                        break;
                }
                
                break;
                
            }
            if (managerItem.checkWin()) {
                if(count<hs){
                SettingController.getInstance().setHighScore(count);}
            int ans = JOptionPane.showConfirmDialog(this, "You WIn!Play  Again !", "TITLE", JOptionPane.OK_CANCEL_OPTION);
                
                switch (ans) {
                    case JOptionPane.OK_OPTION:
                        System.out.println("ok pressed");
                        System.out.println("count" + count);
                        System.out.println("high Score :" + SettingController.getInstance().getHighScore());
                        
                        gamePlayFrame.replay();
                        
                        break;
                    case JOptionPane.CANCEL_OPTION:
                        System.out.println("cancel pressed");
                         System.out.println("count" + count);
                        System.out.println("high Score :" + SettingController.getInstance().getHighScore());
                        gamePlayFrame.setVisible(false);
                        gamePlayFrame.mainMenuFrame.setVisible(true);
                        
                        break;
                }
                
                break;
            }
            repaint();
        }
        
    }
    
    public void setGameFrame(GamePlayFrame gamePlayFrame) {
        this.gamePlayFrame = gamePlayFrame;
    }
    
    private void moveBulletOfTank() {
        managerItem.moveBulletOfMyTank();
        
    }
    
    void fireOfMyTank() {
        if (isFire) {
            managerItem.fireBullet();
        }
    }
    
    void moveMyTank() {
        if (isDown) {
            managerItem.moveMyTank(MyTank.DOWN);
            
        } else {
            if (isUp) {
                managerItem.moveMyTank(MyTank.UP);
                
            } else {
                if (isRight) {
                    managerItem.moveMyTank(MyTank.RIGHT);
                    
                } else {
                    if (isLeft) {
                        managerItem.moveMyTank(MyTank.LEFT);
                        
                    }
                    
                }
            }
        }
        
    }
    
    void setPause(boolean b) {
        pause = b;
    }
}
