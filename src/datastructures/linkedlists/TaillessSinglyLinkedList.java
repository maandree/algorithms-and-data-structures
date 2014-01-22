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
 * Taileess singly linked list class. A linked list
 * is a list constructed from nodes, which hold
 * stored values, with references to each other,
 * this is usally an ineffective data structure,
 * especially on high-level object orientated
 * languages. A singly linked list only stores nodes
 * with references to the following node. It also
 * stores one refence nodes, the first node in the
 * list. The implemention only implements methods
 * that do not require iterating over all nodes for
 * find a specific; all other methods' — those that
 * are implemented — time complexity is Θ(1).
 * 
 * @param  <T>  The value stored in the structure
 */
public class TaillessSinglyLinkedList<T>
£>export with_tail=0 with_prev=0
£>gpp -s £ < src/datastructures/linkedlists/template | sed -e '/^[/ ]\*/d'
