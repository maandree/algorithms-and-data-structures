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
#ifndef ALGO_ALGORITHMS_ARRAYS_SORTED_H
#define ALGO_ALGORITHMS_ARRAYS_SORTED_H


/* NB! This will not play nice if the placeholder `T` is
 * not set to a type only containing [0-9A-Za-z_] (and $
 * in GNU C). Therefore, with the exception of `char`,
 * `short`, `int`, `long`, `float` and `double`, you
 * should only use `typedef`:ed types. */


#include <stddef.h>


/**
 * The order elements are sorted in.
 */
typedef enum algo_sorted_order
  {
    /**
       The array is not sorted.
     */
    UNORDERED,
    
    /**
     * The array is sorted in ascending (normal) order.
     */
    ASCENDING_ORDER,
    
    /**
     * The array is sorted in descending (reverse normal) order.
     */
    DESCENDING_ORDER,
    
    /**
     * All elements in the array are mutually equal or the array
     * contains less than two elements. This is the bitwise OR of
     * `ASCENDING_ORDER` and `DESCENDING_ORDER`.
     */
    FLAT
    
  } algo_sorted_order_t;


/**
 * Gets whether an array is sorted and in which order it is sorted.
 * 
 * `algo_make_implementation_of_is_sorted(T)` is used to make
 * this function available for a particular data type `T`. And
 * implementation without modifiers and attributes will be
 * expanded. You may add `static`, `inline` and `__attribute__`
 * before calling `algo_make_implementation_of_is_sorted(T)`.
 * 
 * `algo_make_prototype_of_is_sorted(T)` is the prototype
 * counterpart of `algo_make_implementation_of_is_sorted(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_is_sorted(T)` is used to get the version of the
 * function that supports the data type `T`.
 * `&(algo_is_sorted(T))` gets the address of this function
 * and `algo_is_sorted(T)(items, n)` calls the function.
 * 
 * This function is pure, if you use GCC you should add
 * `__attribute__((pure))` to its prototype.
 * 
 * @param   items  The items to check if they are ordered.
 * @param   n      The number of elements in `items`.
 * @return         The order elements are sorted in.
 */
//>fun () {
algo_sorted_order_t algo_is_sorted__##T(const T* restrict items, size_t n)
{
  const T* end = items + n;
  T last, cur;
  algo_sorted_order_t order = FLAT;
  
  if (n == 0)
    return FLAT;
  
  for (last = *items++; items != end; last = cur)
    if (cur = *items++, last < cur)
      {
	if      (order == FLAT)              order = ASCENDING_ORDER;
	else if (order == DESCENDING_ORDER)  return UNORDERED;
      }
    else if (last > cur)
      {
	if      (order == FLAT)             order = DESCENDING_ORDER;
	else if (order == ASCENDING_ORDER)  return UNORDERED;
      }
  
  return order;
}
//>} ; . ../make_fun


/**
 * Gets whether an array is sorted and in which order it is sorted.
 * 
 * This variant of `algo_is_sorted` is suitable for
 * non-numeric data.
 * 
 * `algo_make_implementation_of_is_sorted_cmp(T)` is used to make
 * this function available for a particular data type `T`. And
 * implementation without modifiers and attributes will be
 * expanded. You may add `static`, `inline` and `__attribute__`
 * before calling `algo_make_implementation_of_is_sorted_cmp(T)`.
 * 
 * `algo_make_prototype_of_is_sorted_cmp(T)` is the prototype
 * counterpart of `algo_make_implementation_of_is_sorted_cmp(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_is_sorted_cmp(T)` is used to get the version of the
 * function that supports the data type `T`.
 * `&(algo_is_sorted_cmp(T))` gets the address of this function
 * and `algo_is_sorted_cmp(T)(items, n, cmp)` calls the function.
 * 
 * This function is not pure because `cmp` is not necessarily pure.
 * 
 * @param   items  The items to check if they are ordered.
 * @param   n      The number of elements in `items`.
 * @param   cmp    Function used to compare two items, it should return a
 *                 negative value if its first parameter is the lesser, a
 *                 positive value if its second parameter is the lesser
 *                 and zero if the parameters are equal.
 * @return         The order elements are sorted in.
 */
//>fun () {
algo_sorted_order_t algo_is_sorted_cmp__##T(const T* restrict items, size_t n,
					    int (*cmp)(const T*, const T*))
{
  const T* end = items + n;
  T last, cur;
  algo_sorted_order_t order = FLAT;
  int comparison;
  
  if (n == 0)
    return FLAT;
  
  for (last = *items++; items != end; last = cur)
    {
      comparison = cmp(last, cur = *items++);
      if (comparison < 0)
	{
	  if      (order == FLAT)              order = ASCENDING_ORDER;
	  else if (order == DESCENDING_ORDER)  return UNORDERED;
	}
      else if (comparison > 0)
	{
	  if      (order == FLAT)             order = DESCENDING_ORDER;
	  else if (order == ASCENDING_ORDER)  return UNORDERED;
	}
    }
  
  return order;
}
//>} ; . ../make_fun


/**
 * Gets whether an array is sorted and in which order it is sorted.
 * 
 * This variant of `algo_is_sorted_cmp` allows you to
 * store data needed for the comparison in a thread-safe
 * way. It is reentrant.
 * 
 * `algo_make_implementation_of_is_sorted_cmp_r(T)` is used to make
 * this function available for a particular data type `T`. And
 * implementation without modifiers and attributes will be
 * expanded. You may add `static`, `inline` and `__attribute__`
 * before calling `algo_make_implementation_of_is_sorted_cmp_r(T)`.
 * 
 * `algo_make_prototype_of_is_sorted_cmp_r(T)` is the prototype
 * counterpart of `algo_make_implementation_of_is_sorted_cmp_r(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_is_sorted_cmp_r(T)` is used to get the version of the
 * function that supports the data type `T`.
 * `&(algo_is_sorted_cmp_r(T))` gets the address of this function
 * and `algo_is_sorted_cmp_r(T)(items, n, cmp)` calls the function.
 * 
 * This function is not pure because `cmp` is not necessarily pure.
 * 
 * @param   items  The items to check if they are ordered.
 * @param   n      The number of elements in `items`.
 * @param   cmp    Function used to compare two items, it should return a
 *                 negative value if its first parameter is the lesser, a
 *                 positive value if its second parameter is the lesser
 *                 and zero if the parameters are equal. `data` will be
 *                 input as `cmp`'s third argument.
 * @param   data   Arbitrary data (may be `NULL`) to pass throught to `cmp`.
 *                 and zero if the parameters are equal.
 * @return         The order elements are sorted in.
 */
//>fun () {
algo_sorted_order_t algo_is_sorted_cmp_r__##T(const T* restrict items, size_t n,
					    int (*cmp)(const T*, const T*, void*), void* data)
{
  const T* end = items + n;
  T last, cur;
  algo_sorted_order_t order = FLAT;
  int comparison;
  
  if (n == 0)
    return FLAT;
  
  for (last = *items++; items != end; last = cur)
    {
      comparison = cmp(last, cur = *items++, data);
      if (comparison < 0)
	{
	  if      (order == FLAT)              order = ASCENDING_ORDER;
	  else if (order == DESCENDING_ORDER)  return UNORDERED;
	}
      else if (comparison > 0)
	{
	  if      (order == FLAT)             order = DESCENDING_ORDER;
	  else if (order == ASCENDING_ORDER)  return UNORDERED;
	}
    }
  
  return order;
}
//>} ; . ../make_fun


#endif

