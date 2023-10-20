package build.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
public class Host implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHost;

    private String ipHost;

    private String nombreHost;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)    
    private List<Log> logs = new ArrayList<>();

    public Host() {
    }

    public Host(String ipHost, String nombreHost) {
        this.ipHost = ipHost;
        this.nombreHost = nombreHost;
    }

}
