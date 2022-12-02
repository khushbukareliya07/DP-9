/*
approach - 1
1. take a dp array filled with 1. 
2. keep i at 0 and j at 1; 
3. run j loop till n nd i loop till j. 
4. if nums[j] > nums[i]  then dp[j]'s value should eb updated to dp[i]+1 / or keep max 
tc - O(n*n), sc - O(n)'*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n ==0 || nums == null) return 0;
        
        int[] dp = new int [n];
        Arrays.fill(dp, 1);
        
        int max =1; // if no sequence found, we return 1 as individual entries will be making a subsequnce of length 1.
        
        for(int j=1; j< n; j++)
        {
            for(int i=0; i< j; i++)
            {
                if(nums[j] > nums[i])
                {
                    dp[j] = Math.max(dp[j], dp[i]+1);
                    max = Math.max(dp[j], max);
                }
            }
        }
        return max;
    }
}


/*
approach - 2
use binary search 
1. take an array of length n and store 1st value there. and so len ==1 as other elements will be zero and we want to perform binary search on to 0 to len -1;
2. if we find a greater value to last index value , we add a value and len++
else it's smaller than last index's val; we look for the position into the array
    a. target coud be exisiting already, so its duplicate and we just return the mid. 
    b. if not duplicate , we tying to find a location for the target and it doesn't exist, so after we are exhausted, the rightful position will be the low  which will be updated as mid+1; so at last return low.
    
now traverse through the array and check if 
tc - O(n) + O(n log n), sc - O(n)'*/

//extra wasted space  in arr can be saved by using list 

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n ==0 || nums == null) return 0;
        
        int [] arr = new int[n];
        arr[0] = nums[0];
        int len =1;
        
        for(int i=1; i< n; i++)
        {
            if(nums[i] > arr[len-1])
            {
                arr[len] = nums[i];
                len++;
            }else
            {
                int correctedIndex = binarySearch(arr, nums[i], 0, len-1);
                arr[correctedIndex] = nums[i];
            }
        }
        
        return len;
    }
    
    
    //private method
    private int binarySearch(int[] arr, int target, int low, int high)
    {
        while(low <= high)
        {
            int mid = low + (high-low)/2;
            
            if(arr[mid] == target)
                return mid;
            else if(target < arr[mid])
                high = mid -1;
            else
                low = mid +1;
        }
        
        return low;
    }
}