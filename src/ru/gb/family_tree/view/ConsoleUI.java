package ru.gb.family_tree.view;

import ru.gb.family_tree.model.human.Human;
import ru.gb.family_tree.presenter.Presenter;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleUI implements View {
    private Presenter presenter;
    private Scanner scanner;
    private boolean work;
    private MainMenu mainMenu;

    public ConsoleUI() {
        presenter = new Presenter(this);
        scanner = new Scanner(System.in);
        work = true;
        mainMenu = new MainMenu(this);
    }

    @Override
    public void start() {
        System.out.println("Добро пожаловать в информационную систему 'Генеалогическое дерево'");
        while (work) {
            System.out.println(mainMenu.menu());
            String choice = scanner.nextLine();
            // проверка на валидность (цифра в нужном диапазоне и не превышает к-во пунктов)
            int numChoice = Integer.parseInt(choice);
            mainMenu.execute(numChoice);
        }
    }

    public void finish() {
        System.out.println("пока!");
        work = false;
    }

    public void getTreeInfo() {
        presenter.getTreeInfo();
    }

    // TODO проработать вынесение в отдельный интерфейс(или класс?) общей информации для заполнения в методах addXXX
    public void addHuman() {
        System.out.println("Введите номер паспорта (6 знаков)");
        String numPassport = scanner.nextLine();
//        String numPassportString = scanner.nextLine();
//        if (numPassportString.length()==6) {String numPassport = numPassportString;}
//        else {System.out.println("неверно введены данные");}
        System.out.println("Введите фамилию");
        String surname = scanner.nextLine();
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        System.out.println("Введите пол (Male или Female)");
        Human.Gender gender = Human.Gender.valueOf(scanner.nextLine());
        System.out.println("Введите birthday (format: 1982-10-20)");
        String birthdayString = scanner.nextLine();
        LocalDate birthday = LocalDate.parse(birthdayString);
    // проверка возраста на валидность (что число что не <=0). Также написать Такой команды нет
        presenter.addHuman(numPassport, surname, name, gender, birthday);
    }

    public String searchHuman() {
        System.out.println("Введите для поиска (фамилию либо имя либо номер паспорта)");
        String value = scanner.nextLine();
        presenter.searchHuman(value);
        return value;
    }

    // TODO доработать логику ConsoleUI.addKid
    public void addKid() {
        System.out.println("Введите номер паспорта (6 знаков)");
        String numPassport = scanner.nextLine();
        System.out.println("Введите фамилию");
        String surname = scanner.nextLine();
        System.out.println("Введите имя ребенка");
        String name = scanner.nextLine();
        System.out.println("Введите пол (Male или Female)");
        Human.Gender gender = Human.Gender.valueOf(scanner.nextLine());
        System.out.println("Введите birthday (format: 1982-10-20)");
        String birthdayString = scanner.nextLine();
        LocalDate birthday = LocalDate.parse(birthdayString);
        // метод проверки возраста на валидность
        presenter.addKid(numPassport, surname, name, gender, birthday);
        getTreeInfo();
        System.out.println("Выберите родителя из списка (введите номер паспорта): \n");
        //
        // если номер паспорта введен >> добавить human?.addKid((Human) presenter.searchHuman(value));  ??????
//        String answer = presenter.searchHuman(value);
    }

    // TODO доработать логику ConsoleUI.addParent
    public void addParent() {
        System.out.println("Введите фамилию");
        String surname = scanner.nextLine();
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        System.out.println("Введите пол (Male или Female)");
        Human.Gender gender = Human.Gender.valueOf(scanner.nextLine());
        presenter.addParent(surname, name, gender);
        // логика как в addKid: вывод дерева/ указать параметр конкретного родителя/ добавить родителя /
    }

    // TODO доработать логику ConsoleUI.addPartner
    public void addPartner() {
        System.out.println("Введите фамилию");
        String surname = scanner.nextLine();
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        System.out.println("Введите пол (Male или Female)");
        Human.Gender gender = Human.Gender.valueOf(scanner.nextLine());
        presenter.addPartner(surname, name, gender);
        // логика как в addKid: вывод дерева/ указать параметр конкретного партнера/ добавить / вывести дерево
    }

    public void sortByName() {
        presenter.sortByName();
    }

    public void sortByAge() {
        presenter.sortByAge();
    }

    public void sortByBirthday() {
        presenter.sortByBirthday();
    }

    public void saveInFile() {
        presenter.saveInFile();
    }

    public void loadFromFile() {
        presenter.loadFromFile();
    }

    @Override
    public void answer(String text) {
        System.out.println(text);
    }
}