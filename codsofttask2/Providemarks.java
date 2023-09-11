package codsofttask2;
import java.util.*;

public class Providemarks {

	public static void main(String[] args) {
		
		Marks obj1=new Marks();
		
		Scanner obj2=new Scanner(System.in);
		
		System.out.println("Please Enter the totl no of subjects : ");
		
		int subjects=obj2.nextInt();
		
		
		obj1.setvalues(subjects);
		obj1.Grade();
		
		System.out.println("Total_Marks : " + obj1.getTotal_Marks());
		
		System.out.println("Average_Percentage : " + obj1.getAveragePercentage());
	
		System.out.println("Grade Achieved : " + obj1.getGrade());

	}

}
