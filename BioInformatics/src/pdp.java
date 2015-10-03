
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zitu
 */
public class pdp {
    String fle;
    pdp(String f)
    {
        fle=f;
    }
     static final int MAX_NUM_OF_INPUT = 2000000;
	static final int MAX_NUM_OF_POINTS = 2000000;
	static final int MAX_VALUE_OF_INPUT = 1000000;
	public static int[] restriction_point = new int[MAX_NUM_OF_POINTS];
	public static int[] points = new int[MAX_NUM_OF_POINTS];
	public static int[] output = new int[MAX_NUM_OF_POINTS];
	public static int[] all_dist = new int[MAX_NUM_OF_INPUT];
	public static int[] input = new int[MAX_NUM_OF_INPUT];
	public static int[] dist = new int[MAX_VALUE_OF_INPUT];
	public static boolean[] has_taken = new boolean[MAX_VALUE_OF_INPUT];
	public static int points_taken = 0, result = 0, total_points = 0, n = 0, prev_point = 0, y = 0, distinct_dist = 0;
	public static int index_from_right = 0, index_from_left = 0;
	static void prln(Object anyObject)
	{
	     System.out.println(anyObject);
	}
	static void pr(Object anyObject)
	{
		 System.out.print(anyObject);
	}
	public static void clear()
	{
	    int i;
	    for(i = 0; i <= distinct_dist; i++)
	    {
	        all_dist[i] = 0;
	        restriction_point[i] = 0;
	        output[i] = 0;
	        dist[points[i]] = 0;
	        has_taken[points[i]] = false;
	    }
	}
	public static void Number_Of_Outputs()
	{
	    int i, sum;
	    for(i = 1; i < 1010; i++)
	    {
	        sum = (i * (i + 1))/ 2;
	        input[sum] = i + 1;
	    }
	}
	public static void undo_prevstep()
	{
	    int i, d;
	    points_taken--;
	    prev_point = restriction_point[points_taken];
	    restriction_point[points_taken] = 0;
	    has_taken[prev_point] = false;
	    for(i = 0; i < points_taken; i++)
	    {
	        d = Math.abs(prev_point - restriction_point[i]);
	        dist[d]++;
	    }
	}
	public static boolean VALID(int current_point)
	{
	    int i, d;
	    boolean ok = true, not_ok = false;
	    for(i = 0; i < points_taken; i++)
	    {
	       d = Math.abs(current_point - restriction_point[i]);
	       dist[d]--;
	       if(dist[d] == -1) not_ok = true;
	    }
	    if(not_ok == true)
	    {
	         for(i = 0; i < points_taken; i++)
	         {
	               d = Math.abs(current_point - restriction_point[i]);
	               dist[d]++;

	         }
	         return false;
	    }
	    else
	        return true;
	}
	public static void choose_next_index(int i_f_r, int i_f_l)
	{
	    int i, new_dist = 0, d;
	    y = 0;
	    for(i = i_f_r - 1; i >= 0; i--)
	    {
	        if(dist[all_dist[i]] != 0)
	        {
	            y = 1;
	            i_f_r = i;
	            index_from_right = i;
	            new_dist = all_dist[i];
	            break;
	        }
	    }
	    if(y != 0)
	    for(i = i_f_l + 1; i < n; i++)
	    {
	        d = all_dist[n - 1] - new_dist;
	        if(dist[d] !=0 && all_dist[i] == d)
	        {
	            y++;
	            i_f_l = i;
	            index_from_left = i;
	            break;
	        }
	    }
	    return;
	}
	public static void PDP(int in_fr_ri, int in_fr_le)
	{
		index_from_right = in_fr_ri;
		index_from_left = in_fr_le;
	    int i;
	    int prev_index_from_right, prev_index_from_left;
	        while(true)
	        {
	            if(VALID(all_dist[index_from_right]))
	            {
	                restriction_point[points_taken] = all_dist[index_from_right];
	                has_taken[all_dist[index_from_right]] = true;
	                points_taken++;
	                if(points_taken == total_points) break;
	                prev_index_from_right = index_from_right;
	                prev_index_from_left = index_from_left;
	                choose_next_index(index_from_right, index_from_left);
	                if(y == 0) return;
	                PDP(index_from_right, index_from_left);
	                if(points_taken != total_points)
	                {
	                index_from_right = prev_index_from_right;
	                index_from_left = prev_index_from_left;
	                undo_prevstep();
	                }
	            }
	            if(VALID(all_dist[index_from_left]))
	            {
	                restriction_point[points_taken] = all_dist[index_from_left];
	                has_taken[all_dist[index_from_left]] = true;
	                points_taken++;
	                if(points_taken == total_points) break;
	                choose_next_index(index_from_right, index_from_left);
	                if(y == 0) return;
	                PDP(index_from_right, index_from_left);
	                if(points_taken != total_points)
	                {
	                undo_prevstep();
	                }
	            }
	            return;
	        }
	        return;
	}
    String ret_pdp()
    {
     int i, d, j, k, no, test;
		restriction_point[0] = 0;
                String Ret="";
                int cas=1;
            
           try{
                   File fl=new  File(fle);
                BufferedReader bf=new BufferedReader(new FileReader(fl));
		Number_Of_Outputs();
		test = Integer.valueOf(bf.readLine());
		while(test != 0)
		{
			n = Integer.valueOf(bf.readLine());
			points_taken = 2;result = 0;distinct_dist = 0;k = 0;no = 0;
			index_from_right = 0;index_from_left = 0;
			for(i = 0; i < n; i++)
			{
			d = Integer.valueOf(bf.readLine());
		        dist[d]++;
		        all_dist[i] = d;
		        if(dist[d] == 1)
		        {
		         points[distinct_dist] = d;
		         distinct_dist++;
		        }
			}
			//prln(all_dist[0]);
			Arrays.sort(all_dist, 0, n);
			//prln(all_dist[n - 1]);
		    total_points = input[n];
		    restriction_point[1] = all_dist[n - 1];
		    PDP(n - 2, 0);
		    Arrays.sort(restriction_point, 0, points_taken);
		    for(i = 0; i < total_points; i++)
		    {
		        for(j = i+1; j < total_points; j++)
		        {
		            output[k] = Math.abs(restriction_point[i] - restriction_point[j]);
		            k++;
		        }
		    }
		    Arrays.sort(output, 0, k);
		    for(i = 0; i < k; i++)
		    {
		        if(output[i] != all_dist[i])
		        {
		            no = 1;
		        }
		    }
		    //if(no != 0) prln("Verdict: Wrong Answer\n");
		    //else prln("Verdict: Accepted\n");
		    //prln("Total number of points:" +  input[n]);
		    //prln(points_taken);
                    Ret+="Case "+cas+":";
                    cas++;
		    for(i = 0; i < points_taken; i++)
		    {
		        if(i != 0) prln("  ");
		        prln(restriction_point[i]);
                        Ret+=restriction_point[i]+" ";
		    }
		    //prln("\n\n\n");
		    clear();
                    test--;
                    Ret+="\n";
		}
        }catch(FileNotFoundException ex)
           {

        }catch(IOException ex)
           {
            
        }
                return Ret;
    }

}
