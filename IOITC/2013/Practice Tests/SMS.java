/**
That to create distinct words by inserting spaces in between you need to multiply the number of ways to create distinct words for each consecutive segment of chars.

e.g: 

ioitc: 4446664448222 <- this is the shortest possible number of keypresses

lets seperate by characters

444 666 444 8 222

if you think about it, the number of distinct words for the whole word is basically multiplication of the number of words for the subwords. 

ok, so the keypresses spiral.
because 4 is 3 chars only, and 7 is 4 chars .
so 4 = 4444, and 7 = 77777

4 => 4
44 => 4, 4_4
444 => 444, 4_44, 44_4, 4_4_4
4444 => 4444, 4_444, 44_44, 444_4, 4_4_44, 4_44_4, 44_4_4, 4_4_4_4
now, 44444 => remeber 4_4444 = aa, and 4444_4 = aa, so we need to deduct 1 type here because both are same.
so, we need to find a dp recurrence and the answer is not just 2^i

dp[0] = 0
dp[1] = 1
dp[2] = 2
dp[3] = 1 + dp[2] + dp[1] + dp[0] 
whats dp[x] = number of distinct words for a sequence of length x of same characters ? ok
dp[i] = 1 + dp[i - 1] + dp[i - 2] + dp[i - 3]

dp_3[i] = number of distict words for keys with 3 characters of length i
dp_4[i] = number of distict words for keys with 4 characters of length i
4 chars of length 'i' ?
example key 7 - pqrs
you need to find the contiguous characters and group them seperately.
find out the length of the group and the character. then for each of this groups, multiply the dp_3[l] or dp_4[l] according to the character of the group

dp_3[i] = 1 + dp_3[i - 1] + dp_3[i - 2] + dp_3[i - 3]
dp_4[i] = 1 + dp_4[i - 1] + dp_4[i - 2] + dp_4[i - 3] + dp_4[i - 4]
 **/

import java.io.*;
import java.util.*;
class SMS
{
    static int MOD  = 100000007;
    static int keys_3[] = new int[1000001];
    static int keys_4[] = new int[1000001];
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String seq[] = { "2", "22", "222", "3", "33", "333", "4", "44", "444", "5", "55", "555", "6", "66", "666", "7", "77", "777", "7777", "8", "88", "888", "9", "99", "999", "9999"};
        int num_keys[] = {0, 0, 3, 3, 3, 3, 3, 4, 3, 4};

        keys_3[0] = 0; keys_3[1] = 1; keys_3[2] = 2;
        for(int i = 3; i <= 1000000; i++) 
            keys_3[i] = (1 + keys_3[i - 1] + keys_3[i - 2] + keys_3[i - 3]) % MOD;
        keys_4[0] = 0; keys_4[1] = 1; keys_4[2] = 2; keys_4[3] = 4; 
        for(int i = 4; i <= 1000000; i++) 
            keys_4[i] = (1 + keys_4[i - 1] + keys_4[i - 2] + keys_4[i - 3] + keys_4[i - 4]) % MOD;

        while(T-->0)
        {
            String s = br.readLine();
            int N = s.length();
            String keys = "";
            for(int i=0;i<N;i++)
                keys += seq[s.charAt(i) - 'a'];
            ArrayList<pair> letters = new ArrayList<pair>();
            int i = 1; char prev = keys.charAt(0);
            int curr_length = 1;
            while(i < keys.length()) 
            {
                if(keys.charAt(i) == prev)
                {
                    curr_length++;
                    i++;
                }
                else
                {
                    letters.add(new pair(prev, curr_length));
                    curr_length = 1;
                    prev = keys.charAt(i);
                    i++;
                }
            }           
            if(curr_length > 0) 
                letters.add(new pair(prev, curr_length));
            long ans = 1;
            for(pair it: letters) 
                ans = (ans * (num_keys[it.first - '0'] == 3 ? keys_3[it.second] : keys_4[it.second])) % MOD;
            System.out.println(ans);
        }
    }
}
class pair
{
    char first ; int second;
    public pair(char f , int s)
    {
        first = f;
        second = s;
    }
}
