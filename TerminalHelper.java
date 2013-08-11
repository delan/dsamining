public class TerminalHelper {
	public static final String CSI = "\u001B[";
	public static void newScreen(String content) {
		String block =
			CSI + "7m" +            // set bold text
			" " +                   // space as "block"
			CSI + "0m"              // reset graphics rendition
		;
		// The final position to leave the cursor for prompts, etc.
		int final_row = 3;
		int final_column = 3, default_final_column = 3;
		// Additional newlines to pad with before reaching footer.
		int pad_newlines = 20;
		for (int i = 0; i < content.length(); i++) {
			if (content.charAt(i) == '\n') {
				final_row++;
				final_column = default_final_column;
				pad_newlines--;
			} else {
				final_column++;
			}
		}
		displayHeader();
		// Pad the content with one newline before plus some after.
		content =
			"\n" + content +
			// Follows is a string with (pad_newlines) newlines.
			new String(new char[pad_newlines]).replace("\0", "\n")
		;
		// Insert "walls" on either side of each newline.
		content = content.replaceAll(
			"\n",
			CSI + "80G" +           // go to absolute column 80
			block + "\n" +
			block + " "
		);
		// Print out the content.
		System.out.print(
			block + " " +
			content +
			CSI + "80G" +           // go to absolute column 80
			block + "\n"
		);
		displayFooter();
		// Move the cursor to the natural final position.
		System.out.print(
			CSI +
			String.valueOf(final_row) +
			";" +
			String.valueOf(final_column) +
			"H"
		);
	}
	public static void displayHeader() {
		System.out.print(
			CSI + "2J" +            // clear entire screen
			CSI + "H" +             // move cursor to top left
			CSI + "1m" +            // set bold text
			CSI + "7m" +            // inverse video
			"                             " +
			"DSA Mining Corporation" +
			"                             " +
			CSI + "0m" +            // reset graphics rendition
			"\n"
		);
	}
	public static void displayFooter() {
		System.out.print(
			CSI + "7m" +            // inverse video
			"                                        " +
			"                                        " +
			CSI + "0m"              // reset graphics rendition
		);
	}
	public static void cleanup() {
		System.out.print(
			CSI + "2J" +            // clear entire screen
			CSI + "H"               // move cursor to top left
		);
	}
}
