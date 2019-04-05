import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

class Grid
{
	// A Grid stores a cell-based map of blocks (and maybe other goodies).
	// The Grid know how many pixels each block is, so can relate pixel-based
	// coords to the map.
	//Base Code Given by Mike Slattery

	
	/*
	 * Key:
	 * 1 = blue
	 * 0 = wall
	 * 4 = plus50 block
	 * 5 = super
	 * 3 = off
	 */
	//Create Boolean, Ints, map
	public static final int MWIDTH = 20;
	public static final int MHEIGHT = 15;
	int map[][] = new int[MWIDTH][MHEIGHT];
	static final int CELLSIZE = 40; // Number of pixels per map cell
	static int score1,score2;
	boolean eatOthers = false;
	int Timer = 400;
	static int timer2 = 0;
	
	Image block,plus4block,superPower,scoreAdd;
	
	//set Images and Grid
	Grid(Image b1, Image b2, Image b3,Image b4)
	{
		block = b1;
		plus4block = b2;
		superPower = b3;
		scoreAdd = b4;
		for (int row = 0; row < MHEIGHT; row++)
		 for (int col = 0; col < MWIDTH; col++)
			map[col][row] = 0;
	}
	//calculate width
	public int width()
	{
		return MWIDTH*CELLSIZE;
	}
	
	//moveRight for player (takes bb box, how far you want to move, which player called it)
	public int moveRight(BoundingBox r, int d, int player)
	{
		int right = (int)r.getMaxX();
		int col = right/CELLSIZE;
		int row1 = ((int)r.getMinY())/CELLSIZE;
		int row2 = ((int)r.getMaxY())/CELLSIZE;
		if (row2 >= MHEIGHT)
			row2 = MHEIGHT-1;
		int edge = CELLSIZE*(col+1);
		//if you go right without wall, add points, change to white 
		if ((right+d) < edge) {
			if(player == 1) {
				score1++;
			}
			else if (player == 2) {
				score2++;
			}
			map[col][row1] = 3;
			map[col][row2] = 3;
			return d;}
		if (col == (MWIDTH-1))
			return width()-right-1;
		for (int row = row1; row <= row2; row++) {
			//run into more score points
			if (map[col+1][row] == 1) {
				if(player == 1) {
					score1++;
				}
				else if (player == 2) {
					score2++;
				}
				map[col][row] = 3;
				return edge-right-1;
			}
			//run into the plus50 block
			else if (map[col+1][row] == 4) {
				map[col][row] = 3;
				if(player == 1) {
					score1+=50;
				}
				else if (player == 2) {
					score2+=50;
				}
				return d;
			}
			//run into the super power
			else if (map[col+1][row] == 5) {
				if(player == 1) {
					ByteMan.p1.vulnerable = true;
				}
				else if (player == 2) {
					ByteMan.p2.vulnerable = true;
				}
				map[col][row] = 3;
				return edge-right-1;
			}
		}
		return d;
	}

	public int moveLeft(BoundingBox r, int d, int player)
	{
				int left = (int)r.getMinX();
				int col = left/CELLSIZE;
				int row1 = ((int)r.getMinY())/CELLSIZE;
				int row2 = ((int)r.getMaxY())/CELLSIZE;
				if (row2 >= MHEIGHT)
					row2 = MHEIGHT-1;
				int edge = CELLSIZE*col;
				//run into more score points and not wall
				if ((left-d) >= edge) {
					map[col][row1] = 3;
					map[col][row2] = 3;
					if(player == 1) {
						score1++;
					}
					else if (player == 2) {
						score2++;
					}
					return d;}
				if (col == 0)
					return left;
				for (int row = row1; row <= row2; row++) {
					if (map[col-1][row] == 1) {
						//run into more scorePoints
						map[col][row] = 3;
						if(player == 1) {
							score1++;
						}
						else if (player == 2) {
							score2++;
						}
						return left-edge;
					}
					//run into plus50 points
					else if (map[col][row] == 4) {
						map[col][row] = 3;
						if(player == 1) {
							score1+=50;
						}
						else if (player == 2) {
							score2+=50;
						}
						return left-edge;
					}
					//run into add superPower
					else if (map[col][row] == 5) {
						map[col][row] = 3;
						if(player == 1) {
							ByteMan.p1.vulnerable = true;
						}
						else if (player == 2) {
							ByteMan.p2.vulnerable = true;
						}
						return left-edge;
					}
				}
				return d;
	}

	//move down works similar to Left and Right
	public int moveDown(BoundingBox r, int d, int player)
	{
		int rbottom = (int)r.getMaxY();
		int row = rbottom/CELLSIZE;
		int col1 = ((int)r.getMinX())/CELLSIZE;
		int col2 = ((int)r.getMaxX())/CELLSIZE;
		int edge = CELLSIZE*(row+1);
		if (rbottom+d < edge) {
			map[col1][row] = 3;
			map[col2][row]= 3;
			if(player == 1) {
				score1+=1;
			}
			else if (player == 2) {
				score2++;
			}
			return d;
		}
		
		for (int col = col1; col <= col2; col++) {
			if (map[col][row+1] == 1) {
				map[col][row] = 3;
				return edge - rbottom - 1;}
			else if (map[col][row+1] == 4) {
				map[col][row] = 3;
				if(player == 1) {
					score1+=50;
				}
				else if (player == 2) {
					score2+=50;
				}
				return d;
			}
			else if (map[col][row+1] == 5) {
				map[col-1][row] = 3;
				if(player == 1) {
					ByteMan.p1.vulnerable = true;
				}
				else if (player == 2) {
					ByteMan.p2.vulnerable = true;
				}
				return d;
			}
		}
		return d;
	}

	//move up looks similar to others
	public int moveUp(BoundingBox r, int d,int player)
	{
		int rtop = (int)r.getMinY();
		int row = rtop/CELLSIZE;
		int col1 = ((int)r.getMinX())/CELLSIZE;
		int col2 = ((int)r.getMaxX())/CELLSIZE;
		int edge = CELLSIZE*(row);
		if (rtop-d > edge) {
			map[col1][row] = 3;
			map[col2][row] = 3;
			if(player == 1) {
				score1++;
			}
			else if (player == 2) {
				score2++;
			}
			return d;
		}
			
		for (int col = col1; col <= col2; col++) {
			//checks to see if blue aka points being scored
			if (map[col][row-1] == 1) {
				map[col][row] = 3;
				if(player == 1) {
					score1++;
				}
				else if (player == 2) {
					score2++;
				}
				return rtop - edge;
			}
			//else if checks to see if it's Pound Sign (super)
			else if (map[col][row-1] == 4) {
				if(player == 1) {
					score1 += 50;
				}
				else if (player == 2) {
					score2 += 50;
				}
				map[col][row] = 3;
				return d;
			}
			//checks to see if another super
			else if (map[col][row-1] == 5) {
				map[col][row] = 3;
				if(player == 1) {
					ByteMan.p1.vulnerable = true;
				}
				else if (player == 2) {
					ByteMan.p2.vulnerable = true;
				}
				return d;
			}
	}
		return d;
	}


	public boolean atTop(BoundingBox r)
	{
		return false;
	}
	
	public int getScore1() {
		return score1;
	}
	public int getScore2() {
		return score2;
	}

	public void BlockOff(int x,int y) {
		map[x][y] = 3;
	}
	//set Wall
	public void setBlock(int x, int y)
	{
		map[x][y] = 1;
		
	}
	//set Blue
	public void setBlock2(int x, int y)
	{
		map[x][y] = 2;
	}
	//set plus50 block
	public void setBlock3(int x, int y)
	{
		map[x][y] = 4;
	}
	
	//set super
	public void setBlock4(int x, int y)
	{
		map[x][y] = 5;
	}
	public boolean returnSuper() {
		return eatOthers;
	}
	
	//resets points so that they will respawn (randomly 75% chance)
	public void restartBlocks() {
		for (int row = 0; row < MHEIGHT; row++) {
			 for (int col = 0; col < MWIDTH; col++) {
				 if(map[col][row] == 3 && (Math.random()<.75)) {
					 map[col][row] = 2;
				 }
			 }
			 }
	}

	public void render(GraphicsContext gc)
	{
		//call to respawn score blocks
		Timer--;
		if(Timer == 0) {
			restartBlocks();
			Timer = 400;
			
		}
		gc.setFill(Color.BLUE);
		for (int row = 0; row < MHEIGHT; row++) {
		 for (int col = 0; col < MWIDTH; col++) {
			if (map[col][row] == 1)
				gc.drawImage(block, col * CELLSIZE, row*CELLSIZE, CELLSIZE, CELLSIZE);
			else if(map[col][row] == 2) {
				gc.drawImage(scoreAdd, col * CELLSIZE, row*CELLSIZE, CELLSIZE, CELLSIZE);
				gc.setFill(Color.BLUE);
			}
			else if(map[col][row] == 3) {
				gc.setFill(Color.WHITE);
				gc.fillRect(col*CELLSIZE, row*CELLSIZE, CELLSIZE, CELLSIZE);
				gc.setFill(Color.BLUE);
			}
			else if(map[col][row] == 4) {
				gc.drawImage(plus4block, col * CELLSIZE, row*CELLSIZE, CELLSIZE, CELLSIZE);
				gc.setFill(Color.BLUE);
			}
			else if(map[col][row] == 5) {
				gc.drawImage(superPower, col * CELLSIZE, row*CELLSIZE, CELLSIZE, CELLSIZE);
				gc.setFill(Color.BLUE);
			}
		}
		}
	}
	
}
