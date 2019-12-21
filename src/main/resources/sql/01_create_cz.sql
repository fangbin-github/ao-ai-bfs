create  table   T_DMS_WLKQL
(
KMBM  VARCHAR(100),   -- 科目编码
KMMC  VARCHAR(100),   -- 科目名称
QCYE  NUMERIC(24,6),  -- 期初余额
QMYE  NUMERIC(24,6)   -- 期末余额
,USER_ID VARCHAR(20)     --用户ID
)tablespace TBS_DMS_DATA；
COMMENT ON TABLE  T_DMS_WLKQL IS '往来款清理-两年余额无变化'；
COMMENT ON COLUMN  T_DMS_WLKQL.KMBM IS '科目编码'；
COMMENT ON COLUMN  T_DMS_WLKQL.KMMC IS '科目名称'；
COMMENT ON COLUMN  T_DMS_WLKQL.QCYE IS '期初余额'；
COMMENT ON COLUMN  T_DMS_WLKQL.QMYE IS '期末余额'；
COMMENT ON COLUMN T_DMS_WLKQL.USER_ID IS '用户ID'；
-------------------------------------------
create table T_DMS_XMZC_YSZX_LX
(
   YEAR                 VARCHAR(8),
   DEPT_CD              VARCHAR(20),
   DEPT_NM              VARCHAR(200),
   PROJECT_CD           VARCHAR(50),
   PROJECT_NM           VARCHAR(100),
   INDEX_BAL            NUMERIC(24,6),
   PAY_BAL              NUMERIC(24,6),
   BUDG_EXEC            NUMERIC(19,2),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_XMZC_YSZX_LX  is '项目支出预算执行率连续三年低于阈值'；

comment on column T_DMS_XMZC_YSZX_LX.YEAR is
'预算年份'；

comment on column T_DMS_XMZC_YSZX_LX.DEPT_CD is
'部门代码'；

comment on column T_DMS_XMZC_YSZX_LX.DEPT_NM is
'部门名称'；

comment on column T_DMS_XMZC_YSZX_LX.PROJECT_CD is
'项目代码'；

comment on column T_DMS_XMZC_YSZX_LX.PROJECT_NM is
'项目名称'；

comment on column T_DMS_XMZC_YSZX_LX.INDEX_BAL is
'指标金额万元'；

comment on column T_DMS_XMZC_YSZX_LX.PAY_BAL is
'支付金额万元'；

comment on column T_DMS_XMZC_YSZX_LX.BUDG_EXEC is
'预算执行率'；

comment on column T_DMS_XMZC_YSZX_LX.USER_ID is
'用户标示'；
----------------------------------
CREATE TABLE T_DMS_YBDZHDBYS
(
YSNF   VARCHAR(8) , -- 预算年份
BMDM   VARCHAR(20) , -- 部门代码
BMMC   VARCHAR(200) , -- 部门名称
XMDM   VARCHAR(100) , -- 项目代码
XMMC   VARCHAR(100) , -- 项目名称
ZFJEWY   NUMERIC(24,6)  -- 支付金额万元
,USER_ID VARCHAR(20)     --用户ID
)
TABLESPACE TBS_DMS_DATA；
COMMENT ON TABLE T_DMS_YBDZHDBYS IS '应记未记因公出国费用'；
COMMENT ON COLUMN T_DMS_YBDZHDBYS.YSNF IS '预算年份'；
COMMENT ON COLUMN T_DMS_YBDZHDBYS.BMDM IS '部门代码'；
COMMENT ON COLUMN T_DMS_YBDZHDBYS.BMMC IS '部门名称'；
COMMENT ON COLUMN T_DMS_YBDZHDBYS.XMDM IS '项目代码'；
COMMENT ON COLUMN T_DMS_YBDZHDBYS.XMMC IS '项目名称'；
COMMENT ON COLUMN T_DMS_YBDZHDBYS.ZFJEWY IS '支付金额万元'；
COMMENT ON COLUMN T_DMS_YBDZHDBYS.USER_ID IS '用户ID'；
-----------------------------------
CREATE TABLE T_DMS_WXHBZYS
(
YSNF TEXT,        --发文日期
BMDM VARCHAR(50) not null , -- 部门代码
BMMC VARCHAR(100),          -- 部门名称
XMDM VARCHAR(100),          -- 项目代码
XMMC VARCHAR(100),          -- 项目名称
ZBJEWY NUMERIC(24,6),          -- 指标金额万元
ZFXHXHJEWY NUMERIC(24,6),     --支付学会协会金额万元
ZFXHXHBL NUMERIC(19,2)    -- 支付协会学会比例
,USER_ID VARCHAR(20)     --用户ID
)TABLESPACE TBS_DMS_DATA；
COMMENT ON TABLE T_DMS_WXHBZYS IS '为协会编制预算'；
COMMENT ON COLUMN T_DMS_WXHBZYS.BMDM IS '部门代码'；
COMMENT ON COLUMN T_DMS_WXHBZYS.BMMC IS '部门名称'；
COMMENT ON COLUMN T_DMS_WXHBZYS.XMDM IS '项目代码'；
COMMENT ON COLUMN T_DMS_WXHBZYS.XMMC IS '项目名称'；
COMMENT ON COLUMN T_DMS_WXHBZYS.ZBJEWY IS '指标金额万元'；
COMMENT ON COLUMN T_DMS_WXHBZYS.YSNF IS '预算年份'；
COMMENT ON COLUMN T_DMS_WXHBZYS.ZFXHXHJEWY IS '支付学会协会金额万元'；
COMMENT ON COLUMN T_DMS_WXHBZYS.ZFXHXHBL IS '支付协会学会比例'；
COMMENT ON COLUMN T_DMS_WXHBZYS.USER_ID IS '用户ID'；
------------------------
CREATE TABLE T_DMS_DNYSZXLDYZZ
(
YSNF			VARCHAR(8)	,	--	预算年份
BMDM			VARCHAR(20)	,	--	部门代码
BMMC			VARCHAR(200)	,	--	部门名称
XMDM			VARCHAR(200)	,	--	项目代码
XMMC			VARCHAR(200)	,	--	项目名称
YSZXL			NUMERIC(19,2) 	,	--	预算执行率
USER_ID  VARCHAR(20)
)TABLESPACE TBS_DMS_DATA；
COMMENT ON COLUMN T_DMS_DNYSZXLDYZZ.YSNF IS '预算年份'；
COMMENT ON COLUMN T_DMS_DNYSZXLDYZZ.BMDM IS '部门代码'；
COMMENT ON COLUMN T_DMS_DNYSZXLDYZZ.BMMC IS '部门名称'；
COMMENT ON COLUMN T_DMS_DNYSZXLDYZZ.XMDM IS '项目代码'；
COMMENT ON COLUMN T_DMS_DNYSZXLDYZZ.XMMC IS '项目名称'；
COMMENT ON COLUMN T_DMS_DNYSZXLDYZZ.YSZXL IS '预算执行率'；
COMMENT ON COLUMN T_DMS_DNYSZXLDYZZ.USER_ID IS '用户ID'；
--------------------
CREATE TABLE T_DMS_ZSZZ
(
YEAR                 VARCHAR(8),
   CERT_TYPE            VARCHAR(20),
   CERT_CD              VARCHAR(200),
   DEBIT_AMT            NUMERIC(24,6),
   AMOUNT_AMT           NUMERIC(24,6)
,USER_ID VARCHAR(20)     --用户ID
)TABLESPACE TBS_DMS_DATA
；
COMMENT ON TABLE T_DMS_ZSZZ IS '坐收坐支'；
comment on column T_DMS_ZSZZ.YEAR is
'凭证日期'；

comment on column T_DMS_ZSZZ.CERT_TYPE is
'凭证类型'；

comment on column T_DMS_ZSZZ.CERT_CD is
'凭证号'；

comment on column T_DMS_ZSZZ.DEBIT_AMT is
'借方金额'；

comment on column T_DMS_ZSZZ.AMOUNT_AMT is
'贷方金额'；

comment on column T_DMS_ZSZZ.USER_ID is
'用户标示'；
----------------------------------
CREATE TABLE T_DMS_SRZWLKMHS
(
YEAR                 VARCHAR(8),
   CERT_TYPE            VARCHAR(20),
   CERT_CD              VARCHAR(200),
   DEBIT_AMT            NUMERIC(24,6),
   AMOUNT_AMT           NUMERIC(24,6),
   USER_ID              VARCHAR(20)
)TABLESPACE TBS_DMS_DATA；
COMMENT ON TABLE T_DMS_SRZWLKMHS IS '收入在往来科目核算'；
comment on column T_DMS_SRZWLKMHS.YEAR is
'凭证日期'；

comment on column T_DMS_SRZWLKMHS.CERT_TYPE is
'凭证类型'；

comment on column T_DMS_SRZWLKMHS.CERT_CD is
'凭证号'；

comment on column T_DMS_SRZWLKMHS.DEBIT_AMT is
'借方金额'；

comment on column T_DMS_SRZWLKMHS.AMOUNT_AMT is
'贷方金额'；

comment on column T_DMS_SRZWLKMHS.USER_ID is
'用户标示'；
-----------------------------------
CREATE TABLE T_DMS_XBDWSYZJZHBK
(
YSNF   VARCHAR(8) ,
BMDM   VARCHAR(20) ,
BMMC   VARCHAR(200) ,
XMDM   VARCHAR(100) ,
XMMC   VARCHAR(100) ,
ZFPZBH   NUMBER(18) 
,USER_ID VARCHAR(20)     --用户ID
)TABLESPACE TBS_DMS_DATA；
COMMENT ON TABLE T_DMS_XBDWSYZJZHBK IS '向本单位实有资金账户拨款'；
COMMENT ON COLUMN T_DMS_XBDWSYZJZHBK.YSNF IS '预算年份'；
COMMENT ON COLUMN T_DMS_XBDWSYZJZHBK.BMDM IS '部门代码'；
COMMENT ON COLUMN T_DMS_XBDWSYZJZHBK.BMMC IS '部门名称'；
COMMENT ON COLUMN T_DMS_XBDWSYZJZHBK.XMDM IS '项目代码'；
COMMENT ON COLUMN T_DMS_XBDWSYZJZHBK.XMMC IS '项目名称'；
COMMENT ON COLUMN T_DMS_XBDWSYZJZHBK.ZFPZBH IS '支付凭证编号'；
COMMENT ON COLUMN T_DMS_XBDWSYZJZHBK.USER_ID IS '用户ID'；
--------------------------------------
CREATE TABLE T_DMS_XLZC
(
YSNF VARCHAR(8) , -- 预算年份
BMDM VARCHAR(20) , -- 部门代码
BMMC VARCHAR(200) , -- 部门名称
XMDM VARCHAR(100) , -- 项目代码
XMMC VARCHAR(100) , -- 项目名称
ZFPZBH NUMBER(18) , -- 支付凭证编号
SKFMC VARCHAR(200) , -- 收款方名称
ZFJE NUMERIC(24,6)  -- 支付金额
,USER_ID VARCHAR(20)     --用户ID
)TABLESPACE TBS_DMS_DATA；
COMMENT ON TABLE T_DMS_XLZC IS '虚列支出'；
COMMENT ON COLUMN T_DMS_XLZC.YSNF IS '预算年份'；
COMMENT ON COLUMN T_DMS_XLZC.BMDM IS '部门代码'；
COMMENT ON COLUMN T_DMS_XLZC.BMMC IS '部门名称'；
COMMENT ON COLUMN T_DMS_XLZC.XMDM IS '项目代码'；
COMMENT ON COLUMN T_DMS_XLZC.XMMC IS '项目名称'；
COMMENT ON COLUMN T_DMS_XLZC.ZFPZBH IS '支付凭证编号'；
COMMENT ON COLUMN T_DMS_XLZC.SKFMC IS '收款方名称'；
COMMENT ON COLUMN T_DMS_XLZC.ZFJE IS '支付金额'；
COMMENT ON COLUMN T_DMS_XLZC.USER_ID IS '用户ID'；
----------------------------------
CREATE TABLE T_DMS_NDTJHQ
(
YSNF   VARCHAR(8) , -- 预算年份
BMDM   VARCHAR(20) , -- 部门代码
BMMC   VARCHAR(200) , -- 部门名称
XMDM   VARCHAR(100) , -- 项目代码
XMMC   VARCHAR(100) , -- 项目名称
ZBJEWY   NUMERIC(24,6) , -- 指标金额万元
XMZCWY_11_12Y   NUMERIC(24,6),  -- 11、12月项目支出万元
ZCZB   NUMERIC(19,2)  -- 支出占比
,USER_ID VARCHAR(20)     --用户ID
)TABLESPACE TBS_DMS_DATA；
COMMENT ON TABLE T_DMS_NDTJHQ IS '年底突击花钱'；
COMMENT ON COLUMN T_DMS_NDTJHQ.YSNF IS '预算年份'；
COMMENT ON COLUMN T_DMS_NDTJHQ.BMDM IS '部门代码'；
COMMENT ON COLUMN T_DMS_NDTJHQ.BMMC IS '部门名称'；
COMMENT ON COLUMN T_DMS_NDTJHQ.XMDM IS '项目代码'；
COMMENT ON COLUMN T_DMS_NDTJHQ.XMMC IS '项目名称'；
COMMENT ON COLUMN T_DMS_NDTJHQ.ZBJEWY IS '指标金额万元'；
COMMENT ON COLUMN T_DMS_NDTJHQ.XMZCWY_11_12Y IS '11、12月项目支出万元'；
COMMENT ON COLUMN T_DMS_NDTJHQ.ZCZB IS '支出占比'；
COMMENT ON COLUMN T_DMS_NDTJHQ.USER_ID IS '用户ID'；
---------------------------------
CREATE TABLE T_DMS_PQCZZJ
(
YSNF   VARCHAR(8) , -- 预算年份
BMDM   VARCHAR(20) , -- 部门代码
BMMC   VARCHAR(200) , -- 部门名称
XMDM   VARCHAR(100) , -- 项目代码
XMMC   VARCHAR(100) , -- 项目名称
ZCZB   NUMERIC(19,2),  -- 支出占比
USER_ID VARCHAR(20)     --用户ID
)TABLESPACE TBS_DMS_DATA
；
COMMENT ON TABLE T_DMS_PQCZZJ IS '骗取财政资金'；
COMMENT ON COLUMN T_DMS_PQCZZJ.YSNF IS '预算年份'；
COMMENT ON COLUMN T_DMS_PQCZZJ.BMDM IS '部门代码'；
COMMENT ON COLUMN T_DMS_PQCZZJ.BMMC IS '部门名称'；
COMMENT ON COLUMN T_DMS_PQCZZJ.XMDM IS '项目代码'；
COMMENT ON COLUMN T_DMS_PQCZZJ.XMMC IS '项目名称'；
COMMENT ON COLUMN T_DMS_PQCZZJ.ZCZB IS '支出占比'；
COMMENT ON COLUMN T_DMS_PQCZZJ.USER_ID IS '用户ID'；
------------------------------
CREATE TABLE T_DMS_YJWJYGCGFY
(
YEAR                 VARCHAR(8),
	   CERT_TYPE            VARCHAR(20),
	   CERT_CD              VARCHAR(200),
	   DEBIT_AMT            NUMERIC(24,6),
	   AMOUNT_AMT           NUMERIC(24,6),
	   USER_ID              VARCHAR(20)
)TABLESPACE TBS_DMS_DATA；
COMMENT ON TABLE T_DMS_YJWJYGCGFY IS '应记未记因公出国费用'；
comment on column T_DMS_YJWJYGCGFY.YEAR is
'凭证日期'；

comment on column T_DMS_YJWJYGCGFY.CERT_TYPE is
'凭证类型'；

comment on column T_DMS_YJWJYGCGFY.CERT_CD is
'凭证号'；

comment on column T_DMS_YJWJYGCGFY.DEBIT_AMT is
'借方金额'；

comment on column T_DMS_YJWJYGCGFY.AMOUNT_AMT is
'贷方金额'；

comment on column T_DMS_YJWJYGCGFY.USER_ID is
'用户标示'；
------------------------------
CREATE TABLE T_DMS_BYJDJRYGCGFY
(
YEAR                 VARCHAR(8),
	   CERT_TYPE            VARCHAR(20),
	   CERT_CD              VARCHAR(200),
	   DEBIT_AMT            NUMERIC(24,6),
	   AMOUNT_AMT           NUMERIC(24,6),
	   USER_ID              VARCHAR(20)
)TABLESPACE TBS_DMS_DATA；
COMMENT ON TABLE T_DMS_BYJDJRYGCGFY IS '应记未记因公出国费用'；
comment on column T_DMS_BYJDJRYGCGFY.YEAR is
'凭证日期'；

comment on column T_DMS_BYJDJRYGCGFY.CERT_TYPE is
'凭证类型'；

comment on column T_DMS_BYJDJRYGCGFY.CERT_CD is
'凭证号'；

comment on column T_DMS_BYJDJRYGCGFY.DEBIT_AMT is
'借方金额'；

comment on column T_DMS_BYJDJRYGCGFY.AMOUNT_AMT is
'贷方金额'；

comment on column T_DMS_BYJDJRYGCGFY.USER_ID is
'用户标示'；
----------------------------------
create table T_DMS_YGWG_GWYC_WHF 
(
   YEAR                 VARCHAR(8),
   CERT_TYPE            VARCHAR(20),
   CERT_CD              VARCHAR(200),
   DEBIT_AMT            NUMERIC(24,6),
   AMOUNT_AMT           NUMERIC(24,6),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_YGWG_GWYC_WHF  is '应记未记公务用车运行维护费'；

comment on column T_DMS_YGWG_GWYC_WHF.YEAR is
'凭证日期'；

comment on column T_DMS_YGWG_GWYC_WHF.CERT_TYPE is
'凭证类型'；

comment on column T_DMS_YGWG_GWYC_WHF.CERT_CD is
'凭证号'；

comment on column T_DMS_YGWG_GWYC_WHF.DEBIT_AMT is
'借方金额'；

comment on column T_DMS_YGWG_GWYC_WHF.AMOUNT_AMT is
'贷方金额'；

comment on column T_DMS_YGWG_GWYC_WHF.USER_ID is
'用户标示'；
----------------------------------
create table T_DMS_BYJDJ_GWYC_WHF 
(
   YEAR                 VARCHAR(8),
   CERT_TYPE            VARCHAR(20),
   CERT_CD              VARCHAR(200),
   DEBIT_AMT            NUMERIC(24,6),
   AMOUNT_AMT           NUMERIC(24,6),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_BYJDJ_GWYC_WHF  is '不应记但记入公务用车运行维护费'；

comment on column T_DMS_BYJDJ_GWYC_WHF.YEAR is
'凭证日期'；

comment on column T_DMS_BYJDJ_GWYC_WHF.CERT_TYPE is
'凭证类型'；

comment on column T_DMS_BYJDJ_GWYC_WHF.CERT_CD is
'凭证号'；

comment on column T_DMS_BYJDJ_GWYC_WHF.DEBIT_AMT is
'借方金额'；

comment on column T_DMS_BYJDJ_GWYC_WHF.AMOUNT_AMT is
'贷方金额'；

comment on column T_DMS_BYJDJ_GWYC_WHF.USER_ID is
'用户标示'；
----------------------------------
create table T_DMS_YJWJ_GWJDF 
(
   YEAR                 VARCHAR(8),
   CERT_TYPE            VARCHAR(20),
   CERT_CD              VARCHAR(200),
   DEBIT_AMT            NUMERIC(24,6),
   AMOUNT_AMT           NUMERIC(24,6),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_YJWJ_GWJDF  is '应记未记公务接待费'；

comment on column T_DMS_YJWJ_GWJDF.YEAR is
'凭证日期'；

comment on column T_DMS_YJWJ_GWJDF.CERT_TYPE is
'凭证类型'；

comment on column T_DMS_YJWJ_GWJDF.CERT_CD is
'凭证号'；

comment on column T_DMS_YJWJ_GWJDF.DEBIT_AMT is
'借方金额'；

comment on column T_DMS_YJWJ_GWJDF.AMOUNT_AMT is
'贷方金额'；

comment on column T_DMS_YJWJ_GWJDF.USER_ID is
'用户标示'；
----------------------------------

create table T_DMS_BYJWJ_GWJDF 
(
   YEAR                 VARCHAR(8),
   CERT_TYPE            VARCHAR(20),
   CERT_CD              VARCHAR(200),
   DEBIT_AMT            NUMERIC(24,6),
   AMOUNT_AMT           NUMERIC(24,6),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_BYJWJ_GWJDF  is '不应记但记入公务接待费'；

comment on column T_DMS_BYJWJ_GWJDF.YEAR is
'凭证日期'；

comment on column T_DMS_BYJWJ_GWJDF.CERT_TYPE is
'凭证类型'；

comment on column T_DMS_BYJWJ_GWJDF.CERT_CD is
'凭证号'；

comment on column T_DMS_BYJWJ_GWJDF.DEBIT_AMT is
'借方金额'；

comment on column T_DMS_BYJWJ_GWJDF.AMOUNT_AMT is
'贷方金额'；

comment on column T_DMS_BYJWJ_GWJDF.USER_ID is
'用户标示'；
----------------------------------
create table T_DMS_YJWJ_HYF_PXF
(
   YEAR                 VARCHAR(8),
   CERT_TYPE            VARCHAR(20),
   CERT_CD              VARCHAR(200),
   DEBIT_AMT            NUMERIC(24,6),
   AMOUNT_AMT           NUMERIC(24,6),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_YJWJ_HYF_PXF  is '应记未记会议费和培训费'；

comment on column T_DMS_YJWJ_HYF_PXF.YEAR is
'凭证日期'；

comment on column T_DMS_YJWJ_HYF_PXF.CERT_TYPE is
'凭证类型'；

comment on column T_DMS_YJWJ_HYF_PXF.CERT_CD is
'凭证号'；

comment on column T_DMS_YJWJ_HYF_PXF.DEBIT_AMT is
'借方金额'；

comment on column T_DMS_YJWJ_HYF_PXF.AMOUNT_AMT is
'贷方金额'；

comment on column T_DMS_YJWJ_HYF_PXF.USER_ID is
'用户标示'；
----------------------------------
create table T_DMS_BYJWJ_HYF_PXF
(
   YEAR                 VARCHAR(8),
   CERT_TYPE            VARCHAR(20),
   CERT_CD              VARCHAR(200),
   DEBIT_AMT            NUMERIC(24,6),
   AMOUNT_AMT           NUMERIC(24,6),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_BYJWJ_HYF_PXF  is '不应记但记入会议费和培训费'；

comment on column T_DMS_BYJWJ_HYF_PXF.YEAR is
'凭证日期'；

comment on column T_DMS_BYJWJ_HYF_PXF.CERT_TYPE is
'凭证类型'；

comment on column T_DMS_BYJWJ_HYF_PXF.CERT_CD is
'凭证号'；

comment on column T_DMS_BYJWJ_HYF_PXF.DEBIT_AMT is
'借方金额'；

comment on column T_DMS_BYJWJ_HYF_PXF.AMOUNT_AMT is
'贷方金额'；

comment on column T_DMS_BYJWJ_HYF_PXF.USER_ID is
'用户标示'；
----------------------------------

create table T_DMS_DCHYF_CBZ
(
   YEAR                 VARCHAR(8),
   DEPT_CD              VARCHAR(20),
   DEPT_NM              VARCHAR(200),
   PROJECT_CD           VARCHAR(50),
   PROJECT_NM           VARCHAR(100),
   SUBJ_NM              VARCHAR(100),
   PROO_ID              VARCHAR(50),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_DCHYF_CBZ  is '单次会议费超标准'；

comment on column T_DMS_DCHYF_CBZ.YEAR is
'预算年份'；

comment on column T_DMS_DCHYF_CBZ.DEPT_CD is
'部门代码'；

comment on column T_DMS_DCHYF_CBZ.DEPT_NM is
'部门名称'；

comment on column T_DMS_DCHYF_CBZ.PROJECT_CD is
'项目代码'；

comment on column T_DMS_DCHYF_CBZ.PROJECT_NM is
'项目名称'；

comment on column T_DMS_DCHYF_CBZ.SUBJ_NM is
'科目名称'；


comment on column T_DMS_DCHYF_CBZ.USER_ID is
'用户标示'；
----------------------------------

create table T_DMS_WZDD_ZKHY
(
   YEAR                 VARCHAR(8),
   DEPT_CD              VARCHAR(20),
   DEPT_NM              VARCHAR(200),
   PROJECT_CD           VARCHAR(50),
   PROJECT_NM           VARCHAR(100),
   SUBJ_NM              VARCHAR(100),
   PROO_ID              VARCHAR(50),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_WZDD_ZKHY  is '未在定点场所召开会议'；

comment on column T_DMS_WZDD_ZKHY.YEAR is
'预算年份'；

comment on column T_DMS_WZDD_ZKHY.DEPT_CD is
'部门代码'；

comment on column T_DMS_WZDD_ZKHY.DEPT_NM is
'部门名称'；

comment on column T_DMS_WZDD_ZKHY.PROJECT_CD is
'项目代码'；

comment on column T_DMS_WZDD_ZKHY.PROJECT_NM is
'项目名称'；

comment on column T_DMS_WZDD_ZKHY.SUBJ_NM is
'科目名称'；

comment on column T_DMS_WZDD_ZKHY.USER_ID is
'用户标示'；
----------------------------------
create table T_DMS_GDXF
(
   YEAR                 VARCHAR(8),
   DEPT_CD              VARCHAR(20),
   DEPT_NM              VARCHAR(200),
   PROJECT_CD           VARCHAR(50),
   PROJECT_NM           VARCHAR(100),
   SUBJ_NM              VARCHAR(100),
   PROO_ID              VARCHAR(50),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_GDXF  is '高档消费'；

comment on column T_DMS_GDXF.YEAR is
'预算年份'；

comment on column T_DMS_GDXF.DEPT_CD is
'部门代码'；

comment on column T_DMS_GDXF.DEPT_NM is
'部门名称'；

comment on column T_DMS_GDXF.PROJECT_CD is
'项目代码'；

comment on column T_DMS_GDXF.PROJECT_NM is
'项目名称'；

comment on column T_DMS_GDXF.SUBJ_NM is
'科目名称'；

comment on column T_DMS_GDXF.USER_ID is
'用户标示'；
----------------------------------

create table T_DMS_CHSC_XFDE_ZS
(
   YEAR                 VARCHAR(8),
   DEPT_CD              VARCHAR(20),
   DEPT_NM              VARCHAR(200),
   PROJECT_CD           VARCHAR(50),
   PROJECT_NM           VARCHAR(100),
   SUBJ_NM              VARCHAR(100),
   PROO_ID              VARCHAR(50),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_CHSC_XFDE_ZS  is '超市或商场消费为大额整数'；

comment on column T_DMS_CHSC_XFDE_ZS.YEAR is
'预算年份'；

comment on column T_DMS_CHSC_XFDE_ZS.DEPT_CD is
'部门代码'；

comment on column T_DMS_CHSC_XFDE_ZS.DEPT_NM is
'部门名称'；

comment on column T_DMS_CHSC_XFDE_ZS.PROJECT_CD is
'项目代码'；

comment on column T_DMS_CHSC_XFDE_ZS.PROJECT_NM is
'项目名称'；

comment on column T_DMS_CHSC_XFDE_ZS.SUBJ_NM is
'科目名称'；

comment on column T_DMS_CHSC_XFDE_ZS.USER_ID is
'用户标示'；
----------------------------------
create table T_DMS_CFWFF_JJBT
(
   YEAR                 VARCHAR(8),
   CERT_TYPE            VARCHAR(20),
   CERT_CD              VARCHAR(200),
   DEBIT_AMT            NUMERIC(24,6),
   AMOUNT_AMT           NUMERIC(24,6),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_CFWFF_JJBT  is '超范围发放奖金补贴'；

comment on column T_DMS_CFWFF_JJBT.YEAR is
'凭证日期'；

comment on column T_DMS_CFWFF_JJBT.CERT_TYPE is
'凭证类型'；

comment on column T_DMS_CFWFF_JJBT.CERT_CD is
'凭证号'；

comment on column T_DMS_CFWFF_JJBT.DEBIT_AMT is
'借方金额'；

comment on column T_DMS_CFWFF_JJBT.AMOUNT_AMT is
'贷方金额'；

comment on column T_DMS_CFWFF_JJBT.USER_ID is
'用户标示'；
----------------------------------
create table T_DMS_BMYS_DFZY_ZFZJ
(
   YEAR                 VARCHAR(8),
   DEPT_CD              VARCHAR(20),
   DEPT_NM              VARCHAR(200),
   PROJECT_CD           VARCHAR(50),
   PROJECT_NM           VARCHAR(100),
   PAY_BAL              NUMBER(24,6),
   PAY_BAL_ZB           NUMBER(19,2),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_BMYS_DFZY_ZFZJ  is '在部门预算中安排对地方转移支付资金'；

comment on column T_DMS_BMYS_DFZY_ZFZJ.YEAR is
'预算年份'；

comment on column T_DMS_BMYS_DFZY_ZFZJ.DEPT_CD is
'部门代码'；

comment on column T_DMS_BMYS_DFZY_ZFZJ.DEPT_NM is
'部门名称'；

comment on column T_DMS_BMYS_DFZY_ZFZJ.PROJECT_CD is
'项目代码'；

comment on column T_DMS_BMYS_DFZY_ZFZJ.PROJECT_NM is
'项目名称'；

comment on column T_DMS_BMYS_DFZY_ZFZJ.PAY_BAL is
'转移支付金额'；

comment on column T_DMS_BMYS_DFZY_ZFZJ.PAY_BAL_ZB is
'转移支付金额占比'；

comment on column T_DMS_BMYS_DFZY_ZFZJ.USER_ID is
'用户标示'；
----------------------------------

create table T_DMS_WGSQ_SF( 
   YEAR                 VARCHAR(8),
   CERT_TYPE            VARCHAR(20),
   CERT_CD              VARCHAR(200),
   DEBIT_AMT            NUMERIC(24,6),
   AMOUNT_AMT           NUMERIC(24,6),
   USER_ID              VARCHAR(20),  	--用户ID
primary key (CERT_CD)
)TABLESPACE TBS_DMS_DATA；
COMMENT ON TABLE T_DMS_WGSQ_SF IS '违规涉企收费'；
comment on column T_DMS_WGSQ_SF.YEAR is
'凭证日期'；

comment on column T_DMS_WGSQ_SF.CERT_TYPE is
'凭证类型'；

comment on column T_DMS_WGSQ_SF.CERT_CD is
'凭证号'；

comment on column T_DMS_WGSQ_SF.DEBIT_AMT is
'借方金额'；

comment on column T_DMS_WGSQ_SF.AMOUNT_AMT is
'贷方金额'；

comment on column T_DMS_WGSQ_SF.USER_ID is
'用户标示'；
----------------------------------

create table T_DMS_DSJD_YFSGJY( 
BUDG_YEAR		VARCHAR(8),
DEPT_CD		VARCHAR(20),
DEPT_NM		VARCHAR(200),
PROJ_CD		VARCHAR(200),
PROJ_NM		VARCHAR(200),
SUBJ_NM	     VARCHAR(100),
USER_ID        VARCHAR(20)
)TABLESPACE TBS_DMS_DATA；
COMMENT ON TABLE T_DMS_DSJD_YFSGJY IS '第四季度预付三公经费'；
COMMENT on column T_DMS_DSJD_YFSGJY.BUDG_YEAR is
'预算年份'；
COMMENT on column T_DMS_DSJD_YFSGJY.DEPT_CD is
'部门代码'；
COMMENT on column T_DMS_DSJD_YFSGJY.DEPT_NM is
'部门名称'；
COMMENT on column T_DMS_DSJD_YFSGJY.PROJ_CD is
'项目代码'；
COMMENT on column T_DMS_DSJD_YFSGJY.PROJ_NM is
'项目名称'；
COMMENT on column T_DMS_DSJD_YFSGJY.SUBJ_NM is
'科目名称'；
COMMENT ON COLUMN T_DMS_DSJD_YFSGJY.USER_ID IS 
'用户ID'；
----------------------------------

CREATE TABLE T_DMS_WZDDCS_WSGWCL( 
BUDG_YEAR		VARCHAR(8),
DEPT_CD		VARCHAR(20),
DEPT_NM		VARCHAR(200),
PROJ_CD		VARCHAR(200),
PROJ_NM		VARCHAR(200),
SUBJ_NM	     VARCHAR(100),
USER_ID        VARCHAR(20)--, 
--primary key (CERT_ID)
)TABLESPACE TBS_DMS_DATA；
COMMENT ON TABLE T_DMS_WZDDCS_WSGWCL IS '未在定点场所维修公务车辆'；
COMMENT on column T_DMS_WZDDCS_WSGWCL.BUDG_YEAR is
'预算年份'；
COMMENT on column T_DMS_WZDDCS_WSGWCL.DEPT_CD is
'部门代码'；
COMMENT on column T_DMS_WZDDCS_WSGWCL.DEPT_NM is
'部门名称'；
COMMENT on column T_DMS_WZDDCS_WSGWCL.PROJ_CD is
'项目代码'；
COMMENT on column T_DMS_WZDDCS_WSGWCL.PROJ_NM is
'项目名称'；
COMMENT on column T_DMS_WZDDCS_WSGWCL.SUBJ_NM is
'科目名称'；
COMMENT ON COLUMN T_DMS_WZDDCS_WSGWCL.USER_ID IS 
'用户ID'；
----------------------------------
create table T_DMS_XMYS_JJFX_ZB
(
   YEAR                 VARCHAR(8),
   DEPT_CD              VARCHAR(20),
   DEPT_NM              VARCHAR(200),
   PROJECT_CD           VARCHAR(50),
   PROJECT_NM           VARCHAR(100),
   SUBJ_QT_NM           NUMERIC(24,6),
   RATE                 NUMERIC(19,2),
   USER_ID              VARCHAR(20)
)；
comment on TABLE T_DMS_XMYS_JJFX_ZB  is '项目预算编制不细化-经济分类其他科目占比较高'；

comment on column T_DMS_XMYS_JJFX_ZB.YEAR is
'预算年份'；

comment on column T_DMS_XMYS_JJFX_ZB.DEPT_CD is
'部门代码'；

comment on column T_DMS_XMYS_JJFX_ZB.DEPT_NM is
'部门名称'；

comment on column T_DMS_XMYS_JJFX_ZB.PROJECT_CD is
'项目代码'；

comment on column T_DMS_XMYS_JJFX_ZB.PROJECT_NM is
'项目名称'；

comment on column T_DMS_XMYS_JJFX_ZB.SUBJ_QT_NM is
'其他科目类指标'；

comment on column T_DMS_XMYS_JJFX_ZB.RATE is
'占比比例'；

comment on column T_DMS_XMYS_JJFX_ZB.USER_ID is
'用户标示'；
----------------------------------

create table T_DMS_FSSR_YJWJ (
BUDG_YEAR		VARCHAR(8),
DEPT_CD		VARCHAR(20),
DEPT_NM		VARCHAR(200),
PROJ_CD		VARCHAR(200),
PROJ_NM		VARCHAR(200),
SUBJ_NM	     VARCHAR(100),
CERT_ID	     VARCHAR(40),
USER_ID        VARCHAR(20)
)TABLESPACE TBS_DMS_DATA；
COMMENT ON TABLE T_DMS_FSSR_YJWJ IS '非税收入应缴未缴'；
COMMENT on column T_DMS_FSSR_YJWJ.BUDG_YEAR is
'预算年份'；
COMMENT on column T_DMS_FSSR_YJWJ.DEPT_CD is
'部门代码'；
COMMENT on column T_DMS_FSSR_YJWJ.DEPT_NM is
'部门名称'；
COMMENT on column T_DMS_FSSR_YJWJ.PROJ_CD is
'项目代码'；
COMMENT on column T_DMS_FSSR_YJWJ.PROJ_NM is
'项目名称'；
COMMENT on column T_DMS_FSSR_YJWJ.SUBJ_NM is
'科目名称'；
COMMENT on column T_DMS_FSSR_YJWJ.CERT_ID is
'凭证号'；
COMMENT ON COLUMN T_DMS_FSSR_YJWJ.USER_ID IS 
'用户ID'；
---------------中央本级执行情况明细表 Definition---------------
CREATE TABLE "中央本级执行情况明细表"
(
    "序号" text ,
    "单据类型" text ,
    "指标单号" text ,
    "指标金额" numeric(21,4) ,
    "部门编码" text ,
    "部门名称" text ,
    "部门" text ,
    "资金归属司局" text ,
    "收支管理类型" text ,
    "科目" text ,
    "项目代码" text ,
    "项目名称" text ,
    "项目类型" text ,
    "基建标识" text ,
    "来源类型" text ,
    "支付方式" text ,
    "文号" text ,
    "发文日期" text ,
    "摘要" text ,
    "制单人" text ,
    "财政年度" text ,
    "涉密级别" text ,
    "部门经济分类编码" text ,
    "部门经济分类名称" text ,
    "政府经济分类编码" text ,
    "政府经济分类名称" text 
)   TABLESPACE TBS_DMS_DATA
；
CREATE TABLE "中央财政本级预算指标"
(
    "序号" text ,
    "单据类型" text ,
    "指标单号" text ,
    "指标金额" numeric(21,4) ,
    "部门" text ,
    "资金归属司局" text ,
    "收支管理类型" text ,
    "科目" text ,
    "项目代码" text ,
    "项目名称" text ,
    "项目类型" text ,
    "基建标识" text ,
    "来源类型" text ,
    "支付方式" character varying(510) ,
    "文号" text ,
    "发文日期" text ,
    "摘要" text ,
    "制单人" text ,
    "财政年度" text ,
    "涉密级别" text ,
    "部门经济分类编码" text ,
    "部门经济分类名称" text ,
    "政府经济分类编码" text ,
    "政府经济分类名称" text ,
    "部门编码" text ,
    "部门名称" text 
)   TABLESPACE TBS_DMS_DATA
；
CREATE TABLE "全年执行指标账提供审计"
(
    "序号" text ,
    "单据类型" text ,
    "指标单号" text ,
    "指标金额万元" numeric(21,4) ,
    "部门" text ,
    "资金归属司局" text ,
    "收支管理类型" text ,
    "科目" text ,
    "项目代码" text ,
    "项目名称" text ,
    "项目类型" text ,
    "基建标识" text ,
    "来源类型" text ,
    "支付方式" text ,
    "文号" text ,
    "发文日期" text ,
    "摘要" text ,
    "制单人" text ,
    "制单时间" timestamp without time zone ,
    "财政年度" text ,
    "涉密级别" text ,
    "部门经济分类编码" text ,
    "部门经济分类名称" text ,
    "政府经济分类编码" text ,
    "政府经济分类名称" text 
)   TABLESPACE TBS_DMS_DATA
；

CREATE TABLE "会计期间定义"
(
    "会计年份" integer ,
    "会计月份" integer ,
    "开始日期" timestamp without time zone ,
    "截止日期" timestamp without time zone ,
    "导入" integer ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；

CREATE TABLE "会计科目表"
(
    "科目编码" character varying(255) ,
    "科目名称" character varying(255) ,
    "余额方向" integer ,
    "科目类别名称" character varying(255) ,
    "上级科目编码" character varying(255) ,
    "上级科目名称" character varying(255) ,
    "科目全称" character varying(1000) ,
    "科目级别" integer ,
    "是否现金或现金等价物" integer ,
    "是否是标准科目" integer ,
    "是否最底级科目" integer ,
    "备注" character varying(255) ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；

CREATE TABLE "会计科目辅助核算"
(
    "科目编码" character varying(255) ,
    "辅助类型" character varying(255) ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；

CREATE TABLE "全年辅助余额表"
(
    "科目编码" character varying(255) ,
    "会计年份" integer ,
    "会计月份" integer ,
    "余额方向" integer ,
    "本币期初余额" numeric(21,4) ,
    "本币本期借方发生额" numeric(21,4) ,
    "本币本期贷方发生额" numeric(21,4) ,
    "本币期末余额" numeric(21,4) ,
    "外币期初余额" numeric(21,4) ,
    "外币本期借方发生额" numeric(21,4) ,
    "外币本期贷方发生额" numeric(21,4) ,
    "外币期末余额" numeric(21,4) ,
    "凭证数量" integer ,
    "科目名称" character varying(255) ,
    "上级编码" character varying(255) ,
    "是否最底级" integer ,
    "辅助类型" character varying(255) ,
    "辅助编码" character varying(255) ,
    "辅助名称" character varying(255) ,
    "辅助级别" character varying(255) ,
    "上级辅助编码" character varying(255) ,
    "上级科目编码" character varying(255) ,
    "科目级别" integer ,
    "是否最底级科目" integer ,
    "币种代码" character varying(255) ,
    "科目全称" character varying(255) ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；

CREATE TABLE "凭证库"
(
    "源凭证号" character varying(255) ,
    "凭证日期" character varying(20) ,
    "凭证号" character varying(255) ,
    "科目编码" character varying(255) ,
    "科目名称" character varying(255) ,
    "科目全称" character varying(255) ,
    "摘要" character varying(255) ,
    "借方金额" numeric(21,4) ,
    "贷方金额" numeric(21,4) ,
    "凭证类型编号" character varying(255) ,
    "凭证类型名称" character varying(255) ,
    "附件数" integer ,
    "会计年份" integer ,
    "会计月份" integer ,
    "对方科目编码" text ,
    "对方科目名称" text ,
    "财务主管" character varying(255) ,
    "记账人" character varying(255) ,
    "出纳人" character varying(255) ,
    "审核人" character varying(255) ,
    "制单人" character varying(255) ,
    "金额" numeric(21,4) ,
    "备注" character varying(255) ,
    "借方外币金额" numeric(21,4) ,
    "贷方外币金额" numeric(21,4) ,
    "币种代码" character varying(255) ,
    "币种名称" character varying(255) ,
    "汇率" bigint ,
    "是否外币凭证" integer ,
    "选择" integer ,
    "支票号" character varying(255) ,
    "银行单据号" character varying(255) ,
    "分录序号" character varying(255) ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；

CREATE TABLE "凭证明细"
(
    "源凭证号" character varying(255) ,
    "科目编码" character varying(255) ,
    "摘要" character varying(255) ,
    "借方金额" numeric(21,4) ,
    "贷方金额" numeric(21,4) ,
    "借方外币金额" numeric(21,4) ,
    "贷方外币金额" numeric(21,4) ,
    "币种代码" character varying(255) ,
    "币种名称" character varying(255) ,
    "汇率" bigint ,
    "是否外币凭证" integer ,
    "选择" integer ,
    "科目名称" character varying(255) ,
    "支票号" character varying(255) ,
    "分录序号" character varying(255) ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；

CREATE TABLE "凭证表"
(
    "源凭证号" character varying(255) ,
    "凭证日期" timestamp without time zone ,
    "凭证类型名称" character varying(255) ,
    "凭证流水号" character varying(255) ,
    "附件数" integer ,
    "财务主管" character varying(255) ,
    "记账人" character varying(255) ,
    "出纳人" character varying(255) ,
    "审核人" character varying(255) ,
    "制单人" character varying(255) ,
    "金额" numeric(21,4) ,
    "备注" text ,
    "凭证类型编号" character varying(20) ,
    "会计年份" integer ,
    "会计月份" integer ,
    "是否记账" bit(1) ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；

CREATE TABLE "凭证辅助表"
(
    "编码" character varying(50) ,
    "一级单位名称" character varying(50) ,
    "帐套名称" character varying(50) ,
    "分录序号" character varying(255) ,
    "科目编码" character varying(255) ,
    "科目名称" character varying(255) ,
    "摘要" character varying(1000) ,
    "借方金额" numeric(21,4) ,
    "贷方金额" numeric(21,4) ,
    "会计月份" integer ,
    "辅助类型" character varying(255) ,
    "辅助编码" character varying(255) ,
    "辅助名称" character varying(255) ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；

CREATE TABLE "国库支付"
(
    APPLY_NUMBER character varying(100) ,
    PAYID character varying(100) ,
    BUDGET_MONTH character varying(20) ,
    DEPART_CODE text ,
    DEPAT_NAME text ,
    BASE_ORG_ID text ,
    FISK_UNIT_CODE text ,
    BASE_ORG_NAME text ,
    BASE_ORG_CODE text ,
    SUBJECTID text ,
    SUBJECTNAME text ,
    AMOUNT numeric(18,2) ,
    USEAGE character varying(300) ,
    INTEMID text ,
    ITEMNAME text ,
    BUDGET_SOURCE text ,
    BUDGET_NAME text ,
    INC_EXP_TYPE text ,
    INC_EXT_NAME text ,
    CAPITAL_TYPE text ,
    CAPITAL_NAME text ,
    PAMENT_TYPE text ,
    PAMENT_NAME text ,
    ECO_TYPE text ,
    ECO_NAME text ,
    PAY_BANK text ,
    PAN_ACCOUNT_NAME character varying(160) ,
    PAN_ACCOUNT_NO character varying(60) ,
    REC_NAME text ,
    REC_BANK character varying(300) ,
    REC_BANK_ACCOUNTNO character varying(300) ,
    CLEAR_METHOD_TYPE character varying(300) ,
    CLEAR_METHOD_NAME character varying(300) ,
    ZFDATE timestamp without time zone ,
    CREATION_DATE timestamp without time zone 
)   TABLESPACE TBS_DMS_DATA
；

CREATE TABLE "支付_实拨"
(
    APPLY_NUMBER character varying(100) ,
    PAYID character varying(100) ,
    BUDGET_MONTH character varying(20) ,
    DEPART_CODE text ,
    DEPAT_NAME text ,
    BASE_ORG_ID text ,
    FISK_UNIT_CODE text ,
    BASE_ORG_NAME text ,
    BASE_ORG_CODE text ,
    SUBJECTID text ,
    SUBJECTNAME text ,
    AMOUNT numeric(18,2) ,
    USEAGE character varying(300) ,
    INTEMID text ,
    ITEMNAME text ,
    BUDGET_SOURCE text ,
    BUDGET_NAME text ,
    INC_EXP_TYPE text ,
    INC_EXT_NAME text ,
    CAPITAL_TYPE text ,
    CAPITAL_NAME text ,
    PAMENT_TYPE text ,
    PAMENT_NAME text ,
    ECO_TYPE text ,
    ECO_NAME text ,
    PAY_BANK text ,
    PAN_ACCOUNT_NAME character varying(160) ,
    PAN_ACCOUNT_NO character varying(60) ,
    REC_NAME text ,
    REC_BANK character varying(300) ,
    REC_BANK_ACCOUNTNO character varying(300) ,
    CLEAR_METHOD_TYPE character varying(300) ,
    CLEAR_METHOD_NAME character varying(300) ,
    ZFDATE timestamp without time zone ,
    CREATION_DATE timestamp without time zone 
)   TABLESPACE TBS_DMS_DATA
；

CREATE TABLE "支付_授权支付"
(
    APPLY_NUMBER character varying(100) ,
    PAYID character varying(100) ,
    BUDGET_MONTH character varying(20) ,
    DEPART_CODE text ,
    DEPAT_NAME text ,
    BASE_ORG_ID text ,
    FISK_UNIT_CODE text ,
    BASE_ORG_NAME text ,
    BASE_ORG_CODE text ,
    SUBJECTID text ,
    SUBJECTNAME text ,
    AMOUNT numeric(18,2) ,
    USEAGE character varying(300) ,
    INTEMID text ,
    ITEMNAME text ,
    BUDGET_SOURCE text ,
    BUDGET_NAME text ,
    INC_EXP_TYPE text ,
    INC_EXT_NAME text ,
    CAPITAL_TYPE text ,
    CAPITAL_NAME text ,
    PAMENT_TYPE text ,
    PAMENT_NAME text ,
    ECO_TYPE text ,
    ECO_NAME text ,
    PAY_BANK text ,
    PAN_ACCOUNT_NAME character varying(160) ,
    PAN_ACCOUNT_NO character varying(60) ,
    REC_NAME text ,
    REC_BANK character varying(300) ,
    REC_BANK_ACCOUNTNO character varying(300) ,
    CLEAR_METHOD_TYPE character varying(300) ,
    CLEAR_METHOD_NAME character varying(300) ,
    ZFDATE timestamp without time zone ,
    CREATION_DATE timestamp without time zone 
)   TABLESPACE TBS_DMS_DATA
；
CREATE TABLE "支付_直接支付"
(
    APPLY_NUMBER character varying(100) ,
    PAYID character varying(100) ,
    BUDGET_MONTH character varying(20) ,
    DEPART_CODE text ,
    DEPAT_NAME text ,
    BASE_ORG_ID text ,
    FISK_UNIT_CODE text ,
    BASE_ORG_NAME text ,
    BASE_ORG_CODE text ,
    SUBJECTID text ,
    SUBJECTNAME text ,
    AMOUNT numeric(18,2) ,
    USEAGE character varying(300) ,
    INTEMID text ,
    ITEMNAME text ,
    BUDGET_SOURCE text ,
    BUDGET_NAME text ,
    INC_EXP_TYPE text ,
    INC_EXT_NAME text ,
    CAPITAL_TYPE text ,
    CAPITAL_NAME text ,
    PAMENT_TYPE text ,
    PAMENT_NAME text ,
    ECO_TYPE text ,
    ECO_NAME text ,
    PAY_BANK text ,
    PAN_ACCOUNT_NAME character varying(160) ,
    PAN_ACCOUNT_NO character varying(60) ,
    REC_NAME text ,
    REC_BANK character varying(300) ,
    REC_BANK_ACCOUNTNO character varying(300) ,
    CLEAR_METHOD_TYPE character varying(300) ,
    CLEAR_METHOD_NAME character varying(300) ,
    ZFDATE timestamp without time zone ,
    CREATION_DATE timestamp without time zone 
)   TABLESPACE TBS_DMS_DATA
；
CREATE TABLE "科目余额"
(
    "编码" character varying(50) ,
    "一级单位名称" character varying(50) ,
    "帐套名称" character varying(50) ,
    "科目编码" character varying(255) ,
    "科目名称" character varying(255) ,
    "期初余额" numeric(21,4) ,
    "借方发生额" numeric(21,4) ,
    "贷方发生额" numeric(21,4) ,
    "期末余额" numeric(21,4) ,
    "分录数" integer ,
    "余额方向" integer ,
    "科目级别" integer ,
    "上级科目编码" character varying(255) ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；
CREATE TABLE "科目余额表"
(
    "科目编码" character varying(255) ,
    "本币期初余额" numeric(21,4) ,
    "余额方向" integer ,
    "会计年份" integer ,
    "会计月份" integer ,
    "科目名称" character varying(255) ,
    "是否现金或现金等价物" integer ,
    "外币期初余额" numeric(21,4) ,
    "本币本期借方发生额" numeric(21,4) ,
    "外币本期借方发生额" numeric(21,4) ,
    "本币本期贷方发生额" numeric(21,4) ,
    "外币本期贷方发生额" numeric(21,4) ,
    "科目全称" character varying(255) ,
    "科目级别" integer ,
    "是否最底级科目" integer ,
    "币种代码" character varying(255) ,
    "上级科目编码" character varying(255) ,
    "分录数" integer ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；
CREATE TABLE "科目设置表"
(
    "第一级科目长度" integer ,
    "第二级科目长度" integer ,
    "第三级科目长度" integer ,
    "第四级科目长度" integer ,
    "第五级科目长度" integer ,
    "第六级科目长度" integer ,
    "第七级科目长度" integer ,
    "第八级科目长度" integer ,
    "第九级科目长度" integer ,
    "第十级科目长度" integer ,
    "第十一级科目长度" integer ,
    "第十二级科目长度" integer ,
    "第十三级科目长度" integer ,
    "第十四级科目长度" integer ,
    "第十五级科目长度" integer ,
    "第十六级科目长度" integer ,
    "第十七级科目长度" integer ,
    "第十八级科目长度" integer ,
    "第十九级科目长度" integer ,
    "第二十级科目长度" integer ,
    "编码分割符" character varying(50) ,
    "科目编码" character varying(255) ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；
CREATE TABLE "财政部北京定点会议单位名称"
(
    "序号" character varying(50) ,
    "会议定点场所名称" character varying(500) ,
    "预订电话" character varying(50) ,
    "位置" character varying(500) ,
    "网址" character varying(500) 
)   TABLESPACE TBS_DMS_DATA
；
CREATE TABLE "辅助余额期初表"
(
    "编码" character varying(50) ,
    "帐套名称" character varying(50) ,
    "科目编码" character varying(255) ,
    "余额方向" character varying(50) ,
    "期初余额" numeric(21,4) ,
    "辅助类型" character varying(255) ,
    "辅助编码" character varying(255) ,
    "辅助名称" character varying(255) ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；
CREATE TABLE "辅助余额表"
(
    "编码" character varying(255) ,
    "一级单位名称" character varying(255) ,
    "帐套名称" character varying(255) ,
    "科目编码" character varying(255) ,
    "会计年份" integer ,
    "会计月份" integer ,
    "余额方向" integer ,
    "本币期初余额" numeric(21,4) ,
    "本币本期借方发生额" numeric(21,4) ,
    "本币本期贷方发生额" numeric(21,4) ,
    "本币期末余额" numeric(21,4) ,
    "外币期初余额" numeric(21,4) ,
    "外币本期借方发生额" numeric(21,4) ,
    "外币本期贷方发生额" numeric(21,4) ,
    "外币期末余额" numeric(21,4) ,
    "科目名称" character varying(255) ,
    "科目级别" integer ,
    "是否最底级科目" integer ,
    "币种代码" character varying(255) ,
    "上级科目编码" character varying(255) ,
    "分录数" integer ,
    "是否最底级" integer ,
    "辅助类型" character varying(255) ,
    "辅助编码" character varying(255) ,
    "辅助名称" character varying(255) ,
    "辅助级别" character varying(255) ,
    "上级辅助编码" character varying(255) ,
    "科目全称" character varying(255) ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；
CREATE TABLE "辅助信息表"
(
    "编码" character varying(255) ,
    "一级单位名称" character varying(255) ,
    "帐套名称" character varying(255) ,
    "辅助类型" character varying(255) ,
    "辅助编码" character varying(255) ,
    "辅助名称" character varying(255) ,
    "辅助说明" character varying(255) ,
    "辅助级别" character varying(255) ,
    "上级辅助编码" character varying(255) ,
    "年度" integer ,
    "单位名称" character varying(255) ,
    "电子数据编号" character varying(50) ,
    "电子数据名称" character varying(255) 
)   TABLESPACE TBS_DMS_DATA
；

create table T_DMS_AUDI_METH_COL 
 
( 
   ID                   INT AUTO_INCREMENT UNIQUE,	
   METH_CD              VARCHAR(20),
   PARA_ID              INT,
   SORT                 INT,
   COLUMN_DES           VARCHAR(100),
   DEFAUT_VALUE         VARCHAR(100),
   VALUE_MIN	        NUMERIC,
   VALUE_MAX	        NUMERIC,
   CREATE_USER          VARCHAR(20),
   CREATE_USER_NM       VARCHAR(100),
   CREATE_TM        TIMESTAMP,
   UPDATE_USER_ID       VARCHAR(20),
   UPDATE_USER_NM       VARCHAR(100),
   UPDATE_TM            TIMESTAMP
   

)TABLESPACE TBS_DMS_DATA；

comment on table T_DMS_AUDI_METH_COL is'输入字段对照表'；

comment on column T_DMS_AUDI_METH_COL.ID is '唯一标示'；


comment on column T_DMS_AUDI_METH_COL.METH_CD is
'方法编码'；

comment on column T_DMS_AUDI_METH_COL.PARA_ID is
'参数ID'；

comment on column T_DMS_AUDI_METH_COL.SORT is
'排序'；

comment on column T_DMS_AUDI_METH_COL.COLUMN_DES is
'字段描述'；

comment on column T_DMS_AUDI_METH_COL.DEFAUT_VALUE is
'默认显示值'；

comment on column T_DMS_AUDI_METH_COL.VALUE_MIN is '取值最小值'；

comment on column T_DMS_AUDI_METH_COL.VALUE_MAX is '取值最大值'；


comment on column T_DMS_AUDI_METH_COL.CREATE_USER is
'创建人'；

comment on column T_DMS_AUDI_METH_COL.CREATE_USER_NM is
'创建人名称'；

comment on column T_DMS_AUDI_METH_COL.CREATE_TM is
'创建时间'；

comment on column T_DMS_AUDI_METH_COL.UPDATE_USER_ID is
'更新人'；

comment on column T_DMS_AUDI_METH_COL.UPDATE_USER_NM is
'更新人名称'；

comment on column T_DMS_AUDI_METH_COL.UPDATE_TM is
'更新时间'；


create table T_DMS_AUDI_METH_PARA 
(
   ID                   INT AUTO_INCREMENT,
   PARA_EN              VARCHAR(100),
   PARA_NM              VARCHAR(100),
   INPUT_TYPE           VARCHAR(100),
   DOWN_METH            VARCHAR(100),
   DOWN_SORT            VARCHAR(100),
   CREATE_USER          VARCHAR(20),
   CREATE_USER_NM       VARCHAR(100),
   CREATE_TM           TIMESTAMP,
   UPDATE_USER_ID       VARCHAR(20),
   UPDATE_USER_NM       VARCHAR(100),
   UPDATE_TM            TIMESTAMP,
   PRIMARY KEY (id)
)TABLESPACE TBS_DMS_DATA；
comment on table T_DMS_AUDI_METH_PARA is'参数管理表'；

comment on column T_DMS_AUDI_METH_PARA.ID is
'唯一标示'；

comment on column T_DMS_AUDI_METH_PARA.PARA_EN is
'参数英文名'；

comment on column T_DMS_AUDI_METH_PARA.PARA_NM is
'参数中文名'；

comment on column T_DMS_AUDI_METH_PARA.INPUT_TYPE is
'输入类型'；

comment on column T_DMS_AUDI_METH_PARA.DOWN_METH is
'下拉方式'；

comment on column T_DMS_AUDI_METH_PARA.DOWN_SORT is
'下拉取值'；

comment on column T_DMS_AUDI_METH_PARA.CREATE_USER is
'创建人'；

comment on column T_DMS_AUDI_METH_PARA.CREATE_USER_NM is
'创建人名称'；

comment on column T_DMS_AUDI_METH_PARA.CREATE_TM is
'创建时间'；

comment on column T_DMS_AUDI_METH_PARA.UPDATE_USER_ID is
'更新人'；

comment on column T_DMS_AUDI_METH_PARA.UPDATE_USER_NM is
'更新人名称'；

comment on column T_DMS_AUDI_METH_PARA.UPDATE_TM is
'更新时间'；


create table T_DMS_AUDI_METH_SQL 
(
   ID                   INT AUTO_INCREMENT ,	
   METH_CD              VARCHAR(20),
   SQL_TYPE             VARCHAR(100),
   SQL_BLOCK            TEXT,
   EXEC_ORDER           INT,
   CREATE_USER          VARCHAR(20),
   CREATE_USER_NM       VARCHAR(100),
   CREATE_TM           TIMESTAMP,
   UPDATE_USER_ID       VARCHAR(20),
   UPDATE_USER_NM       VARCHAR(100),
   UPDATE_TM            TIMESTAMP,
   PRIMARY KEY (id)
)TABLESPACE TBS_DMS_DATA；

comment on table T_DMS_AUDI_METH_SQL is'SQL存储表'；
comment on column T_DMS_AUDI_METH_SQL.ID is '唯一标示'；
comment on column T_DMS_AUDI_METH_SQL.METH_CD is
'方法编码'；

comment on column T_DMS_AUDI_METH_SQL.SQL_TYPE is
'SQL类型'；

comment on column T_DMS_AUDI_METH_SQL.SQL_BLOCK is
'SQL块'；

comment on column T_DMS_AUDI_METH_SQL.EXEC_ORDER is
'执行顺序'；

comment on column T_DMS_AUDI_METH_SQL.CREATE_USER is
'创建人'；

comment on column T_DMS_AUDI_METH_SQL.CREATE_USER_NM is
'创建人名称'；

comment on column T_DMS_AUDI_METH_SQL.CREATE_TM is
'创建时间'；

comment on column T_DMS_AUDI_METH_SQL.UPDATE_USER_ID is
'更新人'；

comment on column T_DMS_AUDI_METH_SQL.UPDATE_USER_NM is
'更新人名称'；

comment on column T_DMS_AUDI_METH_SQL.UPDATE_TM is
'更新时间'；


create table T_DMS_AUDI_METH_TAB 
(
   
    METH_CD	VARCHAR(20)
,METH_NM	VARCHAR(100)
,RESUL_TAB	VARCHAR(100)
,CREATE_SQL	TEXT
,AUDI_TIPS	VARCHAR(500)
,METH_EXP	VARCHAR(500)
,USE_STAT	VARCHAR(500)
,CREATE_USER	VARCHAR(20)
,CREATE_USER_NM	VARCHAR(100)	
,CREATE_TM	TIMESTAMP
,UPDATE_USER_ID	VARCHAR(20)
,UPDATE_USER_NM	VARCHAR(100)
,UPDATE_TM	TIMESTAMP
,STAR_DATE	TIMESTAMP
,CLASS_NM	VARCHAR(100)
,ATTR_CLASS	VARCHAR(50) NOT NULL
,MODE_CLASS	VARCHAR(50) NOT NULL
,ONE_NM character varying(100) 
,TWO_NM character varying(100) 
,SOURCE_TABLE	VARCHAR(500)
,AUDIT_TARGET_CLOUMN	VARCHAR(200)
   ,PRIMARY KEY (METH_CD)
)TABLESPACE TBS_DMS_DATA；
comment on table T_DMS_AUDI_METH_TAB is'审计方法表'；
comment on column T_DMS_AUDI_METH_TAB.METH_CD is '方法编码'；
comment on column T_DMS_AUDI_METH_TAB.METH_NM is '方法名称'；
comment on column T_DMS_AUDI_METH_TAB.RESUL_TAB is '结果表名'；
comment on column T_DMS_AUDI_METH_TAB.CREATE_SQL is '建表SQL'；
comment on column T_DMS_AUDI_METH_TAB.AUDI_TIPS is '审计提示'；
comment on column T_DMS_AUDI_METH_TAB.METH_EXP is '方法说明'；
comment on column T_DMS_AUDI_METH_TAB.USE_STAT is '使用法规'；
comment on column T_DMS_AUDI_METH_TAB.CREATE_USER is '创建人'；
comment on column T_DMS_AUDI_METH_TAB.CREATE_USER_NM is '创建人名称'；
comment on column T_DMS_AUDI_METH_TAB.CREATE_TM is '创建时间'；
comment on column T_DMS_AUDI_METH_TAB.UPDATE_USER_ID is '更新人'；
comment on column T_DMS_AUDI_METH_TAB.UPDATE_USER_NM is '更新人名称'；
comment on column T_DMS_AUDI_METH_TAB.UPDATE_TM is '更新时间'；
comment on column T_DMS_AUDI_METH_TAB.STAR_DATE is '启用日期'；
comment on column T_DMS_AUDI_METH_TAB.CLASS_NM is '分类'；
comment on column T_DMS_AUDI_METH_TAB.ATTR_CLASS is '属性分类'；
comment on column T_DMS_AUDI_METH_TAB.MODE_CLASS is '模型分类'；
COMMENT ON COLUMN T_DMS_AUDI_METH_TAB.ONE_NM IS '一级名称'；
COMMENT ON COLUMN T_DMS_AUDI_METH_TAB.TWO_NM IS '二级名称'；
COMMENT ON COLUMN T_DMS_AUDI_METH_TAB.SOURCE_TABLE IS '源数据表名称'；
COMMENT ON COLUMN T_DMS_AUDI_METH_TAB.AUDIT_TARGET_CLOUMN IS '审计目标字段'；




