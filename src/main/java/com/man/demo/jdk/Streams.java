package com.man.demo.jdk;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-21 17:14
 **/
public class Streams {
    private enum Status {
        OPEN, CLOSED
    }

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task(final Status status, final Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return this.points;
        }

        public Status getStatus() {
            return this.status;
        }

        @Override
        public String toString() {
            return String.format("{%s, %d]", status, points);
        }
    }

    public static void main(String[] args) {
        final Collection<Task> tasks = Arrays.asList(new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 13),
                new Task(Status.CLOSED, 8));
        final long totalPointsOfOpenTasks = tasks.stream().filter(task -> task.getStatus() == Status.OPEN)
                .mapToInt(Task::getPoints).sum();

        final double totalPoints = tasks.stream().parallel().map(task -> task.getPoints()).reduce(-1, Integer::sum);

        final Map<Status, List<Task>> map = tasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus));

        final Collection<String> result = tasks
                .stream()
                .mapToInt(Task::getPoints)
                .asLongStream()
                .mapToDouble(points -> points / totalPoints)
                .boxed()
                .mapToLong(weigh -> (long)(weigh * 100))
                .mapToObj(percentage -> percentage + "%")
                .collect(Collectors.toList());

//        System.out.println(map);
//        System.out.println(result);

        Collection<Task> newTasks = tasks.stream().filter(task -> task.getStatus() == Status.OPEN).collect(Collectors.toList());

        System.out.println(newTasks);
//        System.out.println("Total points:" + totalPointsOfOpenTasks);
//        System.out.println("Total points:" + totalPoints);
    }
}
