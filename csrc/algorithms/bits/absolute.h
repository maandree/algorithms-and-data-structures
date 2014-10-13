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
#ifndef ALGO_ALGORITHMS_BITS_ABSOLUTE_H
#define ALGO_ALGORITHMS_BITS_ABSOLUTE_H


/* NB! This will not play nice if the placeholder `T` is
 * not set to a type only containing [0-9A-Za-z_] (and $
 * in GNU C). Therefore, with the exception of `char`,
 * `short`, `int`, `long`, `float` and `double`, you
 * should only use `typedef`:ed types. */


#include <limits.h>


/* Note: This file is purely academical, your compiler should
 * be able to select the fast way to compute the absolute value
 * if you write `(a > 0 ? a : -a)`, in GNU C:
 * 
 *     ({
 *         typeof(a) a_ = a;
 *         a_ > 0 ? a : -a;
 *     })
 * 
 * GCC also have built in functions like `__builtin_labs`
 * for calculating the the absolute value of a value.
 */


/**
 * Compute the absolute value of an integer.
 * 
 * This function only works on integer types. And it
 * assumes that two's complement is used, which is
 * always true if you use GCC and is true for most
 * (all?) high-level programming languages.
 * 
 * `algo_make_implementation_of_abs_twos_complement(T)`
 * is used to make this function available for a particular
 * data type `T`. And implementation without modifiers and
 * attributes will be expanded. You may add `static`,
 * `inline` and `__attribute__` before calling
 * `algo_make_implementation_of_abs_twos_complement(T)`.
 * 
 * `algo_make_prototype_of_abs_twos_complement(T)`
 * is the prototype counterpart of
 * `algo_make_implementation_of_abs_twos_complement(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_abs_twos_complement(T)` is used to get the version
 * of the function that supports the data type `T`.
 * `&(algo_abs_twos_complement(T))` gets the address of this
 * function and `algo_abs_twos_complement(T)(items, n, min, max)`
 * calls the function.
 * 
 * This function is constant, if you are using GCC you
 * should add `__attribute__((const))` to its prototype.
 * 
 * @param   value  The value whose absolute value should be calculated.
 * @return         The absolute value of `value`. If `value` is its
 *                 minimum possible value, `value` will be returned
 *                 verbatim because of overflow.
 */
//>fun () {
T algo_abs_twos_complement__##T(T value)
{
  /* Compiles to three operations. */
  T mask = value >> (sizeof(T) * CHAR_BIT - 1);
  return (value ^ mask) - mask;
  /* Or alternatively: (value + mask) ^ mask */
}
//>} ; . ../make_fun


/**
 * Compute the absolute value of an integer.
 * 
 * This function only works on integer types. And it
 * assumes that ones' complement is used, which is
 * seldom true and is always false if you use GCC.
 * 
 * `algo_make_implementation_of_abs_ones_complement(T)`
 * is used to make this function available for a particular
 * data type `T`. And implementation without modifiers and
 * attributes will be expanded. You may add `static`,
 * `inline` and `__attribute__` before calling
 * `algo_make_implementation_of_abs_ones_complement(T)`.
 * 
 * `algo_make_prototype_of_abs_ones_complement(T)`
 * is the prototype counterpart of
 * `algo_make_implementation_of_abs_ones_complement(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_abs_ones_complement(T)` is used to get the version
 * of the function that supports the data type `T`.
 * `&(algo_abs_ones_complement(T))` gets the address of this
 * function and `algo_abs_ones_complement(T)(items, n, min, max)`
 * calls the function.
 * 
 * This function is constant, if you are using GCC you
 * should add `__attribute__((const))` to its prototype.
 * 
 * @param   value  The value whose absolute value should be calculated.
 * @return         The absolute value of `value`.
 */
//>fun () {
T algo_abs_ones_complement__##T(T value)
{
  /* Compiles to two operations. */
  T mask = value >> (sizeof(T) * CHAR_BIT - 1);
  return value ^ mask;
}
//>} ; . ../make_fun


/**
 * Compute the absolute value of an integer.
 * 
 * This function only works on integer types. And it
 * assumes that sign–magnitude is used, which is
 * seldom true and is always false if you use GCC.
 * 
 * `algo_make_implementation_of_abs_sign_magnitude(T)`
 * is used to make this function available for a particular
 * data type `T`. And implementation without modifiers and
 * attributes will be expanded. You may add `static`,
 * `inline` and `__attribute__` before calling
 * `algo_make_implementation_of_abs_sign_magnitude(T)`.
 * 
 * `algo_make_prototype_of_abs_sign_magnitude(T)`
 * is the prototype counterpart of
 * `algo_make_implementation_of_abs_sign_magnitude(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_abs_sign_magnitude(T)` is used to get the version
 * of the function that supports the data type `T`.
 * `&(algo_abs_sign_magnitude(T))` gets the address of this
 * function and `algo_abs_sign_magnitude(T)(items, n, min, max)`
 * calls the function.
 * 
 * This function is constant, if you are using GCC you
 * should add `__attribute__((const))` to its prototype.
 * 
 * @param   value  The value whose absolute value should be calculated.
 * @return         The absolute value of `value`.
 */
//>fun () {
T algo_abs_sign_magnitude__##T(T value)
{
  /* Compiles to one operation. */
  const T magnitude = ~(1 ^ -1);
  return value & magnitude;
  /* Or alternatively: magnitude = ~(1 << (sizeof(T) * CHAR_BIT - 1)) */
}
//>} ; . ../make_fun


#endif

