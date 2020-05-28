package views;

import controllers.GameController;
import controllers.SettingController;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.net.URL;
import utils.BackToMain;

public class GamePlayFrame extends JFrame {

    public static final int WIDTH_FRAME = 29 * 20;
    public static final int HEIGHT_FRAME = 31 * 20;
    GamePanel gamePanel;
  
    MainMenuFrame mainMenuFrame;

    public GamePlayFrame(MainMenuFrame mainMenuFrame) {
        this.mainMenuFrame = mainMenuFrame;
        setTitle("Game Tank");
        URL icon = this.getClass().getResource("/images/bird.png");
        setIconImage(new ImageIcon(icon).getImage());
        //set kich cho khung tranh
        setSize(WIDTH_FRAME, HEIGHT_FRAME);

        //show ra giua man hinh
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        init();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new BackToMain() {
            @Override
            public void windowClosing(WindowEvent we) {
                gamePanel.setPause(true);
                onClosed();
            }
        });
    }

    public void onClosed() {
        int retVal = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm to exit", JOptionPane.OK_CANCEL_OPTION);
        switch (retVal) {
            case JOptionPane.OK_OPTION:
                this.setVisible(false);
                mainMenuFrame.setVisible(true);
                break;
            case JOptionPane.CANCEL_OPTION:
                gamePanel.setPause(false);
                this.setVisible(true);
                break;
            default:
                gamePanel.setPause(false);

        }
    }

    private void init() {
        gamePanel = new GamePanel();
        gamePanel.setGameFrame(this);
        add(gamePanel);
       
    }

    public void replay() {
        dispose();
        GamePlayFrame gui = new GamePlayFrame(mainMenuFrame);
        this.setResizable(false);
        gui.setVisible(true);
        this.setVisible(false);
    }

}
