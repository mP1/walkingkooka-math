/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.math;

import org.junit.jupiter.api.Test;
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.reflect.PublicStaticHelperTesting;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class MathsTest implements ClassTesting2<Maths>,
        PublicStaticHelperTesting<Maths> {

    @Test
    public void testIsNumberNull() {
        this.isNumberAndCheck(null, false);
    }

    @Test
    public void testIsNumberByte() {
        this.isNumberAndCheck(Byte.MAX_VALUE);
    }

    @Test
    public void testIsNumberShort() {
        this.isNumberAndCheck(Short.MAX_VALUE);
    }

    @Test
    public void testIsNumberInteger() {
        this.isNumberAndCheck(Integer.MAX_VALUE);
    }

    @Test
    public void testIsNumberLong() {
        this.isNumberAndCheck(Long.MAX_VALUE);
    }

    @Test
    public void testIsNumberFloat() {
        this.isNumberAndCheck(Float.MAX_VALUE);
    }

    @Test
    public void testIsNumberDouble() {
        this.isNumberAndCheck(Double.MAX_VALUE);
    }

    @Test
    public void testIsNumberBigInteger() {
        this.isNumberAndCheck(BigInteger.ZERO);
    }

    @Test
    public void testIsNumberBigDecimal() {
        this.isNumberAndCheck(BigDecimal.ONE);
    }

    @Test
    public void testIsNumberNonJdkNumberType() {
        this.isNumberAndCheck(new Number() {
            private static final long serialVersionUID = 0;

            @Override
            public int intValue() {
                return 0;
            }

            @Override
            public long longValue() {
                return 0;
            }

            @Override
            public float floatValue() {
                return 0;
            }

            @Override
            public double doubleValue() {
                return 0;
            }
        }, false);
    }

    @Test
    public void testIsNumber() {
        this.isNumberAndCheck(this, false);
    }

    private void isNumberAndCheck(final Number value) {
        this.isNumberAndCheck(value, true);
    }

    private void isNumberAndCheck(final Object value, final boolean expected) {
        assertEquals(expected, Maths.isNumber(value));

        final Class<?> type = null != value ? value.getClass() : null;
        assertEquals(expected, Maths.isNumberClass(type));
    }

    // toBigDecimalRoundingMode.........................................................................................

    @Test
    public void testToBigDecimalRoundingModeNullFails() {
        assertThrows(NullPointerException.class, () -> Maths.toBigDecimalRoundingMode(null));
    }

    @Test
    public void testToBigDecimalRoundingModeCeiling() {
        this.toBigDecimalRoundingModeAndCheck(RoundingMode.CEILING, BigDecimal.ROUND_CEILING);
    }

    @Test
    public void testToBigDecimalRoundingModeDown() {
        this.toBigDecimalRoundingModeAndCheck(RoundingMode.DOWN, BigDecimal.ROUND_DOWN);
    }

    @Test
    public void testToBigDecimalRoundingModeFloor() {
        this.toBigDecimalRoundingModeAndCheck(RoundingMode.FLOOR, BigDecimal.ROUND_FLOOR);
    }

    @Test
    public void testToBigDecimalRoundingModeHalfDown() {
        this.toBigDecimalRoundingModeAndCheck(RoundingMode.HALF_DOWN, BigDecimal.ROUND_HALF_DOWN);
    }

    @Test
    public void testToBigDecimalRoundingModeHalfEven() {
        this.toBigDecimalRoundingModeAndCheck(RoundingMode.HALF_EVEN, BigDecimal.ROUND_HALF_EVEN);
    }

    @Test
    public void testToBigDecimalRoundingModeHalfUp() {
        this.toBigDecimalRoundingModeAndCheck(RoundingMode.HALF_UP, BigDecimal.ROUND_HALF_UP);
    }

    @Test
    public void testToBigDecimalRoundingModeUnnecessary() {
        this.toBigDecimalRoundingModeAndCheck(RoundingMode.UNNECESSARY, BigDecimal.ROUND_UNNECESSARY);
    }

    @Test
    public void testToBigDecimalRoundingModeUp() {
        this.toBigDecimalRoundingModeAndCheck(RoundingMode.UP, BigDecimal.ROUND_UP);
    }

    @Test
    public void testSevenRoundModeConstants() {
        assertEquals(8,
                RoundingMode.values().length,
                () -> Arrays.toString(RoundingMode.values()));
    }

    @Test
    public void testRoundModesAll() throws Exception {
        for (final RoundingMode mode : RoundingMode.values()) {
            this.toBigDecimalRoundingModeAndCheck(mode, (Integer) BigDecimal.class.getField("ROUND_" + mode.name()).get(null));
        }
    }

    private static void toBigDecimalRoundingModeAndCheck(final RoundingMode mode,
                                                         final int bigDecimal) {
        assertEquals(bigDecimal,
                Maths.toBigDecimalRoundingMode(mode),
                () -> "" + mode + " toBigDecimalRoundingMode ");
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<Maths> type() {
        return Maths.class;
    }

    @Override
    public boolean canHavePublicTypes(final Method method) {
        return false;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
