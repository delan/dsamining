import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
public class TerminalHelper {
	public static final String CSI = "\u001B[";
	public static final String BLOCK =
		CSI + "7m" +            // negative video
		" " +                   // space as "block"
		CSI + "27m"             // positive video
	;
	private PrintWriter out;
	public TerminalHelper() {
		try {
			out = new PrintWriter(
				new OutputStreamWriter(
					System.out,
					"UTF-8"
				),
				true
			);
		} catch (UnsupportedEncodingException e) {
			System.out.println("TerminalHelper: " + e);
		}
	}
	public void print(String s) {
		this.out.print(s);
		this.out.flush();
	}
	public void newScreen(String title, String content) {
		// newScreen needs to do the following things:
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
		print(BLOCK);
		for (int i = 0; i < pad_left; i++)
			print("\u2550");
		print(title);
		for (int i = 0; i < pad_right; i++)
			print("\u2550");
		print(BLOCK);
		print("\n");
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
		print(
			BLOCK + " " +
			content +
			CSI + "80G" +           // go to absolute column 80
			BLOCK + "\n"
		);
		displayFooter();
		// Move the cursor to the natural final position.
		print(
			CSI +
			String.valueOf(final_row) +
			";" +
			String.valueOf(final_column) +
			"H"
		);
	}
	public void displayHeader() {
		print(
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
	public void displayFooter() {
		print(
			CSI + "7m" +            // inverse video
			"                                        " +
			"                                        " +
			CSI + "0m"              // reset graphics rendition
		);
	}
	public void cleanup() {
		print(
			CSI + "2J" +            // clear entire screen
			CSI + "H"               // move cursor to top left
		);
	}
}
