package com.galerieslafayette.index.analysis;

import com.galerieslafayette.analyzer.Encoder;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.galerieslafayette.analyzer.Encoder.SOUND_2_ACCENTUATED_CHARS;

public class FrenchPhoneticAnalyzer extends TokenFilter {

    private TokenStream input;
    private List<String> stateTokens = new ArrayList<>();
    private int currentIndex = 0;
    private CharTermAttribute termAtt;
    private PositionIncrementAttribute posIncrAtt;
    private State state;


    public FrenchPhoneticAnalyzer(TokenStream input) {
        super(input);
        this.input = input;
        termAtt = addAttribute(CharTermAttribute.class);
        posIncrAtt = addAttribute(PositionIncrementAttribute.class);
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        stateTokens.clear();
        currentIndex = 0;
        state = null;
    }

    @Override
    public final boolean incrementToken() throws IOException {
        if (state == null) {
            currentIndex = 0;
            stateTokens.clear();
            if(input.incrementToken()) {
                List<String> encodedTokens = encode(termAtt.toString());
                termAtt.setEmpty().append(encodedTokens.get(0));
                posIncrAtt.setPositionIncrement(1);
                if (encodedTokens.size() > 1) {
                    state = captureState();
                    stateTokens.addAll(encodedTokens);
                }
                currentIndex++;
                return true;
            }

        } else {
            if (stateTokens.size() > currentIndex) {
                restoreState(state);
                termAtt.setEmpty().append(stateTokens.get(currentIndex));
                posIncrAtt.setPositionIncrement(0);
                currentIndex++;
                if(currentIndex>= stateTokens.size()){
                    state = null;
                }
                return true;
            } 
        }
        
        return false;
    }

    @Override
    public void end() throws IOException {
        super.end();
        state = null;
        currentIndex = 0;
        stateTokens.clear();
    }
    
    public List<String> encode(String input){
        if (input == null || input.length() == 0) {
            return Arrays.asList(input);
        }
        int len = input.length();
        String upperStr = input.toUpperCase(Locale.FRENCH);

        char[] chars = new char[len];
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (Character.isLetter(upperStr.charAt(i))) {
                if (SOUND_2_ACCENTUATED_CHARS.contains(upperStr.charAt(i))) {
                    chars[count++] = '2';
                } else {
                    chars[count++] = upperStr.charAt(i);
                }
            }
        }
        char[] res = new char[count];
        int finalSize = ASCIIFoldingFilter.foldToASCII(chars, 0, res, 0, count);
        String cleanedString = new String(chars, 0, finalSize);
        return new ArrayList<>(Encoder.operatePhonetic("", Encoder.charAt(cleanedString, 0), Encoder.substring(cleanedString, 1, cleanedString.length())));
    }
    
}
