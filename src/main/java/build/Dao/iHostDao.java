
package build.Dao;

import build.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iHostDao extends JpaRepository<Host, Long>{
    
    public Host findByNombreHost(String nombreHost);
    
    public Host findByIpHost(String ipHost);
    
}
