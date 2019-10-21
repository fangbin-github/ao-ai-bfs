package gov.cnao.ao.ai.bfs.common;

/**
 * @author:zhangguoqing 
 * @version:2019年9月5日下午7:37:38
*/
public interface EnumType<K extends Enum<K>, T> {
    T getType();
}

