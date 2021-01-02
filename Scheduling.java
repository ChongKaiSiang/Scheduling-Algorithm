import java.util.*;

public class Scheduling
{

    /**
     * To enter the information for Preemptive SJF calculation
     */
    public void PreSJF(){
        int n;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println ("Enter no of process(3 - 10):");
            n = sc.nextInt();
            if (n<3 || n>10){
                System.out.println ("Please enter no between 3 - 10 only.");
            }
        }while (n<3 || n>10);
        
        int pid[] = new int[n];
        int arrival[] = new int[n]; 
        int burst[] = new int[n];
        int burst2[] = new int[n];
        int finish[] = new int[n]; 
        int turnaround[] = new int[n]; 
        int waiting[] = new int[n]; 
        int flag[] = new int[n];  // flag will check process is completed or not
        int current=0, totalprocess=0;
        
        for(int i=0;i<n;i++){
            
            do{
                System.out.println ("Enter P" + (i) + " arrival time:");
                arrival[i] = sc.nextInt();
                if (arrival[i] >9){
                    System.out.println ("\nPlease enter number below 10.");
                }
            }while(arrival[i] >9);
            
            do{
                System.out.println ("Enter P" + (i) + " burst time:");
                burst[i] = sc.nextInt();
                if (burst[i] >9){
                    System.out.println ("\nPlease enter number below 10.");
                }
            }while(burst[i] > 9);
            
            burst2[i]=burst[i];
            pid[i] = i;
            flag[i] = 0;
        }
        
        System.out.println();
        System.out.println ("Preemptive SJF");
        System.out.println ("====================================================================");
        System.out.println();
        System.out.println("Gantt chart");
        System.out.println();
        sc.close();
        
        CalculatePreSJF(n,pid,arrival,burst,burst2,
        finish,turnaround,waiting,flag,current,totalprocess);
    }
    
    /**
     * To enter the information for Non-Preemptive SJF calculation
     */
    public void NonPreSJF(){
        
        Scanner input = new Scanner(System.in);
        int n;
        do{
            System.out.println ("Please enter no of process(3 - 10):");
            n = input.nextInt();
            if (n<3 || n>10){
                System.out.println ("Please enter no between 3 - 10 only.");
            }
        }while (n<3 || n>10);

        int pid[] = new int[n];
        int arrt[] = new int[n]; // Arrival time
        int burt[] = new int[n]; // Burst time
        int comt[] = new int[n]; // Complete time
        int tat[] = new int[n]; // Turnaround time
        int wait[] = new int[n];  //Waiting time
        int check[] = new int[n];  // Check process 
        int st=0, tot=0;
        
        
        for(int i=0;i<n;i++)
        {
            System.out.println ("Please enter P" + (i) + " arrival time:");
            arrt[i] = input.nextInt();
            System.out.println ("Please enter P" + (i) + " burst time:");
            burt[i] = input.nextInt();
            pid[i] = i;
            check[i] = 0;
        }
        
        
        
        System.out.println ("\nNon Preemptive SJF");
        System.out.println ("====================================================================");
        System.out.println("\nGantt Chart");
        System.out.println();
        
        CalculateSJF(n,pid,arrt,burt,
        comt,tat,wait,check,st,tot);  
    }
    
    
    /**
     * To enter the information for Non-Preemptive Priority calculation
     */
    public void NonPrePrio(){
        
        int n;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println ("Enter no of process(3 - 10):");
            n = sc.nextInt();
            if (n<3 || n>10){
                System.out.println ("Please enter no between 3 - 10 only.");
            }
        }while (n<3 || n>10);
        
        int pid[] = new int[n];
        int prio[] = new int[n];
        int arrival[] = new int[n]; 
        int burst[] = new int[n];
        int finish[] = new int[n]; 
        int turnaround[] = new int[n]; 
        int waiting[] = new int[n]; 
        int flag[] = new int[n];  // flag will check process is completed or not
        int current=0, totalprocess=0;
        
        for(int i=0;i<n;i++){
            System.out.println ("Enter P" + (i) + " arrival time:");
            arrival[i] = sc.nextInt();
            System.out.println ("Enter P" + (i) + " burst time:");
            burst[i] = sc.nextInt();
            System.out.println ("Enter P" + (i) + " priority:" );
            prio[i] = sc.nextInt();
            pid[i] = i;
            flag[i] = 0;
        }
        
        System.out.println();
        System.out.println ("Non Preemptive Priority");
        System.out.println ("====================================================================");
        System.out.println();
        System.out.println("Gantt chart");
        System.out.println();
        sc.close();
        
        CalculatePrio(n,pid,prio,arrival,burst,
        finish,turnaround,waiting,flag,current,totalprocess);
        
    }
    
    /**
     * To perform the calculation of Preemptive SJF
     */
    public void CalculatePreSJF(int n,int pid[],int arrival[],
    int burst[],int burst2[],int finish[],int turnaround[],int waiting[],
    int flag[],int current,int totalprocess){
        
        boolean first = true;
        do{
            int c=n, minb=999;
            
            for(int i=0; i<n; i++){
                if((arrival[i]<=current) && (flag[i]==0) && (burst[i]< minb)){
                    minb=burst[i];
                    c=i;
                }
   
            }
            
            if(c==n){
                current++;
            }
            
            else{
                
               burst[c]--;
               if((totalprocess==0) && (arrival[c]==current) && (first==true)){
                   System.out.print(arrival[c]);
                   first=false;
               }
               current++; 
               
               System.out.print(" -- | P"+ pid[c]+" | -- "+current);
               
               if(burst[c]==0){
                   finish[c] = current;
                   flag[c]=1;
                   totalprocess++;
               }
            }
 
        }while(totalprocess!=n);
        
        for(int i=0;i<n;i++){
                turnaround[i]= finish[i]-arrival[i];
                waiting[i]= turnaround[i]-burst2[i];
        }
        resultPreSJF(n,pid,arrival,burst2,finish,
        turnaround,waiting);
    }
    
    /**
     * To perform the calculation of Non-Preemptive SJF
     */
    public void CalculateSJF(int n,int pid[],int arrt[],int burt[],
        int comt[],int tat[],int wait[],int check[],int st,int tot){
            
         do{
            boolean a = true;
            int c=n, min=999;
            
            if (tot == n)
                break;
            
            for (int i=0; i<n; i++)
            {
                if ((arrt[i] <= st) && (check[i] == 0) && (burt[i]<min))
                {
                    min=burt[i];
                    c=i;
                }
                
            }
            
            if (c == n) 
                st++;
            else
            {
                comt[c] = st + burt[c];
                st += burt[c];
                tat[c] = comt[c] - arrt[c];
                wait[c] = tat[c] - burt[c];
                check[c] = 1;
                tot++;
                if(tot == 1){
                    System.out.print(arrt[c]);
                }
                
                System.out.print(" -- | P"+pid[c]+" | -- "+comt[c]);
            }
        }while(true);    
        resultSJF(n,pid,arrt,burt,
        comt,tat,wait,check,st,tot);
    }
     
    /**
     * To perform the calculation of Non-Preemptive Priority
     */
    public void CalculatePrio(int n,int pid[],int prio[],int arrival[],
    int burst[],int finish[],int turnaround[],int waiting[],
    int flag[],int current,int totalprocess){

        do
        {
            int c=n, minp=999, minb= 999;
            for (int i=0; i<n; i++)
            {
                /*
                 * If i'th process arrival time <= current time and its flag=0 and prio<minp 
                 * Then check if i'th has the lower burst time
                 * That process will be executed first 
                 */ 
                if ((arrival[i] <= current) && (flag[i] == 0) && (prio[i]<=minp))
                {
                    minp = prio[i];
                    
                    if(burst[i]<minb){
                        minb = burst[i];
                        c=i;
                    }
                    
                    if (prio[i]<=minp && burst[i]>minb){
                        minb = burst[i];
                        c=i;
                    }
                }
                
            }
            
            /* If c==n means c value cannot updated because no process 
               arrive at that time, so increase current time.*/
            if (c==n) {
                current++;
            }
            
            else{
                
                finish[c]=current+burst[c];
                current+=burst[c];
                turnaround[c]=finish[c]-arrival[c];
                waiting[c]=turnaround[c]-burst[c];
                flag[c]=1;
                totalprocess++;
                //Print the beginning time of the process executed
                if(totalprocess == 1){
                    System.out.print(arrival[c]);   
                    

                }
                
                System.out.print(" -- | P"+pid[c]+" | -- " + finish[c]);
                
                
            }
            
        }while(totalprocess!=n);

        resultPrio(n,pid,arrival,prio,burst,finish,
        turnaround,waiting);
       
    }
    
    
    /**
     * To print the result table of Preemptive SJF
     */
    public void resultPreSJF(int n,int pid[],int arrival[],
    int burst2[],int finish[],int turnaround[],int waiting[]){
        
        float totalw=0, totalturn=0;
        System.out.println();
        System.out.println("\nP\tArrival\tBurst\tFinish\tTurnaround\tWaiting");
        for(int i=0;i<n;i++)
        {
            totalw+= waiting[i];
            totalturn+= turnaround[i];
            System.out.println(pid[i]+"\t"+arrival[i]+"\t"+burst2[i]+"\t"+finish[i]+"\t"+turnaround[i]+"\t\t"+waiting[i]);
        }
        System.out.println ("\nTotal turnaround time is "+ (totalturn));
        System.out.println ("Average turnaround time is "+ (float)(totalturn/n));
        System.out.println ("\nTotal waiting time is "+ (totalw));
        System.out.println ("Average waiting time is "+ (float)(totalw/n));
        System.out.println();
    }
    
    
    /**
     * To print the result table of Non-Preemptive SJF
     */
    public void resultSJF(int n,int pid[],int arrt[],int burt[],
        int comt[],int tat[],int wait[],int check[],int st,int tot){
        float totwt = 0, totta = 0;  
        
        System.out.println("\n\nProcess\tArrival\tBurst\tFinish\tTurnaround\tWaiting");
        for(int i=0;i<n;i++)
        {
            totwt += wait[i];
            totta += tat[i];
            System.out.println(pid[i]+"\t"+arrt[i]+"\t"+burt[i]+"\t"+comt[i]+"\t"+tat[i]+"\t\t"+wait[i]);
        }
        
        System.out.println ("\nTotal turnaround time is "+ totta);
        System.out.println ("Average turnaround time is "+ (float)(totta/n));
        System.out.println ("\nTotal waiting time is "+ totwt);
        System.out.println ("Average waiting time is "+ (float)(totwt/n));
        System.out.println();    
    }
    
    /**
     * To print the result table of Non-Preemptive Priority
     */
    public void resultPrio(int n,int pid[],int arrival[],int prio[],
    int burst[],int finish[],int turnaround[],int waiting[]){
        
        float totalw=0, totalturn=0;

        System.out.println();
        System.out.println("\nP\tArrival\tPriority  Burst\tFinish\tTurnaround\tWaiting");
        for(int i=0;i<n;i++)
        {
            totalw+= waiting[i];
            totalturn+= turnaround[i];
            System.out.println(pid[i]+"\t"+arrival[i]+"\t"+prio[i]+"\t  "+burst[i]+"\t"+finish[i]+"\t"+turnaround[i]+"\t\t"+waiting[i]);
        }
        System.out.println ("\nTotal turnaround time is "+ (totalturn));
        System.out.println ("Average turnaround time is "+ (float)(totalturn/n));
        System.out.println ("\nTotal waiting time is "+ (totalw));
        System.out.println ("Average waiting time is "+ (float)(totalw/n));
        System.out.println();
        
    }

    
    /**
     * For user to choose to perform which type of scheduling algorithmn 
     */
    public Scheduling(){
        int choice;
        Scanner sc = new Scanner(System.in); 
        do{
            System.out.println("====================================================================");
            System.out.println("Preemptive SJF [1]");
            System.out.println("Non preemptive SJF [2]");
            System.out.println("Non preemptive Priority [3]");
            System.out.println("Quit [4]");
            System.out.println("Choose the scheduling algorithm to be executed:");
            choice = sc.nextInt();
            if(choice == 1){
                PreSJF();
            }
            
            if(choice == 2){
                NonPreSJF();
            }
            
            if (choice == 3){
                NonPrePrio();
            }
            
            if(choice == 4){
                System.exit(0);
            }
            
            if(choice < 1 || choice > 4){
                System.out.println("Please enter the correct number.");
            }
        }while(choice!=4 );
    }
    
    /**
     * Start a new scheduling program.
     */
    public static void main(String args[])
    {
        Scheduling scheduling = new Scheduling();
    }
}
