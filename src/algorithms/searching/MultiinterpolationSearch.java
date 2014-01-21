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
 * Multiinterpolation search class.This algorithm is exactly to
 * interpolation search as multibinary search is to binary search.
 * 
 * This algorithm was devised by Mattias Andrée in January of 2014.
 */
public class MultiinterpolationSearch
{
£>for T in char byte short int long float double BigInteger BigDecimal; do . src/comparable
    /**
     * Find the indices of multiple items in a list
     * 
     * @param   items  Sorted list of unique items for which to search
     * @param   array  Sorted list in which to search
     * @return         Two arrays of integer arrays, the 0:th being the indices
     *                 of items, the 1:th being their positions. That is,
     *                 two separate arrays, not and array of pairs. The expected
     *                 position is returned inverted if it was not found, the
     *                 position it whould have upon be inserted, otherwise the
     *                 position is returned as an index.
     */
    public static £(fun "long[][]" indexOf "${T}[] items, ${Tarray} array")
    {
	return indexOf(items, array, 0, array.length - 1£{Targ_name});
    }
    
    /**
     * Find the indices of multiple items in a list
     * 
     * @param   items  Sorted list of unique items for which to search
     * @param   array  Sorted list in which to search
     * @param   start  The index of the first position to search in the array
     * @return         Two arrays of integer arrays, the 0:th being the indices
     *                 of items, the 1:th being their positions. That is,
     *                 two separate arrays, not and array of pairs. The expected
     *                 position is returned inverted if it was not found, the
     *                 position it whould have upon be inserted, otherwise the
     *                 position is returned as an index.
     */
    public static £(fun "long[][]" indexOf "${T}[] items, ${Tarray} array, int start")
    {
	return indexOf(items, array, start, array.length - 1£{Targ_name});
    }
    
    /**
     * Find the indices of multiple items in a list
     * 
     * @param   items  Sorted list of unique items for which to search
     * @param   array  Sorted list in which to search
     * @param   start  The index of the first position to search in the array
     * @param   end    The index after the last position to search in the array
     * @return         Two arrays of integer arrays, the 0:th being the indices
     *                 of items, the 1:th being their positions. That is,
     *                 two separate arrays, not and array of pairs. The expected
     *                 position is returned inverted if it was not found, the
     *                 position it whould have upon be inserted, otherwise the
     *                 position is returned as an index.
     */
    public static £(fun "long[][]" indexOf "${T}[] items, ${Tarray} array, int start, int end")
    {
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
	
£>interpol_search="InterpolationSearch.indexOf(items[imax], array, amin, amax${Targ_name})"
	
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
		rc[1][rc_i++] = amax = (int)(£{interpol_search});
		if (amax < 0)
		    amax = ~amax;
		
		minomax[0][mm_i] = imax + 1;
		minomax[1][mm_i] = lastimax;
		minomax[2][mm_i] = amax + 1;
		minomax[3][mm_i++] = lastamax;
	    }
	}
	
	imax = m;
	amax = (int)(£{interpol_search});
	rc[0][rc_i] = imax;
	rc[1][rc_i++] = amax;
	
	return rc;
    }
£>done
}

