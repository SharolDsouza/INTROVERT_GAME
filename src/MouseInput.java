
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
	Game game;

	public MouseInput(Game game) {
		this.game=game;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		// Rectangle playButton=new Rectangle(Game.WIDTH/2-50,250,150,70);
		// Rectangle quitButton=new Rectangle(Game.WIDTH/2+220,250,150,70);
		//Rectangle retryButton=new Rectangle(Game.WIDTH/2+50,370,150,70);
		if(Game.state==Game.STATE.END||Game.state==Game.STATE.WIN){
			if (mx >= Game.WIDTH / 2 + 50 && mx <= Game.WIDTH / 2 + 200) {
				if (my >= 370 && my <= 440) {
					Game.STAMINA=200;
					game.setEnemy_count(0);
					Game.SCORE=0;
					Game.state = Game.STATE.GAME;
				}
			}
		}
		else{
		if (mx >= Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2 + 100) {
			if (my >= 250 && my <= 320) {
				Game.state = Game.STATE.GAME;
			}
		}

		if (mx >= Game.WIDTH / 2 +220 && mx <= Game.WIDTH / 2 + 370) {
			//System.out.println("Hello x");
			if (my >= 250 && my <= 320) {
				
				System.exit(1);
			}
		}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
