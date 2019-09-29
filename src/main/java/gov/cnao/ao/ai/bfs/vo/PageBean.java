package gov.cnao.ao.ai.bfs.vo;

import java.util.List;

/**
 * 
 * @author:zhangguoqing
 * @version:2019年8月29日下午4:01:13
 */
public class PageBean {
	//已知数据
    private int pageNum ;    //当前页,从请求那边传过来。
    private int pageSize;    //每页显示的数据条数。
    private int totalRecord;    //总的记录条数。查询数据库得到的数据
     
    //需要计算得来
    private int totalPage;    //总页数，通过totalRecord和pageSize计算可以得来
    //开始索引，也就是我们在数据库中要从第几行数据开始拿，有了startIndex和pageSize，
     
    //将每页要显示的数据放在list集合中
	private List content;
     
    //通过pageNum，pageSize，totalRecord计算得来tatalPage和startIndex
    //构造方法中将pageNum，pageSize，totalRecord获得
    public PageBean(int pageNum,int pageSize,int totalRecord) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
         
        //totalPage 总页数
        if(totalRecord%pageSize==0){
            //说明整除，正好每页显示pageSize条数据，没有多余一页要显示少于pageSize条数据的
            this.totalPage = totalRecord / pageSize;
        }else{
            //不整除，就要在加一页，来显示多余的数据。
            this.totalPage = totalRecord / pageSize +1;
        }
    }
    public PageBean(){
    	
    }
    
    //get、set方法。
    public int getPageNum() {
        return pageNum;
    }
 
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
 
    public int getPageSize() {
        return pageSize;
    }
 
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
 
    public int getTotalRecord() {
        return totalRecord;
    }
 
    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
 
    public int getTotalPage() {
        return totalPage;
    }
 
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
	public List getContent() {
		return content;
	}

	public void setContent(List content) {
		this.content = content;
	}
 
}
