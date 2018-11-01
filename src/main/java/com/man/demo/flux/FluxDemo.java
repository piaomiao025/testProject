package com.man.demo.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-10-18 10:52
 **/
public class FluxDemo {
    public static void main(String[] args) {
//        Flux.just("Hello", "World").subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });
//        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(x -> System.out.println(x));
//
//        Flux.range(1, 10).subscribe(System.out::println);
//
//
//        Flux.generate(new Consumer<SynchronousSink<String>>() {
//            @Override
//            public void accept(SynchronousSink<String> synchronousSink) {
//                synchronousSink.next("Hello");
//                synchronousSink.complete();
//            }
//        }).subscribe(System.out::println);
//
//        Flux.generate(sink -> {
//            sink.next("hello");
//            sink.complete();
//        }).subscribe(System.out::println);

        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);

        Flux.create(sink -> {
            for (int i = 0 ; i < 10 ; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }
}
