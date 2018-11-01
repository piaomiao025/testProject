package com.man.demo.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-10-18 15:36
 **/
public class MonoDemo {
    public static void main(String[] args) {
//        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
//        Mono.justOrEmpty(Optional.of("hello")).subscribe(System.out::println);
//
//        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);

//        Flux.range(1, 100).buffer(20).subscribe(System.out::println);

//        Flux.intervalMillis(100).bufferMillis(1201).take(2).toStream().forEach(System.out::println);

//        Flux.range(1, 10).bufferUntil( i -> i % 2 == 0).subscribe(System.out::println);
//
//        Flux.range(1, 10).bufferWhile( i -> i % 2 == 0).subscribe(System.out::println);

//        Flux.range(1, 100).window(20).subscribe(System.out::println);

//        Flux.just("a", "c").zipWith(Flux.just("b", "d")).subscribe(System.out::println);

//        Flux.just("a", "c").zipWith(Flux.just("b", "d"), (s1, s2) -> String.format("%s-%s", s1, s2)).subscribe(System.out::println);

        Flux.just(5, 10).flatMap(x -> Flux.intervalMillis(x * 10, 100).take(x))
                .toStream().forEach(System.out::println);

    }
}
