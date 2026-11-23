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

/**
 * Defines some {@link DecimalNumberSymbols} properties that also exist in {@link NumberContext}.
 */
public interface NumberContextLike {

    /**
     * Returns the negative sign.
     */
    char negativeSign();

    /**
     * Returns the numeric value for the given digit or -1 if it is invalid.
     */
    default int digit(final char c) {
        final int value = c - this.zeroDigit();

        return value < 0 || value > 9 ?
            -1 :
            value;
    }

    /**
     * Returns the positive sign.
     */
    char positiveSign();

    /**
     * Returns the zero digit
     */
    char zeroDigit();
}
