package org.elasticsearch.index.analysis;

import com.aper.analysis.FrenchPhoneticAnalyzer;
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
        Assertions.assertThat(encode).contains("T2RMOM2TR");
    }

    @Test
    public void testEncodeWithDoubleF() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("bouzeliffa");
        Assertions.assertThat(encode).contains("BOUZ2LIFA");
    }

    @Test
    public void testEncodeNumber() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("5");
        Assertions.assertThat(encode).contains("");
    }

    @Test
    public void testEncodeWithFinalX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("CEDEX");
        Assertions.assertThat(encode).contains("52D2X");
    }

    @Test
    public void testEncodeWithStartingOEU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("oeuvre");
        Assertions.assertThat(encode).contains("8VR");
    }

    @Test
    public void testEncodeWithContainsOEU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("coeur");
        Assertions.assertThat(encode).contains("K8R");
    }

    @Test
    public void testEncodeWithEndingOEU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("voeu");
        Assertions.assertThat(encode).contains("V8");
    }

    @Test
    public void testEncodeWithContainsEU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("veuve");
        Assertions.assertThat(encode).contains("V8V");
    }

    @Test
    public void testEncodeWithEndingsEU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("matthieu");
        Assertions.assertThat(encode).contains("MATI8");
    }

    @Test
    public void testEncodeWithStartingEU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("europe");
        Assertions.assertThat(encode).contains("8ROP");
    }

    @Test
    public void testEncodeWithMutedContainsHNotPreceded() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("hache");
        Assertions.assertThat(encode).contains("ACH");
    }

    @Test
    public void testEncodeWithMutedContainsHPrecedeByVowels() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("bahut");
        Assertions.assertThat(encode).contains("BAU");
    }



    @Test
    public void testEncodeWithMutedStartingH() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("haricots");
        Assertions.assertThat(encode).contains("ARIKO");
    }

    @Test
    public void testEncodeWithMutedEndingX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("hiboux");
        Assertions.assertThat(encode).contains("IBOU");
    }

    @Test
    public void testEncodeWithMutedEndingTS() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("couts");
        Assertions.assertThat(encode).contains("KOU");
    }

    @Test
    public void testEncodeWithTrailingT() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("rat");
        Assertions.assertThat(encode).contains("RA");
    }

    @Test
    public void testEncodeWithTrailingY() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("dauby");
        Assertions.assertThat(encode).contains("DOBI");
    }

    @Test
    public void testEncodeWithTwoTrailingMutedConsonnant() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("ouest");
        Assertions.assertThat(encode).contains("OU25");
    }

    @Test
    public void testEncodeWithTrailingD() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("suspend");
        Assertions.assertThat(encode).contains("5U5P3");
    }

    @Test
    public void testEncodeWithNotMutedH() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("chat");
        Assertions.assertThat(encode).contains("CHA");
    }

    @Test
    public void testEncodeWithDoubleCH() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("cherche");
        Assertions.assertThat(encode).contains("CH2RCH");
    }

    @Test
    public void testEncodeWithCAsS() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("acceptable");
        Assertions.assertThat(encode).contains("AK52PTABL");
    }

    @Test
    public void testEncodeWithCAsK() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("consommable");
        Assertions.assertThat(encode).contains("K45OMABL");
    }

    @Test
    public void testEncodeWithDoubleConsonant() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("boulette");
        Assertions.assertThat(encode).contains("BOUL2T");
    }

    @Test
    public void testEncodeWithQUAsK() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("graphique");
        Assertions.assertThat(encode).contains("GRAFIK");
    }

    @Test
    public void testEncodeWithTrailingR() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("merard");
        Assertions.assertThat(encode).contains("M2RAR");
    }

    @Test
    public void testEncodeWithQUAsKNotEnds() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("graphiques");
        Assertions.assertThat(encode).contains("GRAFIK");
    }

    @Test
    public void testEncodeWithQUAsKEnds() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("graphiqu");
        Assertions.assertThat(encode).contains("GRAFIK");
    }

    @Test
    public void testEncodeWithPHAlmostEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("graphe");
        Assertions.assertThat(encode).contains("GRAF");
    }

    @Test
    public void testEncodeWithPHEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("graph");
        Assertions.assertThat(encode).contains("GRAF");
    }


    @Test
    public void testEncodeWithAIEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("balai");
        Assertions.assertThat(encode).contains("BAL2");
    }

    @Test
    public void testEncodeWithCEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("FRANC");
        Assertions.assertThat(encode).contains("FR3");
    }

    @Test
    public void testEncodeWithCKEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("FRANCK");
        Assertions.assertThat(encode).contains("FR3K");
    }

    @Test
    public void testEncodeWithKEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("FRANK");
        Assertions.assertThat(encode).contains("FR3K");
    }

    @Test
    public void testEncodeWithSEasZEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("BRAISE");
        Assertions.assertThat(encode).contains("BR2Z");
    }

    @Test
    public void testEncodeWithAYEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("tramway");
        Assertions.assertThat(encode).contains("TR3V2");
    }

    @Test
    public void testEncodeWithANEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("faisan");
        Assertions.assertThat(encode).contains("F2Z3");
    }

    @Test
    public void testEncodeWithGEEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("tige");
        Assertions.assertThat(encode).contains("TIJ");
    }

    @Test
    public void testEncodeWithAIMEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("daim");
        Assertions.assertThat(encode).contains("D1");
    }

    @Test
    public void testEncodeWithAINEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("bain");
        Assertions.assertThat(encode).contains("B1");
    }

    @Test
    public void testEncodeWithEINEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("plein");
        Assertions.assertThat(encode).contains("PL1");
    }

    @Test
    public void testEncodeWithEINContains() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("ceinture");
        Assertions.assertThat(encode).contains("51TUR");
    }

    @Test
    public void testEncodeWithEINContainsFollowByVowels() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("seime");
        Assertions.assertThat(encode).contains("52M");
    }

    @Test
    public void testEncodeWithAINContains() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("maintenant");
        Assertions.assertThat(encode).contains("M1TEN3");
    }

    @Test
    public void testEncodeWithAINAlmostEnding() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("bains");
        Assertions.assertThat(encode).contains("B1");
    }

    @Test
    public void testEncodeWithEY() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("seyante");
        Assertions.assertThat(encode).contains("52I3T");
    }

    @Test
    public void testEncodeWithTrailingEY() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("volley");
        Assertions.assertThat(encode).contains("VOL2");
    }

    @Test
    public void testEncodeWithEndingEY() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("chardonay");
        Assertions.assertThat(encode).contains("CHARDON2");
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
        Assertions.assertThat(encode).contains("P4");
    }

    @Test
    public void testEncodeWithOM() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("pompe");
        Assertions.assertThat(encode).contains("P4P");
    }

    @Test
    public void testEncodeWithYM() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("cymbale");
        Assertions.assertThat(encode).contains("51BAL");
    }

    @Test
    public void testEncodeWithYN() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("pharynx");
        Assertions.assertThat(encode).contains("FAR1X");
    }

    @Test
    public void testEncodeWithAN() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("pendant");
        Assertions.assertThat(encode).contains("P3D3");
    }

    @Test
    public void testEncodeWithEndingEAU() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("eau");
        Assertions.assertThat(encode).contains("O");
    }

    @Test
    public void testEncodeWithEAUContains() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("tableautage");
        Assertions.assertThat(encode).contains("TABLOTAJ");
    }

    @Test
    public void testEncodeWithAUContains() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("vautrer");
        Assertions.assertThat(encode).contains("VOTR2");
    }

    @Test
    public void testEncodeWithANFollowByVowels() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("anhile");
        Assertions.assertThat(encode).contains("ANIL");
    }

    @Test
    public void testEncodeWithAMFollowByVowels() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("amerique");
        Assertions.assertThat(encode).contains("AM2RIK");
    }

    @Test
    public void testEncodeWithVowelTION() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("ebulition");
        Assertions.assertThat(encode).contains("2BULISI4");
    }

    @Test
    public void testEncodeWithConsonantTION() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("bastion");
        Assertions.assertThat(encode).contains("BA5TI4");
    }


    @Test
    public void testEncodeWithEN() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("pendant");
        Assertions.assertThat(encode).contains("P3D3");
    }

    @Test
    public void testEncodeWithNullString() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("");
        Assertions.assertThat(encode).contains("");
    }

    @Test
    public void testEndingERT() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("AUBERT");
        Assertions.assertThat(encode).contains("OB2R");
    }

    @Test
    public void testEndingER() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("OBER");
        Assertions.assertThat(encode).contains("OB2");
    }

    @Test
    public void testBeginningER() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("HERTZIEN");
        Assertions.assertThat(encode).contains("2RTZI3");
    }

    @Test
    public void testEndindAUD() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("BADAUDS");
        Assertions.assertThat(encode).contains("BADO");
    }

    @Test
    public void testEndingCCO() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("BACCO");
        Assertions.assertThat(encode).contains("BAKO");
    }

    @Test
    public void testEndingCO() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("BACO");
        Assertions.assertThat(encode).contains("BAKO");
    }
    @Test
    public void testEndingCOT() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("BACOT");
        Assertions.assertThat(encode).contains("BAKO");
    }

    @Test
    public void testMauriceMatch() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("MAURISSE");
        Assertions.assertThat(encode).contains("MORI5");
    }

    @Test
    public void testMutedEndingZ() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("ALLEZ");
        Assertions.assertThat(encode).contains("AL2");
    }

    @Test
    public void testEndingZ() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("ALAISE");
        Assertions.assertThat(encode).contains("AL2Z");
    }

    @Test
    public void testEnding2() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("André");
        Assertions.assertThat(encode).contains("3DR2");
    }

    @Test
    public void testEndingE() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("Andrey");
        Assertions.assertThat(encode).contains("3DR2");
    }

    @Test
    public void testEas2() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("Audré");
        Assertions.assertThat(encode).contains("ODR2");
    }

    @Test
    public void testEYas2() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("Audrey");
        Assertions.assertThat(encode).contains("ODR2");
    }

    @Test
    public void testEANEqualsEN() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("JEAN");
        Assertions.assertThat(encode).contains("J3");
    }

    @Test
    public void testEANEquals2AN() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("Géant");
        Assertions.assertThat(encode).contains("J23");
    }

    @Test
    public void testEANEqualsANE() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("JEANNE");
        Assertions.assertThat(encode).contains("JAN");
    }

    @Test
    public void testLongerEANEqualsANE() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("JEANNETON");
        Assertions.assertThat(encode).contains("JAN2T4");
    }

    @Test
    public void testJeannette() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("JEANNETTE");
        Assertions.assertThat(encode).contains("JAN2T");
    }

    @Test
    public void testOMutedS() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("GROSJEAN");
        Assertions.assertThat(encode).contains("GROJ3");
    }

    @Test
    public void testAMutedS() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("BASMAISON");
        Assertions.assertThat(encode).contains("BAM2Z4");
    }

    @Test
    public void testFinalSS() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("TOREGROSS");
        Assertions.assertThat(encode).contains("TOR2GRO5");
    }

    @Test
    public void testWithEndingRSound() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("PIERE");
        Assertions.assertThat(encode).contains("PI2R");
    }

    @Test
    public void testWithSpaces() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("DE MARI");
        Assertions.assertThat(encode).contains("DEMARI");
    }

    @Test
    public void testWithEnding1X() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("LARYNX");
        Assertions.assertThat(encode).contains("LAR1X");
    }

    @Test
    public void testWithEndingEUX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("HEUREUX");
        Assertions.assertThat(encode).contains("8R8");
    }

    @Test
    public void testWithEndingRIX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("PERDRIX");
        Assertions.assertThat(encode).contains("P2RDRI");
    }

    @Test
    public void testWithEndingOIX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("CROIX");
        Assertions.assertThat(encode).contains("KROI");
    }

    @Test
    public void testWithSCasS() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("ASCENSION");
        Assertions.assertThat(encode).contains("A535I4");
    }

    @Test
    public void testEncodeCHFollowedDoubleVowels() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("michael");
        Assertions.assertThat(encode).contains("MIKA2L");
    }

    @Test
    public void testEncodeEndingWithOX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("intox");
        Assertions.assertThat(encode).contains("1TOX");
    }

    @Test
    public void testEncodeEndingWithOSoundAndX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("etaux");
        Assertions.assertThat(encode).contains("2TO");
    }

    @Test
    public void testEncodeEndingWith2SoundAndX() {
        FrenchPhoneticAnalyzer frenchPhonetic = new FrenchPhoneticAnalyzer(new FakeTokenStream());
        List<String> encode = frenchPhonetic.encode("PAIX");
        Assertions.assertThat(encode).contains("P2");
    }
}