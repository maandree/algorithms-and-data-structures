/**
 * Copyright © 2014  Mattias Andrée (maandree@member.fsf.org)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package algorithms.arrays;

import java.util.Random;


/**
 * Bias free array shuffling class aiming to imitate card deck shuffling as preformed by humans
 */
public class CardShuffle
{
    /**
     * Calculates the floor of the binary logarithm of an integer
     * 
     * @param   x  The integer
     * @return     The floored binary logarithm
     */
    private static int lb(int x)
    {
	int rc = 0;
	if ((x & 0xFFFF0000) != 0)  { rc += 16;  x >>= 16; }
	if ((x & 0x0000FF00) != 0)  { rc +=  8;  x >>=  8; }
	if ((x & 0x000000F0) != 0)  { rc +=  4;  x >>=  4; }
	if ((x & 0x0000000C) != 0)  { rc +=  2;  x >>=  2; }
	if ((x & 0x00000002) != 0)    rc +=  1;
	return rc;
    }
    
    
£>for T in boolean char byte short int long float double Object; do
    /**
     * Shuffles an array
     *
     * @param  array   The array to shuffle
     * @param  random  The random generator to use
     */
    public static void shuffle(£{T}[] array, Random random)
    {
        int n;
        int min = lb(n = array.length);
        if (n < 2)
            return;
        
        £{T}[] left = new £{T}[n];
        £{T}[] right = new £{T}[n];
	
        int k = 0;
        while ((k++ < min) || (random.nextInt(min) > 0))
	{
	    int lcnt = 0;
	    int rcnt = 0;
	    
	    for (int i = 0; i < n; i++)
		if (random.nextInt(2) == 0)
		    left[lcnt++] = array[i];
		else
		    right[rcnt++] = array[i];
            
	    int lptr = 0;
	    int rptr = 0;
	    
	    for (int i = 0; i < n; i++)
		array[i] = (lptr == lcnt) || ((rptr != rcnt) && (random.nextInt(2) == 0)) 
		    ? right[rptr++] : left[lptr++];
	}
    }
£>done
}

