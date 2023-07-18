package com.epam.ui.utils.logging;

import com.epam.ui.driver.DriverSingleton;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

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
            byte[] screenshot = ((TakesScreenshot) DriverSingleton.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
            LoggingUtils.log(screenshot, "Failure screenshot");
            throw cause;
        }
    }
    @Override
    public void interceptTestTemplateMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext,
                                            ExtensionContext extensionContext) throws Throwable {
        try {
            invocation.proceed();
        } catch (Throwable cause) {
            byte[] screenshot = ((TakesScreenshot) DriverSingleton.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
            LoggingUtils.log(screenshot, "Failure screenshot");
            throw cause;
        }
    }
}
