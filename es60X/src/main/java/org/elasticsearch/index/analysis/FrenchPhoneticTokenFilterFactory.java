package org.elasticsearch.index.analysis;

import com.aper.analysis.FrenchPhoneticAnalyzer;
import org.apache.commons.codec.language.bm.NameType;
import org.apache.commons.codec.language.bm.RuleType;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public class FrenchPhoneticTokenFilterFactory extends AbstractTokenFilterFactory{

    private final String[] languageset;
    private final NameType nametype;
    private final RuleType ruletype;
    private final int maxcodelength;
    private final boolean replace;
    private TokenFilter analyzer;


    @Inject
    public FrenchPhoneticTokenFilterFactory(IndexSettings indexSettings, Environment environment, @Assisted String name, @Assisted Settings settings) {
        super(indexSettings, name, settings);
        this.languageset = null;
        this.nametype = null;
        this.ruletype = null;
        this.maxcodelength = 0;
        this.replace = settings.getAsBoolean("replace", true);
        // weird, encoder is null at last step in SimplePhoneticAnalysisTests, so we set it to metaphone as default
        String encodername = settings.get("encoder", "french_phonetic");
        if(!"french_phonetic".equalsIgnoreCase(encodername)){
            throw new IllegalArgumentException("unknown encoder [" + encodername + "] for phonetic token filter");
        }
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new FrenchPhoneticAnalyzer(tokenStream);
    }
}
