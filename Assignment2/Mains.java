
import javafx.application.Application;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


/*
 * A simple reaction time game that calculates hits and misses for Assignment 2
 * Uses hits,miss counters and generates a random target after each hit. 
 * There is an end screen when the timer ends.
 * Avery Guething
 */
public class Mains extends Application {
	final int FPS = 60; // frames per second
	final static int WIDTH = 600;
	final static int HEIGHT = 500;

	int target_x;
	int target_y;
	boolean target_on = true;
	boolean game_on = true;
	GraphicsContext gc;
	Target target;
	Timer timekeeper;
	Font font;
	
	/**
	 * Set up initial data structures/values
	 */

	/*
	 * Checks to see if mouse is pressed. If pressed, then update the location and +1 score if hit.
	 * 
	 */
	void setHandlers(Scene scene)
	{
		scene.setOnMousePressed(
				e -> {
						int x = (int)e.getX();
						int y = (int)e.getY();
						if (x-20 < target_x && target_x < x+20 &&
								y-20 < target_y && target_y < y+20)
						{
							target_on = false;
							timekeeper.plusOne();
							move();
						}
						else {
							if(timekeeper.getTime()>=0) {
							timekeeper.plusMiss();
							move();
							}
						}
					}
				);
	}
	/*
	 * Initializes the target and timekeeper.
	 */
	void initialize()
	{	
		timekeeper = new Timer();
		target = new Target(target_x = WIDTH - 100, target_y = HEIGHT - 100);
		
	}
	/*
	 * Moves location of the target
	 */
	void move() {
		target.move();
		target_x = target.getX();
		target_y = target.getY();
		target_on = true;
	}
	// Update decreases the timer by one.
	void update() {
		timekeeper.plusOneTemp();
		if(timekeeper.temp == FPS)
		{
			timekeeper.plusOneTimer();
			timekeeper.temp = 0;
			
		}
	}
	void render(GraphicsContext gc) {
		// Clear background
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
	
		font = Font.font("SansSerif", FontWeight.BOLD, 24);
		timekeeper.render(gc);
		target.render(gc);
		
		//Hides time,score,misses and updates screen with done.
		 if(timekeeper.getTime() <= 0) {
			 timekeeper.hide(gc);
			 target.hide();
			 timekeeper.setfinalScore(timekeeper.getScore());
			 timekeeper.setfinalMisses(timekeeper.getMiss());
			 gc.setFill(Color.RED);
			 gc.setFont(font);
			 gc.fillText("Done! Score is: " + timekeeper.getFinalScore(),200,150);
		 }
		}

	/*
	 * Begin boiler-plate code...
	 * [Animation and events with initialization]
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage theStage) {
		theStage.setTitle("Reaction Time");

		Group root = new Group();
		Scene theScene = new Scene(root);
		theStage.setScene(theScene);

		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);

		gc = canvas.getGraphicsContext2D();

		// Initial setup
		initialize();
		setHandlers(theScene);
		
		// Setup and start animation loop (Timeline)
		KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
				e -> {
					// update position
					// draw frame
					update();
					render(gc);
					//timekeeper.minusOne();
				}
			);
		Timeline mainLoop = new Timeline(kf);
		mainLoop.setCycleCount(Animation.INDEFINITE);
		mainLoop.play();

		theStage.show();
	}
	/*
	 * ... End boiler-plate code
	 */
}