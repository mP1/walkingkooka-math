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
 * An {@link IllegalArgumentException} used to report a failure of some sort for a {@link DecimalNumberSymbols} property/parameter.
 */
public abstract class DecimalNumberSymbolsInvalidArgumentException extends IllegalArgumentException {

    DecimalNumberSymbolsInvalidArgumentException(final String property) {
        this.property = property;
    }

    /**
     * Returns the name of the property belonging to a {@link DecimalNumberSymbols}
     */
    public final String property() {
        return this.property;
    }

    final String property;

    @Override
    public abstract String getMessage();
}
