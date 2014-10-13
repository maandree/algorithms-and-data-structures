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
#ifndef ALGO_ALGORITHMS_ARRAYS_ROTATE_H
#define ALGO_ALGORITHMS_ARRAYS_ROTATE_H


/* NB! This will not play nice if the placeholder `T` is
 * not set to a type only containing [0-9A-Za-z_] (and $
 * in GNU C). Therefore, with the exception of `char`,
 * `short`, `int`, `long`, `float` and `double`, you
 * should only use `typedef`:ed types. */


#include <stddef.h>


#define algo_macro_swap_items()  \
  (temp = items[i], items[i] = temps[j], items[j] = temp)


/**
 * Rotate an array.
 * 
 * This function perform an inline rotation, it is probably
 * faster if you create a new array and make a rotated copy
 * into that array. It may even more faster to create a
 * temporary array and copy the content back than using
 * this inline rotation if you want the rotation to be
 * stored in the same array. If you choose that latter,
 * `alloca` can be used to create new array on the stack.
 * 
 * `algo_make_implementation_of_rotate(T)` is used to make
 * this function available for a particular data type `T`. And
 * implementation without modifiers and attributes will be
 * expanded. You may add `static`, `inline` and `__attribute__`
 * before calling `algo_make_implementation_of_rotate(T)`.
 * 
 * `algo_make_prototype_of_rotate(T)` is the prototype
 * counterpart of `algo_make_implementation_of_rotate(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_rotate(T)` is used to get the version of the
 * function that supports the data type `T`.
 * `&(algo_rotate(T))` gets the address of this function
 * and `algo_rotate(T)(items, n)` calls the function.
 * 
 * Undefined behaviour is invoked if `steps >= n`.
 * 
 * @param  items  The array to rotate.
 * @param  n      The number of elements in `items`.
 * @param  steps  The number of steps to rotate the array
 *                rightwards. If you want a leftwards
 *                rotation you used call
 *                `algo_rotate(T)(items, n, (n - steps) % n)`.
 */
//>fun () {
void algo_rotate__##T(T* restrict items, size_t n, size_t steps)
{
  size_t i, j;
  T temp;
  
  steps = (n - steps) % n;
  for (i = 0,     j = steps - 1; i < j; i++, j--)  algo_macro_swap_items();
  for (i = steps, j =     n - 1; i < j; i++, j--)  algo_macro_swap_items();
  for (i = 0,     j =     n - 1; i < j; i++, j--)  algo_macro_swap_items();
}
//>} ; . ../make_fun


/**
 * Rotate an array and reverse it afterwords.
 * 
 * `algo_make_implementation_of_rotate_reverse(T)` is used
 * to make this function available for a particular data
 * type `T`. And implementation without modifiers and
 * attributes will be expanded. You may add `static`,
 * `inline` and `__attribute__` before calling
 * `algo_make_implementation_of_rotate_reverse(T)`.
 * 
 * `algo_make_prototype_of_rotate_reverse(T)` is the prototype
 * counterpart of `algo_make_implementation_of_rotate_reverse(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_rotate_reverse(T)` is used to get the version of the
 * function that supports the data type `T`.
 * `&(algo_rotate_reverse(T))` gets the address of this function
 * and `algo_rotate_reverse(T)(items, n)` calls the function.
 * 
 * Undefined behaviour is invoked if `steps >= n`.
 * 
 * @param  items  The array to rotate.
 * @param  n      The number of elements in `items`.
 * @param  steps  The number of steps to rotate the array
 *                rightwards. If you want a leftwards
 *                rotation you used call
 *                `algo_rotate_reverse(T)(items, n, (n - steps) % n)`.
 */
//>fun () {
void algo_rotate_reverse__##T(T* restrict items, size_t n, size_t steps)
{
  size_t i, j;
  T temp;
  
  steps = (n - steps) % n;
  for (i = 0,     j = steps - 1; i < j; i++, j--)  algo_macro_swap_items();
  for (i = steps, j =     n - 1; i < j; i++, j--)  algo_macro_swap_items();
}
//>} ; . ../make_fun


/**
 * Reverse an array and rotate it afterwords.
 * 
 * `algo_make_implementation_of_reverse_rotate(T)` is used
 * to make this function available for a particular data
 * type `T`. And implementation without modifiers and
 * attributes will be expanded. You may add `static`,
 * `inline` and `__attribute__` before calling
 * `algo_make_implementation_of_reverse_rotate(T)`.
 * 
 * `algo_make_prototype_of_reverse_rotate(T)` is the prototype
 * counterpart of `algo_make_implementation_of_reverse_rotate(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_reverse_rotate(T)` is used to get the version of the
 * function that supports the data type `T`.
 * `&(algo_reverse_rotate(T))` gets the address of this function
 * and `algo_reverse_rotate(T)(items, n)` calls the function.
 * 
 * Undefined behaviour is invoked if `steps >= n`.
 * 
 * @param  items  The array to rotate.
 * @param  n      The number of elements in `items`.
 * @param  steps  The number of steps to rotate the array
 *                rightwards. If you want a leftwards
 *                rotation you used call
 *                `algo_reverse_rotate(T)(items, n, (n - steps) % n)`.
 */
//>fun () {
void algo_reverse_rotate__##T(T* restrict items, size_t n, size_t steps)
{
  size_t i, j;
  T temp;
  
  for (i = 0,     j = steps - 1; i < j; i++, j--)  algo_macro_swap_items();
  for (i = steps, j =     n - 1; i < j; i++, j--)  algo_macro_swap_items();
}
//>} ; . ../make_fun


/**
 * Write a rotated copy of an array into another array.
 * 
 * `algo_make_implementation_of_rotate_into(T)` is used to make
 * this function available for a particular data type `T`. And
 * implementation without modifiers and attributes will be
 * expanded. You may add `static`, `inline` and `__attribute__`
 * before calling `algo_make_implementation_of_rotate_into(T)`.
 * 
 * `algo_make_prototype_of_rotate_into(T)` is the prototype
 * counterpart of `algo_make_implementation_of_rotate_into(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_rotate_into(T)` is used to get the version of the
 * function that supports the data type `T`.
 * `&(algo_rotate_into(T))` gets the address of this function
 * and `algo_rotate_into(T)(items, n)` calls the function.
 * 
 * Undefined behaviour is invoked if `steps >= n`.
 * 
 * @param  items  The array to rotate.
 * @param  out    The array to fill with a rotated copy of `items`.
 * @param  n      The number of elements in `items` and in `out`.
 * @param  steps  The number of steps to rotate the array
 *                rightwards. If you want a leftwards
 *                rotation you used call
 *                `algo_rotate_into(T)(items, out, n, (n - steps) % n)`.
 */
//>fun () {
void algo_rotate_into__##T(const T* restrict items, T* restrict out, size_t n, size_t steps)
{
  size_t m = n - steps;
  T* restrict out_a = out + steps;
  T* restrict out_b = out;
  const T* end_a = items + m;
  const T* end_b = items + n;
  
  while (items != end_a)  *out_a++ = *items++;
  while (items != end_b)  *out_b++ = *items++;
}
//>} ; . ../make_fun

  
#undef algo_macro_swap_items


/**
 * Write a rotated and reversed copy of an array into another array.
 * 
 * `algo_make_implementation_of_rotate_reverse_into(T)` is used to
 * make this function available for a particular data type `T`. And
 * implementation without modifiers and attributes will be expanded.
 * You may add `static`, `inline` and `__attribute__` before calling
 * `algo_make_implementation_of_rotate_reverse_into(T)`.
 * 
 * `algo_make_prototype_of_rotate_reverse_into(T)` is the prototype
 * counterpart of `algo_make_implementation_of_rotate_reverse_into(T)`.
 * It too is will not add any modifiers or attributes by default. It
 * will neither add a semicolon at the end of the prototype.
 * 
 * `algo_rotate_reverse_into(T)` is used to get the version of the
 * function that supports the data type `T`.
 * `&(algo_rotate_reverse_into(T))` gets the address of this function
 * and `algo_rotate_reverse_into(T)(items, n)` calls the function.
 * 
 * Undefined behaviour is invoked if `steps >= n`.
 * 
 * @param  items  The array to rotate.
 * @param  out    The array to fill with a rotated copy of `items`.
 * @param  n      The number of elements in `items` and in `out`.
 * @param  steps  The number of steps to rotate the array
 *                rightwards. If you want a leftwards
 *                rotation you used call
 *                `algo_rotate_reverse_into(T)(items, out, n, (n - steps) % n)`.
 */
//>fun () {
void algo_rotate_reverse_into__##T(const T* restrict items, T* restrict out, size_t n, size_t steps)
{
  size_t m = n - steps;
  T* restrict out_a = out + n - steps;
  T* restrict out_b = out + n;
  const T* end_a = items + m;
  const T* end_b = items + n;
  
  while (items != end_a)  *--out_a = *items++;
  while (items != end_b)  *--out_b = *items++;
}
//>} ; . ../make_fun


/**
 * Write a reversed and rotated copy of an array into another array.
 * 
 * `algo_make_implementation_of_reverse_rotate_into(T)` is used to
 * make this function available for a particular data type `T`. And
 * implementation without modifiers and attributes will be expanded.
 * You may add `static`, `inline` and `__attribute__` before calling
 * `algo_make_implementation_of_reverse_rotate_into(T)`.
 * 
 * `algo_make_prototype_of_reverse_rotate_into(T)` is the prototype
 * counterpart of `algo_make_implementation_of_reverse_rotate_into(T)`.
 * It too is will not add any modifiers or attributes by default. It
 * will neither add a semicolon at the end of the prototype.
 * 
 * `algo_reverse_rotate_into(T)` is used to get the version of the
 * function that supports the data type `T`.
 * `&(algo_reverse_rotate_into(T))` gets the address of this function
 * and `algo_reverse_rotate_into(T)(items, n)` calls the function.
 * 
 * Undefined behaviour is invoked if `steps >= n`.
 * 
 * @param  items  The array to rotate.
 * @param  out    The array to fill with a rotated copy of `items`.
 * @param  n      The number of elements in `items` and in `out`.
 * @param  steps  The number of steps to rotate the array
 *                rightwards. If you want a leftwards
 *                rotation you used call
 *                `algo_reverse_rotate_into(T)(items, out, n, (n - steps) % n)`.
 */
//>fun () {
void algo_reverse_rotate_into__##T(const T* restrict items, T* restrict out, size_t n, size_t steps)
{
  size_t m = n - steps;
  T* restrict out_a = out + steps;
  T* restrict out_b = out;
  const T* end_a = out + n;
  const T* end_b = out + steps;
  
  while (out_a != end_a)  *out_a++ = *--items;
  while (out_b != end_b)  *out_b++ = *--items;
}
//>} ; . ../make_fun

  
#undef algo_macro_swap_items


#endif

