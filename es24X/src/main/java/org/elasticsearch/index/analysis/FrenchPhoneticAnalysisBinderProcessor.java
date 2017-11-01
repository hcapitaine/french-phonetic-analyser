package org.elasticsearch.index.analysis;

public class FrenchPhoneticAnalysisBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {

    @Override
    public void processTokenFilters(TokenFiltersBindings tokenFiltersBindings) {
        tokenFiltersBindings.processTokenFilter("french_phonetic", FrenchPhoneticTokenFilterFactory.class);
    }
}
