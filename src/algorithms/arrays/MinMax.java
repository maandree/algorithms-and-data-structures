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


/**
 * Class for finding the minimum and maximum values in one iteration
 */
public class MinMax
{
£>for T in boolean char byte short int long float double T T+; do . src/comparable
    /**
     * Find the minimum and maximum values in an array
     * 
     * @param   items  The items over to search
     * @return         An array with two elements, at index 0, the minimum value, and at position 1, the maximum value
     */
£>[ $T = T ] &&
    @SuppressWarnings("unchecked")
    public static £(fun "${T}[]" getBounds "${T}[] items")
    {
        £{T} max, min, cur;
        max = min = items[0];
	
        for (int i = 1, n = items.length; i < n; i++)
            if (£(greater "cur = items[i]" "max"))
                max = cur;
            else if (£(less "cur" "min"))
                min = cur;
	
	return £(T-array min max);
    }
£>done
}

