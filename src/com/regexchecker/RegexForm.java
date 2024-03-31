package com.regexchecker;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegexForm extends JFrame {
    private JPanel root;
    private JTextField checkingTextField;
    private JButton runCheckButton;
    private JCheckBox ipAddressCheckBox;
    private JCheckBox emailCheckBox;
    private JCheckBox phoneCheckBox;
    private Map<Pattern, JCheckBox> checkList;

    public RegexForm() {
        setVisible(true);
        setSize(500, 500);
        setContentPane(root);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        checkList = new HashMap<>();
        /*
            \d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}
            [\w\d.-]+@\w+\.\w{2,5}
            \+\d{1,3}\(\d{3}\)\d{3}[- ]{1}\d{2}[- ]{1}\d{2}
         */
        checkList.put(Pattern.compile("[\\wd.-]+@\\w+\\.\\w{2,5}"), emailCheckBox);
        checkList.put(Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"), ipAddressCheckBox);
        checkList.put(Pattern.compile("\\+\\d{1,3}\\(\\d{3}\\)\\d{3}[- ]\\d{2}[- ]\\d{2}"), phoneCheckBox);

        runCheckButton.addActionListener(e -> {
            String checkString = checkingTextField.getText();
            checkList.forEach((pattern, jCheckBox) -> jCheckBox.setSelected(pattern.matcher(checkString).matches()));
        });
    }

    private void createUIComponents() {
        emailCheckBox = new JCheckBox();
        emailCheckBox.setEnabled(false);
    }
}
