import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Boolean;

public class GameOfLife {
    private JLabel widthLabel;
    private JLabel heigthLabel;
    private JTextField widthText;
    private JTextField heightText;
    private JButton resizeButton;
    private JButton runButton;
    private JButton fillButton;
    private JButton clearButton;
    private JButton stepButton;
    private Canvas canvasPanel;
    private JPanel buttonPanel;
    private JFrame frame;

    private Dimension buttonDimension = new Dimension(65, 30);
    private Dimension buttonResizeDimension = new Dimension(75, 30);
    private Dimension labelDimension = new Dimension(40, 30);
    private Random random = new Random();
    private static final int START_SIZE = 50;
    private static final int CELL_RADIUS = 10;
    private static final int PIXEL_STOCK = 15;
    private static final int HEIGHT_BUTTON_PANEL = 70;
    private static final int WIDTH_BUTTON_PANEL = 600;
    private static final int STEP_DELAY = 80;
    volatile boolean goNextGeneration = false;
    private int fieldSizeX;
    private int fieldSizeY;
    private int widthLifeField;
    private int heightLifeField;
    private int offsetX;
    private int offsetY;

    private ArrayList<Boolean> currentGeneration = new ArrayList<>();
    private ArrayList<Boolean> nextGeneration = new ArrayList<>();



    public static void main(String[] args) {
        new GameOfLife();
    }


    public GameOfLife() {
        widthLifeField = START_SIZE;
        heightLifeField = START_SIZE;
        fieldSizeX = Math.max(widthLifeField * CELL_RADIUS + PIXEL_STOCK, WIDTH_BUTTON_PANEL);
        fieldSizeY = heightLifeField * CELL_RADIUS + PIXEL_STOCK;

        offsetX = (fieldSizeX - widthLifeField * CELL_RADIUS - PIXEL_STOCK) / 2;
        offsetY = (fieldSizeY - heightLifeField * CELL_RADIUS - PIXEL_STOCK) / 2;

        frame = new JFrame("Game of life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(fieldSizeX, fieldSizeY + HEIGHT_BUTTON_PANEL);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        for (int i = 0; i < widthLifeField * heightLifeField; i++) {
            currentGeneration.add(false);
            nextGeneration.add(false);
        }


        // animate a cell
        canvasPanel = new Canvas();
        canvasPanel.setBackground(Color.white);
        canvasPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = (e.getX() - offsetX) / CELL_RADIUS;
                int y = (e.getY() - offsetY) / CELL_RADIUS;
                int i = widthLifeField * y + x;
                if (x < widthLifeField && x >= 0 && y < heightLifeField && y >= 0 && i < widthLifeField * heightLifeField) {
                    currentGeneration.set(i, !currentGeneration.get(i));
                }
                canvasPanel.repaint();
            }
        });


        widthLabel = new JLabel("Width:");
        heigthLabel = new JLabel("Height:");
        String startSize = Integer.toString(START_SIZE);
        widthText = new JTextField(startSize);
        heightText = new JTextField(startSize);
        widthText.setPreferredSize(labelDimension);
        heightText.setPreferredSize(labelDimension);

        // resize cell field
        resizeButton = new JButton("Resize");
        resizeButton.setPreferredSize(buttonResizeDimension);
        resizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int x = 0;
                int y = 0;
                boolean error = false;

                try {
                    x = Integer.parseInt(widthText.getText());
                    y = Integer.parseInt(heightText.getText());
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "The value is entered incorrectly.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }

                if (error == false) {
                    if (x < 5 || y < 5 || x > 80 || y > 80) {
                        JOptionPane.showMessageDialog(null, "Too small or too large field.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        widthLifeField = x;
                        heightLifeField = y;

                        currentGeneration.clear();
                        for (int i = 0; i < widthLifeField * heightLifeField; i++) {
                            currentGeneration.add(false);
                            nextGeneration.add(false);
                        }

                        fieldSizeX = Math.max(widthLifeField * CELL_RADIUS + PIXEL_STOCK, WIDTH_BUTTON_PANEL);
                        fieldSizeY = heightLifeField * CELL_RADIUS + PIXEL_STOCK;
                        offsetX = (fieldSizeX - widthLifeField * CELL_RADIUS - PIXEL_STOCK) / 2;
                        offsetY = (fieldSizeY - heightLifeField * CELL_RADIUS - PIXEL_STOCK) / 2;
                        frame.setSize(fieldSizeX, fieldSizeY + HEIGHT_BUTTON_PANEL);
                    }
                }

                goNextGeneration = false;
                runButton.setText("Run");
                canvasPanel.repaint();
            }
        });


        // randomly fill cells
        fillButton = new JButton("Fill");
        fillButton.setPreferredSize(buttonDimension);
        fillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < widthLifeField * heightLifeField; i++) {
                    currentGeneration.set(i, random.nextBoolean());
                }
                goNextGeneration = false;
                runButton.setText("Run");
                canvasPanel.repaint();
            }
        });


        //non-stop generation of a new generation of cells
        runButton = new JButton("Run");
        runButton.setPreferredSize(buttonDimension);
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goNextGeneration = !goNextGeneration;
                runButton.setText(goNextGeneration ? "Stop" : "Run");
            }
        });


        // clear the cell field
        clearButton = new JButton("Clear");
        clearButton.setPreferredSize(buttonDimension);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < widthLifeField * heightLifeField; i++) {
                    currentGeneration.set(i, false);
                }
                goNextGeneration = false;
                runButton.setText("Run");
                canvasPanel.repaint();
            }
        });


        // get the next generation of cells
        stepButton = new JButton("Next");
        stepButton.setPreferredSize(buttonDimension);
        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!goNextGeneration) {
                    cellLifeRules();
                    canvasPanel.repaint();
                }
            }
        });


        // add the necessary elements to the panel
        buttonPanel = new JPanel();
        buttonPanel.add(widthLabel);
        buttonPanel.add(widthText);
        buttonPanel.add(heigthLabel);
        buttonPanel.add(heightText);
        buttonPanel.add(resizeButton);
        buttonPanel.add(fillButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(stepButton);
        buttonPanel.add(runButton);

        // add a draw panel (cell field) and button panel to the main window
        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.setVisible(true);


        // endless cycle of cell life
        while (true) {
            if (goNextGeneration) {
                cellLifeRules();
                canvasPanel.repaint();
                try {
                    Thread.sleep(STEP_DELAY);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }


    // count the number of neighbors of the cell
    private int numberOfNeighbors(int i) {
        int count = 0;
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                int y = i/ widthLifeField;
                int x = i - widthLifeField * y;
                int nY = y + dy;
                int nX = x + dx;

                nX = (nX < 0) ? widthLifeField - 1 : nX;
                nY = (nY < 0) ? heightLifeField - 1 : nY;
                nX = (nX > widthLifeField - 1) ? 0 : nX;
                nY = (nY > heightLifeField - 1) ? 0 : nY;

                int j = widthLifeField * nY + nX;
                count += (currentGeneration.get(j)) ? 1 : 0;
            }
        }

        if (currentGeneration.get(i)) { count--; }
        return count;
    }


    // calculation of a new generation of cells
    private void cellLifeRules() {
        for (int i = 0; i < widthLifeField * heightLifeField; i++) {
            int count = numberOfNeighbors(i);

            // determination of a state of the cell
            nextGeneration.set(i, currentGeneration.get(i));
            //if there are exactly 3 living cells next to the cell, then life is born in it
            nextGeneration.set(i, count == 3 || nextGeneration.get(i));
            // if a living cell has 2 or 3 neighbors, then this cell continues to live, otherwise it dies
            nextGeneration.set(i, (count >= 2) && (count <= 3) && nextGeneration.get(i));
        }

        ArrayList<Boolean> tmp = new ArrayList<>();
        tmp.addAll(nextGeneration);
        nextGeneration.clear();
        nextGeneration.addAll(currentGeneration);
        currentGeneration.clear();
        currentGeneration.addAll(tmp);
    }


    // draw a field (grid and live cells)
    public class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);

            for (int i = 0; i < widthLifeField * heightLifeField; i++) {
                int y = i / widthLifeField;
                int x = i - widthLifeField * y;

                if (currentGeneration.get(i)) {
                    g.setColor(Color.black);
                    g.fillOval(x * CELL_RADIUS + offsetX, y * CELL_RADIUS + offsetY, CELL_RADIUS, CELL_RADIUS);
                }
                g.setColor(Color.lightGray);

                if (i == 0) {
                    g.drawLine(- 1 + offsetX, offsetY, 1 + offsetX, offsetY);
                    g.drawLine(offsetX, - 1  + offsetY, offsetX, 1  + offsetY);
                }
                if (x == 0) {
                    g.drawLine(- 1 + offsetX, (y + 1) * CELL_RADIUS + offsetY,
                            1 + offsetX, (y + 1) * CELL_RADIUS + offsetY);
                    g.drawLine(offsetX, (y + 1) * CELL_RADIUS - 1  + offsetY,
                            offsetX, (y + 1) * CELL_RADIUS + 1  + offsetY);

                }
                if (y == 0) {
                    g.drawLine((x + 1) * CELL_RADIUS - 1 + offsetX, offsetY,
                            (x + 1) * CELL_RADIUS + 1 + offsetX, offsetY);
                    g.drawLine((x + 1) * CELL_RADIUS + offsetX, - 1  + offsetY,
                            (x + 1) * CELL_RADIUS + offsetX, 1  + offsetY);
                }

                g.drawLine((x + 1) * CELL_RADIUS - 1 + offsetX, (y + 1) * CELL_RADIUS + offsetY,
                        (x + 1) * CELL_RADIUS + 1 + offsetX, (y + 1) * CELL_RADIUS + offsetY);
                g.drawLine((x + 1) * CELL_RADIUS + offsetX, (y + 1) * CELL_RADIUS - 1  + offsetY,
                        (x + 1) * CELL_RADIUS + offsetX, (y + 1) * CELL_RADIUS + 1  + offsetY);
            }
        }
    }
}