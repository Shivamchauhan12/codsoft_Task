package codsofttask2;
import java.util.*;

public class Marks {


	private double Percentage;
	private int Total_Marks;
	private String  Grade;
	private int Max_Marks;
	
	public void setvalues(int Subjects) {
		int i=1;
		int count=1;
		
		while(i<=Subjects) {
			
			Scanner obj=new Scanner(System.in);
			
			System.out.println(" Enter the " +  count  + " Subject Marks :");
			
			this.Total_Marks+=obj.nextInt();
			count++;
			i++;
			
		}
		
		
		this.Max_Marks=Subjects*100;
		
		this.Percentage=((double)Total_Marks/Max_Marks)*100;
					
		}
	
	public void Grade() {
		
		if(Percentage>80) {
			this.Grade="A";
		}
		else if (Percentage<80 && Percentage>60) {
			this.Grade="B";
		}
		else {
			this.Grade="C";
		}	
	}
	
	public double getAveragePercentage() {
		
		return Percentage;
	}


	public  int getTotal_Marks() {
		return Total_Marks;
		
	}
	 
	public String getGrade() {
		return Grade;
	}

	
}
