package minesweep;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class MainBody
{
	private final static String GAMING = "Gaming";
	private final static String WIN = "Win";
	private final static String LOST = "Lost";
	private final static String WAITING = "Waiting";
	private final static char UNREVEAL_EMPTY = 'E';
	private final static char UNREVEAL_MINE = 'M';
	private final static char REVEAL_BLANK = 'B';
	private final static char REVEAL_MINE = 'X';
	private final static String[] sizeList = new String[]
	{ "25 x 30 (85)", "16 x 16 (40)", "10 x 10 (10)" };
	private final static int[][] sizeArr = new int[][]
	{
			{ 25, 30, 85 },
			{ 16, 16, 40 },
			{ 10, 10, 10 } };

	private JFrame frame;
	private JButton startButton;
	private JLabel totalMine, unrevNum, usedTime, status;
	private JComboBox<String> sizeComboBox;
	private JPanel mineArea;
	private BtnWithPos[][] mineBtns;
	private char[][] inner;

	private boolean isDigit(char c)
	{
		return c >= '0' && c <= '9';
	}

	private void updateMineBtnTextByInner()
	{
		for (int i = 0; i < inner.length; i++)
			for (int j = 0; j < inner[i].length; j++)
			{
				if (isDigit(inner[i][j]))
					mineBtns[i][j].setText("" + inner[i][j]);
				else if (inner[i][j] == REVEAL_MINE)
					mineBtns[i][j].setText("" + inner[i][j]);
				else if (inner[i][j] == REVEAL_BLANK)
				{
					mineBtns[i][j].setBackground(new Color(0xccffff));
					mineBtns[i][j].setOpaque(true);
				}
			}
	}

	private void removeMineBtnsAL(ActionListener l)
	{
		for (int i = 0; i < mineBtns.length; i++)
			for (int j = 0; j < mineBtns[i].length; j++)
				mineBtns[i][j].removeActionListener(l);
	}

	private boolean judgeWin()
	{
		for (int i = 0; i < inner.length; i++)
			for (int j = 0; j < inner[i].length; j++)
				if (inner[i][j] == UNREVEAL_EMPTY)
					return false;
		return true;
	}

	private void initializeInner(int[] size)
	{
		inner = new char[size[0]][size[1]];

		Solution519 sel = new Solution519(size[0], size[1]);
		for (int i = 0; i < size[0]; i++)
			for (int j = 0; j < size[1]; j++)
				inner[i][j] = UNREVEAL_EMPTY;
		for (int i = 0; i < size[2]; i++)
		{
			int[] flippos = sel.flip();
			inner[flippos[0]][flippos[1]] = UNREVEAL_MINE;
		}
	}

	private int updateUnrevNum()
	{
		int ct = 0;
		for (int i = 0; i < inner.length; i++)
			for (int j = 0; j < inner[i].length; j++)
				if (inner[i][j] == UNREVEAL_EMPTY || inner[i][j] == UNREVEAL_MINE)
					ct++;
		unrevNum.setText("Unreveal Block Number = " + ct);
		return ct;
	}

	private static class BtnWithPos extends JButton
	{
		private static final long serialVersionUID = 1L;
		public final int x, y;

		public BtnWithPos(int _x, int _y)
		{
			super();
			x = _x;
			y = _y;
		}
	}

	private class MineBtnsAL implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			BtnWithPos bwp = (BtnWithPos) e.getSource();
			int x = bwp.x, y = bwp.y;

			if (inner[x][y] == UNREVEAL_MINE)
			{
				inner = new Solution529().updateBoard(inner, new int[]
				{ x, y });
				updateMineBtnTextByInner();
				MainBody.this.status.setText(MainBody.LOST);
				MainBody.this.removeMineBtnsAL(this);
				JOptionPane.showMessageDialog(MainBody.this.frame, "Sorry! Game over. Try a new game :-)");
			} else if (inner[x][y] == UNREVEAL_EMPTY)
			{
				inner = new Solution529().updateBoard(inner, new int[]
				{ x, y });
				updateMineBtnTextByInner();
				updateUnrevNum();
				if (MainBody.this.judgeWin())
				{
					MainBody.this.status.setText(MainBody.WIN);
					JOptionPane.showMessageDialog(MainBody.this.frame, "Congratulations! You Win! *^_^*");
				}
			}
			// isDigit(inner[x][y]) || inner[x][y] == REVEAL_BLANK || inner[x][y] ==
			// REVEAL_MINE, do nothing
		}
	}

	private class NewGameBtnAL implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (MainBody.this.status.getText().compareTo(MainBody.GAMING) == 0)
				return;

			int[] size = sizeArr[sizeComboBox.getSelectedIndex()];

			MainBody.this.status.setText(MainBody.GAMING);
			MainBody.this.totalMine.setText("Total Mine = " + size[2]);

			JPanel jp = MainBody.this.mineArea;
			jp.removeAll();
			jp.setLayout(new GridLayout(size[0], size[1]));

			mineBtns = new BtnWithPos[size[0]][size[1]];
			MineBtnsAL mbal = new MineBtnsAL();
			for (int i = 0; i < size[0]; i++)
				for (int j = 0; j < size[1]; j++)
				{
					mineBtns[i][j] = new BtnWithPos(i, j);
					mineBtns[i][j].addActionListener(mbal);
					mineBtns[i][j].setFont(new Font("Serif", 1, 15));
					jp.add(mineBtns[i][j]);
				}

			initializeInner(size);
			updateUnrevNum();
			updateMineBtnTextByInner();

			new Thread(new TimeUpdater()).start();// time counter
		}
	}

	public static class Solution519
	{
		private int rows, cols, selnum;
		private int[] selected = new int[1010];
		private Random rd = new Random();

		public Solution519(int n_rows, int n_cols)
		{
			rows = n_rows;
			cols = n_cols;
			selnum = 0;
		}

		public int[] flip()
		{
			int p = rd.nextInt(rows * cols - selnum);
			int j = 0;
			while (j < selnum && selected[j] <= p)
			{
				p++;
				j++;
			}
			if (j == selnum)
				selected[selnum++] = p;
			else
			{
				for (int i = selnum; i > j; i--)
					selected[i] = selected[i - 1];
				selnum++;
				selected[j] = p;
			}

			return new int[]
			{ p / cols, p % cols };
		}

		public void reset()
		{
			selnum = 0;
		}
	}

	public static class Solution529
	{
		private final static int[][] di = new int[][]
		{
				{ 1, 0 },
				{ 1, 1 },
				{ 0, 1 },
				{ -1, 1 },
				{ -1, 0 },
				{ -1, -1 },
				{ 0, -1 },
				{ 1, -1 } };

		public char[][] updateBoard(char[][] board, int[] click)
		{
			if (board[click[0]][click[1]] == MainBody.UNREVEAL_MINE)
			{
				board[click[0]][click[1]] = MainBody.REVEAL_MINE;
				return board;
			}
			int row = board.length, col = board[0].length;
			int[][] queue = new int[row * col][2];
			Set<Integer> used = new HashSet<Integer>();
			queue[0][0] = click[0];
			queue[0][1] = click[1];
			used.add(click[0] * col + click[1]);
			int f = 0, r = 1;
			while (f < r)
			{
				int x = queue[f][0], y = queue[f][1];
				f++;
				int nearMineNum = 0;
				for (int i = 0; i < 8; i++)
				{
					int nx = x + di[i][0], ny = y + di[i][1];
					if (nx >= 0 && ny >= 0 && nx < row && ny < col && board[nx][ny] == MainBody.UNREVEAL_MINE)
						nearMineNum++;
				}
				if (nearMineNum > 0)
					board[x][y] = (char) (nearMineNum + '0');
				else
				{
					board[x][y] = MainBody.REVEAL_BLANK;
					for (int i = 0; i < 8; i++)
					{
						int nx = x + di[i][0], ny = y + di[i][1];
						if (nx >= 0 && ny >= 0 && nx < row && ny < col && board[nx][ny] == MainBody.UNREVEAL_EMPTY
								&& !used.contains(nx * col + ny))
						{
							queue[r][0] = nx;
							queue[r][1] = ny;
							used.add(nx * col + ny);
							r++;
						}
					}
				}
			}
			return board;
		}
	}

	private class TimeUpdater implements Runnable
	{
		private final Date startDate = new Date();

		@Override
		public void run()
		{
			while (MainBody.this.status.getText().compareTo(MainBody.GAMING) == 0)
			{
				long dt = ((long) new Date().getTime()) - ((long) startDate.getTime());
				MainBody.this.usedTime.setText("Used Time = " + (dt / 1000));
				try
				{
					Thread.sleep(1000);
				} catch (InterruptedException e)
				{
					System.out.println(e.toString());
					e.printStackTrace();
				}
			}
		}
	}

	private class HelpButtonAL implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (MainBody.this.status.getText().compareTo(MainBody.GAMING) != 0)
				return;
			char[][] inner = MainBody.this.inner;
			for (int i = 0; i < inner.length; i++)
				for (int j = 0; j < inner[i].length; j++)
				{
					if (isDigit(inner[i][j]))
					{
						int unKn = 0;
						for (int k = 0; k < 8; k++)
						{
							int x = i + Solution529.di[k][0];
							int y = j + Solution529.di[k][1];
							if (x >= 0 && y >= 0 && x < inner.length && y < inner[0].length
									&& (inner[x][y] == MainBody.UNREVEAL_EMPTY
											|| inner[x][y] == MainBody.UNREVEAL_MINE))
								unKn++;
						}
						if (unKn != inner[i][j] - '0')
						{
							new Thread(new Helper(i, j)).start();
							return;
						}
					}
				}
		}
	}

	private class Helper implements Runnable
	{
		private final int x, y;

		public Helper(int _x, int _y)
		{
			x = _x;
			y = _y;
		}

		@Override
		public void run()
		{
			BtnWithPos bn = MainBody.this.mineBtns[x][y];
			bn.setBackground(Color.GREEN);
			bn.setOpaque(true);
			bn.repaint();
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			bn.setOpaque(false);
			bn.repaint();
		}
	}

	private void addComponents()
	{
		Container ct = frame.getContentPane();
		ct.setLayout(null);

		JLabel tmpll = new JLabel("Rule: Left click to dig, until all unrevealed blocks are mines.");
		ct.add(tmpll);
		tmpll.setBounds(10, 10, 1000, 30);
		tmpll.setFont(new Font("Dialog", 1, 25));

		JPanel line2 = new JPanel();
		line2.setBounds(10, 60, 1000, 30);
		line2.setLayout(new GridLayout(1, 2));
		line2.add(startButton = new JButton("New Game"));
		startButton.setFont(new Font("Dialog", 1, 25));
		startButton.setMnemonic(java.awt.event.KeyEvent.VK_N);
		line2.add(sizeComboBox = new JComboBox<String>(sizeList));
		sizeComboBox.setFont(new Font("Dialog", 1, 25));

		startButton.addActionListener(new NewGameBtnAL());

		ct.add(line2);

		JPanel line3 = new JPanel();

		line3.setBounds(10, 110, 1300, 30);
		line3.setLayout(new GridLayout(1, 3));
		line3.add(totalMine = new JLabel("Total Mine = ??"));
		totalMine.setFont(new Font("Serif", 1, 25));
		line3.add(unrevNum = new JLabel("Unreveal Block Number = ??"));
		unrevNum.setFont(new Font("Serif", 1, 25));
		line3.add(usedTime = new JLabel("Used Time = ??"));
		usedTime.setFont(new Font("Serif", 1, 25));

		ct.add(line3);

		mineArea = new JPanel();
		mineArea.setBounds(10, 160, 1300, 660);

		ct.add(mineArea);

		JPanel line5 = new JPanel();
		ct.add(line5);
		line5.setBounds(10, 820, 1000, 30);
		line5.setLayout(new GridLayout(1, 2));
		line5.add(status = new JLabel(WAITING));
		status.setFont(new Font("MONOSPACED", 1, 30));

		JButton hlp = new JButton("Help ~~");
		hlp.setMnemonic(java.awt.event.KeyEvent.VK_H);
		line5.add(hlp);
		hlp.addActionListener(new HelpButtonAL());
		hlp.setFont(new Font("Dialog", 1, 30));
	}

	public void createAndShowGUI()
	{
		// Create and set up the window.
		frame = new JFrame("Mine Sweeper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		addComponents();

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}

public class MineSweeper
{
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new MainBody().createAndShowGUI();
			}
		});
	}
}
