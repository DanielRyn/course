package Lesson_02;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.*;

public class MachineStatistics {
    private MachineStatistics() {
    }

    public static List<String> byNumber(Map<Integer, Map<String, String[]>> data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (data.isEmpty()) {
            throw new IllegalArgumentException();
        }
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        data.forEach((kOne, vOne) -> vOne.forEach((kTwo, vTwo) ->
                linkedHashSet.addAll(stream(vTwo).filter(o -> o.matches("^М\\d{3}АВ\\d{2,3}$")).toList()))
        );
        return linkedHashSet.stream().toList();
    }
}
