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
package datastructures.linkedlists;
import java.lang.reflect.Array;


/**
 * Array singly linked list class. An array linked list
 * is a linked list constructed by parallel arrays. In
 * this implementation, when a node is removed the value
 * stored that that position is not removed before that
 * position is reused. Insertion methods have constant
 * amortised time complexity, and constant amortised
 * memory complexity, removal methods have constant time
 * complexity and constant memory complexity.
 * 
 * @param  <T>  The value stored in the structure
 */
£>export name=ArraySinglyLinkedList array=1 with_sentinel=0 with_head=1 with_tail=1 with_prev=0 with_xor=0
£>$GPP -s £ < src/datastructures/linkedlists/template | sed -e '/^[/ ]\*/d' -e '/^$/d'

