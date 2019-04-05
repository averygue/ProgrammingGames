import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Player1
{
	public int SuperPowerTime = 100;
	public int locx;
	public int locy;
	public int width = 35;
	public int height = 35;
	public int dx = 0, dy = 0;
	public int dir = 0;
	public Grid g;
	public boolean vulnerable = false;
	public int playerNum;
	public int score;

	//Creates Player object
	public Player1(Grid grid, int x, int y, int playerNumber)
	{
		locx = x;
		locy = y;
		g = grid;
		playerNum = playerNumber;
	}
	
	//draw character based on number given
	public void render(GraphicsContext gc)
	{
		if(playerNum == 1) {
			if (vulnerable) {
				gc.setFill(Color.CORAL);
			}
			else {
				gc.setFill(Color.CORNFLOWERBLUE);	
			}
			gc.fillOval(locx, locy, width, height);
		}
		else if (playerNum == 2) {
			if (vulnerable) {
				gc.setFill(Color.CORAL);
				gc.fillOval(locx, locy, width, height);
				
			}
			else {
				gc.setFill(Color.PURPLE);	
			}
			gc.fillOval(locx, locy, width, height);
		}
	}
	
		
	//update --> check movement and vulnerability
	public void update()
	{
		if(vulnerable) {
			Grid.timer2++;
			if(Grid.timer2 == SuperPowerTime) {
				vulnerable = false;
				ByteMan.respawn = true;
				Grid.timer2 = 0;
			}
			
		}
		//right movement
		if (dir == 2) {
			dx = g.moveRight(collisionBox(), 5,playerNum);
			locx += dx;
		}
		//down movement
		else if (dir == -2) {
			dx = -g.moveLeft(collisionBox(), 5,playerNum);
			locx += dx;
		}
		//Up movement
		else if (dir == 1) {
			dy = -g.moveUp(collisionBox(), 5,playerNum);
			locy += dy;
		}
		//left movement
		else if(dir == 3) {
			dy = g.moveDown(collisionBox(), 5,playerNum);
			locy += dy;
		}
	}

	public void setPosition(int c, int d)
	{
		locx = c; locy = d;
	}

	public void setVelocity(int x, int y)
	{
		dx = x; dy = y;
	}

	public BoundingBox collisionBox()
	{
		return new BoundingBox(locx, locy, width, height);
	}

}