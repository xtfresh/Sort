import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


public class Sort {

	/**
	 * Implement bubble sort.
	 * 
	 * It should be:
	 *  inplace
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void bubblesort(T[] arr) {
        T temp;
        boolean flag = true;

        while (flag)
        {
            flag= false;
            for( int i=0;  i < arr.length -1;  i++ )
            {
                if ( arr[i].compareTo(arr[i+1]) > 0 )
                {
                    temp = arr[i];
                    arr[i] = arr[i+1] ;
                    arr[i+1] = temp;
                    flag = true;
                }
            }
        }
    }
	
	/**
	 * Implement insertion sort.
	 * 
	 * It should be:
	 *  inplace
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void insertionsort(T[] arr) {
        for (int i = 1; i < arr.length; i++){
            int j = i;
            T pivot = arr[i];
            while ((j > 0) && arr[j-1].compareTo(pivot) > 0){
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = pivot;
        }
    }

	
	/**
	 * Implement quick sort. 
	 * 
	 * Use the provided random object to select your pivots.
	 * For example if you need a pivot between a (inclusive)
	 * and b (exclusive) where b > a, use the following code:
	 * 
	 * int pivotIndex = r.nextInt(b - a) + a;
	 * 
	 * It should be:
	 *  inplace
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n log n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void quicksort(T[] arr, Random r) {
		 quicksort(arr, 0, arr.length - 1, r);
		 
    }

	public static <T extends Comparable<T>> void quicksort(T[] arr, int left, int right, Random r){
	    if(left < right){
	        int pivotIndex = r.nextInt(right - left) + left;
	        int newIndex = quickHelper(arr, left, right, pivotIndex);
	        quicksort(arr, left, newIndex - 1, r);
	        quicksort(arr, newIndex + 1, right, r);
	    }
	}
	
	public static <T extends Comparable<T>> int quickHelper(T[] arr, int left, int right, int pivot){
	    T temp = arr[pivot];
	    T temptwo;
	    arr[pivot] = arr[right];
	    arr[right] = temp;
	
	    int newIndex = left;
	
	    for(int i = left; i < right; i++){
	        if(arr[i].compareTo(temp) < 0){
	            temptwo = arr[newIndex];
	            arr[newIndex] = arr[i];
	            arr[i] = temptwo;
	            newIndex++;
	        }
	    }
	
	    temptwo = arr[right];
	    arr[right] = arr[newIndex];
	    arr[newIndex] = temptwo;
	
	    return newIndex;
	}

	/**
	 * Implement merge sort.
	 * 
	 * It should be:
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n log n)
	 *  
	 * And a best case running time of:
	 *  O(n log n)
	 *  
	 * @param arr
	 * @return
	 */
	public static <T extends Comparable<T>> T[] mergesort(T[] arr) {
		if(arr.length == 1){
            return arr;
        }
		T[] arr1 = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length/2);
		T[] arr2;
		if(arr.length % 2 != 0){
			arr2 = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length/2+1);
		}
		else{ 
			arr2 = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length/2);
		}
			
        int length = arr.length;
        int middle = arr.length/2;
        for(int i = 0; i<middle; i++){
            arr1[i] = arr[i];
        }
        int j = 0;
        for(int i = middle; i<length; i++){
            arr2[j] = arr[i];
            j++;
        }


		return  merge(mergesort(arr1), mergesort(arr2));
	}

    private static <T extends Comparable<T>> T[] merge(T[] arr1, T[] arr2) {
        T[] result = (T[]) Array.newInstance(arr1.getClass().getComponentType(), arr1.length + arr2.length);
        int cEnd = 0;
        int a1First = 0;
        int a2First = 0;
        while(a1First < arr1.length || a2First < arr2.length){
            if(a1First<arr1.length && a2First < arr2.length)
                if(arr1[a1First].compareTo(arr2[a2First]) < 0){
                    result[cEnd] = arr1[a1First];
                    a1First++;
                    cEnd++;
                }else{
                    result[cEnd] = arr2[a2First];
                    a2First++;
                    cEnd++;
                }
            else if(a1First<arr1.length){
                result[cEnd] = arr1[a1First];
                a1First++;
                cEnd++;
            }
            else if(a2First < arr2.length){
                result[cEnd] = arr2[a2First];
                a2First++;
                cEnd++;
            }
        }

        return result;
    }

    /**
	 * Implement radix sort
	 * 
	 * Hint: You can use Integer.toString to get a string
	 * of the digits. Don't forget to account for negative
	 * integers, they will have a '-' at the front of the
	 * string.
	 * 
	 * It should be:
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(kn)
	 *  
	 * And a best case running time of:
	 *  O(kn)
	 * 
	 * @param arr
	 * @return
	 */
    public static int[] radixsort(int[] arr){
        int[] result = new int[arr.length];
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> posTemp = new ArrayList<Integer>();
        for(int temp : arr){
            if(Integer.toString(temp).charAt(0) == '-')
                list.add(temp * (-1));
            else
                posTemp.add(temp);
        }
        list = radixHelper(list, 0);
        posTemp = radixHelper(posTemp, 0);

        int index = 0;
        for(int i = list.size() - 1; i >= 0; i--){
            result[index] = (list.get(i)) * (-1);
            index++;
        }
        for(int j = 0; j < posTemp.size(); j++){
            result[index] = posTemp.get(j);
            index++;
        }
 
        return result;
    }
 
	public static ArrayList<Integer> radixHelper(ArrayList<Integer> arr, int num){
        ArrayList<Integer> zeroes = new ArrayList<Integer>();
        ArrayList<Integer> ones = new ArrayList<Integer>();
        ArrayList<Integer> twos = new ArrayList<Integer>();
        ArrayList<Integer> threes = new ArrayList<Integer>();
        ArrayList<Integer> fours = new ArrayList<Integer>();
        ArrayList<Integer> fives = new ArrayList<Integer>();
        ArrayList<Integer> sixes = new ArrayList<Integer>();
        ArrayList<Integer> sevens = new ArrayList<Integer>();
        ArrayList<Integer> eights = new ArrayList<Integer>();
        ArrayList<Integer> nines = new ArrayList<Integer>();
 
        ArrayList<Integer> list= new ArrayList<Integer>();
 
        boolean flag = true;
 
        for(int temp : arr){
            String stringList = Integer.toString(temp);
 
            if(num >= stringList.length()){
                zeroes.add(temp);
            }
            else {
                flag = false;
                switch(stringList.charAt(stringList.length() - 1 - num)){
                    case '1':
                        ones.add(temp);
                        break;
                    case '2':
                        twos.add(temp);
                        break;
                    case '3':
                        threes.add(temp);
                        break;
                    case '4':
                        fours.add(temp);
                        break;
                    case '5':
                        fives.add(temp);
                        break;
                    case '6':
                        sixes.add(temp);
                        break;
                    case '7':
                        sevens.add(temp);
                        break;
                    case '8':
                        eights.add(temp);
                        break;
                    case '9':
                         nines.add(temp);
                         break;
                    default:
                         zeroes.add(temp);
                         break;
	            }
	        }
	 
	    }
	 
        list.addAll(zeroes);
        list.addAll(ones);
        list.addAll(twos);
        list.addAll(threes);
        list.addAll(fours);
        list.addAll(fives);
        list.addAll(sixes);
        list.addAll(sevens);
        list.addAll(eights);
        list.addAll(nines);
	 
        if(flag)
            return list;
        return radixHelper(list, num + 1);
	}
}
