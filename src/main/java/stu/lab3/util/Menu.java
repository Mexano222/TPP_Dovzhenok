package stu.lab3.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import stu.lab3.Runner;

public class Menu {

    private String title = "";
    private List<String> options = new ArrayList<>();
    private List<Runnable> callbacks = new ArrayList<>();

    public Menu(String title) {
        this.title = title;
    }

    public Menu() {
    }

    public String getTitle() {
        return title;
    }

    public Menu setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<String> getOptions() {
        return options;
    }

    public Menu setOptions(List<String> options) {
        this.options = options;
        return this;
    }

    public List<Runnable> getCallbacks() {
        return callbacks;
    }

    public Menu addOption(String str, Runnable method) {
        getOptions().add(str);
        getCallbacks().add(method);
        return this;
    }

    public Menu addOption(String str) {
        getOptions().add(str);
        return this;
    }

    public Menu addExit() {
        addOption("Exit", () -> System.exit(0));
        return this;
    }

    public Menu addExit(Runnable method) {
        addOption("Exit", method);
        return this;
    }

    public Integer takeOneInput() {
        int option = 0;
        do {
            printOneMenu();
            try {
                option = Runner.sysIn.nextInt();
            } catch (InputMismatchException e) {
                Runner.sysIn.next();
            }
        } while ((option < 1 || option > getOptions().size()));
        if (!getCallbacks().isEmpty())
            getCallbacks().get(option - 1).run();
        return option - 1;
    }

    public Set<Integer> takeSelectInput() {
        addOption("Confirm");
        Set<Integer> selected = new HashSet<Integer>();
        int option = 0;
        do {
            printSelectMenu(selected);
            try {
                option = Runner.sysIn.nextInt();
            } catch (InputMismatchException e) {
                Runner.sysIn.next();
            }
            if (option > 0 && option < getOptions().size()) {
                if (selected.contains(option - 1))
                    selected.remove(option - 1);
                selected.add(option - 1);
            }
        } while (option != getOptions().size());
        return selected;
    }

    private void printOneMenu() {
        int separatorLen = Math.max(getOptions().stream().map(String::length).max(Integer::compareTo).get() + 3,
                getTitle().length());
        if (getTitle().length() > 0)
            System.out.println("\n" + getTitle());
        System.out.println("-".repeat(separatorLen));
        IntStream.range(0, getOptions().size()).mapToObj(i -> (i + 1) + ". " + getOptions().get(i))
                .forEach(System.out::println);
        System.out.println("-".repeat(separatorLen));
        System.out.println("Enter your choice:");
    }

    private void printSelectMenu(Set<Integer> selected) {
        int separatorLen = Math.max(getOptions().stream().map(String::length).max(Integer::compareTo).get() + 3,
                getTitle().length());
        if (getTitle().length() > 0)
            System.out.println("\n" + getTitle());
        System.out.println("-".repeat(separatorLen));

        IntStream.range(0, getOptions().size())
                .mapToObj(i -> (i + 1) + ". " + (selected.contains(i) ? "*" : "") + getOptions().get(i))
                .forEach(System.out::println);
        System.out.println("-".repeat(separatorLen));
        System.out.println("Enter your choice:");
    }

}