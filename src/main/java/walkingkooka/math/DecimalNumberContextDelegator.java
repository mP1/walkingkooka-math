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

public interface DecimalNumberContextDelegator extends DecimalNumberContext,
    DecimalNumberSymbolsLikeDelegator {

    @Override
    default Locale locale() {
        return this.decimalNumberContext()
            .locale();
    }

    @Override
    default MathContext mathContext() {
        return this.decimalNumberContext()
            .mathContext();
    }

    @Override
    default DecimalNumberSymbols decimalNumberSymbols() {
        return this.decimalNumberContext()
            .decimalNumberSymbols();
    }

    @Override
    default  DecimalNumberSymbolsLike decimalNumberSymbolsLike() {
        return this.decimalNumberContext();
    }

    DecimalNumberContext decimalNumberContext();
}
