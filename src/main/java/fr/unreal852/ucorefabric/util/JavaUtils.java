package fr.unreal852.ucorefabric.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

public final class JavaUtils
{
    /**
     * Returns a pseudorandom {@code int} value between the specified minimum and the specified maximum.
     *
     * @param min The minimum value (inclusive)
     * @param max The maximum value (exclusive)
     * @return a pseudorandom {@code int} value between the specified minimum and the specified maximum.
     */
    public static int randInt(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    /**
     * Returns a pseudorandom {@code double} value between the specified minimum and the specified maximum.
     *
     * @param min The minimum value (inclusive)
     * @param max The maximum value (exclusive)
     * @return a pseudorandom {@code double} value between the specified minimum and the specified maximum.
     */
    public static double randDouble(double min, double max)
    {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    /**
     * Returns a pseudorandom {@code long} value between the specified minimum and the specified maximum.
     *
     * @param min The minimum value (inclusive)
     * @param max The maximum value (exclusive)
     * @return a pseudorandom {@code long} value between the specified minimum and the specified maximum.
     */
    public static long randLong(long min, long max)
    {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    /**
     * Finds a specific element in the specified iterable using the specified predicate.
     *
     * @param iterable  Elements to search from
     * @param predicate Find Conditions
     * @param <T>       Find Type
     * @return T if a element has been found, null otherwise
     */
    public static <T> T find(Iterable<T> iterable, Predicate<T> predicate)
    {
        if (iterable == null || predicate == null)
            return null;
        for (T element : iterable)
        {
            if (predicate.test(element))
                return element;
        }
        return null;
    }
}
