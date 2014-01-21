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


/**
 * Binary search class. Binary search runs in logarithmic time, constant memory,
 * and requires the list to be sorted. Binary search often out preforms linear
 * search, interpolation sort however often out preforms binary search for lists
 * with smooth distribution. Identity search is not possible, only equality
 * search. Null elements are not allowed, unless the specified compator allows it.
 */
public class BinarySearch
{
    /**
     * All elements in the array is the searched for item
     */
    public static final int EVERY_ELEMENT = -1;
    
    /**
     * Item was not on the edges, but may be inside
     * Values lower than this value indicate that the value does
     * not exist.
     */
    public static final int MAYBE = -2;
    
    /**
     * The item's value is smaller than the smallest in the array.
     * This value and lower values indicate that the value does
     * not exist.
     */
    public static final int TOO_SMALL = -3;
    
    /**
     * The item's value is larger than the largest in the array
     */
    public static final int TOO_LARGE = -4;
    
    
    
    /**
     * List sort order
     */
    public static enum SortOrder
    {
        /**
         * Bigger index, bigger value
         */
        ASCENDING,
        
        /**
         * Bigger index, smaller value
         */
        DESCENDING,
        
    }
    
    /**
     * List sort order
     */
    public static enum SearchMode
    {
        /**
         * Look for the index of the easiest to find occurence
         */
        FIND_ANY,
        
        /**
         * Look for the index of the first occurence
         */
        FIND_FIRST,
        
        /**
         * Look for the index of the last occurence
         */
        FIND_LAST,
        
        /**
         * Look for both the index of the fist occurence and of the last occurence.<br>
         * The returned value will be {@code (LAST << 32) | FIRST}.
         */
        FIND_FIRST_AND_LAST,
        
    }
    
    
    
£>for T in boolean char byte short int long float double T T++; do . src/comparable
    /**
     * Gets whether an item may be contained by a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   order  The list's element order
     * @return         {@link #MAYBE}, {@link #TOO_SMALL}, {@link #TOO_LARGE}, {@link #EVERY_ELEMENT}
     *                 or the index of a(!) found item [first or last position]
     */
    public static £(fun "int" contains "${T} item, ${Tarray} array, SortOrder order")
    {
	return contains(item, array, order, 0, array.length£{Targ_name});
    }
    
    /**
     * Gets whether an item may be contained by a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   order  The list's element order
     * @param   start  The index of the first position to search in the array
     * @return         {@link #MAYBE}, {@link #TOO_SMALL}, {@link #TOO_LARGE}, {@link #EVERY_ELEMENT}
     *                 or the index of a(!) found item [first or last position]
     */
    public static £(fun "int" contains "${T} item, ${Tarray} array, SortOrder order, int start")
    {
	return contains(item, array, order, start, array.length£{Targ_name});
    }
    
    /**
     * Gets whether an item may be contained by a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   order  The list's element order
     * @param   start  The index of the first position to search in the array
     * @param   end    The index after the last position to search in the array
     * @return         {@link #MAYBE}, {@link #TOO_SMALL}, {@link #TOO_LARGE}, {@link #EVERY_ELEMENT}
     *                 or the index of a(!) found item [first or last position]
     */
    public static £(fun "int" contains "${T} item, ${Tarray} array, SortOrder order, int start, int end")
    {
	int low = £(cmp "array[start]" "item");
        
	if (order == SortOrder.ASCENDING)
	{
	    if (low > 0)
		return TOO_SMALL;
	    
	    int high = £(cmp "array[end - 1]" "item");
	    
	    if (low == 0)
		return high == 0 ? EVERY_ELEMENT : start;
	    
	    return high == 0 ? end - 1 : high < 0 ? TOO_LARGE : MAYBE;
	}
        
	{
	    if (low < 0)
		return TOO_SMALL;
            
	    int high = £(cmp "array[end - 1]" "item");
	    
	    if (low == 0)
		return high == 0 ? EVERY_ELEMENT : start;
	    
	    return high == 0 ? end - 1 : high > 0 ? TOO_LARGE : MAYBE;
	}
    }
    
    
    /**
     * Finds the first, last or any occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   order  The list's element order
     * @param   mode   The search mode
     * @return         The index of the found item, if not mode does not say otherwise, or, if not
     *                 found, the bitwise negation of the position to which it should be inserted
     */
    public static £(fun "long" indexOf "${T} item, ${Tarray} array, SortOrder order, SearchMode mode")
    {
	return indexOf(item, array, order, mode, 0, array.length£{Targ_name});
    }
    
    /**
     * Finds the first, last or any occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   order  The list's element order
     * @param   mode   The search mode
     * @param   start  The index of the first position to search in the array
     * @return         The index of the found item, if not mode does not say otherwise, or, if not
     *                 found, the bitwise negation of the position to which it should be inserted
     */
    public static £(fun "long" indexOf "${T} item, ${Tarray} array, SortOrder order, SearchMode mode, int start")
    {
	return indexOf(item, array, order, mode, start, array.length£{Targ_name});
    }
    
    /**
     * Finds the first, last or any occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   order  The list's element order
     * @param   mode   The search mode
     * @param   start  The index of the first position to search in the array
     * @param   end    The index after the last position to search in the array
     * @return         The index of the found item, if not mode does not say otherwise, or, if not
     *                 found, the bitwise negation of the position to which it should be inserted
     */
    public static £(fun "long" indexOf "${T} item, ${Tarray} array, SortOrder order, SearchMode mode, int start, int end")
    {
        £{Telement} x;
        
        int min = start, mid, rc = -1;
        int max = end - 1;
	
£>function f
£>{
	    if (mode == SearchMode.£{1})
		for (;;)
		{
		    if (item == (x = array[mid = (min + max) >>> 1]))
			£{2};
		    
		    /* NB! (x R item), instead of (item R x) */
		    if (£(${3} x item))  min = mid + 1;
		    else                 max = mid - 1;
		    
		    if (min > max)
			return £{4};
		}
£>}

£>function p
£>{
	    for (;;)
	    {
		if (item == (x = array[mid = (min + max) >>> 1]))
		{
		    rc = mid;
£>if [ $3 = 0 ]; then
		    if (easyMin == -1)
		    {
			easyMax = mid - 1;
			easyMin = min;
		    }
£>fi
		}
		
		/* NB! (x R item), instead of (item R x) */
		if (£(${1} x item))  min = mid + 1;
		else                 max = mid - 1;
		
		if (min > max)
		{
		    if (rc < 0)
			return ~((long)min);
		    £{2} = rc;
		    break;
		}
	    }

£>}
	
£>function _
£>{
        {
£>f  FIND_ANY    'return (long)mid'  "${1}"   '~((long)min)'
£>f  FIND_FIRST  'rc = mid'          "${1}"   'rc < 0 ? ~((long)min) : (long)rc'
£>f  FIND_LAST   'rc = mid'          "${1}="  'rc < 0 ? ~((long)min) : (long)rc'	    
	    
            int easyMin = -1, easyMax = -1, first, last;
£>p  "${1}"  first  0	    
	    min = easyMin;
	    max = easyMax;
£>p  "${1}="  last  1
            return (((long)last) << 32) | (long)first;
        }
£>}
	
        if (order == SortOrder.ASCENDING)
£>_  'less'
£>_  'greater'
    }
£>done
}

