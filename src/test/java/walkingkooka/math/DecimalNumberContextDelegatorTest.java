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

import walkingkooka.math.DecimalNumberContextDelegatorTest.TestDecimalNumberContextDelegator;

import java.math.MathContext;

public final class DecimalNumberContextDelegatorTest implements DecimalNumberContextDelegator,
    DecimalNumberContextTesting2<TestDecimalNumberContextDelegator> {

    @Override
    public void testCheckToStringOverridden() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TestDecimalNumberContextDelegator createContext() {
        return new TestDecimalNumberContextDelegator();
    }

    static class TestDecimalNumberContextDelegator implements DecimalNumberContextDelegator {

        @Override
        public DecimalNumberContext decimalNumberContext() {
            return DecimalNumberContexts.american(MathContext.DECIMAL32);
        }
    }

    @Override
    public String currencySymbol() {
        return this.decimalNumberContext().currencySymbol();
    }

    @Override
    public char decimalSeparator() {
        return this.decimalNumberContext().decimalSeparator();
    }

    @Override
    public String exponentSymbol() {
        return this.decimalNumberContext().exponentSymbol();
    }

    @Override
    public char groupSeparator() {
        return this.decimalNumberContext().groupSeparator();
    }

    @Override
    public MathContext mathContext() {
        return this.decimalNumberContext().mathContext();
    }

    @Override
    public char negativeSign() {
        return this.decimalNumberContext().negativeSign();
    }

    @Override
    public char percentageSymbol() {
        return this.decimalNumberContext().percentageSymbol();
    }

    @Override
    public char positiveSign() {
        return this.decimalNumberContext().positiveSign();
    }

    @Override
    public DecimalNumberContext decimalNumberContext() {
        return DecimalNumberContexts.american(MathContext.DECIMAL32);
    }

    // Class............................................................................................................

    @Override
    public Class<TestDecimalNumberContextDelegator> type() {
        return TestDecimalNumberContextDelegator.class;
    }
}
