package gov.cnao.ao.ai.bfs.contract;

import org.springframework.web.multipart.MultipartFile;

public interface Hello {
    String sayHi(String name);
    
    String upload(MultipartFile file);
}
