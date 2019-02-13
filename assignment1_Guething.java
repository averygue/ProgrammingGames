

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/* Avery Guething
 * COSC3350: Mike Slattery
 * Made for Assignment 1: Create a basic JavaFX program
 * Care Flashing Colors.
 */
public class assignment1_Guething extends Application {

	final int FPS = 45;
	int i = 0;
	int j = -150;
	int k = -350;
	int sun1 = 0;
	int sun2 = 400;
	Line line = new Line(100, 10, 10, 110);
	int time = 0;
	int a = 1000;
	int b = 975;
	int c = 950;
	int d = 925;
	int e = 900;
	int f = 875;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage theStage) {
		theStage.setTitle("This is Avery.");
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        
        Canvas canvas = new Canvas( 600, 500 );
        root.getChildren().add( canvas );
       
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        render(gc);
        KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
    			new EventHandler<ActionEvent>() {
    				@Override
    				public void handle(javafx.event.ActionEvent event) {
    					// update position
    					update();
    					// draw frame
    					render(gc);
    				}
    		});
    		Timeline mainLoop = new Timeline(kf);
    		mainLoop.setCycleCount(Animation.INDEFINITE);
    		mainLoop.play();
        theStage.show();	}

	void update() {
		i += 5;
		j += 5;
		a -= 4;
		b -= 4;
		c -= 4;
		d -= 4;
		e -= 4;
		f -= 4;
		k += 5;
		if(i > 600) {
			i = -150;
		}
		if(j > 600) {
			j = -150;
		}
		if(k > 600) {
			k = -150;
		}
		if(a < 0) {
			a = 1000;
			b = 975;
			c = 950;
			d = 925;
			e = 900;
			f = 875;
		}
		
		
	}

	void render(GraphicsContext gc) {    
		
		gc.setFill( Color.BLACK);
        gc.fillRect( 0, 0, 600, 500);
		
        gc.setFill( Color.MEDIUMPURPLE);
        gc.fillOval(i, 450, 50, 50);
        gc.setFill( Color.BLUEVIOLET);
        gc.fillOval(j, 400, 100, 100);
        gc.setFill( Color.DARKMAGENTA);
        gc.fillOval(k, 350, 150, 150);
       
        gc.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        gc.strokeLine(0, a, a,0);
        gc.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        gc.strokeLine(0, b, b,0);
        gc.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        gc.strokeLine(0, c, c,0);
        gc.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        gc.strokeLine(0, d, d,0);
        gc.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        gc.strokeLine(0, e, e,0);
        gc.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        gc.strokeLine(0, f, f,0);
        
        
        
	}

}
