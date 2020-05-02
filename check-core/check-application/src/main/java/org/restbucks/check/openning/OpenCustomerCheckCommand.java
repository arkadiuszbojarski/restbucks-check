package org.restbucks.check.openning;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value(staticConstructor = "of")
public class OpenCustomerCheckCommand {
    @NotNull private final UUID checkID;
}
