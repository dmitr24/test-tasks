package siberteam.testperiod.io.subtask.second.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private boolean isHelpRequest;
    private String outputDir;
    private String fileName;
    private String sorterName;
}
