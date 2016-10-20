package problem.statement;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Test {

	/*static int minUnfairness(int k, int[] arr) {
        int dif=Integer.MAX_VALUE;
        Arrays.sort(arr);
        
        for(int i=0; i+k-1<arr.length;i++){
        	
        	if(dif>arr[i+k-1]-arr[i]){
        		dif=arr[i+k-1]-arr[i];
        	}
        }
        
        return dif;
    }
	
	public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        int res;
        int _k;
        _k = Integer.parseInt(in.nextLine());
        
        
        int _arr_size = Integer.parseInt(in.nextLine());
        int[] _arr = new int[_arr_size];
        int _arr_item;
        for(int _arr_i = 0; _arr_i < _arr_size; _arr_i++) {
            _arr_item = Integer.parseInt(in.nextLine());
            _arr[_arr_i] = _arr_item;
        }
        
        res = minUnfairness(_k, _arr);
        System.out.println(res);
    }*/
	
	/*static void finalPrice(int[] prices) {
		//System.out.println(Arrays.toString(prices));
		int min=0;
		int[] d = new int[prices.length];
		d[prices.length-1]=0;
		for (int i = prices.length-2; i >= 0; i--) {
			if(prices[i]<prices[i+1]){
				if(prices[i]>=d[i+1])
				d[i] = d[i+1];
			}else{
				d[i]=prices[i+1];
			}
		}
		String index="";
		int sum=0;
		for (int i = 0; i < d.length; i++) {
			sum+=prices[i];
			sum-=d[i];
			if(d[i]==0)
			index+=i+" ";
		}
		System.out.println(sum);
		System.out.println(index.trim());
    }

	
	public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        int _prices_size = 0;
        _prices_size = Integer.parseInt(in.nextLine().trim());
        int[] _prices = new int[_prices_size];
        int _prices_item;
        for(int _prices_i = 0; _prices_i < _prices_size; _prices_i++) {
            _prices_item = Integer.parseInt(in.nextLine().trim());
            _prices[_prices_i] = _prices_item;
        }
        
        finalPrice(_prices);
        
    }*/
	
	static int zombieCluster(String[] zombies) {
        int cluster=0;
        List<Integer> lists= new ArrayList<Integer>();
        int size=0;
        for (int i = 0; i < zombies.length; i++) {
        	if(!lists.contains(i)){
        	lists.add(i);
        	//System.out.println(i);
			search(zombies,lists,i);
        	}
        	if(size!=lists.size()){
        		size=lists.size();
        		cluster++;
        	}
		}
        
        
        return cluster;
    }
	
	

	
	private static void search(String[] zombies, List<Integer> lists, int i) {
		for (int j = i+1; j < zombies.length; j++) {
			char c = zombies[i].charAt(j);
			if(c=='1'){
				lists.add(j);
				//System.out.print(" "+j);
				search(zombies,lists,j);
			}
		}
		
	}




	public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        int res;
        
        int _zombies_size = 0;
        _zombies_size = Integer.parseInt(in.nextLine().trim());
        String[] _zombies = new String[_zombies_size];
        String _zombies_item;
        for(int _zombies_i = 0; _zombies_i < _zombies_size; _zombies_i++) {
            try {
                _zombies_item = in.nextLine();
            } catch (Exception e) {
                _zombies_item = null;
            }
            _zombies[_zombies_i] = _zombies_item;
        }
        
        res = zombieCluster(_zombies);
        System.out.println(res);
        
    }
}
