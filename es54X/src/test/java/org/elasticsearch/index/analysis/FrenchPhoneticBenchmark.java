package org.elasticsearch.index.analysis;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.RefinedSoundex;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@State(Scope.Benchmark)
public class FrenchPhoneticBenchmark {

    @State(Scope.Benchmark)
    public static class FrenchPhoneticFactory
    {
        FrenchPhonetic instance;

        @Setup(Level.Trial)
        public void initialize()
        {
            instance = new FrenchPhonetic();
        }

        @TearDown(Level.Trial)
        public void shutdown()
        {
            // Nothing to do
        }
    }

    @State(Scope.Benchmark)
    public static class RefinedSoundexFactory
    {
        RefinedSoundex instance;

        @Setup(Level.Trial)
        public void initialize()
        {
            instance = new RefinedSoundex();
        }

        @TearDown(Level.Trial)
        public void shutdown()
        {
            // Nothing to do
        }
    }

    protected String data;

    private String getWordFromDictionnary() throws URISyntaxException, IOException {
        Stream<String> lines = Files.lines(Paths.get(FrenchPhoneticBenchmark.class.getResource("/fr-classique.dic").toURI()));
        return lines.findAny().map(line -> {
            int indexSlash = line.indexOf('/');
            int indexSpace = line.indexOf(' ');
            if(indexSlash != -1 && indexSlash<indexSpace){
                return line.substring(0, indexSlash);
            } else {
                if(indexSpace != -1){
                    return line.substring(0, indexSpace);
                } else {
                    return line;
                }
            }
        }).get();
    }

    @Setup(Level.Trial)
    public void initializeTrial() throws IOException, URISyntaxException {
        data = getWordFromDictionnary();
    }

    @Setup(Level.Invocation)
    public void initializeInvocation()
    {
        data.charAt(0);
    }

    @TearDown(Level.Invocation)
    public void shutdownInvocation() { }

    @TearDown(Level.Trial)
    public void shutdownTrial() { }



    @Benchmark
    @BenchmarkMode({Mode.Throughput/* Mode.AverageTime/*, Mode.SampleTime*/})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(8)
    @Measurement(iterations = 10, time= 1, timeUnit = TimeUnit.SECONDS)
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @Threads(4)
    public String encodeStringFrenchPhonetic(FrenchPhoneticFactory frenchPhonetic) throws EncoderException {
        return frenchPhonetic.instance.encode(data);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput/* Mode.AverageTime/*, Mode.SampleTime*/})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(8)
    @Measurement(iterations = 10, time= 1, timeUnit = TimeUnit.SECONDS)
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @Threads(4)
    public String encodeStringRefinedSoundex(RefinedSoundexFactory refinedSoundex) throws EncoderException {
        return refinedSoundex.instance.encode(data);
    }
}
