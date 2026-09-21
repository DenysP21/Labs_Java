import controller.TramController;
import model.CardRegistry;
import model.Turnstile;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Turnstile turnstile = new Turnstile();
        CardRegistry registry = new CardRegistry();
        ConsoleView view = new ConsoleView();

        TramController controller = new TramController(turnstile, registry, view);
        controller.start();
    }
}