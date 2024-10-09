package xyz.zhouxy.plusone.commons.util;

public class StringTools {

    public static boolean isNotBlank(final String cs) {
        if (cs == null || cs.isEmpty()) {
            return false;
        }
        for (int i = 0; i < cs.length(); i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private StringTools() {
        throw new IllegalStateException("Utility class");
    }
}
