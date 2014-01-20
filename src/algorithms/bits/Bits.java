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
 * Operations on individual bits
 */
public class Bits
{
£>for T in char byte short int long; do
    /**
     * Sets or clears individual bits in an integer
     * 
     * @param   value  The value to modify
     * @param   mask   Mask of bits to modify
     * @param   flag   1 if the bit should be set, 0 if the bit should be clears
     * @return         The value with the bits modified
     */
    public static £{T} set_clear(£{T} value, £{T} mask, £{T} flag)
    {
	return (£{T})(value ^ ((-flag ^ value) & mask));
    }
    
    /**
     * Sets or clears individual bits in an integer, superscalar version
     * 
     * @param   value  The value to modify
     * @param   mask   Mask of bits to modify
     * @param   flag   1 if the bit should be set, 0 if the bit should be clears
     * @return         The value with the bits modified
     */
    public static £{T} set_clear_superscalar(£{T} value, £{T} mask, £{T} flag)
    {
	return (£{T})((value & ~mask) | (-flag & mask));
    }
    
    /**
     * Merge bits from two values
     * 
     * @param   zero  Integer whose bits should be kept where the mask has zeroes
     * @param   one   Integer whose bits should be kept where the mask has onces
     * @param   mask  The merge mask
     * @return        {@code (zero & ~mask) | (one & mask)}
     */
    public static £{T} merge(£{T} zero, £{T} one, £{T} mask)
    {
	return (£{T})(zero ^ ((£{T})(zero ^ one) & mask));
    }
£>done
}

