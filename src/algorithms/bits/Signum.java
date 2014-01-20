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

import java.util.*;


/**
 * Compute the sign of an integer
 */
public class Signum
{
£<for T_S in byte_7 short_15 int_31 long_63; do
  T=${T_S%_*}
£>S=${T_S#*_}
    /**
     * Compute the sign of an integer
     * 
     * @param   value  The integer
     * @return         {@code -1} if negative, otherwise {@code 0}
     */
    public static int minus_zero(£{T} value)
    {
	return (int)(value >> £{S});
	/* In C, you can also do either of:
	 *     -(value < 0)
	 *     -(£{T})((unsigned £{T})((£{T})value) >> (sizeof(£{T}) * CHAR_BITS - 1));
	 * The method used here is not portable in C
	 * CHAR_BITS should be defined as 8 if not already defined
	 */
    }
    
    /**
     * Compute the sign of an integer
     * 
     * @param   value  The integer
     * @return         {@code -1} if negative, otherwise {@code 1}
     */
    public static int minus_plus(£{T} value)
    {
	return 1 | (int)(value >> £{S});
    }
    
    /**
     * Compute the sign of an integer
     * 
     * @param   value  The integer
     * @return         {@code -1} if negative, {@code 1} if positive, and {@code 0} if zero
     */
    public static int minus_zero_plus(£{T} value)
    {
	return (int)(value >> £{S}) - (int)(-value >> £{S});
	/* In C, you can also do either of:
	 *     (value != 0) | -(£{T})((unsigned int)((£{T})value) >> (sizeof(£{T}) * CHAR_BITS - 1))
	 *     (value != 0) | (value >> (sizeof(£{T}) * CHAR_BITS - 1))
	 *     (value > 0) - (value < 0)
	 */
    }
    
    /**
     * Compute the sign of an integer
     * 
     * @param   value  The integer
     * @return         {@code 0} if negative, otherwise {@code 1}
     */
    public static int zero_plus(£{T} value)
    {
	return 1 ^ (1 & (int)(value >> £{S}));
	/* In C, you can also do:
	 *     1 ^ ((unsigned £{T})value >> (sizeof(£{T}) * CHAR_BITS - 1))
	 */
    }
£>done
}

