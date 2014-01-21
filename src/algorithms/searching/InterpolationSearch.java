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
package algorithms.searching;

import java.util.*;
import java.math.*;


/**
 * Interpolation search class. Interpolation search runs in logarithmic
 * time, in average, and linear time in worst case, which will occur with
 * rough distrubitions, constant memory, and requires the list to be sorted
 * and numerical. Interpolation search often out preforms binary search for
 * smoothing distributions. Identity search is not possible, only equality
 * search.<br>
 * The list must be sorted in ascending order.
 */
public class InterpolationSearch
{
£>for T in char byte short int long float double; do
    /**
     * Find the easiest to find occurance of an item in a list
     *
     * @param   item   The item to find
     * @param   array  The list in which to search
     * @return         The index of the easiest to find occurance of the item. The bitwise
     *                 negation of the position it insert it in is returned if it was not found.
     */
    public static int indexOf(£{T} item, £{T}[] array)
    {
	return indexOf(item, array, 0, array.length);
    }
    
    /**
     * Find the easiest to find occurance of an item in a list
     *
     * @param   item   The item to find
     * @param   array  The list in which to search
     * @param   start  The index of the first position to search in the array
     * @return         The index of the easiest to find occurance of the item. The bitwise
     *                 negation of the position it insert it in is returned if it was not found.
     */
    public static int indexOf(£{T} item, £{T}[] array, int start)
    {
	return indexOf(item, array, start, array.length);
    }
    
    /**
     * Find the easiest to find occurance of an item in a list
     *
     * @param   item   The item to find
     * @param   array  The list in which to search
     * @param   start  The index of the first position to search in the array
     * @param   end    The index after the last position to search in the array
     * @return         The index of the easiest to find occurance of the item. The bitwise
     *                 negation of the position it insert it in is returned if it was not found.
     */
    public static int indexOf(£{T} item, £{T}[] array, int start, int end)
    {
	£{T} low, high, at;
	int min = start, mid;
	int max = end - 1;
	
	for (;;)
	{
	    if ((low  = array[min]) > item)  break;
	    if ((high = array[max]) < item)  break;
            
	    if ((at = array[mid = min + (int)((item - low) * (max - min) / (high - low))]) < item)
		min = mid + 1;
	    else if (at > item)
		max = mid - 1;
	    else
		return mid;
	}
        
	return (array[min] == item) ? min : ~min;
    }
£>done
    
£>for T in BigInteger BigDecimal; do
    /**
     * Find the easiest to find occurance of an item in a list
     *
     * @param   item   The item to find
     * @param   array  The list in which to search
     * @return         The index of the easiest to find occurance of the item. The bitwise
     *                 negation of the position it insert it in is returned if it was not found.
     */
    public static int indexOf(£{T} item, £{T}[] array)
    {
	return indexOf(item, array, 0, array.length);
    }
    
    /**
     * Find the easiest to find occurance of an item in a list
     *
     * @param   item   The item to find
     * @param   array  The list in which to search
     * @param   start  The index of the first position to search in the array
     * @return         The index of the easiest to find occurance of the item. The bitwise
     *                 negation of the position it insert it in is returned if it was not found.
     */
    public static int indexOf(£{T} item, £{T}[] array, int start)
    {
	return indexOf(item, array, start, array.length);
    }
    
    /**
     * Find the easiest to find occurance of an item in a list
     *
     * @param   item   The item to find
     * @param   array  The list in which to search
     * @param   start  The index of the first position to search in the array
     * @param   end    The index after the last position to search in the array
     * @return         The index of the easiest to find occurance of the item. The bitwise
     *                 negation of the position it insert it in is returned if it was not found.
     */
    public static int indexOf(£{T} item, £{T}[] array, int start, int end)
    {
        £{T} low, high, at;
        int min = start, mid;
        int max = end - 1;
 
        for (;;)
	{
	    if ((low  = array[min]).compareTo(item) > 0)  break;
	    if ((high = array[max]).compareTo(item) < 0)  break;
            
	    mid = item.subtract(low).multiply(£{T}.valueOf(max - min)).divide(high.subtract(low)).intValue();
            
	    if ((at = array[mid += min]).compareTo(item) < 0)
		min = mid + 1;
	    else if (at.compareTo(item) > 0)
		max = mid - 1;
	    else
		return mid;
	}
        
        return (array[min] == item) ? min : ~min;
    }
£>done
}

