
/*
Animesh Fatehpuria (animeshf)
USACO Problem Censoring
 */
import java.util.*;
import java.io.*;
public class KnuthMorrisPratt
{
    static int[]prefix;
    static int patternLength;
    static int sentenceLength;
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("censor.in"));
        PrintWriter pw = new PrintWriter( new FileWriter("censor.out"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sen=br.readLine();
        String pat=br.readLine();
        StringBuilder s = new StringBuilder(sen);
        patternLength=pat.length();
        sentenceLength=sen.length();
        prefix = new int[patternLength];
        fail(pat);
        int i = KnuthMorrisPratt(s,pat);
        while (i != -1)
        {
            s.delete(i, i + patternLength);
            sentenceLength-=patternLength;
            i=KnuthMorrisPratt(s,pat);
        }
        //System.out.println(s);
        pw.println(s);
        pw.close();
    }

    static int KnuthMorrisPratt(StringBuilder text, String pat)
    {
        int pos = posMatch(text, pat);
        return pos;
    }

    static void fail(String pat)
    {
        int n = patternLength;
        prefix[0] = 0;
        int i=1;
        int len=0;
        while (i < n)
        {
            if (pat.charAt(i) == pat.charAt(len))
            {
                len++;
                prefix[i] = len;
                i++;
            }
            else 
            {
                if (len != 0)
                {
                    len = prefix[len-1];
                }
                else 
                {
                    prefix[i] = 0;
                    i++;
                }
            }
        }
    }

    static int posMatch(StringBuilder text, String pat)
    {
        int i = 0, j = 0;
        int lens = sentenceLength;
        int lenp = patternLength;
        while (i < lens)
        {
            if (pat.charAt(j) == text.charAt(i))
            {
                j++;
                i++;
            }
            if (j == lenp)
            {
                return i-j;
            }
            else if (i < lens && pat.charAt(j) != text.charAt(i))
            {
                if (j != 0)
                    j = prefix[j-1];
                else
                    i = i+1;
            }
        }
        return -1;
    }
}
