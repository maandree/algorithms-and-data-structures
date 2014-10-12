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
package datastructures;
import datastructures.linkedlists.*;

import java.util.Random;


/**
 * This program be used to test the performance of
 * some datastructures
 */
public class PerformanceTest
{
    /**
     * This is the main entry point of the test
     * 
     * @param  args  Command line arguments
     */
    public static void main(final String... args)
    {
	int numElements, maxElements = 1 << 23;
	Random rng = new Random();
	
	for (numElements = 1; numElements <= maxElements; numElements <<= 1)
	{
	    long time, subtime;
	    Integer[] elements = new Integer[numElements];
	    System.out.println("Using " + numElements + " random elements.");
	    for (int i = 0; i < numElements; i++)
		elements[i] = Integer.valueOf(rng.nextInt());
	    
	    ArrayDoublyLinkedList<Integer> arrayDoublyLinkedList =
		new ArrayDoublyLinkedList<Integer>(Integer.class);
	    DoublyLinkedList<Integer> doublyLinkedList =
		new DoublyLinkedList<Integer>(Integer.class);
	    ArraySinglyLinkedList<Integer> arraySinglyLinkedList =
		new ArraySinglyLinkedList<Integer>(Integer.class);
	    SinglyLinkedList<Integer> singlyLinkedList =
		new SinglyLinkedList<Integer>(Integer.class);
	    ArraySentinelDoublyLinkedList<Integer> arraySentinelDoublyLinkedList =
		new ArraySentinelDoublyLinkedList<Integer>(Integer.class);
	    SentinelDoublyLinkedList<Integer> sentinelDoublyLinkedList =
		new SentinelDoublyLinkedList<Integer>(Integer.class);
	    ArraySentinelSinglyLinkedList<Integer> arraySentinelSinglyLinkedList =
		new ArraySentinelSinglyLinkedList<Integer>(Integer.class);
	    SentinelSinglyLinkedList<Integer> sentinelSinglyLinkedList =
		new SentinelSinglyLinkedList<Integer>(Integer.class);
	    
	    System.out.println();
	    System.out.println("Insert at beginning:");
	    
£<for class in {Array,}{Sentinel,}{Doubly,Singly}LinkedList; do
  list_a=${class::1}
  list_b=${class:1}
£>list=${list_a,,}${list_b}
	    System.gc();
	    try
	    {
		Thread.sleep(100);
	    } catch(InterruptedException e) {}
	    time = 0;
	    for (int i = 0; i < numElements; i++)
	    {
		subtime = System.nanoTime();
		£{list}.insertBeginning(elements[i]);
		time += System.nanoTime() - subtime;
	    }
	    time /= numElements;
	    System.out.printf("%30s: %7d.%03d µs/element\n", "£{class}",
			      time / 1000, time % 1000);
	    £{list} = null;
£>done
	    
	    System.out.println();
	    System.out.println();
	}
    }
    
}

