package machine;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CoffeeMachineOperations machine = new CoffeeMachineOperations();

        while (!machine.getState().equals(CoffeeMachineOperations.State.EXIT)) {
            machine.awaitingInput(input);
        }
    }
}
