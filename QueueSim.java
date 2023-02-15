import java.util.*;

//main class
public class QueueSim {

    //variables to store the output data
    static double totalMessagesProcessed;
    static double totalMessagesSent;
    static double totalMessagesLeft;
    static double totalMessagesRequeued;
    static double[] attempts = new double[30];

    public static void main(String[] args) {
        Random rand = new Random(); //random number generator
        Queue queue = new Queue(5000); //queue object
        int RandomIndex = 0;

        //1. Simulate the arrival of emails
        //run for 15 minutes
        for (int runtime = 1; runtime <= 15; runtime++) {
            int currentMessagesSent = 0;
            //a. RNG 0 to 60
            int emailArrival = rand.nextInt(61);
            //add emails to total
            totalMessagesProcessed += emailArrival;
            //b. push to queue
            for (int i = 0; i < emailArrival; i++) {
                queue.enqueue(i);
            }
            //2. Simulate the processing of emails
            //number of unsent emails is 25% of the queue size
            int numberOfUnsentEmails = (queue.size() / 4);
            //variable to store original queue size
            int qSize = queue.size();
            //select random indexes for the number of unsent emails

            //new arraylist to store random index values
            ArrayList<Integer> randomIndexArray = new ArrayList<Integer>();
            for (int i = 1; i <= numberOfUnsentEmails; i++) {
                RandomIndex = rand.nextInt(numberOfUnsentEmails);
                //store random index values in arraylist if not reapeated
                if (!randomIndexArray.contains(RandomIndex)) {
                    randomIndexArray.add(RandomIndex);
                    //else generate new random index
                } else {
                    RandomIndex = rand.nextInt(numberOfUnsentEmails);
                    randomIndexArray.add(RandomIndex);
                }
        }
            //in the queue, if the index is not in the random index array, send the email
            for (int i = 0; i < qSize; i++) {
                if (!randomIndexArray.contains(i)) {
                    queue.dequeue();
                    totalMessagesSent++;
                    currentMessagesSent++;
                }
                //else requeue the email
                else {
                    queue.enqueue(queue.front());
                    queue.dequeue();
                    totalMessagesRequeued++;
                }
            }
            totalMessagesLeft += queue.size();
            attempts[runtime] = (qSize-currentMessagesSent);
        }

        System.out.printf ("Total number of messages processed: %28.0f\n" ,totalMessagesProcessed);
        System.out.printf ("Average Arrival Rate: %42.2f\n", (totalMessagesProcessed/15));
        System.out.printf ("Average number of messages sent per minute: %20.2f\n", (totalMessagesSent/15));
        System.out.printf ("Average number of messages in queue per minute: %16.2f\n", (totalMessagesLeft/15));
        System.out.printf ("Number of messages sent on 1st attempt: %24.0f\n", attempts[1]);
        System.out.printf ("Number of messages sent on 2nd attempt: %24.0f\n", attempts[2]);
        System.out.printf ("Number of messages sent on 3rd attempt: %24.0f\n", attempts[3]);
        System.out.printf ("Number of messages sent on 4th attempt: %24.0f\n", attempts[4]);
        System.out.printf ("Number of messages sent on 5th attempt: %24.0f\n", attempts[5]);
        System.out.printf ("Average number of times messages had to be requeued: %11.2f\n", (totalMessagesRequeued/totalMessagesProcessed));
    }
}


class Queue{
    private int maxSize;
    private int[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    //constructor
    public Queue(int qSize){
        maxSize = qSize;
        queueArray = new int[maxSize];
        front = 0;
        rear = 0;
        nItems = 0;
    }

    //enqueue method
    public void enqueue(int item){
        queueArray[++rear] = item;
        nItems++;
    }

    //dequeue method
    public int dequeue(){
        nItems--;
        return queueArray[++front];
    }

    //method to return the size of the queue
    public int size(){
        return nItems;
    }

    //method to return the front of the queue
    public int front(){
        return queueArray[front];
    }
}

