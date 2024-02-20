// I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.

// Any code taken from other sources is referenced within my code solution.
// https://www.programiz.com/java-programming/bufferedreader
// https://www.w3schools.com/java/java_arrays.asp

// IIT Student ID: 20220260
// UoW ID: w1953182   / 19531829
// Date:  20.3.2023

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Theatre {
    public static void main(String[] args) {
        File temp = new File("Theatre.txt"); // To check if the data exists from a previous run
        if (temp.exists())
            System.out.println("\nATTENTION! Saved progress from a previous run has been found!" +
                    " The progress can be reloaded by entering '6' from the menu");

        System.out.println("Welcome to the New Theater");
        int[] row1 = new int[12];
        int[] row2 = new int[16];
        int[] row3 = new int[20];
        int[][] rows = {row1, row2, row3};
        // Task 12 add a arraylist
        ArrayList<Ticket> tickets = new ArrayList<>();
        // To Empty all seats
        for (int[] row : rows) {
            Arrays.fill(row, 0);
        }
        boolean loop = true;
        while (loop) {
            System.out.println("""
                    -----------------------------------------------
                    Please Select an option:
                    1) Buy a ticket
                    2) Print seating area
                    3) Cancel ticket
                    4) List available seats
                    5) Save to file
                    6) Load from file
                    7) Print ticket information and total price
                    8) Sort tickets by price
                        0) Quit
                        
                    -----------------------------------------------
                    """);
            int options = integer_Validation("Enter option: ", "option", 0, 8);
            switch (options) {
                case 1 -> buy_ticket(rows, tickets);
                case 2 -> print_seating_area(rows);
                case 3 -> cancel_ticket(rows, tickets);
                case 4 -> show_available(rows);
                case 5 -> save(rows);
                case 6 -> load(rows);
                case 7 -> show_tickets_info(tickets);
                case 8 -> sort_tickets(tickets);
                case 0 -> loop = false;
                default -> System.out.println("Invalid option. Please try again.");


            }
        }
    }

    public static void buy_ticket(int[][] rows, ArrayList<Ticket> ticketsList) {
        Ticket ticket = new Ticket();
        System.out.println("\n------------Buy a ticket------------\n");
        if (all_row_full(rows)) {
            System.out.println("All rows are full. Please try again later. ");
            return;
        }
        int row = integer_Validation("Enter row number: ", "Row", 1, 3);
        ticket.setRow(row);
        while (is_row_full(rows, row - 1)) {
            System.out.println("Row" + row + "is full. Please select another row.");
            row = integer_Validation("Enter row number: ", "Row", 1, 3);
            ticket.setRow(row);
        }
        int row_size = rows[row - 1].length;
        int seat = integer_Validation("Enter seat number: ", "Seat", 1, row_size);
        if (rows[row - 1][seat - 1] == 0) {
            System.out.println("Ticket for row " + row + " seat " + seat + " has been purchased");
            rows[row - 1][seat - 1] = 1;
            ticket.setSeat(seat);

            String name = string_input("Enter your first name: ");

            String surname = string_input("Enter your surname: ");

            String email = string_input("Enter your email: ");

            //Ask for to select price of the ticket. Row 1 = 10, Row 2 = 20, Row 3 = 30 using a switch statement
            switch (row) {
                case 1 -> ticket.setPrice(10.0);
                case 2 -> ticket.setPrice(20.0);
                case 3 -> ticket.setPrice(30.0);
            }

            Person person = new Person(name, surname, email);

            ticket.setPerson(person);

            ticketsList.add(ticket);
        } else {
            System.out.println("Seat is already occupied! Please check the free seats and try again!. ");
        }
    }

    public static void print_seating_area(int[][] rows) {
        int maxRowLength = 0;
        for (int[] row : rows) {
            maxRowLength = Math.max(maxRowLength, row.length);
        }
        System.out.println("""
                      
                     ***********
                     *  STAGE  *
                     ***********
                """);
        for (int i = 0; i < rows.length; i++) {
            int lining = (maxRowLength - rows[i].length) / 2;
            for (int j = 0; j < lining; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < rows[i].length; j++) {
                if (rows[i][j] == 0) {
                    System.out.print("0");
                } else {
                    System.out.print("X");
                }
                // To Get the middle walking space
                if (i == 0 && j == 5) {
                    System.out.print(" ");
                } else if (i == 1 && j == 7) {
                    System.out.print(" ");
                } else if (i == 2 && j == 9) {
                    System.out.print(" ");
                }

            }
            for (int j = 0; j < lining; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }


    }

    public static void cancel_ticket(int[][] rows, ArrayList<Ticket> ticketsList) {
        System.out.println("\n------------Cancel A Ticket------------\n");
        int row = integer_Validation("Enter row number: ", "Row", 1, 3);
        int row_size = rows[row - 1].length;
        int seat = integer_Validation("Enter seat number: ", "Row", 1, row_size);

        if (rows[row - 1][seat - 1] == 1) {
            System.out.println("Ticket for row " + row + " seat " + seat + " has been canceled.");
            rows[row - 1][seat - 1] = 0;
        } else {
            System.out.println("Seat is already free! Please check the purchased tickets and try again!.");
        }

        for (int i = 0; i < ticketsList.size(); i++) {
            Ticket ticket = ticketsList.get(i);
            if (ticket.getRow() == row && ticket.getSeat() == seat) {
                ticketsList.remove(i);
                break;
            }
        }
    }

    public static void show_available(int[][] rows) {
        System.out.println("\n------------AVAILABLE SEATS------------");
        for (int i = 0; i < rows.length; i++) {
            // Used StingBuilder class to convert into an integer
            StringBuilder string = new StringBuilder();
            string.append("Seats available in row " + (i + 1) + ": ");
            for (int j = 0; j < rows[i].length; j++) {
                if (rows[i][j] == 0) {
                    string.append(j + 1 + ", ");
                }
            }
            String available_seats = string.substring(0, string.length() - 2);
            System.out.println(available_seats + ".\n");
        }
    }

    public static void save(int[][] rows) {
        try {
            FileWriter fileWriter = new FileWriter("Theatre.txt");
            for (int[] row : rows) {
                for (int seat : row) {
                    fileWriter.write(seat + " ");
                }
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
            System.out.println("Data successfully saved to file!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void load(int[][] rows) {
        try {
            FileReader fileReader = new FileReader("Theatre.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int rowIndex = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] seats = line.split(" ");
                for (int i = 0; i < seats.length; i++) {
                    rows[rowIndex][i] = Integer.parseInt(seats[i]);
                }
                rowIndex++;
            }
            bufferedReader.close();
            System.out.println("Data loaded successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // To check Row Seats are full.
    public static boolean is_row_full(int[][] rows, int row) {
        for (int seat : rows[row]) {
            if (seat == 0) {
                return false;
            }
        }
        return true;
    }

    // To check all rows are full.
    public static boolean all_row_full(int[][] rows) {
        boolean allRowsFull = true;
        for (int i = 0; i < rows.length; i++) {
            if (!is_row_full(rows, i)) {
                allRowsFull = false;
                break;
            }
        }
        return allRowsFull;
    }

    //To Input number and check the range.
    public static int integer_Validation(String displayMessage, String errorMessage, int min, int max) {
        Scanner input = new Scanner(System.in);
        int number = 0;
        boolean number_Validate = true;
        while (number_Validate) {
            try {
                System.out.print(displayMessage);
                number = Integer.parseInt(input.nextLine());
                if (number >= min && number <= max) {
                    number_Validate = false;
                } else {
                    System.out.println(errorMessage + " number has to be between " + min + "-" + max + "\n");
                }
            } catch (NumberFormatException e1) {
                System.out.println("Please enter an Integer Value \n");
            }
        }
        return number;
    }

    // Scanner Input for the  string used in name, surname and email.
    public static String string_input(String displayMessage) {
        Scanner input = new Scanner(System.in);
        System.out.print(displayMessage);
        return input.nextLine();
    }

    public static void show_tickets_info(ArrayList<Ticket> tickets) {
        double totalPrice = 0;
        for (Ticket ticket : tickets) {
            ticket.print();
            totalPrice += ticket.getPrice();
        }
        System.out.println("Total price: £" + totalPrice);
    }

    public static void sort_tickets(ArrayList<Ticket> tickets) {
        ArrayList<Ticket> sortedTickets = new ArrayList<>(tickets);

        int n = sortedTickets.size();
        Ticket temp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedTickets.get(j).getPrice() > sortedTickets.get(j + 1).getPrice()) {
                    // swap tickets
                    temp = sortedTickets.get(j);
                    sortedTickets.set(j, sortedTickets.get(j + 1));
                    sortedTickets.set(j + 1, temp);
                }
            }
        }

        for (Ticket ticket : sortedTickets) {
            ticket.print();
        }
    }
}

