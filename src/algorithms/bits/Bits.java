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
£<function table
  {
      if [ $1 = 0 ]; then
          echo -n "${2}, "
      else
	  level=$(( $1 - 1 ))
          table $level $(( $2 + 0 ))
          table $level $(( $2 + 1 ))
          table $level $(( $2 + 1 ))
          table $level $(( $2 + 2 ))
      fi
£>}

    /**
     * Lookup table for the number of set bits in a byte
     */
    private static byte[] ONES_TABLE_256 = { £(table 4 0) };
    /* ONES_TABLE_256[0] = 0;
     * for (int i = 0; i < 256; i++)
     *     ONES_TABLE_256[i] = (i & 1) + ONES_TABLE_256[i / 2];
     */
    
    
£<for T_S in char_2 byte_1 short_2 int_4 long_8; do
  T=${T_S%_*}
£>S=${T_S#*_}
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
    
    /**
     * Clears the least significant bit set
     * 
     * @param   value  The integer
     * @return         The value with its least significant set bit cleared
     */
    public static £{T} clearLeastSignificant(£{T} value)
    {
	return (£{T})(value & (value - 1));
    }
    
    /**
     * Calculate the number of set bits in an integer, naïve version
     * 
     * @param   value  The integer
     * @return         The number of set bits
     */
    public static £{T} ones_naïve(£{T} value)
    {
	£{T} count = 0;
	for (; value != 0; value >>>= 1)
	    count += (£{T})(value & 1);
	return count;
    }
    
    /**
     * Calculate the number of set bits in an integer, Wegner's version
     * 
     * @param   value  The integer
     * @return         The number of set bits
     */
    public static £{T} ones_wegner(£{T} value)
    {
	£{T} count = 0;
	for (; value != 0; count++)
	    value &= value - 1; /* clear the least significant bit set */
	return count;
    }
    
    /**
     * Calculate the number of set bits in an integer, partial lookup table version
     * 
     * @param   value  The integer
     * @return         The number of set bits
     */
    public static £{T} ones_table(£{T} value)
    {
£>function lookup { echo "ONES_TABLE_256[(int)((value >> $1) & 255)]" ; }
	return (£{T})((£{T})(£(lookup 0) + £(lookup 8)) + (£{T})(£(lookup 16) + £(lookup 24)));
	/* In C you can split the value by getting the address of the value and cast the pointer to char* */
    }
£>done
}

