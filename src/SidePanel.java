import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SidePanel extends JPanel {
  private static final Font LARGE_FONT = new Font("Arial", Font.BOLD, 20);
  private static final Font MEDIUM_FONT = new Font("Arial", Font.BOLD, 16);
  private static final Font SMALL_FONT = new Font("Arial", Font.BOLD, 12);

  Image sideImage;

  private CheemsGame game;

  public SidePanel(CheemsGame game) {
    this.game = game;

    setPreferredSize(new Dimension(300, BoardPanel.ROW * BoardPanel.TILE_SIZE));
	setBackground(Color.WHITE);
	sideImage  = new ImageIcon(this.getClass().getResource("images/doge-cheems1.gif")).getImage();

  }

  @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(sideImage,-40,50,null);

		g.setColor(Color.BLACK);

		g.setFont(LARGE_FONT);
		g.drawString("Cheems's Burger Adventure", getWidth() / 2 - g.getFontMetrics().stringWidth("Cheems's Burger Adventure") / 2, 50);

		g.setFont(MEDIUM_FONT);
		g.drawString("Statistics", 30, 150);
		g.drawString("Controls/How To Move", 30, 320);

		g.setFont(SMALL_FONT);

		int drawY = 150;
		g.drawString("Total Score: " + game.getScore(), 50, drawY += 30);
		g.drawString("Burgers Eaten: " + game.getFruitsEaten(), 50, drawY += 30);
		g.drawString("Burgers Score: " + game.getNextFruitScore(), 50, drawY += 30);

    	drawY = 320;
		g.drawString("Move Up: W / Up Arrowkey", 50, drawY += 30);
		g.drawString("Move Down: S / Down Arrowkey", 50, drawY += 30);
		g.drawString("Move Left: A / Left Arrowkey", 50, drawY += 30);
		g.drawString("Move Right: D / Right Arrowkey", 50, drawY += 30);
		g.drawString("Pause Game: P", 50, drawY += 30);
  }
}
