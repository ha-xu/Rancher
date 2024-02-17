package view;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    private final Image farmerImage;
    private Image wolfImage;
    private Image sheepImage;


    public GamePanel(){
        //set background color
        this.setBackground(Color.green);
        ImageIcon icon = new ImageIcon("src/images/farmerImage.png");
        farmerImage = icon.getImage();

        //set size
        this.setSize(GameFrame.WIDTH, GameFrame.HEIGHT);

        //set background color
        float [] hsb = new float[3];
        Color.RGBtoHSB(167, 238, 145, hsb);
        this.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(farmerImage, 12, 12, this);
        //log
        System.out.println("paintComponent");
    }
}