package com.epam.service;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.openqa.selenium.OutputType;

import java.lang.reflect.Method;

public class ScreenShootOnFailureExtension implements InvocationInterceptor {
    /**
     * Make a screenshot with Selenide and attach it to the current test item on Report Portal in case of test failure.
     *
     * @param invocation        JUnit 5th invocation object
     * @param invocationContext JUnit 5th invocation context
     * @param extensionContext  JUnit 5th extension context
     */
    @Override
    public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext,
                                    ExtensionContext extensionContext) throws Throwable {
        try {
            invocation.proceed();
        } catch (Throwable cause) {
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            LoggingUtils.log(screenshot, "Failure screenshot");
            throw cause;
        }
    }
}
