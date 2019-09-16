package taxcab;
import java.util.*;
public class Cab {
	
	    public static void main(String[] args) {

	      Taxi[] t=new Taxi[4];
	      t[0]=new Taxi();
	      t[1]=new Taxi();
	      t[2]=new Taxi();
	      t[3]=new Taxi();
	      Booking[] b=new Booking[100];
	      int i=0,j=0;
	      Scanner scan=new Scanner(System.in);
	      while(true)
	      {
	      System.out.println("__________________CALL TAXI BOOKING__________________");
	      System.out.println("1)Booking");
	      System.out.println("2)Display");
	      System.out.println("3)exit");
	      System.out.println("Enter your choice");
	      int ch=scan.nextInt();
	      
	      if(ch>3 || ch<1){System.out.println("Invalid Input");return;}
	      if(ch==1)
	      {
	    	  
	         System.out.println("Input "+(i+1)+":");
	         System.out.println("Customer Id");
	         int id=scan.nextInt();
	         System.out.println("Pickup Point:");
	         scan.nextLine();
	         char pick=scan.nextLine().charAt(0);
	         System.out.println("Drop Point:");
	         char drop=scan.nextLine().charAt(0);
	         
	         if(drop>(char)70)
			 {
		          System.out.println("Taxies available for upto location F");
		          System.out.println("Drop Point:");
		          drop=scan.nextLine().charAt(0);
			 }
	         System.out.println("Pickup Time:");
	         int PickTime=scan.nextInt();
	         
	         
	         System.out.println(Math.abs(pick-64));
	         
	         
	        b[i]=new Booking(id,pick,drop,PickTime);
	        int a;
	         a = b[i].isAvailable(t);
	        System.out.println("ouput "+(i+1));
	        if(a!=-1)
	        {
	               System.out.println("Taxi-"+(a+1)+" is alloted");
	               
	               t[a].calculateEarnings(b[i].pickupPoint,b[i].dropPoint);
	               
	               b[i].dropTime();
	               b[i].calculateEarnings();
	        }
	        else System.out.println("Booking is rejected");
	        i++;
	      }
	      else if(ch==2)
	      {
	          System.out.format("%-10s%-10s\n","Taxi No:","Total Earnings:");
	          System.out.format("%-10s%-13s%-10s%-10s%-13s%-10s%-10s\n","BookingID","CustomerId","From","To","PickupTime","DropTime","Amount");
	          System.out.println("output");
	          for(int k=0;k<4;k++)
	          {
	              if(t[k].earnings!=0)
	              {
	                  System.out.println("Taxi-"+(k+1)+"    "+"Total Earnings:"+"Rs . "+t[k].earnings);
	                  for(j=0;j<i;j++)
	                  {
	                      if(b[j].taxino==k)
	                      {
	                        
	                          System.out.format("%-10d%-10d%-10c%-10c%-10d%-10d%-10d\n",(j+1),b[j].customerId,
	                            b[j].pickupPoint,b[j].dropPoint,b[j].pickupTime,b[j].dropTime,b[j].earnings);
	                      }
	                      
	                  }
	              }
	          }
	      }
	      else if(ch==3)
	      return;
	      }
	     
	}
	    
	}

	 class Taxi {
	    char initialPoint;
	    int departureTime;
	    int earnings;
	    public Taxi()
	    {
	        initialPoint='A';
	    }
	    public void departureTime(int pickTime,char pick,char drop)
	    {
	        this.departureTime=(pickTime+(Math.abs(pick-drop)));
	    }
	    public int calculateEarnings(char pick,char drop)
	    {
	    	
	    	int array[][]= {{0,15,30,45,60,75},
	    					{15,0,15,30,45,60},
	    					{30,15,0,15,30,45},
	    					{45,30,15,0,15,30},
	    					{60,45,30,15,0,15},
	    					{75,60,45,30,15,0}};
	        int l=Math.abs(pick-65);
	        int m=Math.abs(drop-65);
	        int dist=array[l][m];
	        System.out.println(dist);
	        if(dist==0)
	        {
	        	int amount=((dist-10)*10)+100;
	            this.earnings=earnings+amount;

	        	return dist;
	        }
	        else 
	        {
	        int amount=((dist-5)*10)+100;
	        this.earnings=earnings+amount;
	        return dist;
	        }
	    }
	       
	  
	}
	 class Booking {
	   int customerId;
	   char pickupPoint;
	   char dropPoint;
	   int pickupTime;
	   int dropTime;
	   int earnings; 
	   int taxino; 
	   Taxi[] t;
	  Taxi a1=new Taxi();

	    Booking(int id, char pick, char drop, int PickTime) {
	   customerId=id;
	       pickupPoint=pick;
	       dropPoint=drop;
	       pickupTime=PickTime;
	    }
	    
	    public void dropTime()
	    {
	        this.dropTime=(pickupTime+(Math.abs(pickupPoint-dropPoint))); 
	    }
	    public void calculateEarnings()
	    {
	        int dist=a1.calculateEarnings(pickupPoint,dropPoint); 
	        
	        if(dist==0)
	        {
	        	this.earnings=((dist-10)*10)+100; 
	        }
	        else {
	        this.earnings=((dist-5)*10)+100;
	        }
	        
	    }
	    
	    
	    
	  
	   public int isAvailable(Taxi[] t) 
	   {
	       int j,min=6,temp=-1;
	       for(j=0;j<4;j++)
	       {
	          if(Math.abs(pickupPoint-t[j].initialPoint)<=min && t[j].departureTime<=pickupTime)
	          {
	                if(temp==-1 || Math.abs(pickupPoint-t[j].initialPoint)<min ) 
	                 temp=j;
	             if(Math.abs(pickupPoint-t[j].initialPoint)==min && t[j].earnings!=0) 
	             {
	                 if(t[temp].earnings>t[j].earnings) 
	                 temp=j;
	             }
	             min= Math.abs(pickupPoint-t[j].initialPoint);
	             
	          }
	       }
	       if(min!=6){
	           t[temp].departureTime(pickupTime,pickupPoint,dropPoint);
	           t[temp].initialPoint=dropPoint;
	           taxino=temp;
	           return temp;} 
	       return -1;
	   }
	}
