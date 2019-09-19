package gov.cnao.ao.ai.bfs.contract;

import java.io.IOException;
import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface ILogin {

	 ResponseEntity<Map<String,String>> login(String sso_code) throws IOException;
}
