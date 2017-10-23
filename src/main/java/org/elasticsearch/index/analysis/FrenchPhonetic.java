package org.elasticsearch.index.analysis;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;

import java.util.*;

//1 for in sound
//2 for é sound
//3 for an sound
//4 for on sound
//5 for S sound
//8 for oeu/eu
public class FrenchPhonetic implements StringEncoder {

    private static final List<Character> VOWELS = Arrays.asList('A', 'E', '2', 'I', 'O', 'U', 'Y');

    private static final List<Character> MUTED_ENDED_CONSONANT = Arrays.asList('C', 'D', 'H', 'G', 'P', 'S', 'T', 'X', 'Z');

    private static final List<Character> MUTED_S_PRECEDING_VOWEL = Arrays.asList('A', 'O');

    private static final List<Character> MUTED_S_FOLLOWING_CONSONANT = Arrays.asList('B', 'J', 'M');

    private static final List<Character> DOUBLE_CONSONANT = Arrays.asList('C', 'P', 'R', 'T', 'Z', 'N', 'M', 'G', 'L', 'F');

    private static final List<Character> SOUND_2_ACCENTUATED_CHARS = Arrays.asList('É', 'È', 'Ê', 'Ë');

    @Override
    public String encode(String s) throws EncoderException {
        String cleanedString = clean(s);

        if (cleanedString == null || cleanedString.length() == 0) {
            return cleanedString;
        }
        return operatePhonetic("", charAt(cleanedString, 0), substring(cleanedString, 1, cleanedString.length()));
    }

    private String operatePhonetic(String acc, Character c, String tail) {

        if (c == null) {
            return acc;
        }

        if (tail == null || tail.isEmpty()) {

            //Trailing muted consonant
            if (MUTED_ENDED_CONSONANT.contains(c)) {
                if (c != 'X') {
                    return operatePhonetic(
                            substring(acc, 0, acc.length() - 1),
                            charAt(acc, acc.length() - 1),
                            ""
                    );
                } else {

                    //1X must be pronounced
                    if (Character.valueOf('U').equals(acc.charAt(acc.length() - 1))) {
                        return operatePhonetic(
                                substring(acc, 0, acc.length() - 1),
                                charAt(acc, acc.length() - 1),
                                ""
                        );
                    }
                }
            }

        } else {

            //Muted starting H with vowels
            if ((acc.isEmpty() || (acc.charAt(acc.length() - 1) != 'C' && acc.charAt(acc.length() - 1) != 'S')) && c == 'H') {
                if (VOWELS.contains(tail.charAt(0))) {
                    return operatePhonetic(acc, tail.charAt(0), substring(tail, 1, tail.length()));
                }
            }

            if (!acc.isEmpty() && VOWELS.contains(acc.charAt(acc.length() - 1)) && c == 'H') {
                return operatePhonetic(acc, tail.charAt(0), substring(tail, 1, tail.length()));
            }

            // SC as S
            if(c == 'S' && tail.length() >=2 && tail.charAt(0) == 'C' && (tail.charAt(1) == 'E' ||tail.charAt(1) == 'I' || tail.charAt(1) == 'Y')){
                return operatePhonetic(acc + '5', tail.charAt(1), substring(tail, 2, tail.length()));
            }
            //C as S
            if (c == 'C' && (tail.charAt(0) == 'E' || tail.charAt(0) == 'I' || tail.charAt(0) == 'Y')) {
                return operatePhonetic(acc + '5', tail.charAt(0), substring(tail, 1, tail.length()));
            } else {
                //C as K
                if (c == 'C' && tail.charAt(0) != 'E' && tail.charAt(0) != 'I' && tail.charAt(0) != 'Y' && tail.charAt(0) != 'H') {
                    if (!acc.isEmpty() && charAt(acc, acc.length() - 1) == 'K' || charAt(tail, 0) == 'K') {
                        return operatePhonetic(acc, tail.charAt(0), substring(tail, 1, tail.length()));
                    } else {
                        return operatePhonetic(acc + 'K', tail.charAt(0), substring(tail, 1, tail.length()));
                    }
                }
            }

            if (c == 'O' && "IX".equals(tail)) {
                return operatePhonetic(acc + "OI", null,"");
            }

            if (c == 'O' && tail.length() >= 2 && charAt(tail, 0) == 'E' && charAt(tail, 1) == 'U') {
                return operatePhonetic(acc + "8", charAt(tail, 2), substring(tail, 3, tail.length()));
            }

            if (c == 'E' && charAt(tail, 0) == 'U') {
                if(tail.length() == 2 && tail.charAt(1) == 'X'){
                    return operatePhonetic(acc + "8",null,"");
                }
                return operatePhonetic(acc + "8", charAt(tail, 1), substring(tail, 2, tail.length()));
            }

            //G as J : ge, gi, gy, gé, gè, gë, gê
            if (c == 'G' && (tail.charAt(0) == 'E' || tail.charAt(0) == 'I' || tail.charAt(0) == 'Y' || tail.charAt(0) == '2')) {
                return operatePhonetic(acc + 'J', tail.charAt(0), substring(tail, 1, tail.length()));
            }


            if (c == 'S') {
                //S as Z
                if (!acc.isEmpty() && VOWELS.contains(acc.charAt(acc.length() - 1)) && VOWELS.contains(tail.charAt(0))) {
                    return operatePhonetic(acc + 'Z', tail.charAt(0), substring(tail, 1, tail.length()));
                }
                else if (tail.charAt(0) == 'S') { // SS as 5
                    if (tail.length() > 1)
                        return operatePhonetic(acc + '5', tail.charAt(1), substring(tail, 2, tail.length()));
                    else
                        return acc + '5';
                }
                else if (acc.length() > 0 && MUTED_S_PRECEDING_VOWEL.contains(acc.charAt(acc.length() - 1))
                        && tail.length() > 1 && MUTED_S_FOLLOWING_CONSONANT.contains(tail.charAt(0))) { // Muted S
                    return operatePhonetic(acc, tail.charAt(0), substring(tail, 1, tail.length()));
                }
                else { //S as 5
                    return operatePhonetic(acc + '5', tail.charAt(0), substring(tail, 1, tail.length()));
                }
            }

            //W as V
            if (c == 'W') {
                return operatePhonetic(acc + 'V', tail.charAt(0), substring(tail, 1, tail.length()));
            }

            //remove double consonant ex
            if (isDoubleConsonnant(c, tail)) {
                return operatePhonetic(acc, c, substring(tail, 1, tail.length()));
            }

            //Q, QU as K
            if (c == 'Q' && tail.charAt(0) == 'U') {
                return operatePhonetic(acc + 'K', charAt(tail, 1), substring(tail, 2, tail.length()));
            }

            //PH as F
            if (c == 'P' && tail.charAt(0) == 'H') {
                return operatePhonetic(acc + 'F', charAt(tail, 1), substring(tail, 2, tail.length()));
            }

            String replacedThreeLettersINSound = replaceThreeLettersINSound(acc, c, tail, 'A', 'E');
            if (replacedThreeLettersINSound != null) {
                return replacedThreeLettersINSound;
            }

            // RIX as RI
            if(c == 'R' && "IX".equals(tail)){
                return operatePhonetic(acc + "RI",null,"");
            }

            // AIX as È (2)
            if(c == 'A' && tail.length() == 2 && "IX".equals(tail)){
                return operatePhonetic(acc + "2",null,"");
            }

            //EAU as O
            if (c == 'E' && tail.length() >= 2 && tail.charAt(0) == 'A' && tail.charAt(1) == 'U') {
                // Case EAUX as O
                if(tail.length() == 3 && tail.charAt(2) == 'X'){
                    return operatePhonetic(acc + "O", null, "");
                }
                return operatePhonetic(acc + "O", charAt(tail, 2), substring(tail, 3, tail.length()));
            }

            String replacedTwoLettersSounds = replaceTwoLettersSounds(acc, c, tail);
            if (replacedTwoLettersSounds != null) {
                return replacedTwoLettersSounds;
            }

            String handleJeanCase = handleJEANSpecialCase(acc, c, tail);
            if (handleJeanCase != null) {
                return handleJeanCase;
            }

            if (c == 'T' && tail.length() >= 3 && VOWELS.contains(charAt(acc, acc.length() - 1)) && "ION".equals(substring(tail, 0, 3))) {
                return operatePhonetic(acc + "S", charAt(tail, 0), substring(tail, 1, tail.length()));
            }
        }

        if (c == 'E') {
            String nextAcc = operatePhonetic("2", charAt(tail, 0), substring(tail, 1, tail.length()));
            if ("2".equals(nextAcc)) {
                return acc;
            } else {
                nextAcc = StringUtils.substringAfter(nextAcc, "2");
            }
            return acc + "2" + nextAcc;
        }

        //Y as I
        if (c == 'Y') {
            return operatePhonetic(acc + 'I', charAt(tail, 0), substring(tail, 1, tail.length()));
        }

        return operatePhonetic(acc + c, charAt(tail, 0), substring(tail, 1, tail.length()));
    }

    private boolean isDoubleConsonnant(Character c, String tail) {
        Character character = charAt(tail, 0);
        return c != null && character != null && DOUBLE_CONSONANT.contains(c.charValue()) && character.charValue() == c.charValue();
    }

    // end X managed here
    private String replaceTwoLettersSounds(String acc, char c, String tail) {

        //Trailing ER, ET and EZ as 2
        if (c == 'E' && tail.length() == 1 && (tail.charAt(0) == 'R' || tail.charAt(0) == 'T' || tail.charAt(0) == 'Z')) {
            String encodedTail = operatePhonetic("", charAt(tail, 1), substring(tail, 2, tail.length()));
            if (encodedTail.isEmpty()) {
                return acc + "2";
            } else {
                return acc + "2" + tail.charAt(0) + encodedTail;
            }


        }

        // AU as O
        if (c == 'A' && tail.charAt(0) == 'U') {
            if(tail.length() == 2 && tail.charAt(1) == 'X'){
                return operatePhonetic(acc + 'O', null,"");
            }
            return operatePhonetic(acc + 'O', charAt(tail, 1), substring(tail, 2, tail.length()));
        }


        String replacedAISound = replaceAISounds(acc, c, tail, 'A', 'E');
        if (replacedAISound != null) {
            return replacedAISound;
        }

        String replacedONSound = replaceONOrINOrANSound(acc, c, tail, "4", 'O');
        if (replacedONSound != null) {
            return replacedONSound;
        }


        String replacedINSound = replaceONOrINOrANSound(acc, c, tail, "1", 'Y', 'I', 'U');
        if (replacedINSound != null) {
            return replacedINSound;
        }

        String replacedANSound = replaceONOrINOrANSound(acc, c, tail, "3", 'A', 'E');
        if (replacedANSound != null) {
            return replacedANSound;
        }

        return null;
    }

    /**
     * JEAN => J3
     * JEAN* => JAN
     * @return
     */
    private String handleJEANSpecialCase(String acc, char c, String tail) {
        if (c == 'J' && tail.length() > 2 && tail.charAt(0) == 'E' && tail.charAt(1) == 'A' && tail.charAt(2) == 'N') { // at least 3 trailing chars : EAN
            if (tail.length() == 3) {
                return acc + "J3";
            } else {
                return operatePhonetic(acc + "JA", tail.charAt(2), substring(tail, 3, tail.length()));
            }
        }
        return null;
    }

    private String replaceAISounds(String acc, char c, String tail, Character... firstLetters) {
        if (Arrays.asList(firstLetters).contains(c) && (tail.charAt(0) == 'I' || tail.charAt(0) == 'Y')) {
            acc += "2";
            if (tail.charAt(0) == 'Y' && tail.length() != 1 && (c != 'E' || c == 'E' && VOWELS.contains(tail.charAt(1)))) {
                acc += "I";
            }
            return operatePhonetic(acc, charAt(tail, 1), substring(tail, 2, tail.length()));
        }
        return null;
    }

    private String replaceThreeLettersINSound(String acc, char c, String tail, Character... firstLetters) {
        if (Arrays.asList(firstLetters).contains(c) && tail.length() >= 2 &&
                tail.charAt(0) == 'I' && (tail.charAt(1) == 'N' || tail.charAt(1) == 'M')) {
            if (tail.length() >= 3 && (tail.charAt(2) == 'M' || tail.charAt(2) == 'N' || tail.charAt(2) == 'H' || VOWELS.contains(tail.charAt(2)))) {
                return replaceTwoLettersSounds(acc, c, tail);
            }
            return operatePhonetic(acc + "1", charAt(tail, 2), substring(tail, 3, tail.length()));
        }
        return null;
    }

    private String replaceONOrINOrANSound(String acc, char c, String tail, String replaceValue, Character... firstLetters) {
        if (Arrays.asList(firstLetters).contains(c)) {
            if (tail.charAt(0) == 'N' || tail.charAt(0) == 'M') {
                if (tail.length() > 1 && (tail.charAt(1) == 'N' || tail.charAt(1) == 'M' || tail.charAt(1) == 'H' || VOWELS.contains(tail.charAt(1)))) {
                    return operatePhonetic(acc + c, tail.charAt(0), substring(tail, 1, tail.length()));
                }
                return operatePhonetic(acc + replaceValue, charAt(tail, 1), substring(tail, 2, tail.length()));
            }
        }
        return null;
    }

    protected String substring(String tail, int startIndex, int endIndex) {
        if (tail == null) {
            return "";
        }
        if (tail.length() <= startIndex) {
            return "";
        } else {
            if (endIndex > tail.length() - 1) {
                return tail.substring(startIndex, tail.length());
            } else {
                return tail.substring(startIndex, endIndex);
            }
        }
    }

    protected Character charAt(String tail, int position) {
        if (tail == null || tail.isEmpty() || position < 0 || position >= tail.length()) {
            return null;
        }
        return tail.charAt(position);
    }

    @Override
    public Object encode(Object str) throws EncoderException {
        if (str instanceof String) {
            return encode((String) str);
        }
        throw new EncoderException("Object must be a String to be parsed");
    }

    static String clean(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int len = str.length();
        String STR = str.toUpperCase(Locale.FRENCH);

        char[] chars = new char[len];
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (Character.isLetter(STR.charAt(i))) {
                if (SOUND_2_ACCENTUATED_CHARS.contains(STR.charAt(i))) {
                    chars[count++] = '2';
                } else {
                    chars[count++] = STR.charAt(i);
                }
            }
        }
        char[] res = new char[count];
        int finalSize = ASCIIFoldingFilter.foldToASCII(chars, 0, res, 0, count);
        return new String(chars, 0, finalSize);
    }
}
