import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

public class CheemsGame extends JFrame {
  private static final long FRAME_TIME = 1000L / 50L;

  private BoardPanel board;
  private SidePanel side;
  private Random random;
  private Clock logicTimer;
  
  private boolean isNewGame;
  private boolean isGameOver;
  private boolean isPaused;

  private LinkedList<Point> cheems;
  private LinkedList<Direction> directions;

  private int score;
  private int burgersEaten;
  private int drinksEaten;
  private int nextBurgerScore;
  private int nextDrinkScore;

	public CheemsGame() {
		super("Cheems's Burger Adventure");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		this.board = new BoardPanel(this);
		this.side = new SidePanel(this);
		
		add(board, BorderLayout.CENTER);
		add(side, BorderLayout.WEST);

    	addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
  				case KeyEvent.VK_W:
  				case KeyEvent.VK_UP:
  					if(!isPaused && !isGameOver) {
  						if(directions.size() < 3) {
  							Direction last = directions.peekLast();
  							if(last != Direction.Down && last != Direction.Up) {
  								directions.addLast(Direction.Up);
  							}
  						}
  					}
  					break;
          
  				case KeyEvent.VK_S:
  				case KeyEvent.VK_DOWN:
  					if(!isPaused && !isGameOver) {
  						if(directions.size() < 3) {
  							Direction last = directions.peekLast();
  							if(last != Direction.Up && last != Direction.Down) {
  								directions.addLast(Direction.Down);
  							}
  						}
  					}
  					break;

  				case KeyEvent.VK_A:
  				case KeyEvent.VK_LEFT:
  					if(!isPaused && !isGameOver) {
  						if(directions.size() < 3) {
  							Direction last = directions.peekLast();
  							if(last != Direction.Right && last != Direction.Left) {
  								directions.addLast(Direction.Left);
  							}
  						}
  					}
  					break;

  				case KeyEvent.VK_D:
  				case KeyEvent.VK_RIGHT:
  					if(!isPaused && !isGameOver) {
  						if(directions.size() < 3) {
  							Direction last = directions.peekLast();
  							if(last != Direction.Left && last != Direction.Right) {
  								directions.addLast(Direction.Right);
  							}
  						}
  					}
  					break;
            
  				case KeyEvent.VK_P:
  					if(!isGameOver) {
  						isPaused = !isPaused;
  						logicTimer.setPaused(isPaused);
  					}
  					break;
            
  				case KeyEvent.VK_ENTER:
  					if(isNewGame || isGameOver) {
  						resetGame();
  					}
  					break;    
				case KeyEvent.VK_SPACE:
					if(!isPaused && !isGameOver) {
						LinkedList<Direction> reversedDirections = new LinkedList<>();
						while(!directions.isEmpty()) {
						  reversedDirections.addFirst(directions.removeFirst());
						}
						directions = reversedDirections;
					}
					break;       
        }    
      }
    });
	
	pack();
	setLocationRelativeTo(null);
	setVisible(true);
  }



  private void startGame() {
		this.random = new Random();
		this.cheems = new LinkedList<>();
		this.directions = new LinkedList<>();
		this.logicTimer = new Clock(9.0f);
		this.isNewGame = true;

    logicTimer.setPaused(true);

    while(true) {
      long start = System.nanoTime();
      logicTimer.update();

      if(logicTimer.hasCyclePassed()) {
        updateGame();
      }

      board.repaint();
      side.repaint();

			long delta = (System.nanoTime() - start) / 1000000L;
			if(delta < FRAME_TIME) {
				try {
					Thread.sleep(FRAME_TIME - delta);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}      
    }
  }

  private void updateGame() {
    TileType collision = updatecheems();
    
		if(collision == TileType.Burger) {
			burgersEaten++;

			score += nextBurgerScore;
			if(burgersEaten % 5 == 0) {
				spawnDrink();
			} else {
				spawnBurger();
			}
			logicTimer.setCyclesPerSecond(9.0f);
		} else if(collision == TileType.Drink) {
			logicTimer.setCyclesPerSecond(15.0f);
			spawnBurger();
		} else if(collision == TileType.CheemsBody) {
			isGameOver = true;
			logicTimer.setPaused(true);
		} else if(nextBurgerScore > 10) {
			nextBurgerScore--;
		}
  }

  private TileType updatecheems() {
    Direction direction = directions.peekFirst();

  	Point head = new Point(cheems.peekFirst());
		switch(direction) {
  		case Up:
  			head.y--;
  			break;
  			
  		case Down:
  			head.y++;
  			break;
  			
  		case Left:
  			head.x--;
  			break;
  			
  		case Right:
  			head.x++;
  			break;
		}
    
		if(head.x < 0 || head.x >= BoardPanel.COLUMN || head.y < 0 || head.y >= BoardPanel.ROW) {
			return TileType.CheemsBody;
		}

		TileType old = board.getTile(head.x, head.y);
		if(old != TileType.Burger && cheems.size() > 5) {
			Point tail = cheems.removeLast();
			board.setTile(tail, null);
			old = board.getTile(head.x, head.y);
		}

		if(old != TileType.CheemsBody) {
			board.setTile(cheems.peekFirst(), TileType.CheemsBody);
			cheems.push(head);
			board.setTile(head, TileType.CheemsHead);
			if(directions.size() > 1) {
				directions.poll();
			}
		}
				
		return old;    
  }

  private void resetGame() {
    this.score = 0;
    this.burgersEaten = 0;

    this.isNewGame = false;
    this.isGameOver = false;

    Point head = new Point(BoardPanel.COLUMN / 2, BoardPanel.ROW / 2);

    cheems.clear();
    cheems.add(head);

    board.clearBoard();
    board.setTile(head, TileType.CheemsHead);

    directions.clear();
    directions.add(Direction.Up);

    logicTimer.reset();

    spawnBurger();
  }

	public boolean isNewGame() {
		return isNewGame;
	}
  
	public boolean isGameOver() {
		return isGameOver;
	}

	public boolean isPaused() {
		return isPaused;
	}

  private void spawnBurger() {
    this.nextBurgerScore = 100;

    int index = random.nextInt(BoardPanel.COLUMN * BoardPanel.ROW - cheems.size());

		int freeFound = -1;
		for(int x = 0; x < BoardPanel.COLUMN; x++) {
			for(int y = 0; y < BoardPanel.ROW; y++) {
				TileType type = board.getTile(x, y);
				if(type == null || type == TileType.Burger) {
					if(++freeFound == index) {
						board.setTile(x, y, TileType.Burger);
						break;
					}
				}
			}
		}
  }
  private void spawnDrink() {
    this.nextDrinkScore = 100;

    int index = random.nextInt(BoardPanel.COLUMN * BoardPanel.ROW - cheems.size());

		int freeFound = -1;
		for(int x = 0; x < BoardPanel.COLUMN; x++) {
			for(int y = 0; y < BoardPanel.ROW; y++) {
				TileType type = board.getTile(x, y);
				if(type == null || type == TileType.Drink) {
					if(++freeFound == index) {
						board.setTile(x, y, TileType.Drink);
						break;
					}
				}
			}
		}
  }

  public int getScore() {
    return score;
  }

	public int getBurgersEaten() {
		return burgersEaten;
	}

	public int getNextBurgerScore() {
		return nextBurgerScore;
	}

	public int getDrinksEaten() {
		return drinksEaten;
	}

	public int getNextDrinkScore() {
		return nextDrinkScore;
	}

	public Direction getDirection() {
		return directions.peek();
	}

	public static void main(String[] args) {
		CheemsGame cheems = new CheemsGame();
		cheems.startGame();
	}
}
