package org.elasticsearch.index.analysis;

import com.galerieslafayette.index.analysis.FrenchPhoneticAnalyzer;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FrenchPhoneticTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testEncodeWithMutedContainsH() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("thermometre");
        Assertions.assertThat(encode).containsOnly("T2RMOM2TR");
    }

    @Test
    public void testEncodeWithDoubleF() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("bouzeliffa");
        Assertions.assertThat(encode).containsOnly("BOUZ2LIFA");
    }

    @Test
    public void testEncodeNumber() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("5");
        Assertions.assertThat(encode).containsOnly("");
    }

    @Test
    public void testEncodeWithFinalX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("CEDEX");
        Assertions.assertThat(encode).containsOnly("52D2X");
    }

    @Test
    public void testEncodeWithStartingOEU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("oeuvre");
        Assertions.assertThat(encode).containsOnly("8VR");
    }

    @Test
    public void testEncodeWithContainsOEU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("coeur");
        Assertions.assertThat(encode).containsOnly("K8R");
    }

    @Test
    public void testEncodeWithEndingOEU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("voeu");
        Assertions.assertThat(encode).containsOnly("V8");
    }

    @Test
    public void testEncodeWithContainsEU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("veuve");
        Assertions.assertThat(encode).containsOnly("V8V");
    }

    @Test
    public void testEncodeWithEndingsEU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("matthieu");
        Assertions.assertThat(encode).containsOnly("MATI8");
    }

    @Test
    public void testEncodeWithStartingEU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("europe");
        Assertions.assertThat(encode).containsOnly("8ROP");
    }

    @Test
    public void testEncodeWithMutedContainsHNotPreceded() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("hache");
        Assertions.assertThat(encode).containsOnly("ACH");
    }

    @Test
    public void testEncodeWithMutedContainsHPrecedeByVowels() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("bahut");
        Assertions.assertThat(encode).containsOnly("BAU");
    }



    @Test
    public void testEncodeWithMutedStartingH() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("haricots");
        Assertions.assertThat(encode).containsOnly("ARIKO", "ARIKOTS");
    }

    @Test
    public void testEncodeWithMutedEndingX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("hiboux");
        Assertions.assertThat(encode).containsOnly("IBOU", "IBOUX");
    }

    @Test
    public void testEncodeWithMutedEndingTS() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("couts");
        Assertions.assertThat(encode).containsOnly("KOU", "KOUTS");
    }

    @Test
    public void testEncodeWithTrailingT() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("rat");
        Assertions.assertThat(encode).containsOnly("RA");
    }

    @Test
    public void testEncodeWithTrailingY() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("dauby");
        Assertions.assertThat(encode).containsOnly("DOBI");
    }

    @Test
    public void testEncodeWithTwoTrailingMutedConsonnant() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("ouest");
        Assertions.assertThat(encode).containsOnly("OU25");
    }

    @Test
    public void testEncodeWithTrailingD() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("suspend");
        Assertions.assertThat(encode).containsOnly("5U5P3", "5U5P3D");
    }

    @Test
    public void testEncodeWithNotMutedH() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("chat");
        Assertions.assertThat(encode).containsOnly("CHA");
    }

    @Test
    public void testEncodeWithDoubleCH() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("cherche");
        Assertions.assertThat(encode).containsOnly("CH2RCH");
    }

    @Test
    public void testEncodeWithCAsS() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("acceptable");
        Assertions.assertThat(encode).containsOnly("AK52PTABL");
    }

    @Test
    public void testEncodeWithCAsK() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("consommable");
        Assertions.assertThat(encode).containsOnly("K45OMABL");
    }

    @Test
    public void testEncodeWithDoubleConsonant() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("boulette");
        Assertions.assertThat(encode).containsOnly("BOUL2T");
    }

    @Test
    public void testEncodeWithQUAsK() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("graphique");
        Assertions.assertThat(encode).containsOnly("GRAFIK");
    }

    @Test
    public void testEncodeWithTrailingR() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("merard");
        Assertions.assertThat(encode).containsOnly("M2RAR", "M2RARD");
    }

    @Test
    public void testEncodeWithQUAsKNotEnds() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("graphiques");
        Assertions.assertThat(encode).containsOnly("GRAFIK", "GRAFIK2S");
    }

    @Test
    public void testEncodeWithQUAsKEnds() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("graphiqu");
        Assertions.assertThat(encode).containsOnly("GRAFIK");
    }

    @Test
    public void testEncodeWithPHAlmostEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("graphe");
        Assertions.assertThat(encode).containsOnly("GRAF");
    }

    @Test
    public void testEncodeWithPHEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("graph");
        Assertions.assertThat(encode).containsOnly("GRAF");
    }


    @Test
    public void testEncodeWithAIEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("balai");
        Assertions.assertThat(encode).containsOnly("BAL2");
    }

    @Test
    public void testEncodeWithCEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("FRANC");
        Assertions.assertThat(encode).containsOnly("FR3", "FR3K");
    }

    @Test
    public void testEncodeWithCKEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("FRANCK");
        Assertions.assertThat(encode).containsOnly("FR3K");
    }

    @Test
    public void testEncodeWithKEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("FRANK");
        Assertions.assertThat(encode).containsOnly("FR3K");
    }

    @Test
    public void testEncodeWithSEasZEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("BRAISE");
        Assertions.assertThat(encode).containsOnly("BR2Z");
    }

    @Test
    public void testEncodeWithAYEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("tramway");
        Assertions.assertThat(encode).containsOnly("TR3V2");
    }

    @Test
    public void testEncodeWithANEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("faisan");
        Assertions.assertThat(encode).containsOnly("F2Z3");
    }

    @Test
    public void testEncodeWithGEEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("tige");
        Assertions.assertThat(encode).containsOnly("TIJ");
    }

    @Test
    public void testEncodeWithAIMEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("daim");
        Assertions.assertThat(encode).containsOnly("D1");
    }

    @Test
    public void testEncodeWithAINEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("bain");
        Assertions.assertThat(encode).containsOnly("B1");
    }

    @Test
    public void testEncodeWithEINEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("plein");
        Assertions.assertThat(encode).containsOnly("PL1");
    }

    @Test
    public void testEncodeWithEINContains() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("ceinture");
        Assertions.assertThat(encode).containsOnly("51TUR");
    }

    @Test
    public void testEncodeWithEINContainsFollowByVowels() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("seime");
        Assertions.assertThat(encode).containsOnly("52M");
    }

    @Test
    public void testEncodeWithAINContains() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("maintenant");
        Assertions.assertThat(encode).containsOnly("M1TEN3");
    }

    @Test
    public void testEncodeWithAINAlmostEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("bains");
        Assertions.assertThat(encode).containsOnly("B1", "B1S");
    }

    @Test
    public void testEncodeWithEY() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("seyante");
        Assertions.assertThat(encode).containsOnly("52I3T");
    }

    @Test
    public void testEncodeWithTrailingEY() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("volley");
        Assertions.assertThat(encode).containsOnly("VOL2");
    }

    @Test
    public void testEncodeWithEndingEY() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("chardonay");
        Assertions.assertThat(encode).containsOnly("CHARDON2");
    }

    @Test
    public void testEncodeWithTrailingET() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> result = frenchPhonetic.encode("charret");
        Assertions.assertThat(result.get(0)).isEqualTo("CHAR2");
    }

    @Test
    public void testEncodeWithTrailingAU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> result = frenchPhonetic.encode("tuyau");
        Assertions.assertThat(result.get(0)).isEqualTo("TUIO");
    }

    @Test
    public void testEncodeWithTrailingYAndMutedConsonant() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> result = frenchPhonetic.encode("pays");
        Assertions.assertThat(result.get(0)).isEqualTo("P2I");
    }

    @Test
    public void testEncodeWithAYContains() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> result = frenchPhonetic.encode("payer");
        Assertions.assertThat(result.get(0)).isEqualTo("P2I2");
    }

    @Test
    public void testEncodeWithTrailingETLongWord() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> result = frenchPhonetic.encode("trajet");
        Assertions.assertThat(result.get(0)).isEqualTo("TRAJ2");
    }

    @Test
    public void testEncodeWithTrailingER() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> result = frenchPhonetic.encode("arriver");
        Assertions.assertThat(result.get(0)).isEqualTo("ARIV2");
    }

    @Test
    public void testEncodeWithTrailingE() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> result = frenchPhonetic.encode("vraie");
        Assertions.assertThat(result.get(0)).isEqualTo("VR2");
    }

    @Test
    public void testEncodeWithON() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("pont");
        Assertions.assertThat(encode).containsOnly("P4");
    }

    @Test
    public void testEncodeWithOM() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("pompe");
        Assertions.assertThat(encode).containsOnly("P4P");
    }

    @Test
    public void testEncodeWithYM() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("cymbale");
        Assertions.assertThat(encode).containsOnly("51BAL");
    }

    @Test
    public void testEncodeWithYN() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("pharynx");
        Assertions.assertThat(encode).containsOnly("FAR1X");
    }

    @Test
    public void testEncodeWithAN() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("pendant");
        Assertions.assertThat(encode).containsOnly("P3D3");
    }

    @Test
    public void testEncodeWithEndingEAU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("eau");
        Assertions.assertThat(encode).containsOnly("O");
    }

    @Test
    public void testEncodeWithEAUContains() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("tableautage");
        Assertions.assertThat(encode).containsOnly("TABLOTAJ");
    }

    @Test
    public void testEncodeWithAUContains() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("vautrer");
        Assertions.assertThat(encode).containsOnly("VOTR2");
    }

    @Test
    public void testEncodeWithANFollowByVowels() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("anhile");
        Assertions.assertThat(encode).containsOnly("ANIL");
    }

    @Test
    public void testEncodeWithAMFollowByVowels() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("amerique");
        Assertions.assertThat(encode).containsOnly("AM2RIK");
    }

    @Test
    public void testEncodeWithVowelTION() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("ebulition");
        Assertions.assertThat(encode).containsOnly("2BULISI4");
    }

    @Test
    public void testEncodeWithConsonantTION() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("bastion");
        Assertions.assertThat(encode).containsOnly("BA5TI4");
    }


    @Test
    public void testEncodeWithEN() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("pendant");
        Assertions.assertThat(encode).containsOnly("P3D3");
    }

    @Test
    public void testEncodeWithNullString() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("");
        Assertions.assertThat(encode).containsOnly("");
    }

    @Test
    public void testEndingERT() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("AUBERT");
        Assertions.assertThat(encode).containsOnly("OB2R");
    }

    @Test
    public void testEndingER() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("OBER");
        Assertions.assertThat(encode).containsOnly("OB2");
    }

    @Test
    public void testBeginningER() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("HERTZIEN");
        Assertions.assertThat(encode).containsOnly("2RTZI3");
    }

    @Test
    public void testEndindAUD() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("BADAUDS");
        Assertions.assertThat(encode).containsOnly("BADO", "BADOD", "BADODS");
    }

    @Test
    public void testEndingCCO() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("BACCO");
        Assertions.assertThat(encode).containsOnly("BAKO");
    }

    @Test
    public void testEndingCO() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("BACO");
        Assertions.assertThat(encode).containsOnly("BAKO");
    }
    @Test
    public void testEndingCOT() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("BACOT");
        Assertions.assertThat(encode).containsOnly("BAKO");
    }

    @Test
    public void testMauriceMatch() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("MAURISSE");
        Assertions.assertThat(encode).containsOnly("MORI5");
    }

    @Test
    public void testMutedEndingZ() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("ALLEZ");
        Assertions.assertThat(encode).containsOnly("AL2");
    }

    @Test
    public void testEndingZ() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("ALAISE");
        Assertions.assertThat(encode).containsOnly("AL2Z");
    }

    @Test
    public void testEnding2() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("André");
        Assertions.assertThat(encode).containsOnly("3DR2");
    }

    @Test
    public void testEndingE() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("Andrey");
        Assertions.assertThat(encode).containsOnly("3DR2");
    }

    @Test
    public void testEas2() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("Audré");
        Assertions.assertThat(encode).containsOnly("ODR2");
    }

    @Test
    public void testEYas2() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("Audrey");
        Assertions.assertThat(encode).containsOnly("ODR2");
    }

    @Test
    public void testEANEqualsEN() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("JEAN");
        Assertions.assertThat(encode).containsOnly("J3");
    }

    @Test
    public void testEANEquals2AN() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("Géant");
        Assertions.assertThat(encode).containsOnly("J23");
    }

    @Test
    public void testEANEqualsANE() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("JEANNE");
        Assertions.assertThat(encode).containsOnly("JAN");
    }

    @Test
    public void testLongerEANEqualsANE() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("JEANNETON");
        Assertions.assertThat(encode).containsOnly("JAN2T4");
    }

    @Test
    public void testJeannette() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("JEANNETTE");
        Assertions.assertThat(encode).containsOnly("JAN2T");
    }

    @Test
    public void testOMutedS() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("GROSJEAN");
        Assertions.assertThat(encode).containsOnly("GROJ3");
    }

    @Test
    public void testAMutedS() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("BASMAISON");
        Assertions.assertThat(encode).containsOnly("BAM2Z4");
    }

    @Test
    public void testFinalSS() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("TOREGROSS");
        Assertions.assertThat(encode).containsOnly("TOR2GRO5");
    }

    @Test
    public void testWithEndingRSound() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("PIERE");
        Assertions.assertThat(encode).containsOnly("PI2R");
    }

    @Test
    public void testWithSpaces() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("DE MARI");
        Assertions.assertThat(encode).containsOnly("DEMARI");
    }

    @Test
    public void testWithEnding1X() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("LARYNX");
        Assertions.assertThat(encode).containsOnly("LAR1X");
    }

    @Test
    public void testWithEndingEUX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("HEUREUX");
        Assertions.assertThat(encode).containsOnly("8R8X", "8R8");
    }

    @Test
    public void testWithEndingRIX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("PERDRIX");
        Assertions.assertThat(encode).containsOnly("P2RDRI", "P2RDRIX");
    }

    @Test
    public void testWithEndingOIX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("CROIX");
        Assertions.assertThat(encode).containsOnly("KROIX", "KROI");
    }

    @Test
    public void testWithSCasS() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("ASCENSION");
        Assertions.assertThat(encode).containsOnly("ASK35I4", "A535I4");
    }

    @Test
    public void testWithSCasSK() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("ESCALADE");
        Assertions.assertThat(encode).containsOnly("25KALAD");
    }

    @Test
    public void testEncodeCHFollowedDoubleVowels() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("michael");
        Assertions.assertThat(encode).containsOnly("MIKA2L");
    }

    @Test
    public void testEncodeEndingWithOX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("intox");
        Assertions.assertThat(encode).containsOnly("1TOX");
    }

    @Test
    public void testEncodeEndingWithOSoundAndX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("etaux");
        Assertions.assertThat(encode).containsOnly("2TO", "2TOX");
    }

    @Test
    public void testEncodeEndingWith2SoundAndX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("PAIX");
        Assertions.assertThat(encode).containsOnly("P2");
    }
}