package Number_Game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Random {
	 private static final String USERNAME = "Sai";
	    private static final String PASSWORD = "Sai1";

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(LoginFrame::new);
	    }

	    static class LoginFrame extends JFrame {
	        private JTextField usernameField;
	        private JPasswordField passwordField;

	        LoginFrame() {
	            super("Number Guessing Game");
	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            setResizable(false);

	            // Set the icon
	            ImageIcon icon = new ImageIcon("logo.png");
	            setIconImage(icon.getImage());

	            JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
	            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	            JLabel usernameLabel = new JLabel("Username:");
	            usernameField = new JTextField(20);
	            panel.add(usernameLabel);
	            panel.add(usernameField);

	            JLabel passwordLabel = new JLabel("Password:");
	            passwordField = new JPasswordField(20);
	            panel.add(passwordLabel);
	            panel.add(passwordField);

	            JButton loginButton = new JButton("Login");
	            loginButton.addActionListener(new LoginListener());
	            panel.add(loginButton);

	            add(panel);
	            pack();
	            setLocationRelativeTo(null);
	            setVisible(true);
	        }

	        class LoginListener implements ActionListener {
	            public void actionPerformed(ActionEvent e) {
	                String usernameInput = usernameField.getText();
	                String passwordInput = new String(passwordField.getPassword());

	                if (usernameInput.equals(USERNAME) && passwordInput.equals(PASSWORD)) {
	                    dispose(); 
	                    new NumberGuessingFrame(); 
	                } else {
	                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        }
	    }

	    static class NumberGuessingFrame extends JFrame {
	        private Random random = new Random();
	        private int minRange = 1;
	        private int maxRange = 100;
	        private int attemptsLimit = 3;
	        private int score = 0;

	        NumberGuessingFrame() {
	            super("Number Guessing Game");
	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            setResizable(false);

	            JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
	            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	            JLabel titleLabel = new JLabel("Number Guessing Game");
	            titleLabel.setHorizontalAlignment(JLabel.CENTER);
	            panel.add(titleLabel);

	            JButton startButton = new JButton("Start Game");
	            startButton.addActionListener(new StartGameListener());
	            panel.add(startButton);

	            add(panel);
	            pack();
	            setLocationRelativeTo(null);
	            setVisible(true);
	        }

	        class StartGameListener implements ActionListener {
	            public void actionPerformed(ActionEvent e) {
	                int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;

	                JOptionPane.showMessageDialog(NumberGuessingFrame.this,
	                        "I've selected a number between " + minRange + " and " + maxRange + ". Guess what it is!");

	                int attempts = 0;
	                boolean guessedCorrectly = false;

	                while (attempts < attemptsLimit) {
	                    String guessInput = JOptionPane.showInputDialog(NumberGuessingFrame.this, "Enter your guess:");
	                    int userGuess = Integer.parseInt(guessInput);
	                    attempts++;

	                    if (userGuess == targetNumber) {
	                        JOptionPane.showMessageDialog(NumberGuessingFrame.this,
	                                "Congratulations! You've guessed the correct number.");
	                        guessedCorrectly = true;
	                        break;
	                    } else if (userGuess < targetNumber) {
	                        JOptionPane.showMessageDialog(NumberGuessingFrame.this, "Too low! Try again.");
	                    } else {
	                        JOptionPane.showMessageDialog(NumberGuessingFrame.this, "Too high! Try again.");
	                    }
	                }

	                if (!guessedCorrectly) {
	                    JOptionPane.showMessageDialog(NumberGuessingFrame.this,
	                            "Sorry, you've used all your attempts. The correct number was: " + targetNumber);
	                }

	                score += attempts;

	                JOptionPane.showMessageDialog(NumberGuessingFrame.this, "Your current score: " + score);

	                int playAgain = JOptionPane.showConfirmDialog(NumberGuessingFrame.this, "Do you want to play again?",
	                        "Play Again?", JOptionPane.YES_NO_OPTION);
	                if (playAgain == JOptionPane.NO_OPTION) {
	                    dispose();
	                    JOptionPane.showMessageDialog(null, "Thanks for playing!");
	                }
	            }
	        }
	    }

		public int nextInt(int i) {
			return 0;
		}
		
}
