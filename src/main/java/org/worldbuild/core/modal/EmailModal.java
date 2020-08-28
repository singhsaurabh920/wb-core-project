package org.worldbuild.core.modal;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Data
@Log4j2
public class EmailModal {

    private String from;
    private String to;
    private String subject;
    private String content="";
    private String templateFilePath="";
    private String attachment;
    private Map<String,Object> modal;
}
