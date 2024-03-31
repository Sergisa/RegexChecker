package com.regexchecker;

import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ThemeSelectionForm extends JFrame {
    private JPanel root;
    private JComboBox<UIManager.LookAndFeelInfo> comboBox1;

    public ThemeSelectionForm(JFrame relatedFrame) {
        setLocation(
                relatedFrame.getX() + relatedFrame.getWidth(),
                relatedFrame.getY()
        );
        setSize(500, 200);
        setVisible(true);
        setContentPane(root);
        setTitle("Выбор темы");
    }

    private void createUIComponents() {
        comboBox1 = new JComboBox<>();
        comboBox1.setRenderer(new LookAndFeelRenderer());
        comboBox1.setEditor(new LookAndFeelComboEditor());
        for (UIManager.LookAndFeelInfo currentLookAndFeelInfo : UIManager.getInstalledLookAndFeels()) {
            comboBox1.addItem(currentLookAndFeelInfo);
        }
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo) comboBox1.getModel().getSelectedItem();
                    UIManager.installLookAndFeel(info);
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                        SwingUtilities.updateComponentTreeUI(ThemeSelectionForm.this.getRootPane());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                             UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private static class LookAndFeelComboEditor extends BasicComboBoxEditor {
        @Override
        public void setItem(Object anObject) {
            if (anObject == null) {
                return;
            }
            UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo) anObject;
            System.out.println(anObject.getClass().toString());
            ((JTextField) getEditorComponent()).setText(info.getName());
        }
    }

    static class LookAndFeelRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo) value;
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setText(info.getName());
            return this;
        }
    }
}
