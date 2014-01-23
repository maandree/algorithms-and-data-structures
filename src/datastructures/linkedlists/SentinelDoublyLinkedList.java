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
 * Sentinel doubly linked list class. A linked
 * list is a list constructed from nodes, which
 * hold stored values, with references to each
 * other, this is usally an ineffective data
 * structure, especially on high-level object
 * orientated languages. A doubly linked list
 * stores nodes with references to the both the
 * following node previous node. A sentinel
 * linked list increses performance and reduces
 * complexity by tracking the edges implicitly
 * rather than explicitly; instead of having
 * reference to the first and last node, a
 * sentinel linked list have an extra node,
 * {@link #edge}, is placed at both the end and
 * the beginning of the linked list — the list
 * acts as linear but is constructed as circular
 * — and rather than checking that the next node
 * or previous node is not {@code null} you check
 * that it is not {@link #edge}. This implemention
 * only implements methods that do not require
 * iterating over all nodes for find a specific;
 * all other methods' — those that are implemented
 * — time complexity is Θ(1).
 * 
 * @param  <T>  The value stored in the structure
 */
public class SentinelDoublyLinkedList<T>
£>export with_prev=1
£>$GPP -s £ < src/datastructures/linkedlists/sentinel-template | sed -e '/^[/ ]\*/d'

