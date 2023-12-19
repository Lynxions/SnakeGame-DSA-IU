import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

public class SnakeGame extends JFrame {
  private static final long FRAME_TIME = 1000L / 50L;

  private BoardPanel board;
  private SidePanel side;
  private Random random;
  private Clock logicTimer;
  
  private boolean isNewGame;
  private boolean isGameOver;
  private boolean isPaused;

  private LinkedList<Point> snake;
  private LinkedList<Direction> directions;

  private int score;
  private int fruitsEaten;
  private int nextFruitScore;

	public SnakeGame() {
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
      public void keyPressed(keyEvent e) {
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
  						if(directions.size() < MAX_DIRECTIONS) {
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
  						if(directions.size() < MAX_DIRECTIONS) {
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
        }    
      }
    }
  }

	pack();
	setLocationRelativeTo(null);
	setVisible(true);

  private void startGame() {
		this.random = new Random();
		this.snake = new LinkedList<>();
		this.directions = new LinkedList<>();
		this.logicTimer = new Clock(9.0f);
		this.isNewGame = true;

    logicTimer.setPaused(true);

    while(true) {
      long start = System.nanoTime();
      logicTimer.update();

      if(logicTimer.hasCyclePassed() {
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

  private void UpdateGame() {
    
  }
}
