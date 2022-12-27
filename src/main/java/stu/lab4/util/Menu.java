package stu.lab4.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Menu {

    private String title = "";
    private List<String> options = new ArrayList<>();
    private List<Runnable> callbacks = new ArrayList<>();

    public Menu(String title) {
        this.title = title;
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

    public Menu addOption(String str, Runnable method) {
        getOptions().add(str);
        getCallbacks().add(method);
        return this;
    }

    public Menu addOption(String str) {
        getOptions().add(str);
        return this;
    }

    public List<Runnable> getCallbacks() {
        return callbacks;
    }

    public void setCallbacks(List<Runnable> callbacks) {
        this.callbacks = callbacks;
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
        Scanner cs = new Scanner(System.in);
        int option = 0;
        do {
            printOneMenu();
            try {
                option = cs.nextInt();
            } catch (InputMismatchException e) {
                cs.next();
            }
        } while ((option < 1 || option > getOptions().size()));
        if (!getCallbacks().isEmpty()) {
            getCallbacks().get(option - 1).run();
        }
        return option - 1;
    }

    public Set<Integer> takeSelectInput() {
        Scanner cs = new Scanner(System.in);
        addOption("Confirm");
        Set<Integer> selected = new HashSet<>();
        int option = 0;
        do {
            printSelectMenu(selected);
            try {
                option = cs.nextInt();
            } catch (InputMismatchException e) {
                cs.next();
            }
            if (option > 0 && option < getOptions().size()) {
                if (selected.contains(option - 1)) {
                    selected.remove(option - 1);
                } else {
                    selected.add(option - 1);
                }
            }
        } while (option != getOptions().size());
        return selected;
    }

    public String takeSimpleInput() {
        Scanner cs = new Scanner(System.in);
        System.out.println("\n" + getTitle());
        return cs.nextLine();
    }

    private void printOneMenu() {
        int separatorLen = Math.max(getOptions().stream().map(String::length).max(Integer::compareTo).get() + 3,
                getTitle().length());
        if (getTitle().length() > 0) {
            System.out.println("\n" + getTitle());
        }
        System.out.println("-".repeat(separatorLen));
        IntStream.range(0, getOptions().size()).mapToObj(i -> (i + 1) + ". " + getOptions().get(i))
                .forEach(System.out::println);
        System.out.println("-".repeat(separatorLen));
        System.out.println("Enter your choice:");
    }

    private void printSelectMenu(Set<Integer> selected) {
        int separatorLen = Math.max(getOptions().stream().map(String::length).max(Integer::compareTo).get() + 3,
                getTitle().length());
        if (getTitle().length() > 0) {
            System.out.println("\n" + getTitle());
        }
        System.out.println("-".repeat(separatorLen));

        IntStream.range(0, getOptions().size())
                .mapToObj(i -> (i + 1) + ". " + (selected.contains(i) ? "=>" : "") + getOptions().get(i))
                .forEach(System.out::println);
        System.out.println("-".repeat(separatorLen));
        System.out.println("Enter your choice:");
    }

}
