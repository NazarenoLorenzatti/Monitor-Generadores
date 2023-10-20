
package build.response;

import build.model.Host;
import java.util.List;
import lombok.Data;

@Data
public class ResponseHost extends Response{
    
     private List<Host> hosts;
    
}
