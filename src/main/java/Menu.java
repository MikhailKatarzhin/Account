import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    @NotNull private final Map<Integer, Pair<String, Runnable>> menu;
    @NotNull private final Scanner scanner = new Scanner(System.in);

    public Menu(){
        this.menu = new HashMap<>();
    }

    public Menu withExitOnZero(){
        return this.withMenuItem(0,"Exit", ()->{Runtime.getRuntime().exit(0);});
    }

    public Menu withMenuItem(int id,
                             @NotNull final String title,
                             @NotNull final Runnable menuAction)
    {
        menu.put(id, new Pair<>(title, menuAction));
        return this;
    }

    public void runInfinityLoop(){
        while (true) {
            printMenu();
            try {
                int menuItemId = scanner.nextInt();
                this.menu.get(menuItemId).getSecond().run();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Illegal select");
                e.printStackTrace();
            }
            System.out.println();
        }
    }

    private void printMenu(){
        for(Map.Entry<Integer, Pair<String, Runnable>> menu: this.menu.entrySet()){
            System.out.printf("%d: %s\n", menu.getKey(), menu.getValue().getFirst());
        }
    }
}
