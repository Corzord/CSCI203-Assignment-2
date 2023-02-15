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

        System.out.printf
                ("Total number of messages processed: %28.2f " +
                "\nAverage number of messages sent per minute: %20.2f\n" +
                "Average number of messages in queue per minute: %16.2f\n" +
                "Number of messages sent on 1st attempt: %24.0f\n" +
                "Number of messages sent on 2nd attempt: %24.0f\n" +
                "Number of messages sent on 3rd attempt: %24.0f\n" +
                "Number of messages sent on 4th attempt: %24.0f\n" +
                "Number of messages sent on 5th attempt: %24.0f\n" +
                "Number of messages sent on 6th attempt: %24.0f\n" +
                "Number of messages sent on 7th attempt: %24.0f\n" +
                "Number of messages sent on 8th attempt: %24.0f\n" +
                "Number of messages sent on 9th attempt: %24.0f\n" +
                "Number of messages sent on 10th attempt: %23.0f\n"+
                "Average number of times messages had to be requeued: %11.2f\n"
                , totalMessagesProcessed, (totalMessagesSent/15), (totalMessagesLeft/15), attempts[1], attempts[2], attempts[3], attempts[4], attempts[5], attempts[6], attempts[7], attempts[8], attempts[9], attempts[10], (totalMessagesRequeued/15));

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

    //print method
    public void print(){
        for (int i = 0; i < queueArray.length; i++) {
            System.out.println(queueArray[i]);
        }
    }

    //method to return the size of the queue
    public int size(){
        return nItems;
    }

    //method to return the front of the queue
    public int front(){
        return queueArray[front];
    }

    //method to check if the queue is empty
    public boolean isEmpty(){
        return (nItems == 0);
    }

    //method to check if the queue is full
    public boolean isFull(){
        return (nItems == maxSize);
    }
}

