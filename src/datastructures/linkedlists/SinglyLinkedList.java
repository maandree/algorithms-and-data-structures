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
 * Singly linked list class. A linked list is a
 * list constructed from nodes, which hold stored
 * values, with references to each other, this is
 * usally an ineffective data structure, especially
 * on high-level object orientated languages. A
 * singly linked list only stores nodes with
 * references to the following node. It also stores
 * two refence nodes, the first node in the list,
 * and the last node in the list. The implemention
 * only implements methods that do not require
 * iterating over all nodes for find a specific;
 * all other methods' — those that are implemented
 * — time complexity is Θ(1).
 * 
 * @param  <T>  The value stored in the structure
 */
public class SinglyLinkedList<T>
{
    /**
     * Node for the list
     */
    public class Node
    {
	/**
	 * Constructor
	 * 
	 * @param  value  The value to store in the list
	 */
	private Node(T value)
	{
	    this.value = value;
	}
	
	
	
	/**
	 * The value stored in the list by this node
	 */
	public T value;
	
	/**
	 * The next node in the list
	 */
	public Node next = null;
	
    }
    
    
    
    /**
     * The first node in the list
     */
    public Node head = null;
    
    /**
     * The last node in the list
     */
    public Node tail = null;
    
    
    
    /**
     * Insert a value in the beginning of the list
     * 
     * @param   value  The value to insert
     * @return         The node that has be created and inserted
     */
    public Node insertBeginning(T value)
    {
	Node node = new Node(value);
	node.next = this.head;
	if (this.head == null)
	    this.tail = node;
	this.head = node;
	return node;
    }
    
    /**
     * Remove the node at the beginning of the list
     * 
     * @return  The node that has been removed
     */
    public Node removeBeginning()
    {
	Node node = head;
	if (node != null)
	    head = head.next;
	if (this.tail == node)
	    this.tail = null;
	return node;
    }
    
    /**
     * Insert a value after a specified, reference, node
     * 
     * @param   value        The value to insert
     * @param   predecessor  The reference node
     * @return               The node that has be created and inserted
     */
    public Node insertAfter(T value, Node predecessor)
    {
	Node node = new Node(value);
	node.next = predecessor.next;
	predecessor.next = node;
	if (this.tail == predecessor)
	    this.tail = node;
	return node;
    }
    
    /**
     * Remove the node after a specified, reference, node
     * 
     * @param   predecessor  The reference node
     * @return               The node that has been removed
     */
    public Node removeAfter(Node predecessor)
    {
	Node node = predecessor.next;
	if (node == null)
	    predecessor.next = node.next;
	if (this.tail == node)
	    this.tail = predecessor;
	return node;
    }
    
    /**
     * Insert a value in the end of the list
     * 
     * @param   value  The value to insert
     * @return         The node that has be created and inserted
     */
    public Node insertEnd(T value)
    {
	if (this.tail == null)
	    return insertBeginning(value);
	return insertAfter(value, this.tail);
    }
    
}

