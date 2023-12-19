import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SidePanel extends JPanel {
  private static final Font LARGE_FONT = new Font("Arial", Font.BOLD, 20);
  private static final Font MEDIUM_FONT = new Font("Arial", Font.BOLD, 16);
  private static final Font SMALL_FONT = new Font("Arial", Font.BOLD, 12);

  private SnakeGame game;

  public SidePanel(SnakeGame game) {
    this.game = game;

    setPreferredSize(new Dimension(300, BoardPanel.ROW_COUNT * BoardPanel.TILE_SIZE));
		setBackground(Color.BLACK);
  }

  @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);

		g.setFont(LARGE_FONT);
		g.drawString("Cheems Game", getWidth() / 2 - g.getFontMetrics().stringWidth("Cheems Game") / 2, 50);

		g.setFont(MEDIUM_FONT);
		g.drawString("Statistics", 30, 150);
		g.drawString("Controls/How To Move", 30, 320);

		g.setFont(SMALL_FONT);

		int drawY = 150;
		g.drawString("Total Score: " + game.getScore(), 50, drawY += 30);
		g.drawString("Burgers Eaten: " + game.getFruitsEaten(), 50, drawY += 30);
		g.drawString("Burgers Score: " + game.getNextFruitScore(), 50, drawY += 30);

    drawY = 320;
		g.drawString("Move Up: W / Up Arrowkey", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Move Down: S / Down Arrowkey", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Move Left: A / Left Arrowkey", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Move Right: D / Right Arrowkey", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Pause Game: P", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
  }
}