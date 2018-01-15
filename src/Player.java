import java.awt.Graphics;
import java.awt.Rectangle;

import classes.EntityA;
import classes.EntityB;



public class Player extends GameObject implements EntityA{
	
	
	private double velX;
	private double velY;
	private Textures t;
	Game game;
	Controller c;
	Player(double x,double y,Textures t,Game game,Controller c){
		super(x,y);
		this.t=t;
		this.game=game;
		this.c =c;
		
		
	}
	@SuppressWarnings("static-access")
	public void tick(){
	x+=velX;
	y+=velY;
	if(x<=-24) x=-24;
	if(x>=550) x=550;
	if(y<=0) y=0;
	if(y>=390) y=390;
	
	
	if(Game.STAMINA>0){
	for(int i=0;i<game.eb.size();i++){
		EntityB tempEnt=game.eb.get(i);
		
		if(Physics.Collision(this, tempEnt)){
			c.removeEntity(tempEnt);
			Game.STAMINA-=10;
			game.setEnemy_killed(game.getEnemy_killed()+1);
		}
	}
	}
	else{
		game.state=Game.STATE.END;
	}
		
	}
	
	
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	public void render(Graphics g){
		g.drawImage(t.player, (int) getX(), (int) getY(), 100, 100, null);
		
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,32,32);
		}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
}
