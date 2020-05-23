package Application;

// Импорт классов

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;

// Основной класс
public class Main {
    public static void main(String[] args) {
        int command = -1;
        ArrayList<Leader> leaders_list = new ArrayList<Leader>();
        // Создание объекта класса Scanner
        Scanner input = new Scanner(System.in);
        main_menu();
        do {
            try {
                System.out.print("\nВведите команду: ");
                command = input.nextInt();
                switch (command) {
                    case 1:
                        leaders_list= add_leader(leaders_list);
                        break;
                    case 2:
                        leaders_list = auth(leaders_list);
                        break;
                    case 3:
                        processRequests(leaders_list);
                        break;
                    case 4:
                        main_menu();
                        break;
                    case 5:
                        Show_from_file(leaders_list);
                        break;
                    case 6:
                        System.out.print("Программа завершена.");
                        break;
                    case 7:
                        leaders_list = load(leaders_list);
                        show(leaders_list);
                        break;
                    default:
                        System.out.print("Неверно введена команда!\n");
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.print("Неверно введена команда!\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (command != 6);
    }

    // Метод выбора корректной страны и месяца встречи
    public static void processRequests(ArrayList<Leader> leaders) {
        if (leaders.size() <= 0) System.out.print("Список лидеров не может быть пустым.\n");
        else {
            // Создание общега списка результатов
            ArrayList<String> result_places = new ArrayList<String>();
            // Создание списка для первого запроса
            ArrayList<Request> first_request = new ArrayList<Request>();
            first_request = leaders.get(0).getListRequests();
            for (int index = 0; index < first_request.size(); ++index) {
                int count = 0;
                for (int index1 = 1; index1 < leaders.size(); ++index1) {
                    // Создание списка для текущего запроса
                    ArrayList<Request> cur_request = new ArrayList<Request>(leaders.get(index1).getListRequests());
                    for (int index2 = 0; index2 < cur_request.size(); ++index2) {
                        if (first_request.get(index).getCountry().equals(cur_request.get(index2).getCountry()) && first_request.get(index).getMonth().equals(cur_request.get(index2).getMonth())) {
                            count += 1;
                            break;
                        }
                    }
                }
                if (count == leaders.size() - 1) {
                    result_places.add(first_request.get(index).getCountry() + "-" + first_request.get(index).getMonth());
                }
            }
            if (result_places.size() == 0) System.out.print("Нет подходящего места встречи.\n");
            else
                System.out.print("Список возможных мест встречи:\n" + result_places.toString().replaceAll("^\\[|\\]$", "") + "\n");
        }
    }

    // Метод вывода главного меню программы
    public static void main_menu() {
        System.out.print("1 - Добавить нового пользователя.\n");
        System.out.print("2 - Войти в систему.\n");
        System.out.print("3 - Сформировать встречу.\n");
        System.out.print("4 - Повторный вызов меню.\n");
        System.out.print("5 - Вывод списка лидеров.\n");
        System.out.print("6 - Выйти из программы.\n");
        System.out.print("7 - Загрузиться из файла.\n");
    }

    // Метод вывода второго меню для лидера
    public static void show_second_menu() {
        System.out.print("1 - Добавить запись.\n");
        System.out.print("2 - Повторный вызов меню.\n");
        System.out.print("3 - Выход из системы.\n");
    }

    // Метод добавления нового лидера
    public static ArrayList<Leader> add_leader(ArrayList<Leader> leaders) throws IOException {
        String name, login, password;
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя нового пользователя: ");
        name = input.nextLine();
        System.out.print("Введите логин нового пользователя: ");
        login = input.nextLine();
        System.out.print("Введите пароль нового пользователя: ");
        password = input.nextLine();
        Leader lead = new Leader(name, login, password, new ArrayList<Request>());
        leaders.add(lead);
        FileWriter fileWriter = new FileWriter("data.txt", true);
        fileWriter.append(lead.getName()+ " "+ lead.getLogin() + " " + lead.getPassword() +
                System.getProperty("line.separator"));
        fileWriter.close();
        return leaders;
}

    public static void Show_from_file(ArrayList<Leader> leaders)
    {
        ObjectStream s = new ObjectStream();
        s.read_from_file(leaders);
    }

    // Метод авторизации пользователя
    public static ArrayList<Leader> auth(ArrayList<Leader> leaders) {
        // Создание объекта класса Scanner
        if (leaders.size() <= 0) System.out.print("Список лидеров не может быть пустым.\n");
        else {
            // Создание объекта класса Scanner
            Scanner input = new Scanner(System.in);
            Leader temp_user = new Leader("", "", "");
            if (leaders.size() != 0) {
                boolean flag = false;
                String login, password;
                while (!flag) {
                    System.out.print("Введите логин: ");
                    login = input.nextLine();
                    System.out.print("Введите пароль: ");
                    password = input.nextLine();
                    for (int index = 0; index < leaders.size(); index++) {
                        if (leaders.get(index).getLogin().equals(login) && leaders.get(index).getPassword().equals(password)) {
                            temp_user = new Leader(leaders.get(index).getName(), leaders.get(index).getLogin(), leaders.get(index).getPassword(), leaders.get(index).getListRequests());
                            temp_user = second_menu(temp_user);
                            leaders.set(index, temp_user);
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) System.out.print("\nНеправильный логин или пароль.\n");
                }
            }
        }
        return leaders;
    }

    // Метод вызова второго меню
    public static Leader second_menu(Leader leader) {
        System.out.print("\nВы успешно вошли!\n");
        int command = -1;
        // Создание объекта класса Scanner
        Scanner input = new Scanner(System.in);
        show_second_menu();
        do {
            try {
                System.out.print("\nВведите команду: ");
                command = input.nextInt();
                switch (command) {
                    case 1:
                        leader.addList();
                        break;
                    case 2:
                        show_second_menu();
                        break;
                    case 3:
                        System.out.print("Успешный выход из системы!\n");
                        break;
                    default:
                        System.out.print("Неверное введена команда!\n");
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.print("Неверно введена команда!\n");
            }
        } while (command != 3);
        return leader;
    }

    private static ArrayList<Leader> load(ArrayList<Leader> list) throws IOException {
        FileInputStream fstream = new FileInputStream("data.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            String[] array = strLine.split(" ");
            list.add(new Leader(array[0], array[1], array[2]));
        }
        return list;
    }

    private static void show(ArrayList<Leader> leaders){
        for (int index = 0; index < leaders.size(); index++) {
            System.out.println(leaders.get(index).getName());
        }
    }



//    private static void filingProject() throws IOException{
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.print("Введите название проекта: ");
//        String name = reader.readLine();
//        System.out.print("Введите сумму, необходимую для реализации: ");
//        FileWriter fileWriter = new FileWriter("projects.txt", true);
//        int sum = Integer.parseInt(reader.readLine());
//        int expertMark = (int) (0 + Math.random()*10);
//        projects.add(new Project(getCurrentUser(), name, sum, expertMark));
//        User user = getCurrentUser();
//        fileWriter.append(user.getName()+ " "+ user.getLogin() + " " + user.getPassword() +
//                " "+ name + " " + sum + " " + expertMark + System.getProperty("line.separator"));
//        fileWriter.close();
//        System.out.println("Заявка успешно подана");
//    }
}