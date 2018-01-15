import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;



public class Menu {
	Rectangle playButton=new Rectangle(Game.WIDTH/2-50,250,150,70);
	Rectangle quitButton=new Rectangle(Game.WIDTH/2+220,250,150,70);

public void render(Graphics g){
Graphics2D g2D=(Graphics2D) g;
Font fnt1=new Font("arial",Font.BOLD,40);
g.setColor(Color.white);
g.setFont(fnt1);
g.drawString("PLAY",playButton.x+25,playButton.y+50);
g2D.draw(playButton);
g.drawString("QUIT",quitButton.x+25,quitButton.y+50);
g2D.draw(quitButton);
}
}
