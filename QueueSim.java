//Queue Simulator

import java.util.*;

class QueueSim {


    public static void main(String[] args) {
        //ask user to input total minutes to run
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the total minutes to run: ");
        int totalMinutes = input.nextInt();
        //blank line
        System.out.println();

        //run simulation for minutes input
        for (int i = 0; i < totalMinutes; i++) {
            //run simulation for each minute
            minuteSimulation();
        }

        //print output
        System.out.println("Total number of messages processed: " + emailQueue.size());
        System.out.println("Average arrival rate: " + emailQueue.size() / totalMinutes);
        System.out.println("Average number of messages sent per minute: " + emailQueue.size() / totalMinutes);
        //print arraylist
        System.out.println(emailQueue);
        }

    // create ArrayList to use as queue
    static ArrayList<Email> emailQueue = new ArrayList<>();

    public static void minuteSimulation() {
        //RNG for number of emails (0 to 30)Random rand = new Random();
        int numEmails = (int) (Math.random() * 30);

        //create email objects for each email generated
        for (int i = 0; i < numEmails; i++) {
            Email email = new Email();

            emailQueue.add(email);
        }

    }
}

class Email {
    //variables
    private int EmailID;

    //constructor
    public Email() {
    }

    //getters and setters
    public int getEmailID() {
        return EmailID;
    }

    //assign email ID from numEmail
    public void setEmailID(int numEmails) {
        for (int i = 0; i < numEmails; i++) {
            EmailID = i;
        }
    }
}
