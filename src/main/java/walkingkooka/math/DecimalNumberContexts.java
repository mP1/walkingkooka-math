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

import walkingkooka.reflect.PublicStaticHelper;

import java.math.MathContext;
import java.util.Locale;

/**
 * A collection of factory methods to create {@link DecimalNumberContext}.
 */
public final class DecimalNumberContexts implements PublicStaticHelper {

    /**
     * {@see AmericanDecimalNumberContext}
     */
    public static DecimalNumberContext american(final MathContext mathContext) {
        return AmericanDecimalNumberContext.with(mathContext);
    }

    /**
     * {@see BasicDecimalNumberContext}
     */
    public static DecimalNumberContext basic(final DecimalNumberSymbols symbols,
                                             final Locale locale,
                                             final MathContext mathContext) {
        return BasicDecimalNumberContext.with(
            symbols,
            locale,
            mathContext);
    }

    /**
     * {@see FakeDecimalNumberContext}
     */
    public static DecimalNumberContext fake() {
        return new FakeDecimalNumberContext();
    }

    /**
     * Stop creation.
     */
    private DecimalNumberContexts() {
        throw new UnsupportedOperationException();
    }
}
