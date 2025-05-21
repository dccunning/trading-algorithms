package com.dccunning.utils;

import com.dccunning.utils.MathUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class MathUtilsTest {
    @Test
    void mean_ofNaturalNumbers() {
        assertEquals(5.5, MathUtils.mean(new double[]{1,2,3,4,5,6,7,8,9,10}));
    }

    @Test
    void mean_ofEmptyArray() {
        assertEquals(Double.NaN, MathUtils.mean(new double[]{}));
    }

    @Test
    void mean_ofSingleElement() {
        assertEquals(12345, MathUtils.mean(new double[]{12345}));
    }

    @Test
    void mean_ofMixedSigns() {
        assertEquals(0, MathUtils.mean(new double[]{-1, 1, -1, 1}));
    }

    @Test
    void stdDev_ofNaturalNumbers() {
        assertEquals(Math.sqrt(8.25), MathUtils.stdDev(new double[]{1,2,3,4,5,6,7,8,9,10}));
    }

    @Test
    void stdDev_ofEmptyArray() {
        assertEquals(Double.NaN, MathUtils.stdDev(new double[]{}));
    }

    @Test
    void stdDev_ofMixedSigns() {
        assertEquals(1, MathUtils.stdDev(new double[]{-1, 1, -1, 1}));
    }


}