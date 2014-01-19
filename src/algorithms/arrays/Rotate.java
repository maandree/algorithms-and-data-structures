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

import java.util.*;


/**
 * Class for rotating arrays
 */
public class Rotate
{
    /*
     *  There are many thing you can change in this class to adapt it, for example,
     *  if you want in-place rotation, which probabily is a bit slower (even though
     *  it is less naïve), you can reverse the array, split it at the index 'steps',
     *  and reverse those partitions and the concatinate back again, of cause, you
     *  would only reverse parts of the array, not split and concatinate it.
     *  You could also reverse the array while rotating it, either reverse the
     *  input or the output.
     */
    
    
£<for T in boolean char byte short int long float double T; do
  P= O="$T" cast=
£>[ $T = T ] && P="<T> " O=Object cast="(T[])"
    /**
     * Rotates an array
     *
     * @param   items  The array to rotate
     * @param   steps  The number of steps to higher index to rotate elements
     * @return         The array rotated
     */
£>[ $T = T ] &&
    @SuppressWarnings("unchecked")
    public static £{P}£{T}[] rotateClone(£{T}[] items, int steps)
    {
	£{O}[] rc = new £{O}[items.length];
	
	int n = items.length;
	if ((n & -n) == n) /* power of 2 */
	    for (int i = 0, m = n - 1; i < n; i++)
		rc[(steps + i) & m] = items[i];
	else
	    for (int i = 0; i < n; i++)
		rc[(steps + i) % n] = items[i];
	
	return £{cast}rc;
    }
£>done
}

