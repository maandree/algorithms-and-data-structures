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
#ifndef ALGO_ALGORITHMS_BITS_POWERS_H
#define ALGO_ALGORITHMS_BITS_POWERS_H


/* NB! This will not play nice if the placeholder `T` is
 * not set to a type only containing [0-9A-Za-z_] (and $
 * in GNU C). Therefore, with the exception of `char`,
 * `short`, `int`, `long`, `float` and `double`, you
 * should only use `typedef`:ed types. */

#include <limits.h>

/* Note: These functions assume C-rules for integer encoding,
 * namely, then n:th bit represent 2 to the power of n, and
 * the bitwise or is equivalent to addition if all operands
 * are single-bit valued. */


/**
 * Compute whether an integer is a power of two.
 * Note that zero is indeed not a power of two.
 * 
 * This function only works on integer types. And it
 * assumes that C-rules for integer encoding applies.
 * 
 * `algo_make_implementation_of_is_power_of_2(T)`
 * is used to make this function available for a particular
 * data type `T`. And implementation without modifiers and
 * attributes will be expanded. You may add `static`,
 * `inline` and `__attribute__` before calling
 * `algo_make_implementation_of_is_power_of_2(T)`.
 * 
 * `algo_make_prototype_of_is_power_of_2(T)`
 * is the prototype counterpart of
 * `algo_make_implementation_of_is_power_of_2(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_is_power_of_2(T)` is used to get the version
 * of the function that supports the data type `T`.
 * `&(algo_is_power_of_2(T))` gets the address of this
 * function and `algo_is_power_of_2(T)(items, n, min, max)`
 * calls the function.
 * 
 * This function is constant, if you are using GCC you
 * should add `__attribute__((const))` to its prototype.
 * 
 * @param   value  The integer.
 * @return         Whether the integer is a power of two.
 */
//>fun () {
int algo_is_power_of_2__##T(T value)
{
  /* The left hand side clears the least significant bit set. */
  return (value & (value - 1)) == 0;
  /* Or alternatively: (value & -value) == value*/
}
//>} ; . ../make_fun


/**
 * Computes the nearest, but higher, power of two,
 * independently of whether the current value is
 * a power of two or not.
 * 
 * This function only works on integer types. And it
 * assumes that C-rules for integer encoding applies.
 * 
 * `algo_make_implementation_of_next_power_of_2(T)`
 * is used to make this function available for a particular
 * data type `T`. And implementation without modifiers and
 * attributes will be expanded. You may add `static`,
 * `inline` and `__attribute__` before calling
 * `algo_make_implementation_of_next_power_of_2(T)`.
 * 
 * `algo_make_prototype_of_next_power_of_2(T)`
 * is the prototype counterpart of
 * `algo_make_implementation_of_next_power_of_2(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_next_power_of_2(T)` is used to get the version
 * of the function that supports the data type `T`.
 * `&(algo_next_power_of_2(T))` gets the address of this
 * function and `algo_next_power_of_2(T)(items, n, min, max)`
 * calls the function.
 * 
 * This function is constant, if you are using GCC you
 * should add `__attribute__((const))` to its prototype.
 * 
 * Undefined behaviour is invoked if `value` is negative.
 * 
 * @param   value  The current value, must be non-negative.
 * @return         The next power of two.
 */
//>fun () {
T algo_next_power_of_2__##T(T value)
{
  size_t i, n = sizeof(T);
  value |= value >> 1;
  value |= value >> 2;
  value |= value >> 3;
  for (i = 1; n > i; i <<= 1)
    value |= value >> (i * 8);
  return value + 1;
}
//>} ; . ../make_fun

/* Hopefully you compiler can unroll the loop fully
 * and optimise out our helper variables. These are
 * added simply for flexibility and not assuming
 * any limitations on how large an integer can be. */


/**
 * Computes the nearest, but higher, power of two,
 * but only if the current value is not a power of two.
 * If the current value is a power of 2, that value
 * will be returned.
 * 
 * This function only works on integer types. And it
 * assumes that C-rules for integer encoding applies.
 * 
 * `algo_make_implementation_of_next_power_of_2(T)`
 * is used to make this function available for a particular
 * data type `T`. And implementation without modifiers and
 * attributes will be expanded. You may add `static`,
 * `inline` and `__attribute__` before calling
 * `algo_make_implementation_of_next_power_of_2(T)`.
 * 
 * `algo_make_prototype_of_next_power_of_2(T)`
 * is the prototype counterpart of
 * `algo_make_implementation_of_next_power_of_2(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_next_power_of_2(T)` is used to get the version
 * of the function that supports the data type `T`.
 * `&(algo_next_power_of_2(T))` gets the address of this
 * function and `algo_next_power_of_2(T)(items, n, min, max)`
 * calls the function.
 * 
 * This function is constant, if you are using GCC you
 * should add `__attribute__((const))` to its prototype.
 * 
 * Undefined behaviour is invoked if `value` is zero or
 * negative.
 * 
 * @param   value  The current value, must be positive.
 * @return         The next power of two, or the current
 *                 value if it already is a power of 2.
 */
//>fun () {
T algo_next_power_of_2__##T(T value)
{
  size_t i, n = sizeof(T);
  value -= 1;
  value |= value >> 1;
  value |= value >> 2;
  value |= value >> 3;
  for (i = 1; n > i; i <<= 1)
    value |= value >> (i * 8);
  return value + 1;
}
//>} ; . ../make_fun


/**
 * Computes the nearest, but higher, power of two,
 * but only if the current value is not a power of two.
 * If the current value is a power of 2, that value
 * will be returned. However, if the value is non-positive,
 * zero will be returned.
 * 
 * This function only works on integer types. And it
 * assumes that C-rules for integer encoding applies.
 * 
 * `algo_make_implementation_of_next_power_of_2_or_zero(T)`
 * is used to make this function available for a particular
 * data type `T`. And implementation without modifiers and
 * attributes will be expanded. You may add `static`,
 * `inline` and `__attribute__` before calling
 * `algo_make_implementation_of_next_power_of_2_or_zero(T)`.
 * 
 * `algo_make_prototype_of_next_power_of_2_or_zero(T)`
 * is the prototype counterpart of
 * `algo_make_implementation_of_next_power_of_2_or_zero(T)`.
 * It too is will not add any modifiers or attributes by
 * default. It will neither add a semicolon at the end of
 * the prototype.
 * 
 * `algo_next_power_of_2_or_zero(T)` is used to get the version
 * of the function that supports the data type `T`.
 * `&(algo_next_power_of_2_or_zero(T))` gets the address of this
 * function and `algo_next_power_of_2_or_zero(T)(items, n, min, max)`
 * calls the function.
 * 
 * This function is constant, if you are using GCC you
 * should add `__attribute__((const))` to its prototype.
 * 
 * `algo_next_power_of_2_or_zero(T)(value | 1)` would behave
 * the same way, except, 0 would map to 1.
 * 
 * @param   value  The current value.
 * @return         The next power of two or zero, or the
 *                 current value if it already is a power
 *                 of 2 or zero.
 */
//>fun () {
T algo_next_power_of_2_or_zero__##T(T value)
{
  size_t i, n = sizeof(T);
  value -= 1;
  value &= ~((1 << (n * CHAR_BIT - 1)) - 1);
  value |= value >> 1;
  value |= value >> 2;
  value |= value >> 3;
  for (i = 1; n > i; i <<= 1)
    value |= value >> (i * 8);
  return value + 1;
}
//>} ; . ../make_fun


#endif

