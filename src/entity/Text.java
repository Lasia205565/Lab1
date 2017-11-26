package entity;

public class Text {

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private String[] words;

	public String[] getWords() {
		return words;
	}

	public void setWords(String[] words) {
		this.words = words;
	}

	public void setupWords() {
		StringBuffer newText = new StringBuffer();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				char b = Character.toLowerCase(c);
				newText.append(b);
			}
			if ((c >= 'a' && c <= 'z') || c == ' ') {
				newText.append(c);
			} else {
				newText.append(' ');
			}
		}
		words = newText.toString().trim().split("\\s+");
	}
}
