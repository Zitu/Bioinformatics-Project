
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zitu
 */
public class Sequence {
    String fl;
    String ret="";
    static int n,req[]=new int[20],cal[][]=new int[20][20],prs;
    static int dp[][]=new int[70000][20],vst[][]=new int[70000][20];
    //static List<String> st = new ArrayList<String>();
    static String st[]=new String[20];
    Sequence( String nm)
    {
        fl=nm;
        System.out.println(fl);
    }
    String return_sequence()
    {
        int t,i,j;
        //Scanner in=new Scanner(System.in);
        File file=new File(fl);
        try {
            //  BufferedReader bf = new BufferedReader(new FileReader(file));
            BufferedReader bfr = new BufferedReader(new FileReader(file));

                t=Integer.valueOf(bfr.readLine());

            while(t-->0)
            {
                n=Integer.valueOf(bfr.readLine());;
                st[0]="";
                req[0]=1;
                for(i=1;i<=n;i++)
                {
                    st[i]=bfr.readLine().toString();//in.next();
                    req[i]=1;
                }
                n++;
                Arrays.sort(st,0,n,new Comparator<String>() {

                    @Override
                    public int compare(String a,String b)
                    {
                        return a.length()-b.length();
                    }
                } );

                for(i=0;i<n;i++)
                for(j=0;j<i;j++)
                    if(req[j]==1 && contain(i,j))
                        req[j]=0;
                req[0]=1;
                for(i=1,j=1;i<n;i++)
                {
                    if(req[i]==1)
                        st[j++]=st[i];
                }
                n=j;

                pre();
                prs++;
                rec(1,0);
                ret+="Case "+prs+": ";
                print(1,0);
                ret+="\n";
                System.out.println();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sequence.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex)
        {
            
        }
        
        return ret;
    }
    public static boolean contain(int a, int b) {

        String sa=st[a],sb=st[b];
        int l=sb.length();
    int ll =sa.length();
    int i,j;
    for(i=0;i<ll;i++)
    {
            for(j=0;j<l && i+j<ll;j++)
            if(sa.charAt(i+j)!=sb.charAt(j))
            break;

            if(j==l)
            return true;
    }
        return false;
    }

    private static void pre() {
    int l1,l2,i,j,k,l,flag;


    for(i=0;i<n;i++)
    {
            String sti=st[i];
            l1=sti.length();

            for(j=0;j<n;j++)
            {
                String stj=st[j];
                cal[i][j]=0;
                l2=stj.length();
                flag=1;
                for(k=0;k<l1 && flag==1;k++)
                {
                    for(l=0;l<l2;l++)
                    {
                        if(sti.charAt(k+l)!=stj.charAt(l))
                        break;
                        if(k+l==l1-1)
                        {
                            cal[i][j]=l+1;
                            flag=0;
                            break;
                        }
                    }
                }
            }
    }
    }

    private static int rec(int vs, int pre) {
    if(vs==(1<<n)-1)
    return 0;

    if(vst[vs][pre]==prs)
    return dp[vs][pre];

    vst[vs][pre]=prs;

    int i,mn=0,flag=1,d;
        String sti;

    for(i=0;i<n;i++)
    {
            sti=st[i];
        if(((vs>>i) & 1)==0)
        {
            int l=sti.length()-cal[pre][i];
            if(flag==1)
            {
                flag=0;
                mn=rec(vs|(1<<i),i)+l;
            }
            else
            {
                d=rec(vs|(1<<i),i)+l;
                if(d<mn)
                mn=d;
            }
        }
    }
    return dp[vs][pre]=mn;
    }

    private  void print(int vs, int pre) {
    if(vs==(1<<n)-1)
    return;

    int i,ans=rec(vs,pre),id=0,j,flag=1;
    String str,sss="";

    for(i=0;i<n;i++)
    {
            str="";
            String sti=st[i];
        if(((vs>>i)&1)==0)
        {
            if(ans==rec(vs|(1<<i),i)+sti.length()-cal[pre][i])
            {
                for(j=cal[pre][i];j<sti.length();j++)
                str+=sti.charAt(j);

                if(flag==1 || sss.compareTo(str)>0)
                                {
                                    sss=str;
                                    flag=0;
                                    id=i;
                                }
            }
        }
    }
    ret+=sss;
    System.out.print(sss);
    print(vs|(1<<id),id);
    }

}
