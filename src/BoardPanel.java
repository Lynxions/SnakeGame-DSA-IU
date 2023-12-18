import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
  public static final int COLUMN = 25;
  public static final int ROW = 25;
  public static final int TILE_SIZE = 20;
  
  private static final font FONT = new Font("Arial", Font.BOLD, 25);
  private SnakeGame game;
  private TileType[] tiles;

  public BoardPanel(SnakeGame game) {
    this.game = game;
    this.tiles = new TileType[COLUMN * ROW];

    setPreferredSize(new Dimension(COLUMN * TILE_SIZE, ROW * TILE_SIZE);
    setBackground(Color.Black);
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

    for (int x = 0; x < COLUMN, x++) {
      for (int y = 0; y < ROW, y++) {
        TileType type = getTile(x, y);
        if (type != null) {
          drawTile(x * TILE_SIZE, y * TILE_SIZE, type, g);
        }
      }
    }
    
  }
}
