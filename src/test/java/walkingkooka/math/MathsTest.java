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
        this.checkEquals(expected, Maths.isNumber(value));

        final Class<?> type = null != value ? value.getClass() : null;
        this.checkEquals(expected, Maths.isNumberClass(type));
    }

    // round............................................................................................................

    @Test
    public void testRoundUp5_5() {
        this.roundUpAndCheck(
                5.5,
                6
        );
    }

    @Test
    public void testRoundUp2_5() {
        this.roundUpAndCheck(
                2.5,
                3
        );
    }

    @Test
    public void testRoundUp1_6() {
        this.roundUpAndCheck(
                1.6,
                2
        );
    }

    @Test
    public void testRoundUp1_1() {
        this.roundUpAndCheck(
                1.1,
                2
        );
    }

    @Test
    public void testRoundUp1_0() {
        this.roundUpAndCheck(
                1.0,
                1
        );
    }

    @Test
    public void testRoundUpMinus1_0() {
        this.roundUpAndCheck(
                -1.0,
                -1
        );
    }

    @Test
    public void testRoundUpMinus1_1() {
        this.roundUpAndCheck(
                -1.1,
                -2
        );
    }

    @Test
    public void testRoundUpMinus1_6() {
        this.roundUpAndCheck(
                -1.6,
                -2
        );
    }

    @Test
    public void testRoundUpMinus2_5() {
        this.roundUpAndCheck(
                -2.5,
                -3
        );
    }

    @Test
    public void testRoundUpMinus5_5() {
        this.roundUpAndCheck(
                -5.5,
                -6
        );
    }

    private void roundUpAndCheck(final double value,
                                 final double expectedValue) {
        this.roundAndCheck(
                value,
                RoundingMode.UP,
                expectedValue
        );
    }

    @Test
    public void testRoundDown5_5() {
        this.roundDownAndCheck(
                5.5,
                5
        );
    }

    @Test
    public void testRoundDown2_5() {
        this.roundDownAndCheck(
                2.5,
                2
        );
    }

    @Test
    public void testRoundDown1_6() {
        this.roundDownAndCheck(
                1.6,
                1
        );
    }

    @Test
    public void testRoundDown1_1() {
        this.roundDownAndCheck(
                1.1,
                1
        );
    }

    @Test
    public void testRoundDown1_0() {
        this.roundDownAndCheck(
                1.0,
                1
        );
    }

    @Test
    public void testRoundDownMinus1_0() {
        this.roundDownAndCheck(
                -1.0,
                -1
        );
    }

    @Test
    public void testRoundDownMinus1_1() {
        this.roundDownAndCheck(
                -1.1,
                -1
        );
    }

    @Test
    public void testRoundDownMinus1_6() {
        this.roundDownAndCheck(
                -1.6,
                -1
        );
    }

    @Test
    public void testRoundDownMinus2_5() {
        this.roundDownAndCheck(
                -2.5,
                -2
        );
    }

    @Test
    public void testRoundDownMinus5_5() {
        this.roundDownAndCheck(
                -5.5,
                -5
        );
    }

    private void roundDownAndCheck(final double value,
                                   final double expectedValue) {
        this.roundAndCheck(
                value,
                RoundingMode.DOWN,
                expectedValue
        );
    }

    @Test
    public void testRoundCeiling5_5() {
        this.roundCeilingAndCheck(
                5.5,
                6
        );
    }

    @Test
    public void testRoundCeiling2_5() {
        this.roundCeilingAndCheck(
                2.5,
                3
        );
    }

    @Test
    public void testRoundCeiling1_6() {
        this.roundCeilingAndCheck(
                1.6,
                2
        );
    }

    @Test
    public void testRoundCeiling1_1() {
        this.roundCeilingAndCheck(
                1.1,
                2
        );
    }

    @Test
    public void testRoundCeiling1_0() {
        this.roundCeilingAndCheck(
                1.0,
                1
        );
    }

    @Test
    public void testRoundCeilingMinus1_0() {
        this.roundCeilingAndCheck(
                -1.0,
                -1
        );
    }

    @Test
    public void testRoundCeilingMinus1_1() {
        this.roundCeilingAndCheck(
                -1.1,
                -1
        );
    }

    @Test
    public void testRoundCeilingMinus1_6() {
        this.roundCeilingAndCheck(
                -1.6,
                -1
        );
    }

    @Test
    public void testRoundCeilingMinus2_5() {
        this.roundCeilingAndCheck(
                -2.5,
                -2
        );
    }

    @Test
    public void testRoundCeilingMinus5_5() {
        this.roundCeilingAndCheck(
                -5.5,
                -5
        );
    }

    private void roundCeilingAndCheck(final double value,
                                      final double expectedValue) {
        this.roundAndCheck(
                value,
                RoundingMode.CEILING,
                expectedValue
        );
    }

    @Test
    public void testRoundFloor5_5() {
        this.roundFloorAndCheck(
                5.5,
                5
        );
    }

    @Test
    public void testRoundFloor2_5() {
        this.roundFloorAndCheck(
                2.5,
                2
        );
    }

    @Test
    public void testRoundFloor1_6() {
        this.roundFloorAndCheck(
                1.6,
                1
        );
    }

    @Test
    public void testRoundFloor1_1() {
        this.roundFloorAndCheck(
                1.1,
                1
        );
    }

    @Test
    public void testRoundFloor1_0() {
        this.roundFloorAndCheck(
                1.0,
                1
        );
    }

    @Test
    public void testRoundFloorMinus1_0() {
        this.roundFloorAndCheck(
                -1.0,
                -1
        );
    }

    @Test
    public void testRoundFloorMinus1_1() {
        this.roundFloorAndCheck(
                -1.1,
                -2
        );
    }

    @Test
    public void testRoundFloorMinus1_6() {
        this.roundFloorAndCheck(
                -1.6,
                -2
        );
    }

    @Test
    public void testRoundFloorMinus2_5() {
        this.roundFloorAndCheck(
                -2.5,
                -3
        );
    }

    @Test
    public void testRoundFloorMinus5_5() {
        this.roundFloorAndCheck(
                -5.5,
                -6
        );
    }

    private void roundFloorAndCheck(final double value,
                                    final double expectedValue) {
        this.roundAndCheck(
                value,
                RoundingMode.FLOOR,
                expectedValue
        );
    }

    @Test
    public void testRoundHalfUp5_5() {
        this.roundHalfUpAndCheck(
                5.5,
                6
        );
    }

    @Test
    public void testRoundHalfUp2_5() {
        this.roundHalfUpAndCheck(
                2.5,
                3
        );
    }

    @Test
    public void testRoundHalfUp1_6() {
        this.roundHalfUpAndCheck(
                1.6,
                2
        );
    }

    @Test
    public void testRoundHalfUp1_1() {
        this.roundHalfUpAndCheck(
                1.1,
                1
        );
    }

    @Test
    public void testRoundHalfUp1_0() {
        this.roundHalfUpAndCheck(
                1.0,
                1
        );
    }

    @Test
    public void testRoundHalfUpMinus1_0() {
        this.roundHalfUpAndCheck(
                -1.0,
                -1
        );
    }

    @Test
    public void testRoundHalfUpMinus1_1() {
        this.roundHalfUpAndCheck(
                -1.1,
                -1
        );
    }

    @Test
    public void testRoundHalfUpMinus1_6() {
        this.roundHalfUpAndCheck(
                -1.6,
                -2
        );
    }

    @Test
    public void testRoundHalfUpMinus2_5() {
        this.roundHalfUpAndCheck(
                -2.5,
                -3
        );
    }

    @Test
    public void testRoundHalfUpMinus5_5() {
        this.roundHalfUpAndCheck(
                -5.5,
                -6
        );
    }

    private void roundHalfUpAndCheck(final double value,
                                     final double expectedValue) {
        this.roundAndCheck(
                value,
                RoundingMode.HALF_UP,
                expectedValue
        );
    }

    @Test
    public void testRoundHalfDown5_5() {
        this.roundHalfDownAndCheck(
                5.5,
                5
        );
    }

    @Test
    public void testRoundHalfDown2_5() {
        this.roundHalfDownAndCheck(
                2.5,
                2
        );
    }

    @Test
    public void testRoundHalfDown1_6() {
        this.roundHalfDownAndCheck(
                1.6,
                2
        );
    }

    @Test
    public void testRoundHalfDown1_1() {
        this.roundHalfDownAndCheck(
                1.1,
                1
        );
    }

    @Test
    public void testRoundHalfDown1_0() {
        this.roundHalfDownAndCheck(
                1.0,
                1
        );
    }

    @Test
    public void testRoundHalfDownMinus1_0() {
        this.roundHalfDownAndCheck(
                -1.0,
                -1
        );
    }

    @Test
    public void testRoundHalfDownMinus1_1() {
        this.roundHalfDownAndCheck(
                -1.1,
                -1
        );
    }

    @Test
    public void testRoundHalfDownMinus1_6() {
        this.roundHalfDownAndCheck(
                -1.6,
                -2
        );
    }

    @Test
    public void testRoundHalfDownMinus2_5() {
        this.roundHalfDownAndCheck(
                -2.5,
                -2
        );
    }

    @Test
    public void testRoundHalfDownMinus5_5() {
        this.roundHalfDownAndCheck(
                -5.5,
                -5
        );
    }

    private void roundHalfDownAndCheck(final double value,
                                       final double expectedValue) {
        this.roundAndCheck(
                value,
                RoundingMode.HALF_DOWN,
                expectedValue
        );
    }

    @Test
    public void testRoundHalfEven5_5() {
        this.roundHalfEvenAndCheck(
                5.5,
                6
        );
    }

    @Test
    public void testRoundHalfEven2_5() {
        this.roundHalfEvenAndCheck(
                2.5,
                2
        );
    }

    @Test
    public void testRoundHalfEven1_6() {
        this.roundHalfEvenAndCheck(
                1.6,
                2
        );
    }

    @Test
    public void testRoundHalfEven1_1() {
        this.roundHalfEvenAndCheck(
                1.1,
                1
        );
    }

    @Test
    public void testRoundHalfEven1_0() {
        this.roundHalfEvenAndCheck(
                1.0,
                1
        );
    }

    @Test
    public void testRoundHalfEvenMinus1_0() {
        this.roundHalfEvenAndCheck(
                -1.0,
                -1
        );
    }

    @Test
    public void testRoundHalfEvenMinus1_1() {
        this.roundHalfEvenAndCheck(
                -1.1,
                -1
        );
    }

    @Test
    public void testRoundHalfEvenMinus1_6() {
        this.roundHalfEvenAndCheck(
                -1.6,
                -2
        );
    }

    @Test
    public void testRoundHalfEvenMinus2_5() {
        this.roundHalfEvenAndCheck(
                -2.5,
                -2
        );
    }

    @Test
    public void testRoundHalfEvenMinus5_5() {
        this.roundHalfEvenAndCheck(
                -5.5,
                -6
        );
    }

    private void roundHalfEvenAndCheck(final double value,
                                       final double expectedValue) {
        this.roundAndCheck(
                value,
                RoundingMode.HALF_EVEN,
                expectedValue
        );
    }

    @Test
    public void testRoundUnnecessary5_5() {
        this.roundUnnecessaryFails(
                5.5
        );
    }

    @Test
    public void testRoundUnnecessary2_5() {
        this.roundUnnecessaryFails(
                2.5
        );
    }

    @Test
    public void testRoundUnnecessary1_6() {
        this.roundUnnecessaryFails(
                1.6
        );
    }

    @Test
    public void testRoundUnnecessary1_1() {
        this.roundUnnecessaryFails(
                1.1
        );
    }

    @Test
    public void testRoundUnnecessary1_0() {
        this.roundUnnecessaryAndCheck(
                1.0,
                1
        );
    }

    @Test
    public void testRoundUnnecessaryMinus1_0() {
        this.roundUnnecessaryAndCheck(
                -1.0,
                -1
        );
    }

    @Test
    public void testRoundUnnecessaryMinus1_1() {
        this.roundUnnecessaryFails(
                -1.1
        );
    }

    @Test
    public void testRoundUnnecessaryMinus1_6() {
        this.roundUnnecessaryFails(
                -1.6
        );
    }

    @Test
    public void testRoundUnnecessaryMinus2_5() {
        this.roundUnnecessaryFails(
                -2.5
        );
    }

    @Test
    public void testRoundUnnecessaryMinus5_5() {
        this.roundUnnecessaryFails(
                -5.5
        );
    }

    private void roundUnnecessaryFails(final double value) {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Maths.round(value, RoundingMode.UNNECESSARY);
                }
        );
    }

    private void roundUnnecessaryAndCheck(final double value,
                                          final double expectedValue) {
        this.roundAndCheck(
                value,
                RoundingMode.UNNECESSARY,
                expectedValue
        );
    }

    private void roundAndCheck(final double value,
                               final RoundingMode roundingMode,
                               final double expectedValue) {
        this.checkEquals(
                expectedValue,
                Maths.round(value, roundingMode),
                () -> "round " + value + " " + roundingMode
        );
    }

    @Test
    public void testRoundManyValuesUp() {
        this.roundManyValuesAndCheck(RoundingMode.UP);
    }

    @Test
    public void testRoundManyValuesDown() {
        this.roundManyValuesAndCheck(RoundingMode.DOWN);
    }

    @Test
    public void testRoundManyValuesCeiling() {
        this.roundManyValuesAndCheck(RoundingMode.CEILING);
    }

    @Test
    public void testRoundManyValuesFloor() {
        this.roundManyValuesAndCheck(RoundingMode.FLOOR);
    }

    @Test
    public void testRoundManyValuesHalfUp() {
        this.roundManyValuesAndCheck(RoundingMode.HALF_UP);
    }

    @Test
    public void testRoundManyValuesHalfDown() {
        this.roundManyValuesAndCheck(RoundingMode.HALF_DOWN);
    }

    @Test
    public void testRoundManyValuesHalfEven() {
        this.roundManyValuesAndCheck(RoundingMode.HALF_EVEN);
    }

    private void roundManyValuesAndCheck(final RoundingMode roundingMode) {
        for(double value = -6.0; value < +6.0; value = value + 0.2) {
            final double finalValue = value;

            assertEquals(
                    new BigDecimal(value)
                            .setScale(0, roundingMode)
                            .doubleValue(),
                    Maths.round(value, roundingMode),
                    0.1,
                    () -> "round " + finalValue + " " + roundingMode
            );
        }
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
        this.checkEquals(8,
                RoundingMode.values().length,
                () -> Arrays.toString(RoundingMode.values()));
    }

    @Test
    public void testToBigDecimalRoundModesAll() throws Exception {
        for (final RoundingMode mode : RoundingMode.values()) {
            this.toBigDecimalRoundingModeAndCheck(mode, (Integer) BigDecimal.class.getField("ROUND_" + mode.name()).get(null));
        }
    }

    private void toBigDecimalRoundingModeAndCheck(final RoundingMode mode,
                                                  final int bigDecimal) {
        this.checkEquals(bigDecimal,
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
