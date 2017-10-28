package org.elasticsearch.index.analysis;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;

import java.util.Locale;

import static com.galerieslafayette.analyzer.Encoder.*;

public class FrenchPhonetic implements StringEncoder {

    @Override
    public String encode(String s) throws EncoderException {
        String cleanedString = clean(s);

        if (cleanedString == null || cleanedString.length() == 0) {
            return cleanedString;
        }


        String result = operatePhonetic("", charAt(cleanedString, 0), substring(cleanedString, 1, cleanedString.length()));

        return result;
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
