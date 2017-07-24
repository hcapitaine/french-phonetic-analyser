package org.elasticsearch.plugin.analysis;

import org.elasticsearch.index.analysis.FrenchPhoneticTokenFilterFactory;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.Collections;
import java.util.Map;

import static org.elasticsearch.indices.analysis.AnalysisModule.AnalysisProvider;

public class AnalysisFrenchPhonetic extends Plugin implements AnalysisPlugin{

    @Override
    public Map<String, AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
        return Collections.singletonMap("french_phonetic", FrenchPhoneticTokenFilterFactory::new);
    }

}
