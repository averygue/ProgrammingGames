
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*
 * Timer / Scoreboard Keeper
 * Just draws the score at the bottom. Mostly Setters and Getters
 */
public class ScoreKeeper {
   public static int timeleft = 30;
   public int score1,score2;
 
   Font font;
   Font font2;
   int temp = 0;
   
   public ScoreKeeper(){
	   font = Font.font("SansSerif", FontWeight.BOLD, 23);
	   font2 = Font.font("SansSerif", FontWeight.BOLD, 25);
   }

   public void render(GraphicsContext gc){
	   gc.setFill(Color.WHITE);
	   gc.setFont(font);
	   gc.fillText("Score: " + score1 +"\t\t\tScore2: "+ score2,150,550);
   }
   
   public void hide(GraphicsContext gc){
	   gc.setFill(Color.BLACK);
	   gc.fillRect(0, 0, 160, 125);
   }

   public void plusOne1(){
	  score1++;
   }
   public void plusOne2(){
		 score2++;
	   }
   //getters and setters
   public void plusOneTimer () {
	   timeleft = (timeleft - 1);
   }
   public void plusOneTemp() {
	   temp++;
   }
   public void restart() {
	   score1 = 0;
	   score2 = 0;
   }
   public void setScore1(int a) {
	   score1 = a;
   }
   public void setScore2(int a) {
	   score2 = a;
   }
   public int getTime() {
	   return timeleft;
   }
   public int getScore1() {
	   return score1;
   }
   public int getScore2() {
	   return score2;
   }
}