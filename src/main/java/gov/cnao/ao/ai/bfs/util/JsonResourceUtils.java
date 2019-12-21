package gov.cnao.ao.ai.bfs.util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.net.URL;
public class JsonResourceUtils {
    private final static Logger logger = Logger.getLogger(JsonResourceUtils.class);
    private JsonResourceUtils() {}
    //filename 为文件名字 如 “/json/app_version_info.json”
    public static JSONObject getJsonObjFromResource(String filename) {
        JSONObject json = null;
        if (!filename.contains(".json")) {
            filename += ".json";
        }
        try {
            URL url = JsonResourceUtils.class.getResource(filename);
            String path = url.getPath();
            File file = new File(path);
            if (file.exists()) {
                String content = FileUtils.readFileToString(file, "UTF-8");
                json = JSON.parseObject(content);
            } else {
                logger.info("file not exist!");
            }

        } catch (IOException e) {
            logger.info("readFileToString" + e.getMessage());
        }
        return json;
    }
}
