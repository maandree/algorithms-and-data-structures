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

import java.util.Random;


/**
 * Bias free array shuffling class aiming to imitate naïve shuffling as preformed by humans
 */
public class HumanShuffle
{
£>for T in boolean char byte short int long float double Object; do
    /**
     * Shuffles an array
     *
     * @param  array   The array to shuffle
     * @param  random  The random generator to use
     */
    public static void shuffle(£{T}[] array, Random random)
    {
	int n;
	if ((n = array.length) < 2)
	    return;
        
	int min = (int)(random.nextGaussian() * n) >> 1;
	if (min < 0)
	    min = n - min;
	else
	    min += n;
        
	int k = 0;
	while ((k++ < min) || (random.nextInt(2) == 0))
	{
	    int i = random.nextInt(n);
	    int j = random.nextInt(n);

	    if (i == j)
		j = (j + 1) % n;

	    £{T} temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	}
    }
£>done
}

