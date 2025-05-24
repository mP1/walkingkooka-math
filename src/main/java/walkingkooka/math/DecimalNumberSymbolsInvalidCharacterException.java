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

import walkingkooka.text.CharSequences;

/**
 * An {@link IllegalArgumentException} used to report invalid characters given to individual {@link DecimalNumberSymbols}
 * properties.
 */
public final class DecimalNumberSymbolsInvalidCharacterException extends IllegalArgumentException {

    DecimalNumberSymbolsInvalidCharacterException(final String property,
                                                  final char value) {
        this.property = property;
        this.value = value;
    }

    /**
     * Returns the name of the property belonging to a {@link DecimalNumberSymbols}
     */
    public String property() {
        return this.property;
    }

    private final String property;

    /**
     * The invalid character
     */
    public char value() {
        return this.value;
    }

    private final char value;

    @Override
    public String getMessage() {
        return "Invalid " + this.property + " character " + CharSequences.quoteIfChars(this.value);
    }
}
