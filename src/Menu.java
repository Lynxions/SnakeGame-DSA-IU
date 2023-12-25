import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    public Menu() {
        setTitle("Cheems Game Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JButton normalModeButton = new JButton("Normal Mode");
        JButton frenzyModeButton = new JButton("Frenzy Mode");

        normalModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("normal");
            }
        });

        frenzyModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("frenzy");
            }
        });

        panel.add(normalModeButton);
        panel.add(frenzyModeButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startGame(String mode) {
        // Based on the selected mode, create a new instance of CheemsGame
        CheemsGame cheemsGame = new CheemsGame();
        
        // Additional logic based on the selected mode can be added here

        // Start the game
        cheemsGame.startGame();

        // Close the menu window
        dispose();
    }

    public static void main(String[] args) {
        // Create an instance of the Menu class to display the menu window
        Menu menu = new Menu();
    }
}
