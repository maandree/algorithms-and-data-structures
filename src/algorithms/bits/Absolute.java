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
 * Compute the absolute value of an integer
 */
public class Absolute
{
£<for T_S in byte_7 short_15 int_31 long_63; do
  T=${T_S%_*}
£>S=${T_S#*_}
    /**
     * Compute the absolute value of an integer
     * 
     * @param   value  The integers
     * @return         The absolute value, the most negative value will because of overflow return the value itself
     */
    public static £{T} abs(£{T} value)
    {
	£{T} mask;;
	return (value ^ (mask = value >> £{S})) - mask;
	/* Or alternatively:
	 *     (v + (mask = value >> £{S})) ^ mask
	 */
    }
£>done
}

