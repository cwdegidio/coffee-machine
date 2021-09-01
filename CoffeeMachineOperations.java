package machine;

import java.util.Scanner;

public class CoffeeMachineOperations {
    private int money;
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    State state;
    Coffee coffee = Coffee.NONE;

    public enum State {
        AWAITING_INPUT,
        BUY,
        FILL,
        TAKE,
        REMAINING,
        EXIT
    }

    public enum Coffee {
        NONE,
        ESPRESSO,
        LATTE,
        CAPPUCCINO
    }

    public CoffeeMachineOperations() {
        // Starting resources
        this.money = 550;
        this.water = 400;
        this.milk = 540;
        this.coffeeBeans = 120;
        this.cups = 9;
        this.state = State.AWAITING_INPUT;
    }

    public void nextOperation(State state, Scanner input) {
        switch (state) {
            case BUY:
                buyCoffee(input);
                break;
            case FILL:
                fillMachine(input);
                break;
            case TAKE:
                takeMoney();
                break;
            case REMAINING:
                displayRemaining();
                break;
            case EXIT:
                this.setState(State.EXIT);
                break;
            default:
                break;
        }

    }

    public void buyCoffee(Scanner input) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");

        switch (input.next()) {
            case "1":
                setCoffee(Coffee.ESPRESSO);
                break;
            case "2":
                setCoffee(Coffee.LATTE);
                break;
            case "3":
                setCoffee(Coffee.CAPPUCCINO);
                break;
            case "back":
                awaitingInput(input);
                break;
            default:
                break;
        }

        makeCoffee();
    }

    public void makeCoffee() {
        // [water, milk, coffee beans, cost]
        int[] espresso = {250, 0, 16, 4};
        int[] latte = {350, 75, 20, 7};
        int[] cappuccino = {200, 100, 12, 6};
        int[] coffeeValues = new int[4];
        String lowResource;

        switch (getCoffee()) {
            case ESPRESSO:
                coffeeValues = espresso;
                break;
            case LATTE:
                coffeeValues = latte;
                break;
            case CAPPUCCINO:
                coffeeValues = cappuccino;
                break;
            default:
                break;
        }

        if (water < coffeeValues[0]) {
            lowResource = "water";
        } else if (milk < coffeeValues[1]) {
            lowResource = "milk";
        } else if (coffeeBeans < coffeeValues[2]) {
            lowResource = "coffee beans";
        } else if(cups < 1) {
            lowResource = "disposable cups";
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= coffeeValues[0];
            milk -= coffeeValues[1];
            coffeeBeans -= coffeeValues[2];
            money += coffeeValues[3];
            cups--;
            return;
        }

        StringBuilder output = new StringBuilder("Sorry, not enough ");
        System.out.println(output.append(lowResource).append("!"));
    }

    public void awaitingInput(Scanner input) {
        setState(State.AWAITING_INPUT);
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        switch (input.next()) {
            case "buy":
                this.setState(State.BUY);
                break;
            case "fill":
                this.setState(State.FILL);
                break;
            case "take":
                this.setState(State.TAKE);
                break;
            case "remaining":
                this.setState(State.REMAINING);
                break;
            case "exit":
                this.setState(State.EXIT);
                break;
        }
        nextOperation(getState(), input);
    }

    public void fillMachine(Scanner input) {
        System.out.println("\nWrite how many ml of water you want to add:");
        setWater(getWater() + input.nextInt());
        System.out.println("Write how many ml of milk you want to add:");
        setMilk(getMilk() + input.nextInt());
        System.out.println("Write how many grams of coffee beans you want to add:");
        setCoffeeBeans(getCoffeeBeans() + input.nextInt());
        System.out.println("Write how many disposable cups of coffee you want to add:");
        setCups(getCups() + input.nextInt());
    }

    public void takeMoney() {
        StringBuilder output = new StringBuilder("\nI gave you $");
        System.out.println(output.append(getMoney()).append("\n"));
        setMoney(0);
    }

    public void displayRemaining() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money\n");
    }

    public int getMoney() {
        return this.money;
    }

    public int getWater() {
        return this.water;
    }

    public int getMilk() {
        return this.milk;
    }

    public int getCoffeeBeans() {
        return this.coffeeBeans;
    }

    public int getCups() {
        return this.cups;
    }

    public State getState() {
        return this.state;
    }

    public Coffee getCoffee() {
        return this.coffee;
    }

    public void setMoney(int amount) {
        this.money = amount;
    }

    public void setWater(int amount) {
        this.water = amount;
    }

    public void setMilk(int amount) {
        this.milk = amount;
    }

    public void setCoffeeBeans(int amount) {
        this.coffeeBeans = amount;
    }

    public void setCups(int amount) {
        this.cups = amount;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }
}
