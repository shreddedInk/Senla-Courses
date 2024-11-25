import java.util.Scanner;

public class FlowerShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Bouquet bouquet = new Bouquet();

        System.out.println("Welcome to the flower shop!");
        boolean shopping = true;

        while (shopping) {
            System.out.println("\nChoose a flower to add to your bouquet:");
            System.out.println("1. Rose (" + new Rose().getPrice() + " RUB)");
            System.out.println("2. Tulip (" + new Tulip().getPrice() + " RUB)");
            System.out.println("3. Lily (" + new Lily().getPrice() + " RUB)");
            System.out.println("4. Orchid (" + new Orchid().getPrice() + " RUB)");
            System.out.println("5. Daisy (" + new Daisy().getPrice() + " RUB)");
            System.out.println("6. Sunflower (" + new Sunflower().getPrice() + " RUB)");
            System.out.println("7. Finish and show the bouquet");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bouquet.addFlower(new Rose());
                    System.out.println("Rose added to the bouquet.");
                    break;
                case 2:
                    bouquet.addFlower(new Tulip());
                    System.out.println("Tulip added to the bouquet.");
                    break;
                case 3:
                    bouquet.addFlower(new Lily());
                    System.out.println("Lily added to the bouquet.");
                    break;
                case 4:
                    bouquet.addFlower(new Orchid());
                    System.out.println("Orchid added to the bouquet.");
                    break;
                case 5:
                    bouquet.addFlower(new Daisy());
                    System.out.println("Daisy added to the bouquet.");
                    break;
                case 6:
                    bouquet.addFlower(new Sunflower());
                    System.out.println("Sunflower added to the bouquet.");
                    break;
                case 7:
                    shopping = false;
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }

        bouquet.displayBouquet();
        System.out.println("Total cost of the bouquet: " + bouquet.calculateTotalCost() + " RUB.");
        scanner.close();
    }
}
