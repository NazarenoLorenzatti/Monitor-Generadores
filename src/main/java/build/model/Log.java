package build.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Data;

@Data
@Entity
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLog;

    private Timestamp inicio;

    private Timestamp fin;
    
    @Column(nullable = true)
    private Double diferenciaEnHoras;

    @ManyToOne
    @JsonIgnore
    private Host host;
}
