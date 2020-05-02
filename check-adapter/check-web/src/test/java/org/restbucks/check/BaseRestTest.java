package org.restbucks.check;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;

import static java.lang.String.format;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CheckController.class})
public abstract class BaseRestTest {

    private static final String OUTPUT = "target/generated-snippets";
    private static final String IDENTIFIER_TEMPLATE = "%s_%s";
    private static final String COFFEE_LOCATION_TEMPLATE = "http://check-service/checks/%s/coffees/%s";

    @Rule
    public JUnitRestDocumentation documentation = new JUnitRestDocumentation(OUTPUT);

    @Rule
    public TestName test = new TestName();

    @MockBean
    private CheckFacade facade;

    @MockBean
    ViewIntegration integration;

    @Autowired
    private CheckController controller;

    @Before
    public void setupDocumentation() {
        final var configuration = MockMvcBuilders
                .standaloneSetup(controller)
                .apply(documentationConfiguration(documentation)
                        .uris()
                        .withPort(80))
                .alwaysDo(document(identifier()));

        RestAssuredMockMvc.standaloneSetup(configuration);
    }

    @Before
    public void setupAddingCoffee() {
        final var checkID = "00570c06-38a0-43a1-a690-d3792fe83b36";
        final var coffeeID = "b7a8d2e4-99ea-4849-9347-bfd76d5e94d5";
        final var location = URI.create(format(COFFEE_LOCATION_TEMPLATE, checkID, coffeeID));

        when(integration.checkCoffeeLocation(any(), any())).thenReturn(location);
    }

    private String identifier() {
        final var className = getClass().getSimpleName();
        final var methodName = test.getMethodName();

        return format(IDENTIFIER_TEMPLATE, className, methodName);
    }

}
