import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import classes.EntityA;
import classes.EntityB;


public class Enemy extends GameObject implements EntityB{

	
	
	@SuppressWarnings("unused")
	private Textures t;
	Random r=new Random();
	private int speed=r.nextInt(5)+1;
	Game game;
	Controller c;
	Animation anim;
	public Enemy(double x, double y,Textures t,Game game,Controller c) {
		super(x,y);
		this.t=t;
		this.game =game;
		this.c=c;
		
		anim=new Animation(5,t.enemy[0],t.enemy[1],t.enemy[2]);
	}
	
	public void tick(){
		x-=speed;;
		if(x<0){
			speed=r.nextInt(5)+1;
			x=550;
			y=r.nextInt((Game.HEIGHT*Game.SCALE)-64);
			Game.STAMINA-=5;
			
		}
		for(int i=0;i<game.ea.size();i++)
		{
			EntityA tempEnt=game.ea.get(i);
			if(Physics.Collision(this,tempEnt)){
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				Game.STAMINA+=2;
				game.setEnemy_killed(game.getEnemy_killed()+1);
				Game.SCORE++;
			}
		}
		
		
		anim.runAnimation();
	}
	
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,32,32);}
	
	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x=x;
		
	}

	public void setY(int y) {
		this.y=y;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}
	
	
}
