/* 
 * Implement linearSearch(a,key) and binarySearch(a,key) functions.
 * Part A. In this part we will calculate the average-case running time of each function.
 	* 1. Request the user to enter a positive integer, and call it n. 
 	* 2. Generate n random integers between -1000 to 1000 and save them in array a.
 	* 3. Sort a (you can use any sorting algorithm you want).
 	* 4. Pick a random number in a and save it in variable called key.
 	* 5. Call each function separately to search for the key in the given array.
 	* 6. To calculate the average-running time, you need to have a timer to save the total runtime when repeating step 4 and 5 for 500 times.
 	* Note1: Do not forget to divide the runtime by the number of the times you run step 4-5.
 	* Note2: Remember to choose a different random number each time you go back to step 4. 
 * Part B. In this part we will calculate the worst-case running time of each function.
	* 1. Repeat steps 1 to 3 in part A.
	* 2. Now to have the worst-case scenario, set the value of the key to 5000 to make sure it does not exist in the array.
	* 3. Run each function once to calculate the worst-case running time when n = 10^5.
	* 4. Calculate how much time your machine takes to run one single step.
	* 5. Now estimate the worst-case running time for each algorithm when n=10^7. 
	* 6. Now repeat step 1-3 for n = 10^7 and compare the actual running time with your answer in step 5.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main{

	public static void main(String[] args){

		// Part A
		Scanner keyboard = new Scanner(System.in);
		List<Integer> myArray = new ArrayList<>();
		Random randomGenerator = new Random();
		
		System.out.println("Enter a positive integer");
		int n = keyboard.nextInt();
		int nTrials = 500;
		for(int i = 0; i < n; i++){
			int num = -1000 + randomGenerator.nextInt(2000);
			myArray.add(num);
		}
		Collections.sort(myArray);
		long linearStartTime = System.nanoTime();
		for(int i = 1; i <= nTrials; i++){
			int randomIndex = 0 + randomGenerator.nextInt(n - 1);
			int key = myArray.get(randomIndex);
			linearSearch(myArray, key);
		}
		long linearEndTime = System.nanoTime();
		long averageLinearTime = (linearEndTime - linearStartTime) / nTrials;
		System.out.println("\nLinear Search Average Time: " + averageLinearTime + " ns");
		long binaryTotalTime = 0;
		for(int i = 1; i <= nTrials; i++){
			int randomIndex = 0 + randomGenerator.nextInt(n - 1);
			int key = myArray.get(randomIndex);
			long binaryStartTime = System.nanoTime();
			binarySearch(myArray, key);
			long binaryEndTime = System.nanoTime();
			binaryTotalTime += (binaryEndTime - binaryStartTime);
		}
		long averageBinaryTime = binaryTotalTime / nTrials;
		System.out.println("Binary Search Average Time: " + averageBinaryTime + " ns");
		
		
		// Part B
		myArray.clear();
		int key = 5000;
		n = 100000;
		for(int i = 0; i < n; i++){
			int num = -1000 + randomGenerator.nextInt(1000);
			myArray.add(num);
		}
		Collections.sort(myArray);
		linearStartTime = System.nanoTime();
		linearSearch(myArray, key);
		linearEndTime = System.nanoTime();
		averageLinearTime = linearEndTime - linearStartTime;
		System.out.println("\nLinear Search Worse-Case Scenario, n = 10^5: " + averageLinearTime + " ns");
		long binaryStartTime = System.nanoTime();
		binarySearch(myArray, key);
		long binaryEndTime = System.nanoTime();
		averageBinaryTime = binaryEndTime - binaryStartTime;
		System.out.println("Binary Search Worse-Case Scenario, n = 10^5: " + averageBinaryTime + " ns");
		
		
		long singleLinear = averageLinearTime / n;
		System.out.println("\nLinear Search Single Step Worse-Case Scenario: " + singleLinear + " ns");
		double singleBinary = averageBinaryTime / Math.log(n)/Math.log(2);
		System.out.println("Binary Search Single Step Worse-Case Scenario: " + singleBinary + " ns");
		
		n = 10000000;
		long linearEstimation = n * singleLinear;
		System.out.println("\nLinear Search Worse-Case Scenario Estimation, n = 10^7: " + linearEstimation + " ns");
		double binaryEstimation = Math.log(n)/Math.log(2) * singleBinary;
		System.out.println("Binary Search Worse-Case Scenario Estimation, n = 10^7: " + binaryEstimation + " ns");


		myArray.clear();
		for(int i = 0; i < n; i++){
			int num = -1000 + randomGenerator.nextInt(1000);
			myArray.add(num);
		}
		Collections.sort(myArray);
		linearStartTime = System.nanoTime();
		linearSearch(myArray, key);
		linearEndTime = System.nanoTime();
		averageLinearTime = linearEndTime - linearStartTime;
		System.out.println("\nLinear Search Worse-Case Scenario, n = 10^7: " + averageLinearTime + " ns");
		binaryStartTime = System.nanoTime();
		binarySearch(myArray, key);
		binaryEndTime = System.nanoTime();
		averageBinaryTime = binaryEndTime - binaryStartTime;
		System.out.println("Binary Search Worse-Case Scenario, n = 10^7: " + averageBinaryTime + " ns");
	}
	
	public static void linearSearch(List<Integer> a, int key){
		for(int i = 0; i < a.size(); i++){
			if(a.get(i) == key)
				break;
		}
	}
	
	public static void binarySearch(List<Integer> a, int key){
		int low = 0;
		int high = a.size() - 1;
		while(low <= high){
			int middle = (low + high) / 2;
			if(key == a.get(middle))
				break;
			else if(key > a.get(middle))
				low = middle + 1;
			else
				high = middle - 1;
		}
	}

}
