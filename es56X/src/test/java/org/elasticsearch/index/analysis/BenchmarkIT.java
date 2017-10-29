package org.elasticsearch.index.analysis;

import org.junit.Test;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BenchmarkIT {

    @Test
    public void launchBenchmark() throws RunnerException {

        String targetFolder = Paths.get(
                this.getClass().getResource("/").getFile()).getParent().toString();

        Options opt = new OptionsBuilder()
                .include(".*Benchmark")
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(5)
                .measurementTime(TimeValue.seconds(1))
                .measurementIterations(10)
                .threads(1)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-server")
                .resultFormat(ResultFormatType.JSON)
                .result(targetFolder+"/"+FrenchPhoneticBenchmark.class.getName() + ".jmh.json")
        .build();

        new Runner(opt).run();
    }
}
