package ie.dit.dt211.java;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import static java.lang.System.err;

/**
 * Controller
 *
 */
public class MatchingGame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 4;
	private static final int DEFAULT_HEIGHT = 5;
	private static final int DEFAULT_MATCH_COUNT = 2;
	private JPanel contentPane;
	private GameTiles gameTiles;
	private Tile[][] tileGrid;

	private JTextField timerField;
	private JTextField scoreField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatchingGame window = new MatchingGame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MatchingGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the game display.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = createContentPane();
		setContentPane(contentPane);
		contentPane.add(createInfoDisplay(), BorderLayout.NORTH);
		contentPane.add(createBoard(), BorderLayout.CENTER);
		new InfoUpdater(gameTiles.count()).start();
		revealAll(false);
	}
	
	// The content pane from which everything hangs
	private JPanel createContentPane() {
		JPanel pane = new JPanel();
		pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		pane.setLayout(new BorderLayout(0, 0));
		return pane;
	}

	// Top level container for the match count and timer displays
	private Container createInfoDisplay() {
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(1, 2, 0, 0));
		infoPanel.add(createMatchesDisplay());
		infoPanel.add(createTimerDisplay());
		return infoPanel;
	}
	
	// Set up the match count display
	private Container createMatchesDisplay() {
		JPanel matchesPanel = new JPanel(new FlowLayout());
		Font font = new Font("Monaco", Font.BOLD, 26);
		JLabel scoreLabel = new JLabel("Matched");
		scoreLabel.setFont(font);
		matchesPanel.add(scoreLabel);
		scoreField = new JTextField();
		matchesPanel.add(scoreField);
		scoreField.setPreferredSize(new Dimension(85, 40));
		scoreField.setFont(font);
		scoreField.setHorizontalAlignment(JTextField.RIGHT);
		return matchesPanel;
	}
	
	// Set up the timer display
	private Container createTimerDisplay() {
		JPanel timerPanel = new JPanel(new FlowLayout());
		Font font = new Font("Monaco", Font.BOLD, 26);
		JLabel timerLabel = new JLabel("Elapsed");
		timerLabel.setFont(font);
		timerPanel.add(timerLabel);
		timerField = new JTextField();
		timerPanel.add(timerField);
		timerField.setPreferredSize(new Dimension(85, 40));
		timerField.setFont(font);
		timerField.setHorizontalAlignment(JTextField.RIGHT);
		return timerPanel;
	}

	// Position each tile in sequence in a grid layout
	private Container createBoard() {
		JPanel board = new JPanel();
		board.setLayout(new GridLayout(DEFAULT_HEIGHT, DEFAULT_WIDTH, 2, 2));
		chooseTiles();
		for (int x = 0; x < DEFAULT_WIDTH; x++) {
			for (int y = 0; y < DEFAULT_HEIGHT; y ++) {
				board.add(tileGrid[x][y]);
			}
		}
		return board;
	}
	
	// Initialise the tile grid from the randomly chosen game tiles
	private void chooseTiles() {
		PictureSet set = new PictureSet(PictureSet.NUMERIC);
		gameTiles = new GameTiles(set, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_MATCH_COUNT);
		gameTiles.assignTiles();
		tileGrid = new Tile[DEFAULT_WIDTH][DEFAULT_HEIGHT];
		for (int x = 0; x < DEFAULT_WIDTH; x++) {
			for (int y = 0; y < DEFAULT_HEIGHT; y ++) {
				tileGrid[x][y] = new Tile(gameTiles.getImage(x, y));
			}
		}
	}
	
	// Show or hide all tiles
	private void revealAll(boolean show) {
		for (int x = 0; x < DEFAULT_WIDTH; x++) {
			for (int y = 0; y < DEFAULT_HEIGHT; y ++) {
				reveal(x, y, show);
			}
		}
	}

	// Show or hide a tile
	private void reveal(int x, int y, boolean show) {
		if (show) {
			tileGrid[x][y].show();
		} else {
			tileGrid[x][y].hide();
		}
	}
	
	// Look at all of the tiles in the grid and count how many are currently matched
	private int countMatches() {
		int n = 0;
		for (int x = 0; x < DEFAULT_WIDTH; x++) {
			for (int y = 0; y < DEFAULT_HEIGHT; y ++) {
				if (tileGrid[x][y].isMatched()) {
					n++;
				}
			}
		}
		return n;
	}
	
	/**
	 * View
	 * 
	 * The tile is just a JPanel with an associated image which we can check later
	 * in the Matcher class
	 * @author brian
	 */
	private class Tile extends JPanel {
		private static final long serialVersionUID = -9180977451235714100L;
		final BufferedImage image;
		final ImageIcon icon;
		final JToggleButton button;
		final Font font = new Font("Monaco", Font.BOLD, 60);
		private boolean matched;
		
		Tile(BufferedImage image) {
			this.image = image;
			this.icon = new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_FAST));
			button = new JToggleButton();
			button.setBorderPainted(true);
			button.setFont(font);
			button.setPreferredSize(new Dimension(100, 100));
			button.addActionListener(new Matcher(this));
			this.add(button);
		}
		
		// Need this as we've overridden JPanel
		@Override public void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
		
		// Toggle icon on
		public void show() {
			button.setText(null);
			button.setIcon(icon);
		}
		
		// Toggle icon off
		public void hide() {
			button.setIcon(null);
			button.setText("?");
		}
		
		// Good idea to hide how we do the matching
		public boolean matches(Tile other) {
			return this.image.equals(other.image);
		}
		
		public boolean isMatched() {
			return matched;
		}

		public void setMatched(Tile other) {
			this.matched = other.matched = true;
		}
	}
	
	/**
	 * Controller
	 * 
	 * List for tile clicks and check for matches
	 */
	private static class Matcher implements ActionListener {
		private Tile tile;
		private static Tile last = null;	// Shared across all instances
		
		public Matcher(Tile tile) {
			this.tile = tile;
		}

		@Override public void actionPerformed(ActionEvent ev) {
			// User has clicked on a tile so show it and check if it matches
			tile.show();
			checkMatch(tile);
		}
		
		private void checkMatch(final Tile tile) {
			if (last == null) {
				// First of a pair to be clicked
				last = tile;
			} else {
				// Second of a pair to be clicked
				if (tile.matches(last)) {
					// Success, we have a match
					tile.setMatched(last);
					last = null;
				} else {
					// Not this time. Pause and then hide both tiles again (in a separate thread)
					EventQueue.invokeLater(new Runnable() {
						@Override
						public void run() {
							try {
								Thread.sleep(750);
								tile.hide();
								last.hide();
								last = null;
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			}
		}
	}
	
	/**
	 * Controller
	 * 
	 * This thread is responsible to maintaining the info display contents, including
	 * updating the seconds timer value
	 *
	 */
	private class InfoUpdater extends Thread {
		int seconds;
		int matchCount;
		private int matchMax;
		
		InfoUpdater(int matchMax) {
			this.matchMax = matchMax;
		}
		
		public void run() {
			do {
				matchCount = countMatches();
				scoreField.setText(Integer.toString(matchCount));
				timerField.setText(Integer.toString(seconds++));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} while (matchCount < matchMax);
			JOptionPane.showMessageDialog(MatchingGame.this,
					"Game Over!", "User Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
