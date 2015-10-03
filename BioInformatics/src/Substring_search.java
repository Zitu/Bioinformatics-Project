
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zitu
 */
public class Substring_search {

    String fl="";
    public Substring_search(String s) {
        fl=s;
    }
    public static final int max_size = 1000005;
	public static int n,m,len, length_of_find, length_of_input;
	public static int[] f = new int[max_size];
	public static String input_string, string_to_find;
	static void prln(Object anyObject)
	{
	     System.out.println(anyObject);
	}
	public static void build_failure_function()
	{
	 int i,j;
	 f[0]=f[1]=0;
	 for(i = 2; i <= m; i++)
	 {
		 j = f[i - 1];
		 while(true)
		 {
			 if(string_to_find.charAt(j) == string_to_find.charAt(i - 1))
			 {
				 f[i] = j + 1;
				 break;
			 }
			 if(j == 0)
			 {
				 f[i] = 0;
				 break;
			 }
			 j = f[j];
		 }
	 }
	}
    String ret_sub()
    {
        String ret="";
        int test, cs = 1, i, j, cnt,cas=0;
        File file=new File(fl);
        try{
            BufferedReader bf=new BufferedReader(new FileReader(file));
		test = Integer.valueOf(bf.readLine());
		while(test != 0)
		{
                    cas++;
			input_string = bf.readLine().toString();//in.next();//s
			string_to_find = bf.readLine().toString();//in.next();//p
			length_of_find = string_to_find.length();//m
			length_of_input = input_string.length();//n
			build_failure_function();
			i = j = cnt = 0;
			while(j < length_of_input)
			{
				if(string_to_find.charAt(i) == input_string.charAt(j))
				{
					i++;
					j++;
					if(i == length_of_find)
					{
						cnt++;
						i = f[i];
					}
				}
				else if(i > 0)
				{
					i = f[i];
				}
				else {
					j++;
				}
			}
                        ret+="Case"+cas+":"+cnt+"\n";
			prln(cnt);
                        test--;
		}
        }catch(FileNotFoundException ex)
        {

        }catch(IOException e){
            
        }
        return ret;
    }



}
