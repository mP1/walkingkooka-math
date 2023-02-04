/*
 * Copyright © 2020 Miroslav Pokorny
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
 */
package test;


import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Assert;
import org.junit.Test;
import walkingkooka.math.DecimalNumberContexts;

import java.math.MathContext;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@J2clTestInput(JunitTest.class)
public class JunitTest {

    private final static Locale LOCALE = Locale.forLanguageTag("fr");
    private final static MathContext MATH_CONTEXT = MathContext.DECIMAL32;

    @Test
    public void testDateTimeContextAmerican() {
        Assert.assertNotNull(DecimalNumberContexts.american(MATH_CONTEXT));
    }

    @Test
    public void testDateTimeContextBasic() {
        Assert.assertNotNull(DecimalNumberContexts.basic("$",
                '.',
                "E",
                ',',
                '-',
                '%',
                '+',
                LOCALE,
                MATH_CONTEXT));
    }

    @Test
    public void testDateTimeContextDecimalFormatSymbols() {
        Assert.assertNotNull(DecimalNumberContexts.decimalFormatSymbols(DecimalFormatSymbols.getInstance(LOCALE),
                '+',
                LOCALE,
                MATH_CONTEXT));
    }
}
