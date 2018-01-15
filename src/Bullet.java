import java.awt.Graphics;
import java.awt.Rectangle;

import classes.EntityA;


public class Bullet extends GameObject implements EntityA{


private Textures t;
@SuppressWarnings("unused")
private Game game;
public Bullet(double x,double y,Textures t,Game game){
	super(x,y);
	this.t=t;
	this.game=game;
	
}

public void tick(){
	x+=10;
}

public void render(Graphics g){
	g.drawImage(t.bullet, (int)x,(int) y, 100, 100,null);
}

public double getX() {
	return x;
}
public Rectangle getBounds(){
	return new Rectangle((int)x,(int)y,32,32);}



@Override
public double getY() {
	return y;
}


}
