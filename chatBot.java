package ChatBotjava;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Scanner;

public class chatBot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm your chatbot. How can I assist y?");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase();

            if (userInput.contains("hi") || userInput.contains("hello")) {
                System.out.println("Bot: hello! How can I help y?");
            } else if (userInput.contains("how are you")) {
                System.out.println("Bot: I'm just a bot, but I'm functioning perfectly! How about you?");
            } else if (userInput.contains("your name")) {
                System.out.println("Bot: I'm a chatbot. You can call me ChatBot!");
            } else if (userInput.contains("time")) {
                LocalTime currentTime = LocalTime.now();
                System.out.println("Bot: The current time is " + currentTime + ".");
            } else if (userInput.contains("date")) {
                LocalDate currentDate = LocalDate.now();
                System.out.println("Bot: Today's date is " + currentDate + ".");
            } else if (userInput.contains("weather")) {
                System.out.println("Bot: I'm sorry, I can't check the weather right now. Maybe try a weather app?");
            } else if (userInput.contains("bye") || userInput.contains("goodbye")) {
                System.out.println("Bot: Goodbye! Have a great day!");
                break;
            } else {
                System.out.println("Bot: I'm not sure how to respond to that. try asking something else?");
            }
        }

        scanner.close();
    }
}



