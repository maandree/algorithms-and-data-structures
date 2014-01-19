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
 * Class for checking whether an array is sorted and in which order it is sorted
 */
public class Sorted
{
    /**
     * The array is not sorted
     */
    public static final int UNORDERED = 0;
    
    /**
     * The array is sorted in ascending (normal) order
     */
    public static final int ASCENDING_ORDER = 1;
    
    /**
     * The array is sorted in descending (reverse normal) order
     */
    public static final int DESCENDING_ORDER = 2;
    
    /**
     * All elements in the array are mutually equal or the array contains less than two elements.<br>
     * This is the bitwise OR of {@link ASCENDING_ORDER} and {@link DESCENDING_ORDER}.
     */
    public static final int FLAT = 3;
    
    
    
£>for T in boolean char byte short int long float double T T+; do . src/comparable
    /**
     * Gets whether an array is sorted and in which order it is sorted
     *
     * @param   items  The items to check if they are ordered
     * @return         {@link #UNORDERED}, {@link #ASCENDING_ORDER}, {@link #DESCENDING_ORDER} or {@link #FLAT}
     */
    public static £(fun "int" "isSorted" "${T}[] items")
    {
	if (items.length == 0)
	    return FLAT;
        
	£{T} last = items[0], cur;
	int order = FLAT;
        
	for (int i = 1, n = items.length; i < n; i++)
	{
	    cur = items[i];
	    if (£(less "last" "cur"))
	    {
		if (order == FLAT)
		    order = ASCENDING_ORDER;
		else if (order == DESCENDING_ORDER)
		    return UNORDERED;
	    }
	    else if (£(greater "last" "cur"))
		if (order == FLAT)
		    order = DESCENDING_ORDER;
		else if (order == ASCENDING_ORDER)
		    return UNORDERED;
	    last = cur;
	}
        
	return order;
    }
£>done
}

