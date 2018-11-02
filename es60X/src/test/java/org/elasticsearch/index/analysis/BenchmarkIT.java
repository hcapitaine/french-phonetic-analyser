package org.elasticsearch.index.analysis;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.junit.Test;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class BenchmarkIT {

    @Test
    public void launchBenchmark() throws RunnerException {

        String targetFolder = Paths.get(
                this.getClass().getResource("/").getFile()).getParent().toString();

        Options opt = new OptionsBuilder()
                .include(".*Benchmark")
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(5)
                .measurementTime(TimeValue.milliseconds(100))
                .measurementIterations(100)
                .threads(4)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-server")
                .resultFormat(ResultFormatType.JSON)
                .result(targetFolder+"/"+FrenchPhoneticBenchmark.class.getName() + ".jmh.json")
        .build();

        Collection<RunResult> run = new Runner(opt).run();
        run.stream()
                .filter(runResult -> "encodeStringFrenchPhonetic".equals(runResult.getPrimaryResult().getLabel()))
                .forEach(
                    runResult ->
                        Assertions.assertThat(runResult.getPrimaryResult().getStatistics().getPercentile(0.95)).isGreaterThan(6000)
                );
    }
}
