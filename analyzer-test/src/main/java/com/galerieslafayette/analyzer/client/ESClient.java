package com.galerieslafayette.analyzer.client;

import com.galerieslafayette.analyzer.document.MyDocument;
import com.galerieslafayette.analyzer.document.SearchResponse;
import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.io.File;
import java.util.Map;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface ESClient {

    @RequestLine("PUT /my_index/my_type/{id}?refresh=wait_for")
    void addDocument(MyDocument myDocument, @Param("id")Long id);

    @RequestLine("POST /my_index/my_type/_search")
    @Body("%7B\"query\":%7B\"match\" : %7B\"content\" :%7B\"query\": \"{searchText}\"%7D%7D%7D%7D")
    SearchResponse search(@Param("searchText") String searchText);

    @RequestLine("DELETE /my_index/my_type/*")
    void deleteAll();

    @RequestLine("PUT /my_index")
    void createIndex(String body);

    @RequestLine("PUT /my_index/_mapping/my_type")
    void applyMapping(String settings);
}
