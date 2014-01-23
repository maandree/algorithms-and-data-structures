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


/**
 * Headless singly linked list class. A linked list
 * is a list constructed from nodes, which hold
 * stored values, with references to each other,
 * this is usally an ineffective data structure,
 * especially on high-level object orientated
 * languages. A singly linked list only stores nodes
 * with references to the following node. It also
 * stores one refence nodes, the last node in the
 * list. This implemention only implements methods
 * that do not require iterating over all nodes for
 * find a specific; all other methods' — those that
 * are implemented — time complexity is Θ(1).
 * 
 * A headless singly linked list some features that
 * could be an advantage depending on what you are
 * using it for: Because it is singly linked it uses
 * slightly less memory and it is slightly faster.
 * Because it is also headless there is not need to
 * remove nodes from the beginning of the list if you
 * are iterating over the list, they are automatically
 * lost and freed by the garbage collector (assuming
 * there is one.)
 * 
 * @param  <T>  The value stored in the structure
 */
£>export name=HeadlessSinglyLinkedList array=0 with_sentinel=0 with_head=0 with_tail=1 with_prev=0
£>$GPP -s £ < src/datastructures/linkedlists/template | sed -e '/^[/ ]\*/d' -e '/^$/d'

