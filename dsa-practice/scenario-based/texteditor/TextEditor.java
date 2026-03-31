package texteditor;

public class TextEditor {
    public static void main(String[] args) {
        String para = "   life is unpredictable,it throws challenges at us.   sometimes we stumble!but we keep moving forward?   the journey teaches lessons,   yet many ignore them. success is not instant,it requires patience.   without effort nothing changes!and still people hope for miracles?   reality demands persistence,   but distractions are everywhere.";

        StringBuilder sb = new StringBuilder();
        para = para.trim(); // remove leading/trailing spaces
        boolean capitalizeNext = true; // capitalize after sentence-ending punctuation

        for (char c : para.toCharArray()) {
            // Sentence-ending punctuation
            if (c == '.' || c == '!' || c == '?') {
                sb.append(c).append(" ");
                capitalizeNext = true;
                continue;
            }

            // Comma handling (space after comma, but no capitalization)
            if (c == ',') {
                sb.append(c).append(" ");
                continue;
            }

            // Capitalize first letter after sentence-ending punctuation
            if (capitalizeNext && Character.isLetter(c)) {
                sb.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                sb.append(c);
            }
        }

        // Normalize spaces
        String result = sb.toString().replaceAll("\\s+", " ").trim();
        System.out.println("Text : " + result);
    }
}