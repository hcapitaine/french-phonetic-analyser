package com.galerieslafayette.analyzer.document;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchHits {

    @JsonProperty("_source")
    private MyDocument source;

    public MyDocument getSource() {
        return source;
    }

    public void setSource(MyDocument source) {
        this.source = source;
    }

}
