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
 * Context that typically accompanies another stateless component such as a number parser or formatter that involves decimals.
 */
public interface DecimalNumberContext extends DecimalNumberSymbolsLike,
    NumberContext,
    HasMathContext,
    HasDecimalNumberSymbols {

    /**
     * A useful default number of digits constant, useful when formatting decimal numbers into text.
     */
    int DEFAULT_NUMBER_DIGIT_COUNT = 9;
}
