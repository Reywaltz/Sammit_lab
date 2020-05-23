package Application;

// Импорт библиотек
import java.util.ArrayList;
import java.util.Scanner;

// Основной класс
public class Leader extends User
{
    // Поля
    private ArrayList<Request> list;

    // Конструктор с параметрами
    public Leader(String name, String login, String password, ArrayList<Request> lst)
    {
        super(name, login, password);
        this.list = lst;
    }

    // Конструктор с параметрами
    public Leader(String name, String login, String password)
    {
        super(name, login, password);
        this.list = new ArrayList<Request>();
    }

    // Метод получения заявок пользователя
    public ArrayList<Request> getListRequests()
    {
        ArrayList<Request> reqList = new ArrayList<Request>();
        for (int index = 0; index < list.size(); index++) {
            Request this_request = new Request(list.get(index).getCountry(), list.get(index).getMonth());
            reqList.add(this_request);
        }
        return reqList;
    }

    // Метод добавления заявки пользователя
    public void addList()
    {
        // Создание объекта класса Scanner
        Scanner input = new Scanner(System.in);
        System.out.print("Введите страну: ");
        String name = input.nextLine();
        System.out.print("Введите месяц: ");
        String mnt = input.nextLine();
        Request reqq = new Request(name, mnt);
        list.add(reqq);
    }
}