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
 * Doubly linked list class. A linked list is a
 * list constructed from nodes, which hold stored
 * values, with references to each other, this is
 * usally an ineffective data structure, especially
 * on high-level object orientated languages. A
 * doublely linked list stores nodes with references
 * to both the following node and the previous node.
 * It also stores two refence nodes, the first node
 * in the list, and the last node in the list. This
 * implemention only implements methods that do not
 * require iterating over all nodes for find a
 * specific; all other methods' — those that are
 * implemented — time complexity is Θ(1).
 * 
 * @param  <T>  The value stored in the structure
 */
£>export name=DoublyLinkedList array=0 with_sentinel=0 with_head=1 with_tail=1 with_prev=1 with_xor=0
£>$GPP -s £ < src/datastructures/linkedlists/template | sed -e '/^[/ ]\*/d' -e '/^$/d'

