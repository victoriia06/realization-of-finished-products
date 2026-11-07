package ru.lab.finishedgoods;

public class CustomerMapper {

    public static ShortCustomer toShortCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException(
                    "Клиент не может быть null"
            );
        }

        return new ShortCustomer(
                customer.getCustomerId(),
                customer.getName(),
                customer.getPhone()
        );
    }
}