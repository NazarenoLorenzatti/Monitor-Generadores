
package build.response;

import build.model.Log;
import java.util.List;
import lombok.Data;

@Data
public class ResponseLog  extends Response{
    
    private List<Log> logs;
}
