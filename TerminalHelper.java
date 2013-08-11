import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
public class TerminalHelper {
	private static final int TERM_WIDTH = 80;
	private static final int TERM_HEIGHT = 24;
	private static final String DHRULE = "\u2550";
	private static final String CSI = "\u001B[";
	private static final String BLOCK =
		CSI + "7m" +            // negative video
		" " +                   // space as "block"
		CSI + "27m"             // positive video
	;
	private PrintWriter out;
	private String appTitle;
	public TerminalHelper(String appTitle) {
		this.appTitle = appTitle;
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
	public void newScreen(String pageTitle, String content) {
		// newScreen needs to do the following things:
		// 1. Print the appTitle bold, centred and in negative video.
		// 2. Print the pageTitle centred between double horizontals.
		// 3. Print every line of content with "walls" on either side.
		// 4. Pad with blank lines to force "full-screen".
		// 5. Print a "floor", completing a nice white "box".
		// 6. Move the cursor to the end of the actual content so that
		//    things like text input at a prompt work as intended.
		// Now for the implementation.
		// The final position to leave the cursor for prompts, etc.
		int finalRow = 4;
		int finalCol = 3, defaultFinalCol = 3;
		// Additional newlines to pad with before reaching footer.
		int padNewlines = TERM_HEIGHT - 5;
		String wrappedContent = "";
		// Process the content to figure out the values for above.
		// Also insert newlines to wrap long lines where necessary.
		for (int i = 0; i < content.length(); i++) {
			wrappedContent += content.charAt(i);
			if (content.charAt(i) == '\n') {
				finalRow++;
				finalCol = defaultFinalCol;
				padNewlines--;
			} else {
				finalCol++;
				if (finalCol == TERM_WIDTH - 1) {
					wrappedContent += "\n";
					finalRow++;
					finalCol = defaultFinalCol;
					padNewlines--;
				}
			}
		}
		content = wrappedContent;
		this.printGlobalHeader();
		this.printPageHeader(pageTitle);
		// Pad the content with newlines to fit TERM_HEIGHT.
		content = content + repeatText("\n", padNewlines);
		// Insert "walls" on either side of each newline.
		content = content.replaceAll(
			"\n",
			CSI + TERM_WIDTH + "G" +  // go to column TERM_WIDTH
			BLOCK + "\n" +
			BLOCK + " "
		);
		// Print out the content.
		print(
			BLOCK +
			repeatText(" ", TERM_WIDTH - 2) +
			BLOCK + "\n" + BLOCK + " " +
			content +
			CSI + TERM_WIDTH + "G" +  // go to column TERM_WIDTH
			BLOCK + "\n"
		);
		this.printGlobalFooter();
		// Move the cursor to the natural final position.
		print(
			CSI +
			String.valueOf(finalRow) +
			";" +
			String.valueOf(finalCol) +
			"H"
		);
	}
	public void cleanup() {
		print(
			CSI + "2J" +            // clear entire screen
			CSI + "H"               // move cursor to top left
		);
	}
	private String repeatText(String text, int num) {
		String result = "";
		if (num > 0)
			result = new String(new char[num]).replace("\0", text);
		return result;
	}
	private String centredText(String text, String flank, int width) {
		int left, right;
		String leftFlank, rightFlank;
		text = " " + text + " ";
		left = (int) Math.floor(
			(width - text.length()) / 2.0
		);
		right = (int) Math.ceil(
			(width - text.length()) / 2.0
		);
		leftFlank = repeatText(flank, left);
		rightFlank = repeatText(flank, right);
		return leftFlank + text + rightFlank;
	}
	private String centredText(String text, String flank) {
		return this.centredText(text, flank, TERM_WIDTH);
	}
	private void printGlobalHeader() {
		print(
			CSI + "2J" +            // clear entire screen
			CSI + "H" +             // move cursor to top left
			CSI + "1m" +            // set bold text
			CSI + "7m" +            // inverse video
			centredText(appTitle, " ", TERM_WIDTH) +
			CSI + "0m" +            // reset graphics rendition
			"\n"
		);
	}
	private void printPageHeader(String pageTitle) {
		print(
			BLOCK + " " +
			centredText(pageTitle, DHRULE, TERM_WIDTH - 4) +
			" " + BLOCK + "\n"
		);
	}
	private void printGlobalFooter() {
		print(
			CSI + "7m" +            // inverse video
			repeatText(" ", TERM_WIDTH) +
			CSI + "0m"              // reset graphics rendition
		);
	}
	private void print(String s) {
		this.out.print(s);
		this.out.flush();
	}
}
