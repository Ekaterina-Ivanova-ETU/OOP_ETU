package ShapesForms;

import Shapes.Shape;
import WorkWithFiles.StoreFile;
import WorkWithFiles.StoreShapes;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.IOException;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;


public class MainDialog extends JDialog {
    private JPanel contentPane;
    private JButton removeButton;
    private JButton createCircleButton;
    private JButton createRectangleButton;
    private JButton createSquareButton;
    private JButton createTriangleButton;
    private JButton moveDownButton;
    private JButton moveUpButton;
    private JList shapesList;
    StoreFile<Shape> shapeStoreFile;

    public MainDialog() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Shapes App");
        setSize(600, 400);
        setLocationRelativeTo(null);

        shapeStoreFile = new StoreShapes("shapes.json");
        DefaultListModel<Shape> defaultListModel = new DefaultListModel<>();

        try {
            defaultListModel.addAll(shapeStoreFile.ReadFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        shapesList.setListData(defaultListModel.toArray());

        defaultListModel.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                shapesList.setListData(defaultListModel.toArray());
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
                shapesList.setListData(defaultListModel.toArray());
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
                shapesList.setListData(defaultListModel.toArray());
            }
        });

        moveDownButton.addActionListener(e -> {
            int index = shapesList.getSelectedIndex();
            if (index >= 0 && index + 1 < defaultListModel.size()) {
                Shape temp = defaultListModel.set(index + 1, defaultListModel.get(index));
                defaultListModel.set(index, temp);

                List<Shape> updatedShapes = new ArrayList<>();
                Arrays.stream(defaultListModel.toArray()).forEach(element -> updatedShapes.add((Shape) element));
                try {
                    shapeStoreFile.WriteToFile(updatedShapes);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        moveUpButton.addActionListener(e -> {
            int index = shapesList.getSelectedIndex();
            if (index >= 1 && index < defaultListModel.size()) {
                Shape temp = defaultListModel.set(index - 1, defaultListModel.get(index));
                defaultListModel.set(index, temp);

                List<Shape> updatedShapes = new ArrayList<>();
                Arrays.stream(defaultListModel.toArray()).forEach(element -> updatedShapes.add((Shape) element));
                try {
                    shapeStoreFile.WriteToFile(updatedShapes);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        removeButton.addActionListener(e -> {
            if (shapesList.getSelectedIndex() != -1) {
                defaultListModel.remove(shapesList.getSelectedIndex());

                List<Shape> updatedShapes = new ArrayList<>();
                Arrays.stream(defaultListModel.toArray()).forEach(element -> updatedShapes.add((Shape) element));
                try {
                    shapeStoreFile.WriteToFile(updatedShapes);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        createCircleButton.addActionListener(e -> {
            CircleDialog circleDialog = new CircleDialog(shape -> {
                defaultListModel.addElement(shape);

                List<Shape> updatedShapes = new ArrayList<>();
                Arrays.stream(defaultListModel.toArray()).forEach(element -> updatedShapes.add((Shape) element));
                try {
                    shapeStoreFile.WriteToFile(updatedShapes);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            });

            circleDialog.setVisible(true);
        });

        createSquareButton.addActionListener(e -> {
            SquareDialog squareDialog = new SquareDialog(shape -> {
                defaultListModel.addElement(shape);

                List<Shape> updatedShapes = new ArrayList<>();
                Arrays.stream(defaultListModel.toArray()).forEach(element -> updatedShapes.add((Shape) element));
                try {
                    shapeStoreFile.WriteToFile(updatedShapes);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            });

            squareDialog.setVisible(true);
        });

        createRectangleButton.addActionListener(e -> {
            RectangleDialog rectangleDialog = new RectangleDialog(shape -> {
                defaultListModel.addElement(shape);

                List<Shape> updatedShapes = new ArrayList<>();
                Arrays.stream(defaultListModel.toArray()).forEach(element -> updatedShapes.add((Shape) element));
                try {
                    shapeStoreFile.WriteToFile(updatedShapes);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            });

            rectangleDialog.setVisible(true);
        });

        createTriangleButton.addActionListener(e -> {
            TriangleDialog triangleDialog = new TriangleDialog(shape -> {
                defaultListModel.addElement(shape);

                List<Shape> updatedShapes = new ArrayList<>();
                Arrays.stream(defaultListModel.toArray()).forEach(element -> updatedShapes.add((Shape) element));
                try {
                    shapeStoreFile.WriteToFile(updatedShapes);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            });

            triangleDialog.setVisible(true);
        });


        setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 2, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setBackground(new Color(-1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1));
        contentPane.add(panel1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        createCircleButton = new JButton();
        createCircleButton.setBackground(new Color(-3022357));
        createCircleButton.setForeground(new Color(-16777216));
        createCircleButton.setText("Create Circle");
        panel1.add(createCircleButton, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createRectangleButton = new JButton();
        createRectangleButton.setBackground(new Color(-3022357));
        createRectangleButton.setForeground(new Color(-16777216));
        createRectangleButton.setText("Create Rectangle");
        panel1.add(createRectangleButton, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createSquareButton = new JButton();
        createSquareButton.setBackground(new Color(-3022357));
        createSquareButton.setForeground(new Color(-16777216));
        createSquareButton.setText("Create Square");
        panel1.add(createSquareButton, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createTriangleButton = new JButton();
        createTriangleButton.setBackground(new Color(-3022357));
        createTriangleButton.setForeground(new Color(-16777216));
        createTriangleButton.setText("Create Triangle");
        panel1.add(createTriangleButton, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeButton = new JButton();
        removeButton.setBackground(new Color(-2310726));
        removeButton.setForeground(new Color(-16777216));
        removeButton.setText("Remove");
        panel1.add(removeButton, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        moveDownButton = new JButton();
        moveDownButton.setBackground(new Color(-8069939));
        moveDownButton.setForeground(new Color(-16777216));
        moveDownButton.setText("Move Down");
        panel1.add(moveDownButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        moveUpButton = new JButton();
        moveUpButton.setBackground(new Color(-8069939));
        moveUpButton.setForeground(new Color(-16777216));
        moveUpButton.setText("Move Up");
        panel1.add(moveUpButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        shapesList = new JList();
        shapesList.setBackground(new Color(-1118482));
        shapesList.setForeground(new Color(-16777216));
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        shapesList.setModel(defaultListModel1);
        shapesList.setSelectionBackground(new Color(-657931));
        shapesList.setToolTipText("");
        contentPane.add(shapesList, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-1));
        label1.setForeground(new Color(-16777216));
        label1.setText("Shapes");
        contentPane.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
