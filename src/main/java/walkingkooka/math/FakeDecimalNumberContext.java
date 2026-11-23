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
import java.util.Locale;

public class FakeDecimalNumberContext implements DecimalNumberContext {

    @Override
    public String currencySymbol() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String decimalSeparator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String exponentSymbol() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String groupSeparator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String infinitySymbol() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String monetaryDecimalSeparator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String nanSymbol() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String negativeSign() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String percentSymbol() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String permillSymbol() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String positiveSign() {
        throw new UnsupportedOperationException();
    }

    @Override
    public char zeroDigit() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DecimalNumberSymbols decimalNumberSymbols() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int decimalNumberDigitCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Locale locale() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MathContext mathContext() {
        throw new UnsupportedOperationException();
    }
}
