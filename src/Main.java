//src/Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Decks deck = new Decks();

        System.out.print("Nome do primeiro jogador: ");
        String name1 = scanner.nextLine();

        System.out.print("Nome do segundo jogador: ");
        String name2= scanner.nextLine();


        scanner.close();


        };

    }

