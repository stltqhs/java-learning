package com.jmh;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author yuqing
 * @date 2024/4/6
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JsonBenchmark {

  @Param(value = {"0", "1", "2", "3"})
  private int index;

  private static Map<String, Object>[] arr = new Map[4];

  private static Gson gson = new Gson();

  public static void main(String[] args) throws RunnerException {
    initData();

    Options opt = new OptionsBuilder()
        .include(JsonBenchmark.class.getSimpleName())
        .result("result.json")
        .resultFormat(ResultFormatType.JSON).build();
    new Runner(opt).run();
  }

  private static void initData() {
    // 1KB
    arr[0] = createMapData(1024);

    // 1MB
    arr[1] = createMapData(1048576);

    // 3MB
    arr[2] = createMapData(3145728);

    // 5MB
    arr[3] = createMapData(5242880);
  }

  private static Map<String, Object> createMapData(int length) {
    Map<String, Object> mapData = new HashMap<>(16);
    mapData.put("a", 1);
    mapData.put("b", new Date());
    mapData.put("c", createString(length));
    mapData.put("d", "d");
    return mapData;
  }

  private static String createString(int length) {
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      char c = (char) ('a' + (i % 26));
      sb.append(c);
    }
    return sb.toString();
  }

  @Benchmark
  public void testGsonStringify(Blackhole blackhole) {
    blackhole.consume(gson.toJson(arr[index]));
  }

  @Benchmark
  public void testFastjsonStringify(Blackhole blackhole) {
    blackhole.consume(JSON.toJSONString(arr[index]));
  }
}
