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
£<function ones-table
  {
      if [ $1 = 0 ]; then
          echo -n "${2}, "
      else
	  level=$(( $1 - 1 ))
          ones-table $level $(( $2 + 0 ))
          ones-table $level $(( $2 + 1 ))
          ones-table $level $(( $2 + 1 ))
          ones-table $level $(( $2 + 2 ))
      fi
£>}

    /**
     * Lookup table for the number of set bits in a byte
     */
    private static byte[] ONES_TABLE_256 = { £(ones-table 4 0) };
    /* ONES_TABLE_256[0] = 0;
     * for (int i = 0; i < 256; i++)
     *     ONES_TABLE_256[i] = (i & 1) + ONES_TABLE_256[i / 2];
     */


£<function parity-table
  {
      if [ $1 = 0 ]; then
          echo -n "${2}, "
      else
	  level=$(( $1 - 1 ))
          parity-table $level $(( $2 ^ 0 ))
          parity-table $level $(( $2 ^ 1 ))
          parity-table $level $(( $2 ^ 1 ))
          parity-table $level $(( $2 ^ 0 ))
      fi
£>}

    /**
     * Lookup table for the parity of the bits in a byte
     */
    private static byte[] PARITY_TABLE_256 = { £(parity-table 4 0) };


£<function reverse-table
  {
      if [ $1 = 0 ]; then
	  if (( $2 < 128 )); then
              echo -n "${2}, "
	  else
              echo -n "$(( $2 - 256 )), "
	  fi
      else
	  level=$(( $1 - 1 ))
	  reverse-table $level $(( $2 + 0 * 4 ** (4 - $1) ))
	  reverse-table $level $(( $2 + 2 * 4 ** (4 - $1) ))
	  reverse-table $level $(( $2 + 1 * 4 ** (4 - $1) ))
	  reverse-table $level $(( $2 + 3 * 4 ** (4 - $1) ))
      fi
£>}

    /**
     * Lookup table for the revered bit order in a byte
     */
    private static byte[] REVERSE_TABLE_256 = { £(reverse-table 4 0) };
    
    
    /**
     * Compute the parity of all bits in an integer, 64-bit multiply–modulus version
     * 
     * @param   value  The integer
     * @return         The parity
     */
    public static int parity_64bit(byte value)
    {
	return (int)(((value * 0x0101010101010101L) & 0x8040201008040201L) % 0x1FF) & 1;
    }
    
    /**
     * Reverse the order of all bits in an integer, 64-bit instructions version
     * 
     * @param   value  The integer
     * @return         The integer reversed
     */
    public static byte reverse_64bit(byte value)
    {
	return (byte)(((value * 0x0202020202L) & 0x010884422010L) % 1023);
    }
    
    /**
     * Reverse the order of all bits in an integer, division-free 64-bit instructions version
     * 
     * @param   value  The integer
     * @return         The integer reversed
     */
    public static byte reverse_64bit_noMod(byte value)
    {
	return (byte)(((value * 0x80200802L) & 0x0884422110L) * 0x0101010101L >> 32);
    }
    
    /**
     * Reverse the order of all bits in an integer, 32-bit instructions version
     * 
     * @param   value  The integer
     * @return         The integer reversed
     */
    public static byte reverse_32bit(byte value)
    {
	return (byte)(((value * 0x0802 & 0x22110) | (value * 0x8020 & 0x88440)) * 0x10101 >> 16);
    }
    
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
    public static byte ones_table(£{T} value)
    {
£>function _ { echo "ONES_TABLE_256[(int)((value >> $1) & 255)]" ; }
        return (byte)((byte)(£(_ 0) + £(_ 8)) + (byte)(£(_ 16) + £(_ 24)));
	/* In C you can split the value by getting the address of the value and cast the pointer to char* */
    }
    
    /**
     * Calculate the number of set bits in an integer, 64-bits instructions version
     * 
     * @param   value  The integer
     * @return         The number of set bits
     */
    public static long ones_64bits(£{T} value)
    {
	long v = value, rc;
£>(( $S > 1 )) &&
	if ((v & (1L << 14L) - 1L) == v)
	{
	    rc = (v * 0x200040008001L & 0x111111111111111L) % 0xF;
	}
£>if (( $S >= 2 )); then
	else
£>(( $S > 3 )) &&
	    if ((v & (1L << 24L) - 1L) == v)
	{
	    rc = ((v & 0xFFF) * 0x1001001001001L & 0x84210842108421L) % 0x1F;
	    rc += (((v & 0XFFF000) >> 12) * 0x1001001001001L & 0x84210842108421L) % 0x1F;
	}
£>if (( $S > 3 )); then
	else
£>(( $S > 4 )) &&
	    if ((v & (1L << 32L) - 1L) == v)
	{
	    rc = ((v & 0xFFF) * 0x1001001001001L & 0x84210842108421L) % 0x1F;
	    rc += (((v & 0xFFF000) >> 12) * 0x1001001001001L & 0x84210842108421L) % 0x1F;
	    rc += ((v >> 24) * 0x1001001001001L & 0x84210842108421L) % 0x1F;
	}
£>if (( $S > 4 )); then
	else
	{
	    rc = ones_64bits(v & (1L << 32L) - 1L);
	    rc += ones_64bits(v >>> 32);
	}
£>fi;fi;fi
	return rc;
    }
    
    /**
     * Calculate the number of set bits in an integer, naïve parallel version
     * 
     * @param   value  The integer
     * @return         The number of set bits
     */
    public static £{T} ones_parallel(£{T} value)
    {
	£{T} rc = value;
	rc = (£{T})(((rc >>  1) & (£{T})0x5555555555555555L) + (rc & (£{T})0x5555555555555555L));
	rc = (£{T})(((rc >>  2) & (£{T})0x3333333333333333L) + (rc & (£{T})0x3333333333333333L));
	rc = (£{T})(((rc >>  4) & (£{T})0x0F0F0F0F0F0F0F0FL) + (rc & (£{T})0x0F0F0F0F0F0F0F0FL));
£>(( $S > 1 )) &&
	rc = (£{T})(((rc >>  8) & (£{T})0x00FF00FF00FF00FFL) + (rc & (£{T})0x00FF00FF00FF00FFL));
£>(( $S > 2 )) &&
	rc = (£{T})(((rc >> 16) & (£{T})0x0000FFFF0000FFFFL) + (rc & (£{T})0x0000FFFF0000FFFFL));
£>(( $S > 4 )) &&
	rc = (£{T})(((rc >> 32) & (£{T})0x00000000FFFFFFFFL) + (rc & (£{T})0x00000000FFFFFFFFL));
	return rc;
    }
    
    /**
     * Calculate the number of set bits in an integer, optimised parallel version
     * 
     * @param   value  The integer
     * @return         The number of set bits
     */
    public static £{T} ones_optimised_parallel(£{T} value)
    {
	£{T} rc = (£{T})(value - ((value >> 1) & (£{T})0x5555555555555555L));
	rc = (£{T})(((rc >>  2) & (£{T})0x3333333333333333L) + (rc & (£{T})0x3333333333333333L));
	rc = (£{T})(((rc >>  4) + rc) & (£{T})0x0F0F0F0F0F0F0F0FL);
£>(( $S > 1 )) &&
	rc = (£{T})(((rc >>  8) + rc) & (£{T})0x00FF00FF00FF00FFL);
£>(( $S > 2 )) &&
	rc = (£{T})(((rc >> 16) + rc) & (£{T})0x0000FFFF0000FFFFL);
£>(( $S > 4 )) &&
	rc = (£{T})(((rc >> 32) + rc) & (£{T})0x00000000FFFFFFFFL);
	return rc;
    }
    
    /**
     * Calculate the number of set bits in an integer, parallel–64bits-like hybrid version, probably the best version
     * 
     * @param   value  The integer
     * @return         The number of set bits
     */
    public static £{T} ones_hybrid(£{T} value)
    {
£>L1="($T)$(bc <<< "(256^$S - 1) / 3")L"
£>L2="($T)$(bc <<< "(256^$S - 1) / 15 * 3")L"
£>L3="($T)$(bc <<< "(256^$S - 1) / 255 * 15")L"
£>L4="($T)$(bc <<< "(256^$S - 1) / 255")L"

	value -= (value >> 1) & £{L1};
	value = (£{T})((value & £{L2}) + ((value >> 2) & £{L2}));
	value = (£{T})((value + (value >> 4)) & £{L3});
	value = (£{T})((value * £{L4}) >> ((£{S} - 1) * 8));
	return value; /* Only applicable upto 128 bits */
    }
    
    /**
     * Compute the parity of all bits in an integer, naïve version
     * 
     * @param   value  The integer
     * @return         The parity
     */
    public static £{T} parity_naïve(£{T} value)
    {
	£{T} rc = 0;
	while (value != 0)
	{
	    rc ^= 1;
	    value &= value - 1;
	}
	return rc;
    }
    
    /**
     * Compute the parity of all bits in an integer, parallel version
     * 
     * @param   value  The integer
     * @return         The parity
     */
    public static £{T} parity_parallel(£{T} value)
    {
£>(( $S > 4 )) &&
	value ^= value >> 32;
£>(( $S > 2 )) &&
	value ^= value >> 16;
£>(( $S > 1 )) &&
	value ^= value >> 8;
	value ^= value >> 4;
	value ^= value >> 3;
	value ^= value >> 2;
	value ^= value >> 1;
	return (£{T})(value & 1);
    }
    
    /**
     * Compute the parity of all bits in an integer, partial lookup table version
     * 
     * @param   value  The integer
     * @return         The parity
     */
    public static byte parity_table(£{T} value)
    {
£>(( $S > 4 )) &&
	value ^= value >> 32;
£>(( $S > 2 )) &&
	value ^= value >> 16;
£>(( $S > 1 )) &&
	value ^= value >> 8;
	return PARITY_TABLE_256[(int)value];
    }
    
    /**
     * Compute the parity of all bits in an integer, optimised parallel version
     * 
     * @param   value  The integer
     * @return         The parity
     */
    public static int parity_optimised_parallel(£{T} value)
    {
£>(( $S > 4 )) &&
	value ^= value >> 32;
£>(( $S > 2 )) &&
	value ^= value >> 16;
£>(( $S > 1 )) &&
	value ^= value >> 8;
	value ^= value >> 4;
	return (0x6996 >> (value & 15)) & 1;
    }
    
    /**
     * Compute the parity of all bits in an integer, multiplication version
     * 
     * @param   value  The integer
     * @return         The parity
     */
    public static £{T} parity_multiplication(£{T} value)
    {
	value ^= value >> 1;
	value ^= value >> 2;
	value = (£{T})((value & (£{T})0x1111111111111111L) * (£{T})0x1111111111111111L);
	return (£{T})((value >> (£{S} - 4)) & 1);
    }
    
    /**
     * Reverse the order of all bits in an integer, naïve version
     * 
     * @param   value  The integer
     * @return         The integer reversed
     */
    public static £{T} reverse_naïve(£{T} value)
    {
	int s = £{S} * 8 - 1;
	£{T} rc = value;
	for (value >>>= 1; value != 0; value >>>= 1)
	{
	    rc <<= 1;
	    rc |= value & 1;
	    s--;
	}
	return (£{T})(rc << s);
    }
    
    /**
     * Reverse the order of all bits in an integer, parallel version
     * 
     * @param   value  The integer
     * @return         The integer reversed
     */
    public static £{T} reverse_parallel(£{T} value)
    {
	value = (£{T})(((value & (£{T})0x5555555555555555L) <<  1) | ((value >>  1) & (£{T})0x5555555555555555L));
	value = (£{T})(((value & (£{T})0x3333333333333333L) <<  2) | ((value >>  2) & (£{T})0x3333333333333333L));
	value = (£{T})(((value & (£{T})0x0F0F0F0F0F0F0F0FL) <<  4) | ((value >>  4) & (£{T})0x0F0F0F0F0F0F0F0FL));
£>(( $S > 1 )) &&
	value = (£{T})(((value & (£{T})0x00FF00FF00FF00FFL) <<  8) | ((value >>  8) & (£{T})0x00FF00FF00FF00FFL));
£>(( $S > 2 )) &&
	value = (£{T})(((value & (£{T})0x0000FFFF0000FFFFL) << 16) | ((value >> 16) & (£{T})0x0000FFFF0000FFFFL));
£>(( $S > 4 )) &&
	value = (£{T})(((value & (£{T})0x00000000FFFFFFFFL) << 32) | ((value >> 32) & (£{T})0x00000000FFFFFFFFL));
	return value;
    }
    
    /**
     * Reverse the order of all bits in an integer, partial lookup table version
     * 
     * @param   value  The integer
     * @return         The integer reversed
     */
    public static £{T} reverse_table(£{T} value)
    {
£>function _ { echo "(${T})(REVERSE_TABLE_256[(int)((value >> $1) & 255)] << $2)" ; }
	£{T} rc = 0;
£>for s in `seq 0 8 $(( $S - 8 ))`; do
	rc |= £(_ $s "($S - 8 - $s)");
£>done
	return rc;
    }
£>done
}

