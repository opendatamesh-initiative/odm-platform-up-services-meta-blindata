package org.opendatamesh.platform.up.metaservice.blindata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.opendatamesh.platform.up.notification.api.clients.MetaServiceClient;
import org.opendatamesh.platform.up.notification.api.resources.NotificationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//@ActiveProfiles("dev")
//@ActiveProfiles("testpostgresql")
//@ActiveProfiles("testmysql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { MetaserviceApp.class })
public class MetaserviceAppIT {

    @LocalServerPort
    protected String port;

    protected MetaServiceClient metaServiceClient;

    protected ResourceBuilder resourceBuilder;

    protected final String NOTIFICATION_1 = "src/test/resources/notification1.json";

    @PostConstruct
    public final void init() {
        metaServiceClient = new MetaServiceClient("http://localhost:" + port);
        resourceBuilder = new ResourceBuilder();
    }

    @BeforeEach
    public void cleanDbState(@Autowired JdbcTemplate jdbcTemplate, @Autowired Environment environment) {
        if(Arrays.stream(environment.getActiveProfiles()).findFirst().get().equals("testpostgresql")) {
            JdbcTestUtils.deleteFromTables(
                    jdbcTemplate,
                    "\"ODMNOTIFICATION\".\"NOTIFICATION\""
            );
        } else if (Arrays.stream(environment.getActiveProfiles()).findFirst().get().equals("testmysql")) {
            JdbcTestUtils.deleteFromTables(
                    jdbcTemplate,
                    "ODMNOTIFICATION.NOTIFICATION"
            );
        }
    }


    // ======================================================================================
    // Create test basic resources
    // ======================================================================================

    protected NotificationResource createNotification1() throws IOException {

        NotificationResource notificationResource = resourceBuilder.readResourceFromFile(
                NOTIFICATION_1,
                NotificationResource.class
        );

        ResponseEntity<NotificationResource> postResponse = metaServiceClient.createNotification(notificationResource);

        verifyResponseEntity(postResponse, HttpStatus.CREATED, true);

        return postResponse.getBody();

    }


    // ======================================================================================
    // Verify test basic resources
    // ======================================================================================

    protected ResponseEntity verifyResponseEntity(
            ResponseEntity responseEntity,
            HttpStatus statusCode,
            boolean checkBody
    ) {
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(statusCode);
        if (checkBody)
            assertThat(responseEntity.getBody()).isNotNull();
        return responseEntity;
    }

    protected void verifyResponseError(ResponseEntity<Error> errorResponse, HttpStatus errorStatus) {
        assertThat(errorResponse.getStatusCode()).isEqualByComparingTo(errorStatus);
    }
}
