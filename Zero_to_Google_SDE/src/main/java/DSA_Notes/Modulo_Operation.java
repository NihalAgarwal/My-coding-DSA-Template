package DSA_Notes;

/****
 *
 * There is a difference between "remainder" and "Modulo" operation. For "%", java returns remainder.
 * This difference can not be seen when both dividend and divisor are positive, as in that case modulo and remainder is both same.
 * This difference can be noticed for negative value dividend (in Java).
 *
 * Java decides the sign value of modulo result, on the basis of sign of dividend (numerator) whereas python decides on the basis of divisor.
 *
 * 13 % 3 = 1  ==> correct
 * 13 % -3 = 1 ( but as per Euclidean it should be 2 )
 * -13 % 3 = -1 ( but as per Euclidean  should be 2 )
 * -13 % -3 = -1 ( according to Eucledian it should be 2 )
 *
 * Euclidean division is superior to the other ones.
 * Raymond T. Boute describes the Euclidean definition in which the remainder is non-negative always, 0 ≤ r, and is thus consistent with the Euclidean division algorithm
 *
 *
 * 0 % a = 0
 * a % 0 == undefined
 * a % 1 = a % -1 = -a % 1 = -a % -1 == 0
 *
 * I) if a > 0 && b > 0
 *      mod = a % b
 *
 * II) if a < 0 && b > 0
 *      mod = a % b + b
 *      Ex: a = -13, b = 3 ==> -13 % 3 + 3 = 2
 *
 * III) if a < 0 && b < 0
 *      mod = a % b - b
 *      Ex: a = -13, b = -3 ==> -13 % -3 - (-3) = -13 % -3 + 3 = 2
 *
 * IV) if a > 0 && b < 0
 *      mod = a % b
 *      Ex : a = 13, b = -3 ==> 13 % -3 = 1 ( as Eucledian modulo is always positive and java decides the sign of modulo answer on the basis of dividend (numerator) )
 *
 *  Common solution for above 4 cases : (a % b + b) % b ==> IT WILL ALWAYS GIVE POSITIVE VALUE
 *
 * Modulo vs Modulus
 * Modulo : It is basically an operator which is denoted by “mod” and, in programming, uses “%”.
 *          It is a function which returns the remainder value when a number is divided by another number.
 * Modulus : It is simply a noun which is the value N in the expression “x mod N”. We abbreviate it as N is the modulus.
 *
 *
 */

public class Modulo_Operation {
    public int modulo( int a, int b )
    {
        if( a == 0 || Math.abs(b) == 1 )
            return 0;
        int res = a % b;
        if( res < 0 )
            return b < 0 ? res - b : res + b;
        return res;
    }

    // OR

    public int modulo2( int a, int b )
    {
        return ( a % b + b ) % b;
    }
}
