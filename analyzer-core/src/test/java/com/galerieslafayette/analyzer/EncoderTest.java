package com.galerieslafayette.analyzer;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class EncoderTest {

    @Test
    public void testSubstringStartIndexGreaterThanLengthExpectedEmptyString() throws Exception {
        Encoder encoder = new Encoder();
        String result = encoder.substring("E", 2, 4);
        Assertions.assertThat(result).isEqualTo("");
    }
    @Test
    public void testSubstringStartIndexEqualsToLengthExpectedEmptyString() throws Exception {
        Encoder encoder = new Encoder();
        String result = encoder.substring("E", 1, 4);
        Assertions.assertThat(result).isEqualTo("");
    }

    @Test
    public void testSubstringStartIndexGreaterThanLengthExpectedNull() throws Exception {
        Encoder encoder = new Encoder();
        String result = encoder.substring("E", 2, 4);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void testSubstringStartIndexEqualsToLengthExpectedNull() throws Exception {
        Encoder encoder = new Encoder();
        String result = encoder.substring("E", 1, 4);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void testSubstringEndIndexEqualsToTailLength() throws Exception {
        Encoder encoder = new Encoder();
        String result = encoder.substring("E", 1, 1);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void testSubstringEndIndexGreaterToTailLength() throws Exception {
        Encoder encoder = new Encoder();
        String result = encoder.substring("EE", 0, 2);
        Assertions.assertThat(result).isEqualTo("EE");
    }

    @Test
    public void testSubstringEndIndexEqualsToStartIndex() throws Exception {
        Encoder encoder = new Encoder();
        String result = encoder.substring("ERT", 2, 2);
        Assertions.assertThat(result).isEqualTo("");
    }

    @Test
    public void testSubstringEndIndexLesserThanLength() throws Exception {
        Encoder encoder = new Encoder();
        String result = encoder.substring("ERT", 1, 2);
        Assertions.assertThat(result).isEqualTo("R");
    }

    @Test
    public void testSubstringEndIndexEqualsToLength() throws Exception {
        Encoder encoder = new Encoder();
        String result = encoder.substring("ERT", 1, 3);
        Assertions.assertThat(result).isEqualTo("RT");
    }
}
