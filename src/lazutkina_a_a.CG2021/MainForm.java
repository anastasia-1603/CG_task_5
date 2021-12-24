import math.Vector2;
import model.ModelType;
import model.factory.ImageFactory;
import model.factory.OvalFactory;
import model.factory.SquareFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Objects;

public class MainForm extends JDialog implements KeyListener {
    private JPanel contentPane;
    private JPanel panelButtons;
    private JPanel drawPanel;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField weightField;
    private JTextField xField;
    private JTextField yField;
    private JButton buttonCreate;
    private JComboBox<ModelType> comboBox1;
    private JButton chooseColorButton;
    private JButton buttonApply;
    private JButton loadImageButton;
    private JLabel fileStatusLabel;
    private JLabel xLabel;
    private JLabel yLabel;
    private Color color;
    private JFileChooser fileChooser;
    private File image = null;

    public MainForm() {
        $$$setupUI$$$();
        setContentPane(contentPane);
        setModal(true);
        this.setFocusable(true);
        this.addKeyListener(this);
        comboBox1.addItem(ModelType.OVAL);
        comboBox1.addItem(ModelType.SQUARE);
        comboBox1.addItem(ModelType.IMAGE);
        color = Color.BLACK;
        fileStatusLabel.setForeground(Color.RED);
        fileStatusLabel.setText("no file selected");
        buttonCreate.addActionListener(e -> {
            setFactory((ModelType) Objects.requireNonNull(comboBox1.getSelectedItem()));
            if (comboBox1.getSelectedItem() != ModelType.IMAGE || image != null) {
                ((DrawPanel) drawPanel).addObject(
                        Double.parseDouble(weightField.getText()),
                        Double.parseDouble(widthField.getText()),
                        Double.parseDouble(heightField.getText()),
                        new Vector2(Double.parseDouble(xField.getText()),
                                Double.parseDouble(yField.getText())),
                        color, image);
            }

            widthField.setFocusable(false);
            heightField.setFocusable(false);
            weightField.setFocusable(false);
            buttonCreate.setFocusable(false);
            this.setFocusable(true);

        });

        chooseColorButton.addActionListener(e -> {
            color = JColorChooser.showDialog(MainForm.this, "Choose a color", color);
            if (color == null) {
                color = Color.BLACK;
            }
            chooseColorButton.setFocusable(false);
            this.setFocusable(true);
        });

        /*buttonApply.addActionListener(e -> {
            ((DrawPanel) drawPanel).setG(Double.parseDouble(xField.getText()));
            ((DrawPanel) drawPanel).setMu(Double.parseDouble(yField.getText()));

            buttonApply.setFocusable(false);
            this.setFocusable(true);

        });*/


        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Images", "jpg", "jpeg", "png");
        fileChooser.setCurrentDirectory(new File("resources/бабочка.png"));

        fileChooser.setFileFilter(filter);
        loadImageButton.addActionListener(e -> {
            comboBox1.setSelectedItem(ModelType.IMAGE);
            fileChooser.setDialogTitle("Select image");
            int result = fileChooser.showOpenDialog(MainForm.this);

            if (result == JFileChooser.APPROVE_OPTION) {
                image = fileChooser.getSelectedFile();
                fileStatusLabel.setForeground(Color.BLUE);
                fileStatusLabel.setText(image.getName());
            } else {
                if (image == null) {
                    fileStatusLabel.setForeground(Color.RED);
                    fileStatusLabel.setText("no file selected");
                }

            }
            loadImageButton.setFocusable(false);
            this.setFocusable(true);
        });
    }

    public static void main(String[] args) {
        MainForm dialog = new MainForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public void setFactory(ModelType type) {
        switch (type) {
            case OVAL -> ((DrawPanel) drawPanel).setFactory(new OvalFactory());
            case SQUARE -> ((DrawPanel) drawPanel).setFactory(new SquareFactory());
            case IMAGE -> ((DrawPanel) drawPanel).setFactory(new ImageFactory());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        ((DrawPanel) drawPanel).onKeyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        ((DrawPanel) drawPanel).onKeyReleased(e);
    }

    private void createUIComponents() {
        drawPanel = new DrawPanel();
        drawPanel.setSize(500, 500);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        contentPane = new JPanel();
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setOpaque(false);
        contentPane.setPreferredSize(new Dimension(1000, 700));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 5, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(1000, 700), null, 0, false));
        panelButtons = new JPanel();
        panelButtons.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 8, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panelButtons, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 3, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(100, 200), null, 0, false));
        heightField = new JTextField();
        heightField.setText("1");
        panelButtons.add(heightField, new com.intellij.uiDesigner.core.GridConstraints(2, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        weightField = new JTextField();
        weightField.setText("1");
        panelButtons.add(weightField, new com.intellij.uiDesigner.core.GridConstraints(3, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        yField = new JTextField();
        yField.setText("5");
        panelButtons.add(yField, new com.intellij.uiDesigner.core.GridConstraints(2, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Height");
        panelButtons.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Weight");
        panelButtons.add(label2, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        yLabel = new JLabel();
        yLabel.setText("Y");
        panelButtons.add(yLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonApply = new JButton();
        buttonApply.setText("Apply");
        panelButtons.add(buttonApply, new com.intellij.uiDesigner.core.GridConstraints(3, 6, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        comboBox1.setModel(defaultComboBoxModel1);
        panelButtons.add(comboBox1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chooseColorButton = new JButton();
        chooseColorButton.setText("Choose color");
        panelButtons.add(chooseColorButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loadImageButton = new JButton();
        loadImageButton.setText("Load image");
        panelButtons.add(loadImageButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCreate = new JButton();
        buttonCreate.setText("Create");
        panelButtons.add(buttonCreate, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 8, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fileStatusLabel = new JLabel();
        fileStatusLabel.setText("Label");
        panelButtons.add(fileStatusLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Width");
        panelButtons.add(label3, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        widthField = new JTextField();
        widthField.setText("1");
        panelButtons.add(widthField, new com.intellij.uiDesigner.core.GridConstraints(1, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        xLabel = new JLabel();
        xLabel.setText("X");
        panelButtons.add(xLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        xField = new JTextField();
        xField.setText("5");
        panelButtons.add(xField, new com.intellij.uiDesigner.core.GridConstraints(1, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panel1.add(drawPanel, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(400, 400), new Dimension(900, 500), new Dimension(600, 600), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }


}
