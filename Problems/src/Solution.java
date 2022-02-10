import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Solution {
	// #1
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> ints = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {

			if (ints.containsKey(target - nums[i])) {
				return new int[] { ints.get(target - nums[i]), i };
			}
			ints.put(nums[i], i);
		}
		return new int[] { 0, 1 };
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

		public String toString() {
			String result = String.valueOf(val);

			if (next != null) {
				result = result + " -> " + next.toString();
			}

			return result;

		}
	}

	// #2
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = new ListNode();
		ListNode psum = result;
		int overg = 0;

		while (l1 != null || l2 != null) {

			int digsum = overg + ((l1 == null) ? 0 : l1.val) + ((l2 == null) ? 0 : l2.val);
			int dig = digsum % 10;
			overg = digsum / 10;

			psum.next = new ListNode(dig);
			psum = psum.next;
			l1 = (l1 == null) ? null : l1.next;
			l2 = (l2 == null) ? null : l2.next;
		}

		if (overg > 0) {
			psum.next = new ListNode(overg);
		}
		return result.next;

	}

	// #3
	public int lengthOfLongestSubstring(String s) {
		int max = 0;
		HashMap<Character, Integer> charSet = new HashMap<Character, Integer>();
		int prevIndex = 0;

		for (int i = 0; i < s.length(); i++) {
			Character ch = s.charAt(i);

			if (charSet.containsKey(ch)) {
				int size = charSet.size();
				if (size > max) {
					max = size;
				}
				int index = charSet.get(ch);

				for (int ii = prevIndex; ii < index; ii++) {
					charSet.remove(s.charAt(ii));
				}
				charSet.put(ch, i);
				prevIndex = index + 1;
			} else {
				charSet.put(ch, i);
			}
		}
		int size = charSet.size();
		if (size > max) {
			max = size;
		}

		return max;

	}

	// #4
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

		int nums1Pointer = 0;
		int nums2Pointer = 0;

		int mergedSize = nums1.length + nums2.length;

		if (mergedSize == 1) {
			return (nums1.length > 0) ? nums1[0] : nums2[0];
		}

		int middle = mergedSize / 2 - 1 + (mergedSize % 2) + 1;
		TwoNumbersQueue queue = new TwoNumbersQueue();

		while (nums1Pointer + nums2Pointer <= middle) {
			if (nums1Pointer < nums1.length && nums2Pointer < nums2.length) {
				if (nums1[nums1Pointer] < nums2[nums2Pointer]) {
					queue.push(nums1[nums1Pointer]);
					nums1Pointer++;
				} else {
					queue.push(nums2[nums2Pointer]);
					nums2Pointer++;
				}
			} else if (nums1Pointer < nums1.length) {
				queue.push(nums1[nums1Pointer]);
				nums1Pointer++;
			} else if (nums2Pointer < nums2.length) {
				queue.push(nums2[nums2Pointer]);
				nums2Pointer++;
			}
		}

		if (mergedSize % 2 == 0) {
			return (queue.getFirst() + queue.getSecond()) / 2.;
		} else {
			return queue.getFirst();
		}
	}

	private static class TwoNumbersQueue {
		private int first;
		private int second;

		public TwoNumbersQueue() {

		}

		public void push(int val) {
			first = second;
			second = val;
		}

		public int getFirst() {
			return first;
		}

		public int getSecond() {
			return second;
		}
	}

	// #5
	public String longestPalindrome(String s) {
		HashMap<Integer, List<Integer>> indexesMap = new HashMap<Integer, List<Integer>>();

		int maxLength = 0;
		int maxIndex = 0;
		int maxStartIndex = 0;

		for (int i = 0; i < s.length(); i++) {
			if (indexesMap.containsKey(i - 1)) {
				List<Integer> indexes = indexesMap.get(i - 1);
				List<Integer> currentIndexes = indexesMap.computeIfAbsent(i, k -> new ArrayList<>());
				for (int index : indexes) {
					if (index > 0 && s.charAt(index - 1) == s.charAt(i)) {
						currentIndexes.add(index - 1);
						if (i - index + 1 > maxLength) {
							maxLength = i - index + 1;
							maxIndex = i;
							maxStartIndex = index - 1;
						}
					}
				}

				if (i > 0 && s.charAt(i) == s.charAt(i - 1)) {
					currentIndexes.add(i - 1);
					if (1 > maxLength) {
						maxLength = 1;
						maxIndex = i;
						maxStartIndex = i - 1;
					}
				}
				currentIndexes.add(i);
			} else {
				indexesMap.computeIfAbsent(i, k -> new ArrayList<>()).add(i);
			}
		}
		System.out.println(indexesMap);
		return s.substring(maxStartIndex, maxIndex + 1);
	}
	//#6
	public String convert(String s, int numRows) {
		String res = "";
		if (numRows == 1) {
			return s;
		}

		int step = 2 * (numRows - 1);
		for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
			if (rowIndex == 0) {
				for (int i = 0; i < s.length(); i = i + step) {
					res += s.charAt(i);
				}
			} else if (rowIndex == numRows - 1) {
				for (int i = numRows - 1; i < s.length(); i = i + step) {
					res += s.charAt(i);
				}
			} else {
				for (int i = rowIndex; i < s.length(); i = i + step) {
					res += s.charAt(i);

					int secondI = i + step - 2 * rowIndex;
					if (secondI < s.length()) {
						res += s.charAt(secondI);
					}
				}
			}
		}

		return res;
	}
	
	//#7
	public int reverse(int x) {
		int v = (x > 0) ? -x : x;
		int reversed = 0;
		
		while (v <= -1) {
			int dig = (v >= 0) ? v % 10 : - (v % 10);
			v = v / 10;
			
			int sum = 0;
			for (int i = 0; i < 10; i++) {
				sum = sum + reversed;
				if (sum < 0) {
					return 0;
				}
		    }
			sum = sum + dig;
			if (sum < 0) {
				return 0;
			}
			reversed = sum;
		}
		
		return (x > 0) ? reversed : -reversed;
	}

	//#9- * - any sumstring, . - any character
	public boolean isMatch(String s, String p) {
		boolean arr[]  = new boolean[p.length()];
		
		boolean prev = true;
		for (int  i = 0; i < s.length(); i++) {
			for (int j = 0; j < p.length(); j++) {
				boolean res = true;
				if (p.charAt(j) == '*') {
					//res = (p.charAt(j-1) == s.charAt(i)) && (prev | arr[j - 1]);
					
					res = prev | arr[j];
					res = res | ((j - 1 >= 0) ? arr[j - 1] : false);
				} else 
				if (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) {
					res = prev;
					res = res | ((j - 1 >= 0) && p.charAt(j - 1) == '*' && arr[j - 1])  ;
				} else {
					res = false;
				}
				
				prev = arr[j];
				arr[j] = res;
				
				
			}
			for (int k = 0; k < p.length(); k ++) {
				System.out.print(arr[k] + " ");
			}
			System.out.println();
			prev = false;
		}
		
		return arr[p.length() - 1];
    }
	
	
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new Solution().isMatch("ssissippi", "?*ss*?i*pi"));
		//System.out.println(new Solution().reverse(-1111111111));
		
		// System.out.println("Computed: " + new Solution().convert("PAYPALISHIRING",
		// 3));
		// System.out.println("Should be: " + "PAHNAPLSIIGYIR");
		// System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa = " + new
		// Solution().longestPalindrome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		// two sum
//	    	int[] res = new Solution().twoSum(new int[] {3, 3}, 9);
//	    	System.out.println("{" + res[0] +  ", " + res[1] + "}");

		// add two numbers
//	    	ListNode l11 = new ListNode(2);
//	    	ListNode l12 = new ListNode(4);
//	    	ListNode l13 = new ListNode(3);
//	    	l11.next = l12;
//	    	l12.next = l13;
//	    	
//	    	
//	    	ListNode l21 = new ListNode(5);
//	    	ListNode l22 = new ListNode(6);
//	    	ListNode l23 = new ListNode(4);
//	    	l21.next = l22;
//	    	l22.next = l23;
//	    	
//	    	ListNode sum = new Solution().addTwoNumbers(l11, l21);
//	    	System.out.println(sum);

		// Longest Substring Without Repeating Characters
		// System.out.println(new Solution().lengthOfLongestSubstring("bpfbhmipx"));

		// findMedianSortedArrays
//			int[] nums1 = new int[] {2, 3};
//
//			int[] nums2 = new int[] {3};
//			System.out.println(new Solution().findMedianSortedArrays(nums1, nums2));

	}
}
