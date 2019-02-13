import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*
 * Timer / Scoreboard Keeper
 */
public class Timer {
   public static int count = 0;
   public static int miss = 0;
   public static int timeleft = 30;
   public static int finalscore = 0;
   public static int finalmisses = 0;
 
   Font font;
   Font font2;
   int temp = 0;
   
   public Timer(){
	   font = Font.font("SansSerif", FontWeight.BOLD, 23);
	   font2 = Font.font("SansSerif", FontWeight.BOLD, 25);
   }

   public void render(GraphicsContext gc){
	   gc.setFill(Color.GREENYELLOW);
	   gc.setFont(font);
	   gc.fillText("Score: " + count +"\nMisses: "+ miss  + "\nTime Left: " + timeleft,5,25);
   }
   
   public void hide(GraphicsContext gc){
	   gc.setFill(Color.BLACK);
	   gc.fillRect(0, 0, 160, 125);
   }

   public void plusOne(){
	  count++;
   }
   public void plusMiss(){
		 miss++;
	   }
   //getters and setters
   public void setfinalScore(int a) {
	  finalscore = a;
   }
   public void setfinalMisses(int a) {
		  finalmisses = a;
	   }
   public void plusOneTimer () {
	   timeleft = (timeleft - 1);
   }
   
   public void plusOneTemp() {
	   temp++;
   }
   public void restart() {
	   count = 0;
   }
   public int getTime() {
	   return timeleft;
   }
   public int getScore() {
	   return count;
   }
   public int getMiss() {
	   return miss;
   }
   public int getFinalScore() {
	   return finalscore;
   }
   public int getFinalMisses() {
	   return finalmisses;
   }

}