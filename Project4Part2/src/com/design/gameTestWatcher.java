package com.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

//https://stackoverflow.com/questions/49037406/testwatcher-in-junit5
//https://www.baeldung.com/junit-testwatcher
//https://stackoverflow.com/questions/31959039/using-testwatcher-for-logging-assertion-failures-in-test-cases
//https://junit.org/junit5/docs/current/user-guide/
//https://www.baeldung.com/junit-5-extensions
public class gameTestWatcher implements TestWatcher, AfterAllCallback {
    private final List<TestResultStatus> testResultsStatus = new ArrayList<>();
    static final Logger LOG = Logger.getLogger(gameTestWatcher.class.getName());

    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED;
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        LOG.info(() -> String.format("Test successful for test {%s}",
                context.getDisplayName()));

        testResultsStatus.add(TestResultStatus.SUCCESSFUL);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        LOG.info(() -> String.format("Test aborted for test {%s}",
                context.getDisplayName()));

        testResultsStatus.add(TestResultStatus.ABORTED);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional reason) {
        LOG.info(() -> String.format("Test disabled for test {%s}",
                context.getDisplayName()));

        testResultsStatus.add(TestResultStatus.DISABLED);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable reason) {
        LOG.info(() -> String.format("Test failed for test {%s}",
                context.getDisplayName()));

        testResultsStatus.add(TestResultStatus.FAILED);
    }

    @Override
    public void afterAll(ExtensionContext context){
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        LOG.info(()-> String.format("Test result summary for {%s} %s", context.getDisplayName(), summary.toString()));
    }
}

