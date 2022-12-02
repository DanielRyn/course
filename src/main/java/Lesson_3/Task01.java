package Lesson_3;

public class Task01 {
    public static void main(String[] args) {
        Human human = Human.builder().name("Daniel").age(21).weight(77).build();
        human.info();
    }
}


