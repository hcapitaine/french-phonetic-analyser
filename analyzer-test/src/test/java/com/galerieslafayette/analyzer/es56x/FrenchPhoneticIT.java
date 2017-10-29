package com.galerieslafayette.analyzer.es56x;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.galerieslafayette.analyzer.client.BodyStringEncoder;
import com.galerieslafayette.analyzer.client.ESClient;
import com.galerieslafayette.analyzer.document.MyDocument;
import com.galerieslafayette.analyzer.document.SearchResponse;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.IndexSettings;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import java.io.IOException;

import static java.lang.ClassLoader.getSystemResource;
import static java.lang.ClassLoader.getSystemResourceAsStream;

public class FrenchPhoneticIT {

    private String INDEX_TYPE = "my_type";

    private ESClient esClient;

    @Before
    public void setup(){

        ObjectMapper esClientMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        esClient = Feign.builder()
                .client(new OkHttpClient())
                .decoder(new JacksonDecoder(esClientMapper))
                .encoder(new BodyStringEncoder(esClientMapper))
                .target(ESClient.class, "http://localhost:9222");
    }

    @Test
    public void test_analyzer_with_version_54X() throws IOException, InterruptedException {
        execute_test("5.4."+System.getProperty("es54X.version"), "es-5.4.X.zip");
    }

    @Test
    public void test_analyzer_with_version_56X() throws IOException, InterruptedException {
        execute_test("5.6."+System.getProperty("es56X.version"), "es-5.6.X.zip");
    }

    private void execute_test(String esVersion, String analyzerFileName) throws IOException, InterruptedException {
        final EmbeddedElastic embeddedElastic = EmbeddedElastic.builder()
            .withElasticVersion(esVersion)
            .withSetting(PopularProperties.HTTP_PORT, 9222)
            .withSetting(PopularProperties.CLUSTER_NAME, "my_cluster")
            .withPlugin("file://"+getSystemResource(analyzerFileName).getPath())
            .withIndex("my_index", IndexSettings.builder()
                    .withType(INDEX_TYPE, getSystemResourceAsStream("my_type_mapping.json"))
                    .withSettings(getSystemResourceAsStream("my_index_settings.json"))
                    .build())
            .build()
            .start();

        MyDocument myDocument1 = new MyDocument();
        myDocument1.setId(1l);
        myDocument1.setContent("Harold Capitaine");
        MyDocument myDocument2 = new MyDocument();
        myDocument2.setId(1l);
        myDocument2.setContent("Yves Mathieu Rideau Baudin");
        MyDocument myDocument3 = new MyDocument();
        myDocument3.setId(1l);
        myDocument3.setContent("Alexandre Pocheau");
        MyDocument myDocument4 = new MyDocument();
        myDocument4.setId(1l);
        myDocument4.setContent("Jonathan Baranzin");

        esClient.addDocument(myDocument1, 1l);
        esClient.addDocument(myDocument2, 2l);
        esClient.addDocument(myDocument3, 3l);
        esClient.addDocument(myDocument4, 4l);

        SearchResponse response1 = esClient.search("arold");
        SearchResponse response2 = esClient.search("bodain");

        embeddedElastic.stop();

        Assertions.assertThat(response1.getHits()).isNotNull();
        Assertions.assertThat(response1.getHits().getHits()).isNotEmpty();
        Assertions.assertThat(response1.getHits().getHits()).extracting("source").extracting("id").containsOnly(myDocument1.getId());

        Assertions.assertThat(response2.getHits()).isNotNull();
        Assertions.assertThat(response2.getHits().getHits()).isNotEmpty();
        Assertions.assertThat(response2.getHits().getHits()).extracting("source").extracting("id").containsOnly(myDocument2.getId());
    }
}
