package org.unit.gui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import one.main.experimental.view.VirtualKeyBoardLayout;

public class GuiTest {

	@Test
	public void testKeyBoard() {
		
		VirtualKeyBoardLayout layout = new VirtualKeyBoardLayout();
		
		
		System.err.println(layout.getUnshifted().length);
		System.err.println(layout.getShifted().length);
		System.err.println(layout.getCodes().length);
//		System.err.println(layout.getModifiers());
	}
}
