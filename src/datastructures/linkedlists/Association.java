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
 * Value type that can be stored in linked lists
 * to turn them in to association lists.
 * 
 * @param  <K>  The type of the key of the node
 * @param  <V>  The type of the value associated with the key
 */
public class Association<K, V>
{
    /**
     * Constructor
     * 
     * @param  key    The key of the node
     * @param  value  The value associated with the key
     */
    public Association(K key, V value)
    {
	this.key = key;
	this.value = value;
    }
    
    
    
    /**
     * The key of the node
     */
    public K key;
    
    /**
     * The value associated with the key
     */
    public V value;
    
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other)
    {
	if ((this.key == null) || (other == null))
	    return (this.key == null) && (other == null);
	return this.key.equals(other);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
	return this.key.hashCode();
    }
    
}

