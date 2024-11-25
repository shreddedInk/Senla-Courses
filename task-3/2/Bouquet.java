

import java.util.ArrayList;
import java.util.List;

public class Bouquet {
    private List<Flower> flowers;

    public Bouquet() {
        flowers = new ArrayList<>();
    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public double calculateTotalCost() {
        double total = 0;
        for (Flower flower : flowers) {
            total += flower.getPrice();
        }
        return total;
    }

    public void displayBouquet() {
        System.out.println("\nYour bouquet consists of:");
        for (Flower flower : flowers) {
            System.out.println("- " + flower);
        }
    }
}
