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
 * Class for reversing arrays
 */
public class Reverse
{
£>for T in boolean char byte short int long; do
    /**
     * Reverses an array
     *
     * @param  items  The array to reverse
     */
    public static void reverse(£{T}[] items)
    {
        for (int i = 0, j = items.length - 1; i < j; i++, j--)
	{
	    items[j] ^= items[i];
	    items[i] ^= items[j];
	    items[j] ^= items[i];
	}
    }
£>done

£>for T in float double Object; do
    /**
     * Reverses an array
     *
     * @param  items  The array to reverse
     */
    public static void reverse(£{T}[] items)
    {
        £{T} temp;
        for (int i = 0, j = items.length - 1; i < j; i++, j--)
	{
	    temp = items[i];
	    items[i] = items[j];
	    items[j] = temp;
	}
    }
£>done

£>for T in boolean char byte short int long float double Object; do
    /**
     * Make a clone of an array, but make it reversed
     *
     * @param   items  The array to reverse
     * @return         The array reversed
     */
    public static £{T}[] reverseClone(£{T}[] items)
    {
        £{T}[] rc = new £{T}[items.length];
        for (int i = 0, n = items.length - 1; i <= n; i++)
            rc[i] = items[n - i];
        return rc;
    }
£>done
}

