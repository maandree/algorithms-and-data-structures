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
 * Array sentinel singly linked list class. An array
 * linked list is a linked list constructed by parallel
 * arrays. A sentinel linked list is a linear linked
 * listed constructed as a circular linked listed with
 * a sentinel (dummy) node between the first node and
 * the last node. In this implementation, when a node
 * is removed the value stored that that position is
 * not removed before that position is reused.
 * 
 * @param  <T>  The value stored in the structure
 */
£>export name=ArraySentinelSinglyLinkedList with_sentinel=1 with_head=0 with_tail=0 with_prev=0
£>$GPP -s £ < src/datastructures/linkedlists/array-template | sed -e '/^[/ ]\*/d'

