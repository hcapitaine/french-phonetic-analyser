package org.elasticsearch.index.analysis;

import org.apache.commons.codec.EncoderException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FrenchPhoneticTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testEncodeWithMutedContainsH() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("thermometre");
        Assertions.assertThat(encode).isEqualTo("T2RMOM2TR");
    }

    @Test
    public void testEncodeWithDoubleF() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("bouzeliffa");
        Assertions.assertThat(encode).isEqualTo("BOUZ2LIFA");
    }

    @Test
    public void testEncodeNumber() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("5");
        Assertions.assertThat(encode).isEqualTo("");
    }

    @Test
    public void testEncodeWithFinalX() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("CEDEX");
        Assertions.assertThat(encode).isEqualTo("52D2X");
    }

    @Test
    public void testEncodeWithStartingOEU() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("oeuvre");
        Assertions.assertThat(encode).isEqualTo("8VR");
    }

    @Test
    public void testEncodeWithContainsOEU() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("coeur");
        Assertions.assertThat(encode).isEqualTo("K8R");
    }

    @Test
    public void testEncodeWithEndingOEU() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("voeu");
        Assertions.assertThat(encode).isEqualTo("V8");
    }

    @Test
    public void testEncodeWithContainsEU() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("veuve");
        Assertions.assertThat(encode).isEqualTo("V8V");
    }

    @Test
    public void testEncodeWithEndingsEU() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("matthieu");
        Assertions.assertThat(encode).isEqualTo("MATI8");
    }

    @Test
    public void testEncodeWithStartingEU() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("europe");
        Assertions.assertThat(encode).isEqualTo("8ROP");
    }

    @Test
    public void testEncodeWithMutedContainsHNotPreceded() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("hache");
        Assertions.assertThat(encode).isEqualTo("ACH");
    }

    @Test
    public void testEncodeWithMutedContainsHPrecedeByVowels() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("bahut");
        Assertions.assertThat(encode).isEqualTo("BAU");
    }



    @Test
    public void testEncodeWithMutedStartingH() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("haricots");
        Assertions.assertThat(encode).isEqualTo("ARIKO");
    }

    @Test
    public void testEncodeWithMutedEndingX() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("hiboux");
        Assertions.assertThat(encode).isEqualTo("IBOU");
    }

    @Test
    public void testEncodeWithMutedEndingTS() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("couts");
        Assertions.assertThat(encode).isEqualTo("KOU");
    }

    @Test
    public void testEncodeWithTrailingT() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("rat");
        Assertions.assertThat(encode).isEqualTo("RA");
    }

    @Test
    public void testEncodeWithTrailingY() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("dauby");
        Assertions.assertThat(encode).isEqualTo("DOBI");
    }

    @Test
    public void testEncodeWithTwoTrailingMutedConsonnant() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("ouest");
        Assertions.assertThat(encode).isEqualTo("OU25");
    }

    @Test
    public void testEncodeWithTrailingD() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("suspend");
        Assertions.assertThat(encode).isEqualTo("5U5P3");
    }

    @Test
    public void testEncodeWithNotMutedH() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("chat");
        Assertions.assertThat(encode).isEqualTo("CHA");
    }

    @Test
    public void testEncodeWithDoubleCH() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("cherche");
        Assertions.assertThat(encode).isEqualTo("CH2RCH");
    }

    @Test
    public void testEncodeWithCAsS() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("acceptable");
        Assertions.assertThat(encode).isEqualTo("AK52PTABL");
    }

    @Test
    public void testEncodeWithCAsK() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("consommable");
        Assertions.assertThat(encode).isEqualTo("K45OMABL");
    }

    @Test
    public void testEncodeWithDoubleConsonant() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("boulette");
        Assertions.assertThat(encode).isEqualTo("BOUL2T");
    }

    @Test
    public void testEncodeWithQUAsK() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("graphique");
        Assertions.assertThat(encode).isEqualTo("GRAFIK");
    }

    @Test
    public void testEncodeWithTrailingR() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("merard");
        Assertions.assertThat(encode).isEqualTo("M2RAR");
    }

    @Test
    public void testEncodeWithQUAsKNotEnds() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("graphiques");
        Assertions.assertThat(encode).isEqualTo("GRAFIK");
    }

    @Test
    public void testEncodeWithQUAsKEnds() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("graphiqu");
        Assertions.assertThat(encode).isEqualTo("GRAFIK");
    }

    @Test
    public void testEncodeWithPHAlmostEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("graphe");
        Assertions.assertThat(encode).isEqualTo("GRAF");
    }

    @Test
    public void testEncodeWithPHEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("graph");
        Assertions.assertThat(encode).isEqualTo("GRAF");
    }


    @Test
    public void testEncodeWithAIEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("balai");
        Assertions.assertThat(encode).isEqualTo("BAL2");
    }

    @Test
    public void testEncodeWithCEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("FRANC");
        Assertions.assertThat(encode).isEqualTo("FR3");
    }

    @Test
    public void testEncodeWithCKEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("FRANCK");
        Assertions.assertThat(encode).isEqualTo("FR3K");
    }

    @Test
    public void testEncodeWithKEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("FRANK");
        Assertions.assertThat(encode).isEqualTo("FR3K");
    }

    @Test
    public void testEncodeWithSEasZEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("BRAISE");
        Assertions.assertThat(encode).isEqualTo("BR2Z");
    }

    @Test
    public void testEncodeWithAYEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("tramway");
        Assertions.assertThat(encode).isEqualTo("TR3V2");
    }

    @Test
    public void testEncodeWithANEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("faisan");
        Assertions.assertThat(encode).isEqualTo("F2Z3");
    }

    @Test
    public void testEncodeWithGEEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("tige");
        Assertions.assertThat(encode).isEqualTo("TIJ");
    }

    @Test
    public void testEncodeWithAIMEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("daim");
        Assertions.assertThat(encode).isEqualTo("D1");
    }

    @Test
    public void testEncodeWithAINEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("bain");
        Assertions.assertThat(encode).isEqualTo("B1");
    }

    @Test
    public void testEncodeWithEINEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("plein");
        Assertions.assertThat(encode).isEqualTo("PL1");
    }

    @Test
    public void testEncodeWithEINContains() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("ceinture");
        Assertions.assertThat(encode).isEqualTo("51TUR");
    }

    @Test
    public void testEncodeWithEINContainsFollowByVowels() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("seime");
        Assertions.assertThat(encode).isEqualTo("52M");
    }

    @Test
    public void testEncodeWithAINContains() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("maintenant");
        Assertions.assertThat(encode).isEqualTo("M1TEN3");
    }

    @Test
    public void testEncodeWithAINAlmostEnding() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("bains");
        Assertions.assertThat(encode).isEqualTo("B1");
    }

    @Test
    public void testEncodeWithEY() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("seyante");
        Assertions.assertThat(encode).isEqualTo("52I3T");
    }

    @Test
    public void testEncodeWithTrailingEY() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("volley");
        Assertions.assertThat(encode).isEqualTo("VOL2");
    }

    @Test
    public void testEncodeWithEndingEY() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("chardonay");
        Assertions.assertThat(encode).isEqualTo("CHARDON2");
    }

    @Test
    public void testEncodeWithTrailingET() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String result = frenchPhonetic.encode("charret");
        Assertions.assertThat(result).isEqualTo("CHAR2");
    }

    @Test
    public void testEncodeWithTrailingAU() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String result = frenchPhonetic.encode("tuyau");
        Assertions.assertThat(result).isEqualTo("TUIO");
    }

    @Test
    public void testEncodeWithTrailingYAndMutedConsonant() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String result = frenchPhonetic.encode("pays");
        Assertions.assertThat(result).isEqualTo("P2I");
    }

    @Test
    public void testEncodeWithAYContains() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String result = frenchPhonetic.encode("payer");
        Assertions.assertThat(result).isEqualTo("P2I2");
    }

    @Test
    public void testEncodeWithTrailingETLongWord() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String result = frenchPhonetic.encode("trajet");
        Assertions.assertThat(result).isEqualTo("TRAJ2");
    }

    @Test
    public void testEncodeWithTrailingER() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String result = frenchPhonetic.encode("arriver");
        Assertions.assertThat(result).isEqualTo("ARIV2");
    }

    @Test
    public void testEncodeWithTrailingE() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String result = frenchPhonetic.encode("vraie");
        Assertions.assertThat(result).isEqualTo("VR2");
    }

    @Test
    public void testEncodeWithON() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("pont");
        Assertions.assertThat(encode).isEqualTo("P4");
    }

    @Test
    public void testEncodeWithOM() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("pompe");
        Assertions.assertThat(encode).isEqualTo("P4P");
    }

    @Test
    public void testEncodeWithYM() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("cymbale");
        Assertions.assertThat(encode).isEqualTo("51BAL");
    }

    @Test
    public void testEncodeWithYN() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("pharynx");
        Assertions.assertThat(encode).isEqualTo("FAR1X");
    }

    @Test
    public void testEncodeWithAN() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("pendant");
        Assertions.assertThat(encode).isEqualTo("P3D3");
    }

    @Test
    public void testEncodeWithEndingEAU() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("eau");
        Assertions.assertThat(encode).isEqualTo("O");
    }

    @Test
    public void testEncodeWithEAUContains() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("tableautage");
        Assertions.assertThat(encode).isEqualTo("TABLOTAJ");
    }

    @Test
    public void testEncodeWithAUContains() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("vautrer");
        Assertions.assertThat(encode).isEqualTo("VOTR2");
    }

    @Test
    public void testEncodeWithANFollowByVowels() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("anhile");
        Assertions.assertThat(encode).isEqualTo("ANIL");
    }

    @Test
    public void testEncodeWithAMFollowByVowels() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("amerique");
        Assertions.assertThat(encode).isEqualTo("AM2RIK");
    }

    @Test
    public void testEncodeWithVowelTION() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("ebulition");
        Assertions.assertThat(encode).isEqualTo("2BULISI4");
    }

    @Test
    public void testEncodeWithConsonantTION() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("bastion");
        Assertions.assertThat(encode).isEqualTo("BA5TI4");
    }


    @Test
    public void testEncodeWithEN() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("pendant");
        Assertions.assertThat(encode).isEqualTo("P3D3");
    }

    @Test
    public void testEncodeWithNullString() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("");
        Assertions.assertThat(encode).isEqualTo("");
    }

    @Test
    public void testEndingERT() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("AUBERT");
        Assertions.assertThat(encode).isEqualTo("OB2R");
    }

    @Test
    public void testEndingER() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("OBER");
        Assertions.assertThat(encode).isEqualTo("OB2");
    }

    @Test
    public void testBeginningER() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("HERTZIEN");
        Assertions.assertThat(encode).isEqualTo("2RTZI3");
    }

    @Test
    public void testEndindAUD() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("BADAUDS");
        Assertions.assertThat(encode).isEqualTo("BADO");
    }

    @Test
    public void testEndingCCO() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("BACCO");
        Assertions.assertThat(encode).isEqualTo("BAKO");
    }

    @Test
    public void testEndingCO() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("BACO");
        Assertions.assertThat(encode).isEqualTo("BAKO");
    }
    @Test
    public void testEndingCOT() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("BACOT");
        Assertions.assertThat(encode).isEqualTo("BAKO");
    }

    @Test
    public void testMauriceMatch() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("MAURISSE");
        Assertions.assertThat(encode).isEqualTo("MORI5");
    }

    @Test
    public void testMutedEndingZ() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("ALLEZ");
        Assertions.assertThat(encode).isEqualTo("AL2");
    }

    @Test
    public void testEndingZ() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("ALAISE");
        Assertions.assertThat(encode).isEqualTo("AL2Z");
    }

    @Test
    public void testEnding2() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("André");
        Assertions.assertThat(encode).isEqualTo("3DR2");
    }

    @Test
    public void testEndingE() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("Andrey");
        Assertions.assertThat(encode).isEqualTo("3DR2");
    }

    @Test
    public void testEas2() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("Audré");
        Assertions.assertThat(encode).isEqualTo("ODR2");
    }

    @Test
    public void testEYas2() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("Audrey");
        Assertions.assertThat(encode).isEqualTo("ODR2");
    }

    @Test
    public void testEANEqualsEN() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("JEAN");
        Assertions.assertThat(encode).isEqualTo("J3");
    }

    @Test
    public void testEANEquals2AN() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("Géant");
        Assertions.assertThat(encode).isEqualTo("J23");
    }

    @Test
    public void testEANEqualsANE() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("JEANNE");
        Assertions.assertThat(encode).isEqualTo("JAN");
    }

    @Test
    public void testLongerEANEqualsANE() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("JEANNETON");
        Assertions.assertThat(encode).isEqualTo("JAN2T4");
    }

    @Test
    public void testJeannette() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("JEANNETTE");
        Assertions.assertThat(encode).isEqualTo("JAN2T");
    }

    @Test
    public void testOMutedS() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("GROSJEAN");
        Assertions.assertThat(encode).isEqualTo("GROJ3");
    }

    @Test
    public void testAMutedS() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("BASMAISON");
        Assertions.assertThat(encode).isEqualTo("BAM2Z4");
    }

    @Test
    public void testFinalSS() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("TOREGROSS");
        Assertions.assertThat(encode).isEqualTo("TOR2GRO5");
    }

    @Test
    public void testWithEndingRSound() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("PIERE");
        Assertions.assertThat(encode).isEqualTo("PI2R");
    }

    @Test
    public void testWithSpaces() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("DE MARI");
        Assertions.assertThat(encode).isEqualTo("DEMARI");
    }

    @Test
    public void testWithEnding1X() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("LARYNX");
        Assertions.assertThat(encode).isEqualTo("LAR1X");
    }

    @Test
    public void testWithEndingEUX() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("HEUREUX");
        Assertions.assertThat(encode).isEqualTo("8R8");
    }

    @Test
    public void testWithEndingRIX() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("PERDRIX");
        Assertions.assertThat(encode).isEqualTo("P2RDRI");
    }

    @Test
    public void testWithEndingOIX() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("CROIX");
        Assertions.assertThat(encode).isEqualTo("KROI");
    }

    @Test
    public void testWithSCasS() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String encode = frenchPhonetic.encode("ASCENSION");
        Assertions.assertThat(encode).isEqualTo("A535I4");
    }

    @Test
    public void testEncodeCHFollowedDoubleVowels() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String result = frenchPhonetic.encode("michael");
        Assertions.assertThat(result).isEqualTo("MIKA2L");
    }

    @Test
    public void testEncodeEndingWithOX() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String result = frenchPhonetic.encode("intox");
        Assertions.assertThat(result).isEqualTo("1TOX");
    }

    @Test
    public void testEncodeEndingWithOSoundAndX() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String result = frenchPhonetic.encode("etaux");
        Assertions.assertThat(result).isEqualTo("2TO");
    }

    @Test
    public void testEncodeEndingWith2SoundAndX() throws EncoderException {
        FrenchPhonetic frenchPhonetic = new FrenchPhonetic();
        String result = frenchPhonetic.encode("PAIX");
        Assertions.assertThat(result).isEqualTo("P2");
    }
}