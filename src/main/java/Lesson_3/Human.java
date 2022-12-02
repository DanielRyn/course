package Lesson_3;

public class Human {

    private final int age;
    private final int weight;
    private final String name;

    private Human(HumanBuilder humanBuilder) {
        this.age = humanBuilder.age;
        this.weight = humanBuilder.weight;
        this.name = humanBuilder.name;
    }

    public static HumanBuilder builder() {
        return new HumanBuilder();
    }

    public void info() {
        System.out.println(name + " - возраст " + age + ", вес " + weight);
    }

    static class HumanBuilder {

        private int age;
        private int weight;
        private String name;

        public HumanBuilder age(int age) {
            this.age = age;
            return this;
        }

        public HumanBuilder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public HumanBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Human build() {
            return new Human(this);
        }
    }
}

// Реализовать шаблон Builder для класса Human (атрибуты - возраст, имя, вес. Метод: инфо о человеке):
// Для этого шаблона характерно:
// 1. приватный конструктор,
// 2. вложенный статический класс (HumanBuilder)
// 3. статический метод builder(), который возврашает экземпляр типа HumanBuilder.
// Во время инициализации HumanBuilder, создается объект класса Human, и записывается в приватный атрибут.
// 4. Класс HumanBuilder имеет методы, которые имеют тоже самое название, что и атрибуты класса Human,
// которые вызывают сеттеры у экземпляра класса Human.
// 5. HumanBuilder имеет метод build, который возвращает готовый объект типа Human.
// Ожидаемый результат
// Human human = Human.builder().name("Петр").age(20).weight(80).build();
// human.info()
// Петр - возраст 20, вес 80