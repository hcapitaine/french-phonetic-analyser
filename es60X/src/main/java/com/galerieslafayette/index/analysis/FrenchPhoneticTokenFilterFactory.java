package com.galerieslafayette.index.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

public class FrenchPhoneticTokenFilterFactory extends AbstractTokenFilterFactory {

    @Inject
    public FrenchPhoneticTokenFilterFactory(IndexSettings indexSettings, Environment environment, @Assisted String name, @Assisted Settings settings) {
        super(indexSettings, name, settings);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new FrenchPhoneticAnalyzer(tokenStream);
    }
}
