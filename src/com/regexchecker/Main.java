package com.regexchecker;

import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
        CustomTheme.setup();
        CustomTheme.installLafInfo();
        RegexForm form = new RegexForm();
        ThemeSelectionForm themeSelectionForm = new ThemeSelectionForm(form);
    }
}