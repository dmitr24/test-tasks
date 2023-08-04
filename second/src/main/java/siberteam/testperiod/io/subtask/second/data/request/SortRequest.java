package siberteam.testperiod.io.subtask.second.data.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortRequest implements UserRequest {
    private String outputDir;
    private String fileName;
    private String sorterName;
}
