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
    private JPanel buttonPanel;
    private JFrame frame;
    private Canvas canvasPanel;

    private Dimension buttonDimension = new Dimension(65, 30);
    private Dimension buttonResizeDimension = new Dimension(75, 30);
    private Dimension labelDimension = new Dimension(40, 30);
    private static final int START_SIZE = 50;
    private static final int POINT_RADIUS = 10;
    private static final int PIXEL_STOCK = 15;
    private static final int BTN_PANEL_HEIGHT = 70;
    private static final int BTN_PANEL_WIDTH = 600;
    private static final int STEP_DELAY = 80;
    private int fieldSizeX;
    private int fieldSizeY;
    volatile boolean goNextGeneration = false;
    private int widthLifeField;
    private int heightLifeField;
    private int offsetX;
    private int offsetY;

    private ArrayList<Boolean> lifeGeneration = new ArrayList<>();
    private ArrayList<Boolean> nextGeneration = new ArrayList<>();
    private Random random = new Random();


    public static void main(String[] args) {
        new GameOfLife();
    }


    public GameOfLife() {
        widthLifeField = START_SIZE;
        heightLifeField = START_SIZE;
        fieldSizeX = Math.max(widthLifeField * POINT_RADIUS + PIXEL_STOCK, BTN_PANEL_WIDTH);
        fieldSizeY = heightLifeField * POINT_RADIUS + PIXEL_STOCK;

        offsetX = (fieldSizeX - widthLifeField * POINT_RADIUS - PIXEL_STOCK) / 2;
        offsetY = (fieldSizeY - heightLifeField * POINT_RADIUS - PIXEL_STOCK) / 2;

        frame = new JFrame("Game of life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(fieldSizeX, fieldSizeY + BTN_PANEL_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        ArrayList<Boolean> tmp = new ArrayList<>();
        for (int i = 0; i < widthLifeField * heightLifeField; i++) {
            lifeGeneration.add(false);
            nextGeneration.add(false);
        }


        //animate a cell
        canvasPanel = new Canvas();
        canvasPanel.setBackground(Color.white);
        canvasPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = (e.getX() - offsetX) / POINT_RADIUS ;
                int y = (e.getY() - offsetY) / POINT_RADIUS ;
                int i = widthLifeField * y + x;
                if (x < widthLifeField && x >= 0 && y < heightLifeField && y >= 0 && i < widthLifeField * heightLifeField) {
                    lifeGeneration.set(i, !lifeGeneration.get(i));
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

                        lifeGeneration.clear();
                        for (int i = 0; i < widthLifeField * heightLifeField; i++) {
                            lifeGeneration.add(false);
                            nextGeneration.add(false);
                        }

                        fieldSizeX = Math.max(widthLifeField * POINT_RADIUS + PIXEL_STOCK, BTN_PANEL_WIDTH);
                        fieldSizeY = heightLifeField * POINT_RADIUS + PIXEL_STOCK;
                        offsetX = (fieldSizeX - widthLifeField * POINT_RADIUS - PIXEL_STOCK) / 2;
                        offsetY = (fieldSizeY - heightLifeField * POINT_RADIUS - PIXEL_STOCK) / 2;
                        frame.setSize(fieldSizeX, fieldSizeY + BTN_PANEL_HEIGHT);
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
                    lifeGeneration.set(i, random.nextBoolean());
                }
                goNextGeneration = false;
                runButton.setText("Run");
                canvasPanel.repaint();
            }
        });


        // generation after generation without stopping
        runButton = new JButton("Run");
        runButton.setPreferredSize(buttonDimension);
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goNextGeneration = !goNextGeneration;
                runButton.setText(goNextGeneration ? "Stop" : "Run");
            }
        });


        // clear fields
        clearButton = new JButton("Clear");
        clearButton.setPreferredSize(buttonDimension);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < widthLifeField * heightLifeField; i++) {
                    lifeGeneration.set(i, false);
                }
                goNextGeneration = false;
                runButton.setText("Run");
                canvasPanel.repaint();
            }
        });


        // get the next generation
        stepButton = new JButton("Next");
        stepButton.setPreferredSize(buttonDimension);
        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!goNextGeneration) {
                    processOfLife();
                    canvasPanel.repaint();
                }
            }
        });


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

        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.setVisible(true);


        // endless loop of life
        while (true) {
            if (goNextGeneration) {
                processOfLife();
                canvasPanel.repaint();
                try {
                    Thread.sleep(STEP_DELAY);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }


    // count the number of neighbors
    private int countNeighbors(int i) {
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
                count += (lifeGeneration.get(j)) ? 1 : 0;
            }
        }

        if (lifeGeneration.get(i)) { count--; }
        return count;
    }


    // the main process of life
    private void processOfLife() {
        for (int i = 0; i < widthLifeField * heightLifeField; i++) {
            int count = countNeighbors(i);
            nextGeneration.set(i, lifeGeneration.get(i));
            // if are 3 live neighbors around empty cells - the cell becomes alive
            nextGeneration.set(i, count == 3 || nextGeneration.get(i));
            // if cell has less than 2 or greater than 3 neighbors - it will be die
            nextGeneration.set(i, (count >= 2) && (count <= 3) && nextGeneration.get(i));
        }

        // swap generations
        ArrayList<Boolean> tmp = new ArrayList<>();
        tmp.addAll(nextGeneration);
        nextGeneration.clear();
        nextGeneration.addAll(lifeGeneration);
        lifeGeneration.clear();
        lifeGeneration.addAll(tmp);
    }


    public class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);

            for (int i = 0; i < widthLifeField * heightLifeField; i++) {
                int y = i / widthLifeField;
                int x = i - widthLifeField * y;

                if (lifeGeneration.get(i)) {
                    g.setColor(Color.black);
                    g.fillOval(x * POINT_RADIUS + offsetX, y * POINT_RADIUS + offsetY, POINT_RADIUS, POINT_RADIUS);
                }
                g.setColor(Color.lightGray);

                if (i == 0) {
                    g.drawLine(- 1 + offsetX, offsetY, 1 + offsetX, offsetY);
                    g.drawLine(offsetX, - 1  + offsetY, offsetX, 1  + offsetY);
                }
                if (x == 0) {
                    g.drawLine(- 1 + offsetX, (y + 1) * POINT_RADIUS + offsetY,
                            1 + offsetX, (y + 1) * POINT_RADIUS  + offsetY);
                    g.drawLine(offsetX, (y + 1) * POINT_RADIUS - 1  + offsetY,
                            offsetX, (y + 1) * POINT_RADIUS + 1  + offsetY);

                }
                if (y == 0) {
                    g.drawLine((x + 1) * POINT_RADIUS - 1 + offsetX, offsetY,
                            (x + 1) * POINT_RADIUS + 1 + offsetX, offsetY);
                    g.drawLine((x + 1) * POINT_RADIUS + offsetX, - 1  + offsetY,
                            (x + 1) * POINT_RADIUS + offsetX, 1  + offsetY);
                }

                g.drawLine((x + 1) * POINT_RADIUS - 1 + offsetX, (y + 1) * POINT_RADIUS + offsetY,
                        (x + 1) * POINT_RADIUS + 1 + offsetX, (y + 1) * POINT_RADIUS  + offsetY);
                g.drawLine((x + 1) * POINT_RADIUS + offsetX, (y + 1) * POINT_RADIUS - 1  + offsetY,
                        (x + 1) * POINT_RADIUS + offsetX, (y + 1) * POINT_RADIUS + 1  + offsetY);
            }
        }
    }
}