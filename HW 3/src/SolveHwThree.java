     /* COURSE      : COP 3337
       * Section     : U08
       * Semester    : Fall 2015
       * Instructor  : Alex Pelin
       * Author      : Rachel Petitto
       * Assignment #: 3
       * Due date    : November 5th 2015
       * Description :
       * This program contains the methods to solves 4 mini problems, for HW #3B. The theme is Recursion.
       *
       *  I certify that the work is my own and did not consult with
       *  anyone.
       *
       *
       *                                       Rachel Ann Petitto
       *
       */

public class SolveHwThree {
	
	/* Problem I.
	 * We define the Pestaina strings as follows:
	 *
     * 1. ab is a Pestaina string.
     * 2. cbac is a Pestaina string.
	 * 3. If S is a Pestaina string, so is SbSa.
	 * 4. If U and V are Pestaina strings, so is aUbV.

	 * Here a, b, c are constants and S,U,V are variables. In these rules,
	 * the same letter represents the same string. So, if S = ab,
	 *rule 3 tells us that abbaba is a Pestaina string.
	 *In rule 4, U and V represent Pestaina strings, but they may be different.
	 */

	//Returns true if in is a Pestaina string and false otherwise.
	public static boolean isPestaina(String in)
	{
		// Throw out instant false values
		if (in.length() < 2)
		return false;
		
		//Base Case, Rule 1 and 2
		if (in.equalsIgnoreCase("ab") || in.equalsIgnoreCase("cbac") )
		return true;
		
		// Some Variables
		int length = in.length(); 			// Length of in
		char last = in.charAt(length-1); 	// Last Character of in
		char first = in.charAt(0);			// First Char of in
				
		// BEGIN RECURSION
		
		// Test for Case 3, SbSa
		// IN (MAY) be a condition 3 Pestaina number (If) it is even, (AND) the last 
		// character is equal to a, (AND) the character between S0 and S1 is b.
		
		if ( length % 2 == 0 && last == 'a' && in.charAt((length-2)/2) == 'b' ) 
		{
			// Divide IN into S0 and S1.
			// S0 would begin at index 0 and end at index b-1.
			// S1 would begin at index b+1 and end at index a-1.
			String S0 = in.substring(0,(length-2)/2);
			String S1 = in.substring(((length-2)/2) + 1, length -1);
			// Compare S0 and S1 for equality, (AND) if true, call Pestaina 
			// for final check of base case. 
			if (S0.equalsIgnoreCase(S1) && isPestaina(S0))
				return isPestaina(S0);
		}	
		
		// Final Test for Case 4 (aUbV)
		// We know the first character must be 'a'
		if (first == 'a')
		{
			// Temp Variables
			String U = "";						// First Pestaina
			String V = "";						// Second Pestaina
			int bCounter = 0;					// initialize counter for b
			String UbV = in.substring(1); 		// in([possible]aUbV) without a 
			char[] UbVArr = UbV.toCharArray();  // Make life easier with a char Array of UbV
			int bLimit = 2;						// initial b limit (every b after the first)
			
			
			// Begin Loop
			for (int i = 0; i < UbVArr.length; i++)
			{
				// b is constant we want to keep track of. It is the divider between U and V.
				// we should do nothing at the first b, but every b after we will use as a stopping point
				// to test for Pestaina values.
				if (UbVArr[i] == 'b')
				{
					bCounter++;
					
					// If Limit is reached, stop to test U for Pestaina
					if (bCounter == bLimit )
					{
						// Test U for Pestaina String. 
						// If TRUE, set the remaining characters after b equal to V
						// If V is also a Pestaina String, then return true.
						// If V is not a Pestaina String, try again with a new value for U at the 
						// next increment of b
						if (isPestaina(U))
						{
							// be careful not to include divider constant b in variable V
							V = UbV.substring(i+1);
							if (isPestaina(V))
							{
								return true;
							}
						}
						// Either U (OR) V was (NOT) a Pestaina String
						// INCREMENT bLimit, so we may add more characters to U and try again at the 
						// next instance of b. 
						bLimit++;
					}
				}
				// Continue adding characters to U
				U += UbVArr[i];
			}	
		}
		
		// String in is NOT a Pestaina String
		return false;	
	}
	
	 		
		/*
		 * Problem IV. 
		* Search the array slice arr[low:high] for an occurrence
		* of inq. If inq occurs in arr, return an index i such that
		* arr[i] == inq. Otherwise, return -1.

		* Assume that arr is sorted in increasing order. 
		* Implement the binary search method.
		 */
		public static int binarySearch(double[] arr, int low, int high,
				double inq)
		{
			// Incorrect Value / Time to Stop Recursion
			if (low > high)
				return -1;
			// Base Case (middle)
			int middle = (low + high) / 2;
			if (arr[middle] == inq)
				return middle;
			
			// If middle value is less than inq, Take Upper Half
			else if (arr[middle] < inq)
			{
				low = middle + 1;
				binarySearch(arr, low, high, inq);
			}
			// Bottom Half
			else 
				high = middle -1;
				return binarySearch(arr, low, high, inq);
		}
		
	public static void main(String[] args) 
    {
        System.out.println("Checking Hw#3B");
        System.out.println("==============\n\n");

        // check isShaw
        System.out.println("We check isPestaina");
        System.out.println("isPestaina(\"acbacbcbac\") is " + isPestaina("acbacbcbac"));
        System.out.println("isPestaina(\"acbacbcbacabab\") is " + isPestaina("acbacbcbacabab"));
        System.out.println("isPestaina(\"cbacbaba\") is " +
                isPestaina("cbacbaba"));
        System.out.println("isPestaina(\"cbacbcbac\") is " +
                isPestaina("cbacbcbac"));
        System.out.println("isPestaina(\"acbababa\") is " +
                isPestaina("acbababa"));
        System.out.println("isPestaina(\"cbacbcbacabcbacbcbacaa\") is "
                + isPestaina("cbacbcbacabcbacbcbacaa"));

        System.out.println("isPestaina(\"aabbcbacbcbaca\") is " + isPestaina("aabbcbacbcbaca"));
        
        
        System.out.println("\nWe check finlayson");
        
        System.out.println("finlayson(2) = " + Finlayson(9));
        System.out.println("finlayson(10) = " + Finlayson(10));
        System.out.println("finlayson(17) = " + Finlayson(17));
        try
        {
            System.out.printf("finlayson(-2) ");
            long out = Finlayson(-2);
            System.out.println( "= " + out);
        }    
        catch (IllegalArgumentException e)
        {
            System.out.println(" caused an illegal argument exception.");
        }    
        
        System.out.println("\nWe test isBalanced.");
        System.out.println("isBalanced(\"\") is " + isBalanced(""));
        System.out.println("isBalanced(\"([]){[]}\") is "  + isBalanced("[][][][]")) ;
        System.out.println("isBalanced(\"[{{})]\") is " + isBalanced("[{{{)]"));
        System.out.println("isBalanced(\"([)]\") is " + isBalanced("([)]"));
        System.out.println("isBalanced(\"((([[[{{{}}}]]])))\") is " + isBalanced("((([[[{{{}}}]]])))"));
        // test sort
        System.out.println("\nWe test binary search.");
        System.out.println("The input array is table = { 2, 4, 6, 8, 10, 12, 14 }. ");
        double[] table = { 2, 4, 6, 8, 10, 12, 14};

        System.out.println("2 was found in table[0:6] at index " + binarySearch(table, 0, 6, 2));
        System.out.println("3 was found in table[0:6] at index " + binarySearch(table, 0, 6, 3));
        System.out.println("4 was found in table[2:6] at index " + binarySearch(table, 2, 6, 4));
        System.out.println("12 was found in table[2:5] at index " + binarySearch(table, 2, 5, 12));

        System.out.println("\nThis is all folks. I hope that your program worked.");
        System.exit(0);
    }
}
