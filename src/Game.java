import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import classes.EntityA;
import classes.EntityB;


public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID=1L;
	public static final int WIDTH=320;
	public static final int HEIGHT=WIDTH/12*9;
	public static final int SCALE=2;
	public static String TITLE="INTROVERT.game";

	private boolean running=false;
	private Thread thread;

	private BufferedImage image =new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet=null;
	private BufferedImage background=null;
	private BufferedImage menuimg=null;
	private Menu menu;
	private boolean is_shooting=false;
	private int enemy_count=1;
	private int enemy_killed=0;
	
	public static int STAMINA=200;
	public static int SCORE=0;
	public  enum STATE{MENU,GAME,END,WIN};
	
	public static STATE state=STATE.MENU;

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
	private Player p;
	private Controller c;
	private Textures t;
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	public void init(){

		BufferedImageLoader loader=new BufferedImageLoader();
		try{
			spriteSheet=loader.loadImage("res/SpriteSheet.png");
			background=loader.loadImage("res/Background.png");
			menuimg=loader.loadImage("res/Menu.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		this.addMouseListener(new MouseInput(this));
		addKeyListener(new KeyInput(this));
		this.requestFocus();
		t=new Textures(this);
		
		c=new Controller(this,t);
		p=new Player(200,200,t,this,c);
		menu=new Menu();
		ea=c.getEntityA();
		eb=c.getEntityB();
		c.createEnemy(enemy_count);
	}

	private synchronized void start(){
		if(running)
			return;
		running =true;
		thread=new Thread(this);
		thread.start();

	}

	private synchronized void stop(){
		if(!running){
			return;
		}
		running=false;
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.exit(1);
	}

	public static  void main(String[] args){
		Game game=new Game();
		game.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		game.setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		JFrame frame=new JFrame(Game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();

	}
	@Override
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks= 60.0;
		double ns= 1000000000/amountOfTicks;
		double delta = 0;
		int updates=0;
		int frames=0;
		long timer= System.currentTimeMillis();
		while(running){
			long now= System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime=now;
			if(delta>=1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() -timer> 1000){
				timer+=1000;
				System.out.println(updates+" Ticks, FPS "+frames);
				updates=0;
				frames=0;
			}

		}
		stop();
	}

	private void tick() {
		// TODO Auto-generated method stub
		if(state==STATE.GAME){
		p.tick();
		c.tick();
		if(STAMINA>200) STAMINA=200;
		if(enemy_killed>=enemy_count && enemy_count<=30){
			//System.out.println("Hello game");
			enemy_count+=1;
			enemy_killed=0;
			c.createEnemy(enemy_count);
		}
		else if(enemy_count>30){
			state=Game.STATE.WIN;
			
		}
		
		}
		
		
	}
	private void render() {
		// TODO Auto-generated method stub
		BufferStrategy bs= this.getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		Graphics g=bs.getDrawGraphics();
		///////////////////////////////////
		g.drawImage(image, 0, 0,getWidth(),getHeight(),this);
		
		
		if(state==STATE.GAME){
			g.drawImage(background,0,0,null);
		p.render(g);
		c.render(g);
		

		g.setColor(Color.red);
		g.fillRect(10, 10, 200, 25);
		g.setColor(Color.white);
		g.drawRect(10, 10, 200, 25);
		
		g.setColor(Color.green);
		g.fillRect(10, 10, STAMINA, 25);
		
		Font fnt0=new Font("arial",Font.BOLD,20);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("STAMINA",220,30);
		g.drawString("Level: "+enemy_count,350, 30);
		g.drawString("SCORE: "+Game.SCORE,500, 30);
		
		
		}else if(state==STATE.MENU){
			g.drawImage(menuimg,0,0,null);
			menu.render(g);
		}
		else if(state==STATE.END){
			Rectangle playButton=new Rectangle(Game.WIDTH/2+50,370,150,70);
			Font fnt0=new Font("arial",Font.BOLD,43);
			g.setFont(fnt0);
			g.setColor(Color.WHITE);
			g.drawString("YOU RAN OUT OF STAMINA!",30,250);
			g.drawString("SCORE :  "+Game.SCORE,WIDTH/2,320);
			Graphics2D g2D=(Graphics2D) g;
			Font fnt1=new Font("arial",Font.BOLD,30);
			g.setColor(Color.white);
			g.setFont(fnt1);
			g.drawString("RETRY",playButton.x+25,playButton.y+50);
			g2D.draw(playButton);
		}
		else{
			Rectangle playButton=new Rectangle(Game.WIDTH/2+50,370,150,70);
			Font fnt0=new Font("arial",Font.BOLD,43);
			g.setFont(fnt0);
			g.setColor(Color.WHITE);
			g.drawString("YOU WIN!",WIDTH/2+50,200);
			g.drawString("SCORE :  "+Game.SCORE,WIDTH/2+40,300);
			Graphics2D g2D=(Graphics2D) g;
			Font fnt1=new Font("arial",Font.BOLD,20);
			g.setColor(Color.white);
			g.setFont(fnt1);
			g.drawString("PLAY AGAIN",playButton.x+20,playButton.y+40);
			g2D.draw(playButton);
		}
		////////////////////////////////////
		g.dispose();
		bs.show();
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

public void keyPressed(KeyEvent e){
		int key=e.getKeyCode();
		
		if(state==STATE.GAME){
		if(key==KeyEvent.VK_RIGHT){
			p.setVelX(5);
		}else if(key==KeyEvent.VK_LEFT){
			p.setVelX(-5);
		}else if(key==KeyEvent.VK_DOWN){
			p.setVelY(5);
		}else if(key==KeyEvent.VK_UP){
			p.setVelY(-5);
		}else if(key==KeyEvent.VK_SPACE && !is_shooting){
			is_shooting=true;
			c.addEntity(new Bullet(p.getX(),p.getY(),t,this));
		}
		}
	}
	public void keyReleased(KeyEvent e){
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_RIGHT){
			p.setVelX(0);
		}else if(key==KeyEvent.VK_LEFT){
			p.setVelX(0);
		}else if(key==KeyEvent.VK_DOWN){
			p.setVelY(0);
		}else if(key==KeyEvent.VK_UP){
			p.setVelY(0);
		}else if(key==KeyEvent.VK_SPACE){
			is_shooting=false;
			}
	}

}
