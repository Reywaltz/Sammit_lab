package Application;

// Класс пользователя
public class User implements java.io.Serializable{
    // Поля
    private String name, login, password;

    // Конструктор с параметрами
    public User(String name, String login, String password)
    {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    // Метод получения имени
    public String getName()
    {
        return this.name;
    }

    // Метод получения логина
    public String getLogin()
    {
        return this.login;
    }

    // Метод получения пароля
    public String getPassword()
    {
        return this.password;
    }
}