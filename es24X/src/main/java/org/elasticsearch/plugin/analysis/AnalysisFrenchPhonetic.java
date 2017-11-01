package org.elasticsearch.plugin.analysis;

import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.index.analysis.FrenchPhoneticAnalysisBinderProcessor;
import org.elasticsearch.plugins.Plugin;

public class AnalysisFrenchPhonetic extends Plugin {


    @Override
    public String name() {
        return "french_phonetic";
    }

    @Override
    public String description() {
        return null;
    }

    public void onModule(AnalysisModule module) {
        module.addProcessor(new FrenchPhoneticAnalysisBinderProcessor());
    }
}
