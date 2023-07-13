package Services;

import java.util.List;
import DomainModel.Ban;


public interface IServiceBangBan {
	
	List<Ban> findAll(int position, int pageSize);
    
	Ban findById(long id);
    
	Ban create(Ban ban);
	
	Ban update(Ban ban);
    
	Ban remove(long id);
    
    long totalCount();
}
