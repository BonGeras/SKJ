package Prog2PL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import static Prog2PL.Util.*;

public class RequestHandler implements Runnable {

    private Socket clientSocket;



    /* Zadanie polega na komunikacji z użyciem protokołu TCP. Protokół komunikacji jest tekstowy: odebranie pojedyńczej
    odpowiedzi polega na wczytaniu jedej linii tekstu; analogicznie, wysłanie pojedyńczej odpowiedzi polega na wysłaniu
    jednej linii tekstu. W szczególności wszystkie liczby powinny być przesyłane w tekstowej reprezentacji.
    Twoją początkową flagą jest liczba 177938. Flagę tę należy wysłać do serwera aby zainicjować komunikację.
    Serwer TCP działa na adresie 172.21.48.141 i porcie 23155. Użyj protokołu TCP do komunikacji.
    1. Wyślij do serwera jedną linię w formacie adres:port, gdzie adres jest adresem IP Twojego serwera, a port jego
    numerem portu. Twój serwer powinien móc obsługiwać wielu kientów jednocześnie. Komunikacja z każdym z klientów
    zaczyna się od wczytania od klienta jednej lini tekstu i odesłania jej do klienta, a następnie wykonania poniższych
    zadań dla każdego klienta. Uwaga! nie wszyscy klienci muszą wykonać cały protokół, ale należy założyć,
    że przynajmniej jeden z nich go wykona oraz wszyscy, którzy go wykonają zwrócą tę samą finalną flagę.
    2. W 5 kolejnych liniach odbierz 5 liczb(y) naturalne(ych). Policz ich największy wspólny dzielnik i wynik odeślij.
    3. Wyślij numer portu z którego się komunikujesz.
    4. Wyślij największy wspólny dizelnik liczb otrzymanych przez Twoj serwer od wszystkich klientów w ich pierwszych komunikatach (tj., w pkt. 1 zadania).
    5. Odbierz napis. Usuń z niego wszystkie wystąpienia 4 i odeślij wynik.
    6. Wyślij sumę liczb otrzymanych przez Twoj serwer od wszystkich klientów w ich pierwszych komunikatach (tj., w pkt. 1 zadania).
    Odbierz finalną flagę i wpisz ją poniżej.*/

    public RequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            clientSocket.setSoTimeout(10000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (
                PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            // nawiązanie komunikacji z serwerem
            String firstInput = inFromClient.readLine();
            outToClient.println(firstInput);
            System.out.println("Pierwsza linia: " + firstInput);
            Server.addClientInput(Integer.parseInt(firstInput));


            // 2. Odbierz napis. Usuń z niego wszystkie wystąpienia 5 i odeślij wynik.
            String str = inFromClient.readLine();
            str = str.replaceAll("5", "");
            System.out.println(str);
            outToClient.println(str);


            // 3. W 4 kolejnych liniach odbierz 4 liczb(y) naturalne(ych). Policz ich największy wspólny dzielnik i wynik odeślij.
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                numbers.add(Integer.parseInt(inFromClient.readLine()));
            }
            long gcdResult = gcdFromList(numbers);
            outToClient.println(gcdResult);

            // 4. Wyślij największy wspólny dizelnik liczb otrzymanych przez Twoj serwer od wszystkich klientów w ich pierwszych komunikatach (tj., w pkt. 1 zadania).
            outToClient.println(Server.getGcd());

            // 5. Wyślij sumę liczb otrzymanych przez Twoj serwer od wszystkich klientów w ich pierwszych komunikatach (tj., w pkt. 1 zadania).
            //INNE
            for (int i = 0; i < 4; i++) {
                numbers.add(Integer.parseInt(inFromClient.readLine()));
            }

            // 6. Wyślij numer portu z którego się komunikujesz.
            outToClient.println(Server.port);

            // Odbierz finalną flagę i wpisz ją poniżej
            String finalFlag = inFromClient.readLine();
            System.out.println("final:" + finalFlag);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}