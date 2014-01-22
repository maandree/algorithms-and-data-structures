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
package algorithms.sorting.other;

import java.util.*;


/**
 * Topological sorting class. There are a few ways to perform
 * a topological sort, this class implements an topological
 * sort with advisory dependencies and strict dependencies.
 * Dependencies are broken when there are cyclic dependencies,
 * but if the dependency is strict rather than advisory, the
 * algorithm fails. Additionally, this this, implementation
 * uses two input parameters, a map from items to sets of
 * dependencies, and a map from items, to a set of strict
 * dependencies. The strict dependencies must appear in both
 * of the sets, whilst the advisory dependencies must only
 * appear in the first set of dependencies. This implemention
 * tries to minimise the number of transversially broken
 * dependencies. It is advisable to use both hash based maps
 * and sets.
 * 
 * @param  <T>  The data type that has been sorted
 */
public class TopologicalSort<T>
{
    /**
     * Constructor
     */
    private TopologicalSort()
    {
	/* Do nothing */
    }
    
    
    
    /**
     * List of missing dependencies. There may be duplicates,
     * see {@link #missingDependent}.
     */
    public ArrayList<T> missingDependency = new ArrayList<T>();
    
    /**
     * List of items with missing dependencies. For any give index
     * i, the element in {@link #missingDependency} with index i
     * is required by the element with index i in this list.
     */
    public ArrayList<T> missingDependent = new ArrayList<T>();
    
    /**
     * The items in topological order
     */
    public ArrayList<T> order = new ArrayList<T>();
    
    /**
     * If an items exists as a key in this map it has been used
     * to break a cyclic dependency. The associated value of a
     * key is a set of all direct dependencies that will appear
     * later in the topologically ordered list {@link #order}.
     */
    public HashMap<T, HashSet<T>> breaks = new HashMap<T, HashSet<T>>();
    
    /**
     * Whether the topological sort was successful, i.e., not
     * absolute dependencies needed to be broken. If this value
     * is {@code false}, the topologically ordered list and
     * the map of cyclic dependency breaks may no be complete.
     */
    public boolean successful = false;
    
    
    
    /**
     * Topolocally sort data.
     * 
     * @param   dependencies  Map for items to their advisory dependencies, will be modified
     * @param   absolutes     Map for items to their absolute dependencies
     * @return                A data structure with the result of the topological sort
     */
    @SuppressWarnings("unchecked")
    public static <T> TopologicalSort<T> tsort(Map<T, Set<T>> dependencies, Map<T, Set<T>> absolutes)
    {
	TopologicalSort<T> rc = new TopologicalSort<T>();
	if (absolutes == null) /* sugar */
	    absolutes = new HashMap<T, Set<T>>();
	
	/* Find, list and remove missing dependencies */
	{
	    HashMap<T, ArrayList<T>> removed = new HashMap<T, ArrayList<T>>();
	    for (Map.Entry<T, Set<T>> req_deps : dependencies.entrySet())
	    {
		T req = req_deps.getKey();
		for (T dep : req_deps.getValue())
		    if (dependencies.containsKey(dep) == false)
		    {
			rc.missingDependency.add(dep);
			rc.missingDependent.add(req);
			if (removed.containsKey(dep) == false)
			    removed.put(dep, new ArrayList<T>());
			removed.get(dep).add(req);
		    }
	    }
	    for (Map.Entry<T, ArrayList<T>> remove_reqs : removed.entrySet())
	    {
		T remove = remove_reqs.getKey();
		for (T req : remove_reqs.getValue())
		    dependencies.get(req).remove(remove);
	    }
	}
	
	/* Sort cyclic graph topologically */
	{
	    ArrayList<T> removed = new ArrayList<T>();
	    removed.add(null);
	    while (removed.size() > 0)
	    {
		/* Sort acyclic graph topologically */
		while (removed.size() > 0)
		{
		    removed.clear();
		    /* Report and remove items with no unresolved dependency */
		    T[] dependencies_ = (T[])(new Object[dependencies.size()]); /* needed to avoid concurrent modification */
		    int i = 0;
		    for (T key : dependencies.keySet())
			dependencies_[i++] = key;
		    for (T item : dependencies_)
			if (dependencies.get(item).size() == 0)
			{
			    rc.order.add(item);
			    removed.add(item);
			    dependencies.remove(item);
			}
		    /* Remove reported items from dependency lists */
		    for (Map.Entry<T, Set<T>> req_deps : dependencies.entrySet())
		    {
			T req = req_deps.getKey();
			Set<T> deps = req_deps.getValue();
			for (T old : removed)
			    if (deps.contains(old))
				deps.remove(old);
		    }
		}
		
		/* Break one cycle with as few transversial dependencies as possible */
		if (dependencies.size() > 0)
		{
		    T best = null;
		    HashSet<T> bestBreaks = null;
		    /* Find best cycle break */
		    for (Map.Entry<T, Set<T>> item_deps : dependencies.entrySet())
		    {
			T item = item_deps.getKey();
			Set<T> queue_ = item_deps.getValue();
			Set<T> abs_ = absolutes.get(item);
			
			HashSet<T> abs = new HashSet<T>();
			if (abs_ != null)
			    for (T elem : abs_)
				abs.add(elem);
			
			HashSet<T> deps = new HashSet<T>();
			T[] queue = (T[])(new Object[queue_.size()]);
			int queue_h = 0, queue_t = 0;
			for (T elem : queue_)
			    queue[queue_t++] = elem;
			deps.add(item);
			boolean bad = false;
			
			while (queue_h != queue_t)
			{
			    T dep = queue[queue_h++];
			    if (abs.contains(dep))
			    {
				bad = true;
				break;
			    }
			    if (deps.contains(dep))
				continue;
			    deps.add(dep);
			    
			    Set<T> extra = dependencies.get(dep);
			    queue_t -= queue_h;
			    System.arraycopy(queue, queue_h, queue = (T[])(new Object[queue_t + extra.size()]), 0, queue_t);
			    queue_h = 0;
			    for (T elem : extra)
				queue[queue_t++] = elem;
			    
			    if ((extra = absolutes.get(dep)) != null)
				for (T elem : extra)
				    abs.add(elem);
			}
			
			if (bad)
			    continue;
			deps.remove(item);
			if ((best == null) || (bestBreaks.size() > deps.size()))
			{
			    best = item;
			    bestBreaks = deps;
			}
		    }
		    
		    /* List cycle break and remove item */
		    if (best == null)
			return rc;
		    rc.order.add(best);
		    rc.breaks.put(best, bestBreaks);
		    removed.add(best);
		    dependencies.remove(best);
		    
		    /* Remove item from dependency lists */
		    for (Map.Entry<T, Set<T>> item_deps : dependencies.entrySet())
		    {
			T item = item_deps.getKey();
			Set<T> deps = item_deps.getValue();
			if (deps.contains(item) == false)
			    deps.remove(best);
		    }
		}
	    }
	}
	
	rc.successful = true;
	return rc;
    }
    
}

