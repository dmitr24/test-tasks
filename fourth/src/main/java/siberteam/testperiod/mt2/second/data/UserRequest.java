package siberteam.testperiod.mt2.second.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private boolean isHelpRequest;
    private String outputDir;
    private String fileName;
    private String sorterName;
    private Integer parallelExecutions;
}
