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
package algorithms.bits;


/**
 * Compute the minimum or maximum of two integers
 */
public class MinMax
{
£<for T_S in char_15 byte_7 short_15 int_31 long_63; do
  T=${T_S%_*}
£>S=${T_S#*_}
    /**
     * Compute the minimum of two integers
     * 
     * @param   a  One of the integers
     * @param   b  The other of the integers
     * @return     The minimum of the two integers
     */
    public static £{T} min(£{T} a, £{T} b)
    {
	return (£{T})(b ^ (£{T})((a ^ b) & ((a - b) >> £{S})));
	/* In C you can also do:
	 *     b ^ ((a ^ b) & -(a < b))
	 */
	/*
	 * Other version that can be faster:
	 *     (a < b) ? a : b
	 *     Assuming you now that INT_MIN <= x - y <= INT_MAX:
	 *         b + ((a - b) & ((a - b) >> (sizeof(£{T}) * CHAR_BITS - 1)))
	 */
    }
    
    /**
     * Compute the maximum of two integers
     * 
     * @param   a  One of the integers
     * @param   b  The other of the integers
     * @return     The maximum of the two integers
     */
    public static £{T} max(£{T} a, £{T} b)
    {
	return (£{T})(a ^ (£{T})((a ^ b) & ((a - b) >> £{S})));
	/* In C you can also do:
	 *    a ^ ((a ^ b) & -(a < b))
	 */
	/*
	 * Other version that can be faster:
	 *     (a < b) ? b : a
	 *     Assuming you now that INT_MIN <= x - y <= INT_MAX:
	 *         a - ((a - b) & ((a - b) >> (sizeof(£{T}) * CHAR_BITS - 1)))
	 */
    }
    
£>done
}

