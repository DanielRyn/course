package Lesson_02;

import java.util.List;
import java.util.stream.Stream;

public class Task01 {
    public static void main(String[] args) {
        List<String> machineList = MachineStatistics.byNumber(GeneratorExpertHomework.getData());
        System.out.println("\n  - Всего машин со спец номерами: " + machineList.size());
        if (machineList.size() > 0) {
            showLine();
            Stream.iterate(0, o -> o + 1).limit(machineList.size()).forEach(o -> System.out.println(
                    "  - " + (o + 1) + "\t\t" + machineList.get(o))
            );
            showLine();
        }
    }

    private static void showLine() {
        System.out.print("<" + "-".repeat(30) + ">\n");
    }
}
