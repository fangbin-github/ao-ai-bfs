package gov.cnao.ao.ai.bfs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.apache.servicecomb.core.Invocation;

import com.bjsasc.drap.auth.AuthUtils;
import com.bjsasc.drap.auth.IdentityToken;
import com.bjsasc.drap.pt.context.ContextUser;
import com.bjsasc.drap.pt.context.ThreadLocalUtil;
import com.bjsasc.drap.sso.SSOService;
import com.bjsasc.drap.sso.SimpleClientHttpRequestFactory4Https;

import gov.cnao.ao.ai.bfs.contract.ILogin;

@RestSchema(schemaId="iLogin")
@RequestMapping("/login")
public class LoginController implements ILogin {

	private final static String SSO_CODE = "sso_code";

	/**
	 * 应用唯一标识,对应统一权限分系统中应用ID
	 */
	//private String app_id = "edge";

	/**
	 * 查询当前用户对指定应用的的资源数，大于0表示具有访问应用权限
	 */
	@Value("${drap_platform.sso_server.rootpath}/context/ptaa_context/cntContextResourcePrivilege")
	private String cntContextResourcePrivilege_url;

	/**
	 * 查询当前用户应用内可操作的菜单集
	 */
	@Value("${drap_platform.sso_server.rootpath}/context/ptaa_context/qryAuthedAllMenu?app_id=app_demo_id")
	private String qryAuthedAllMenu_url;

	@Autowired
	private SSOService ssoService;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
    @Autowired
    private Environment evn;
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping
	@Override
	public ResponseEntity<Map<String,String>> login(@RequestParam(value ="sso_code") String  sso_code,HttpServletRequest request) throws IOException {
		try {
			Object[] result = null;
			ContextUser user= null;
			String app_id = evn.getProperty("app_id");
			request.getHeader("servicecomb-rest-request");
			request.getHeader("sso_token");
			// 从url参数获取sso_code
			//String sso_code = getSSOCodeFromQueryStr(request);
			if (!StringUtils.isEmpty(sso_code)) {
				// 换取token
				result = ssoService.getIdentityTokenByCode(sso_code,app_id);
			} 

			if (result == null) {
				throw new RuntimeException("sso_code or sso_token is required.");
			}

			// 1 检查用户能否访问该应用,调用上下文用户相关接口需要header【ssotoken】，去掉下划线，nginx默认不支持
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			requestHeaders.add("ssotoken", (String) result[0]);

			Map<String, String> data = new HashMap<String, String>();
			// 固定参数
			data.put("resource_type", "pt_app");
			// 固定参数
			data.put("privilege_code", "access");
			// 应用ID
			data.put("resource_id", app_id);

			HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(data, requestHeaders);
			List rest_result = restTemplate.postForObject(cntContextResourcePrivilege_url, requestEntity, List.class);

			user = AuthUtils.createContextUser((String)result[0], (IdentityToken)result[1]);
			ThreadLocalUtil.setContextUser(user);
			int count = Integer.valueOf(((Map<String, Object>) rest_result.get(0)).get("total").toString());
			// 无权访问应用
			if (count < 1) {
				throw new RuntimeException("The user[" + user.getUserLoginName() + "] does not have access.");
			}

			// 2 获取用户在该应用内的权限
			HttpEntity requestEntity2 = new HttpEntity(null, requestHeaders);
			ResponseEntity<List> rest_result2 = restTemplate.exchange(qryAuthedAllMenu_url, HttpMethod.GET,
					requestEntity2, List.class);
//			rest_result2.getHe
			// 缓存权限集
			//request.getSession().setAttribute("menus", rest_result2.getBody());

			// 建立用户会话
			//request.getSession().setAttribute("user", user);

			// 登录成功跳转到应用首页
			//response.sendRedirect("#");
			Map<String, String> body = new HashMap<>();
			body.put("ssotoken",(String) result[0]);
			body.put("menus", rest_result2.getBody().toString());
			ResponseEntity<Map<String,String>> res = new ResponseEntity<Map<String,String>>(body, requestHeaders,HttpStatus.OK );
			//redisTemplate.opsForValue().set(user.getUserLoginName(), body.toString());
			body.put("userId", user.getUserID());
			body.put("userLoginName", user.getUserLoginName());
			body.put("ip", user.getIp());
			body.put("roles", user.getRoles().toString());
			
			redisTemplate.opsForHash().putAll(user.getUserLoginName(),body);
			//System.out.println(redisTemplate.opsForHash().get(key, hashKey));
			return res;

		} catch (Exception e) {
			e.printStackTrace();
//			response.setContentType("text/html;charset=UTF-8");
//			response.getWriter().print(e.getMessage());
		}
		return null;

	}

	private String getSSOCodeFromQueryStr(HttpServletRequest request) {
		String queryStr = request.getQueryString();
		if (queryStr != null) {
			String[] paras = queryStr.split("&");
			for (int i = 0; i < paras.length; i++) {
				String[] para = paras[i].split("=");
				if (para.length == 2 && SSO_CODE.equals(para[0])) {
					return para[1];
				}
			}
		}
		return null;
	}

	@PostConstruct
	public void init() {
		SimpleClientHttpRequestFactory4Https requestFactory = new SimpleClientHttpRequestFactory4Https();

		requestFactory.setReadTimeout(120 * 1000);

		this.restTemplate = new RestTemplate(requestFactory);
	}


}
