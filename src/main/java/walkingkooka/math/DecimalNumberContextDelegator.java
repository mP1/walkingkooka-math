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

import java.math.MathContext;

public interface DecimalNumberContextDelegator extends DecimalNumberContext,
    NumberContextDelegator {

    @Override
    default String currencySymbol() {
        return this.decimalNumberContext().currencySymbol();
    }

    @Override
    default char decimalSeparator() {
        return this.decimalNumberContext().decimalSeparator();
    }

    @Override
    default String exponentSymbol() {
        return this.decimalNumberContext().exponentSymbol();
    }

    @Override
    default char groupSeparator() {
        return this.decimalNumberContext().groupSeparator();
    }

    @Override
    default char negativeSign() {
        return this.decimalNumberContext().negativeSign();
    }

    @Override
    default char percentSymbol() {
        return this.decimalNumberContext().percentSymbol();
    }

    @Override
    default char positiveSign() {
        return this.decimalNumberContext().positiveSign();
    }

    @Override
    default MathContext mathContext() {
        return this.decimalNumberContext().mathContext();
    }

    DecimalNumberContext decimalNumberContext();

    @Override
    default NumberContext numberContext() {
        return this.decimalNumberContext();
    }
}
