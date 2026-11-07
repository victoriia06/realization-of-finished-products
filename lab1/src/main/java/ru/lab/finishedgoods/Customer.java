package ru.lab.finishedgoods;

import java.util.Objects;

public class Customer implements CustomerView {

    private long customerId;
    private String name;
    private String address;
    private String phone;
    private String contactPerson;

    // Основной конструктор: принимает все поля отдельно.
    public Customer(long customerId, String name, String address,
                    String phone, String contactPerson) {

        validateCustomerId(customerId);
        validateName(name);
        validateAddress(address);
        validatePhone(phone);
        validateContactPerson(contactPerson);

        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.contactPerson = contactPerson;
    }

    // Перегруженный конструктор: принимает данные одной строкой.
    public Customer(String data) {
        this(parseData(data));
    }

    // Вспомогательный конструктор для передачи разобранных данных
    // в основной конструктор.
    private Customer(ParsedCustomer data) {
        this(
                data.customerId(),
                data.name(),
                data.address(),
                data.phone(),
                data.contactPerson()
        );
    }

    // Разбирает строку и получает значения всех полей.
    private static ParsedCustomer parseData(String data) {
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException(
                    "Строка с данными клиента не должна быть пустой."
            );
        }

        String[] parts = data.split(";", -1);

        if (parts.length != 5) {
            throw new IllegalArgumentException(
                    "Строка должна содержать ровно 5 полей, разделённых точкой с запятой."
            );
        }

        try {
            long customerId = Long.parseLong(parts[0].trim());

            return new ParsedCustomer(
                    customerId,
                    parts[1].trim(),
                    parts[2].trim(),
                    parts[3].trim(),
                    parts[4].trim()
            );

        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Идентификатор клиента должен быть целым числом.",
                    exception
            );
        }
    }

    // Внутренняя структура для временного хранения разобранных данных.
    private record ParsedCustomer(
            long customerId,
            String name,
            String address,
            String phone,
            String contactPerson
    ) {
    }

    // Геттеры для получения значений закрытых полей.

    public long getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setName() {
        this.name = "Новое название клиента";
    }

    // Возвращает полную информацию о клиенте.
    public String toFullString() {
        return "Клиент:\n" +
                "ID: " + customerId + "\n" +
                "Название: " + name + "\n" +
                "Адрес: " + address + "\n" +
                "Телефон: " + phone + "\n" +
                "Контактное лицо: " + contactPerson;
    }

    // Возвращает краткую информацию о клиенте.
    public String toShortString() {
        return "Клиент: " + name + " (ID: " + customerId + ")\n" +
                "Телефон: " + phone;
    }

    // Сравнивает клиентов по идентификатору.
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Customer customer = (Customer) object;

        return customerId == customer.customerId;
    }

    // Возвращает хеш-код на основе идентификатора клиента.
    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    // При обычном выводе объекта возвращается полная информация.
    @Override
    public String toString() {
        return toFullString();
    }

    public static void validateCustomerId(long customerId) {
        if (customerId <= 0) {
            throw new IllegalArgumentException(
                    "Идентификатор клиента должен быть положительным числом."
            );
        }
    }

    public static void validateName(String name) {
        validateNotBlank(name, "Имя клиента не должно быть пустым.");
    }

    public static void validateAddress(String address) {
        validateNotBlank(address, "Адрес клиента не должен быть пустым.");
    }

    public static void validateContactPerson(String contactPerson) {
        validateNotBlank(
                contactPerson,
                "Контактное лицо не должно быть пустым."
        );
    }

    private static void validateNotBlank(String value, String errorMessage) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validatePhone(String phone) {
        if (phone == null || !phone.matches("\\+?[0-9()\\-\\s]+")) {
            throw new IllegalArgumentException(
                    "Телефон содержит недопустимые символы."
            );
        }

        long digitCount = phone.chars()
                .filter(Character::isDigit)
                .count();

        if (digitCount < 7 || digitCount > 15) {
            throw new IllegalArgumentException(
                    "Телефон должен содержать от 7 до 15 цифр."
            );
        }
    }
}
