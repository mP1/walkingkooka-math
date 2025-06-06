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

import walkingkooka.NeverError;
import walkingkooka.reflect.PublicStaticHelper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;

public final class Maths implements PublicStaticHelper {

    /**
     * Returns true if the given {@link Object} is a number as defined by {@link #isNumberClass(Class)}.
     */
    public static boolean isNumber(final Object value) {
        return null != value && isNumberClass(value.getClass());
    }

    /**
     * Returns true if the given {@link Class type} is a JDK Number type and not a custom type.
     * These are the same types supported by {@link NumberVisitor} and its visit methods.
     */
    public static boolean isNumberClass(final Class<?> type) {
        return type == BigDecimal.class ||
            type == BigInteger.class ||
            type == Byte.class ||
            type == Double.class ||
            type == Float.class ||
            type == Integer.class ||
            type == Long.class ||
            type == Short.class;
    }

    /**
     * Rounds the given double using the provided {@link RoundingMode}. Note NAN or INIFINITE values are returned as is.
     */
    public static double round(final double value,
                               final RoundingMode roundingMode) {
        Objects.requireNonNull(roundingMode, "roundingMode");

        return Double.isFinite(value) ?
            roundFinite(value, roundingMode) :
            value;
    }

    private static double roundFinite(final double value,
                                      final RoundingMode roundingMode) {
        double rounded = value;

        switch (roundingMode) {
            case UP:
                rounded = Math.ceil(
                    Math.abs(value)
                );

                rounded = value < 0 ? -rounded : rounded;
                break;
            case DOWN:
                rounded = Math.floor(
                    Math.abs(value)
                );

                rounded = value < 0 ? -rounded : rounded;
                break;
            case CEILING:
                rounded = Math.ceil(value);
                break;
            case FLOOR:
                rounded = Math.floor(value);
                break;
            case HALF_UP:
                rounded = halfUp(value);
                break;
            case HALF_DOWN:
                rounded = halfDown(value);
                break;
            case HALF_EVEN:
                rounded = ((((long) value) & 1) == 1) ?
                    halfUp(value) :
                    halfDown(value);
                break;
            case UNNECESSARY:
                rounded = Math.floor(value);
                if (rounded != value) {
                    throw new IllegalArgumentException("Invalid value " + value);
                }
                break;
            default:
                NeverError.unhandledEnum(roundingMode, RoundingMode.values());
        }

        return rounded;
    }

    private static double halfDown(final double value) {
        final double rounded = Math.ceil(
            Math.abs(value) - 0.5
        );

        return value < 0 ? -rounded : rounded;
    }

    private static double halfUp(final double value) {
        double rounded = Math.round(
            Math.abs(value)
        );
        return value < 0 ? -rounded : rounded;
    }

    /**
     * Attempts to convert the given {@link Number number} to a {@link BigDecimal}.
     */
    public static Optional<BigDecimal> toBigDecimal(final Number value) {
        return MathsToBigDecimalNumberVisitor.toBigDecimal(value);
    }

    /**
     * Converts a {@link RoundingMode} to equivalent {@link BigDecimal} rounding constant.
     */
    @SuppressWarnings("deprecation")
    public static int toBigDecimalRoundingMode(final RoundingMode mode) {
        Objects.requireNonNull(mode, "RoundingMode");

        final int bigDecimal;

        switch (mode) {
            case CEILING:
                bigDecimal = BigDecimal.ROUND_CEILING;
                break;
            case DOWN:
                bigDecimal = BigDecimal.ROUND_DOWN;
                break;
            case FLOOR:
                bigDecimal = BigDecimal.ROUND_FLOOR;
                break;
            case HALF_DOWN:
                bigDecimal = BigDecimal.ROUND_HALF_DOWN;
                break;
            case HALF_EVEN:
                bigDecimal = BigDecimal.ROUND_HALF_EVEN;
                break;
            case HALF_UP:
                bigDecimal = BigDecimal.ROUND_HALF_UP;
                break;
            case UNNECESSARY:
                bigDecimal = BigDecimal.ROUND_UNNECESSARY;
                break;
            case UP:
                bigDecimal = BigDecimal.ROUND_UP;
                break;
            default:
                bigDecimal = NeverError.unhandledEnum(mode, RoundingMode.values());
        }

        return bigDecimal;
    }

    /**
     * Stop creation
     */
    private Maths() {
        throw new UnsupportedOperationException();
    }
}
