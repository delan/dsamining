public class TerminalHelper {
	public static final String CSI = "\u001B[";
	public static final String BLOCK =
		CSI + "7m" +            // set bold text
		" " +                   // space as "block"
		CSI + "0m"              // reset graphics rendition
	;
	public static void newScreen(String title, String content)
		throws java.io.IOException {
		// The final position to leave the cursor for prompts, etc.
		int final_row = 4;
		int final_column = 3, default_final_column = 3;
		// Additional newlines to pad with before reaching footer.
		int pad_newlines = 19;
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
		// Print out the page title.
		byte[] line_char = {
			(byte) 0xE2,
			(byte) 0x95,
			(byte) 0x90
		}; // U+2550 in UTF-8
		title = " " + title + " ";
		int pad_left = (int)Math.floor((80 - title.length()) / 2.0) - 1;
		int pad_right = (int)Math.ceil((80 - title.length()) / 2.0) - 1;
		System.out.print(BLOCK);
		for (int i = 0; i < pad_left; i++)
			System.out.write(line_char);
		System.out.print(title);
		for (int i = 0; i < pad_right; i++)
			System.out.write(line_char);
		System.out.print(BLOCK);
		System.out.print("\n");
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
			BLOCK + "\n" +
			BLOCK + " "
		);
		// Print out the content.
		System.out.print(
			BLOCK + " " +
			content +
			CSI + "80G" +           // go to absolute column 80
			BLOCK + "\n"
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
