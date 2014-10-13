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
#ifndef ALGO_ALGORITHMS_ARRAYS_REVERSE_H
#define ALGO_ALGORITHMS_ARRAYS_REVERSE_H


/* NB! This will not play nice if the placeholder `T` is
 * not set to a type only containing [0-9A-Za-z_] (and $
 * in GNU C). Therefore, with the exception of `char`,
 * `short`, `int`, `long`, `float` and `double`, you
 * should only use `typedef`:ed types. */


#include <stddef.h>


/**
 * Reverses an array.
 * 
 * `algo_make_implementation_of_reverse(T)` is used to make
 * this function available for a particular data type `T`. And
 * implementation without modifiers and attributes will be
 * expanded. You may add `static`, `inline` and `__attribute__`
 * before calling `algo_make_implementation_of_reverse(T)`.
 * 
 * `algo_make_prototype_of_reverse(T)` is the prototype
 * counterpart of `algo_make_implementation_of_reverse(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_reverse(T)` is used to get the version of the
 * function that supports the data type `T`.
 * `&(algo_reverse(T))` gets the address of this function
 * and `algo_reverse(T)(items, n)` calls the function.
 * 
 * @param  items  The array to reverse.
 * @param  n      The number of elements in `items`.
 */
//>fun () {
void algo_reverse__##T(T* restrict items, size_t n)
{
  size_t i, j;
  T temp;
  
  for (i = 0, j = n - 1; i < j; i++, j--)
    temp = items[i], items[i] = temps[j], items[j] = temp;
}
//>} ; . ../make_fun

/* For integer data (a ^= b, b ^= a, a ^= b) can be used.
 * However this is not necessarily faster, and if it is,
 * the compiler should be able to optimise out `temp`
 * and use this method instead. */


#endif

