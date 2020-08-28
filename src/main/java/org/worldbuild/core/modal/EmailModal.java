package org.worldbuild.core.modal;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public class EmailModal {

    private String from;
    private String to;
    private String subject;
    private String template;
    private String attachment;
}
