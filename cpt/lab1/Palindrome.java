public class Palindrome {

    /** Разворачивает строку посимвольно. */
    public static String reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    /** Истинно, если s совпадает со своей обратной записью (учитывается регистр). */
    public static boolean isPalindrome(String s) {
        return s.equals(reverseString(s));
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            System.out.println(s + " -> " + (isPalindrome(s) ? "palindrome" : "not palindrome"));
        }
    }
}
