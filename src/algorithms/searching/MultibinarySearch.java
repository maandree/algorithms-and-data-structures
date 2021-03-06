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
 * Multibinary search class. Multibinary search runs with time complexity
 * 𝓞(log n + m) and memory complexity 𝓞(log m), where n is the number of
 * elements in the array to be searched, and m is the number of items for
 * which to search. Multibinary search locates multiple items in an array
 * effectively, the items to locate must however be unique and stored in
 * a sorted list. The algorithm only works with sorted arrays. Identity
 * search is not possible, only equality search. Null elements are not
 * allowed, unless the specified compator allows it.
 * 
 * This algorithm was devised by Mattias Andrée in February of 2013.
 */
public class MultibinarySearch
{
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
     * Find the indices of multiple items in a list, with time
     * complexity 𝓞(log n + m) and memory complexity 𝓞(log m)
     * 
     * @param   items  Sorted list of unique items for which to search, the number
     *                 of elements is named ‘m’ in the complexity analysis
     * @param   array  Sorted list in which to search, the number of elements
     *                 is named ‘n’ in the complexity analysis
     * @param   order  The lists' (both) element order
     * @param   mode   The search mode
     * @return         Two arrays of integer arrays, the 0:th being the indices
     *                 of items, the 1:th being their positions. That is,
     *                 two separate arrays, not and array of pairs. The expected
     *                 position is returned inverted if it was not found, the
     *                 position it whould have upon be inserted, otherwise the
     *                 position is returned as an index if the mode does not
     *                 specify anything else.
     */
    public static £(fun "long[][]" indexOf "${T}[] items, ${Tarray} array, SortOrder order, SearchMode mode")
    {
	return indexOf(items, array, order, mode, 0, array.length - 1£{Targ_name});
    }
    
    /**
     * Find the indices of multiple items in a list, with time
     * complexity 𝓞(log n + m) and memory complexity 𝓞(log m)
     * 
     * @param   items  Sorted list of unique items for which to search, the number
     *                 of elements is named ‘m’ in the complexity analysis
     * @param   array  Sorted list in which to search, the number of elements
     *                 is named ‘n’ in the complexity analysis
     * @param   order  The lists' (both) element order
     * @param   mode   The search mode
     * @param   start  The index of the first position to search in the array
     * @return         Two arrays of integer arrays, the 0:th being the indices
     *                 of items, the 1:th being their positions. That is,
     *                 two separate arrays, not and array of pairs. The expected
     *                 position is returned inverted if it was not found, the
     *                 position it whould have upon be inserted, otherwise the
     *                 position is returned as an index if the mode does not
     *                 specify anything else.
     */
    public static £(fun "long[][]" indexOf "${T}[] items, ${Tarray} array, SortOrder order, SearchMode mode, int start")
    {
	return indexOf(items, array, order, mode, start, array.length - 1£{Targ_name});
    }
    
    /**
     * Find the indices of multiple items in a list, with time
     * complexity 𝓞(log n + m) and memory complexity 𝓞(log m)
     * 
     * @param   items  Sorted list of unique items for which to search, the number
     *                 of elements is named ‘m’ in the complexity analysis
     * @param   array  Sorted list in which to search, the number of elements
     *                 is named ‘n’ in the complexity analysis
     * @param   order  The lists' (both) element order
     * @param   mode   The search mode
     * @param   start  The index of the first position to search in the array
     * @param   end    The index after the last position to search in the array
     * @return         Two arrays of integer arrays, the 0:th being the indices
     *                 of items, the 1:th being their positions. That is,
     *                 two separate arrays, not and array of pairs. The expected
     *                 position is returned inverted if it was not found, the
     *                 position it whould have upon be inserted, otherwise the
     *                 position is returned as an index if the mode does not
     *                 specify anything else.
     */
    public static £(fun "long[][]" indexOf "${T}[] items, ${Tarray} array, SortOrder order, SearchMode mode, int start, int end")
    {
	BinarySearch.SearchMode mode_ =
	          mode == SearchMode.FIND_ANY   ? BinarySearch.SearchMode.FIND_ANY
	        : mode == SearchMode.FIND_FIRST ? BinarySearch.SearchMode.FIND_FIRST
	        : mode == SearchMode.FIND_LAST  ? BinarySearch.SearchMode.FIND_LAST
	                                        : BinarySearch.SearchMode.FIND_FIRST_AND_LAST;
	BinarySearch.SortOrder order_ = order == SortOrder.ASCENDING
	        ? BinarySearch.SortOrder.ASCENDING
	        : BinarySearch.SortOrder.DESCENDING;
	
	int m = items.length, lb_m = 1;
	long[][] rc = new long[2][m];
	
	if (m == 0)
	    return rc;
	
	if ((m & 0xFFFF0000) != 0)  { lb_m |= 16;  m >>= 16; }
	if ((m & 0x0000FF00) != 0)  { lb_m |=  8;  m >>=  8; }
	if ((m & 0x000000F0) != 0)  { lb_m |=  4;  m >>=  4; }
	if ((m & 0x0000000C) != 0)  { lb_m |=  2;  m >>=  2; }
	if ((m & 0x00000002) != 0)  { lb_m +=  1; }
	
        int[][] minomax = new int[4][lb_m];
	m = items.length - 1;
	
	int rc_i = 0;
	int mm_i = 0;
	int imin, imax, amin = 0, amax = 0, lastimax, lastamax;
	
	minomax[0][mm_i] = 0;
	minomax[1][mm_i] = m;
	minomax[2][mm_i] = start;
	minomax[3][mm_i++] = end - 1;
	
£>bin_search="BinarySearch.indexOf(items[imax], array, order_, mode_, amin, amax${Targ_name})"
	
	while (mm_i-- > 0)
	{
	    imin = minomax[0][mm_i];
	    imax = minomax[1][mm_i];
	    amin = minomax[2][mm_i];
	    amax = minomax[3][mm_i];
	    
	    while (imax != imin)
	    {
		lastimax = imax;
		lastamax = amax;
		rc[0][rc_i] = imax = imin + ((imax - imin) >>> 1);
		amax = (int)(rc[1][rc_i++] = £{bin_search});
		if (amax < 0)
		    amax = ~amax;
		/* This is possible to do, but you will probably lose performance:
		else if (mode == SearchMode.FIND_FIRST_AND_LAST)
		    amax = (int)(rc[1][rc_i - 1] >> 32L);
		*/
		
		minomax[0][mm_i] = imax + 1;
		minomax[1][mm_i] = lastimax;
		minomax[2][mm_i] = amax + 1;
		minomax[3][mm_i++] = lastamax;
	    }
	}
	
	imax = m;
	amax = (int)(£{bin_search});
	rc[0][rc_i] = imax;
	rc[1][rc_i++] = amax;
	
	return rc;
    }
£>done
}

