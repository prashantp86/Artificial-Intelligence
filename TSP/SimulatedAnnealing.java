import java.util.*;
import java.io.*;
import java.math.*;


public class SimulatedAnnealing
{
    Random random = new Random();
    private int city;
    private  ArrayList<ArrayList<Integer> > distance = new ArrayList<ArrayList<Integer> >();
    ArrayList<Integer> tour=new ArrayList<>();
    int cost;
    double T = 10000;
    ArrayList<String> RajCity=new ArrayList<String>(Arrays.asList("Ajmer", "Jaipur", "Kishangarh", "Bundi", "Sawai_Madhavpur", "Udaipur", "Jodhpur", "Amer", "Mountabu", "Pali", "churu", "Alwar", "Bhilwara", "Neemrana", "Ganganagar", "Mandawa", "Sikar", "Bhangarh", "Salasar", "Barmer"));
	double iteration = 100000;
    
    public SimulatedAnnealing(int n, ArrayList<ArrayList<Integer> > arr)
    {
        this.city=n;
        this.distance=arr;
    }
    public void build_Graph()
    {
        for(int i=0;i<city;i++)
        {
            for(int j=0;j<city;j++)
            {
                if(distance.get(i).get(j)==-1)
                {
                    if(i==j)
                    {
                        distance.get(i).set(j,0);
                    }
                    else
                    {
                        distance.get(i).set(j,random.nextInt(32767)%400+1);
                        distance.get(j).set(i,distance.get(i).get(j));
                    }
                        
                }
            }
        }
    }
    public double getTemp()
    {
        return T;
    }
    public void path_generation()
    {
        cost=0;
        tour.add(0);
        int c[] = new int [city];
        c[0] = 1;
        for(int i=1;i<city;i++)
        {
            int temp=(int)random.nextInt(32767)%city;
            while(c[temp]!=0)
            {
                temp = (int)random.nextInt(32767)%city;
            }
            c[temp] = 1;
            cost+=distance.get(tour.size()-1).get(temp);
            tour.add(temp);
        }
    }
    public void get_path()
    {
        for(int i=0;i<tour.size();i++)
        {
            System.out.print(RajCity.get(tour.get(i))+ " ---> ");
        }
        System.out.println(RajCity.get(0));
    }
    public void print_distance()
    {
        for(int i=0;i<city;i++)
        {
            for(int j=0;j<city;j++)
            {
                System.out.print(" |"+ distance.get(i).get(j) + "|");
            }
            System.out.println();
        }
    }
    public double return_Cost()
    {
        
        return cost;
    }
    int Cost(ArrayList<Integer> t)
    {
        int dist=0;
        for(int i=0;i<t.size()-1;i++)
        {
            dist+=distance.get(t.get(i)).get(t.get(i+1));
        }
        return dist;
    }
    ArrayList<Integer> generateChild(ArrayList<Integer> t)
    {
        ArrayList<Integer> temp;
        int temp1=(int)random.nextInt(32767)%city;
        int temp2=(int)random.nextInt(32767)%city;
        while(temp1==temp2||temp1==0||temp2==0)
        {
            temp1 = (int)random.nextInt(32767)%city;
            temp2 = (int)random.nextInt(32767)%city;
        }

        temp = t;

        int s;
        s = temp.get(temp1);
        temp.set(temp1,temp.get(temp2));
        temp.set(temp2,s);

        return temp;
    }
   void Sim_annealing()
    {
        
        while(iteration>0)
        {
        
            ArrayList<Integer> temp = generateChild(tour);
            int newCost = Cost(temp);
            int diff = newCost - cost;
            double temp_prob = 1/(1+Math.exp(-(diff/T)));

            if(diff<0)
            {
                
                tour = temp;
                cost = newCost;
                T*=0.2;
            }
            else
            {
                double probability = 1/(random.nextInt(32767)+0.000001);
                if(probability > temp_prob)
                {
                    tour = temp;
                    cost = newCost;
                    T*=0.2;
                }
            }
            iteration--;
        }    
    }
	public static void main(String arg[]) 
	{

	    int n = 20;

	    ArrayList<ArrayList<Integer> > l = new ArrayList<ArrayList<Integer> >(n);
	    for(int i=0;i<n;i++)
	    {
            ArrayList<Integer> list = new ArrayList<Integer>();
            l.add( list );
	        for(int j=0;j<n;j++)
	        {
                l.get( l.size() -1 ).add( -1 );
	        }
	    }

	    SimulatedAnnealing graph=new SimulatedAnnealing(n,l);

	    System.out.println("Initial Temperature taken = "+ graph.getTemp());
	    System.out.println("Number of Iterations = "+graph.iteration);
	    System.out.println("\nGiven Rajasthan Cities are: \n");

	    for(int i=0;i<graph.RajCity.size();i++)
	    {
	        System.out.print("| "+graph.RajCity.get(i)+" ");
	    }
	    System.out.println();
	    graph.build_Graph();
        System.out.println();
	    System.out.println("Randomly generated distance between the city:\n");
        graph.print_distance();
	    System.out.println();
	    graph.path_generation();
	    System.out.println("Initial tour of the given graph:\n");
	    graph.get_path();
        System.out.println();
	    System.out.println("Initial cost of the graph: "+graph.return_Cost()+"\n");
	    graph.Sim_annealing();
        System.out.println();
	    System.out.println("Final tour of the given graph:\n");
	    graph.get_path();
        System.out.println();
	    System.out.println("Final cost of the graph: "+graph.return_Cost()+"\n");

	}

}


