package ru.lab.finishedgoods;

public class Main {

    public static void main(String[] args) {

        // Создаём первого клиента через основной конструктор.
        Customer firstCustomer = new Customer(
                1,
                "ООО Ромашка",
                "г. Москва, ул. Ленина, 10",
                "+79991234567",
                "Иванов Иван"
        );

        // Создаём второго клиента через перегруженный конструктор.
        Customer secondCustomer = new Customer(
                "1;ООО Ромашка;г. Москва, ул. Ленина, 10;+79991234567;Иванов Иван"
        );

        // Создаём третьего клиента с тем же ID, но другими данными.
        Customer thirdCustomer = new Customer(
                1,
                "ООО Ромашка",
                "г. Казань, ул. Центральная, 5",
                "+79991234568",
                "Петров Петр"
        );

        // Создаём четвёртого клиента с другим ID.
        Customer fourthCustomer = new Customer(
                2,
                "ООО Василёк",
                "г. Москва, ул. Мира, 15",
                "+79991234569",
                "Сидоров Сергей"
        );

        // Проверяем полный вывод.
        System.out.println("=== ПОЛНАЯ ИНФОРМАЦИЯ ===");
        System.out.println(firstCustomer.toFullString());

        // Проверяем краткий вывод.
        System.out.println("\n=== КРАТКАЯ ИНФОРМАЦИЯ ===");
        System.out.println(firstCustomer.toShortString());

        // Проверяем геттеры.
        System.out.println("\n=== ПОЛУЧЕНИЕ ДАННЫХ ЧЕРЕЗ ГЕТТЕРЫ ===");
        System.out.println("ID: " + firstCustomer.getCustomerId());
        System.out.println("Название: " + firstCustomer.getName());
        System.out.println("Адрес: " + firstCustomer.getAddress());
        System.out.println("Телефон: " + firstCustomer.getPhone());
        System.out.println("Контактное лицо: " + firstCustomer.getContactPerson());

        // Проверка сеттера.
        System.out.println("\n=== ИЗМЕНЕНИЕ ЧЕРЕЗ СЕТТЕР ===");
        System.out.println("До изменения: " + firstCustomer.getName());
        firstCustomer.setName();
        System.out.println("После изменения: " + firstCustomer.getName());

        // Проверяем обычный вывод объекта через toString().
        System.out.println("\n=== ВЫЗОВ toString() ===");
        System.out.println(firstCustomer);

        // Проверяем сравнение объектов.
        System.out.println("\n=== СРАВНЕНИЕ ОБЪЕКТОВ ===");
        System.out.println(
                "Первый и второй клиенты равны: "
                        + firstCustomer.equals(secondCustomer)
        );

        System.out.println(
                firstCustomer == secondCustomer
        );

        System.out.println(
                "Первый и третий клиенты равны: "
                        + firstCustomer.equals(thirdCustomer)
        );

        System.out.println(
                "Первый и четвёртый клиенты равны: "
                        + firstCustomer.equals(fourthCustomer)
        );

        // Проверяем согласованность equals() и hashCode().
        System.out.println("\n=== ПРОВЕРКА hashCode() ===");
        System.out.println("Хэш первого: " + firstCustomer.hashCode());
        System.out.println("Хэш второго: " + secondCustomer.hashCode());
        System.out.println(
                "Хеш-коды первого и второго клиентов совпадают: "
                        + (firstCustomer.hashCode() == secondCustomer.hashCode())
        );

        System.out.println("\n=== КРАТКАЯ ВЕРСИЯ ОТДЕЛЬНЫМ КЛАССОМ ===");

        ShortCustomer shortCustomer = new ShortCustomer(
                1,
                "ООО Ромашка",
                "+79991234567"
        );

        System.out.println(shortCustomer);

        System.out.println("\n=== ПОЛУЧЕНИЕ ДАННЫХ SHORTCUSTOMER ===");
        System.out.println("ID: " + shortCustomer.getCustomerId());
        System.out.println("Название: " + shortCustomer.getName());
        System.out.println("Телефон: " + shortCustomer.getPhone());

        System.out.println("\n=== Преобразование Customer в ShortCustomer через Mapper ===");

        ShortCustomer mappedCustomer = CustomerMapper.toShortCustomer(firstCustomer);

        System.out.println("Результат преобразования:");
        System.out.println(mappedCustomer.toShortString());

        System.out.println("\n=== ДЕМОНСТРАЦИЯ ПОЛИМОРФИЗМА ===");

// Переменная интерфейсного типа хранит полный объект Customer.
        CustomerView fullView = firstCustomer;

// Переменная того же интерфейсного типа хранит краткий объект ShortCustomer.
        CustomerView shortView = shortCustomer;

// Получаем данные через общий интерфейс.
        System.out.println("Полная версия:");
        System.out.println("ID: " + fullView.getCustomerId());
        System.out.println("Название: " + fullView.getName());
        System.out.println("Телефон: " + fullView.getPhone());

        System.out.println("\nКраткая версия:");
        System.out.println("ID: " + shortView.getCustomerId());
        System.out.println("Название: " + shortView.getName());
        System.out.println("Телефон: " + shortView.getPhone());
    }
}
