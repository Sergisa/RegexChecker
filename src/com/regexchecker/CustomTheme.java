package com.regexchecker;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class CustomTheme
	extends FlatMacLightLaf
{
	public static final String NAME = "CustomTheme";

	public static boolean setup() {
		return setup( new CustomTheme() );
	}

	public static void installLafInfo() {
		installLafInfo( NAME, CustomTheme.class );
	}

	@Override
	public String getName() {
		return NAME;
	}
}
