package Application;

// Класс запрос
public class Request
{
    // Поля
    private String country;
    private String month;

    // Конструктор с параметрами
    public Request(String country, String month)
    {
        this.country = country;
        this.month = month;
    }

    // Метод получения стран
    public String getCountry() {
        return this.country;
    }

    // Метод получения месяцев
    public String getMonth() {
        return this.month;
    }
}