public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        
        int N = 0;          
        if (args.length == 1) N = Integer.parseInt(args[0]);
        
        //StdOut.println(" ---> " + N);
        
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            //StdOut.println(" ::: " + item);
            rq.enqueue(item);
            
            
        }
        
        int i = 0;
        for (String s : rq) {
            StdOut.println(s);
            i++;
            if (i >= N) break;
            
        }
    }
}