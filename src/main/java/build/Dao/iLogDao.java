package build.Dao;

import build.model.Host;
import build.model.Log;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iLogDao extends JpaRepository<Log, Long>{
    
    public List<Log> findByHost(Host host);    
    
}
