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
 * Linear search class. Linear search runs in linear time, constant memory,
 * and imposes no requirements on the list. Linear is not recommended for
 * sorted lists.
 */
public class LinearSearch
{
    /**
     * The value returned if the item was not found
     */
    public static final int NOT_FOUND = -1;
    
    
    
    /**
     * Finds the index of the first occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @return         The index of the first occurance of the item within the list, {@link #NOT_FOUND} if it was not found
     */
    public static int indexOfFirstEqual(Object item, Object[] array)
    {
        return indexOfFirstEqual(item, array, 0, -1);
    }
    
    /**
     * Finds the index of the first occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   start  Offset for the list or search range
     * @return         The index of the first occurance of the item within the list, {@link #NOT_FOUND} if it was not found
     */
    public static int indexOfFirstEqual(Object item, Object[] array, int start)
    {
        return indexOfFirstEqual(item, array, start, -1);
    }
    
    /**
     * Finds the index of the first occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   start  Offset for the list or search range
     * @param   end    End of the list or search range
     * @return         The index of the first occurance of the item within the list, {@link #NOT_FOUND} if it was not found
     */
    public static int indexOfFirstEqual(Object item, Object[] array, int start, int end)
    {
        int i = start < 0 ? (array.length - start) : start;
        int n =   end < 0 ? (array.length -   end) :   end;
        
        for (;;)
	{
	    if (i == n)
		break;
            
	    if ((array[i] == null) == (item == null))
	    {
		if (item == null)
		    return i;
		if (array[i].equals(item))
		    return i;
	    }
            
	    i++;
	}
	
        return NOT_FOUND;
    }
    
    /**
     * Finds the index of the last occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @return         The index of the last occurance of the item within the list, {@link #NOT_FOUND} if it was not found
     */
    public static int indexOfLastEqual(Object item, Object[] array)
    {
        return indexOfLastEqual(item, array, -1, 0);
    }
    
    /**
     * Finds the index of the last occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   start  End offset for the list or search range
     * @return         The index of the last occurance of the item within the list, {@link #NOT_FOUND} if it was not found
     */
    public static int indexOfLastEqual(Object item, Object[] array, int start)
    {
        return indexOfLastEqual(item, array, start, 0);
    }
    
    /**
     * Finds the index of the last occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   start  End offset for the list or search range
     * @param   end    Beginning offset for the list or search range
     * @return         The index of the last occurance of the item within the list, {@link #NOT_FOUND} if it was not found
     */
    public static int indexOfLastEqual(Object item, Object[] array, int start, int end)
    {
        int i = start < 0 ? (array.length - start) : start;
        int n =   end < 0 ? (array.length -   end) :   end;
        
        for (;;)
	{
	    if (i == n)
		break;
	    
	    if ((array[i] == null) == (item == null))
	    {
		if (item == null)
		    return i;
		if (array[i].equals(item))
		    return i;
	    }
            
	    i--;
	}
	
        return NOT_FOUND;
    }
    
£>for T in boolean char byte short int long float double Object; do
    /**
     * Finds the index of the first occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @return         The index of the first occurance of the item within the list, {@link #NOT_FOUND} if it was not found
     */
    public static int indexOfFirst(£{T} item, £{T}[] array)
    {
	return indexOfFirst(item, array, 0, -1);
    }
    
    /**
     * Finds the index of the first occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   start  Offset for the list or search range
     * @return         The index of the first occurance of the item within the list, {@link #NOT_FOUND} if it was not found
     */
    public static int indexOfFirst(£{T} item, £{T}[] array, int start)
    {
        return indexOfFirst(item, array, start, -1);
    }
    
    /**
     * Finds the index of the first occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   start  Offset for the list or search range
     * @param   end    End of the list or search range
     * @return         The index of the first occurance of the item within the list, {@link #NOT_FOUND} if it was not found
     */
    public static int indexOfFirst(£{T} item, £{T}[] array, int start, int end)
    {
        int i = start < 0 ? (array.length - start) : start;
        int n =   end < 0 ? (array.length -   end) :   end;
        
        for (;;)
	{
	    if (i == n)
		break;
            
	    if (array[i] == item)
		return i;
            
	    i++;
	}
	
        return NOT_FOUND;
    }
    
    /**
     * Finds the index of the last occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @return         The index of the last occurance of the item within the list, {@link #NOT_FOUND} if it was not found
     */
    public static int indexOfLast(£{T} item, £{T}[] array)
    {
        return indexOfLast(item, array, -1, 0);
    }
    
    /**
     * Finds the index of the last occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   start  End offset for the list or search range
     * @return         The index of the last occurance of the item within the list, {@link #NOT_FOUND} if it was not found
     */
    public static int indexOfLast(£{T} item, £{T}[] array, int start)
    {
        return indexOfLast(item, array, start, 0);
    }
    
    /**
     * Finds the index of the last occurance of an item in a list
     *
     * @param   item   The item for which to search
     * @param   array  The list in which to search
     * @param   start  End offset for the list or search range
     * @param   end    Beginning offset for the list or search range
     * @return         The index of the last occurance of the item within the list, {@link #NOT_FOUND} if it was not found
     */
    public static int indexOfLast(£{T} item, £{T}[] array, int start, int end)
    {
        int i = start < 0 ? (array.length - start) : start;
        int n =   end < 0 ? (array.length -   end) :   end;
        
        for (;;)
	{
	    if (i == n)
		break;
            
	    if (array[i] == item)
		return i;
            
	    i--;
	}
	
        return NOT_FOUND;
    }
£>done
}

