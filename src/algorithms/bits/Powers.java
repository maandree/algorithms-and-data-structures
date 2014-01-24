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
 * Power of 2 interger operations
 */
public class Powers
{
£<for T_S in char_2 byte_1 short_2 int_4 long_8; do
  T=${T_S%_*}
£>S=${T_S#*_}
    /**
     * Compute whether an integer is a power of two,
     * note that zero is indeed not a power of two
     * 
     * @param   value  The integer
     * @return         Whether the integer is a power of two
     */
    public static boolean isPowerOf2(£{T} value)
    {
	return (value & (value - 1)) == 0; /* The left hand side clears the least significant bit set */
	/* Or alternatively: (value & -value) == value */
    }
    
    /**
     * Computes the nearest, but higher, power of two,
     * independently of whether the current value is
     * a power of two or not
     * 
     * @param   value  The current value
     * @return         The next power of two
     */
    public static £{T} nextPowerOf2(£{T} value)
    {
	value |= value >> 1;
	value |= value >> 2;
	value |= value >> 4;
£>(( S > 1)) &&
	value |= value >> 8;
£>(( S > 2)) &&
	value |= value >> 16;
£>(( S > 4)) &&
	value |= value >> 32;
	return (£{T})(value + 1);
    }
    
    /**
     * Computes the nearest, but higher, power of two,
     * but only if the current value is not a power of two
     * 
     * @param   value  The current value
     * @return         The next power of two
     */
    public static £{T} toPowerOf2(£{T} value)
    {
	value -= 1;
	value |= value >>> 1;
	value |= value >>> 2;
	value |= value >>> 4;
£>(( S > 1)) &&
	value |= value >>> 8;
£>(( S > 2)) &&
	value |= value >>> 16;
£>(( S > 4)) &&
	value |= value >>> 32;
	return (£{T})(value + 1);
    }
£>done
}

