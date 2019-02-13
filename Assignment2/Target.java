import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*
 * Target Class for the target that appears on screens
 * Notes:
 * Moves to a random location (somewhere not near the score board)
 * Hide method was never called as it was required only in low FPS settings.
 */
public class Target{
  int locx, locy;
  boolean target_on = true;
  public boolean done;
	
  public Target(int x, int y) {
    locx = x;
    locy = y;
  }

  public void setFalse() {
	  target_on=false;
  }
  public void setTrue() {
	  target_on=true;
  }
  public void render(GraphicsContext g) {
	  g.setFill(Color.WHITE);
	  g.fillOval(locx-5,locy-5, 30, 30);
  }
  public void hide() {
	  locx = -50;
	  locy = -50;
  }
  public void move() {
	  Random random = new Random();
	  Random random2 = new Random();
	  int randomNumber = random.nextInt(500 - 40) + 40;
	  int randomNumber2 =random2.nextInt(400 - 40) + 40;
	  locx = randomNumber;
	  locy = randomNumber2;
  }
  /*
   * Getters and setters for the locations
   */
  public int getX() {
	  return locx;
  }
  public int getY() {
	  return locy;
  }
  public void setX(int testx) {
	  locx = testx;
  }
  public void setY(int testy) {
	  locy = testy;
  }
}