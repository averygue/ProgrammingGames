import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/*Avery 
 * Byte Man
 * Music w/ Permission:
 * Music: Eric Skiff - Chibi Ninja - Resistor Anthems - Available at http://EricSkiff.com/music
 * Goal: Get 600 points before your opponent.
 */
public class ByteMan extends Application {
	final String appName = "Byte-Man";
	final int FPS = 25; // frames per second
	final static double WIDTH = 600;
	final static double HEIGHT = 575;
	public boolean introScreen = true;
	public boolean wonGame1 = false;
	public boolean wonGame2 = false;
	static boolean respawn = false;
	MediaPlayer mP;
	Image keys2,keys;
	AudioClip collision;
	Grid grid;
	static Player1 p1;
	static Player1 p2;
	ScoreKeeper scores;
	Font font,font2,font3,font4;
	Image blockImage = new Image("dots 2.png");
	Image Plus4Image = new Image("dots 4.png");
	Image superPower = new Image("dots 5.png");
	Image plusScores = new Image("dots 3.png");
	
	/**
	 * Set up initial data structures/values
	 */
	void initialize()
	{
		Media song = new Media(ClassLoader.getSystemResource("ChibiNinja.mp3").toString());
		mP = new MediaPlayer(song);
		mP.play();
		collision = new AudioClip(ClassLoader.getSystemResource("Blip 3.wav").toString());
		keys2 = new Image("Keys2.png");
		keys = new Image("Keys.png");
		grid = new Grid(blockImage,Plus4Image,superPower,plusScores);
		p1 = new Player1(grid,50,200,1);
		p2 = new Player1(grid,510,200,2);
		scores = new ScoreKeeper();
		font = Font.font("Times New Roman", 40);
		font2 = Font.font("Times New Roman", 25);
		font3 = Font.font("Times New Roman", 30);
		font4 = Font.font("Times New Roman", 15);
		setScene();
	}
	
	//creates main Scene
	public void setScene()
	{
		for (int i = 0; i <= 14;i++) {
			for (int j = 0; j <=12;j++) {
				grid.setBlock2(i, j);}
		}
		
		grid.setBlock3(1, 1);
		grid.setBlock3(1, 11);
		grid.setBlock3(13, 1);
		grid.setBlock3(13, 11);
		
		grid.setBlock4(7, 6);
		
		grid.setBlock(0,0);grid.setBlock(0,1);grid.setBlock(0,2);grid.setBlock(0,3);
		grid.setBlock(0,4);grid.setBlock(0,7);grid.setBlock(0,8);grid.setBlock(0,9);
		grid.setBlock(0,10);grid.setBlock(0,11);grid.setBlock(0,12);
		
		
		grid.setBlock(14,0);grid.setBlock(14,1);grid.setBlock(14,2);grid.setBlock(14,3);
		grid.setBlock(14,4);grid.setBlock(14,7);grid.setBlock(14,8);grid.setBlock(14,9);
		grid.setBlock(14,10);grid.setBlock(14,11);grid.setBlock(14,12);
		
		for (int i = 0; i <= 14; i++) 
			grid.setBlock(i, 12);
		
		for(int i = 0; i < 14; i++)
			grid.setBlock(i, 12);
		
		for(int i = 0; i <=14;i++) {
			grid.setBlock(i, 0);
		}
		
		grid.setBlock(2,2);grid.setBlock(3,2);grid.setBlock(4,2);grid.setBlock(5,2);
		grid.setBlock(9,2);grid.setBlock(10,2);grid.setBlock(11,2);grid.setBlock(12,2);
		
		grid.setBlock(2,10);grid.setBlock(3,10);grid.setBlock(4,10);grid.setBlock(5,10);
		grid.setBlock(9,10);grid.setBlock(10,10);grid.setBlock(11,10);grid.setBlock(12,10);
		
		grid.setBlock(5,5);grid.setBlock(5,6);grid.setBlock(5,7);
		grid.setBlock(9,5);grid.setBlock(9,6);grid.setBlock(9,7);
		
	}
	
	//Key Listeners
	void setHandlers(Scene scene)
	{
		scene.setOnMousePressed(e -> {
			introScreen = false;
		});
		scene.setOnKeyPressed(
				e -> {
					KeyCode key = e.getCode();
					switch (key)
					{
					case W: 
						p1.dir = 1;
						break;
					case A: 
						p1.dir = -2;
						break;
					case S: 
						p1.dir = 3;
						break;
					case D:
						p1.dir = 2;
						break;
					case UP: 
						p2.dir = 1;
						break;
					case LEFT: 
						p2.dir = -2;
						break;
					case DOWN: 
						p2.dir = 3;
						break;
					case RIGHT:
						p2.dir = 2;
						break;
						
					default:
						break;
					}
				});
		scene.setOnKeyReleased(
				e -> {
					KeyCode key = e.getCode();
					switch (key)
					{
					case W: 
						p1.dir = 0;
						break;
					case A: 
						p1.dir = 0;
						break;
					case S: 
						p1.dir = 0;
						break;
					case D:
						p1.dir = 0;
						break;
					case UP: 
						p2.dir = 0;
						break;
					case LEFT: 
						p2.dir = 0;
						break;
					case DOWN: 
						p2.dir = 0;
						break;
					case RIGHT:
						p2.dir = 0;
						break;
					default:
						break;
					}
				});
	}
	

	/**
	 *  Update variables for one time step
	 */
	public void update()
	{
		//update Player movement
		p1.update();
		p2.update();
	
		//check to see if vulnerable and if they crashed into each other
		if(p1.vulnerable) {
			if (p1.collisionBox().intersects(p2.collisionBox()))
			{
				collision.play();
				Grid.score2 = 0;
				p2.locx = 510;
				p2.locy = 200;
			}
		}
		if(p2.vulnerable) {
			if (p2.collisionBox().intersects(p1.collisionBox()))
			{
				collision.play();
				Grid.score1 = 0;
				p1.locx = 50;
				p1.locy = 200;
			}
		}
		//respawn middle block if necessary
		if(respawn) {
			grid.setBlock4(7, 6);
			respawn = false;
		}
		//set scores
		scores.setScore1(grid.getScore1());
		scores.setScore2(grid.getScore2());
		
		//check to see who won
		if(scores.getScore1() == 500) {
			System.out.println("Player 1 has won!");
			wonGame1 = true;
		}
		if(scores.getScore2() == 500) {
			System.out.println("Player 2 has won!");
			wonGame2 = true;
		}
	}
	

	/**
	 *  Draw the game world
	 */
	void render(GraphicsContext gc) {
		
		if (introScreen) {
			// startup screen
			gc.setFill(Color.ALICEBLUE);
			gc.fillRect(0, 0, WIDTH, HEIGHT);
			gc.setStroke(Color.BLACK);
			gc.strokeRect(0, 0, WIDTH - 1, HEIGHT - 1);
			gc.setFill(Color.RED);
			gc.setFont(font);
			gc.fillText("Byte Man", 200, 40);
			gc.setFill(Color.BLACK);
			gc.setFont(font2);
			gc.fillText("Welcome to Byte-Man:", 165, 75);
			gc.fillText("A Two Player Game where two Professors\r\ttry to complete their research.", 90, 105);
			gc.fillText("\tFirst to Get 500 points wins.", 90, 200);
			gc.setFont(font4);
			gc.fillText("Eat your opponent\rand reset their score.", 100, 250);
			gc.fillText("Add 50 points to your score.", 355, 250);
			gc.setFill(Color.BLUEVIOLET);
			gc.setFont(font3);
			gc.fillText("Player 1 Controls: \t \t Player 2 Controls:", 20, 350);
			gc.setFill(Color.BLACK);
			gc.setFont(font2);
			gc.fillText("Click Anywhere to Start the Game", 125, 550);
			gc.drawImage(keys2, 60, 375);
			gc.drawImage(keys, 375, 370);
			gc.drawImage(superPower, 40, 225, 50, 50);
			gc.drawImage(Plus4Image, 300, 225, 50, 50);
			return;
		}
		//see if anyone won
		gc.setFill(Color.WHITE);
		gc.fillRect(0.0, 0.0, WIDTH, HEIGHT);
		gc.setFill(Color.DARKCYAN);
		gc.fillRect(0, 500, WIDTH, 100);
		grid.render(gc);
		p1.render(gc);
		p2.render(gc);
		scores.render(gc);
		if (wonGame1) {
			gc.setFill(Color.DARKBLUE);
			gc.fillRect(0, 0, WIDTH, HEIGHT);
			gc.setFont(font);
			gc.setFill(Color.WHITE);
			gc.fillText("Player 1 has Won.", 150, 200);
		}
		if (wonGame2) {
			gc.setFill(Color.PURPLE);
			gc.fillRect(0, 0, WIDTH, HEIGHT);
			gc.setFont(font);
			gc.setFill(Color.WHITE);
			gc.fillText("Player 2 has Won.", 150, 200);
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
		theStage.setTitle(appName);

		Group root = new Group();
		Scene theScene = new Scene(root);
		theStage.setScene(theScene);

		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Initial setup
		initialize();
		setHandlers(theScene);
		
		// Setup and start animation loop (Timeline)
		KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
				e -> {
					// update position
					update();
					// draw frame
					render(gc);
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
