import java.awt.image.BufferedImage;


public class Textures {
	public BufferedImage player,bullet;
	public BufferedImage[] enemy=new BufferedImage[3];
private SpriteSheet ss;

public Textures(Game game){
	 ss=new SpriteSheet(game.getSpriteSheet());

	 getTextures();
}
	 private void getTextures(){
		 player=ss.grabImage(2, 1, 32, 32);
		 bullet=ss.grabImage(3, 1, 32, 32);
		 enemy[0]=ss.grabImage(4, 1, 32, 32);
		 enemy[1]=ss.grabImage(5, 1, 32, 32);
		 enemy[2]=ss.grabImage(6, 1, 32, 32);
	 }

}
