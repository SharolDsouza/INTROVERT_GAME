import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import classes.EntityA;
import classes.EntityB;


public class Controller {
	private LinkedList<EntityA> ea= new LinkedList<EntityA>();
	private LinkedList<EntityB> eb= new LinkedList<EntityB>();
	
	Random r=new Random();

	EntityA enta;
	EntityB entb;
	
	Game game;
	Textures t;
	public Controller(Game game,Textures t){
		this.game=game;
		this.t=t;
		
	}
	
	public void createEnemy(int enemy_count){
		for(int i=0;i<enemy_count;i++){
			//System.out.println("Hello");
			addEntity(new Enemy(550,r.nextInt((Game.HEIGHT*Game.SCALE)-64),t, game, this));
		}
		
	}
	
	public LinkedList<EntityA> getEntityA() {
		return ea;
	}

	

	public LinkedList<EntityB> getEntityB() {
		return eb;
	}

	

	public void tick(){
		//A class
		for(int i=0;i<ea.size();i++){
			enta=ea.get(i);
			enta.tick();
		}
		//B class
				for(int i=0;i<eb.size();i++){
					entb=eb.get(i);
					entb.tick();
				}
	}
	public void render(Graphics g){
		for(int i=0;i<ea.size();i++){
			enta=ea.get(i);
			enta.render(g);
		}
		for(int i=0;i<eb.size();i++){
			entb=eb.get(i);
			entb.render(g);
		}
	
	}
	public void addEntity(EntityA block){
		ea.add(block);
	}
	public void removeEntity(EntityA block){
		ea.remove(block);
	}
	public void addEntity(EntityB block){
		eb.add(block);
	}
	public void removeEntity(EntityB block){
		eb.remove(block);
	}
	
	
}
