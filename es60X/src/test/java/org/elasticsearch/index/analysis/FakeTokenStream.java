package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.TokenStream;

import java.io.IOException;

public final class FakeTokenStream extends TokenStream {

    @Override
    public final boolean incrementToken() throws IOException {
        return false;
    }
}
