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
#ifndef ALGO_ALGORITHMS_ARRAYS_MINMAX_H
#define ALGO_ALGORITHMS_ARRAYS_MINMAX_H


/* NB! This will not play nice if the placeholder `T` is
 * not set to a type only containing [0-9A-Za-z_] (and $
 * in GNU C). Therefore, with the exception of `char`,
 * `short`, `int`, `long`, `float` and `double`, you
 * should only use `typedef`:ed types. */


#include <stddef.h>


/**
 * Find the minimum and maximum values in an array.
 * 
 * `algo_make_implementation_of_get_bounds(T)` is used to make
 * this function available for a particular data type `T`. And
 * implementation without modifiers and attributes will be
 * expanded. You may add `static`, `inline` and `__attribute__`
 * before calling `algo_make_implementation_of_get_bounds(T)`.
 * 
 * `algo_make_prototype_of_get_bounds(T)` is the prototype
 * counterpart of `algo_make_implementation_of_get_bounds(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_get_bounds(T)` is used to get the version of the
 * function that supports the data type `T`.
 * `&(algo_get_bounds(T))` gets the address of this function
 * and `algo_get_bounds(T)(items, n, min, max)` calls the
 * function.
 * 
 * This function is not pure since it will edit the value
 * of `min` and `max`. This function does not accept `NULL`
 * arguments, if you are using GCC you should add
 * `__attribute__((nonnull))` to its prototype.
 * 
 * Undefined behaviour is invoked `n == 0`.
 * 
 * @param  items  The items over to search.
 * @param  n      The number of elements in `items`.
 * @param  min    Output parameter for the minimum value.
 * @param  max    Output parameter for the maximum value.
 */
//>fun () {
void algo_get_bounds__##T(const T* restrict items, size_t n, T* min, T* max)
{
  const T* end = items + n;
  T maxv, minv, cur;
  size_t i;
  
  max = min = *items++;
  
  while (items != end)
    {
      cur = *items++;
      if      (cur > maxv)  maxv = cur;
      else if (cur < minv)  minv = cur;
    }
  
  *min = min;
  *max = max;
}
//>} ; . ../make_fun


/**
 * Find the minimum and maximum values in an array.
 * 
 * This variant of `algo_get_bounds` is suitable for
 * non-numeric data.
 * 
 * `algo_make_implementation_of_get_bounds_cmp(T)` is used to make
 * this function available for a particular data type `T`. And
 * implementation without modifiers and attributes will be
 * expanded. You may add `static`, `inline` and `__attribute__`
 * before calling `algo_make_implementation_of_get_bounds_cmp(T)`.
 * 
 * `algo_make_prototype_of_get_bounds_cmp(T)` is the prototype
 * counterpart of `algo_make_implementation_of_get_bounds_cmp(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_get_bounds_cmp(T)` is used to get the version of the
 * function that supports the data type `T`.
 * `&(algo_get_bounds_cmp(T))` gets the address of this function
 * and `algo_get_bounds_cmp(T)(items, n, cmp, min, max)` calls
 * the function.
 * 
 * This function is not pure since it will edit the value
 * of `min` and `max`, and `cmp` is not necessarily pure.
 * This function does not accept `NULL` arguments, if you
 * are using GCC you should add `__attribute__((nonnull))`
 * to its prototype.
 * 
 * Undefined behaviour is invoked `n == 0`.
 * 
 * @param  items  The items over to search.
 * @param  n      The number of elements in `items`.
 * @param  cmp    Function used to compare two items, it should return a.
 *                negative value if its first parameter is the lesser, a.
 *                positive value if its second parameter is the lesser.
 *                and zero if the parameters are equal.
 * @param  min    Output parameter for the minimum value.
 * @param  max    Output parameter for the maximum value.
 */
//>fun () {
void algo_get_bounds_cmp__##T(const T* restrict items, size_t n,
			      int (*cmp)(const T*, const T*),
			      T* min, T* max)
{
  const T* end = items + n;
  T maxv, minv, cur;
  size_t i;
  
  max = min = *items++;
  
  while (items != end)
    {
      cur = *items++;
      if      (cmp(cur, maxv) > 0)  maxv = cur;
      else if (cmp(cur, minv) < 0)  minv = cur;
    }
  
  *min = min;
  *max = max;
}
//>} ; . ../make_fun


/**
 * Find the minimum and maximum values in an array.
 * 
 * This variant of `algo_get_bounds_cmp` allows you to
 * store data needed for the comparison in a thread-safe
 * way. It is reentrant.
 * 
 * `algo_make_implementation_of_get_bounds_cmp_r(T)` is used to make
 * this function available for a particular data type `T`. And
 * implementation without modifiers and attributes will be
 * expanded. You may add `static`, `inline` and `__attribute__`
 * before calling `algo_make_implementation_of_get_bounds_cmp_r(T)`.
 * 
 * `algo_make_prototype_of_get_bounds_cmp(T)` is the prototype
 * counterpart of `algo_make_implementation_of_get_bounds_cmp_r(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_get_bounds_cmp_r(T)` is used to get the version of the
 * function that supports the data type `T`.
 * `&(algo_get_bounds_cmp_r(T))` gets the address of this function
 * and `algo_get_bounds_cmp_r(T)(items, n, cmp, data, min, max)`
 * calls the function.
 * 
 * This function is not pure since it will edit the value
 * of `min` and `max`, and `cmp` is not necessarily pure.
 * This function does not accept `NULL` arguments, if you
 * are using GCC you should add `__attribute__((nonnull))`
 * to its prototype.
 * 
 * Undefined behaviour is invoked `n == 0`.
 * 
 * @param  items  The items over to search.
 * @param  n      The number of elements in `items`.
 * @param  cmp    Function used to compare two items, it should return a
 *                negative value if its first parameter is the lesser, a
 *                positive value if its second parameter is the lesser
 *                and zero if the parameters are equal. `data` will be
 *                input as `cmp`'s third argument.
 * @param  data   Arbitrary data (may be `NULL`) to pass throught to `cmp`.
 * @param  min    Output parameter for the minimum value.
 * @param  max    Output parameter for the maximum value.
 */
//>fun () {
void algo_get_bounds_cmp_r__##T(const T* restrict items, size_t n,
				int (*cmp)(const T*, const T*, void*),
				void* data, T* min, T* max)
{
  const T* end = items + n;
  T maxv, minv, cur;
  size_t i;
  
  max = min = *items++;
  
  while (items != end)
    {
      cur = *items++;
      if      (cmp(cur, maxv, data) > 0)  maxv = cur;
      else if (cmp(cur, minv, data) < 0)  minv = cur;
    }
  
  *min = min;
  *max = max;
}
//>} ; . ../make_fun


#endif

