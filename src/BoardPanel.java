import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
  public static final int COLUMN = 25;
  public static final int ROW = 25;
  public static final int TILE_SIZE = 20;
  
  private static final Font FONT = new Font("Arial", Font.BOLD, 25);
  private CheemsGame game;
  private TileType[] tiles;
  Image burgerImage;

  public BoardPanel(CheemsGame game) {
    this.game = game;
    this.tiles = new TileType[COLUMN * ROW];

    setPreferredSize(new Dimension(COLUMN * TILE_SIZE, ROW * TILE_SIZE));
    setBackground(Color.BLACK); 
  }

  public void clearBoard() {
    for(int i = 0; i < tiles.length; i++) {
      tiles[i] = null;
    }
  }

  public void setTile(Point point, TileType type) {
    setTile(point.x, point.y, type);
  }

  public void setTile(int x, int y, TileType type) {
    tiles[y * ROW + x] = type;
  }

  public TileType getTile(int x, int y) {
    return tiles[y * ROW + x];
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    for (int x = 0; x < COLUMN; x++) {
      for (int y = 0; y < ROW; y++) {
        TileType type = getTile(x, y);
        if (type != null) {
          drawTile(x * TILE_SIZE, y * TILE_SIZE, type, g);
        }
      }
    }
    
    g.setColor(Color.DARK_GRAY);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		for(int x = 0; x < COLUMN; x++) {
			for(int y = 0; y < ROW; y++) {
				g.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE, getHeight());
				g.drawLine(0, y * TILE_SIZE, getWidth(), y * TILE_SIZE);
			}
		}

    if(game.isGameOver() || game.isNewGame() || game.isPaused()) {
      g.setColor(Color.WHITE);

      int centerX = getWidth() / 2;
      int centerY = getHeight() / 2;

      String largeMessage = null;
			String smallMessage = null;
			if(game.isNewGame()) {
				largeMessage = "Cheems Game!";
				smallMessage = "Press Enter to Start";
			} else if(game.isGameOver()) {
				largeMessage = "Game Over!";
				smallMessage = "Press Enter to Restart";
			} else if(game.isPaused()) {
				largeMessage = "Paused";
				smallMessage = "Press P to Resume";
			}

      g.setFont(FONT);
			g.drawString(largeMessage, centerX - g.getFontMetrics().stringWidth(largeMessage) / 2, centerY - 50);
			g.drawString(smallMessage, centerX - g.getFontMetrics().stringWidth(smallMessage) / 2, centerY + 50);
    }
  }

  private void drawTile(int x, int y, TileType type, Graphics g) {
    switch(type) {
      case Burger:        
      	burgerImage  = new ImageIcon(this.getClass().getResource("images/burger1.png")).getImage();
        g.drawImage(burgerImage, x - 3, y - 3, null);
			  break;

      case CheemsBody:
        g.setColor(Color.ORANGE);
			  g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
			  break;
        
      case CheemsHead:
        g.setColor(Color.GREEN);
        g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
    }
  }  
}
