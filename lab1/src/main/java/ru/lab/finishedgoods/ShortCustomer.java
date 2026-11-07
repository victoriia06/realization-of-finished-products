package ru.lab.finishedgoods;

public class ShortCustomer implements CustomerView {

    private final long customerId;
    private final String name;
    private final String phone;

    public ShortCustomer(long customerId, String name, String phone) {
        Customer.validateCustomerId(customerId);
        Customer.validateName(name);
        Customer.validatePhone(phone);

        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String toShortString() {
        return "Клиент: " + name + " (ID: " + customerId + ")\n"
                + "Телефон: " + phone;
    }

    @Override
    public String toString() {
        return toShortString();
    }
}
