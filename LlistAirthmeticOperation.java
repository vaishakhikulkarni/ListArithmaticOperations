/*Vaishakhi Kulkarni
 * Net Id:vpk140230
 */

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;

public class LlistAirthmeticOperation {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		LinkedList<Integer> num1 = new LinkedList<Integer>();
		LinkedList<Integer> num2 = new LinkedList<Integer>();
		LinkedList<Integer> ans = new LinkedList<Integer>();

		System.out
				.println("Large Numbers Arithmetic Operations Using Linked Lists Test\n");
		System.out.println("Enter number 1");
		String s1 = scan.next();
		System.out.println("Enter number 2");
		String s2 = scan.next();
		scan.close();

		num1 = stringToList(s1);
		num2 = stringToList(s2);

		ans = adds(num1, num2);

		String addition = listToString(ans);

		System.out.print("\nAddition Result = " + addition);

		ans = sub(num1, num2);

		String subtraction = listToString(ans);

		System.out.print("\nSubtraction Result = " + subtraction);

	}

	// Convert from String to List
	public static LinkedList<Integer> stringToList(String s) {
		LinkedList<Integer> num = new LinkedList<Integer>();
		int length = s.length();
		for (int i = length - 1; i >= 0; i--)
			num.add(s.charAt(i) - '0');
		return num;
	}

	// Convert from List to String
	public static String listToString(LinkedList<Integer> a) {
		String s = " ";
		for (int i = a.size() - 1; i >= 0; i--) {
			s = s.concat(a.get(i).toString());
		}
		return s;
	}

	// Addition Function
	public static LinkedList<Integer> adds(LinkedList<Integer> a,
			LinkedList<Integer> b) {
		LinkedList<Integer> ans = new LinkedList<Integer>();
		int l1 = a.size();
		int l2 = b.size();
		// Check which number size is greater
		int len = l1 > l2 ? l1 : l2;
		int carry = 0;
		for (int i = 0; i < len; i++) {
			int d1 = 0, d2 = 0;
			try {
				d1 = a.get(i);
			} catch (Exception e) {
			}
			try {
				d2 = b.get(i);
			} catch (Exception e) {
			}
			// Perform Addition
			int x = d1 + d2 + carry;
			ans.add(x % 10);
			// Carry is stored
			carry = x / 10;
		}
		// Final carry stored in List
		while (carry != 0) {
			ans.add(carry % 10);
		}

		return ans;
	}

	// Subtraction Function
	public static LinkedList<Integer> sub(LinkedList<Integer> a,
			LinkedList<Integer> b) {
		LinkedList<Integer> diff = new LinkedList<Integer>();

		Iterator<Integer> itr1 = a.iterator();
		Iterator<Integer> itr2 = b.iterator();

		int val1 = 0, val2 = 0, cnt = 0;

		// checking the size of number1 is less than number2
		if (a.size() < b.size()) {
			diff.add(0);
			return diff;
		} else {
			// if next digits are present in both list
			while (itr1.hasNext() && itr2.hasNext()) {
				cnt++;
				val1 = itr1.next();
				val2 = itr2.next();
				// if digit of number1 is less than digit of number 2
				if (val1 < val2) {
					// last element then result will be negative so return 0
					if (cnt == a.size()) {
						diff.clear();
						diff.add(0);
						return diff;
					}
					int temp = cnt;
					// if next digit zero get nearest non zero digit
					while (temp < a.size() && a.get(temp) - 1 < 0)
						temp++;
					// last element is 0 then again result 0
					if (temp == a.size()) {
						diff.clear();
						diff.add(0);
						return diff;
					}

					// borrow carry
					if (temp < a.size() && a.get(temp) - 1 >= 0) {
						a.set(temp, a.get(temp) - 1);
						temp--;
					}
					// adjust borrowed carry from next digits
					while (temp >= cnt) {
						a.set(temp, a.get(temp) + 'B' - 1);
						temp--;
					}
					// Add Base B to the current digit
					val1 = val1 + 'B';

				}
				// Perform Subtraction
				diff.add(val1 - val2);

			}
			// number1 digits copied to result
			while (itr1.hasNext()) {
				diff.add(itr1.next());
			}
		}
		return diff;
	}

}
