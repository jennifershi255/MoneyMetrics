
import java.util.Scanner;

public class Assessment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner s = new Scanner (System.in);

		int categories =0, choice = 0;
		
		double [] expenseValues = null; 
		
		String [] expenseCatNames = new String[0];
		
		
		while (choice != 3) {
			
			System.out.print("\n1. Expense Breakdown Graph\n2. Revenue Breakdown Graph\n3. Exit\n");
			
			System.out.println("Enter a choice: ");
			
			choice = s.nextInt();
			
			if (choice == 1) {
				
				do {
					
				Object[] result = expenseInput(expenseValues, expenseCatNames, categories);
	            expenseValues = (double[]) result[0];
	            expenseCatNames = (String[]) result[1];
				
				expenseGraphVertical(expenseValues, expenseCatNames, categories);
				
				System.out.println("Do you want to update expense values (1) or go back to the menu (2)?");
                choice = s.nextInt();
			
				} while (choice == 1);
				
			} else if (choice == 2) {
				
				expenseGraphHorizontal (expenseValues,expenseCatNames,categories);
			}
			
		}
				
				
	} public static Object[] expenseInput (double [] expenseValues, String [] expenseCatNames, int categories) {
		
		Scanner s = new Scanner (System.in);
		
		System.out.print("How many categories?");
		
		categories = s.nextInt();
		
		expenseCatNames = new String [categories];
		
		expenseValues = new double [categories];
		
		for (int i = 0; i < expenseCatNames.length ; i ++) {
			
			s.nextLine(); 
			
			System.out.println();
			
			System.out.print("Name of category " + (i + 1) + ": ");
			
			expenseCatNames[i] = s.nextLine(); 
			
			System.out.print("\nTotal expense for category: ");
			
			expenseValues[i] = s.nextDouble();
		
			
		}
		
		for (int i = 0; i < expenseCatNames.length; i ++)	{
			
			System.out.print(expenseCatNames[i] + " ");
		}
		

		for (int i = 0; i < expenseValues.length; i ++)	{
			
			System.out.print(expenseValues[i] + "  ");
		}
		
		return new Object[]{expenseValues, expenseCatNames};

		
	} public static void expenseGraphHorizontal (double [] expenseValues, String [] expenseCatNames, int categories) {
		
		double max = Double.MIN_VALUE; // Initialize max to smallest possible value

	    // Find the maximum expense value
	    for (int i = 0; i < expenseValues.length; i++) {
	        if (expenseValues[i] > max) {
	            max = expenseValues[i];
	        }
	    }

	    // Print the maximum value (for testing purposes)
	    System.out.println("Maximum expense value: " + max);

	    // Calculate the scale factor for plotting the graph
	    double scaleFactor = 10; // Adjust this value as needed

	    // Create a 2D array to represent the graph
        char[][] graph = new char[expenseValues.length][50]; // Assuming graph width is 50 characters

        for (int i = 0; i < expenseValues.length; i++) {
            int barLength = (int) (expenseValues[i] * scaleFactor);
            
            barLength = Math.min(barLength, 50); // Ensure bar length doesn't exceed graph width
            
            for (int j = 0; j < barLength; j++) {
                graph[i][j] = '=';
            }
            for (int j = barLength; j < 50; j++) {
                graph[i][j] = ' ';
            }
        }

        // Print the graph
        for (int i = 0; i < expenseValues.length; i++) {
            System.out.printf("%-15s| ", expenseCatNames[i]);
            for (int j = 0; j < 50; j++) {
                System.out.print(graph[i][j]);
            }
            System.out.println(" $" + expenseValues[i]);
        }
	} public static void expenseGraphVertical (double[] expenseValues, String[] expenseCatNames, int categories) {
		
		 double max = Double.MIN_VALUE;

	        for (int i = 0; i < expenseValues.length; i++) {
	            if (expenseValues[i] > max) {
	                max = expenseValues[i];
	            }
	        }

	        System.out.println("Maximum expense value: " + max);

	        // Determine the scale factor for plotting the graph
	        double scaleFactor = 10.0 / max;

	        // Create a 2D array to represent the graph
	        char[][] graph = new char[12][expenseValues.length + 1]; // 12 rows, categories + 1 columns

	        // Initialize the graph array with spaces
	        for (int i = 0; i < 12; i++) {
	            for (int j = 0; j < expenseValues.length + 1; j++) {
	                graph[i][j] = ' ';
	            }
	        }

	        // Add y-axis labels
	        for (int i = 0; i <= 10; i++) {
	            graph[i][0] = (char) ('0' + (10 - i));
	        }

	        // Add bars to the graph
	        for (int i = 0; i < expenseValues.length; i++) {
	            int barHeight = (int) (expenseValues[i] * scaleFactor);
	            for (int j = 0; j < barHeight; j++) {
	                graph[9 - j][i + 1] = '*';
	            }
	        }

	        // Add x-axis labels
	        for (int i = 0; i < expenseValues.length; i++) {
	            graph[11][i + 1] = expenseCatNames[i].charAt(0);
	        }

	        // Print the graph
	        for (int i = 0; i < 12; i++) {
	            for (int j = 0; j < expenseValues.length + 1; j++) {
	                System.out.print(graph[i][j] + " ");
	            }
	            System.out.println();
	        }
	   
	}

}
