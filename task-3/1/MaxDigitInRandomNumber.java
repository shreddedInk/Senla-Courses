public class MaxDigitInRandomNumber {
    public static void main(String[] args) {
        final int MAX_NUMBER = 1_000_000;

        int number = new java.util.Random().nextInt(MAX_NUMBER) + 1;


        int maxDigit = 0;
        int temp = number;
        while (temp > 0) {
            int digit = temp % 10;
            if (digit > maxDigit) {
                maxDigit = digit;
            }
            temp /= 10;
        }

        System.out.println("Random number: " + number);
        System.out.println("maxDigit: " + maxDigit);
    }
}